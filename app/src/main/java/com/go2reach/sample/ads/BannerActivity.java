package com.go2reach.sample.ads;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.reach.IAdItem;
import com.reach.IAdService;
import com.reach.IBannerAd;
import com.reach.ICallback;
import com.reach.Services;

public class BannerActivity extends ActionBarActivity {
	IBannerAd adBottom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banner);
		
		IAdService adService = Services.get(IAdService.class, this);
		IBannerAd adTop = adService.getBannerAd("banner.top");
		adTop.setReloadInterval(60);
		FrameLayout flTop = (FrameLayout)this.findViewById(R.id.topbanner);
		View banner = adTop.create();
		flTop.addView(banner);
		String[] rfs = new String[]{IAdItem.VIDEO};
		rfs = null;
		adBottom = adService.getBannerAd("banner.bottom", -1, 60, rfs);
		adBottom.setOnLoadLisenter(new ICallback(){

			@Override
			public void call(int resultCode) {
				Toast.makeText(BannerActivity.this, "Bottom banner is loaded!", Toast.LENGTH_LONG).show();
			}
			
		});
		FrameLayout flButtom = (FrameLayout)this.findViewById(R.id.bottombanner);
		flButtom.addView(adBottom.create());
		Button btnLoad = (Button)this.findViewById(R.id.loadBottomBanner);
		btnLoad.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adBottom.load();
				
			}
		});
	}

}
