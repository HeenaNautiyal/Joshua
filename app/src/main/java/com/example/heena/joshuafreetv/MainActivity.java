package com.example.heena.joshuafreetv;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    Button btnok;
    ImageView iv1,iv2,iv3,iv4,iv5,iv6;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnok=(Button)findViewById(R.id.btnok1);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildDialog(R.style.DialogTheme, "Left - Right Animation!");
            }
        });
    }

    private void buildDialog(int animationSource, String type) {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        promptView.getBackground().setAlpha(0);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);
        AlertDialog alert = alertDialogBuilder.create();
        iv1=(ImageView)promptView.findViewById(R.id.i_podcast);
        iv2=(ImageView)promptView.findViewById(R.id.i_quantum);
        iv3=(ImageView)promptView.findViewById(R.id.i_comunity);
        iv4=(ImageView)promptView.findViewById(R.id.i_freehealing);
        iv5=(ImageView)promptView.findViewById(R.id.i_more);
        iv6=(ImageView)promptView.findViewById(R.id.i_home);

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2Fgroups%2FEmotionallyFreeTV"));
                startActivity(browserIntent);
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(MainActivity.this,MenuPage.class);
                startActivity(it);
            }
        });
        alert.getWindow().getAttributes().windowAnimations = animationSource;
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = alert.getWindow().getAttributes();

        wmlp.x = 50;   //x position
        wmlp.y = 50;   //y position

        alert.show();
    }

    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                  if(currentPage==1){
                      Thread timerThread = new Thread(){
                          public void run(){
                              try{
                                  sleep(2000);
                              }catch(InterruptedException e){
                                  e.printStackTrace();
                              }finally{
                                  Intent intent = new Intent(MainActivity.this,HomeScren.class);
                                  startActivity(intent);
                              }
                          }
                      };
                      timerThread.start();
                  }
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}
