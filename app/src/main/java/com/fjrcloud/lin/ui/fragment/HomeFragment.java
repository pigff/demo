package com.fjrcloud.lin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.adapter.MultiAdapter;
import com.fjrcloud.lin.model.bean.CategoryBean;
import com.fjrcloud.lin.model.bean.Multi;
import com.fjrcloud.lin.model.bean.NewsBean;
import com.fjrcloud.lin.model.domain.Article;
import com.fjrcloud.lin.ui.activity.DetailedActivity;
import com.fjrcloud.lin.ui.activity.MoreActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

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
        getData();
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
                                bundle.putSerializable(Constant.BEAN, ((Multi) baseQuickAdapter.getItem(i)).getNews());
                                intent.putExtra(Constant.BUNDLE, bundle);
                                intent.putExtra(Constant.TITLE, "相关推荐");
                                startActivity(intent);
                                break;
                            case R.id.big_image_more:
                                Intent intent2List = new Intent(getActivity(), MoreActivity.class);
                                intent2List.putExtra(Constant.TITLE, "相关推荐");
                                startActivity(intent2List);
                                break;
                        }
                        break;
                    case Multi.NEWS_RIGHT:
                        Intent intent = new Intent(getActivity(), DetailedActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.BEAN, ((Multi) baseQuickAdapter.getItem(i)).getNews());
                        intent.putExtra(Constant.TITLE, "相关推荐");
                        intent.putExtra(Constant.BUNDLE, bundle);
                        startActivity(intent);
//                        Toast.makeText(getActivity(), ((Multi) baseQuickAdapter.getItem(i)).getNews().getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case Multi.CATEGORY:
                        Intent intent2List = new Intent(getActivity(), MoreActivity.class);
                        intent2List.putExtra(Constant.TITLE, ((Multi) baseQuickAdapter.getItem(i)).getCategory());
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
        CategoryBean.Category[] imgs = new CategoryBean.Category[]{
                new CategoryBean.Category("haha", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("haha", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("haha", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg"),
                new CategoryBean.Category("haha", "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1112/28/c11/10084076_10084076_1325087736046.jpg")};
        Multi multi = new Multi(Multi.BANNER, imgs, Multi.NORMAL_SIZE);
        mMultis.add(multi);

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
    private void getData() {
        getCategories(new Article().new FindAllCategory());
    }

    private void getCategories(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<CategoryBean>() {
            @Override
            public void onSuccess(CategoryBean result) {
                List<Multi> multis = new ArrayList<>();
                for (int i = 0; i < result.getData().size(); i++) {
                    multis.add(new Multi(Multi.CATEGORY, result.getData().get(i), Multi.CATEGORY_SIZE));
                }
                mMultiAdapter.addData(multis);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), R.string.unknow_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Multi multi = new Multi(Multi.DIVIDING, Multi.NORMAL_SIZE);
                mMultis.add(multi);
                getAllNews(new Article().new FindAllArticles(0, 6));
            }
        });
    }

    private void getAllNews(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean result) {
                List<Multi> mulitis = new ArrayList<>();
                for (int i = 0; i < result.getData().getContent().size(); i++) {
                    if (i == 0) {
                        mulitis.add(new Multi(Multi.BIG_IMG, result.getData().getContent().get(i), Multi.NORMAL_SIZE));
                    } else {
                        mulitis.add(new Multi(Multi.NEWS_RIGHT, result.getData().getContent().get(i), Multi.NORMAL_SIZE));
                    }
                }
                mMultiAdapter.addData(mulitis);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), R.string.unknow_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
