package com.xh229050532.Asian_Game_Knowledge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

/*
 * ListView适配器类，用于将数据源中的各列表项填充到ListView中
 */
public class LvAdapter extends ArrayAdapter {
    private List<TiBean> list; // 声明了List类型的数据源
    private int resourceId; // 声明了int类型的ListView列表项布局资源id

    /*
     * 构造函数，用于将数据源与ListView列表项布局资源id关联
     */
    public LvAdapter(@NonNull Context context, int resource, List<TiBean> list) {
        super(context, resource);
        resourceId = resource; // 为布局资源id赋初值
        this.list = list; // 为数据源赋初值
    }

    /*
     * 获取ListView条目数
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /*
     * 为ListView中每个列表项填充数据并返回填充好的列表项视图
     * convertView：之前加载好的布局View，用于进行缓存
     * parent：列表项所在的ListView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) { // 当之前的convertView为空时，获取列表项布局资源并获取Holder对象
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null); // 获取列表项布局资源
            holder = new ViewHolder(convertView); // 获取Holder对象
            convertView.setTag(holder); // 将Holder对象存入convertView的tag中以进行缓存
        } else {
            holder = (ViewHolder) convertView.getTag(); // 当之前的convertView不为空时，从convertView的tag中获取Holder对象
        }
        holder.tvTitle.setText(list.get(position).getTi()); // 为Holder中的TextView控件设置文本
        return convertView; // 返回填充好数据的列表项视图
    }

    /*
     * ViewHolder类，用于存储列表项中的视图控件
     */
    class ViewHolder {
        TextView tvTitle; // 声明了TextView类型的视图控件

        /*
         * 构造函数，用于获取并存储列表项布局的视图控件
         */
        public ViewHolder(View v) {
            tvTitle = v.findViewById(R.id.tv_ti); // 获取TextView视图控件
        }
    }
}
