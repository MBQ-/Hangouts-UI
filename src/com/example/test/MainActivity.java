package com.example.test;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint({ "CutPasteId" })
public class MainActivity extends FragmentActivity {
	
	public static final int pane_slider_fade_color = 2131427514;
	public static final int panel_shadow = 2130838923;
	
	private boolean canSlide;
	
	int[] flag;
	int[] flag2;
	int[] flag3;
	
	String[] items;
	String[] items2;
	String[] items3;
	
	View.OnLayoutChangeListener listener;
	Drawable localDrawable;
	
	ListView lv;
	ListView lv2;
	ListView lv3;
	
	private ActionBarHelper mActionBar;
	
	private String[] mCategories;
	
	private ListView mList;
	
	private SlidingPaneLayout mSlidingLayout;
	
	private CharSequence mTitle;
	
	ActionBarDrawerToggle mToggle;
	
	SharedPreferences prefs;
	
	Fragment test = new Test();

	
	private ActionBarHelper createActionBarHelper() {
		if (Build.VERSION.SDK_INT >= 14) {
			return new ActionBarHelperICS();
		}
		return new ActionBarHelper();
	}

	private void selectItem(int position) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		switch (position) {

		case 0:
			ft.replace(R.id.content_frame, test);
			break;

		case 1:
			
			break;
		}
		
		ft.commit();
		
		this.mList.setItemChecked(position, true);
		this.mSlidingLayout.closePane();
		
		return;
	}

	public boolean isCanSlide() {
		return this.canSlide;
	}

	public void onConfigurationChanged(Configuration paramConfiguration) {
		super.onConfigurationChanged(paramConfiguration);
	}

	@SuppressLint({ "ResourceAsColor" })
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		
		getWindow().setFlags(16777216, 16777216);
		setContentView(2130903040);
		
		getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setHomeButtonEnabled(false);
		
		this.mActionBar = createActionBarHelper();
		this.mActionBar.init();
		
		Resources localResources = getResources();
		
		getActionBar();
		
		this.mSlidingLayout = ((SlidingPaneLayout) findViewById(2131100146));
		this.mList = ((ListView) findViewById(2131100147));
		this.mSlidingLayout.setPanelSlideListener(new SliderListener());
		this.mSlidingLayout.openPane();
		this.mSlidingLayout.setHorizontalFadingEdgeEnabled(true);
		this.mSlidingLayout.setFitsSystemWindows(true);
		((ViewGroup) getWindow().getDecorView())
				.requestTransparentRegion(this.mSlidingLayout);
		this.mSlidingLayout.setShadowDrawable(localResources
				.getDrawable(2130837513));
		this.mSlidingLayout.setSliderFadeColor(localResources
				.getColor(2130968590));
		this.mSlidingLayout.setParallaxDistance(localResources
				.getDimensionPixelOffset(2131034120));
		this.mSlidingLayout.setAnimationCacheEnabled(true);
		this.mSlidingLayout.buildDrawingCache(true);
		this.mSlidingLayout.setAlwaysDrawnWithCacheEnabled(true);
		this.mCategories = getResources().getStringArray(2131230720);
		this.mList.setAdapter(new ArrayAdapter<Object>(this, 2130903041,
				this.mCategories));
		this.mList.setOnItemClickListener(new ListItemClickListener());
		this.mSlidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(
				new FirstLayoutListener());
		
	}

	public boolean onCreateOptionsMenu(Menu paramMenu) {
		if (this.mSlidingLayout.isOpen()) {
			getMenuInflater().inflate(2131361792, paramMenu);
			
			return true;
		}
		return super.onCreateOptionsMenu(paramMenu);
	}

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if (paramKeyEvent.getKeyCode() == 4) {
		}
		switch (paramKeyEvent.getAction()) {
		default:
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
		if ((paramMenuItem.getItemId() == 16908332)
				&& (!this.mSlidingLayout.isOpen())) {
			this.mSlidingLayout.smoothSlideOpen();
			return true;
		}
		paramMenuItem.getItemId();
		return super.onOptionsItemSelected(paramMenuItem);
	}

	public void setCanSlide(boolean paramBoolean) {
		this.canSlide = paramBoolean;
	}

	public int setParallaxDistance(int paramInt) {
		return paramInt;
	}

	public void setSliderFadeColor(int paramInt) {
	}

	public void setTitle(CharSequence paramCharSequence) {
		this.mTitle = paramCharSequence;
		getActionBar().setTitle(this.mTitle);
	}

	private class ActionBarHelper {
		private ActionBarHelper() {
		}

		public void init() {
			MainActivity.this.getActionBar().setDisplayHomeAsUpEnabled(true);
			MainActivity.this.getActionBar().setHomeButtonEnabled(true);
		}

		public void onFirstLayout() {
		}

		public void onPanelClosed() {
			MainActivity.this.getActionBar().setDisplayHomeAsUpEnabled(true);
			MainActivity.this.getActionBar().setHomeButtonEnabled(true);
		}

		public void onPanelOpened() {
			MainActivity.this.getActionBar().setDisplayHomeAsUpEnabled(false);
			MainActivity.this.getActionBar().setHomeButtonEnabled(false);
		}
	}

	private class ActionBarHelperICS extends MainActivity.ActionBarHelper {
		private final ActionBar mActionBar = MainActivity.this.getActionBar();

		ActionBarHelperICS() {
		}

		public void init() {
			this.mActionBar.setDisplayHomeAsUpEnabled(true);
			this.mActionBar.setHomeButtonEnabled(true);
		}

		@SuppressWarnings("deprecation")
		public void onFirstLayout() {
			if ((MainActivity.this.mSlidingLayout.canSlide())
					&& (!MainActivity.this.mSlidingLayout.isOpen())) {
				onPanelClosed();
				return;
			}
			onPanelOpened();
			this.mActionBar.setHomeButtonEnabled(false);
			this.mActionBar.setDisplayHomeAsUpEnabled(false);
			invalidateOptionsMenu();
		}

		public void onPanelClosed() {
			super.onPanelClosed();
			this.mActionBar.setDisplayHomeAsUpEnabled(true);
			this.mActionBar.setHomeButtonEnabled(true);
			// this.mActionBar.setTitle("XP ROM Center");
		}

		public void onPanelOpened() {
			super.onPanelOpened();
			this.mActionBar.setHomeButtonEnabled(false);
			this.mActionBar.setDisplayHomeAsUpEnabled(false);
			// this.mActionBar.setTitle("Select an item!");
		}
	}

	public static class CategoriesFragment extends Fragment {
		public static final String ARG_CATEGORY = "category";

		public View onCreateView(LayoutInflater paramLayoutInflater,
				ViewGroup paramViewGroup, Bundle paramBundle) {
			View localView = paramLayoutInflater.inflate(2130903042,
					paramViewGroup, false);
			int i = getArguments().getInt("category");
			String str = getResources().getStringArray(2131230720)[i];
			getActivity().setTitle(str);
			return localView;
		}
	}

	private class FirstLayoutListener implements
			ViewTreeObserver.OnGlobalLayoutListener {
		private FirstLayoutListener() {
		}

		@SuppressLint({ "NewApi" })
		public void onGlobalLayout() {
			MainActivity.this.mActionBar.onFirstLayout();
			MainActivity.this.mSlidingLayout.getViewTreeObserver()
					.removeOnGlobalLayoutListener(this);
		}
	}

	private class ListItemClickListener implements
			AdapterView.OnItemClickListener {
		private ListItemClickListener() {
		}

		@SuppressWarnings("deprecation")
		public void onItemClick(AdapterView<?> paramAdapterView,
				View paramView, int paramInt, long paramLong) {
			MainActivity.this.selectItem(paramInt);
			MainActivity.this.mSlidingLayout.smoothSlideClosed();
		}
	}

	private class SliderListener extends
			SlidingPaneLayout.SimplePanelSlideListener {
		private SliderListener() {
		}

		public void onPanelClosed(View paramView) {
			MainActivity.this.mActionBar.onPanelClosed();
		}

		public void onPanelOpened(View paramView) {
			MainActivity.this.mActionBar.onPanelOpened();
		}
	}
}