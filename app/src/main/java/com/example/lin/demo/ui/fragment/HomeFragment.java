package com.example.lin.demo.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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

public class HomeFragment extends Fragment {

    private RecyclerView mRv;

    private MultiAdapter mMultiAdapter;

    private List<Multi> mMultis;

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
        return view;
    }

    private void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mMultiAdapter);
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
                    case Multi.LIST:
                        Intent intent = new Intent(getActivity(), DetailedActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.BEAN, ((Multi) baseQuickAdapter.getItem(i)).getNews());
                        intent.putExtra(Constant.TITLE, "相关推荐");
                        intent.putExtra(Constant.BUNDLE, bundle);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initData() {
        mMultis = new ArrayList<>();
        Integer[] imgs = new Integer[]{R.mipmap.banner_3, R.mipmap.banner_3, R.mipmap.banner_3, R.mipmap.banner_3};
        Multi multi = new Multi(Multi.BANNER, imgs);
        Integer bigImg = R.mipmap.image;
        Multi multi1 = new Multi(Multi.BIG_IMG, bigImg);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("发展变迁", R.mipmap.first_f_1));
        categories.add(new Category("地方文化", R.mipmap.first_f_2));
        categories.add(new Category("特产美食", R.mipmap.first_f_3));
        categories.add(new Category("风景名胜", R.mipmap.first_f_4));
        Multi multi2 = new Multi(Multi.CATEGORY, categories);
        Multi multi3 = new Multi(Multi.DIVIDING);
        mMultis.add(multi);
        mMultis.add(multi2);
        mMultis.add(multi3);
        mMultis.add(multi1);
        News news1 = new News("福清高山山羊", "高山羊是我省肉用型优良山羊品种之一。其肉鲜嫩少膻味,味道香甜。逢年过节,外省和香港都有人特地到福清采购高山羊。福清籍的海外华侨和港台同胞回乡时也必尝高...",
                R.mipmap.first_f_6, "2015-12-24");
        News news2 = new News("福清高山名胜古迹", "从福州图库乘车到福清高山镇至沙浦镇牛头尾,再坐渡船上目屿岛,约莫 2...", R.mipmap.first_f_7, "2015-10-2");
        mMultis.add(new Multi(Multi.LIST, news1));
        mMultis.add(new Multi(Multi.LIST, news2));
    }


    private void initAdapter() {
        mMultiAdapter = new MultiAdapter(mMultis);
    }

    class MultiAdapter extends BaseMultiItemQuickAdapter<Multi> {
        public MultiAdapter(List<Multi> data) {
            super(data);
            addItemType(Multi.BANNER, R.layout.banner);
            addItemType(Multi.CATEGORY, R.layout.grid_item);
            addItemType(Multi.BIG_IMG, R.layout.big_img_item);
            addItemType(Multi.LIST, R.layout.right_img_item);
            addItemType(Multi.DIVIDING, R.layout.divide_item);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, final Multi multi) {
            switch (baseViewHolder.getItemViewType()) {
                case Multi.BANNER:
                    BGABanner bgaBanner = baseViewHolder.getView(R.id.test_banner);
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
                    GridView gridView = baseViewHolder.getView(R.id.grid);
                    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent2List = new Intent(getActivity(), ListActivity.class);
                            intent2List.putExtra(Constant.TITLE, multi.getCategories().get(position).getName());
                            startActivity(intent2List);
                        }
                    };
                    baseViewHolder.setOnItemClickListener(R.id.grid, listener);
                    InGridAdapter gridAdapter = new InGridAdapter( multi.getCategories(), mContext);
                    gridView.setAdapter(gridAdapter);
                    break;
                case Multi.BIG_IMG:
                    baseViewHolder.setImageResource(R.id.big_image, multi.getBigImg())
                            .addOnClickListener(R.id.big_image_more)
                            .addOnClickListener(R.id.big_image);
                    break;
                case Multi.LIST:
                    baseViewHolder.setImageResource(R.id.right_img, multi.getNews().getImg())
                            .setText(R.id.right_item_title, multi.getNews().getTitle())
                            .setText(R.id.right_item_time, multi.getNews().getTime())
                            .setText(R.id.right_item_content, multi.getNews().getContent())
                            .addOnClickListener(R.id.right_img_group);
                    break;
            }
        }

        class InGridAdapter extends BaseAdapter {

            private List<Category> mCategories;

            private LayoutInflater mLayoutInflater;

            public InGridAdapter(List<Category> categories, Context context) {
                mCategories = categories;
                mLayoutInflater = LayoutInflater.from(context);
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
                    convertView = mLayoutInflater.inflate(R.layout.home_category_item, parent, false);
                    holder = new ViewHolder();
                    holder.mImageView = (ImageView) convertView.findViewById(R.id.category_img);
                    holder.mTextView = (TextView) convertView.findViewById(R.id.category_text);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.mImageView.setImageResource(mCategories.get(position).getImg());
                holder.mTextView.setText(mCategories.get(position).getName());
                return convertView;
            }
        }

        class ViewHolder {
            TextView mTextView;

            ImageView mImageView;
        }
    }
}
