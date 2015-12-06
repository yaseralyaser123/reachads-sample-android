package com.go2reach.sample.ads;

//import com.gmobi.go2reach.ITrackService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		Button btnBannerSample = (Button)findViewById(R.id.bannerSample);
		btnBannerSample.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, BannerActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
		
		Button btnInterstitialSample = (Button)findViewById(R.id.interstitialSample);
		btnInterstitialSample.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, InterstitialActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});

		Button btnNativeSample = (Button)findViewById(R.id.nativeSample);
		btnNativeSample.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, NativeAdActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});

	}
	@Override
	public void onBackPressed() {
	    finish();
	    System.exit(0);
	}
}
