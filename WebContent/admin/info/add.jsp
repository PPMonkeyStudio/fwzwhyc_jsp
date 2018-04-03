<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("path", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加信息</title>
<link href="${path }admin/css/style.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="../kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="../kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="../kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="../kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="../../js/jquery.min.js"></script>

<script type="text/javascript" src="../../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../../js/ajaxfileupload.js"></script>

<script type="text/javascript">
	KindEditor.ready(function(K) {
		var editor = K.create('textarea[id="content"]', {
			cssPath : '../kindeditor/plugins/code/prettify.css',
			uploadJson : '../kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '../kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterBlur : function() {
				this.sync();
				K.ctrl(document, 13, function() {
					K("form[name='ff']")[0].submit();
				});
				K.ctrl(this.edit.doc, 13, function() {
					K("form[name='ff']")[0].submit();
				});
			}
		});
		prettyPrint();
	});
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var url = "${path}admin/findAllCategoryForDrop.action";
				$.ajax({
					type : 'get',
					url : url,
					dataType : 'json',
					success : function(data) {
						$.each(data, function(i, list) {
							$("#cid").append(
									"<option value='"+list.cid+"'>"
											+ list.cname + "</option>");
						})
					}
				})
			})
</script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="${path }admin/findInfosByPage">信息列表</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加信息</span>
		</div>
		<form action="${path }admin/addInfo" name="ff" method="post"
			onsubmit="return checkValue()">

			<ul class="forminfo">

				<li><label style="width: 150px;">标题（<font
						style="color: Red; font-weight: bolder;">*</font>）：
				</label><input id="title" name="title" type="text" class="dfinput" /><i></i></li>
				<li><label style="width: 150px;">副标题：</label><input
					id="contentTitle" name="contentTitle" type="text" class="dfinput" /><i></i></li>
				<li><label style="width: 150px;">信息类别（<font
						style="color: Red; font-weight: bolder;">*</font>）：
				</label> <select name="category.cid" id="cid" class="dfinput">
						<option value="">--请选择--</option>
						<c:forEach items="${list }" var="c">
							<option value="${c.cid }">${c.cname }</option>
						</c:forEach>
				</select> <i></i></li>
				<li><label style="width: 150px;">作者（来源）：</label><input
					id="author" name="author" type="text" class="dfinput" /><i></i></li>
				<li><label style="width: 150px;">内容摘要：</label>
				<textarea id="contentAbstract" name="contentAbstract" cols="100"
						rows="4" style="width: 800px; height: 100px;" class="dfinput"></textarea><i></i></li>
				<li><label style="width: 150px;">信息内容（<font
						style="color: Red; font-weight: bolder;">*</font>）：
				</label>
				<textarea id="content" name="content" cols="100" rows="8"
						style="width: 800px; height: 400px;" /> </textarea><i></i></li>
				<li><label style="width: 150px;">排序：</label><input id="paiXu"
					name="paiXu" type="text" value="10000" class="dfinput" /><i></i></li>
				<li><label style="width: 150px;">图片：</label><input id="file1"
					name="file" type="file" onchange="fileUpload();" /><i></i></li>
				<script type="text/javascript">
					function fileUpload() {
						var files = [ 'file1' ]; //将上传三个文件 ID 分别为file2,file2,file3
						$
								.ajaxFileUpload({
									url : 'fileUploadAction', //用于文件上传的服务器端请求地址  
									secureuri : false, //一般设置为false  
									fileElementId : files, //文件上传空间的id属性  <input type="file" id="file" name="file" />  
									dataType : 'json', //返回值类型 一般设置为json  
									success : function(data, status) {
										var fileNames = data.fileFileName; //返回的文件名 
										var filePaths = data.filePath; //返回的文件地址 
										for (var i = 0; i < data.fileFileName.length; i++) {
											//将上传后的文件 添加到页面中 以进行下载											
											$("#pic").attr(
													"src",
													"${path }attached/"
															+ filePaths[i]);
											$("#picPath").val(
													"attached/" + filePaths[i]);
										}
									}
								})
					}
				</script>
				<li><label style="width: 150px;">&nbsp;</label><img src=""
					id="pic" width="120px" border="0" /><i></i></li>
				<input id="picPath" name="picPath" type="hidden" />
				<li><label style="width: 150px;">是否发布：</label><select
					id="publishStatus" name="publishStatus" class="dfinput">
						<option value="0">否</option>
						<option value="1">是</option>
				</select><i></i></li>
				<li><label>&nbsp;</label><input name="" type="submit"
					class="btn" value="确认添加" /> &nbsp;&nbsp;<input name=""
					type="button" onclick="goback();" class="btn" value="返回列表" /></li>

			</ul>
		</form>
	</div>
	<script>
		function goback() {
			window.location.href = "${path }admin/findInfosByPage";
		}
		function checkValue() {
			var str = document.getElementById("title").value;
			if (str.length < 1) {
				alert("请输入信息标题");
				document.getElementById("title").focus();
				return false;
			}
			if (str.length > 50) {
				alert("信息标题长度应小于等于50");
				document.getElementById("title").focus();
				return false;
			}
			str = document.getElementById("cid").value;
			if (str == 0) {
				alert("请选择信息类别");
				return false;
			}
			str = document.getElementById("content").value;
			if (str.length < 1) {
				alert("请输入信息内容");
				document.getElementById("content").focus();
				return false;
			}
			return true;
		}
	</script>


</body>
</html>
