package com.android.activities;

import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import nu.xom.Document;

import com.android.mainLogic.*;
import XML_handler.*;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Backup_main extends Activity {

	//Called when the activity is first created
	@Override
	 public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.backup_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		main();
	}
	
	public void main(){
		
		String user ="empty";
        String pass ="empty";
		
		Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
        	user = extras.getString("user");
        	pass = extras.getString("pass");
        }
		
		//access resources and send it to server
		final Stack<String[]> contacts = getContacts();
		Stack<String[]> contacts2 = (Stack<String[]>) contacts.clone();
		int i = 0;
		
		while(! contacts2.empty()){
			//draw oout all contacts
			drawContactBox(contacts2.pop(), i);
			i++;
		}
        
        SynergyMessageReader msgReader= new SynergyMessageReader();

		SynergyMessageCreator messageCreator = new SynergyMessageCreator(MessageType.HANDSHAKE);
		messageCreator.createHandshakeMsg(user, pass);
		String message = messageCreator.getReadyXML_Messsage();
		
		final ObjectReceiveControler socket_handler = new ObjectReceiveControler();
		
		socket_handler.sendObject((Object) message );
		
		String responce = (String) socket_handler.receiveObject();
		Boolean loggedIn = msgReader.parseHandshake_Res(responce);
					
		//if login failed quit and inform used of error

		if(!loggedIn){
			Context context = getApplicationContext();
			CharSequence text = "Login to server Failed\nWrong login or password";

			Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
			toast.show();
			//socket_handler.closeConnection();
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		}
		
		
		//create xml message with selected contacts
		//final String messageBackup = createBackupContactsXML(contacts);	
			
		//button handling
		Button next = (Button) findViewById(R.id.backupConfirmation);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
	
				 Stack<String[]> contactsStack = (Stack<String[]>) contacts.clone();
				 Stack<String[]> contactsForBackup = new Stack<String[]>();
				
				 //select contacts that has a tick box checked
				 LinearLayout ll = (LinearLayout) findViewById(R.id.contactsScrolableBox);	 
				 for(int i=0; i< ll.getChildCount(); i++ ){
					 
					 CheckBox cb = (CheckBox) ll.getChildAt(i);
					 
					 if(cb.isChecked()){ 
						 contactsForBackup.push(contactsStack.pop());
					 }else{
						 contactsStack.pop();
					 }
					 
				 }
				
				String msg = createBackupContactsXML(contactsForBackup);
				 				
				socket_handler.sendObject((Object) msg );
				
			
				
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}

		});
		
		Button all = (Button) findViewById(R.id.selectAll);
		all.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				LinearLayout ll = (LinearLayout) findViewById(R.id.contactsScrolableBox);
				
				for(int i=0 ; i < ll.getChildCount(); i++){
					( (CheckBox) ll.getChildAt(i)).setChecked(true);
				}
			
			}

		});
		
		Button none = (Button) findViewById(R.id.selectNone);
		none.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				LinearLayout ll = (LinearLayout) findViewById(R.id.contactsScrolableBox);
				
				for(int i=0 ; i < ll.getChildCount(); i++){
					( (CheckBox) ll.getChildAt(i)).setChecked(false);
				}
			
			}

		});
		
	}

	public String createBackupContactsXML(Stack<String[]> contacts){
		
		SynergyMessageCreator msgCreator = new SynergyMessageCreator(MessageType.BACKUP_CONTACTS);
		msgCreator.createBackupContactsMsg(contacts);
		return msgCreator.getReadyXML_Messsage();
	}
	
	
	public Stack<String[]> getContacts(){
		
		String name;
		String id;
		String email;
		String[] contact = new String[3];
		
		Stack<String> phoneStack = new Stack<String>();
		Stack<String[]> contactsStack = new Stack<String[]>();
		
		ContentResolver contentRes = getContentResolver();
		Cursor cursor = contentRes.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

		Boolean phoneFound;
		
		if(cursor.getCount() > 0){
			while(cursor.moveToNext()){
				
				phoneFound = false;
				
				id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				
				contact[0] = name;
				
				if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					Cursor pCur = contentRes.query(
				 		    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
				 		    null, 
				 		    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
				 		    new String[]{id}, null);
					while (pCur.moveToNext()) {
						String phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						contact[1] = phoneNumber;
						phoneFound = true;
					} 
					pCur.close();
								
					Cursor emailCur = contentRes.query( 
							ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
							null,
							ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", 
							new String[]{id}, null); 
					while (emailCur.moveToNext()) { 
					    email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
					    contact[2] = email;
					    break;
					} 
					emailCur.close();
					

					contactsStack.push(contact);
					
					contact = new String[3];
				
				}
			}
		}
		
		return contactsStack;
		
	}
	
	public void drawContactBox(String[] cDetails, Integer id){
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.contactsScrolableBox);
		
		CheckBox cb = new CheckBox(this);
		cb.setText(cDetails[0]);
		cb.setId(id);
		cb.setChecked(true);
		ll.addView(cb);
		
	}
}
