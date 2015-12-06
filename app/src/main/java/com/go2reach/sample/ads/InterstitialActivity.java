package com.go2reach.sample.ads;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.reach.IAdItem;
import com.reach.IAdService;
import com.reach.IInterstitialAd;
import com.reach.Services;

public class InterstitialActivity extends ActionBarActivity {
	IAdService adService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interstitial);
		adService = Services.get(IAdService.class, this);
		
		Button btnDefault = (Button)this.findViewById(R.id.btnDefault);
		btnDefault.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final IInterstitialAd ad = adService.getInterstitialAd("interstitial.default");
				ad.popup();
				/*
				Handler handler = new Handler();
				handler.postDelayed(new Runnable(){

					@Override
					public void run() {
						ad.close();
						
					}
					
				}, 10 * 1000);
				*/
			}
		});

		Button btnCustom = (Button)this.findViewById(R.id.btnCustom);
		btnCustom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IInterstitialAd ad = adService.getInterstitialAd("interstitial.custom", 300, 250, 500, 300, new String[]{IAdItem.VIDEO});
				ad.popup();
			}
		});

		Button btnLanscape = (Button)this.findViewById(R.id.btnLandscape);
		btnLanscape.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IInterstitialAd ad = adService.getInterstitialAd("interstitial.landscape", 0, 0, 500, 300, null);
				ad.popup();
			}
		});

		Button btnFullScreen = (Button)this.findViewById(R.id.btnFullScreen);
		btnFullScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IInterstitialAd ad = adService.getInterstitialAd("interstitial.fullscreen", -1, -1, -1, -1, null);
				ad.popup();
			}
		});
	}

}
