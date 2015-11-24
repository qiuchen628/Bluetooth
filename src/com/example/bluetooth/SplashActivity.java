package com.example.bluetooth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;



import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View.OnClickListener;  

public class SplashActivity extends Activity {

	//public Button FirstButton; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        ImageButton btn = (ImageButton)findViewById(R.id.imageButton1);
        btn.setOnClickListener(new BtnListener());
    }
        
    class BtnListener implements  OnClickListener{
    	 public void onClick(View v) {
    	 
    	  Intent intent = new Intent();
    	  intent.setClass(SplashActivity.this, MainActivity.class);
    	  startActivity(intent);
       
        }
        }
}
        