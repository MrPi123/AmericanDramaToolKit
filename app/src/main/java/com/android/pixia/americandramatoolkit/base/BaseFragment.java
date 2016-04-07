package com.android.pixia.americandramatoolkit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.pixia.americandramatoolkit.R;

import java.util.List;

/**
 * Created by pixia on 2016/4/6.
 *
 */

public class BaseFragment extends Fragment {

    public BaseActivity mActivity;
    public View mView;
    protected boolean showing = false;

    public View findViewById(int id){
        if(getView()!=null){
            return getView().findViewById(id);
        }else {
            return mView.findViewById(id);
        }
    }

    public static BaseFragment getShowingFragement(FragmentManager fragmentManager){
        List<Fragment> fragments = fragmentManager.getFragments();
        for(Fragment fragment: fragments){
            BaseFragment fragmentBase = (BaseFragment)fragment;
            if( fragmentBase != null)
                if(fragmentBase.showing ) {
                    return fragmentBase;
                }
        }
        return null;
    }

    public boolean isShowing() {
        return showing;
    }

    @Override
    public void onResume() {
        super.onResume();
        showing = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        showing = false;
    }

}
