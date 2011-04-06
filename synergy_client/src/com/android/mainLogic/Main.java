package com.android.mainLogic;

import com.android.activities.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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
       
    }
}