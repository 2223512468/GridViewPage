package com.gridviewpage.control;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gridviewpage.R;

public class PageControl {

	private LinearLayout layout;
	private TextView[] textViews;
	private TextView textView;
	private int pageSize;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private int selectedImage = R.drawable.radio_sel;
	private int unselectedImage = R.drawable.radio;

	private int currentPage = 0;
	private Context mContext;

	public PageControl(Context context, LinearLayout layout, int pageSize) {
		this.mContext = context;
		this.pageSize = pageSize;
		this.layout = layout;

		initDots();
	}

	void initDots() {
		textViews = new TextView[pageSize];
		for (int i = 0; i < pageSize; i++) {

			textView = new TextView(mContext);
			textView.setLayoutParams(new LayoutParams(30, 30));
			textView.setPadding(2, 2, 2, 0);

			textViews[i] = textView;
			if (i == 0) {
				// Ĭ�Ͻ��������һ��ͼƬ��ѡ��;
				textViews[i].setBackgroundResource(R.drawable.radio_sel);
			} else {
				textViews[i].setBackgroundResource(R.drawable.radio);
			}
			layout.addView(textViews[i]);
		}
	}

	boolean isFirst() {
		return this.currentPage == 0;
	}

	boolean isLast() {
		return this.currentPage == pageSize;
	}

	public void selectPage(int current) {
		for (int i = 0; i < textViews.length; i++) {
			textViews[current].setBackgroundResource(R.drawable.radio_sel);
			if (current != i) {
				textViews[i].setBackgroundResource(R.drawable.radio);
			}
		}
	}

	void turnToNextPage() {
		if (!isLast()) {
			currentPage++;
			selectPage(currentPage);
		}
	}

	void turnToPrePage() {
		if (!isFirst()) {
			currentPage--;
			selectPage(currentPage);
		}
	}
}
