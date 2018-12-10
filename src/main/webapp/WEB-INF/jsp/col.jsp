<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">


<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous"></script>

<!-- fileiput -->
<link rel="stylesheet" href="fileinput/css/fileinput.css">
<script src="fileinput/js/fileinput.js"></script>
<script src="fileinput/js/locales/zh.js"></script>

<title>颜色识别</title>
</head>
<body>

	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header">
					<h1>
						<small>上传本地图片</small>
					</h1>
				</div>
				<div class="row clearfix">
					<div class="col-md-12 column">


						<div class="file-loading">
							<input id="image1" class="file" type="file" multiple
								data-preview-file-type="any">

						</div>

					</div>
				</div>
				<br>
				<blockquote>
					<p>结果：</p>
					<small id="ret"></small>
				</blockquote>
			</div>
		</div>
	</div>



	<script type="text/javascript">
		$('#image1').fileinput({
			language : 'zh', //设置语言
			uploadUrl : "http://127.0.0.1:8081/topiot/test/light", //上传的地址
			enctype : 'multipart/form-data',
			allowedFileExtensions : [ 'jpg', 'png', 'bmp', 'txt' ],//接收的文件后缀,
			maxFileCount : 1,

			autoReplace : true

		});

		$(document).ready(
				function() {

					$("#image1").on("fileuploaded",
							function(event, data, previewId, index) {
								var recData = data.response;
						 

								 
								$("#ret").text(recData);

							});

				});
	</script>



</body>
</html>