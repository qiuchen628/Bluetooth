package com.example.bluetooth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import com.example.bluetooth.SplashActivity.BtnListener;

import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
 
public class Display extends ListActivity   {
	    
	    DatabaseHandler datasource = new DatabaseHandler(this);
	    
	    int tvStyleName;
	    
	    class BtnListener3 implements  OnClickListener{
	    	 
			 public void onClick(View v) {
	    	 
				 Intent  intent3 = new Intent(); 
				 intent3.setClass(Display.this, Figure.class); 
				 startActivity(intent3);
	       
	        }
	        } 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		
		Button btntofriend = (Button)findViewById(R.id.buttontofriend);
        btntofriend.setOnClickListener(new BtnListener3());
		
		List<Contact> datadisplay = datasource.getAllContacts();
		  
		
		
		ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_1, datadisplay);

	    ListView myList=(ListView)findViewById(android.R.id.list);
	    myList.setAdapter(adapter);
	   
	    List<Contact> contacts = datasource.getAllContacts();       
        
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getDeviceName();
                // Writing Contacts to log
            Log.d("Name: ", log);
    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display, menu);
		return true;
	}
	
	
}
