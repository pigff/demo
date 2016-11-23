package com.example.lin.demo.ui.fragment;


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

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.lin.demo.R;
import com.example.lin.demo.ui.activity.TownGridActivity;
import com.example.lin.demo.util.Constant;

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
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                String title = bundle.getString(Constant.BEAN);
                Intent intent = new Intent(getActivity(), TownGridActivity.class);
                intent.putExtra(Constant.BEAN, title);
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
                if (mapStatus.zoom < 14.5) {
                    if (mIsShow) {
                        mBaiduMap.clear();
                        mIsShow = !mIsShow;
                    }
                } else {
                    if (!mIsShow) {
                        initOverlay();
                    }
                }
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
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(25.479496,119.566955)));
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(16);
        mBaiduMap.animateMapStatus(u);
    }

    private void initData() {
        mIsFirst = true;
    }

    private void initView() {
        initOverlay();
    }

    private void initOverlay() {
        mIsShow = true;
        LatLng latLng = new LatLng(25.479496,119.566955);
        setMap(latLng, "北垞村", R.mipmap.third_01);
        LatLng latLng2 = new LatLng(25.478459,119.559925);
        setMap(latLng2, "薛港村", R.mipmap.third_02);
        LatLng latLng3 = new LatLng(25.48066,119.577245);
        setMap(latLng3, "后安村", R.mipmap.third_03);
        LatLng latLng4 = new LatLng(25.473688,119.577676);
        setMap(latLng4, "东埔村", R.mipmap.third_04);
        LatLng latLng5 = new LatLng(25.470657,119.571981);
        setMap(latLng5, "安适村", R.mipmap.third_05);
        LatLng latLng6 = new LatLng(25.477087,119.58113);
        setMap(latLng6, "目屿村", R.mipmap.third_06);
    }

    private void setMap(LatLng latLng, String title, Integer img) {
        Marker marker;
        marker = (Marker) mBaiduMap.addOverlay(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromView(getOverlayView(img))));
        Bundle bundle1 = new Bundle();
        bundle1.putString(Constant.BEAN, title);
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

    public View getOverlayView(Integer img) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.overlay_layout, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.over_img);
        imageView.setImageResource(img);
//        TextView textView = (TextView) view.findViewById(R.id.overlay_name);
//        textView.setText(companyName);
        return view;
    }

}
