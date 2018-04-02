package com.example.newLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.demo1_1.MainActivity;
import com.example.demo1_1.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyGridView extends GridLayout {

	private MyCard[][] mycards;
	private List<Point> list;
	private boolean flag = false;

	public MyGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initGame();
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initGame();
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGame();
	}

	public MyGridView(Context context) {
		super(context);
		initGame();
	}

	public void initGame() {

		mycards = new MyCard[4][4];
		list = new ArrayList<Point>();
		setColumnCount(4);

		setOnTouchListener(new OnTouchListener() {

			private float startX, startY, offestX, offestY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					Win();
					Defeat();
					offestX = event.getX() - startX;
					offestY = event.getY() - startY;
					if (Math.abs(offestX) > Math.abs(offestY)) {
						if (offestX < -5) {
							swipeLeft();
						} else if (offestX > 5) {
							swipeRight();
						}
					} else {
						if (offestY < -5) {
							swipeUp();
						} else if (offestY > 5) {
							swipeDown();
						}
					}
					break;
				}
				return true;
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		int cardWidth = (Math.min(w, h)) / 4;
		addCard(cardWidth, cardWidth);
		startGame();
	}

	// 添加卡片
	public void addCard(int cardWidth, int cardHeight) {
		MyCard c;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new MyCard(getContext());
				c.setNum(0);
				addView(c, cardWidth, cardHeight);
				mycards[x][y] = c;
			}
		}
	}

	// 添加两个随机初始位置
	public void addRandomNum() {
		list.clear();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (mycards[x][y].getNum() <= 0) {
					list.add(new Point(x, y));
				}
			}
		}
		int number = new Random().nextInt(list.size());
		Point p = list.remove(number);
		mycards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	// 开始游戏
	public void startGame() {
		MainActivity.getMainActivity().saveBest();
		MainActivity.getMainActivity().cleanScore();
		;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				mycards[x][y].setNum(0);
			}
		}
		addRandomNum();
		addRandomNum();
	}

	public void swipeLeft() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 3; x++) {
				for (int x1 = x + 1; x1 < 4; x1++) {
					if (mycards[x1][y].getNum() > 0) {
						if (mycards[x][y].getNum() <= 0) {
							mycards[x][y].setNum(mycards[x1][y].getNum());
							mycards[x1][y].setNum(0);
							x--;
							flag = true;
							break;
						} else if (mycards[x][y].equals(mycards[x1][y])) {
							mycards[x][y].setNum(mycards[x][y].getNum() * 2);
							mycards[x1][y].setNum(0);
							flag = true;
							MainActivity.getMainActivity().addScore(mycards[x][y].getNum());
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		addNewPoint();
	}

	public void swipeRight() {
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x > 0; x--) {
				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (mycards[x1][y].getNum() > 0) {
						if (mycards[x][y].getNum() <= 0) {
							mycards[x][y].setNum(mycards[x1][y].getNum());
							mycards[x1][y].setNum(0);
							x++;
							flag = true;
							break;
						} else if (mycards[x][y].equals(mycards[x1][y])) {
							mycards[x][y].setNum(mycards[x][y].getNum() * 2);
							mycards[x1][y].setNum(0);
							flag = true;
							MainActivity.getMainActivity().addScore(mycards[x][y].getNum());
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		addNewPoint();
	}

	public void swipeUp() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 3; y++) {
				for (int y1 = y + 1; y1 < 4; y1++) {
					if (mycards[x][y1].getNum() > 0) {
						if (mycards[x][y].getNum() <= 0) {
							mycards[x][y].setNum(mycards[x][y1].getNum());
							mycards[x][y1].setNum(0);
							y--;
							flag = true;
							break;
						} else if (mycards[x][y].equals(mycards[x][y1])) {
							mycards[x][y].setNum(mycards[x][y].getNum() * 2);
							mycards[x][y1].setNum(0);
							flag = true;
							MainActivity.getMainActivity().addScore(mycards[x][y].getNum());
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		addNewPoint();
	}

	public void swipeDown() {
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y > 0; y--) {
				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (mycards[x][y1].getNum() > 0) {
						if (mycards[x][y].getNum() <= 0) {
							mycards[x][y].setNum(mycards[x][y1].getNum());
							mycards[x][y1].setNum(0);
							y++;
							flag = true;
							break;
						} else if (mycards[x][y].equals(mycards[x][y1])) {
							mycards[x][y].setNum(mycards[x][y].getNum() * 2);
							mycards[x][y1].setNum(0);
							flag = true;
							MainActivity.getMainActivity().addScore(mycards[x][y].getNum());
							break;
						} else {
							break;
						}
					}
				}
			}
		}
		addNewPoint();
	}

	public void addNewPoint() {
		if (flag == true) {
			addRandomNum();
			MainActivity.getMainActivity().playerAddMusic();
			flag = false;
		} else {
			MainActivity.getMainActivity().playerNotAddMusic();
		}
	}

	public void Win() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (mycards[x][y].getNum() == 2048) {
					showWin();
					return;
				}
			}
		}
	}

	public void Defeat() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (x == 3) {
					if (mycards[x][y].getNum() == mycards[x][y + 1].getNum() && y != 3) {
						return;
					}
				} else if (y == 3) {
					if (mycards[x][y].getNum() == mycards[x + 1][y].getNum() && x != 3) {
						return;
					}
				} else {
					if (mycards[x][y].getNum() == mycards[x + 1][y].getNum()
							|| mycards[x][y].getNum() == mycards[x][y + 1].getNum()) {
						return;
					}
				}
			}
		}
		showDefeat();
	}

	public void showWin() {
		Toast toast = Toast.makeText(getContext(), "", 1);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		toastView.setBackgroundResource(R.drawable.win);
		toast.show();
		MainActivity.getMainActivity().saveBest();
		MainActivity.getMainActivity().playerWinOrDef();
	}

	public void showDefeat() {
		Toast toast = Toast.makeText(getContext(), "", 1);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		toastView.setBackgroundResource(R.drawable.defaet);
		toast.show();
		MainActivity.getMainActivity().saveBest();
		MainActivity.getMainActivity().playerWinOrDef();
	}

}
