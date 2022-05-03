package com.asimolpiq.catchthegilfoyle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int skor = 0;
    TextView skorText,sayacText;
    ImageView imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    MediaPlayer player;
    MediaPlayer soundtrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skorText = findViewById(R.id.skor);
        sayacText = findViewById(R.id.sayac);
        imageView =findViewById(R.id.imageView);
        imageView1 =findViewById(R.id.imageView1);
        imageView2 =findViewById(R.id.imageView2);
        imageView3 =findViewById(R.id.imageView3);
        imageView4 =findViewById(R.id.imageView4);
        imageView5 =findViewById(R.id.imageView5);
        imageView6 =findViewById(R.id.imageView6);
        imageView7 =findViewById(R.id.imageView7);
        imageView8 =findViewById(R.id.imageView8);
        imageView9 =findViewById(R.id.imageView9);
        imageView10 =findViewById(R.id.imageView10);
        imageView11 =findViewById(R.id.imageView11);
        imageView12 =findViewById(R.id.imageView12);
        imageView13 =findViewById(R.id.imageView13);
        imageView14 =findViewById(R.id.imageView14);
        imageView15 =findViewById(R.id.imageView15);
        imageArray = new ImageView[] {imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15};
        player = MediaPlayer.create(MainActivity.this,R.raw.fyou);
        soundtrack = MediaPlayer.create(MainActivity.this,R.raw.soundtrack);
        soundtrack.setVolume((float)0.05,(float)0.05);
        soundtrack.start();
        hideImage();


        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
               sayacText.setText("Left time: "+(millisUntilFinished/1000)+"sn");
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                for(ImageView a: imageArray){
                    a.setVisibility(View.INVISIBLE);
                }
                soundtrack.stop();
                player.start();
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                if(skor==0){
                    alert.setTitle("Fuck you Dinesh!");
                }
                else{
                    alert.setTitle("You catch me Dinesh!");
                }
                alert.setMessage("Your score: "+skor+"\n"+"Try again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        player.start();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                alert.setCancelable(false);
                alert.show();
            }
        }.start();




    }
    public void skorArtir(View view){
        skor++;
        skorText.setText("Skor: "+String.valueOf(skor));
    }
    public void hideImage(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(15);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,400);
            }
        };
        handler.post(runnable);

    }
}