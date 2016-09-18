package com.gwb.util;

import java.util.Properties;

/**
 * 存储配置信息
 */

public final class Config
{

	public final static String Server_IP = "http://139.129.33.233:8080/";

	// 每天可提现次数
	public final static int WITHDRAW_NUM = 5;
	// 头像存放位置
	public final static String USER_ICON_PATH = "upload/icon/";
	// 名片存放位置
	public final static String USER_CARD_PATH = "upload/card/";
	// 资源文件存放目录
	public final static String RES_PAHT = FileUtils.getRootPath() + "/gwb_res/";
	// 配置文件存储文件
	public final static String SETTING_FILE_PATH = "/setting.properties";

	/**
	 * 分页信息
	 */
	// 手机端用户请求每页显示的条数
	public final static int USER_REQUEST_PAGER_SIZE = 5;
	// 手机端用户订单每页显示的条数
	public final static int USER_ORDER_PAGER_SIZE = 5;
	// 手机端每页显示顾问条数
	public final static int CONSULTANT_PAGER_SIZE = 5;
	// 手机端每页显示顾问评价条数
	public final static int COMMENT_PAGER_SIZE = 5;
	// 手机端每页显示聊天记录条数
	public final static int MSG_PAGER_SIZE = 5;
	// 预约每页显示条数
	public final static int APPOINT_PAGER_SIZE = 5;
	// 手机端每页显示财务明细的条数
	public final static int FINANCIAL_DETAIL_PAGER_SIZE = 5;
	// 管理员每页显示条数
	public final static int ADMIN_PAGER_SIZE = 15;

	/**
	 * 短信服务器信息
	 */
	public final static String SMS_API_KEY = "69eedb49c3f42421aa57c066a7d69e5a";

	public final static int WX_PAY_TYPE = 0;
	public final static int AL_PAY_TYPE = 1;

	/**
	 * 微信支付信息
	 */
	// 微信预支付接口URL
	public final static String WX_PRE_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	// 微信退款URL
	public final static String WX_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	// 微信退款查询URL
	public final static String WX_REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
	// 微信关闭订单URL
	public final static String WX_CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
	// app id
	public final static String WX_APP_ID = "wx8cb6093de4eac6df";
	// 商户id
	public final static String WX_MCH_ID = "1356552602";
	// 商户接收支付结果通知的URL
	public final static String NOTIFY_URL = Server_IP + "gwb/Order_wxPayOrder";
	// 商户交易方式
	public final static String TRADE_TYPE = "APP";
	// 商户API 秘钥
	public final static String WX_APP_KEY = "dk0dufscigym2kuxqkjt4ymzij6hw2jz";

	/**
	 * 阿里支付信息
	 */
	// 合作者身份
	public final static String AL_PID = "2088421380560762";
	// APPID
	public final static String AL_APP_ID = "2016071401616018";
	// 私钥
	public final static String private_key = "rsa_private_key_pkcs8.pem";
	// 公钥
	public final static String public_key = "rsa_public_key.pem";
	// 支付宝公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm
	public final static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	// 支付异步通知接口
	public final static String Alipay_notify_url = Server_IP + "gwb/Alipay_alipayOrderNotify";
	// 批量退款异步通知接口
	public final static String Alipay_refund_notify_url = Server_IP + "gwb/Alipay_alipayRefundNotify";
	// 批量转账异步通知接口
	public final static String Alipay_withdraw_notify_url = Server_IP + "gwb/Alipay_alipayWithdrawNotify";
	// 卖家支付宝账号
	public final static String seller_id = "m18519129052@163.com";
	// 卖家支付宝名称
	public final static String seller_name = "米驼（北京）科技有限公司";

	/**
	 * ping++ 支付配置信息 （Ping++没有使用）
	 */
	// Pingpp 管理平台对应的 API Key
	public final static String PING_API_Key = "sk_test_W904uDOqzrTGHCuH0Gj90SqH";
	// 你生成的私钥路径
	public final static String PING_privateKeyPath = Config.class.getClassLoader().getResource("").getPath()
			+ "/rsa_private_key.pem";
	// Pingpp 管理平台对应的应用 ID，app_id
	public final static String PING_APP_ID = "app_OevnfLOOOSq5mXzP";

	/**
	 * 报价信息
	 */
	// 线上咨询价格
	public static float ONLINE_CONSULT_PRICE;
	// 线下咨询价格
	public static float OFFLINE_CONSULT_PRICE;
	// 线上咨询抽成
	public static float ONLINE_PERCENT;
	// 线下咨询抽成
	public static float OFFLINE_PERCENT;

	static
	{
		initPrice();
	}

	public static void initPrice()
	{
		Properties properties = PropertiesUtils.getPropertiesFromFile(Config.RES_PAHT + Config.SETTING_FILE_PATH);
		ONLINE_CONSULT_PRICE = new Float(properties.getProperty("online_price"));
		ONLINE_PERCENT = new Float(properties.getProperty("online_percent"));
		OFFLINE_CONSULT_PRICE = new Float(properties.getProperty("offline_price"));
		OFFLINE_PERCENT = new Float(properties.getProperty("offline_percent"));
	}

}
