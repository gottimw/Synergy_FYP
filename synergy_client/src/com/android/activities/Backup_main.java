package com.android.activities;

import com.android.mainLogic.*;
import XML_handler.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Backup_main extends Activity {

	//Called when the activity is first created
	@Override
	 public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.backup_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        String user ="empty";
        String pass ="empty";
        
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
        	user = extras.getString("user");
        	pass = extras.getString("pass");
        }
        
		main(user, pass);
	}
	
	public void main(String user, String pass){
		
		
		
		
		
		
		
		//button handling
		Button next = (Button) findViewById(R.id.backupConfirmation);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				String user ="empty";
		        String pass ="empty";
		        
		        Bundle extras = getIntent().getExtras();
		        if(extras !=null)
		        {
		        	user = extras.getString("user");
		        	pass = extras.getString("pass");
		        }
				
				
				SynergyMessageCreator messageCreator = new SynergyMessageCreator(MessageType.HANDSHAKE);
				messageCreator.createHandshakeMsg(user, pass);
				String message = messageCreator.getReadyXML_Messsage();
				
				ObjectReceiveControler socket_handler = new ObjectReceiveControler();
				
				socket_handler.sendObject((Object) message );
				
				String recponce = (String) socket_handler.receiveObject();
				SynergyMessageReader messageReader = new SynergyMessageReader();
				
				
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}

		});
		
		
	}

	public void getContacts(){
		
	}

}
