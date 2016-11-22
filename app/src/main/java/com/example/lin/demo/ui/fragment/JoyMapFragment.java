package com.example.lin.demo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.lin.demo.R;
import com.example.lin.demo.ui.activity.DetailedActivity;


public class JoyMapFragment extends Fragment {


    private MapView mMapView;
    private BaiduMap mBaiduMap;

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
        initView();
        return view;
    }

    private void initView() {
        LatLng latLng = new LatLng(25.479496,119.566955);
        setMap(latLng, R.mipmap.second_1);
        LatLng latLng2 = new LatLng(25.478459,119.559925);
        setMap(latLng2, R.mipmap.second_2);
        LatLng latLng3 = new LatLng(25.48066,119.577245);
        setMap(latLng3, R.mipmap.second_3);
        LatLng latLng4 = new LatLng(25.473688,119.577676);
        setMap(latLng4, R.mipmap.second_4);
        LatLng latLng5 = new LatLng(25.470657,119.571981);
        setMap(latLng5, R.mipmap.second_5);
        LatLng latLng6 = new LatLng(25.477087,119.58113);
        setMap(latLng6, R.mipmap.second_6);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(getActivity(), DetailedActivity.class);
                startActivity(intent);
                return true;
            }
        });
    }

    private void setMap(LatLng latLng, Integer img) {
        Marker marker;
        marker = (Marker) mBaiduMap.addOverlay(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromView(getOverlayView(img))));

        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(15);
        mBaiduMap.animateMapStatus(u);
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
