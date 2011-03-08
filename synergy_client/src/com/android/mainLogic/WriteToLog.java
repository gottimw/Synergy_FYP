package com.android.mainLogic;

import android.widget.TextView;

public class WriteToLog {
	
	TextView textView;
	
	public WriteToLog(TextView textView){
		this.textView = textView;
	}
	
	public void appendText(CharSequence text){
		
		textView.setText(textView.getText()+""+text+"\n");		
	}
	
	public void writeText(CharSequence text){
		textView.setText(text+"\n");		
	}
}
