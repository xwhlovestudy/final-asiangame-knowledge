package com.xh229050532.Asian_Game_Knowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    // 随机抽取的题目的编号列表
    private List<Integer> randomNum;
    private TextView tv_ti;
    private TextView tv_zhunbei;
    private TextView tv_daojishi;
    private TextView tv_tool;
    private ListView lv_daan;
    private int tool = 0;
    private int down = 0;

    private CountDownTimer countDownTimer2;
    private LinearLayout ll_zunbei;
    private LinearLayout ll_ti;
    private LinearLayout ll_daan;
    private Button btn;
    private Button btn_next;
    private int id;
    private int max = 10;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 获取传入的参数
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        // 初始化视图和控件
        initView();
    }

    private void initView() {
        // 查找布局中的各个视图和控件
        ll_zunbei = findViewById(R.id.ll_zunbei);
        ll_daan = findViewById(R.id.ll_daan);
        ll_ti = findViewById(R.id.ll_ti);
        tv_ti = findViewById(R.id.tv_ti);
        tv_daojishi = findViewById(R.id.tv_daojishi);
        tv_zhunbei = findViewById(R.id.tv_zhunbei);
        tv_tool = findViewById(R.id.tv_tool);
        btn = findViewById(R.id.btn);
        btn_next = findViewById(R.id.btn_next);
        lv_daan = findViewById(R.id.lv_daan);
        chronometer = findViewById(R.id.chronometer);

        // 添加事件监听器，点击按钮可以结束当前Activity
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 添加事件监听器，点击按钮可以切换到下一道题目
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTi();
            }
        });

        // 如果是查看已答题目，那么只显示当前题目即可，无需倒计时
        if (id != -1) {
            max = 1;
            chronometer.setVisibility(View.GONE);
        } else { // 否则显示倒计时
            chronometer.setVisibility(View.VISIBLE);
        }

        // 随机抽取指定数量的题目，用随机编号列表记录题目编号
        randomNum = getRandomNum(0, App.ti.size() - 1, max);

        // 启动倒计时器
        countDownTimer2 = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int i = (int) (millisUntilFinished / 1000);
                tv_zhunbei.setText((i) + "");
            }

            @Override
            public void onFinish() {
                chronometer.start();
                ll_zunbei.setVisibility(View.GONE);
                ll_ti.setVisibility(View.VISIBLE);
                setTi(down);
                // 取消倒计时器
                countDownTimer2.cancel();
            }
        };
        // 如果是查看已答题目，那么不需要倒计时，直接显示题目
        if (id == -1) {
            countDownTimer2.start();
        } else {
            ll_zunbei.setVisibility(View.GONE);
            ll_ti.setVisibility(View.VISIBLE);
            setTi(down);
        }
    }

    // 切换到下一道题目
    private void nextTi() {
        if (down < max - 1) { // 如果还有未答的题目，则切换到下一道题目
            down++;
            setTi(down);
        } else { // 否则统计分数，显示得分和保存答题记录
            ll_zunbei.setVisibility(View.VISIBLE);
            ll_ti.setVisibility(View.GONE);
            tv_zhunbei.setText("");
            tv_tool.setText("你的得分是:" + tool + "分");
            btn.setVisibility(View.VISIBLE);

            // 从SharedPreferences中读取已保存的答题记录
            SharedPreferences ti = getSharedPreferences("fen", MODE_PRIVATE);
            SharedPreferences.Editor edit = ti.edit();
            String tistr = ti.getString("fen", new Gson().toJson(new FenBean()));
            FenBean fromJson = new Gson().fromJson(tistr, FenBean.class);
            FenBean.FenBean2 bean = new FenBean.FenBean2();
            bean.setFen(tool);
            bean.setTime(System.currentTimeMillis());
            // 将本次答题的记录添加到之前的记录列表中
            fromJson.getList().add(bean);
            edit.putString("fen", new Gson().toJson(fromJson));
            edit.commit();
        }
    }

    // 显示指定编号的题目及答案选项
    private void setTi(int i) {
        ll_daan.setVisibility(View.GONE);
        final TiBean tiBean;
        // 如果是查看已答题目，那么直接根据参数id获取指定的题目
        // 否则根据记录的随机编号列表获取题目
        if (id == -1) {
            tiBean = App.ti.get(randomNum.get(i));
        } else {
            tiBean = App.ti.get(id - 1);
        }
        tv_ti.setText((i + 1) + "  " + tiBean.getTi());
        String[] daan = tiBean.getDaan();
        final String[] str = new String[]{"0", "A", "B", "C", "D"};
        lv_daan.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daan));
        // 添加答案选项的事件监听器
        lv_daan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ll_daan.getVisibility() == View.VISIBLE) {
                    return;
                }
                // 显示正确的答案及答题结果，并计算得分
                ll_daan.setVisibility(View.VISIBLE);
                tv_daojishi.setText("正确答案:" + str[tiBean.getDui()] + "\n" + tiBean.getInfo() + "\n");
//                回答正确,加分
                if (position + 1 == tiBean.getDui()) {
                    tool += 10;
                    tv_daojishi.append("回答正确!+10分");
                    san(tiBean.getId());
                } else {
                    tv_daojishi.append("你选择了" + str[position + 1] + ",回答错误");
                    baocun(tiBean.getId());
                }


            }
        });

    }

    private void baocun(int id) {

        SharedPreferences ti = getSharedPreferences("ti", MODE_PRIVATE);
        SharedPreferences.Editor edit = ti.edit();

        // 第1步：获取已保存的题目信息
        String tistr = ti.getString("ti", new Gson().toJson(new Ti()));
        Ti fromJson = new Gson().fromJson(tistr, Ti.class);

        List<Integer> ids = fromJson.getIds();

        // 第2步：判断当前题目是否已保存
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == id) {
                return;
            }
        }

        // 如果当前题目未保存，则进行保存
        fromJson.getIds().add(id);
        edit.putString("ti", new Gson().toJson(fromJson));
        edit.apply();
    }

    private void san(int id) {

        SharedPreferences ti = getSharedPreferences("ti", MODE_PRIVATE);
        SharedPreferences.Editor edit = ti.edit();

        // 第1步：获取已保存的题目信息
        String tistr = ti.getString("ti", new Gson().toJson(new Ti()));
        Ti fromJson = new Gson().fromJson(tistr, Ti.class);

        List<Integer> ids = fromJson.getIds();

        // 第2步：查找当前题目是否已保存
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == id) {
                // 如果已经保存，则将该题目从保存列表中删除
                fromJson.getIds().remove(i);
            }
        }

        // 第3步：保存最新的题目信息
        edit.putString("ti", new Gson().toJson(fromJson));
        edit.apply();
    }



    public List<Integer> getRandomNum(int requMin, int requMax, int targetLength) {
        if (requMax - requMin < 1) {
            System.out.print("最小值和最大值数据有误");
            return null;
        } else if (requMax - requMin < targetLength) {
            System.out.print("指定随机个数超过范围");
            return null;
        }
        int target = targetLength;
        List<Integer> list = new ArrayList<>();

        List<Integer> requList = new ArrayList<>();
        for (int i = requMin; i <= requMax; i++) {
            requList.add(i);
        }

        for (int i = 0; i < targetLength; i++) {

            // 取出一个随机角标
            int r = (int) (Math.random() * requList.size());
            list.add(requList.get(r));

            // 移除已经取过的值
            requList.remove(r);


        }

        return list;
    }
}
