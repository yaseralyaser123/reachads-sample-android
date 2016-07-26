package com.go2reach.sample.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import com.go2reach.sample.ads.BannerFragment;
import com.go2reach.sample.ads.InterstitialFragment;
import com.go2reach.sample.ads.NativeAdFragment;
import com.go2reach.sample.ads.VideoAdsFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new InterstitialFragment();
            case 1:
                return new BannerFragment();
            case 2:
                return new NativeAdFragment();
            case 3:
                return new VideoAdsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Interstitial Ads";
            case 1:
                return "Banner Ads";
            case 2:
                return "Native Ads";
            case 3:
                return "Video Ads";
        }
        return null;
    }
}
