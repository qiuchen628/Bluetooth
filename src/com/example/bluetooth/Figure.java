package com.example.bluetooth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.*;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Figure extends Activity {

	private GraphicalView mChart;


	
	
	LinkedList<String> dName = new LinkedList<String>();
	LinkedList<Integer> dValue = new LinkedList<Integer>();
	
	DatabaseHandler datasourcefriend2 = new DatabaseHandler(this);
	int cntfriend=0;
	LinkedList<String> named = new LinkedList<String>();
	HashMap<String,Integer> namemapd = new HashMap<String,Integer> ();
	
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_figure);
		
		TextView textViewtest=(TextView)findViewById(R.id.textViewtest);
		List<Contact> frineds2 = datasourcefriend2.getAllContacts(); 
		
		// get each device name	 
	       int position2=0;
	       for (Contact fn : frineds2) {
	            String logname2 = fn.getDeviceName();
	              named.add(position2, logname2);
	            position2++;
	           }
	     
	   // put each device name to name hashmap
		   for(String x:named){
			   Integer c = namemapd.get(x);
			   if(c==null){namemapd.put(x, 1);}
			   else{namemapd.put(x,c+1);}
		   }
		   
		  
		   Set keys = namemapd.keySet();

		   for (Iterator i = keys.iterator(); i.hasNext();)
		   {
		       String key = (String) i.next();
		       Integer value = (Integer) namemapd.get(key);
		  
		   }
		 
		   for (Iterator i = sortByValue(namemapd).iterator(); i.hasNext(); ) {
	           String key = (String) i.next();
	           dName.add(key);
	           dValue.add(namemapd.get(key));
	       }
		  
		   //
		   
		   
		 
		   
		   for (Iterator i = sortByValue(namemapd).iterator(); i.hasNext(); ) {
	           String key = (String) i.next();
	          // System.out.printf("key: %s, value: %s\n", key, namemap.get(key));
	           textViewtest.append(key + " encounter " + namemapd.get(key) + " times; ");
	       }
		   
		   //
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   int dNamesize = dName.size();
		   int [] timesvalue= new int [dName.size()];
		   
		   for(int i=0; i<dValue.size(); i++){
			   timesvalue[i]=dValue.get(i);
		   }
		   
		   
		   
		   String [] tnn= new String [dName.size()];
		   for (int i=0; i<dName.size();i++)
			  {
				  tnn[i]=dName.get(i);
			  }

		   
		    final String[] mMonth = tnn.clone();
		    int [] f = new int [dName.size()];
			
		    for (int i=0; i<dName.size();i++)
			  {
				  f[i]=i;
			  }
		     
		     
		     int x[] = f.clone();
		     
		     int z[] = timesvalue.clone();
		      
		     
			  
		     

		      // Create XY Series for X Series.
		     XYSeries xSeries=new XYSeries("X Series");
		     

		     //  Adding data to the X Series.
		     for(int i=0;i<z.length;i++)
		     {
		     xSeries.add(x[i],z[i]);
		
		     }

		        // Create a Dataset to hold the XSeries.
		     
		     XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
		     
		      // Add X series to the Dataset.   
		     dataset.addSeries(xSeries);
		     
		     
		      // Create XYSeriesRenderer to customize XSeries

		     XYSeriesRenderer Xrenderer=new XYSeriesRenderer();
		     Xrenderer.setColor(Color.BLUE);
		     Xrenderer.setPointStyle(PointStyle.TRIANGLE);
		     Xrenderer.setDisplayChartValues(true);
		     Xrenderer.setLineWidth(2);
		     Xrenderer.setFillPoints(true);
		     
		     // Create XYMultipleSeriesRenderer to customize the whole chart

		     XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();
		     
		     mRenderer.setChartTitle("Device Name vs Times of Encounting");
		     mRenderer.setXTitle("Device Name");
		     mRenderer.setYTitle("Times of Encounting");
		     mRenderer.setZoomButtonsVisible(true);
		     mRenderer.setXLabels(0);
		     mRenderer.setPanEnabled(true);
		   
		     mRenderer.setShowGrid(true);
		 
		     mRenderer.setClickEnabled(true);
		     
		     for(int i=0;i<z.length;i++)
		     {
		      mRenderer.addXTextLabel(i, mMonth[i]);
		     }
		     
		       // Adding the XSeriesRenderer to the MultipleRenderer. 
		     mRenderer.addSeriesRenderer(Xrenderer);
		  
		     
		     LinearLayout chart_container=(LinearLayout)findViewById(R.id.Chart_layout);

		   // Creating an intent to plot line chart using dataset and multipleRenderer
		     
		     mChart=(GraphicalView)ChartFactory.getBarChartView(getBaseContext(), dataset, mRenderer, null);
		     
		     //  Adding click event to the Line Chart.

		     mChart.setOnClickListener(new View.OnClickListener() {
		   
		   @Override
		   public void onClick(View arg0) {
		    // TODO Auto-generated method stub
		    
		    SeriesSelection series_selection=mChart.getCurrentSeriesAndPoint();
		    
		    if(series_selection!=null)
		    {
		     int series_index=series_selection.getSeriesIndex();
		     
		     String select_series="X Series";
		     if(series_index==0)
		     {
		      select_series="X Series";
		     }else
		     {
		      select_series="Y Series";
		     }
		     
		     String month=mMonth[(int)series_selection.getXValue()];
		     
		     int amount=(int)series_selection.getValue();
		     
		     Toast.makeText(getBaseContext(), select_series+"in" + month+":"+amount, Toast.LENGTH_LONG).show();
		    }
		   }
		  });
		     
		// Add the graphical view mChart object into the Linear layout .
		     chart_container.addView(mChart);
		   
		    
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
		getMenuInflater().inflate(R.menu.figure, menu);
		return true;
	}
	
	 

}

	

	


