package com.gridviewpage.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gridviewpage.R;

/**
 * 用于GridView装载数据的适配器
 * 
 * @author xxs
 * 
 */
public class AppAdapter extends BaseAdapter implements OnItemClickListener {
	private List<ResolveInfo> mList;// 定义一个list对象
	private Context mContext;// 上下文
	public static final int APP_PAGE_SIZE = 8;// 每一页装载数据的大小
	private PackageManager pm;// 定义一个PackageManager对象

	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            上下文
	 * @param list
	 *            所有APP的集合
	 * @param page
	 *            当前页
	 */
	public AppAdapter(Context context, List<ResolveInfo> list, int page) {
		mContext = context;
		pm = context.getPackageManager();
		this.page = page;
		mList = new ArrayList<ResolveInfo>();
		// 根据当前页计算装载的应用，每页只装载16个
		int i = page * APP_PAGE_SIZE;// 当前页的其实位置
		int iEnd = i + APP_PAGE_SIZE;// 所有数据的结束位置
		while ((i < list.size()) && (i < iEnd)) {
			mList.add(list.get(i));
			i++;
		}
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.app_item, parent, false);
		}
		ResolveInfo appInfo = mList.get(position);
		ImageView appicon = (ImageView) convertView
				.findViewById(R.id.ivAppIcon);
		TextView appname = (TextView) convertView.findViewById(R.id.tvAppName);
		appicon.setImageDrawable(appInfo.loadIcon(pm));
		appname.setText(appInfo.loadLabel(pm));

		return convertView;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		int index = this.getPage() * APP_PAGE_SIZE + arg2;

		for (int i = 0; i < APP_PAGE_SIZE; i++) {
			if (i == arg2) {

				arg0.getChildAt(i).setBackgroundResource(
						R.drawable.gift_selected);
			} else {
				arg0.getChildAt(i).setBackgroundColor(Color.argb(0, 0, 0, 0));
			}
		}

	}

}
