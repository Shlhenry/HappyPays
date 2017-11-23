package com.futeng.happypays.tools;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lincoln on 15/7/15.
 * 倒计时按钮
 */
public class TimerButton extends Button{

    Callback callback;
    Timer    timer;
    private TimerTask task;
    int maxSeconds;
    int curSeconds;
    CharSequence text;
    int color;
    private Runnable updateText = new Runnable()
    {
        @Override
        public void run()
        {
            if (callback != null)
            {
                setText(String.format(callback.getTickerText(), curSeconds));
                curSeconds--;
                if (curSeconds < 0)
                {
                    setEnabled(true);
                    setText(text);
                    task.cancel();
                }
            }
        }
    };

    public TimerButton(Context context)
    {
        super(context);

    }

    public TimerButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public TimerButton(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void start() {
        if (callback != null) {
            maxSeconds = callback.getMaxTime();
            curSeconds = maxSeconds;
        }
        if (timer == null) {
            timer = new Timer();
        }

        if (task != null) {
            task.cancel();
        }

        task = new TimerTask() {
            @Override
            public void run() {
                post(updateText);
            }
        };
        setEnabled(false);
        text=getText();
        timer.schedule(task, 0, 1000);

    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (timer!=null){
            timer.cancel();
            timer = null;
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        /**
         * 返回值是一个带有%d的字符串
         */
        String getTickerText();

        int getMaxTime();


    }
}
