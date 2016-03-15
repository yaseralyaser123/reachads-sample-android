package com.go2reach.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.go2reach.sample.ads.BannerFragment;
import com.go2reach.sample.ads.InterstitialFragment;
import com.go2reach.sample.ads.NativeAdFragment;

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
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Interstitial Ads";
            case 1:
                return "Banner Ads";
            case 2:
                return "Native Ads";            }
        return null;
    }
}
