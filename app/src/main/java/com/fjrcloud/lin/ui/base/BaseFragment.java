package com.fjrcloud.lin.ui.base;

import android.support.v4.app.Fragment;

/**
 * Created by lin on 2016/10/31.
 */
public abstract class BaseFragment extends Fragment {

    protected boolean mIsVisible;

    protected boolean mIsPrepared;

    protected boolean mIsFirstLoad;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInVisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void  onInVisible() {

    }

    public abstract void lazyLoad();
}
