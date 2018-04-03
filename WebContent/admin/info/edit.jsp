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
<title>修改信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${path }admin/css/style.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="${path}admin/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="${path}admin/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="${path}admin/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${path}admin/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${path}admin/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path}js/ajaxfileupload.js"></script>
<script>
	KindEditor
			.ready(function(K) {
				var editor1 = K
						.create(
								'textarea[name="content"]',
								{
									cssPath : '${path}admin/kindeditor/plugins/code/prettify.css',
									uploadJson : '${path}admin/kindeditor/jsp/upload_json.jsp',
									fileManagerJson : '${path}admin/kindeditor/jsp/file_manager_json.jsp',
									allowFileManager : true,
									afterCreate : function() {
										var self = this;
										K.ctrl(document, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
										K.ctrl(self.edit.doc, 13, function() {
											self.sync();
											document.forms['example'].submit();
										});
									}
								});
				prettyPrint();
			});
</script>
<%-- <script type="text/javascript"> 
		$(document).ready(function () {  
            var url = "${path}admin/findAllCategoryForDrop.action";  
            $.ajax({  
                type:'get',  
                url:url,  
                dataType: 'json',  
                success:function(data){  
                    $.each(data,function(i,list){  
                    	$("#cid").append("<option value='"+list.cid+"'>"+list.cname+"</option>");
                    })  
                }  
            })
        })  
    </script> --%>
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
			<span>修改信息</span>
		</div>
		<form action="${path }admin/updateInfo" name="ff" method="post"
			onsubmit="return checkValue()">

			<ul class="forminfo">
				<input id="infoId" name="infoId" value="${info.infoId}"
					type="hidden" />
				<li><label style="width: 150px;">标题（<font
						style="color: Red; font-weight: bolder;">*</font>）：
				</label><input id="title" name="title" value="${info.title}" type="text"
					class="dfinput" /><i></i></li>
				<li><label style="width: 150px;">副标题：</label><input
					id="contentTitle" name="contentTitle" value="${info.contentTitle}"
					type="text" class="dfinput" /><i></i></li>
				<li><label style="width: 150px;">信息类别（<font
						style="color: Red; font-weight: bolder;">*</font>）：
				</label><select name="category.cid" id="cid" class="dfinput">
						<option value="">--请选择--</option>
						<c:forEach items="${categorylist }" var="c">
							<option value="${c.cid }"
								<c:if test="${c.cid eq info.category.cid }">selected="selected"</c:if>>${c.cname }</option>
						</c:forEach>
				</select> <i></i></li>
				<li><label style="width: 150px;">作者/来源：</label><input
					id="author" name="author" value="${info.author}" type="text"
					class="dfinput" /><i></i></li>
				<li><label style="width: 150px;">内容摘要：</label>
				<textarea id="contentAbstract" name="contentAbstract" cols="100"
						rows="4" style="width: 800px; height: 100px;" class="dfinput">${info.contentAbstract}</textarea><i></i></li>
				<li><label style="width: 150px;">信息内容（<font
						style="color: Red; font-weight: bolder;">*</font>）：
				</label>
				<textarea id="content" name="content" cols="100" rows="8"
						style="width: 800px; height: 200px;" class="dfinput" />${info.content}</textarea><i></i></li>
				<li><label style="width: 150px;">排序：</label><input id="paiXu"
					name="paiXu" type="text" value="${info.paiXu}" class="dfinput" /><i></i></li>
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
				<li><label style="width: 150px;">&nbsp;</label><img
					src="${path }${info.picPath}" id="pic" width="120px" border="0" /><i></i></li>
				<li><label style="width: 150px;">图片路径：</label><input
					id="picPath" name="picPath" value="${info.picPath}" type="text" /><i></i></li>

				<li><label style="width: 150px;">是否发布：</label><select
					id="publishStatus" name="publishStatus" class="dfinput">
						<option value="0"
							<c:if test="${info.publishStatus eq 0 }">selected="selected"</c:if>>否</option>
						<option value="1"
							<c:if test="${info.publishStatus eq 1 }">selected="selected"</c:if>>是</option>
				</select><i></i></li>
				<li><label style="width: 150px;">&nbsp;</label><input name=""
					type="submit" class="btn" value="确认保存" /> &nbsp;&nbsp;<input
					name="" type="button" onclick="goback();" class="btn" value="返回列表" /></li>

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

