package com.example.lin.demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lin.demo.R;
import com.example.lin.demo.adapter.ListAdapter2;
import com.example.lin.demo.bean.News;
import com.example.lin.demo.ui.base.BaseActivity;
import com.example.lin.demo.util.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

@ContentView(R.layout.activity_list)
public class ListActivity extends BaseActivity {

    private List<News> mNewses;

    @ViewInject(R.id.list_lv)
    private ListView mListView;

    @ViewInject(R.id.list_banner)
    private BGABanner mBanner;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        setTitle(mTitle);
        mListView.setFocusable(false);
        mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
                ((ImageView) view).setImageResource(((int) model));
            }
        });

        mBanner.setData(Arrays.asList(R.mipmap.banner_4, R.mipmap.banner_4, R.mipmap.banner_4, R.mipmap.banner_4), null);
        ListAdapter2 adapter2 = new ListAdapter2(mNewses, this);
        mListView.setAdapter(adapter2);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.BEAN, mNewses.get(position));
                intent.putExtra(Constant.TITLE, mTitle);
                intent.putExtra(Constant.BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mTitle = getIntent().getStringExtra(Constant.TITLE);
        mNewses = new ArrayList<>();
        News news1 = new News("鱼溪龙眼", "龙眼（学名：Dimocarpus longan Lour.），又称桂圆，益智。常绿乔木，高通常10余米；小枝粗壮，被微柔毛....", R.mipmap.image);
        News news2 = new News("一都枇杷", "一都枇杷是福建省福州市福清市一都镇的特产。一都枇杷以“果大、色艳、肉厚...", R.mipmap.image);
        News news3 = new News("嘉儒蛤", "嘉儒蛤是福建福州福清的特产。嘉儒蛤 福建省福清市,北纬25°41′43.89″至25°29′33.58″,东经119°27′42.73″...", R.mipmap.image);
        News news4 = new News("福清海蛎饼", "福清是沿海城市，而海蛎饼则是福清的汉族小吃。海蛎饼，以新鲜海蛎为主要原料，搭配上紫菜...", R.mipmap.image);
        mNewses.add(news1);
        mNewses.add(news2);
        mNewses.add(news3);
        mNewses.add(news4);
    }
}
