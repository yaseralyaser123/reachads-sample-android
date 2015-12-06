package com.go2reach.sample.ads;

import com.reach.IAdItem;

public class Item {
	public static final int TYPE_PRODUCT = 0;
	public static final int TYPE_AD = 1;
	int type;
	Object obj;
	public Item(int type, Object obj){
		this.type = type;
		this.obj = obj;
	}
	public int getType(){
		return type;
	}
	public IAdItem getAsAdItem(){
		return (IAdItem)obj;
	}
	public Product getAsProduct(){
		return (Product)obj;
	}
}
