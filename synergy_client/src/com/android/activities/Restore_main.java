package com.android.activities;

import com.android.mainLogic.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Restore_main extends Activity {

	//Called when the activity is first created
	@Override
	 public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.restore_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
		main();
	}
	
	public void main(){
		
		getAllContacts();
		
		final Button next = (Button) findViewById(R.id.commitRestoreButton);
		next.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view) {
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();
		}

		});
		
		
	}

	public void getAllContacts(){
		
	}

}
