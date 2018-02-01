package com.test.exercise.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.test.exercise.R;
import com.test.exercise.presenters.ISettingsPresenter;
import com.test.exercise.presenters.SettingsPresenter;

import java.util.Arrays;

import static com.test.exercise.utils.HexConverterUtils.PREFERNCE_INDEX;
import static com.test.exercise.utils.HexConverterUtils.PREFERNCE_KEY;
import static com.test.exercise.utils.HexConverterUtils.PREFERNCE_NAME;
import static com.test.exercise.utils.HexConverterUtils.RESULT_KEY;


/**
 * Created by renu on 31/01/18.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, ISettingsActivity {
    private int DEFAULT_VALUE = 4;
    private ISettingsPresenter mPresenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPresenter = new SettingsPresenter(this);
        Spinner lengthSelector = (Spinner) findViewById(R.id.lenght_selector);
        String[] lenghtValues = getResources().getStringArray(R.array.length);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, Arrays.asList(lenghtValues));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lengthSelector.setAdapter(dataAdapter);

        lengthSelector.setSelection(getSelectedIndex());

        lengthSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selectedValue = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                storeLength(selectedValue, i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button generate = (Button) findViewById(R.id.generatehex);
        generate.setOnClickListener(this);


    }


    private void storeLength(int length, int postion) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFERNCE_NAME, MODE_PRIVATE).edit();
        editor.putInt(PREFERNCE_KEY, length);
        editor.putInt(PREFERNCE_INDEX, postion);

        editor.apply();
    }

    private int getSelectedLength() {
        SharedPreferences prefernce = getSharedPreferences(PREFERNCE_NAME, MODE_PRIVATE);
        return prefernce.getInt(PREFERNCE_KEY, DEFAULT_VALUE);
    }

    private int getSelectedIndex() {
        SharedPreferences prefernce = getSharedPreferences(PREFERNCE_NAME, MODE_PRIVATE);
        return prefernce.getInt(PREFERNCE_INDEX, 0);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.generatehex:
                mPresenter.generateHexString(getSelectedLength());
                break;
        }
    }

    @Override
    public void sendResultToParent(String result) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_KEY, result);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
