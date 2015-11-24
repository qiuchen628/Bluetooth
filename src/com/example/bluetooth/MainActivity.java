package com.example.bluetooth;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.sql.Timestamp;

 




import com.example.bluetooth.DatabaseHandler;
import com.example.bluetooth.SplashActivity.BtnListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener, SensorEventListener{
	
	//ImageButton SQLiteButton;
	 class BtnListener2 implements  OnClickListener{
    	 
		 public void onClick(View v) {
    	 
			 Intent  intent2 = new Intent(); 
			 intent2.setClass(MainActivity.this, Display.class); 
			 startActivity(intent2);
       
        }
        } 
	 

	
	public ArrayList<String> ArrayDist = new ArrayList<String> ();
	
	DatabaseHandler ins_rssi = new DatabaseHandler(this);
	
	
	
    private SensorManager sensorManager;
	
	TextView xCoor; // declare X axis object
	TextView yCoor; // declare Y axis object
	TextView zCoor; // declare Z axis object
	
     private SensorManager sm;  
 
     private Sensor aSensor;  
     private Sensor mSensor;  
       
     float[] accelerometerValues = new float[3];  
     float[] magneticFieldValues = new float[3];  
   
     private static final String TAG = "sensor";  


	ArrayAdapter<String> listAdapter;
	public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	protected static final int SUCCESS_CONNECT = 0;
	protected static final int MESSAGE_READ = 1;

	
	
	ListView listView;
	BluetoothAdapter btAdapter;
	Set<BluetoothDevice> devicesArray;
	ArrayList<String> pairedDevices;
	ArrayList<BluetoothDevice> devices;
	IntentFilter filter;
	BroadcastReceiver receiver;
	String tag ="debugging";
	
	
	String fileName = "dewdf";

	String message = "deswafrweqfgwq";
	    

		

	


	
	Handler mHandler = new  Handler(){
		
	public void handlerMessage(Message msg){
		
		super.handleMessage(msg);
		switch(msg.what){
		
		case SUCCESS_CONNECT:
			ConnectedThread connectedThread = new ConnectedThread((BluetoothSocket)msg.obj);
			Toast.makeText(getApplicationContext(), "CONNECT", 0).show();
			String s ="successfully connected";
			connectedThread.write(s.getBytes());
			break;
			
		case MESSAGE_READ:
			
			byte[] readBuf =(byte[])msg.obj;
			String string = new String(readBuf);
			Toast.makeText(getApplicationContext(), "CONNECT", 0).show();
			
		break;
	}
	}
	};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
	

		 
			
	//	ImageButton SQLiteButton = (ImageButton) findViewById(R.id.SQLButton);
	//     SQLiteButton.setOnClickListener(new BtnListener2());
		 init();

		Button displayButton = (Button)findViewById(R.id.displayButton);
		displayButton.setOnClickListener(new BtnListener2());
 		
 		
		if (btAdapter==null)
		{Toast.makeText(getApplicationContext(), "No Bluetooth Detected", 0);
		finish();}
		else{
			
			if(!btAdapter.isEnabled()){
				turnOnBT();
			}
			
			getPairedDevices();
			startDiscovery();
			
		}
	
		xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
		zCoor=(TextView)findViewById(R.id.zcoor); // create Z axis object
		

        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);  
        aSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  
        mSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);  
  
        sm.registerListener(myListener, aSensor, SensorManager.SENSOR_DELAY_NORMAL);  
        sm.registerListener(myListener, mSensor,SensorManager.SENSOR_DELAY_NORMAL);  
       
        calculateOrientation();  
   //     writeFileData(fileName, message);

		
	}
			
			
public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	




private  void calculateOrientation() {  
	
         float[] values = new float[3];  
         float[] R = new float[9];  
         SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticFieldValues);           
         SensorManager.getOrientation(R, values);  
  
          values[0] = (float) Math.toDegrees(values[0]); 
          float xaxis= values[0];
          xCoor.setText("X: "+xaxis);
           
          values[1] = (float) Math.toDegrees(values[1]);  
          float yaxis= values[1];
          yCoor.setText("Y: "+yaxis);
          
          values[2] = (float) Math.toDegrees(values[2]);  
          float zaxis= values[2];
          zCoor.setText("Z: "+zaxis);
          
         
         } 

	public void onSensorChanged(SensorEvent event){
		
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
			
			// assign directions
			float x=event.values[0];
			float y=event.values[1];
			float z=event.values[2];
			
		//	xCoor.setText("X: "+x);
			yCoor.setText("Y: "+y);
			zCoor.setText("Z: "+z);
		} 
	}

	
	final SensorEventListener myListener = new SensorEventListener() {  
		     public void onSensorChanged(SensorEvent sensorEvent) {  
		           
		     if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)  
		     magneticFieldValues = sensorEvent.values;  
		    if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)  
		         accelerometerValues = sensorEvent.values;  
		     calculateOrientation();  
		     }  
		     public void onAccuracyChanged(Sensor sensor, int accuracy) {}  
		     };  


	private void startDiscovery() {
		// TODO Auto-generated method stub
		btAdapter.cancelDiscovery();
		btAdapter.startDiscovery();
		
	}

	private void turnOnBT() {
		
		Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		startActivityForResult(intent,1);
	
	}

	private void getPairedDevices() {
		
		devicesArray = btAdapter.getBondedDevices();
		
		if (devicesArray.size()>0){
			for(BluetoothDevice device:devicesArray){
				pairedDevices.add(device.getName());
			}
		}
		
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void init()
	{  
		
		listView=(ListView)findViewById(R.id.listView);
		listView.setOnItemClickListener(this);
		listAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,0);
		listView.setAdapter(listAdapter);
		btAdapter = BluetoothAdapter.getDefaultAdapter();
		pairedDevices = new ArrayList<String> ();
		filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		devices = new ArrayList<BluetoothDevice>();
		receiver = new BroadcastReceiver ()
		{

			@Override
			public void onReceive(Context context, Intent intentReceive) {
				
				String action =intentReceive.getAction();
				
				if (BluetoothDevice.ACTION_FOUND.equals(action)){
					
					BluetoothDevice device= intentReceive.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					devices.add(device);
					String s =" ";
					

					for(int a=0; a<pairedDevices.size();a++)
					{
						if(device.getName().equals(pairedDevices.get(a))){
							s="(Paired)";
							break;
							
						}
						
					}
				
				listAdapter.add(device.getName()+" "+s +" "+ "\n" +device.getAddress());
				
				
                int rssi = intentReceive.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                
             
	            //Use the hash table to store the mapping relation between RSSI and real distanc in indoor room
	            
	                Hashtable<Integer,String> numbers= new Hashtable<Integer,String>();
	            
	                numbers.put(-70, " within five meter");numbers.put(-71, " within five meter");numbers.put(-72, " within five meter");numbers.put(-73, " within five meter");
	                numbers.put(-74, " within five meter");numbers.put(-75, " within five meter");numbers.put(-76, " within five meter");numbers.put(-77, " within five meter");
	                numbers.put(-78, " within five meter");numbers.put(-79, " within five meter");numbers.put(-80, " within five meter");numbers.put(-81, " within ten meter");
	                numbers.put(-82, " within ten meter");numbers.put(-83, " within ten meter");numbers.put(-84, " within ten meter");numbers.put(-85, " within ten meter");
            	    numbers.put(-69, " within five meter");numbers.put(-68, " within five meter");numbers.put(-67, " within two meter");numbers.put(-66, " within two meter");
            	    numbers.put(-65, " within two meter");numbers.put(-64, " within two meter");numbers.put(-63, " within two meter");numbers.put(-62, " within two meter");
            	    numbers.put(-61, " within two meter");numbers.put(-60, " within two meter");numbers.put(-59, " within two meter");numbers.put(-58, " within two meter");
            	    numbers.put(-57, " within two meter");numbers.put(-56, " within two meter");numbers.put(-55, " within two meter");numbers.put(-54, " within two meter");
            	    numbers.put(-53, " within two meter");numbers.put(-52, " within two meter");numbers.put(-51, " within two meter");numbers.put(-50, " within two meter");  
            	    numbers.put(-49, " within one meter");numbers.put(-48, " within one meter"); numbers.put(-47, " within one meter");
            	    numbers.put(-46, " within one meter"); numbers.put(-45, " within one meter"); numbers.put(-44, " within one meter"); 
            	    numbers.put(-43, " within one meter"); numbers.put(-42, " within one meter"); numbers.put(-41, " within one meter"); 
            	    numbers.put(-40, " within one meter"); numbers.put(-39, " within one meter"); numbers.put(-38, " within one meter");
            	    numbers.put(-37, " within one meter");numbers.put(-36, " within one meter"); numbers.put(-35, " within one meter");
            	    numbers.put(-34, " within one meter"); numbers.put(-33, " within one meter"); numbers.put(-32, " within one meter"); 
            	    numbers.put(-31, " within one meter"); numbers.put(-30, " within one meter"); numbers.put(-29, " within one meter"); 
            	    numbers.put(-28, " within one meter"); numbers.put(-27, " within one meter"); numbers.put(-26, " within one meter");
	            
	            
	            String distan = numbers.get(rssi);
	             
	            
	            ArrayDist.add(device.getName());
	            
	            Timestamp d = new Timestamp(System.currentTimeMillis()); 
	            String time= d.toString();
	            
	            String strRSSI= Integer.toString(rssi);
	            
	            
	            for(int i=0;i<ArrayDist.size();i++){
	            ins_rssi.addContact(new Contact(ArrayDist.get(i), strRSSI, distan, time));
	               } 
	           
	             
	          /*   
	             Toast toast2=Toast.makeText(getApplicationContext(), "The device is: "+device.getName()+". RSSI: "+ rssi + "dBm"+". Distance is"+distan, Toast.LENGTH_SHORT); 
	             
	             
	             
	             toast2.setGravity(Gravity.CENTER, 0, 0); 
	             
	             ImageView imageView= new ImageView(getApplicationContext()); 
	             
	             imageView.setImageResource(R.drawable.detect); 
	          
	             LinearLayout toastView = (LinearLayout) toast2.getView(); 
	         
	             toastView.setOrientation(LinearLayout.HORIZONTAL); 
	          
	             toastView.addView(imageView, 0); 
	             toast2.show();
	            */
	             
	            
				}
	
				
				else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
					
					Toast.makeText(getApplicationContext(),"start discover", Toast.LENGTH_SHORT).show();
				}
				
				else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
					Toast.makeText(getApplicationContext(),"Discover Finish", Toast.LENGTH_SHORT).show();
				}
				
				else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)){
					//run some codes
					
	               if (btAdapter.getState()==btAdapter.STATE_OFF){
	            	   turnOnBT();
	               }				
				}
				
			}
			
		};
	
		
		registerReceiver(receiver,filter);
		 filter =new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		registerReceiver(receiver,filter);
		 filter =new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(receiver,filter);
		 filter =new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
		 
        registerReceiver(receiver,filter);
        
        
       
        
       
        
	}
	
	
	protected void onPause() {
		super.onPause();
		sm.unregisterListener(myListener); 
	}
	
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode==RESULT_CANCELED)
		{
			Toast.makeText(getApplicationContext(), "Bluetooth Must be enable to continue", Toast.LENGTH_SHORT).show();
			finish();	
		}
		
	}

	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		if(btAdapter.isDiscovering())
		{
			btAdapter.cancelDiscovery();
		}
		
		if(listAdapter.getItem(arg2).contains("Paired")){
			BluetoothDevice selectedDevice = devices.get(arg2);
			ConnectThread connect = new ConnectThread(selectedDevice);
			connect.start();
			
		}
		
		else{
		
			Toast.makeText(getApplicationContext(),"device is not paried", 0).show();
		}
	}
	
	
	private class ConnectThread extends Thread {
	  
		private final BluetoothSocket mmSocket;
	    private final BluetoothDevice mmDevice;
	 
	    public ConnectThread(BluetoothDevice device) {
	    	
	        BluetoothSocket tmp = null;
	        mmDevice = device;
	 
	   
	        try {
	            // MY_UUID is the app's UUID string, also used by the server code
	            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
	        } catch (IOException e) { }
	        mmSocket = tmp;
	    }
	 
	    public void run() {
	        // Cancel discovery because it will slow down the connection
	        btAdapter.cancelDiscovery();
	        Log.i(tag, "connect - run");
	        try {
	            // Connect the device through the socket. This will block
	            // until it succeeds or throws an exception
	            mmSocket.connect();
	        } catch (IOException connectException) {
	            // Unable to connect; close the socket and get out
	            try {
	                mmSocket.close();
	            } catch (IOException closeException) { }
	            return;
	        }
	 
	        // Do work to manage the connection (in a separate thread)
	       
	        mHandler.obtainMessage(SUCCESS_CONNECT, mmSocket).sendToTarget();
	        
	    }
	 
		/** Will cancel an in-progress connection, and close the socket */
	    public void cancel() {
	        try {
	            mmSocket.close();
	        } catch (IOException e) { }
	    }
	}
	
	
	
	private class ConnectedThread extends Thread {
	    private final BluetoothSocket mmSocket;
	    private final InputStream mmInStream;
	    private final OutputStream mmOutStream;
	 
	    public ConnectedThread(BluetoothSocket socket) {
	        mmSocket = socket;
	        InputStream tmpIn = null;
	        OutputStream tmpOut = null;
	 
	        // Get the input and output streams, using temp objects because
	        // member streams are final
	        try {
	            tmpIn = socket.getInputStream();
	            tmpOut = socket.getOutputStream();
	        } catch (IOException e) { }
	 
	        mmInStream = tmpIn;
	        mmOutStream = tmpOut;
	    }
	 
	    public void run() {
	        byte[] buffer;  // buffer store for the stream
	        int bytes; // bytes returned from read()
	 
	        // Keep listening to the InputStream until an exception occurs
	        while (true) {
	            try {
	                // Read from the InputStream
	            	buffer = new byte[1024];
	                bytes = mmInStream.read(buffer);
	                // Send the obtained bytes to the UI activity
	                mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
	                        .sendToTarget();
	            } catch (IOException e) {
	                break;
	            }
	        }
	    }
	 
	    /* Call this from the main activity to send data to the remote device */
	    public void write(byte[] bytes) {
	        try {
	            mmOutStream.write(bytes);
	        } catch (IOException e) { }
	    }
	 
	    /* Call this from the main activity to shutdown the connection */
	    public void cancel() {
	        try {
	            mmSocket.close();
	        } catch (IOException e) { }
	    }
	}
	

}
