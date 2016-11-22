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
import com.example.lin.demo.ui.activity.TownGridActivity;
import com.example.lin.demo.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TownMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TownMapFragment extends Fragment {

    private BaiduMap mBaiduMap;

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
        initView();
        return view;
    }

    private void initView() {
        LatLng latLng = new LatLng(25.479496,119.566955);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BEAN, "北垞村");
        setMap(latLng, "北垞村", R.mipmap.third_01);
        LatLng latLng2 = new LatLng(25.478459,119.559925);
        Bundle bundle2 = new Bundle();
        bundle.putString(Constant.BEAN, "薛港村");
        setMap(latLng2, "薛港村", R.mipmap.third_02);
        LatLng latLng3 = new LatLng(25.48066,119.577245);
        Bundle bundle3 = new Bundle();
        bundle.putString(Constant.BEAN, "后安村");
        setMap(latLng3, "后安村", R.mipmap.third_03);
        LatLng latLng4 = new LatLng(25.473688,119.577676);
        Bundle bundle4 = new Bundle();
        bundle.putString(Constant.BEAN, "东埔村");
        setMap(latLng4, "东埔村", R.mipmap.third_04);
        LatLng latLng5 = new LatLng(25.470657,119.571981);
        Bundle bundle5 = new Bundle();
        bundle.putString(Constant.BEAN, "安适村");
        setMap(latLng5, "安适村", R.mipmap.third_05);
        LatLng latLng6 = new LatLng(25.477087,119.58113);
        Bundle bundle6 = new Bundle();
        bundle.putString(Constant.BEAN, "目屿村");
        setMap(latLng6, "目屿村", R.mipmap.third_06);

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
    }

    private void setMap(LatLng latLng, String title, Integer img) {
        Marker marker;
        marker = (Marker) mBaiduMap.addOverlay(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromView(getOverlayView(img))));
        Bundle bundle1 = new Bundle();
        bundle1.putString(Constant.BEAN, title);
        marker.setExtraInfo(bundle1);
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

}
