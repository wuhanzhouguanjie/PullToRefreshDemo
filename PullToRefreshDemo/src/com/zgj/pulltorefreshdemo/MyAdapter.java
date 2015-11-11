package com.zgj.pulltorefreshdemo;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MyAdapter extends BaseAdapter {

	private Context mContext;

	private ArrayList<Bean> mList;



	public MyAdapter(Context context, ArrayList<Bean> list) {
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub

		return mList.get(arg0);

	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;  
		if(convertView==null){  
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(  
	                    R.layout.item, null);  
			viewHolder.text = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();  
		}
		
		viewHolder.text.setText(mList.get(position).getText());
		
		
		return convertView;
	}




	


	public class ViewHolder {
		TextView text;
	}

}
