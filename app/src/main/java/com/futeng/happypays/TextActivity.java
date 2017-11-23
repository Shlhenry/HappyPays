package com.futeng.happypays;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.futeng.happypays.tools.SpeechUtils;

public class TextActivity extends Activity {

    @InjectView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        SpeechUtils.getInstance(getApplicationContext()).speakText("shh is stupied");
        Log.e("4444","4524");
    }

}
