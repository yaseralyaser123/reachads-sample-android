package com.go2reach.sample.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.go2reach.sample.R;
import com.reach.AdServiceManager;
import com.reach.IAdItem;
import com.reach.IAdService;
import com.reach.IBannerAd;
import com.reach.ICallback;


public class BannerFragment extends Fragment {
    IBannerAd adBottom;
    IBannerAd adCenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_banner,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IAdService adService = AdServiceManager.get(getContext());
        IBannerAd adTop = adService.getBannerAd("banner.top", -1, 60, new String[]{IAdItem.IMAGE});
        adTop.setReloadInterval(60);
        FrameLayout flTop = (FrameLayout)view.findViewById(R.id.topbanner);
        View banner = adTop.create();
        flTop.addView(banner);

        adCenter = adService.getBannerAd("banner.center",-1,60,new String[]{IAdItem.VIDEO});
        final FrameLayout flCenter = (FrameLayout) view.findViewById(R.id.centerbanner);
        adCenter.setReloadInterval(30);

        Button btnLoadVideo = (Button) view.findViewById(R.id.loadcenterVideoBanner);
        btnLoadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flCenter.addView(adCenter.create());
                adCenter.load();
            }
        });


        adBottom = adService.getBannerAd("banner.bottom", -1, 60, null);
        adBottom.setOnLoadLisenter(new ICallback(){

            @Override
            public void call(int resultCode) {
                Toast.makeText(getActivity(), "Bottom banner is loaded!", Toast.LENGTH_LONG).show();
            }

        });
        FrameLayout flButtom = (FrameLayout)view.findViewById(R.id.bottombanner);
        flButtom.addView(adBottom.create());
        Button btnLoad = (Button)view.findViewById(R.id.loadBottomBanner);
        btnLoad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adBottom.load();

            }
        });
    }

}
