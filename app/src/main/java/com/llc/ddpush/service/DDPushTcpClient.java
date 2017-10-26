package com.llc.ddpush.service;

import android.content.Context;
import android.os.PowerManager;

import com.llc.ddpush.message.IMessageDispatcher;

import org.ddpush.im.v1.client.appuser.Message;
import org.ddpush.im.v1.client.appuser.TCPClientBase;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/26.
 */

public class DDPushTcpClient extends TCPClientBase {
	
	PowerManager.WakeLock mWakeLock;
	Context               mContext;
	IMessageDispatcher    mMessageDispatcher;
	
	public DDPushTcpClient(Context context, byte[] uuid, IMessageDispatcher messageDispatcher) throws Exception {
		this( context, uuid, 1, DDPushSettings.DDPUSH_SERVER_IP, DDPushSettings.DDPUSH_SERVER_PORT, messageDispatcher );
	}
	
	public DDPushTcpClient(Context context, byte[] uuid, int appid, String serverAddr, int serverPort, IMessageDispatcher messageDispatcher)
		throws Exception {
		super( uuid, appid, serverAddr, serverPort, 10 );
		PowerManager pm = ( PowerManager ) context.getSystemService( Context.POWER_SERVICE );
		mWakeLock = pm.newWakeLock( PowerManager.PARTIAL_WAKE_LOCK, "OnlineService" );
		mMessageDispatcher = messageDispatcher;
	}
	
	@Override
	public boolean hasNetworkConnection() {
		return Util.hasNetwork( mContext );
	}
	
	
	@Override
	public void trySystemSleep() {
		if ( mWakeLock != null && mWakeLock.isHeld() == true ) {
			mWakeLock.release();
		}
	}
	
	@Override
	public void onPushMessage(Message message) {
		if ( message == null ) {
			return;
		}
		if ( message.getData() == null || message.getData().length == 0 ) {
			return;
		}
		if ( message.getCmd() == 16 ) {// 0x10 通用推送信息
			//			messageDispatcher( 16, "DDPush通用推送信息", "时间：" + DateTimeUtil.getCurDateTime(), "收到通用推送信息" );
			mMessageDispatcher.dispatchCommonMessage();
		}
		
		if ( message.getCmd() == 17 ) {// 0x11 分组推送信息
			long msg = ByteBuffer.wrap( message.getData(), 5, 8 ).getLong();
			//			messageDispatcher( 17, "DDPush分组推送信息", "" + msg, "收到通用推送信息" );
			mMessageDispatcher.dispatchGroupMessage( msg );
		}
		
		if ( message.getCmd() == 32 ) {// 0x20 自定义推送信息
			String str = null;
			try {
				str = new String( message.getData(), 5, message.getContentLength(), "UTF-8" );
			} catch ( Exception e ) {
				str = Util.convert( message.getData(), 5, message.getContentLength() );
			}
			//			messageDispatcher( 32, "DDPush自定义推送信息", "" + str, "收到自定义推送信息" );
			
			// 提取消息
			String serviceName = "从String 里面提取服务类型";
			
			SimpleDateFormat sdf = new SimpleDateFormat( "yy-MM-dd HH:mm:ss" );
			
			String timeStamp = sdf.format( new Date() );
			
			// todo 消息校验
			
			// 消息分发
			mMessageDispatcher.dispatchCustomMessage( serviceName, timeStamp, str );
		}
	}
	
}
