package com.fjrcloud.lin.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.CategoryBean;
import com.fjrcloud.lin.model.bean.Multi;
import com.fjrcloud.lin.util.Constant;
import com.fjrcloud.lin.util.DateUtil;
import com.fjrcloud.lin.util.GlideCircleTransform;

import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by lin on 2016/11/24.
 */
public class MultiAdapter extends BaseMultiItemQuickAdapter<Multi, BaseViewHolder> {

    private static final String TAG = "MultiAdapter";

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
                        Glide.with(mContext).load(((CategoryBean.Category) model).getImgPath()).
                                error(R.mipmap.no_img).into((ImageView) view);
//                        Glide.with(mContext).load(Constant.SERVICE_HOST + ((CategoryBean.Category) model).getImgPath()).
//                                error(R.mipmap.no_img).into((ImageView) view);
                    }
                });
                bigBanner.setData(Arrays.asList(multi.getBannerImgs()), null);
                bigBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                        Toast.makeText(mContext, ((CategoryBean.Category) model).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case Multi.BANNER:
                BGABanner bgaBanner = baseViewHolder.getView(R.id.common_banner);
                bgaBanner.setData(Arrays.asList(multi.getBannerImgs()), null);
                bgaBanner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                        Glide.with(mContext).load(((CategoryBean.Category) model).getImgPath()).
                                error(R.mipmap.no_img).into((ImageView) view);
//                        Glide.with(mContext).load(Constant.SERVICE_HOST + ((CategoryBean.Category) model).getImgPath()).
//                                error(R.mipmap.no_img).into((ImageView) view);
                    }
                });
                bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                        Toast.makeText(mContext, ((CategoryBean.Category) model).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case Multi.CATEGORY:
                baseViewHolder.setText(R.id.category_text, multi.getCategory().getName())
                        .addOnClickListener(R.id.category_group);
                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getCategory().getImgPath()).error(R.mipmap.no_img).
                        transform(new GlideCircleTransform(mContext)).into((ImageView) baseViewHolder.getView(R.id.category_img));
                break;
            case Multi.BIG_IMG:
                baseViewHolder.setText(R.id.big_image_title, multi.getNews().getTitle())
                        .addOnClickListener(R.id.big_image_more)
                        .addOnClickListener(R.id.big_image);
                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getNews().getImgPath()).
                        error(R.mipmap.no_img).into((ImageView) baseViewHolder.getView(R.id.big_image));
                break;
            case Multi.NEWS_RIGHT:
                baseViewHolder.setText(R.id.right_item_title, multi.getNews().getTitle())
                        .setText(R.id.right_item_content, multi.getNews().getContent())
                        .setText(R.id.right_item_time, DateUtil.getDateToString(multi.getNews().getCreateTime()))
                        .addOnClickListener(R.id.right_img_group);

                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getNews().getImgPath()).
                        error(R.mipmap.no_img).into((ImageView) baseViewHolder.getView(R.id.right_img));
                break;
            case Multi.NEWS_LEFT:
                baseViewHolder.setText(R.id.left_item_title, multi.getNews().getTitle())
                        .setText(R.id.left_item_content, multi.getNews().getContent())
                        .addOnClickListener(R.id.left_img_group);
                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getNews().getImgPath()).error(R.mipmap.no_img).
                        into((ImageView) baseViewHolder.getView(R.id.left_img));
        }
    }
}
