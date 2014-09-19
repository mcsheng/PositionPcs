package com.example.positonpcs;

import com.baidu.pcs.BaiduPCSActionInfo;
import com.baidu.pcs.BaiduPCSClient;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;


public class MainActivity extends Activity
{
	private TextView mTextView;
	private Button mQuatoButton;
	private Button mUpLoadButton;
	private BaiduPCSClient mApi;
	private Handler mHandler;
	private String mOauth = "23.86d52666004e9426179af70008437336.2592000.1413644365.3255912961-238347";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView = (TextView)findViewById(R.id.textView);
		mQuatoButton = (Button)findViewById(R.id.quatoButton);
		mUpLoadButton = (Button)findViewById(R.id.upLoadButton);
		mHandler = new Handler();
		mApi = new BaiduPCSClient();
		mApi.setAccessToken(mOauth);
		
		System.out.println("error");
//		BaiduPCSActionInfo.PCSQuotaResponse info = mApi.quota();
//		mTextView.setText(new String(info.used+""));
//	
//		Toast.makeText(MainActivity.this, new String(info.used + ""),Toast.LENGTH_SHORT).show();
		mQuatoButton.setOnClickListener(new View.OnClickListener( )
		{
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				new Thread()
				{
					public void run() {
						System.out.println("inRun");

							final BaiduPCSActionInfo.PCSQuotaResponse info = mApi.quota();
//							/**值为0即成功*/
//							if(info.status.errorCode == 0){
//								System.out.println(info.used);
//							}
//							else{
//								System.out.println("quota error");
//							}
							mHandler.post(new Runnable()
							{
								
								@Override
								public void run()
								{
									// TODO Auto-generated method stub
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
							});
							
					};
					
				}.start();
				
			}
		});
		

	}

}
