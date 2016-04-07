package com.android.pixia.americandramatoolkit.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;

import com.android.pixia.americandramatoolkit.R;
import com.android.pixia.americandramatoolkit.base.BaseActivity;
import com.android.pixia.americandramatoolkit.fragment.UpdateFragment;

public class MainActivity extends BaseActivity {

    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
    }

    @Override
    protected void initView() {
        replaceFragment(mUpdateFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
