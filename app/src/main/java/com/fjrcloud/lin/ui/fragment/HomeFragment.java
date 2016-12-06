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
import com.fjrcloud.lin.adapter.NewsMultiAdapter;
import com.fjrcloud.lin.model.bean.AdBean;
import com.fjrcloud.lin.model.bean.CategoryBean;
import com.fjrcloud.lin.model.bean.NewsMulti;
import com.fjrcloud.lin.model.bean.NewsBean;
import com.fjrcloud.lin.model.domain.Advertising;
import com.fjrcloud.lin.model.domain.Article;
import com.fjrcloud.lin.ui.activity.DetailedActivity;
import com.fjrcloud.lin.ui.activity.MoreActivity;
import com.fjrcloud.lin.util.Constant;
import com.fjrcloud.lin.util.HtmlUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mRv;

    private NewsMultiAdapter mMultiAdapter;

    private List<NewsMulti> mMultis;

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
//        lazyLoad();
        return view;
    }

    private void initListener() {
        mRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int type = baseQuickAdapter.getItemViewType(i);
                switch (type) {
                    case NewsMulti.BIG_IMG:
                        switch (view.getId()) {
                            case R.id.big_image:
                                Intent intent = new Intent(getActivity(), DetailedActivity.class);
                                intent.putExtra(Constant.BEAN, ((NewsMulti) baseQuickAdapter.getItem(i)).getNews());
                                intent.putExtra(Constant.TITLE, "相关推荐");
                                intent.putExtra(Constant.CONTENT, ((NewsMulti) baseQuickAdapter.getItem(i)).getOgContent());
                                startActivity(intent);
                                break;
                            case R.id.big_image_more:
                                Intent intent2List = new Intent(getActivity(), MoreActivity.class);
                                intent2List.putExtra(Constant.TITLE, "相关推荐");
                                startActivity(intent2List);
                                break;
                        }
                        break;
                    case NewsMulti.NEWS_RIGHT:
                        Intent intent = new Intent(getActivity(), DetailedActivity.class);
                        intent.putExtra(Constant.BEAN, ((NewsMulti) baseQuickAdapter.getItem(i)).getNews());
                        intent.putExtra(Constant.TITLE, "相关推荐");
                        intent.putExtra(Constant.CONTENT, ((NewsMulti) baseQuickAdapter.getItem(i)).getOgContent());
                        startActivity(intent);
                        break;
                    case NewsMulti.CATEGORY:
                        Intent intent2List = new Intent(getActivity(), MoreActivity.class);
                        intent2List.putExtra(Constant.TITLE, ((NewsMulti) baseQuickAdapter.getItem(i)).getCategory().getName());
                        intent2List.putExtra(Constant.ID, ((NewsMulti) baseQuickAdapter.getItem(i)).getCategory().getId());
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
//        mIsFirstLoad = false;
//        mIsPrepared = true;
        mMultis = new ArrayList<>();

    }


    private void initAdapter() {
        mMultiAdapter = new NewsMultiAdapter(mMultis);
        mMultiAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                return mMultis.get(i).getSpanSize();
            }
        });
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
        getAd(new Advertising().new FindAdByCategory(null, 0, 8));

    }

    private void getCategories(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<CategoryBean>() {
            @Override
            public void onSuccess(CategoryBean result) {
//                mIsPrepared = true;
                List<NewsMulti> multis = new ArrayList<>();
                for (int i = 0; i < result.getData().size(); i++) {
                    multis.add(new NewsMulti(NewsMulti.CATEGORY, result.getData().get(i), NewsMulti.CATEGORY_SIZE));
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
                NewsMulti multi = new NewsMulti(NewsMulti.DIVIDING, NewsMulti.NORMAL_SIZE);
                mMultis.add(multi);
                getAllNews(new Article().new FindAllArticles(0, 6));
            }
        });
    }

    private void getAllNews(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<NewsBean>() {
            @Override
            public void onSuccess(NewsBean result) {
                List<NewsMulti> mulitis = new ArrayList<>();
                for (int i = 0; i < result.getData().getContent().size(); i++) {
                    String ogContent = result.getData().getContent().get(i).getContent();
                    if (i == 0) {
                        mulitis.add(new NewsMulti(NewsMulti.BIG_IMG, result.getData().getContent().get(i), ogContent, NewsMulti.NORMAL_SIZE));
                    } else {
                        result.getData().getContent().get(i).setContent(HtmlUtil.getTextFromHtml(ogContent));
                        mulitis.add(new NewsMulti(NewsMulti.NEWS_RIGHT, result.getData().getContent().get(i), ogContent, NewsMulti.NORMAL_SIZE));
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

    private void getAd(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<AdBean>() {
            @Override
            public void onSuccess(AdBean result) {
                List<NewsMulti> multis = new ArrayList<NewsMulti>();
                NewsMulti multi = null;
                List<AdBean.DataEntity.Ad> ads = new ArrayList<AdBean.DataEntity.Ad>();
                if (result.getData().getContent().size() > 0) {
                    multi = new NewsMulti(NewsMulti.BANNER, result.getData().getContent().
                            toArray(new AdBean.DataEntity.Ad[result.getData().getContent().size()]), NewsMulti.NORMAL_SIZE);
                } else {
                    multi = new NewsMulti(NewsMulti.BANNER,
                            new AdBean.DataEntity.Ad[]{new AdBean.DataEntity.Ad("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg")}, NewsMulti.NORMAL_SIZE);
                }

                multis.add(multi);
                mMultiAdapter.addData(multis);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                List<NewsMulti> multis = new ArrayList<NewsMulti>();
                NewsMulti multi = new NewsMulti(NewsMulti.BANNER,
                        new AdBean.DataEntity.Ad[]{new AdBean.DataEntity.Ad("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg")}, NewsMulti.NORMAL_SIZE);
                multis.add(multi);
                mMultiAdapter.addData(multis);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                getCategories(new Article().new FindAllCategory());
            }
        });
    }


//    @Override
//    public void lazyLoad() {
//        if (!mIsPrepared || !mIsVisible || mIsFirstLoad) {
//            return;
//        }
//        getData();
//    }
}
