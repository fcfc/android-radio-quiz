package com.ice.quiz2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ice on 5/10/16.
 */

public class Chooser extends Activity {

    protected Dialog mSplashDialog;
    Button technicianButton;
    Button generalButton;
    Button extraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.chooser);
        // ------------------- SPLASH SCREEN CODE


        showSplashScreen();
        setContentView(R.layout.chooser);

        technicianButton = (Button) findViewById(R.id.button);
        technicianButton.setOnClickListener(myhandler);

        generalButton = (Button) findViewById(R.id.button2);
        generalButton.setOnClickListener(myhandler2);

        extraButton = (Button) findViewById(R.id.button3);
        extraButton.setOnClickListener(myhandler3);

    }

    View.OnClickListener myhandler = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Chooser.this, Quiz2.class);
            intent.putExtra("dataset", "xml/technicianClass.xml");
            startActivity(intent);
        }
    };

    View.OnClickListener myhandler2 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Chooser.this, Quiz2.class);
            intent.putExtra("dataset", "xml/generalClass.xml");
            startActivity(intent);
        }
    };

    View.OnClickListener myhandler3 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Chooser.this, Quiz2.class);
            intent.putExtra("dataset", "xml/extraclass.xml");
            startActivity(intent);
        }
    };


    protected void showSplashScreen() {
        mSplashDialog = new Dialog(this, R.style.SplashScreen);
        mSplashDialog.setContentView(R.layout.splash);
        mSplashDialog.setCancelable(false);
        mSplashDialog.show();

        // Set Runnable to remove splash screen just in case
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeSplashScreen();
            }
        }, 1000);
    }

    protected void removeSplashScreen() {
        if (mSplashDialog != null) {
            mSplashDialog.dismiss();
            mSplashDialog = null;
        }
    }
}
