package com.xh229050532.Asian_Game_Knowledge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//一个继承自 ArrayAdapter 的自定义适配器 FenAdapter，用于在列表中展示分数信息
public class FenAdapter extends ArrayAdapter {
    private List<FenBean.FenBean2> list;// 数据源
    private int resourceId;// 布局资源 ID

    //构造函数
    public FenAdapter(@NonNull Context context, int resource, List<FenBean.FenBean2> list) {
        super(context, resource);
        resourceId = resource;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();

    }

    /**
     * 用于将数据绑定到 View 上
     * @param position 当前展示的列表项的位置
     * @param convertView 之前加载好的布局进行缓存
     * @param parent 父容器
     * @return 返回当前展示的 View
     */
    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 设置控件属性
        holder.tv_ti1.setText((position + 1) + "");
        holder.tv_ti2.setText(list.get(position).getFen() + "");
        long time = list.get(position).getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(time);
        String stime = simpleDateFormat.format(date);
        holder.tv_ti3.setText(stime);
        return convertView;
    }

    //缓存 ViewHolder
    class ViewHolder {
        TextView tv_ti1;
        TextView tv_ti2;
        TextView tv_ti3;

        public ViewHolder(View v) {
            tv_ti1 = v.findViewById(R.id.tv_ti1);
            tv_ti2 = v.findViewById(R.id.tv_ti2);
            tv_ti3 = v.findViewById(R.id.tv_ti3);
        }
    }
}
