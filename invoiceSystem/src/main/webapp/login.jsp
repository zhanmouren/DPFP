<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="static/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="static/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="static/css/style.css" rel="stylesheet" type="text/css" />
<link href="static/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>发票云开票系统</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal"  method="post">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="username" name=loginName type="text"  placeholder="账户" class="input-text size-L"> 
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="密码"  class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
       <div class="formControls col-xs-8 col-xs-offset-3">
        <font color="red" id="errorTip"></font>
        </div>
        </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="button" class="btn btn-success radius size-L" onClick="loginAjax()"  value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright © 2018 - 2028 Koron Inc. All Rights Reserved. Powered by GDH</div>
<script type="text/javascript" src="static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/js/H-ui.js"></script> 
<script>
function loginAjax(){
	 $("#errorTip").load(location.href+" #errorTip"); 
    var logonname = document.getElementById("username").value; 
    var paw = document.getElementById("password").value; 
    var data={loginName:logonname,password:paw};
  $.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8", 
		url: "http://localhost:8080/login",
		data: JSON.stringify(data),
		success: function(data){
			if(data.code=="0"){
				window.location.href="index.jsp";
			}else{
				$("#errorTip").append(data.message);
				}
			
		},
		error: function(result) {
		   alert(result.message)
		}
	});

}
 		

</script>
</body>

</html>