package com.drimtim.dimlights;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.drimtim.dimlights.dimlights.R;


public class GameActivity extends AppCompatActivity implements View.OnTouchListener {

    private MenuItem settings;
    //private int stepCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setTitle("Exit");
                dialog.show(fragmentManager, "dialog");
            }
        });
        GameView gameView = (GameView) findViewById(R.id.game_view);
        Field field = new Field(this);
        gameView.setField(field);

    }

    public void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("")
                .setMessage(R.string.win)
                .setCancelable(false)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                GameActivity.this.finish();
                                GameActivity.super.finish();
                            }
                        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_game, menu);
        settings = menu.findItem(R.id.settings);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setTitle("Exit");
        dialog.show(fragmentManager, "dialog");
//        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.equals(settings)) {
            Intent intent = new Intent(this, OptionsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override //TODO Подсчет количества ходов пользователя
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
