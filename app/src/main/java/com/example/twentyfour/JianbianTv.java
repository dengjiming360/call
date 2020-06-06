package com.example.twentyfour;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class JianbianTv extends AppCompatTextView {
    public JianbianTv(Context context) {
        super(context);
    }

    public JianbianTv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public JianbianTv(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed,
                            int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            getPaint().setShader(new LinearGradient(
                    0, 0, getWidth(), getHeight(),
                    new int[]{0xFFFF0000, 0xFFFFA500,0xFFFFFF00, 0xFF00FF00,0xFF00FFFF,0xFF0000FF,0xFF800080}, new float[]{0, 0.17f,0.33f,0.49f,0.66f,0.83f,1},
                    Shader.TileMode.CLAMP));
        }
    }
}

