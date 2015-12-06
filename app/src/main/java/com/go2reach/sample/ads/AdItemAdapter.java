package com.go2reach.sample.ads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.reach.IAdItem;
import com.reach.INativeAd;

public class AdItemAdapter extends BaseAdapter{
	INativeAd ad;
	LayoutInflater inflater;
	public AdItemAdapter(Context context, INativeAd ad){
		this.ad = ad;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return ad.getCount();
	}
	@Override
	public Object getItem(int position) {
		return ad.getAdItem(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		IAdItem item = ad.getAdItem(position);
		View itemView = convertView;
		if (itemView == null){
			itemView = inflater.inflate(R.layout.grid_item, parent, false);
		}
		item.bind(itemView, 
				new String[]{IAdItem.TITLE, IAdItem.ICON}, 
				new int[]{R.id.tvGridItemTitle, R.id.ivGridItemIcon}
		);
		return itemView;
	}
}
