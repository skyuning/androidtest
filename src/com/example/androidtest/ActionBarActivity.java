//package com.example.androidtest;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.SpinnerAdapter;
//
//public class ActionBarActivity extends Activity implements TabListener, OnNavigationListener {
//    private MenuInflater inflater;
//    Fragment firstFragment;
//    Fragment secondFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.action);
//        inflater = new MenuInflater(this);
//        // getActionBar().setHomeButtonEnabled(true);//应用图标作为导航条
//        /*
//         * tab样式菜单 ActionBar acitonbar=getActionBar();
//         * acitonbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); Tab
//         * t=acitonbar
//         * .newTab().setIcon(android.R.drawable.menu_frame).setText("back");
//         * t.setTabListener(this); Tab
//         * t1=acitonbar.newTab().setIcon(android.R.drawable
//         * .menu_frame).setText("back"); t1.setTabListener(this); Tab
//         * t2=acitonbar
//         * .newTab().setIcon(android.R.drawable.menu_frame).setText("back");
//         * t2.setTabListener(this); acitonbar.addTab(t); acitonbar.addTab(t1);
//         * acitonbar.addTab(t2);
//         */
//        /* 下拉导航 */
//        ActionBar acitonbar = getActionBar();
//        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
//                R.array.action_list,
//                android.R.layout.simple_spinner_dropdown_item);
//        acitonbar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);// 设置Actionbar为下拉导航方式
//        acitonbar.setListNavigationCallbacks(mSpinnerAdapter, this);// 设置下拉导航和监听事件
//        firstFragment = new MyFragment();// first fragmen
//        secondFragment = new MyFragment();// Second fragment
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // TODO Auto-generated method stub
//        // inflater.inflate(R.menu.actionbar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // TODO Auto-generated method stub
//        Log.i("debug", "menu selected");
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//    /* 监听事件 */
//    @Override
//    public boolean onNavigationItemSelected(int pos, long arg1) {
//        // TODO Auto-generated method stub
//        Log.i("debug", "position--" + pos);
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        if (pos == 0) {
//            ft.replace(R.id.frameLayout1, secondFragment);// 替换fragment
//        } else {
//            ft.replace(R.id.frameLayout1, firstFragment);
//        }
//
//        ft.commit();// 注意提交事务
//        return false;
//    }
//}