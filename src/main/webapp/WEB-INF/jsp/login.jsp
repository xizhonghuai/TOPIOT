<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多邦iot后台管理系统</title>
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
<link rel="stylesheet" href="assets/css/common.css">
<link rel="stylesheet" href="assets/css/app.css">
</head>
<body data-type="login">

	<div class="am-g myapp-login">

		<div>
			<h1></h1>
			<h1></h1>
			<h1></h1>
			<h1></h1>
		</div>


		<div class="myapp-login-logo-block  tpl-login-max">
			<div class="myapp-login-logo-text">
				<div class="myapp-login-logo-text">
					多邦科技 <span> Login</span> <i class="am-icon-skyatlas"></i>

				</div>
			</div>

			<div class="login-font">

				<!-- or <span> Sign Up</span> -->
			</div>
			<div class="am-u-sm-10 login-am-center">
				<form class="am-form" action="home" method="post">
					<fieldset>
						<div class="am-form-group">
							<input style="border-radius: 10px;" type="text" class=""
								id="name" name="name" placeholder="输入账户"
								onclick="deleteTipsHtml()">
						</div>
						<div>
							<h3></h3>
						</div>
						<div class="am-form-group">
							<input style="border-radius: 10px;" type="password" class=""
								id="password" name="password" placeholder="输入密码"
								onclick="deleteTipsHtml()">
						</div>
						<p>
							<button type="submit" class="am-btn am-btn-default">登录</button>
						</p>
					</fieldset>
				</form>
			</div>

			${tips}
			<script type="text/javascript">
				function deleteTipsHtml() {
					var tips = document.getElementById("tips");
					tips.parentNode.removeChild(tips);
				}
			</script>

			<!-- 
				<div class="alert alert-success">
					<a href="#" class="close" data-dismiss="alert">×</a> <strong>警告！</strong>您的网络连接有问题。
				</div>-->
		</div>
	</div>


	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/app.js"></script>
</body>
</html>