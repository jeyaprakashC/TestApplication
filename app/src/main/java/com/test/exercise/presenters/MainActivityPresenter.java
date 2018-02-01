package com.test.exercise.presenters;

import com.test.exercise.utils.HexConverterUtils;
import com.test.exercise.view.IMainActivity;

/**
 * Created by renu on 31/01/18.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivity mActivityView = null;
    HexConverterUtils mUtils = null;

    public MainActivityPresenter(IMainActivity activityView) {
        this.mActivityView = activityView;
        mUtils = new HexConverterUtils();
    }


    @Override
    public void sum(String hexString) {
        int result = mUtils.sum(hexString);
        mActivityView.setResults(String.valueOf(result));

    }

    @Override
    public void multiply(String hexString) {
        int result = mUtils.multiply(hexString);
        mActivityView.setResults(String.valueOf(result));

    }
}
