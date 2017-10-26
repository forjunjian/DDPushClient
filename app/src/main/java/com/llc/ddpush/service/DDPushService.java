package com.llc.ddpush.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.llc.ddpush.message.DefaultMessageDispatcher;

/**
 * Created by Administrator on 2017/10/26.
 */

public class DDPushService extends Service {
	
	DDPushTcpClient mTcpClient;
	
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		resetClient();
	}
	
	protected void resetClient() {
		
		if ( this.mTcpClient != null ) {
			try {
				mTcpClient.stop();
			} catch ( Exception e ) {
			}
		}
		
		try {
			mTcpClient = new DDPushTcpClient( this, Util.md5Byte( DDPushSettings.DDPUSH_ACCOUNT ), new DefaultMessageDispatcher() );
			mTcpClient.setHeartbeatInterval( 50 );
			mTcpClient.start();
			// 重启统计
			//			SharedPreferences.Editor editor = account.edit();
			//			editor.putString( DDPushSettings.SENT_PKGS, "0" );
			//			editor.putString( DDPushSettings.RECEIVE_PKGS, "0" );
			//			editor.commit();
		} catch ( Exception e ) {
			Toast.makeText( this.getApplicationContext(), "操作失败：" + e.getMessage(), Toast.LENGTH_LONG ).show();
		}
		Toast.makeText( this.getApplicationContext(), "ddpush：终端重置", Toast.LENGTH_LONG ).show();
	}
}
