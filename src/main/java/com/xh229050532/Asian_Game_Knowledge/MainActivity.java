package com.xh229050532.Asian_Game_Knowledge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import dbhelper.QuizDbHelper;

// 主页面的实现类
public class MainActivity<MyActivity> extends AppCompatActivity {

    // 主页面控件
    private ListView lv; // 列表控件
    private LvAdapter lvAdapter; // 列表适配器
    private List<TiBean> list; // 题目列表
    private QuizDbHelper dbHelper; // 数据库帮助类
    private List<App> questionList; // 问题列表

    // 创建活动时调用，初始化主页面的控件和事件处理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    // 初始化控件
    private void initView() {
        lv = findViewById(R.id.lv);
        Button btn1 = findViewById(R.id.btn1);
        TextView tv_me = findViewById(R.id.tv_me);
        Button btn2 = findViewById(R.id.btn2);
        list = new ArrayList<>();
        lvAdapter = new LvAdapter(this, R.layout.item_ti, list);
        lv.setAdapter(lvAdapter);
        getData();

        // 设置事件处理
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        });
        tv_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main4Activity.class));
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });

    }

    // 重新获取数据并刷新列表
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    // 获取数据
    private void getData() {
        list.clear();
        // 读取已保存的题目
        SharedPreferences ti = getSharedPreferences("ti", MODE_PRIVATE);
        String tistr = ti.getString("ti", new Gson().toJson(new Ti()));
        System.out.println("======" + tistr);
        Ti fromJson = new Gson().fromJson(tistr, Ti.class);
        List<Integer> ids = fromJson.getIds(); // 获取题目id列表
        // 遍历题目列表，将已选择的添加到列表中
        for (int i = 0; i < App.ti.size(); i++) {
            TiBean tiBean = App.ti.get(i);
            for (int j = 0; j < ids.size(); j++) {
                if (tiBean.getId() == ids.get(j)) {
                    list.add(tiBean);
                }
            }
        }
        lvAdapter.notifyDataSetChanged(); // 刷新列表
    }

    // 加载问题列表
    private void loadQuizQuestions() {
        dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions(); // 获取问题列表

        // 如果问题列表为空，则向数据库添加问题并重新获取问题列表
        if (questionList.isEmpty()) {
            dbHelper.fillQuestionsTable();
            questionList = dbHelper.getAllQuestions();
        }
    }

    // 显示下一个问题
    private void showNextQuestion() {
        // 提供了动态地根据问题列表去进行下一道题目的切换
        int currentIndex = 0;
        currentIndex++;

        if (currentIndex < questionList.size()) {
            displayQuestion();
        } else {
            showQuizSummary();
        }
    }

    // 显示测验总结
    private void showQuizSummary() {
        //TODO显示测验总结
    }

    // 显示问题
    private void displayQuestion() {
        int currentIndex = 0;
        App currentQuestion = questionList.get(currentIndex);
        BreakIterator questionTextView = null;
        questionTextView.setText(currentQuestion.getQuestion()); // 设置问题文本
        BreakIterator option1Button = null;
        option1Button.setText(currentQuestion.getOption1()); // 设置选项1文本
        BreakIterator option2Button = null;
        option2Button.setText(currentQuestion.getOption2()); // 设置选项2文本
        BreakIterator option3Button = null;
        option3Button.setText(currentQuestion.getOption3()); // 设置选项3文本
        setAnswer(currentQuestion.getAnswerNr()); // 设置答案
    }

    // 设置答案
    private void setAnswer(int answerNr) {
        int selectedAnswer = answerNr;
        View option1Button = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            option1Button.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }
        View option2Button = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            option2Button.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }
        View option3Button = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            option3Button.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }
        // 根据答案设置选中状态
        switch(answerNr) {
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    option1Button.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_answer));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    option2Button.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_answer));
                }
                break;
            case 3:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    option3Button.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_button_answer));
                }
                break;
        }
    }

}
