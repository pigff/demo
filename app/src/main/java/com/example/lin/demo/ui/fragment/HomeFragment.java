package com.example.lin.demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.lin.demo.R;
import com.example.lin.demo.bean.Category;
import com.example.lin.demo.bean.Multi;
import com.example.lin.demo.bean.News;
import com.example.lin.demo.ui.activity.DetailedActivity;
import com.example.lin.demo.ui.activity.ListActivity;
import com.example.lin.demo.util.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class HomeFragment extends Fragment{

    private RecyclerView mRv;

    private MultiAdapter mMultiAdapter;

    private List<Multi> mMultis;

    private int count;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRv = (RecyclerView) view.findViewById(R.id.home_rv);
        initData();
        initAdapter();
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        mRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int type = baseQuickAdapter.getItemViewType(i);
                switch (type) {
                    case Multi.BIG_IMG:
                        switch (view.getId()) {
                            case R.id.big_image:
                                Intent intent = new Intent(getActivity(), DetailedActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable(Constant.BEAN, new News("测试", "测试内容", R.mipmap.image, "2012-12"));
                                intent.putExtra(Constant.BUNDLE, bundle);
                                intent.putExtra(Constant.TITLE, "相关推荐");
                                startActivity(intent);
                                break;
                            case R.id.big_image_more:
                                Intent intent2List = new Intent(getActivity(), ListActivity.class);
                                intent2List.putExtra(Constant.TITLE, "相关推荐");
                                startActivity(intent2List);
                                break;
                        }
                        break;
                    case Multi.NEWS:
                        Intent intent = new Intent(getActivity(), DetailedActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.BEAN, ((Multi) baseQuickAdapter.getItem(i)).getNews());
                        intent.putExtra(Constant.TITLE, "相关推荐");
                        intent.putExtra(Constant.BUNDLE, bundle);
                        startActivity(intent);
//                        Toast.makeText(getActivity(), ((Multi) baseQuickAdapter.getItem(i)).getNews().getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case Multi.CATEGORY:
                        Intent intent2List = new Intent(getActivity(), ListActivity.class);
                        intent2List.putExtra(Constant.TITLE, ((Multi) baseQuickAdapter.getItem(i)).getCategory().getName());
                        startActivity(intent2List);
                        break;
                }
            }
        });
    }

    private void initView() {
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mMultiAdapter);
    }

    private void initData() {
        mMultis = new ArrayList<>();
        Integer[] imgs = new Integer[]{R.mipmap.banner_3, R.mipmap.banner_3, R.mipmap.banner_3, R.mipmap.banner_3};
        Multi multi = new Multi(Multi.BANNER, imgs, Multi.NORMAL_SIZE);
        Integer bigImg = R.mipmap.image;
        Multi multi1 = new Multi(Multi.BIG_IMG, bigImg, Multi.NORMAL_SIZE);
        Category category = new Category("发展变迁", R.mipmap.change);
        Category category1 = new Category("地方文化", R.mipmap.culture);
        Category category2 = new Category("特产美食", R.mipmap.food);
        Category category3 = new Category("风景名胜", R.mipmap.attractions);
        Multi multi3 = new Multi(Multi.DIVIDING, Multi.NORMAL_SIZE);
        mMultis.add(multi);
        mMultis.add(new Multi(Multi.CATEGORY, category, Multi.CATEGORY_SIZE));
        mMultis.add(new Multi(Multi.CATEGORY, category1, Multi.CATEGORY_SIZE));
        mMultis.add(new Multi(Multi.CATEGORY, category2, Multi.CATEGORY_SIZE));
        mMultis.add(new Multi(Multi.CATEGORY, category3, Multi.CATEGORY_SIZE));
        mMultis.add(multi3);
        mMultis.add(multi1);
        News news1 = new News("第一个", "第一个内容",
                R.mipmap.image, "2015-12-24");
        News news2 = new News("第二个", "第二个内容", R.mipmap.image, "2015-10-2");
        mMultis.add(new Multi(Multi.NEWS, news1, Multi.NORMAL_SIZE));
        mMultis.add(new Multi(Multi.NEWS, news2, Multi.NORMAL_SIZE));
    }


    private void initAdapter() {
        mMultiAdapter = new MultiAdapter(mMultis);
        mMultiAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                return mMultis.get(i).getSpanSize();
            }
        });
        mMultiAdapter.openLoadAnimation();
//        mMultiAdapter.setOnLoadMoreListener(this);
//        mMultiAdapter.setEnableLoadMore(true);
    }

//    @Override
//    public void onLoadMoreRequested() {
//        if (count < 3) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mMultiAdapter.addData(getData());
//                    count++;
//                    mMultiAdapter.loadMoreComplete();
//                }
//            }, 1000);
//        } else {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mMultiAdapter.loadMoreEnd();
//                }
//            }, 1000);
//        }
//    }

    class MultiAdapter extends BaseMultiItemQuickAdapter<Multi, BaseViewHolder> {
        public MultiAdapter(List<Multi> data) {
            super(data);
            addItemType(Multi.BANNER, R.layout.banner);
            addItemType(Multi.CATEGORY, R.layout.home_category_item);
            addItemType(Multi.BIG_IMG, R.layout.big_img_item);
            addItemType(Multi.NEWS, R.layout.right_img_item);
            addItemType(Multi.DIVIDING, R.layout.divide_item);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, final Multi multi) {
            switch (baseViewHolder.getItemViewType()) {
                case Multi.BANNER:
                    BGABanner bgaBanner = baseViewHolder.getView(R.id.common_banner);
                    bgaBanner.setAdapter(new BGABanner.Adapter() {
                        @Override
                        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                            ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                            ((ImageView) view).setImageResource(((int) model));
                        }
                    });
                    bgaBanner.setData(Arrays.asList(multi.getBannerImgs()), null);
//                    bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
//                        @Override
//                        public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//                        }
//                    });
                    break;
                case Multi.CATEGORY:
                    baseViewHolder.setImageResource(R.id.category_img, multi.getCategory().getImg())
                            .setText(R.id.category_text, multi.getCategory().getName())
                            .addOnClickListener(R.id.category_group);
                    break;
                case Multi.BIG_IMG:
                    baseViewHolder.setImageResource(R.id.big_image, multi.getBigImg())
                            .addOnClickListener(R.id.big_image_more)
                            .addOnClickListener(R.id.big_image);
                    break;
                case Multi.NEWS:
                    baseViewHolder.setImageResource(R.id.right_img, multi.getNews().getImg())
                            .setText(R.id.right_item_title, multi.getNews().getTitle())
                            .setText(R.id.right_item_time, multi.getNews().getTime())
                            .setText(R.id.right_item_content, multi.getNews().getContent())
                            .addOnClickListener(R.id.right_img_group);
                    break;
            }
        }
    }


//    private List<Multi> getData() {
//        List<Multi> multis = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            Multi multi = new Multi(Multi.NEWS, new News("第" + i, "第" + i + "content", R.mipmap.banner_img2, "232323"), Multi.NORMAL_SIZE);
//            Category category = new Category("发展变迁", R.mipmap.change);
//            Category category1 = new Category("地方文化", R.mipmap.culture);
//            Category category2 = new Category("特产美食", R.mipmap.food);
//            Category category3 = new Category("风景名胜", R.mipmap.attractions);
//            multis.add(new Multi(Multi.CATEGORY, category, Multi.CATEGORY_SIZE));
//            multis.add(new Multi(Multi.CATEGORY, category1, Multi.CATEGORY_SIZE));
//            multis.add(new Multi(Multi.CATEGORY, category2, Multi.CATEGORY_SIZE));
//            multis.add(new Multi(Multi.CATEGORY, category3, Multi.CATEGORY_SIZE));
//            multis.add(multi);
//        }
//        return multis;
//    }
}
