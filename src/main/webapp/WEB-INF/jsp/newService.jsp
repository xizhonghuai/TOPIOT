<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>数据接收服务管理系统</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet"
	href="//s.amazeui.org/assets/2.x/css/amaze.min.css?v=ivnek6k6">
<link rel="stylesheet" href="assets/css/admin.css">
<link rel="stylesheet" href="assets/css/app.css">
<link rel="stylesheet" href="common/css/common.css">
<script src="assets/js/echarts.min.js"></script>

</head>

<body data-type="index">


	<header class="am-topbar am-topbar-inverse admin-header">
		<div class="am-topbar-brand">
			<a href="javascript:;" class="tpl-logo"> <img
				src="assets/img/logo.png" alt="">
			</a> <span>数据接收服务管理系统</span>
		</div>
		<div
			class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

		</div>

		<button
			class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
			data-am-collapse="{target: '#topbar-collapse'}">
			<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
		</button>

		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

			<ul
				class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">

				<li><a href="###" class="tpl-header-list-link"><span
						class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
			</ul>
		</div>
	</header>


	<div class="tpl-page-container tpl-page-header-fixed">

		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">菜单列表</div>
			<div class="tpl-left-nav-list">
				<ul class="tpl-left-nav-menu">
					<li class="tpl-left-nav-item"><a href="home" class="nav-link">
							<i class="am-icon-cloud"></i> <span>服务列表</span>
					</a></li>
					<li class="tpl-left-nav-item"><a href="addService"
						class="nav-link tpl-left-nav-link-list active"> <i
							class="am-icon-server"></i> <span>新建服务</span>
					</a></li>

					<li class="tpl-left-nav-item"><a href="deviceDebug"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-bug"></i> <span>远程调试</span>
					</a></li>
				</ul>
			</div>
		</div>





		<div class="tpl-content-wrapper">
			<ol class="am-breadcrumb">
				<li><a href="javascript:void(0)" onclick="location.reload()"
					class="am-icon-server">添加服务</a></li>
			</ol>

			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-server"></span> 添加服务
					</div>

				</div>
				<div class="tpl-block ">

					<div class="am-g tpl-amazeui-form">


						<div class="am-u-sm-12 am-u-md-9">
							<form id="addService" class="am-form am-form-horizontal"
								action="">
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">服务id</label>
									<div class="am-u-sm-9">
										<input type="text" id="serviceId" name="serviceId"
											placeholder="服务唯一标识，由字母或数字组成"
											onkeyup="value=value.replace(/[^\w\/]/ig,'')"> <small
											id="serviceId_s" style="color: red"></small>
									</div>
								</div>

								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">应用层协议</label>
									<div class="am-u-sm-9">
										<select id="handler" name="handler"
											data-am-selected="{btnSize: 'sm'}" style="display: none;">
											<option value="com.protocol.test.TestHandler">测试</option>
											<option value="com.protocol.wxy.WxyHandler">危险源监控系统</option>
											<option value="com.protocol.dk.dkHandler">灯控</option>
											<option value="com.protocol.js.dljsHandler">道路积水</option>
											<option value="com.protocol.szy.szyHandler">水资源</option>
											<option value="com.protocol.sl.cqHandler">重庆水文</option>
											<option value="com.protocol.jsonrtu.JsonRtuHandler">简易RTU(JSON数据格式)</option>
										</select>
									</div>
								</div>



								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">传输层协议</label>
									<div class="am-u-sm-9">
										<select id="transport" name="transport"
											onchange="transportlayerClick()"
											data-am-selected="{btnSize: 'sm'}" style="display: none;">
											<option value="com.transportlayer.Tcp">TCP</option>
											<option value="com.transportlayer.Udp">UDP</option>
											<option value="com.transportlayer.Usart">USART</option>
											<option value="com.transportlayer.Mqtt">MQTT</option>
										</select>
									</div>
								</div>

								<div id="transport_par">

									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">监听端口</label>
										<div class="am-u-sm-9">
											<input type="text" id="port" name="port"
												placeholder="多个端口用逗号分隔"> <small id="port_s"
												style="color: red"></small>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">空闲超时时间</label>
										<div class="am-u-sm-9">
											<input type="text" id="idle" name="idle" value="600"
												placeholder="应大于tcp心跳时间"> <small id="idle_s"
												style="color: red"></small>
										</div>
									</div>


								</div>


								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">字符编码方式</label>
									<div class="am-u-sm-9">
										<select id="encodecharset" name="encodecharset"
											data-am-selected="{btnSize: 'sm'}" style="display: none;">
											<option value="ISO-8859-1">ISO-8859-1</option>
											<option value="GBK">GBK</option>
											<option value="UTF-8">UTF-8</option>
										</select> <small id="encodecharset_s" style="color: red"></small>
									</div>
								</div>

								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">字符解码方式</label>
									<div class="am-u-sm-9">
										<select id="decodecharset" name="decodecharset"
											data-am-selected="{btnSize: 'sm'}" style="display: none;">
											<option value="ISO-8859-1">ISO-8859-1</option>
											<option value="GBK">GBK</option>
											<option value="UTF-8">UTF-8</option>
										</select> <small id="decodecharset_s" style="color: red"></small>
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-intro" class="am-u-sm-3 am-form-label">数据转发</label>
									<div class="am-u-sm-9">
										<div class="tpl-switch">
											<input id="pushflag" type="checkbox" onclick="pushClick()"
												class="ios-switch bigswitch tpl-switch-btn">
											<div class="tpl-switch-btn-view">
												<div></div>
											</div>
										</div>
									</div>
								</div>


								<div id="push_par">
									<!-- <div class="am-form-group">
										<label for="user-intro" class="am-u-sm-3 am-form-label">转发地址</label>
										<div class="am-u-sm-9">
											<input type="text" id="pushUrl" name="pushUrl"
												placeholder="服务器地址，http//开头"> <small id="pushUrl_s"
												style="color: red"></small>
										</div>
									</div> -->
								</div>

								<div class="am-form-group">
									<label for="user-intro" class="am-u-sm-3 am-form-label">调试服务</label>
									<div class="am-u-sm-9">
										<div class="tpl-switch">
											<input id="debugflag" type="checkbox"
												class="ios-switch bigswitch tpl-switch-btn">
											<div class="tpl-switch-btn-view">
												<div></div>
											</div>
										</div>
									</div>
								</div>



								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3"></div>
								</div>

								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3"></div>
								</div>



								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<button type="button" onclick="checkPar()"
											class="am-btn am-btn-primary tpl-btn-bg-color-success ">
											<i class="am-icon-cloud-upload"></i> 提交
										</button>
									</div>
								</div>
								<div class="portlet-title">
									<div class="caption font-green bold"></div>
								</div>


								<div id="retInfo" class="am-form-group"></div>

							</form>
						</div>
					</div>
				</div>


				<!-- 	<div id="retInfo"></div> -->

			</div>
		</div>
	</div>



	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="common/js/common.js"></script>
	<script type="text/javascript">
		function switchStransportPar(transport) {
			var dom = document.getElementById("transport_par");

			var tcp_html = [];

			tcp_html.push("<div class=\"am-form-group\">");
			tcp_html
					.push("<label class=\"am-u-sm-3 am-form-label\">监听端口</label>");
			tcp_html.push("<div class=\"am-u-sm-9\">");
			tcp_html.push("<input   type=\"text\"  id=\"port\" name=\"port\"");
			tcp_html.push("placeholder=\"多个端口用逗号分隔\" > <small id=\"port_s\"");
			tcp_html.push("style=\"color: red\"></small>");
			tcp_html.push("</div>");
			tcp_html.push("</div>");
			tcp_html.push("<div class=\"am-form-group\">");
			tcp_html
					.push("<label class=\"am-u-sm-3 am-form-label\">空闲超时时间</label>");
			tcp_html.push("<div class=\"am-u-sm-9\">");
			tcp_html
					.push("<input type=\"text\" id=\"idle\" name=\"idle\" value=\"600\"");
			tcp_html.push("placeholder=\"应大于tcp心跳时间\" > <small id=\"idle_s\"");
			tcp_html.push("style=\"color: red\"></small>");
			tcp_html.push("</div>");
			tcp_html.push("</div>");

			var udp_html = [];

			udp_html.push("<div class=\"am-form-group\">");
			udp_html
					.push("<label class=\"am-u-sm-3 am-form-label\">监听端口</label>");
			udp_html.push("<div class=\"am-u-sm-9\">");
			udp_html.push("<input type=\"text\"  id=\"port\" name=\"port\"");
			udp_html.push("placeholder=\"多个端口用逗号分隔\"> <small id=\"port_s\"");
			udp_html.push("style=\"color: red\"></small>");
			udp_html.push("</div>");
			udp_html.push("</div>");

			var usart_html = [];

			usart_html.push("<div class=\"am-form-group\">");
			usart_html
					.push("<label class=\"am-u-sm-3 am-form-label\">端口</label>");
			usart_html.push("<div class=\"am-u-sm-9\">");
			usart_html.push("<input type=\"text\"  id=\"port\" name=\"port\"");
			usart_html
					.push("placeholder=\"Windows: COM1、COM2;  Linux: /dev/ttyS0、/dev/ttyS1; Unix: /dev/ttyUsb0、/dev/ttyUsb1\"> <small id=\"port_s\"");
			usart_html.push("style=\"color: red\"></small>");
			usart_html.push("</div>");
			usart_html.push("</div>");
			usart_html.push("<div class=\"am-form-group\">");
			usart_html
					.push("<label class=\"am-u-sm-3 am-form-label\">波特率</label>");
			usart_html.push("<div class=\"am-u-sm-9\">");
			usart_html.push("<select id=\"baud\" name=\"baud\"");
			usart_html.push("<option value=\"1200\">1200bps</option>");
			usart_html.push("<option value=\"2400\">2400bps</option>");
			usart_html.push("<option value=\"4800\">4800bps</option>");
			usart_html.push("<option value=\"9600\">9600bps</option>");
			usart_html.push("<option value=\"19200\">19200bps</option>");
			usart_html.push("<option value=\"38400\">38400bps</option>");
			usart_html.push("<option value=\"57600\">57600bps</option>");
			usart_html.push("<option value=\"115200\">115200bps</option>");
			usart_html

			usart_html.push("</select>");
			usart_html.push("</div>");
			usart_html.push("</div>");

			if (transport == "com.transportlayer.Tcp") {
				dom.innerHTML = tcp_html.join(" ");
			} else if (transport == "com.transportlayer.Udp") {
				dom.innerHTML = udp_html.join(" ");
			} else if (transport == "com.transportlayer.Usart") {
				dom.innerHTML = usart_html.join(" ");
			} else if (transport == "com.transportlayer.Mqtt") {
				dom.innerHTML = "";
			}

			/* 	document.getElementById(id).innerHTML = html;	 */
		}

		function transportlayerClick() {
			var transport = $("#transport").val();

			switchStransportPar(transport);
		}

		function pushClick() {

			var pushflag = document.getElementById("pushflag").checked;
			var dom = document.getElementById("push_par");
			if (pushflag) {
				var html = [];
				html.push("<div class=\"am-form-group\">");
				html
						.push("<label class=\"am-u-sm-3 am-form-label\">转发地址</label>");
				html.push("<div class=\"am-u-sm-9\">");
				html
						.push("<input type=\"text\"  id=\"pushUrl\" name=\"pushUrl\"");
				html.push("placeholder=\"http://开头\"> <small id=\"pushUrl_s\"");
				html.push("style=\"color: red\"></small>");
				html.push("</div>");
				html.push("</div>");

				dom.innerHTML = html.join(" ");

			} else {
				dom.innerHTML = "";
			}

		}

		function checkPar() {
			var flag = true;
			var par = new Object();

			var serviceId = $.trim($("#serviceId").val());
			var handler = $("#handler").val();
			var transport = $("#transport").val();
			var encodecharset = $("#encodecharset").val();
			var decodecharset = $("#decodecharset").val();
			var debugflag = document.getElementById("debugflag").checked;
			var port = $.trim($("#port").val());
			var idle = $.trim($("#idle").val());
			var baud = $("#baud").val();

			var pushUrl = $("#pushUrl").val();
			var pushflag = document.getElementById("pushflag").checked;

			if (serviceId.length > 0) {
				par.serviceId = serviceId;
			} else {
				flag = false;
				$("#serviceId_s").text("请输入服务id");
			}

			if (port.length > 0) {
				par.port = port;
			} else {
				flag = false;
				$("#port_s").text("请输入端口号");
			}

			if (transport == "com.transportlayer.Tcp") {
				if (idle.length > 0) {
					par.idle = idle;
				} else {
					flag = false;
					$("#idle_s").text("请输入读写空闲时间");
				}
			}

			if (pushflag) {
				if (pushUrl.length > 0) {
					par.pushUrl = pushUrl;
				} else {
					flag = false;
					$("#pushUrl_s").text("请输入服务器地址");
				}
			}

			par.handler = handler;
			par.transport = transport;
			par.encodecharset = encodecharset;
			par.decodecharset = decodecharset;
			par.baud = baud;
			par.debugflag = debugflag;
			par.pushflag = pushflag;

			if (flag) {

				var ret = GetData("postService", par, "text",
						"application/x-www-form-urlencoded; charset=UTF-8");

				setCode("retInfo", ret);

			}

		}
	</script>
	<script src="assets/js/iscroll.js"></script>
	<script src="assets/js/app.js"></script>





</body>

</html>