package com.example.lin.demo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lin.demo.R;
import com.example.lin.demo.bean.Category;
import com.example.lin.demo.bean.Multi;

import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by lin on 2016/11/24.
 */
public class MultiAdapter extends BaseMultiItemQuickAdapter<Multi, BaseViewHolder> {

    public MultiAdapter(List<Multi> data) {
        super(data);
        addItemType(Multi.BANNER, R.layout.banner);
        addItemType(Multi.CATEGORY, R.layout.home_category_item);
        addItemType(Multi.BIG_IMG, R.layout.big_img_item);
        addItemType(Multi.NEWS_RIGHT, R.layout.right_img_item);
        addItemType(Multi.DIVIDING, R.layout.divide_item);
        addItemType(Multi.TEXT_IMG, R.layout.text_img);
        addItemType(Multi.NEWS_LEFT, R.layout.left_img_item);
        addItemType(Multi.BIG_BANNER, R.layout.big_banner_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Multi multi) {
        switch (baseViewHolder.getItemViewType()) {
            case Multi.BIG_BANNER:
                BGABanner bigBanner = baseViewHolder.getView(R.id.big_banner);
                bigBanner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                        ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                        ((ImageView) view).setImageResource(((Category) model).getImg());
                    }
                });
                bigBanner.setData(Arrays.asList(multi.getBannerImgs()), null);
                bigBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                        Toast.makeText(mContext, ((Category) model).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case Multi.BANNER:
                BGABanner bgaBanner = baseViewHolder.getView(R.id.common_banner);
                bgaBanner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                        ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                        ((ImageView) view).setImageResource(((Category) model).getImg());
                    }
                });
                bgaBanner.setData(Arrays.asList(multi.getBannerImgs()), null);
                bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                        Toast.makeText(mContext, ((Category) model).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case Multi.CATEGORY:
                baseViewHolder.setImageResource(R.id.category_img, multi.getCategory().getImg())
                        .setText(R.id.category_text, multi.getCategory().getName())
                        .addOnClickListener(R.id.category_group);
                break;
            case Multi.BIG_IMG:
                baseViewHolder.setImageResource(R.id.big_image, multi.getNews().getImg())
                        .setText(R.id.big_image_title, multi.getNews().getTitle())
                        .addOnClickListener(R.id.big_image_more)
                        .addOnClickListener(R.id.big_image);
                break;
            case Multi.NEWS_RIGHT:
                baseViewHolder.setImageResource(R.id.right_img, multi.getNews().getImg())
                        .setText(R.id.right_item_title, multi.getNews().getTitle())
                        .setText(R.id.right_item_time, multi.getNews().getTime())
                        .setText(R.id.right_item_content, multi.getNews().getContent())
                        .addOnClickListener(R.id.right_img_group);
                break;
            case Multi.NEWS_LEFT:
                baseViewHolder.setImageResource(R.id.left_img, multi.getNews().getImg())
                        .setText(R.id.left_item_title, multi.getNews().getTitle())
                        .setText(R.id.left_item_content, multi.getNews().getContent())
                        .addOnClickListener(R.id.left_img_group);
        }
    }
}
