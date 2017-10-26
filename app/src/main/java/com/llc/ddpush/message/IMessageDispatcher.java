package com.llc.ddpush.message;

/**
 * Created by Administrator on 2017/10/26.
 */

public interface IMessageDispatcher {
	
	/**
	 * 通用消息（广播）
	 */
	void dispatchCommonMessage();
	
	/**
	 * 分组消息
	 *
	 * @param groupId
	 */
	void dispatchGroupMessage(long groupId);
	
	/**
	 * 自定义消息
	 *
	 * @param service   服务类型
	 * @param timeStamp 时间截
	 * @param data      数据（建议json格式）
	 */
	void dispatchCustomMessage(String service, String timeStamp, String data);
}
