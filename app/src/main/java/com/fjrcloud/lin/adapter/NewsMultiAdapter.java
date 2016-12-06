package com.fjrcloud.lin.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.AdBean;
import com.fjrcloud.lin.model.bean.NewsMulti;
import com.fjrcloud.lin.util.Constant;
import com.fjrcloud.lin.util.DateUtil;
import com.fjrcloud.lin.util.GlideCircleTransform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by lin on 2016/11/24.
 */
public class NewsMultiAdapter extends BaseMultiItemQuickAdapter<NewsMulti, BaseViewHolder> {


    public NewsMultiAdapter(List<NewsMulti> data) {
        super(data);
        addItemType(NewsMulti.BANNER, R.layout.banner);
        addItemType(NewsMulti.CATEGORY, R.layout.home_category_item);
        addItemType(NewsMulti.BIG_IMG, R.layout.big_img_item);
        addItemType(NewsMulti.NEWS_RIGHT, R.layout.right_img_item);
        addItemType(NewsMulti.DIVIDING, R.layout.divide_item);
        addItemType(NewsMulti.TEXT_IMG, R.layout.text_img);
        addItemType(NewsMulti.NEWS_LEFT, R.layout.left_img_item);
        addItemType(NewsMulti.BIG_BANNER, R.layout.big_banner_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsMulti multi) {
        switch (baseViewHolder.getItemViewType()) {
            case NewsMulti.BIG_BANNER:
                List<String> bigNames = new ArrayList<>();
                BGABanner bigBanner = baseViewHolder.getView(R.id.big_banner);
                if (multi.getBannerImgs().length > 0 &&
                        !TextUtils.equals(multi.getBannerImgs()[0].getTitle(), "广告位招租")) {
                    bigBanner.setAdapter(new BGABanner.Adapter() {
                        @Override
                        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                            Glide.with(mContext).load(Constant.SERVICE_HOST + ((AdBean.DataEntity.Ad) model).getImgPath()).
                                    fitCenter().error(R.mipmap.no_img).into((ImageView) view);
                        }
                    });
                } else {
                    bigBanner.setAdapter(new BGABanner.Adapter() {
                        @Override
                        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                            Glide.with(mContext).load(((AdBean.DataEntity.Ad) model).getImgPath()).
                                    fitCenter().error(R.mipmap.no_img).into((ImageView) view);
                        }
                    });
                }
                for (int i = 0; i < multi.getBannerImgs().length; i++) {
                    bigNames.add(multi.getBannerImgs()[i].getTitle());
                }
                bigBanner.setData(Arrays.asList(multi.getBannerImgs()), bigNames);
                bigBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//                        Toast.makeText(mContext, ((CategoryBean.Category) model).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case NewsMulti.BANNER:
                List<String> names = new ArrayList<>();
                BGABanner bgaBanner = baseViewHolder.getView(R.id.common_banner);
                if (multi.getBannerImgs().length > 0 &&
                        !TextUtils.equals(multi.getBannerImgs()[0].getTitle(), "广告位招租")) {
                    bgaBanner.setAdapter(new BGABanner.Adapter() {
                        @Override
                        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                            Glide.with(mContext).load(Constant.SERVICE_HOST + ((AdBean.DataEntity.Ad) model).getImgPath()).
                                    fitCenter().error(R.mipmap.no_img).into((ImageView) view);
                        }
                    });
                } else {
                    bgaBanner.setAdapter(new BGABanner.Adapter() {
                        @Override
                        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                        Glide.with(mContext).load(((AdBean.DataEntity.Ad) model).getImgPath()).
                                error(R.mipmap.no_img).into((ImageView) view);
                        }
                    });
                }
                for (int i = 0; i < multi.getBannerImgs().length; i++) {
                    names.add(multi.getBannerImgs()[i].getTitle());
                }
                bgaBanner.setData(Arrays.asList(multi.getBannerImgs()), names);
                bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//                        Toast.makeText(mContext, ((CategoryBean.Category) model).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case NewsMulti.CATEGORY:
                baseViewHolder.setText(R.id.category_text, multi.getCategory().getName())
                        .addOnClickListener(R.id.category_group);
                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getCategory().getImgPath()).error(R.mipmap.no_img).
                        transform(new GlideCircleTransform(mContext)).into((ImageView) baseViewHolder.getView(R.id.category_img));
                break;
            case NewsMulti.BIG_IMG:
                baseViewHolder.setText(R.id.big_image_title, multi.getNews().getTitle())
                        .addOnClickListener(R.id.big_image_more)
                        .addOnClickListener(R.id.big_image);
                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getNews().getImgPath()).
                        error(R.mipmap.no_img).into((ImageView) baseViewHolder.getView(R.id.big_image));
                break;
            case NewsMulti.NEWS_RIGHT:
                baseViewHolder.setText(R.id.right_item_title, multi.getNews().getTitle())
                        .setText(R.id.right_item_content, multi.getNews().getContent())
                        .setText(R.id.right_item_time, DateUtil.getDateToString(multi.getNews().getCreateTime()))
                        .addOnClickListener(R.id.right_img_group);

                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getNews().getImgPath()).
                        error(R.mipmap.no_img).into((ImageView) baseViewHolder.getView(R.id.right_img));
                break;
            case NewsMulti.NEWS_LEFT:
                baseViewHolder.setText(R.id.left_item_title, multi.getNews().getTitle())
                        .setText(R.id.left_item_content, multi.getNews().getContent())
                        .addOnClickListener(R.id.left_img_group);
                Glide.with(mContext).load(Constant.SERVICE_HOST + multi.getNews().getImgPath()).error(R.mipmap.no_img).
                        into((ImageView) baseViewHolder.getView(R.id.left_img));
        }
    }
}
