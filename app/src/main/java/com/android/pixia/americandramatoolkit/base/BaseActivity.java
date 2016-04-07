package com.android.pixia.americandramatoolkit.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.pixia.americandramatoolkit.R;
import com.android.pixia.americandramatoolkit.fragment.UpdateFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 * Created by pixia on 2016/4/6.
 */
public abstract class BaseActivity extends AppCompatActivity{

    public Toolbar mToolBar;
    public ActionBar mActionBar;
    private ProfileDrawerItem profileDrawerItem;
    private AccountHeader mAccount;
    private Drawer mDrawer;
    public UpdateFragment mUpdateFragment;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void initFragment(){
        mUpdateFragment = UpdateFragment.newInstance(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initFragment();
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        if(getSupportActionBar()!=null){
            mActionBar = getSupportActionBar();
        }

        initView();

    }

    protected abstract void initView();

    public void showToast(String msg){
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void initDrawer(){

        profileDrawerItem = new ProfileDrawerItem()
                .withName(getString(R.string.user_name))
                .withEmail(getString(R.string.user_email))
                .withIcon(getResources().getDrawable(R.drawable.profile));

        mAccount = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profileDrawerItem
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //menu_item
        PrimaryDrawerItem item_update = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName(R.string.drawer_time);
        PrimaryDrawerItem item_news = new PrimaryDrawerItem()
                .withIcon(R.mipmap.ic_launcher)
                .withName(R.string.drawer_news);

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolBar)
                .withAccountHeader(mAccount)
                .addDrawerItems(
                        item_update,
                        item_news,
                        new PrimaryDrawerItem()
                                .withIcon(R.mipmap.ic_launcher)
                                .withName(R.string.drawer_tip),
                        new PrimaryDrawerItem()
                                .withIcon(R.mipmap.ic_launcher)
                                .withName(R.string.drawer_save),
                        new PrimaryDrawerItem()
                                .withIcon(R.mipmap.ic_launcher)
                                .withName(R.string.drawer_setting),
                        new PrimaryDrawerItem()
                                .withIcon(R.mipmap.ic_launcher)
                                .withName(R.string.drawer_exit)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                replaceFragment(mUpdateFragment);
                                break;
                            case 2:
                                showToast("2");
                                break;
                            case 3:
                                showToast("3");
                                break;
                            case 4:
                                showToast("4");
                                break;
                            case 5:
                                showToast("5");
                                break;
                            case 6:
                                finish();
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(fragment.isAdded()){
            transaction.hide(BaseFragment.getShowingFragement(getSupportFragmentManager()));
            transaction.show(fragment);
        }else{
            transaction.replace(R.id.content_layout, fragment);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}
