package com.example.lin.demo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.lin.demo.R;
import com.example.lin.demo.bean.Category;
import com.example.lin.demo.bean.Multi;
import com.example.lin.demo.bean.News;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class TestActivity extends AppCompatActivity {

    private List<Multi> mMultis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initData();
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.test_in_ry);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MultiAdapter adapter = new MultiAdapter(mMultis);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int type = baseQuickAdapter.getItemViewType(i);
                switch (type) {
                    case Multi.BANNER:
                        break;
                    case Multi.BIG_IMG:
                       switch (view.getId()) {
                           case R.id.big_image:
                               Toast.makeText(TestActivity.this, "大图", Toast.LENGTH_SHORT).show();
                               break;
                           case R.id.big_image_more:
                               Toast.makeText(TestActivity.this, "大图更多", Toast.LENGTH_SHORT).show();
                               break;
                       }
                        break;
                    case Multi.CATEGORY:

                        break;
                    case Multi.LIST:
                        Toast.makeText(TestActivity.this, ((Multi) baseQuickAdapter.getItem(i)).getNews().getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
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
        categories.add(new Category("第一", R.mipmap.first_f_1));
        categories.add(new Category("第二", R.mipmap.first_f_2));
        categories.add(new Category("第三", R.mipmap.first_f_3));
        categories.add(new Category("第四", R.mipmap.first_f_4));
        Multi multi2 = new Multi(Multi.CATEGORY, categories);
        Multi multi3 = new Multi(Multi.DIVIDING);
        mMultis.add(multi);
        mMultis.add(multi2);
        mMultis.add(multi3);
        mMultis.add(multi1);
        for (int i = 0; i < 100; i++) {
            mMultis.add(new Multi(Multi.LIST, new News("第" + i + "个标题", "第" + i + "个内容", R.mipmap.image, "2100-2-2")));
        }
    }

    class MultiAdapter extends BaseMultiItemQuickAdapter<Multi> {
        public MultiAdapter(List<Multi> data) {
            super(data);
            addItemType(Multi.BANNER, R.layout.banner);
            addItemType(Multi.CATEGORY, R.layout.test_ry);
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
                    bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                        @Override
                        public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                            Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case Multi.CATEGORY:
                    GridView gridView = baseViewHolder.getView(R.id.test_grid_ry);
                    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(mContext, multi.getCategories().get(position).getName(), Toast.LENGTH_SHORT).show();
                        }
                    };
                    baseViewHolder.setOnItemClickListener(R.id.test_grid_ry, listener);
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
                convertView = mLayoutInflater.inflate(R.layout.design_tab_layout2, parent, false);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_tab2);
                TextView textView = (TextView) convertView.findViewById(R.id.iv_text2);
                imageView.setImageResource(mCategories.get(position).getImg());
                textView.setText(mCategories.get(position).getName());
                return convertView;
            }
        }
    }
}
