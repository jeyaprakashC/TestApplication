package com.test.exercise.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.exercise.R;
import com.test.exercise.presenters.IMainActivityPresenter;
import com.test.exercise.presenters.MainActivityPresenter;

import static com.test.exercise.utils.HexConverterUtils.RESULT_KEY;


public class MainActivity extends AppCompatActivity implements IMainActivity, View.OnClickListener {

    private IMainActivityPresenter mPresenter = null;
    private EditText inputTextView = null;
    private static int requestCode = 9090;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenter(this);

        Button sumButton = (Button) findViewById(R.id.button_sum);
        sumButton.setOnClickListener(this);

        Button multiplyButton = (Button) findViewById(R.id.button_multiply);
        multiplyButton.setOnClickListener(this);

        ImageView settings = (ImageView) findViewById(R.id.settings_view);
        settings.setOnClickListener(this);

        inputTextView = (EditText) findViewById(R.id.input_text);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_sum:
            case R.id.button_multiply:

                if (inputTextView != null) {

                    String input = inputTextView.getText().toString();
                    if (input != null && input.length() > 0) {
                        if (view.getId() == R.id.button_sum) {
                            mPresenter.sum(inputTextView.getText().toString());
                        } else {
                            mPresenter.multiply(input);
                        }
                    } else {
                        Toast.makeText(this, getString(R.string.toast_msg), Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            case R.id.settings_view:
                launchSettingsView();
                break;
        }
    }


    public void setResults(String result) {
        TextView resultView = (TextView) findViewById(R.id.lable_result_content);
        resultView.setText(result);
    }


    public void setGeneratedInput(String result) {
        inputTextView = (EditText) findViewById(R.id.input_text);
        inputTextView.setText(result);
    }


    public void launchSettingsView() {
        startActivityForResult(new Intent(this, SettingsActivity.class), requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra(RESULT_KEY);
                setGeneratedInput(result);
            } else {
                Toast.makeText(this, getString(R.string.toast_msg_error), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
