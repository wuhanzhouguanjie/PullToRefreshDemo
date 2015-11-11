package com.zgj.pulltorefreshdemo;

import java.util.ArrayList;

import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreGridViewContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.loadmore.LoadMoreUIHandler;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

//http://cube-sdk.liaohuqiu.net/cn/load-more/

public class MainActivity extends Activity {

	private PtrFrameLayout mPtrFrame;

	private GridViewWithHeaderAndFooter mListView;
	
	private MyAdapter mAdapter;
	
	private ArrayList<Bean> mList;
	
	private LoadMoreGridViewContainer loadMoreContainer;
	
	private boolean mIsEnd = false;
	
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		
//		loadMoreListViewContainer.addView(view);
		
//		loadMoreListViewContainer.setLoadMoreView(view);
		

		// 获取view的引用
		
		

		// 创建
//		MyLoadMoreDefaultFooterView footerView = 
//				new MyLoadMoreDefaultFooterView(loadMoreListViewContainer.getContext());


		// 设置
//		loadMoreListViewContainer.setLoadMoreView(footerView);
//		loadMoreListViewContainer.setLoadMoreUIHandler(footerView);
		

		mListView = (GridViewWithHeaderAndFooter) findViewById(R.id.load_more_grid_view);
		
		  // header place holder
        View headerMarginView = new View(this);
        headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LocalDisplay.dp2px(20)));
        mListView.addHeaderView(headerMarginView);
        
        // load more container
        loadMoreContainer = (LoadMoreGridViewContainer) findViewById(R.id.load_more_grid_view_container);
        loadMoreContainer.useDefaultFooter();
        
		loadMoreContainer.setAutoLoadMore(true);
		loadMoreContainer.setShowLoadingForFirstPage(true);

		
		mList = new ArrayList<Bean>();
		
		for( int loop=0;loop<100;loop++ ){
			Bean b = new  Bean("测试数据:"+loop);
			mList.add(b);
		}
		
		mAdapter = new MyAdapter(this, mList);

		mListView.setAdapter(mAdapter);
		
		
		
		mHandler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if( msg.what == 1 ){
					// 数据加载完后，设置是否为空，是否有更多
//					loadMoreListViewContainer.loadMoreFinish(false, false);
					mPtrFrame.refreshComplete();
					// 更新列表显示
					mAdapter.notifyDataSetChanged();
				}else if( msg.what == 2 ){
					// 更新列表显示
					mAdapter.notifyDataSetChanged();
				}
			};
		};
		
		mPtrFrame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
		mPtrFrame.setLoadingMinTime(1000);
//		mPtrFrame.autoRefresh(false);
		mPtrFrame.setPinContent(true);
		mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                mDataModel.queryFirstPage();
            	queryNextPage();
            }
        });

	}
	
	private void queryNextPage(){
		new Thread(){
			public void run() {
				SystemClock.sleep(3000);
//				for( int loop=0;loop<5;loop++ ){
//					Bean b = new  Bean("测试数据:"+loop);
//					mList.add(b);
//				}
				Message msg = new Message();
				msg.what = 1;
				mHandler.sendMessage(msg);
			};
		}.start();
		
	}
	
	

}
