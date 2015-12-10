package com.go2reach.sample.ads;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.reach.IAd;
import com.reach.IAdItem;
import com.reach.IAdService;
import com.reach.ICallback;
import com.reach.INativeAd;
import com.reach.Services;

public class NativeAdActivity extends ActionBarActivity {
	IAdService adService;
	INativeAd ad1, ad2, ad3, ad4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_native_ad);
		
		adService = Services.get(IAdService.class, this);
		
		ad1 = adService.getNativeAd("native.ad1", 50, 50, 1, null);
		final View container = this.findViewById(R.id.container);
		IAdItem item = ad1.getAdItem(0);
		item.bind(container,
				new String[]{IAdItem.ICON, IAdItem.TITLE, IAdItem.CALL_TO_ACTION}, 
				new int[]{R.id.ivNative, R.id.tvTitle, R.id.btnCta});
		container.setVisibility(View.GONE);
		ad1.setOnLoadLisenter(new ICallback(){

			@Override
			public void call(int resultCode) {
				if (resultCode == IAd.OK){
					container.setVisibility(View.VISIBLE);
				}
				
			}
			
		});
		ad1.load();
		
		ad2 = adService.getNativeAd("native.ad2", 100, 100, 3, null);
		final AdItemAdapter adapter2 = new AdItemAdapter(this, ad2);
		GridView gv = (GridView)this.findViewById(R.id.gvNative);
		gv.setAdapter(adapter2);
		ad2.setOnLoadLisenter(new ICallback(){

			@Override
			public void call(int resultCode) {
				if (resultCode == IAd.OK){
					adapter2.notifyDataSetChanged();
				}
				
			}
			
		});
		ad2.load();

		
		ad3 = adService.getNativeAd("native.ad3", 50, 50, 3, new String[]{IAdItem.VIDEO});
		ad3.setAutoplayMode(true);
		final ArrayList<Item> items = new ArrayList<Item>(); 
		final ItemListAdapter adapter3 = new ItemListAdapter(this, items);
		for (int i = 0; i < 50; i++){
			items.add(new Item(Item.TYPE_PRODUCT, new Product("Product name " + i, Math.round(Math.random() * 100))));
		}
		ListView lv = (ListView)this.findViewById(R.id.lvNative);
		lv.setAdapter(adapter3);

		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(NativeAdActivity.this, "pos:" + position, Toast.LENGTH_LONG).show();
				
			}
			
		});
		ad3.setOnLoadLisenter(new ICallback(){

			@Override
			public void call(int resultCode) {
				if (resultCode == IAd.OK){
					for(int i = 0; i < ad3.getCount(); i++){
						int index = (i + 1) * 10;
						items.add(index, new Item(Item.TYPE_AD, ad3.getAdItem(i)));
					}
					adapter3.notifyDataSetChanged();
				}
				
			}
			
		});
		ad3.load();

		ad4 = adService.getNativeAd("native.ad4", 200, 100, 1, new String[]{IAdItem.VIDEO});
		final View containerVideo = this.findViewById(R.id.containerVideo);
		IAdItem itemVideo = ad4.getAdItem(0);
		itemVideo.bind(containerVideo,
				new String[]{IAdItem.VIDEO, IAdItem.TITLE, IAdItem.CALL_TO_ACTION}, 
				new int[]{R.id.vpPlayer, R.id.tvVideoTitle, R.id.btnVideoCta});
		containerVideo.setVisibility(View.GONE);
		ad4.setOnLoadLisenter(new ICallback(){

			@Override
			public void call(int resultCode) {
				if (resultCode == IAd.OK){
					containerVideo.setVisibility(View.VISIBLE);
				}
				
			}
			
		});
		ad4.load();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK && 
	    		(ad1.isFullscreen() || ad2.isFullscreen() || ad3.isFullscreen() || ad4.isFullscreen())) {
	        ad1.closeFullscreen();
	        ad2.closeFullscreen();
	        ad3.closeFullscreen();
	        ad4.closeFullscreen();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
