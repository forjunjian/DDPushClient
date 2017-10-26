package com.llc.ddpush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.llc.ddpush.service.DDPushService;

import static com.llc.ddpush.R.id.demo_push_port;
import static com.llc.ddpush.R.id.demo_send_0x11_data;
import static com.llc.ddpush.R.id.demo_send_0x20_data;
import static com.llc.ddpush.R.id.demo_server_ip;
import static com.llc.ddpush.R.id.demo_server_port;
import static com.llc.ddpush.R.id.demo_target_user_name;
import static com.llc.ddpush.R.id.demo_user_name;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	
	private EditText     mDemoServerIp;
	private EditText     mDemoServerPort;
	private EditText     mDemoPushPort;
	private EditText     mDemoUserName;
	private LinearLayout mDemoContainerConfiguration;
	private Button       mDemoStartButton;
	private TextView     mDemoCurServerIp;
	private TextView     mDemoCurServerPort;
	private TextView     mDemoCurPushPort;
	private TextView     mDemoCurUserName;
	private TextView     mDemoCurUuid;
	private TextView     mDemoCurSentPkgs;
	private TextView     mDemoCurReceivePkgs;
	private TextView     mDemoMsg;
	private EditText     mDemoTargetUserName;
	private Button       mDemoSend0x10Button;
	private EditText     mDemoSend0x11Data;
	private Button       mDemoSend0x11Button;
	private EditText     mDemoSend0x20Data;
	private Button       mDemoSend0x20Button;
	private LinearLayout mDemoContainerRunning;
	private ScrollView   mContainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		initView();
	}
	
	private void initView() {
		mDemoServerIp = ( EditText ) findViewById( demo_server_ip );
		mDemoServerPort = ( EditText ) findViewById( demo_server_port );
		mDemoPushPort = ( EditText ) findViewById( demo_push_port );
		mDemoUserName = ( EditText ) findViewById( demo_user_name );
		mDemoContainerConfiguration = ( LinearLayout ) findViewById( R.id.demo_container_configuration );
		mDemoStartButton = ( Button ) findViewById( R.id.demo_start_button );
		mDemoCurServerIp = ( TextView ) findViewById( R.id.demo_cur_server_ip );
		mDemoCurServerPort = ( TextView ) findViewById( R.id.demo_cur_server_port );
		mDemoCurPushPort = ( TextView ) findViewById( R.id.demo_cur_push_port );
		mDemoCurUserName = ( TextView ) findViewById( R.id.demo_cur_user_name );
		mDemoCurUuid = ( TextView ) findViewById( R.id.demo_cur_uuid );
		mDemoCurSentPkgs = ( TextView ) findViewById( R.id.demo_cur_sent_pkgs );
		mDemoCurReceivePkgs = ( TextView ) findViewById( R.id.demo_cur_receive_pkgs );
		mDemoMsg = ( TextView ) findViewById( R.id.demo_msg );
		mDemoTargetUserName = ( EditText ) findViewById( demo_target_user_name );
		mDemoSend0x10Button = ( Button ) findViewById( R.id.demo_send_0x10_button );
		mDemoSend0x11Data = ( EditText ) findViewById( demo_send_0x11_data );
		mDemoSend0x11Button = ( Button ) findViewById( R.id.demo_send_0x11_button );
		mDemoSend0x20Data = ( EditText ) findViewById( demo_send_0x20_data );
		mDemoSend0x20Button = ( Button ) findViewById( R.id.demo_send_0x20_button );
		mDemoContainerRunning = ( LinearLayout ) findViewById( R.id.demo_container_running );
		mContainer = ( ScrollView ) findViewById( R.id.container );
		
		mDemoStartButton.setOnClickListener( this );
		mDemoSend0x10Button.setOnClickListener( this );
		mDemoSend0x11Button.setOnClickListener( this );
		mDemoSend0x20Button.setOnClickListener( this );
		
		mDemoContainerConfiguration.setVisibility( View.VISIBLE );
		isRunning = false;
		mDemoContainerRunning.setVisibility( View.GONE );
		mDemoStartButton.setText( "开始测试" );
		
	}
	
	private boolean isRunning;
	
	@Override
	public void onClick(View v) {
		switch ( v.getId() ) {
			case R.id.demo_start_button:
				if ( isRunning ) {
					mDemoContainerConfiguration.setVisibility( View.VISIBLE );
					mDemoContainerRunning.setVisibility( View.GONE );
					mDemoStartButton.setText( "开始测试" );
					
					Intent startSrv = new Intent( this, DDPushService.class );
					startService( startSrv );
					
					isRunning = false;
				} else {
					mDemoContainerRunning.setVisibility( View.VISIBLE );
					mDemoContainerConfiguration.setVisibility( View.GONE );
					mDemoStartButton.setText( "关闭测试" );
					
					isRunning = true;
				}
				break;
			case R.id.demo_send_0x10_button:
				
				break;
			case R.id.demo_send_0x11_button:
				
				break;
			case R.id.demo_send_0x20_button:
				
				break;
		}
	}
	
	
}