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
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

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
				
		        SynergyMessageReader msgReader= new SynergyMessageReader();
		
				SynergyMessageCreator messageCreator = new SynergyMessageCreator(MessageType.HANDSHAKE);
				messageCreator.createHandshakeMsg(user, pass);
				String message = messageCreator.getReadyXML_Messsage();
				
				ObjectReceiveControler socket_handler = new ObjectReceiveControler();
				
				socket_handler.sendObject((Object) message );
				
				String responce = (String) socket_handler.receiveObject();
				Boolean loggedIn = msgReader.parseHandshake_Res(responce);
							
				//if login failed quit and inform used of error

				if(!loggedIn){
					Context context = getApplicationContext();
					CharSequence text = "Login to server Failed\nWrong login or password";

					Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
					toast.show();
					socket_handler.closeConnection();
				}
				
				//access resources and send it to server
				getContacts();
				

				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}

		});
		
		
	}

	public void getContacts(){
		
		String name;
		String id;
		Stack<String> phoneStack = new Stack<String>();
		Stack<String> emailStack = new Stack<String>();
		
		String[] contact = new String[5];
		
		ContentResolver contentRes = getContentResolver();
		Cursor cursor = contentRes.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

		if(cursor.getCount() > 0){
			while(cursor.moveToNext()){
				id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					Cursor pCur = contentRes.query(
				 		    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
				 		    null, 
				 		    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
				 		    new String[]{id}, null);
				 	        while (pCur.moveToNext()) {
				 		    // Do something with phones
				 	        } 
				 	        pCur.close();
					
					
				}
			}
		}
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) 
	{

	  if (requestCode == 0)
	  {         
	    getContactInfo(intent);         
	    // Your class variables now have the data, so do something with it. 
	  }
	}//onActivityResult

	protected void getContactInfo(Intent intent)
	{
		String name;
		String contactId;
		Stack<String> phoneStack = new Stack<String>();
		Stack<String> emailStack = new Stack<String>();
		
	   Cursor cursor =  managedQuery(intent.getData(), null, null, null, null);      
	   while (cursor.moveToNext()) 
	   {           
	       contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
	       name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)); 

	       String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

	       if ( hasPhone.equalsIgnoreCase("1"))
	           hasPhone = "true";
	       else
	           hasPhone = "false" ;

	       if (Boolean.parseBoolean(hasPhone)) 
	       {
	        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,null, null);
	        while (phones.moveToNext()) 
	        {
	          String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
	          phoneStack.push(phoneNumber);
	        }
	        phones.close();
	       }

	       // Find Email Addresses
	       Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,null, null);
	       while (emails.moveToNext()) 
	       {
	        String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
	        emailStack.push(emailAddress);
	       }
	       emails.close();
/*
	    Cursor address = getContentResolver().query(
	                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
	                null,
	                ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = " + contactId,
	                null, null);
	    while (address.moveToNext()) 
	    { 
	      // These are all private class variables, don't forget to create them.
	      poBox      = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
	      street     = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
	      city       = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
	      state      = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
	      postalCode = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
	      country    = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
	      type       = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));
	    }  //address.moveToNext()   
	  }  //while (cursor.moveToNext())  
 */      
	   cursor.close();
	  }//getContactInfo

	}
}
