package com.zgj.pulltorefreshdemo;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreUIHandler;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyLoadMoreDefaultFooterView extends RelativeLayout implements
		LoadMoreUIHandler {
	
	private TextView mTextView;
	
	private Context mContext;
	
	public MyLoadMoreDefaultFooterView(Context context) {
		this(context, null);
		mContext = context;
	}

	public MyLoadMoreDefaultFooterView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		mContext = context;
	}

	public MyLoadMoreDefaultFooterView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		setupViews();
	}
	
	private void setupViews(){
		LayoutInflater.from(getContext()).inflate(R.layout.test, this);
        mTextView = (TextView) findViewById(R.id.cube_views_load_more_default_footer_text_view);
        mTextView.setText("22222");
	}

	@Override
	public void onLoading(LoadMoreContainer container) {
		// TODO Auto-generated method stub
		setVisibility(VISIBLE);
		mTextView.setText("加载中");
	}

	@Override
	public void onLoadFinish(LoadMoreContainer container, boolean empty,
			boolean hasMore) {
		// TODO Auto-generated method stub
		if (!hasMore) {
            setVisibility(VISIBLE);
            if (empty) {
                mTextView.setText(R.string.cube_views_load_more_loaded_empty);
            } else {
            	Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
            	setVisibility(GONE);
//                mTextView.setText(R.string.cube_views_load_more_loaded_no_more);
            }
        } else {
            setVisibility(GONE);
//        	mTextView.setText("111");
        }
	}

	@Override
	public void onWaitToLoadMore(LoadMoreContainer container) {
		// TODO Auto-generated method stub
		setVisibility(VISIBLE);
        mTextView.setText(R.string.cube_views_load_more_click_to_load_more);
	}

	@Override
	public void onLoadError(LoadMoreContainer container, int errorCode,
			String errorMessage) {
		// TODO Auto-generated method stub
		mTextView.setText(R.string.cube_views_load_more_error);
	}
}
