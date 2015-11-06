package com.zgj.pulltorefreshdemo;

import java.util.ArrayList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements OnRefreshListener,
		OnPullEventListener {
	private PullToRefreshListView mPullToRefreshListView;

	private GetDataTask mGetDataTask;

	private ArrayAdapter<String> mAdapter;

	private ArrayList<String> mArrayList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.list);
		// 添加下拉刷新事件
		mPullToRefreshListView.setOnRefreshListener(this);
		// 添加下拉刷新状态事件，以便及时现实刷新时间
		mPullToRefreshListView.setOnPullEventListener(this);
		
		mPullToRefreshListView.setMode(Mode.BOTH);

		mArrayList.add("测试数据1");
		mArrayList.add("测试数据2");
		mArrayList.add("测试数据3");
		mArrayList.add("测试数据4");
		mArrayList.add("测试数据5");
		mArrayList.add("测试数据6");

		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, mArrayList);
		
		mPullToRefreshListView.setAdapter(mAdapter);
	}
	
	private Mode mMode;

	@Override
	public void onPullEvent(PullToRefreshBase refreshView, State state,
			Mode direction) {
		// 开始下拉时更新上次刷新时间
		mPullToRefreshListView.getLoadingLayoutProxy().setLastUpdatedLabel(
				"123");
		if( direction == Mode.PULL_FROM_END ){
			mMode = Mode.MANUAL_REFRESH_ONLY;
		}else if( direction == Mode.PULL_FROM_START ){
			mMode = Mode.DISABLED;
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase refreshView) {
		// 如果正在刷新，则返回
		if (mGetDataTask != null && mGetDataTask.getStatus() == Status.RUNNING)
			return;
		mPullToRefreshListView.setMode(mMode);
		mGetDataTask = new GetDataTask();
		mGetDataTask.execute();
	}

	private class GetDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Call setRefreshing when the list begin to refresh.
			mPullToRefreshListView.setRefreshing(true);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Simulates a background job.
			SystemClock.sleep(5000);
			mArrayList.add("添加1");
			mArrayList.add("添加2");
			mArrayList.add("添加3");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Do some stuff here
			// Call onRefreshComplete when the list has been refreshed.
			mPullToRefreshListView.onRefreshComplete();
			mPullToRefreshListView.setMode(Mode.BOTH);
			mAdapter.notifyDataSetChanged();

		}

	}

}
