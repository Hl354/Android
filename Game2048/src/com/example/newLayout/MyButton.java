package com.example.newLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends Button {
	
	private Paint paint;

	public MyButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyButton(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int w=getMeasuredWidth();
		int h=getMeasuredHeight();
		paint=new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0xdd8f7a66);
		canvas.drawRect(0, 0, w, h, paint);
		super.onDraw(canvas);
		canvas.restore();
	}
	
}
