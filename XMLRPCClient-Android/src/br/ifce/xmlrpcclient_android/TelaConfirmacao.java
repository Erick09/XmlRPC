package br.ifce.xmlrpcclient_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaConfirmacao extends Activity {
	
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		TextView view = new TextView(this);
		view.setText("Cadastro Confirmado");
		setContentView(view);
	}

}
