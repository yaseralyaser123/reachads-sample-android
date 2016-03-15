package com.go2reach.sample.ads;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.go2reach.sample.R;


public class NativeAdFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_native_ad,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View btn1 = view.findViewById(R.id.native_pic);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), NativeAdPictureActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        View btn2 = view.findViewById(R.id.native_item);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), NativeAdActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        View btn3 = view.findViewById(R.id.native_item_video);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), NativeAdVideoActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        View btn4 = view.findViewById(R.id.native_pic_video);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), NativeAdPictureVideoActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

}
