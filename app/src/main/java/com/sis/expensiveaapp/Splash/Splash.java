package com.sis.expensiveaapp.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.sis.expensiveaapp.Activities.MainActivity;
import com.sis.expensiveaapp.R;

public class Splash extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    TextView slogan;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.sis.expensiveaapp.R.layout.activity_splash);
        //Animations
       slogan=findViewById(R.id.slogan);
        image=findViewById(R.id.logo);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


//Set animation to elements
        image.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);

                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        return;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        thread.start();

    }
}