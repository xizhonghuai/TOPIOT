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
					<li class="tpl-left-nav-item"><a href="home"
						class="nav-link active"> <i class="am-icon-cloud"></i> <span>服务列表</span>
					</a></li>
					<li class="tpl-left-nav-item"><a href="newService"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-server"></i> <span>新建服务</span>
					</a></li>

				<!-- 	<li class="tpl-left-nav-item"><a href="deviceDebug"
						class="nav-link tpl-left-nav-link-list"> <i
							class="am-icon-bug"></i> <span>远程调试</span>
					</a></li> -->
				</ul>
			</div>
		</div>





		<div class="tpl-content-wrapper">
			<ol class="am-breadcrumb">
				<li><a href="javascript:void(0)" onclick="location.reload()"
					class="am-icon-cloud">服务列表</a></li>
			</ol>
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-cloud"></span> 详细信息
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g">
						<div class="am-u-sm-12">

							<table
								class="am-table am-table-striped am-table-hover table-main">
								<thead>
									<tr>

										<th>服务id</th>
										<th>状态</th>
										<th>传输层协议</th>
										<th>端口</th>
										<th>调试端口</th>
										<th>应用层协议</th>
										<th>描述</th>
										<th>版本</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="serviceData">
								</tbody>
							</table>
							<!-- <div class="am-cf">
								<div class="am-fr">
									<ul class="am-pagination tpl-pagination">
										<li><a href="#" id="begin" onclick="pageContr(this.id)">«</a></li>
										<li><a href="#" id="u" onclick="pageContr(this.id)">&lt;</a></li>
										<li><a href="#" id="p" onclick="pageContr(this.id)">&gt;</a></li>
										<li><a href="#" id="end" onclick="pageContr(this.id)">»</a></li>
									</ul>
								</div>
							</div> -->
							<hr>
						</div>
					</div>
				</div>
					<div id="retInfo"></div>
			</div>
		</div>
	</div>

	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
		<div class="am-modal-dialog">
			<!-- 		<div class="am-modal-hd">服务控制</div> -->
			<div class="am-modal-bd">确定执行此操作？</div>
			<div class="am-modal-footer">
				<span class="am-modal-btn" data-am-modal-cancel>否</span> <span
					class="am-modal-btn" data-am-modal-confirm>是</span>
			</div>
		</div>
	</div>

	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="common/js/common.js"></script>
	<script type="text/javascript">
		var servicreId;
		function createTable(data) {
			var p = "'";
			var wrench;
			var sticon;
			var transpondflag;
			var html = [];
			/* var delid; */
			document.getElementById("serviceData").innerHTML = "";
			if (data.length > 0) {

				for (var i = 0; i < data.length; i++) {
					/* delid = "1" + data[i].stcd; */

					if (data[i].status) {
						data[i].status = "运行";
						wrench = "停止";
						sticon = "<i class=\"am-icon-gear am-icon-spin\"></i>&nbsp;";
					} else {
						data[i].status = " 停止";
						wrench = " 启动";
						sticon = "<i class=\"am-icon-gear\"></i>&nbsp;";
					}
					
					 

					html.push("<tr>");
					html.push("<td>" + data[i].serviceId + "</td>");
					html.push("<td> " + sticon + data[i].status + "</td>");
					html.push("<td>" + data[i].transport + "</td>");
					html.push("<td>" + data[i].port + "</td>");
					html.push("<td>" + data[i].debugflag + "</td>");
					html.push("<td>" + data[i].handler.substring(13) + "</td>");
					html.push("<td>" + data[i].handlerMark + "</td>");
					html.push("<td>" + data[i].handlerVsersion + "</td>");
					html.push("<td>");
					html.push("<div class=\"am-btn-toolbar\">");
					html.push("<div class=\"am-btn-group am-btn-group-xs\">");
					html
							.push("<button  onclick=\"stService("
									+ p
									+ data[i].serviceId
									+ p
									+ ")\"  class=\"am-btn am-btn-default am-btn-xs am-text-secondary\"><span class=\"am-icon-cogs\"></span>&nbsp;&nbsp;"
									+ wrench + "</button>");

					html.push("</div>");
					html.push("</div>");
					html.push("</td>");
					html.push("</tr>");
				}

				document.getElementById("serviceData").innerHTML = html
						.join(" ");

			}

		}

		$(function() {

			var par = new Object();
			var data;
			data = GetData("getService", par);
			createTable(data);

		});

		function stService(id) {
			servicreId = id;
			console.log(servicreId);
			$('#my-confirm').modal();
		}

		$(function() {
			var $confirm = $('#my-confirm');
			var $confirmBtn = $confirm.find('[data-am-modal-confirm]');
			var $cancelBtn = $confirm.find('[data-am-modal-cancel]');
			$confirmBtn
					.off('click.confirm.modal.amui')
					.on(
							'click',
							function() {

								var par = new Object();
								par.servicreId = servicreId;

								var ret = GetData("stService", par, "text",
										"application/x-www-form-urlencoded; charset=UTF-8");

								  setTips("retInfo", ret);    

							});

			$cancelBtn.off('click.cancel.modal.amui').on('click', function() {
				//
			});
		});
	</script>
	<script src="assets/js/iscroll.js"></script>
	<script src="assets/js/app.js"></script>


   

</body>

</html>