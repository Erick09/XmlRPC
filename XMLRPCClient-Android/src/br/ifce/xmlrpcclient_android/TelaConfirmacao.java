package br.ifce.xmlrpcclient_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class TelaConfirmacao extends Activity {
	
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		WebView qrImage = new WebView(this);
		
		Intent it = getIntent();
		Bundle b = it.getExtras();
		String tmp = b.getString("myKey");
				
		qrImage.loadUrl(tmp);
		
		setContentView(qrImage);
	}

}
