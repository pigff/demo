package com.fjrcloud.lin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.Category;

import java.util.List;

/**
 * Created by lin on 2016/8/1.
 */
public class CenterGridAdapter extends BaseAdapter {

    private Context mContext;

    private List<Category> mCategories;

    public CenterGridAdapter(Context context, List<Category> categories) {
        mContext = context;
        mCategories = categories;
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.home_grid_item, parent, false);
            holder = new ViewHolder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.home_grid_image_item);
            holder.mTextView = (TextView) convertView.findViewById(R.id.home_grid_text_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImageView.setImageResource(mCategories.get(position).getImg());
        holder.mTextView.setText(mCategories.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        ImageView mImageView;

        TextView mTextView;
    }
}
