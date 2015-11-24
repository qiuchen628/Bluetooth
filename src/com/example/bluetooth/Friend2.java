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

public class Friend2 extends  Activity {


	DatabaseHandler datasourcefriend = new DatabaseHandler(this);
	int cntfriend=0;
	
	LinkedList<String> name = new LinkedList<String>();
	LinkedList<String> uniname = new LinkedList<String>();
	LinkedList<String> rawdistance = new LinkedList<String>();
	LinkedList<Double> distance = new LinkedList<Double>();
	HashMap<String,Integer> namemap = new HashMap<String,Integer> ();
	HashMap<String,String> namedissum = new HashMap<String,String> ();
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend2);
	// get all the friends' information from data base
		List<Contact> frineds = datasourcefriend.getAllContacts(); 
	    
	// get each device name	 
      
       for (Contact fn : frineds) {
            String logname = fn.getDeviceName();
              name.add(logname);
           }
       
   	// get rawdistance of each device	 
      
       for (Contact ttn : frineds) 
       {
            String distlog = ttn.getDistance();
            rawdistance.add(distlog);
           }
       
      /*
       for (String tmp:rawdistance) 
       {
           if(tmp.equals(" within ten meter")) {distance.add((double)7.0);}
           else if(tmp.equals(" within five meter")) {distance.add((double)3.1);}
           else if(tmp.equals(" within two meter")) {distance.add((double)1.5);}
           else if(tmp.equals(" within one meter")) {distance.add((double)0.5);}
           else 
           {distance.add((double)11);}
           
       }
    	   */
           
       
       
       // put each device name to name hashmap
     
       int pos2=0;

	   for(int x=0; x<name.size();x++){
		   Integer c = namemap.get(x);
		   if(c==null){namemap.put(name.get(x), 1);
		   uniname.add(pos2,name.get(x)); pos2++;
		   }
		   else{namemap.put(name.get(x),c+1);}
	   }
	   
	   for(int x=0; x<name.size();x++)
	   
	   {
		//   namedissum.put(name.get(x), distance.get(x));
		   
	   }
	   
	   TextView textView11=(TextView)findViewById(R.id.textView11);
	   TextView textView22=(TextView)findViewById(R.id.textView22);
	   TextView textView33=(TextView)findViewById(R.id.textView33);
	   TextView textView44=(TextView)findViewById(R.id.textView44);
	   
	   Set keys = namemap.keySet();

	   for (Iterator i = keys.iterator(); i.hasNext();)
	   {
	       String key = (String) i.next();
	       Integer value = (Integer) namemap.get(key);
	       textView11.append(key + " encounter " + value + " times; ");
	   }
	   
	   for (Iterator i = sortByValue(namemap).iterator(); i.hasNext(); ) {
           String key = (String) i.next();
          // System.out.printf("key: %s, value: %s\n", key, namemap.get(key));
           textView22.append(key + " encounter " + namemap.get(key) + " times; ");
       }
	  
	  
	  /* 
	   for (int i=0; i< uniname.size();i++)
	   {
		   for (int j=0; j< name.size();j++)
		   
		   {
			   if (uniname.get(i)==uniname.get(j))
			   {
				   uninamedis.put(uniname.get(i), "dwfdwedf");
				   
			   }
			   
			   
		   }
		   
		   
	   }
	   
	   for (int i=0; i<rawdistance.size();i++)
	   {
	      
	       textView33.append(rawdistance.get(i) + " encounter");
	   }
	   
	   
	   */
	   
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

	

	
	






	

	