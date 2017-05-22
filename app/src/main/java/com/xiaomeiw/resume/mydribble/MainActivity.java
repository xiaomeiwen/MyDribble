package com.xiaomeiw.resume.mydribble;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaomeiw.resume.mydribble.view.shot_list.ShotListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) Toolbar toolbar;
    //整个主页面的layout = Fragment + NavigationView
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    //抽屉菜单的Layout：NavigationView = headerlayout + menu
    @BindView(R.id.drawer) NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.i("state","Create");
        //抽屉的hunbergur必须设置的
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //画抽屉,包含着画Fragment主界面
        setupDrawer();

        //不会转屏就从新画Fragment
        if (savedInstanceState == null) {
            Log.i("state","inFunction");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, ShotListFragment.newInstance())
                    .commit();   //防止屏幕旋转之后增加新的item
        }
    }

    //必须在onPostCreate和onConfigurationChanged中进行设置，可以理解为屏幕刚画好之后进行绑定，屏幕旋转之后进行绑定

    //onCreate –> onContentChanged –> onStart –> onPostCreate –>
    // onResume –> onPostResume –> onPause –> onStop –> onDestroy
    //Activity onStart之后调用, 官方规定的Toggle的调用时间
    //将Toggle与页面同步
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i("state","onPost");
        drawerToggle.syncState();
    }

    //onConfigurationChanged事件并不是只有屏幕方向改变才可以触发，其他的一些系统设置改变也可以触发，比如打开或者隐藏键盘。
    //发生这种变化时，Android 会重启正在运行的 Activity（先后调用 onDestroy() 和 onCreate()）
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("state","onConfigure");
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawer() {
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,          /* DrawerLayout object */
                R.string.open_drawer,         /* "open drawer" description */
                R.string.close_drawer         /* "close drawer" description */
        );
        //画汉堡包
        drawerLayout.setDrawerListener(drawerToggle);
        //抽屉菜单点击后画不同的fragment
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //抽屉菜单选择
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()) {
                    drawerLayout.closeDrawers();
                    return true;
                }

                Fragment fragment = null;
                //返回true选中，什么也不做返回false
                switch (item.getItemId()) {
                    case R.id.drawer_item_home:
                        //new Instance, 选中返回的是不同的列表
                        fragment =  ShotListFragment.newInstance();
                        Toast.makeText(MainActivity.this, "home clicked", Toast.LENGTH_LONG).show();
                        setTitle(R.string.drawer_menu_home);
                        break;
                    case R.id.drawer_item_likes:
                        fragment =  ShotListFragment.newInstance();
                        Toast.makeText(MainActivity.this, "like clicked", Toast.LENGTH_LONG).show();
                        setTitle(R.string.drawer_menu_likes);
                        break;
                    case R.id.drawer_item_buckets:
                        fragment =  ShotListFragment.newInstance();
                        Toast.makeText(MainActivity.this, "home buckets", Toast.LENGTH_LONG).show();
                        setTitle(R.string.drawer_menu_buckets);
                        break;
                }
                //点击后菜单自动关闭
                drawerLayout.closeDrawers();
                //代码风格！
                if (fragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }
}
