package com.fjrcloud.lin.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.fjrcloud.lin.R;
import com.fjrcloud.lin.model.bean.TownBean;
import com.fjrcloud.lin.ui.activity.TownGridActivity;
import com.fjrcloud.lin.util.Constant;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TownMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TownMapFragment extends Fragment {
    private static final String TAG = "TownMapFragment";
    private boolean mIsShow;
    private boolean mIsFirst;
    private BaiduMap mBaiduMap;

    private EditText mEditText;
    private ImageButton mSearchBtn;
    private boolean mIsSearch; //点击search的时候  zoom按16来
    private List<TownBean.Town> mTowns;

    public TownMapFragment() {
        // Required empty public constructor
    }


    public static TownMapFragment newInstance() {
        TownMapFragment fragment = new TownMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_town_map, container, false);
        MapView mapView = (MapView) view.findViewById(R.id.town_bdmap);
        mBaiduMap = mapView.getMap();
        mEditText = (EditText) view.findViewById(R.id.search_edit);
        mSearchBtn = (ImageButton) view.findViewById(R.id.search_btn);
        initData();
        getData();
        initListener();
        return view;
    }

    private void getData() {
//        getTown(new YsTown().new FindTownByParent(null));
    }

    private void initListener() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                TownBean.Town town = (TownBean.Town) bundle.getSerializable(Constant.BEAN);
                Intent intent = new Intent(getActivity(), TownGridActivity.class);
                intent.putExtra(Constant.BEAN, town);
                startActivity(intent);
                return true;
            }
        });

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
//                if (mapStatus.zoom < 14.5) {
//                    if (mIsShow) {
//                        mBaiduMap.clear();
//                        mIsShow = !mIsShow;
//                    }
//                } else {
//                    if (!mIsShow) {
//                        initOverlay();
//                    }
//                }
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {

            }
        });
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        searchLat(v);
                        return true;
                }
                return false;
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = String.valueOf(mEditText.getText());
                if (!TextUtils.isEmpty(keyWord)) {
                    searchLat(v);
                }
            }
        });
    }

    private void searchLat(View v) {
        mIsSearch = true;
        InputMethodManager imm = (InputMethodManager) v
                .getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(
                    v.getApplicationWindowToken(), 0);
        }
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(25.479496, 119.566955)));
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(16);
        mBaiduMap.animateMapStatus(u);
    }

    private void initData() {
        mIsFirst = true;
        mIsShow = true;
        mTowns = new ArrayList<>();
    }


    private void initOverlay() {
        for (int i = 0; i < mTowns.size(); i++) {
            setMap(mTowns.get(i));
        }
    }

    private void setMap(TownBean.Town town) {
        LatLng latLng = new LatLng(Float.parseFloat(town.getLatitude()), Float.parseFloat(town.getLongitude()));
        Marker marker;
        marker = (Marker) mBaiduMap.addOverlay(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromView(getOverlayView(R.mipmap.town_icon, town.getName()))));
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable(Constant.BEAN, town);
        marker.setExtraInfo(bundle1);
        if (mIsFirst) {
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
            mIsFirst = false;
        }
        if (!mIsSearch) {
            MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(15);
            mBaiduMap.animateMapStatus(u);
            mIsSearch = false;
        }
    }

    public View getOverlayView(Integer img, String name) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.overlay_layout, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.over_img);
        imageView.setImageResource(img);
        TextView textView = (TextView) view.findViewById(R.id.over_name);
        int left = textView.getPaddingLeft();
        int right = textView.getPaddingRight();
        int top = textView.getTotalPaddingTop();
        int bottom = textView.getPaddingBottom();
        textView.setBackgroundResource(R.drawable.bg_dark_pink_red);
        textView.setText(name);
        textView.setPadding(left, top, right, bottom);
//        TextView textView = (TextView) view.findViewById(R.id.overlay_name);
//        textView.setText(companyName);
        return view;
    }

    private void getTown(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<TownBean>() {
            @Override
            public void onSuccess(TownBean result) {
                for (int i = 0; i < result.getData().size(); i++) {
                    mTowns.add(result.getData().get(i));
                    setMap(result.getData().get(i));
                }

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
