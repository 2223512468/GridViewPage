package com.gridviewpage.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.gridviewpage.R;
import com.gridviewpage.adapter.AppAdapter;
import com.gridviewpage.adapter.MyViewPagerAdapter;
import com.gridviewpage.control.PageControl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 实现横向滑动的GridView
 *
 * @author xxs
 */
public class MainActivity extends Activity {
    private static final float APP_PAGE_SIZE = 8.0f;
    private MyViewPagerAdapter adapter;
    private ViewPager viewPager;
    LayoutInflater inflater;

    private PageControl pageControl;

    private Map<Integer, GridView> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflater = getLayoutInflater();

        // setContentView(R.layout.activity_main);
        ViewGroup main = (ViewGroup) inflater.inflate(R.layout.activity_main,
                null);
        initViews();
        viewPager = (ViewPager) findViewById(R.id.myviewpager);
        adapter = new MyViewPagerAdapter(this, map);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new MyListener());

    }

    /**
     * 获取系统所有的应用程序，并根据APP_PAGE_SIZE生成相应的GridView页面
     */
    public void initViews() {
        final PackageManager packageManager = getPackageManager();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        // get all apps
        final List<ResolveInfo> apps = packageManager.queryIntentActivities(
                mainIntent, 0);
        // the total pages
        final int PageCount = (int) Math.ceil(apps.size() / APP_PAGE_SIZE);

        map = new HashMap<Integer, GridView>();
        for (int i = 0; i < PageCount; i++) {
            GridView appPage = new GridView(this);
            final AppAdapter adapter = new AppAdapter(this, apps, i);
            appPage.setAdapter(adapter);
            appPage.setNumColumns(4);
//			appPage.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1,
//						int arg2, long arg3) {
//					// TODO Auto-generated method stub
//		          Toast.makeText(MainActivity.this, "这是第"+((adapter.getPage()*8)+arg2)+"个", Toast.LENGTH_LONG).show();
//		          
//		          
//				}
//			});
            appPage.setOnItemClickListener(adapter);
            map.put(i, appPage);

        }

        ViewGroup main = (ViewGroup) inflater.inflate(R.layout.activity_main,
                null);
        // group是R.layou.main中的负责包裹小圆点的LinearLayout.
        ViewGroup group = (ViewGroup) main.findViewById(R.id.viewGroup);
        pageControl = new PageControl(this, (LinearLayout) group, PageCount);
        setContentView(main);
    }

    class MyListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {

            pageControl.selectPage(arg0);
        }

    }
}
