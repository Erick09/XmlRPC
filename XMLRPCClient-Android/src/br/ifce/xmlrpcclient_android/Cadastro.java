package br.ifce.xmlrpcclient_android;

import java.net.URI;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.HttpHostConnectException;
import org.xmlrpc.android.XMLRPCClient;
import org.xmlrpc.android.XMLRPCException;
import org.xmlrpc.android.XMLRPCFault;
import org.xmlrpc.android.XMLRPCSerializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ListView;
import android.widget.TextView;


/**
 * Demonstrates use of XMLRPC library.
 * 
 * What you really need to do is:
 * <ul>
 *  <li>create XMLRPCClient client
 *  <pre>XMLRPCClient client = new XMLRPCClient(URI.create("http://10.0.2.2:8888"));</pre>
 *  or even
 *  <pre>XMLRPCClient client = new XMLRPCClient("http://10.0.2.2:8888");</pre>
 *  </li>
 *  <li>call RPCXML method
 *  <pre>
 *  try {
 *      // call method "add" with two parameters: 2 and 4
 *      int i = (Integer) client.call("add", 2, 4);
 *      Log.d("XMLRPC Test", "result int i = " + i);
 *  } catch (XMLRPCException e) {
 *      Log.d("XMLRPC Test", "Error", e);
 *  }
 *  </pre>
 *	</li>
 * </ul>
 * 
 */

public class Cadastro extends Activity implements OnClickListener{

	private XMLRPCClient client;
	private URI uri;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		uri = URI.create("http://10.0.2.2:8888");
		client = new XMLRPCClient(uri);
		
		setContentView(R.layout.activity_cadastro);
		
		Button b = (Button) findViewById(R.id.butCad);
		b.setOnClickListener(this);

	}
	
	public void onClick(View view) {
		final Context c = this;
		XMLRPCMethod method = new XMLRPCMethod("exibe", new XMLRPCMethodCallback() {
			public void callFinished(Object result) {
				Log.i("INFO",result.toString());
				Intent it = new Intent( c, TelaConfirmacao.class);
				startActivity(it);
				//testResult.setText(result.toString());
			}
        });
		EditText textn1 = (EditText) findViewById(R.id.Nome);
		EditText textn2 = (EditText) findViewById(R.id.Data);
		
		String nome = textn1.getText().toString();
		String data = textn2.getText().toString();
		
        Object[] params = {nome,data};
        method.call(params);
	}

	class Person implements XMLRPCSerializable {
		private String firstName;
		private String lastName;
		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		public Object getSerializable() {
			Map<String, String> map = new HashMap<String, String>();
			map.put("firstName", firstName);
			map.put("lastName", lastName);
			return map;
		}
	}
	
	class TestAdapter extends ArrayAdapter<String> {
		private LayoutInflater layouter;
		private int layoutId;
		public TestAdapter(Context context, int layoutId, int textId) {
			super(context, layoutId, textId);
			this.layoutId = layoutId;
			layouter = LayoutInflater.from(Cadastro.this);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = layouter.inflate(layoutId, null);
			TextView title = (TextView) view.findViewById(R.id.title);
			TextView params = (TextView) view.findViewById(R.id.params);
			String string = getItem(position);
			String[] arr = string.split(";");
			title.setText(arr[0]);
			if (arr.length == 2) {
				params.setText(arr[1]);
			} else {
				params.setVisibility(View.GONE);
			}
			return view;
		}
	}

	interface XMLRPCMethodCallback {
		void callFinished(Object result);
	}
	
	class XMLRPCMethod extends Thread {
		private String method;
		private Object[] params;
		private Handler handler;
		private XMLRPCMethodCallback callBack;
		public XMLRPCMethod(String method, XMLRPCMethodCallback callBack) {
			this.method = method;
			this.callBack = callBack;
			handler = new Handler();
		}
		public void call() {
			call(null);
		}
		
		public void call(Object[] params) {
			this.params = params;
			start();
		}
		
		@Override
		public void run() {
    		try {
    			int i = params.length;
    			Log.i("INFO", "Valor de parms[] "+i);
    			final Object result = client.callEx(method, params);
    			handler.post(new Runnable() {
					public void run() {
						callBack.callFinished(result);
					}
    			});
    		} catch (final XMLRPCFault e) {
    			handler.post(new Runnable() {
					public void run() {
						Log.d("Test", "error", e);
					}
    			});
    		} catch (final XMLRPCException e) {
    			handler.post(new Runnable() {
					public void run() {
						Throwable couse = e.getCause();
						if (couse instanceof HttpHostConnectException) {
							Log.i("INFO",e.toString());
						} else {
							Log.i("INFO",e.toString());
						}
						Log.d("Test", "error", e);
					}
    			});
    		}
		}
	}
}