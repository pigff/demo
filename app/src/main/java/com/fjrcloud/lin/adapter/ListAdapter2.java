package com.fjrcloud.lin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.bean.News;

import java.util.List;

/**
 * Created by lin on 2016/11/21.
 */
public class ListAdapter2 extends BaseAdapter{

    private List<News> mNewses;

    private LayoutInflater mInflater;

    public ListAdapter2(List<News> newses, Context context) {
        mNewses = newses;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mNewses.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.left_img_item, parent, false);
            holder = new ViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.left_item_title);
            holder.mContent = (TextView) convertView.findViewById(R.id.left_item_content);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.left_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mNewses.get(position).getTitle());
        holder.mContent.setText(mNewses.get(position).getContent());
        holder.mImageView.setImageResource(mNewses.get(position).getImg());
        return convertView;
    }

    class ViewHolder {
        TextView mTitle;

        TextView mContent;

        ImageView mImageView;
    }
}
