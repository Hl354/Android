package com.example.newLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MyCard extends FrameLayout {

	private TextView tv;
	private int num = 0;

	public MyCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public MyCard(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyCard(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyCard(Context context) {
		super(context);
		tv = new TextView(getContext());
		tv.setTextSize(32);
		tv.setGravity(Gravity.CENTER);
		tv.setBackgroundColor(0x33ffffff);
		// -1,-1³äÂúÕû¸öÆÁÄ»
		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(20, 20, 20, 20);
		addView(tv, lp);
		setNum(0);
	}

	public TextView getTv() {
		return tv;
	}

	public void setTv(TextView tv) {
		this.tv = tv;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		if (num <= 0) {
			setNewColor(num);
			tv.setText("");
		} else {
			setNewColor(num);
			tv.setText("" + num);
		}
	}

	public boolean equals(MyCard myCard) {
		return this.getNum() == myCard.getNum();
	}

	public void setNewColor(int num) {
		int color = 0;
		if (num == 2) {
			color = 0xffeee4da;
		} else if (num == 4) {
			color = 0xffede0c8;
		} else if (num == 8) {
			color = 0xfff2b179;
		} else if (num == 16) {
			color = 0xfff59563;
		} else if (num == 32) {
			color = 0xfff67c5f;
		} else if (num == 64) {
			color = 0xfff65e3b;
		} else if (num == 128) {
			color = 0xffedcf72;
		} else if (num == 256) {
			color = 0xffC0FF3E;
		} else if (num == 512) {
			color = 0xffBA55D3;
		} else if (num == 1024) {
			color = 0xff6495ED;
		} else if (num == 2048) {
			color = 0xffFF0033;
		} else {
			color = 0x33ffffff;
		}
		tv.setBackgroundColor(color);
	}
}
