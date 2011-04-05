package com.android.mainLogic;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.android.activities.*;

import XML_handler.MessageType;
import XML_handler.SynergyMessageCreator;
import aaa.Aaa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //grab focus from any editText widgets
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        main();
    }
      
    public void main(){
    	
    	
    	
    	
    	
    	
    	
    	//Buttons handlers    	
    	Button backupButton = (Button) findViewById(R.id.backupButton);
        backupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            
            	EditText userBox =  (EditText)findViewById(R.id.loginInput);
            	EditText passBox =  (EditText)findViewById(R.id.passwordInput);
            	
            	Bundle b = new Bundle();
            	b.putString("user", userBox.getText().toString());
            	b.putString("pass", passBox.getText().toString());
            	
            	Intent myIntent = new Intent(Main.this, Backup_main.class);
            	myIntent.putExtras(b);
            	
            	startActivityForResult(myIntent, 0);
            }
            

        });
        
        Button restoreButton = (Button) findViewById(R.id.restoreButton);
        restoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	EditText userBox =  (EditText)findViewById(R.id.loginInput);
            	EditText passBox =  (EditText)findViewById(R.id.passwordInput);
            	
            	Bundle b = new Bundle();
            	b.putString("keyName", userBox.getText().toString());
            	b.putString("pass", passBox.getText().toString());
            
            	Intent myIntent = new Intent(Main.this, Restore_main.class);
            	myIntent.putExtras(b);
            	
            	startActivity(myIntent);
            }
            

            
        });
        
        Button wipeButton = (Button) findViewById(R.id.wipeButton);
        wipeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	
            }
            

            
        });
        
        Button serviceButton = (Button) findViewById(R.id.serviceStartButton);
        serviceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
           

            }
            

            
        });
/*
        
		
*/
/*
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
 */  	
    }
}