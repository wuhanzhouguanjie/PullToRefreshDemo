package com.zgj.pulltorefreshdemo;

import android.widget.ImageView;

public class Bean {
	
	private ImageView mImg;
	
	private String mText;
	
	private int type;
	
	public Bean(ImageView mImg,String mText,int type){
		this.mImg = mImg;
		this.mText = mText;
		this.type = type;
	}
	
	public Bean(String mText){
		this.mText = mText;
	}
	
	public ImageView getImg(){
		return mImg;
	}

	public String getText(){
		return mText;
	}
	
	public int getType(){
		return type;
	}
}
