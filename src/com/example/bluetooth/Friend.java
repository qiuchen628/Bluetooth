package com.example.bluetooth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.TreeMap;

import com.example.bluetooth.SplashActivity.BtnListener;


public class Friend extends Activity {

	class BtnListener4 implements OnClickListener{
   	 
		 public void onClick(View v) {
   	 
			 Intent  intent32 = new Intent(); 
			 intent32.setClass(Friend.this, Figure.class); 
			 startActivity(intent32);
      
       }
       } 
	
	
	
	
	DatabaseHandler datasourcefriend = new DatabaseHandler(this);
	int cntfriend=0;
	LinkedList<String> name = new LinkedList<String>();
	HashMap<String,Integer> namemap = new HashMap<String,Integer> ();
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		
		Button btnfigure = (Button)findViewById(R.id.figbutton);
		btnfigure.setOnClickListener(new BtnListener4());
	// get all the friends' information from data base
		List<Contact> frineds = datasourcefriend.getAllContacts(); 
	
	// get each device name	 
       int position=0;
       for (Contact fn : frineds) {
            String logname = fn.getDeviceName();
              name.add(position, logname);
            position++;
           }
     
   // put each device name to name hashmap
	   for(String x:name){
		   Integer c = namemap.get(x);
		   if(c==null){namemap.put(x, 1);}
		   else{namemap.put(x,c+1);}
	   }
	   
	   TextView textView1=(TextView)findViewById(R.id.textView1);
	   TextView textView2=(TextView)findViewById(R.id.textView2);
	  
	   Set keys = namemap.keySet();

	   for (Iterator i = keys.iterator(); i.hasNext();)
	   {
	       String key = (String) i.next();
	       Integer value = (Integer) namemap.get(key);
	       textView1.append(key + " encounter " + value + " times; ");
	   }
	   
	   for (Iterator i = sortByValue(namemap).iterator(); i.hasNext(); ) {
           String key = (String) i.next();
          // System.out.printf("key: %s, value: %s\n", key, namemap.get(key));
           textView2.append(key + " encounter " + namemap.get(key) + " times; ");
       }
	    
    }
   
    
    public static List sortByValue(final HashMap m) {
        List keys = new ArrayList();
        keys.addAll(m.keySet());
        Collections.sort(keys, new Comparator() {
            public int compare(Object o1, Object o2) {
                Object v1 = m.get(o1);
                Object v2 = m.get(o2);
                if (v1 == null) {
                    return (v2 == null) ? 0 : 1;
                }
                else if (v1 instanceof Comparable) {
                    return ((Comparable) v1).compareTo(v2);
                }
                else {
                    return 0;
                }
            }
        });
        return keys;
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friend, menu);
		return true;
	}

    
    
    
    
    
    

}

	

	
	





