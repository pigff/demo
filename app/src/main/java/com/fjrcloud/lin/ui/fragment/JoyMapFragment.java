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
import com.fjrcloud.lin.ui.activity.DetailedActivity;


public class JoyMapFragment extends Fragment {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private boolean mIsShow;
    private boolean mIsFirst;
    private boolean mIsSearch;

    private EditText mEditText;
    private ImageButton mSearchBtn;

    public JoyMapFragment() {
        // Required empty public constructor
    }

    public static JoyMapFragment newInstance() {
        JoyMapFragment fragment = new JoyMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joy_map, container, false);
        mMapView = (MapView) view.findViewById(R.id.joy_bdmap);
        mBaiduMap = mMapView.getMap();
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
                Intent intent = new Intent(getActivity(), DetailedActivity.class);
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

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                if (mapStatus.zoom < 14.5) {
                    if (mIsShow) {
                        mBaiduMap.clear();
                        mIsShow = false;
                    }
                } else {
                    if (!mIsShow) {
                        initOverlay();
                        mIsShow = true;
                    }
                }
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
        LatLng latLng = new LatLng(25.479496, 119.566955);
        setMap(latLng, R.mipmap.second_1);
        LatLng latLng2 = new LatLng(25.478459, 119.559925);
        setMap(latLng2, R.mipmap.second_2);
        LatLng latLng3 = new LatLng(25.48066, 119.577245);
        setMap(latLng3, R.mipmap.second_3);
        LatLng latLng4 = new LatLng(25.473688, 119.577676);
        setMap(latLng4, R.mipmap.second_4);
        LatLng latLng5 = new LatLng(25.470657, 119.571981);
        setMap(latLng5, R.mipmap.second_5);
        LatLng latLng6 = new LatLng(25.477087, 119.58113);
        setMap(latLng6, R.mipmap.second_6);
    }

    private void setMap(LatLng latLng, Integer img) {
        Marker marker;
        marker = (Marker) mBaiduMap.addOverlay(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromView(getOverlayView(img))));
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

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

}
