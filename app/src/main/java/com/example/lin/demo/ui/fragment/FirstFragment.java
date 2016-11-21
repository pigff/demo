package com.example.lin.demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.lin.demo.R;
import com.example.lin.demo.adapter.ListAdapter;
import com.example.lin.demo.bean.Category;
import com.example.lin.demo.bean.News;
import com.example.lin.demo.ui.activity.DetailedActivity;
import com.example.lin.demo.ui.activity.ListActivity;
import com.example.lin.demo.util.Constant;
import com.example.lin.demo.util.HomeListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class FirstFragment extends Fragment implements View.OnClickListener {
    private BGABanner mBanner;

    private RecyclerView mGridRv;

    private TextView mBigImgMore;

    private List<Category> mCategories;

    private List<News> mNewses;
    private GridAdapter mGridAdapter;
    private HomeListView mListView;
    private ListAdapter mListAdapter;
    private FrameLayout mLayout;

    public FirstFragment() {

    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mBanner = (BGABanner) view.findViewById(R.id.common_banner);
        mGridRv = (RecyclerView) view.findViewById(R.id.grid_rv);
        mListView = (HomeListView) view.findViewById(R.id.lv);
        mBigImgMore = (TextView) view.findViewById(R.id.big_image_more);
        mLayout = (FrameLayout) view.findViewById(R.id.big_img_group);
        initData();
        initAdapter();
        initView();
        return view;
    }

    private void initView() {
        mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                ((ImageView) view).setImageResource(((int) model));
            }
        });
        mBanner.setData(Arrays.asList(R.mipmap.banner_img1, R.mipmap.banner_img1, R.mipmap.banner_img1, R.mipmap.banner_img1), null);

        mGridRv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mGridRv.setHasFixedSize(true);
        mGridRv.setAdapter(mGridAdapter);
        mGridRv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent2List = new Intent(getActivity(), ListActivity.class);
                intent2List.putExtra(Constant.TITLE, mCategories.get(i).getName());
                startActivity(intent2List);
            }
        });
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.BEAN, mNewses.get(position));
                intent.putExtra(Constant.TITLE, "相关推荐");
                intent.putExtra(Constant.BUNDLE, bundle);
                startActivity(intent);
            }
        });
        mBigImgMore.setOnClickListener(this);
        mLayout.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.big_image_more:
                Intent intent2List = new Intent(getActivity(), ListActivity.class);
                intent2List.putExtra(Constant.TITLE, "相关推荐");
                startActivity(intent2List);
                break;
            case R.id.big_img_group:
                Intent intent = new Intent(getActivity(), DetailedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.BEAN, mNewses.get(0));
                intent.putExtra(Constant.BUNDLE, bundle);
                intent.putExtra(Constant.TITLE, "相关推荐");
                startActivity(intent);
            default:
                break;
        }
    }

    private void initData() {
        mCategories = new ArrayList<>();
        Category category1 = new Category("发展变迁", R.mipmap.first_f_1);
        Category category2 = new Category("地方文化", R.mipmap.first_f_2);
        Category category3 = new Category("特产美食", R.mipmap.first_f_3);
        Category category4 = new Category("风景名胜", R.mipmap.first_f_4);
        mCategories.add(category1);
        mCategories.add(category2);
        mCategories.add(category3);
        mCategories.add(category4);

        mNewses = new ArrayList<>();
        News news1 = new News("福清高山山羊", "高山羊是我省肉用型优良山羊品种之一。其肉鲜嫩少膻味,味道香甜。逢年过节,外省和香港都有人特地到福清采购高山羊。福清籍的海外华侨和港台同胞回乡时也必尝高...",
                R.mipmap.first_f_6, "2015-12-24");
        News news2 = new News("福清高山名胜古迹", "从福州图库乘车到福清高山镇至沙浦镇牛头尾,再坐渡船上目屿岛,约莫 2...", R.mipmap.first_f_7, "2015-10-2");
        mNewses.add(news1);
        mNewses.add(news2);
    }


    private void initAdapter() {
        mGridAdapter = new GridAdapter(R.layout.design_tab_layout2, mCategories);
        mListAdapter = new ListAdapter(mNewses, getActivity());
    }

    class GridAdapter extends BaseQuickAdapter<Category> {

        public GridAdapter(int layoutResId, List<Category> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, Category category) {
            baseViewHolder.setImageResource(R.id.iv_tab2, category.getImg())
                    .setText(R.id.iv_text2, category.getName())
                    .addOnClickListener(R.id.tab_group);
        }
    }
}
