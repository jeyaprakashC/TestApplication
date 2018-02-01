package com.test.exercise.presenters;

import com.test.exercise.utils.HexConverterUtils;
import com.test.exercise.view.ISettingsActivity;

/**
 * Created by renu on 31/01/18.
 */

public class SettingsPresenter implements ISettingsPresenter {

    ISettingsActivity mActivityView = null;

    public SettingsPresenter(ISettingsActivity activityView) {
        this.mActivityView = activityView;
    }

    public void generateHexString(int length) {
        HexConverterUtils convertor = new HexConverterUtils();
        String value = convertor.generateRandomString(length);
        String hexString = convertor.getHexString(value);
        mActivityView.sendResultToParent(hexString);

    }
}
