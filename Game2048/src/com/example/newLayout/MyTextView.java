package com.example.newLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView{
	
	private Paint paint;

	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyTextView(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int w=getMeasuredWidth();
		int h=getMeasuredHeight();
		paint=new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0xffbbada0);
		canvas.drawRect(0, 0, w, h, paint);
		super.onDraw(canvas);
		canvas.restore();
	}
	
}
