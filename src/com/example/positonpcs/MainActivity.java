package com.example.positonpcs;

import com.baidu.pcs.BaiduPCSActionInfo;
import com.baidu.pcs.BaiduPCSClient;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;


public class MainActivity extends Activity
{
	private TextView mTextView;
	private BaiduPCSClient mApi;
	private String mOauth = "23.86d52666004e9426179af70008437336.2592000.1413644365.3255912961-238347";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView = (TextView)findViewById(R.id.textView);
		mApi = new BaiduPCSClient();
		mApi.setAccessToken(mOauth);
		
		System.out.println("error");
//		BaiduPCSActionInfo.PCSQuotaResponse info = mApi.quota();
//		mTextView.setText(new String(info.used+""));
//	
//		Toast.makeText(MainActivity.this, new String(info.used + ""),Toast.LENGTH_SHORT).show();
		new Thread()
		{
			public void run() {
				//Looper.prepare();
				System.out.println("inRun");
				//Toast.makeText(MainActivity.this, "hello",Toast.LENGTH_SHORT).show();

				try
				{

					BaiduPCSActionInfo.PCSQuotaResponse info = mApi.quota();
					if(info == null)
					{
						System.out.println("info is null");
						Toast.makeText(MainActivity.this,"info is null" ,Toast.LENGTH_SHORT).show();

					}
					else
					{
						mTextView.setText(new String(info.used+""));
	
						Toast.makeText(MainActivity.this, new String(info.used + ""),Toast.LENGTH_SHORT).show();
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				//Looper.loop();
			};
			
		}.start();

	}

}
