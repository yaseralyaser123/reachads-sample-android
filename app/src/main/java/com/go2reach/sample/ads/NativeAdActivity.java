package com.go2reach.sample.ads;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.go2reach.sample.R;
import com.go2reach.sample.adapter.NativeAdAdapter;
import com.reach.AdServiceManager;
import com.reach.IAd;
import com.reach.IAdItem;
import com.reach.IAdService;
import com.reach.ICallback;
import com.reach.INativeAd;
import com.reach.IServiceCallback;

import java.util.ArrayList;


public class NativeAdActivity extends AppCompatActivity {
	IAdService adService;
	INativeAd ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_native_ad_item);


		final ArrayList<Item> items = new ArrayList<>();
		final NativeAdAdapter adapter = new NativeAdAdapter(this, items);
		for (int i = 0; i < 50; i++){
			items.add(new Item(Item.TYPE_PRODUCT, new Product("Product name " + i, Math.round(Math.random() * 100))));
		}
		ListView lv = (ListView)this.findViewById(R.id.native_item);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Toast.makeText(NativeAdActivity.this, "pos:" + position, Toast.LENGTH_LONG).show();

			}

		});

		AdServiceManager.get(this, new IServiceCallback<IAdService>(){
			@Override
			public void call(IAdService service) {
				adService = service;
				ad = adService.getNativeAd("native.ad.item", 400, 50, 3, new String[]{IAdItem.IMAGE});
				ad.setOnLoadLisenter(new ICallback() {

					@Override
					public void call(int resultCode) {
						if (resultCode == IAd.OK) {
							for (int i = 0; i < ad.getCount(); i++) {
								int index = (i + 1) * 5;
								items.add(index, new Item(Item.TYPE_AD, ad.getAdItem(i)));
							}
							adapter.notifyDataSetChanged();
						}

					}
				});
				ad.load();
			}
		});

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK && null != ad && ad.isFullscreen()) {
			ad.closeFullscreen();
			return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
