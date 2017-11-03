----------------------------
vue-组件					|
----------------------------
	# 个组件本质上是一个拥有预定义选项的一个 Vue 实例
	# 向vue注册一个组件
		// 定义名为 todo-item 的新组件
		Vue.component('todo-item', {
			template: '<li>这是个待办项</li>'
		})

	# 构建模版组件
		<ol>
			<!-- 创建一个 todo-item 组件的实例 -->
			<todo-item></todo-item>
		</ol>
	


1,获取系统订单号
	url		:order/create
	method	:POST
	param	:
		carNum			//车牌号码
		parkId			//车场ID
		payType			//支付类型(枚举:ALIPAY)
		isno			//IIS服务器订单号

2,支付宝支付
	url		:order/createAlipayOrder
	method	:POST
	param	:
		orderNum		//系统订单号
		total_fee		//总金额(单位为分)
		isno			//IIS服务器订单号 



partner="2088101568358171"&
seller_id="xxx@alipay.com"&
out_trade_no="0819145412-6177"&
subject="测试"&
body="测试测试"&
total_fee="0.01"&
notify_url="http://notify.msp.hk/notify.htm"&
service="mobile.securitypay.pay"&
payment_type="1"&
_input_charset="utf-8"&
it_b_pay="30m"&
sign="lBBK%2F0w5LOajrMrji7DUgEqNjIhQbidR13GovA5r3TgIbNqv231yC1NksLdw%2Ba3JnfHXoXuet6XNNHtn7VE%2BeCoRO1O%2BR1KugLrQEZMtG5jmJIe2pbjm%2F3kb%2FuGkpG%2BwYQYI51%2BhA3YBbvZHVQBYveBqK%2Bh8mUyb7GM1HxWs9k4%3D"&
sign_type="RSA"

_input_charset=utf-8&
body=付款&
notify_url=http://app.123667.com/park/order/callback&
out_trade_no=CCO69620170628181444&
partner=2088011271959663&
payment_type=1&
seller_id=2088011271959663&
service=alipay.wap.create.direct.pay.by.user&
sign=kslPKD9Vb+MiAXbU2OkQVVFcNNw5goFIOYWKJ4UuxOZKkfNNraf4wBNjhKXTls6G1xyif+tWAIqAYADPL0g0brZlkp1qFoIn9R+huF/xlE7l53Ckgao1ig2XDA/Loizw9myYiT46XkpebtU48jUG5SBDtcpGTsM0CSTb5UVHd+Q=&
sign_type=RSA&
subject=停车支付&
total_fee=900