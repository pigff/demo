package com.fjrcloud.lin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fjrcloud.lin.R;
import com.fjrcloud.lin.bean.Town;

import java.util.List;

/**
 * Created by lin on 2016/11/21.
 */
public class TownListAdapter extends BaseAdapter{

    private List<Town> mTowns;

    private LayoutInflater mInflater;

    public TownListAdapter(List<Town> towns, Context context) {
        mTowns = towns;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mTowns.size();
    }

    @Override
    public Object getItem(int position) {
        return mTowns.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.town_lv_item, parent, false);
            holder = new ViewHolder();
            holder.mName = (TextView) convertView.findViewById(R.id.town_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mName.setText(mTowns.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView mName;

    }
}
