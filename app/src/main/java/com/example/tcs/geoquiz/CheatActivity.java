package com.example.tcs.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by TCS on 1/17/15.
 */
public class CheatActivity extends ActionBarActivity {

    private static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.tcs.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.example.tcs.geoquiz.answer_shown";
    private static final String KEY_CHEATER = "cheater";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private TextView mBuildNumber;
    private Button mShowAnswer;
    private boolean mAnswerShown;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d(TAG, "onCreate() called");

        setContentView(R.layout.activity_cheat);
        //Log.d(TAG, "Content view set");
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        //Log.d(TAG, "Boolean extracted");
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        //Log.d(TAG, "Got answerTextView id");
        if(savedInstanceState != null) {
            //Log.d(TAG, "Saved instance exists");
            mAnswerShown = savedInstanceState.getBoolean(KEY_CHEATER, false);
            //Log.d(TAG, "Got boolean from saved instance");
            setAnswerShownResult(mAnswerShown);
            //Log.d(TAG, "set answershown True");
            if(mAnswerShown)
            {
                if(mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerShown = true;
                setAnswerShownResult(true);
            }
        }
        //Testing...
        //Log.d(TAG, "Finished looking for saved instances");

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        //Log.d(TAG, "getButtonID");
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerShown = true;
                setAnswerShownResult(true);
            }
        });
        //Log.d(TAG, "Past possible error");
        //Log.d(TAG, Build.VERSION.SDK_INT + "");
        mBuildNumber = (TextView)findViewById(R.id.buildNumber);
        //Log.d(TAG, "Error 1");
        String text = "API Level " + Build.VERSION.SDK_INT + "";
        //Log.d(TAG, "Text: "+text);
        mBuildNumber.setText(text);
        //Log.d(TAG, "Error 2");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //Log.d(TAG, "onSaveInstanceState() called");
        savedInstanceState.putBoolean(KEY_CHEATER, mAnswerShown);
    }
}
