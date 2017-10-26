package com.llc.ddpush.message;

/**
 * Created by Administrator on 2017/10/26.
 */

public interface IMessageSender {
	
	void sendCommonMessage(OnSendingListener listener);
	
	void sendGroupMessage(long groupID, OnSendingListener listener);
	
	void sendCustomMessage(String service, String timeStamp, String data, OnSendingListener listener);
	
	interface OnSendingListener {
		
		void onSendMessageSuccess();
		
		void onSendMessageFailure(int errorCode, String msg);
	}
}
