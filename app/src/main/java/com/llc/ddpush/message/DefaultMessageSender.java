package com.llc.ddpush.message;

import org.ddpush.im.v1.client.appserver.Pusher;

/**
 * Created by Administrator on 2017/10/26.
 */

public class DefaultMessageSender implements IMessageSender {
	
	
	@Override
	public void sendCommonMessage(OnSendingListener listener) {
		//		new Thread(new send0x10Task( listener )).start();
	}
	
	@Override
	public void sendGroupMessage(long groupID, OnSendingListener listener) {
		
	}
	
	@Override
	public void sendCustomMessage(String service, String timeStamp, String data, OnSendingListener listener) {
		
	}
	
	class send0x10Task implements Runnable {
		
		private String            serverIp;
		private int               port;
		private byte[]            uuid;
		private OnSendingListener listener;
		
		public send0x10Task(String serverIp, int port, byte[] uuid, OnSendingListener listener) {
			this.serverIp = serverIp;
			this.port = port;
			this.uuid = uuid;
			this.listener = listener;
		}
		
		public void run() {
			Pusher pusher = null;
			//			Intent startSrv = new Intent( context, OnlineService.class);
			//			startSrv.putExtra("CMD", "TOAST");
			try {
				boolean result;
				pusher = new Pusher( serverIp, port, 1000 * 5 );
				result = pusher.push0x10Message( uuid );
				if ( result ) {
					listener.onSendMessageSuccess();
				} else {
					listener.onSendMessageFailure( -1, "发送失败！格式有误" );
				}
			} catch ( Exception e ) {
				e.printStackTrace();
				listener.onSendMessageFailure( -2, "发送失败！" + e.getMessage() );
			} finally {
				if ( pusher != null ) {
					try {
						pusher.close();
					} catch ( Exception e ) {
					}
					;
				}
			}
		}
	}
	
	class send0x11Task implements Runnable {
		
		private String            serverIp;
		private int               port;
		private byte[]            uuid;
		private long              msg;
		private OnSendingListener listener;
		
		public send0x11Task(String serverIp, int port, byte[] uuid, long msg, OnSendingListener listener) {
			this.serverIp = serverIp;
			this.port = port;
			this.uuid = uuid;
			this.msg = msg;
			this.listener = listener;
		}
		
		public void run() {
			Pusher pusher = null;
			try {
				boolean result;
				pusher = new Pusher( serverIp, port, 1000 * 5 );
				result = pusher.push0x11Message( uuid, msg );
				if ( result ) {
					listener.onSendMessageSuccess();
				} else {
					listener.onSendMessageFailure( -1, "发送失败！格式有误" );
				}
			} catch ( Exception e ) {
				e.printStackTrace();
				listener.onSendMessageFailure( -2, "发送失败！" + e.getMessage() );
			} finally {
				if ( pusher != null ) {
					try {
						pusher.close();
					} catch ( Exception e ) {
					}
					;
				}
			}
		}
	}
	
	
	class send0x20Task implements Runnable {
		
		private String            serverIp;
		private int               port;
		private byte[]            uuid;
		private byte[]            msg;
		private OnSendingListener listener;
		
		public send0x20Task(String serverIp, int port, byte[] uuid, byte[] msg, OnSendingListener listener) {
			this.serverIp = serverIp;
			this.port = port;
			this.uuid = uuid;
			this.msg = msg;
			this.listener = listener;
		}
		
		public void run() {
			Pusher pusher = null;
			try {
				boolean result;
				
				
				pusher = new Pusher( serverIp, port, 1000 * 5 );
				result = pusher.push0x20Message( uuid, msg );
				if ( result ) {
					listener.onSendMessageSuccess();
				} else {
					listener.onSendMessageFailure( -1, "发送失败！格式有误" );
				}
			} catch ( Exception e ) {
				e.printStackTrace();
				listener.onSendMessageFailure( -2, "发送失败！" + e.getMessage() );
			} finally {
				if ( pusher != null ) {
					try {
						pusher.close();
					} catch ( Exception e ) {
					}
					;
				}
			}
		}
	}
	
}
