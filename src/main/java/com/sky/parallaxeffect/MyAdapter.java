package com.sky.parallaxeffect;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * 作者：SKY
 * 创建时间：2016-10-4 18:22
 * 描述：适配器
 */
public class MyAdapter extends BaseAdapter {

    public MyAdapter () {
    }

    @Override
    public int getCount () {
        return Cheeses.NAMES.length;
    }

    @Override
    public Object getItem (int position) {
        return getItem(position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        Random random = new Random();
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.list_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 使用picasso必须设置占位图片
        Picasso.with(parent.getContext())
                .load(Cheeses.ICONS[random.nextInt(Cheeses.ICONS.length)])
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .resize(120,120)//指定压缩参考的宽高比
                .into(holder.icon);
//        holder.icon.setImageResource(Cheeses.ICONS[random.nextInt(Cheeses.ICONS.length)]);
        holder.tvName.setText(Cheeses.NAMES[position]);
        holder.tvDesc.setText(Cheeses.DESCS[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
        TextView tvName;
        TextView tvDesc;
    }
}
