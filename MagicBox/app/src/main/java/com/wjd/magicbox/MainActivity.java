package com.wjd.magicbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.common.activity.AbsBaseActivity;
import com.wjd.magicbox.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AbsBaseActivity implements ViewPager.OnPageChangeListener {

    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppbarLayout;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NavigationView mNavigationView;

    private String[] mTitles;
    private List<Fragment> mFragments;
    private ViewPagerAdapter mViewPagerAdapter;
    private boolean isLogined = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initData();
        configViews();

    }

    private void initData() {
        mTitles = getResources().getStringArray(R.array.tab_titles);
        mFragments = new ArrayList<Fragment>();
        JokeFragment jokeFragment = new JokeFragment();
        NewsFragment newsFragment = new NewsFragment();
        GirlsFragment photosFragment = new GirlsFragment();
        mFragments.add(jokeFragment);
        mFragments.add(newsFragment);
        mFragments.add(photosFragment);

    }

    private void configViews() {

        setSupportActionBar(mToolBar);

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

//        View header_nav = getLayoutInflater().inflate(R.layout.header_nav,null);
//        RelativeLayout user_layout = (RelativeLayout)header_nav.findViewById(R.id.id_user_layout);
//        user_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isLogined){
//                    //TODO:跳转到登陆页面
//                    Toast.makeText(MainActivity.this, "跳转到登陆页面", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        mNavigationView.addHeaderView(header_nav);

        mNavigationView.inflateHeaderView(R.layout.header_nav);
        mNavigationView.inflateMenu(R.menu.menu_nav);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(this);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mViewPagerAdapter);

        onNavigationItemClick(mNavigationView);
    }

    private void findViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_coordinatorlayout);
        mAppbarLayout = (AppBarLayout) findViewById(R.id.id_appbarlayout);
        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mNavigationView = (NavigationView) findViewById(R.id.id_navigationview);

    }

    private void onNavigationItemClick(NavigationView mNavigationView) {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String msg = "";
                switch (menuItem.getItemId()) {
                    case R.id.nav_menu_home:
                        msg = menuItem.getTitle().toString();
                        break;
                    case R.id.nav_menu_categories:
                        msg = menuItem.getTitle().toString();
                        break;
                    case R.id.nav_menu_feedback:
                        msg = menuItem.getTitle().toString();
                        break;
                    case R.id.nav_menu_setting:
                        msg = menuItem.getTitle().toString();
                        break;
                }

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_weather) {
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
