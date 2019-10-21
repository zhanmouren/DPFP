<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath()+"/jsp";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${path}/static/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${path }/static/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>单位资料列表</title>
</head>
<body>
<article class="page-container">
	<form action=""  class="form form-horizontal" id="form-member-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单位地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="addr" name="addr">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>增值税号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="tax_num" name="tax_num">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系电话：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="link_tel" name="link_tel">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>银行名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="bank_name" name="bank_name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>银行帐号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="bank_num" name="bank_num">
			</div>
		</div>		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="button" onClick="addAjax()" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>
<script type="text/javascript" src="${path}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${path}/static/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="${path}/static/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="${path}/static/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${path}/static/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${path}/static/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${path}/static/lib/jquery.validation/1.14.0/messages_zh.min.js"></script> 
<script type="text/javascript" src="${path}/static/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${path}/static/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${path}/static/js/H-ui.js"></script> 
<script type="text/javascript" src="${path}/static/js/H-ui.admin.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").validate({
		rules:{
			username:{
				required:true,
				minlength:2,
				maxlength:16
			},
			sex:{
				required:true,
			},
			mobile:{
				required:true,
				isMobile:true,
			},
			email:{
				required:true,
				email:true,
			},
			uploadfile:{
				required:true,
			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			//$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});

function addAjax(){
	var name = document.getElementById("name").value; 
	var addr = document.getElementById("addr").value; 
	var taxnum = document.getElementById("tax_num").value; 
	var linktel= document.getElementById("link_tel").value; 
	var bankname = document.getElementById("bank_name").value; 
	var banknum = document.getElementById("bank_num").value; 
	var data={ctm_name:name,ctm_addr:addr,tax_num:taxnum,link_tel:linktel,bank_name:bankname,bank_num:banknum};
    var result = JSON.stringify(data);
			  $.ajax({
					type: "POST",
					url: "http://localhost:8080/unit/add",
					data: result,
					dataType : "json",
					contentType: 'application/json',
					success: function(data){
						if(data.code=="0"){
							layer.msg(data.message, {icon: 1,time:2000});
							window.parent.location.reload();
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);						
						}else{
							layer.msg(data.message, {icon: 2,time:2000});
						}				
					},
					error: function(data){
					    layer.msg('增加失败!', {icon: 2,time:2000});
		}
	});   
				
}

</script> 
</body>

</html>