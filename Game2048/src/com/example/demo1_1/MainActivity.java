package com.example.demo1_1;

import android.support.v7.app.ActionBarActivity;

import android.text.TextUtils;

import com.example.newLayout.MyGridView;
import com.example.service.AddMusicService;
import com.example.service.EndMusicService;
import com.example.service.NotAddMusicService;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private MyGridView game_view;
	private TextView tv_score, tv_best;
	private int score;
	private static MainActivity ma = null;

	public MainActivity() {
		ma = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Init();
	}

	public void Init() {
		game_view = (MyGridView) findViewById(R.id.game_view);
		tv_score = (TextView) findViewById(R.id.tv_score);
		tv_best = (TextView) findViewById(R.id.tv_best);
		SharedPreferences spf = this.getSharedPreferences("score", this.MODE_PRIVATE);
		tv_best.setText("最好:" + spf.getInt("score", 0));
	}

	public void reStart(View view) {
		game_view.startGame();
	}

	public static MainActivity getMainActivity() {
		return ma;
	}

	public void cleanScore() {
		score = 0;
		tv_score.setText("分数:" + score);
	}

	public void addScore(int num) {
		score += num;
		tv_score.setText("分数:" + score);
	}

	public void saveBest() {
		SharedPreferences spf = this.getSharedPreferences("score", this.MODE_PRIVATE);
		Editor e = spf.edit();
		if (spf.getInt("score", 0) < score) {
			e.putInt("score", score);
		}
		e.commit();
		tv_best.setText("最好:" + spf.getInt("score", 0));
	}

	public void playerAddMusic() {
		startService(new Intent(this, AddMusicService.class));
	}

	public void playerNotAddMusic() {
		startService(new Intent(this, NotAddMusicService.class));
	}

	public void playerWinOrDef() {
		startService(new Intent(this, EndMusicService.class));
	}
}
