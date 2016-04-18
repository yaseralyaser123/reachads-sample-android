package com.go2reach.sample.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.go2reach.sample.R;
import com.reach.AdServiceManager;
import com.reach.IAdItem;
import com.reach.IAdService;
import com.reach.ICallback;
import com.reach.IInterstitialAd;


public class InterstitialFragment extends Fragment {

    IAdService adService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_interstitial,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adService = AdServiceManager.get(getContext());

        LinearLayout btnDefault = (LinearLayout)view.findViewById(R.id.btnDefault);
        btnDefault.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final IInterstitialAd ad = adService.getInterstitialAd("interstitial.default");
                ad.popup();
                ad.setOnLoadLisenter(new ICallback() {
                    @Override
                    public void call(int i) {
                        Toast.makeText(getActivity(), "Default interstitial ad has loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        LinearLayout btnCustom = (LinearLayout)view.findViewById(R.id.btnCustom);
        btnCustom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IInterstitialAd ad = adService.getInterstitialAd("interstitial.video", -1, -1, -1, -1, new String[]{IAdItem.VIDEO});
                ad.popup();
                ad.setOnLoadLisenter(new ICallback() {
                    @Override
                    public void call(int i) {
                        Toast.makeText(getActivity(), "Video interstitial ad has loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        LinearLayout btnLanscape = (LinearLayout)view.findViewById(R.id.btnLandscape);
        btnLanscape.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IInterstitialAd ad = adService.getInterstitialAd("interstitial.landscape", 0, 0, 500, 300, new String[]{IAdItem.IMAGE});
                ad.popup();
                ad.setOnLoadLisenter(new ICallback() {
                    @Override
                    public void call(int i) {
                        Toast.makeText(getActivity(), "Landscape interstitial ad has loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        LinearLayout btnFullScreen = (LinearLayout)view.findViewById(R.id.btnFullScreen);
        btnFullScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IInterstitialAd ad = adService.getInterstitialAd("interstitial.fullscreen", -1, -1, -1, -1, new String[]{IAdItem.IMAGE});
                ad.popup();
                ad.setOnLoadLisenter(new ICallback() {
                    @Override
                    public void call(int i) {
                        Toast.makeText(getActivity(), "Fullscreen interstitial ad has loaded!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
