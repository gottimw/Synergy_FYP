package com.android.mainLogic;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        main();
    }
      
    public void main(){

    	ObjectReceiveControler orc;
    	TextView logBox = (TextView) findViewById(R.id.LogDump);
    	WriteToLog writeToLog = new WriteToLog(logBox);
    	Object obj = null;
    	
    	writeToLog.writeText("Receive Object Test Start\n--------\n");
    	
    	orc = new ObjectReceiveControler();
    	orc.sendObject("Android says hello");
    	obj = orc.receiveObject();
    	if(obj != null){
    		writeToLog.appendText((CharSequence)obj);
    	}else{
    		writeToLog.appendText("Object NOT Received");
    	}
   	
    }
}