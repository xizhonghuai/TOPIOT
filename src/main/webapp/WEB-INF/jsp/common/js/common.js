/**
 * 
 */

// 日期格式化
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

function DateFormat(tm) {
	return new Date(tm).format('yyyy-MM-dd hh:mm:ss');
}

function formatDate(tm, form) {
	return new Date(tm).format(form);
}

function checkTM(TM) {
	var subM = Math.abs((new Date().getTime() - new Date(TM).getTime())
			/ (60 * 60 * 1000));// 得到两个时间相差的小时数

	if (subM < 2) {
		return true;
	}
	return false;
}

// 数据请求
function GetData(Url, request) {
	var ret;
	$.ajax({
		url : Url,
		type : 'post',
		async : false,
		dataType : "json",
		data : request,
		success : function(data) {

			ret = data;
		},
		error : function() {
			ret = "err";
		}
	});

	return ret;
}

function deleteTipsHtml() {
	var tips = document.getElementById("tips");
	tips.parentNode.removeChild(tips);
}

function setWarningTips(id, text) {
	var html = "	<div id=\"tips\" class=\"alert alert-warning\"  onclick=\"deleteTipsHtml()\"> "
			+ "<a href=\"#\" class=\"close\" data-dismiss=\"alert\">×</a> <strong>错误：</strong>"
			+ text + "</div>";

	document.getElementById(id).innerHTML = html;

}

function setTips(id, text) {
	var html = "	<div id=\"tips\" class=\"alert alert-success\"  onclick=\"deleteTipsHtml()\"> "
			+ "<a href=\"#\" class=\"close\" data-dismiss=\"alert\">×</a> <strong>信息：</strong>"
			+ text + "</div>";
	document.getElementById(id).innerHTML = html;
}

function setCode(id, text) {
	var html = "<div class=\"doc-actions\"> " + "<div class=\"doc-act-inner\">"
			+ "<span class=\"doc-act-clip am-icon-copy\"   onclick=\"copyCode()\"> Copy</span>"
			+ "</div>" + "</div>" + "<div class=\"doc-code demo-highlight\">"
			+ "<pre style=\"margin: 0;\"> "
			+ " <code id=\"xmlcode\"  class=\"xml\"></code> </pre> </div>";
	document.getElementById(id).innerHTML = html;

	document.getElementById("xmlcode").innerText = text;

}

function copyCode() {
	var Url2 = document.getElementById("xmlcode");
	Url2.select(); // 选择对象
	document.execCommand("Copy"); // 执行浏览器复制命令
	alert("复制成功!");

}

// 数据请求
function GetData(Url, request, retType, enType) {
	var ret;
	$.ajax({
		url : Url,
		type : 'post',
		timeout : 5000,
		async : false,
		contentType : enType, // 默认值: "application/x-www-form-urlencoded;
		// charset=UTF-8"
		dataType : retType,
		data : request,
		success : function(data) {
			ret = data;

		},
		error : function(XMLHttpRequest, textStatus) {
			ret = "错误：" + XMLHttpRequest.status + "              "
					+ XMLHttpRequest.statusText

		}
	});

	return ret;
}
