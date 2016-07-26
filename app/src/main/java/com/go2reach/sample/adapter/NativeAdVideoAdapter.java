package com.go2reach.sample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.go2reach.sample.R;
import com.go2reach.sample.ads.Item;
import com.reach.IAdItem;

import java.util.List;

public class NativeAdVideoAdapter extends BaseAdapter {
	List<Item> items;
	LayoutInflater inflater;
	public NativeAdVideoAdapter(Context context, List<Item> items){
		this.items = items;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item = items.get(position);
		View itemView = convertView;
		if (itemView == null){
			if (item.getType() == Item.TYPE_PRODUCT){
				itemView = inflater.inflate(R.layout.list_item_product, parent, false);
			} else {
				itemView = inflater.inflate(R.layout.list_item_ad_video, parent, false);
			}
		}
		if (item.getType() == Item.TYPE_PRODUCT){
			TextView tvName = (TextView)itemView.findViewById(R.id.tvListItemProductName);
			TextView tvPrice = (TextView)itemView.findViewById(R.id.tvListItemProductPrice);
			tvName.setText(item.getAsProduct().getName());
			tvPrice.setText("$" + item.getAsProduct().getPrice());
			itemView.setBackgroundColor(position % 2 == 1 ? 0xFFEEEEEE : 0xFFFFFFFF); 
		} else {
			item.getAsAdItem().bind(itemView,
					new String[]{IAdItem.TITLE, IAdItem.MEDIA_CONTAINER},
					new int[]{R.id.tvListItemTitle, R.id.flContainer}
			);
		}
		return itemView;
	}
	@Override
	public int getItemViewType(int position) {
		Item item = items.get(position);
		return item.getType();
	}
	@Override
	public int getViewTypeCount() {
		return 2;
	}

}
