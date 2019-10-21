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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="${path}/static/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="${path }/static/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${path }/static/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>专用发票</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 发票开具 <span class="c-gray en">&gt;</span> 专用发票 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
  <form  name="frm" action="${pageContext.request.contextPath}/findbill"  method="post">
<div class="page-container">
	<div class="text-c"> 年月：
		<input type="text" value="201808"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y%M\'}'})" id="logmin" name="month_begin" class="input-text Wdate" style="width:90px;">
		-
		<input type="text" value="201903"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y%M'})" id="logmax" name="month_end" class="input-text Wdate" style="width:90px;">
		<input type="text" value="205809" name="ctmnum" id="ctmnum" placeholder=" 户号\户名\水表号" style="width:250px" class="input-text">
		<input type="text" value="1" name="zzflag" id="zzflag" style="display:none"  class="input-text">
		<button name="submit1" id="" onclick="check()" class="btn btn-success"  type="submit"><i class="Hui-iconfont">&#xe665;</i> 查 询</button>
		<button name="submit1" id="" onclick="taxHome()" class="btn btn-success"  type="submit"> 进入税务系统</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
<span class="l">专用发票库存剩余：<font size="3" color="red">20</font>张
	 </span>
	 <span class="r">共有数据：<strong>1</strong> 条
	 </span>
	  </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">户号</th>
					<th width="130">户名</th>
					<th width="50">年月</th>
					<th width="50">水量</th>
					<th width="80">水费</th>
					<th width="65">污水费</th>
					<th width="65">垃圾费</th>
					<th width="55">违约金</th>
					<th width="65">发票金额</th>
					<th width="75">发票号码</th>
					<th width="60">状态</th>
					<th width="110">操作</th>
				</tr>
			</thead>
			<tbody>
			<tr class="text-c">
					<td>A08816</td>
					<td>李四</td>
					<td>201907</td>
					<td>38</td>
					<td>120</td>
					<td>40</td>
					<td>10</td>
					<td>0</td>
					<td>120</td>
					<td>823482333</td>  					
                     <td class="td-status"><span class="label label-success radius">已开票</span></td> 
         
					<td class="f-14 td-manage">
					<a style="text-decoration:none" class="ml-5" onClick="invoice_open('',1)" href="javascript:;" title="推送">推送</a>
					<a style="text-decoration:none" class="ml-5" onClick="invoice_withdraw('')" href="javascript:;" title="撤回">撤回</a>				
					<a style="text-decoration:none" class="ml-5" onClick="invoice_cancel('',-1)" href="javascript:;" title="作废">作废</a></td>
				</tr>
			<%-- <c:if test="${!empty invoice }">
		       <c:forEach items="${invoice}" var="list">
				<tr class="text-c">
					<td>${list.ctmnum}</td>
					<td>${list.ctmname}</td>
					<td>${list.month}</td>
					<td>${list.waternum}</td>
					<td>${list.sf}</td>
					<td>${list.wsf}</td>
					<td>${list.ljf}</td>
					<td>${list.wyj}</td>
					<td>${list.invoice_money}</td>
					<td>${list.invoice_num}</td>  					
                   <c:if test="${list.invoice_num!=null}">
                        <td class="td-status"><span class="label label-success radius">已开票</span></td>
                  </c:if>  
                  <c:if test="${list.invoice_num==null && list.invoice_lsh == null}">
                        <td class="td-status"><span class="label label-danger radius">未开票</span></td>
                  </c:if> 
                  <c:if test="${list.invoice_num==null && list.invoice_lsh != null}">
                        <td class="td-status"><span class="label label-warning radius">待开票</span></td>
                  </c:if>           
					<td class="f-14 td-manage">
					<a style="text-decoration:none" class="ml-5" onClick="invoice_open('${list.listids}',1)" href="javascript:;" title="推送">推送</a>
					<a style="text-decoration:none" class="ml-5" onClick="invoice_withdraw('${list.invoice_url}')" href="javascript:;" title="撤回">撤回</a>				
					<a style="text-decoration:none" class="ml-5" onClick="invoice_cancel('${list.listids}',-1)" href="javascript:;" title="作废">作废</a></td>
				</tr>
				</c:forEach>
			</c:if> --%>
<!-- 				<tr class="text-c">
					<td>10002</td>
					<td class="text-l"><u style="cursor:pointer" class="text-primary" onClick="article_edit('查看','article-zhang.html','10002')" title="查看">李四</u></td>
					<td>211811</td>
					<td>23</td>
					<td>157.3</td>
					<td>29.58</td>
					<td>18.5</td>
					<td>0</td>
					<td>208.75</td>
					<td></td>
					<td class="td-status"><span class="label label-danger radius">未开票</span></td>
					<td class="f-14 td-manage"><a style="text-decoration:none" class="ml-5" onClick="article_edit('资讯编辑','article-add.html','10001')" href="javascript:;" title="生成">查看</a><a style="text-decoration:none" class="ml-5" onClick="article_edit('资讯编辑','article-add.html','10001')" href="javascript:;" title="生成">生成</a> <a style="text-decoration:none" class="ml-5" onClick="article_del(this,'10001')" href="javascript:;" title="作废">作废</a></td>
				</tr> -->
			</tbody>
		</table>
	</div>
</div>
 </form>
<script type="text/javascript" src="${path}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${path}/static/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="${path}/static/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${path}/static/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${path}/static/js/H-ui.js"></script> 
<script type="text/javascript" src="${path}/static/js/H-ui.admin.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,8]}// 不参与排序的列
	]
});

function taxHome(){
	window.open("http://192.168.4.202/etcloud/etcloud-sys/html/login/password_free_porxy.html?111");	
}

function check() {
     if(frm.ctmnum.value=='') {
           alert("请输入用户帐号!");
           return;
      }
     if(frm.month_begin.value=='') {
         alert("请选择开始年月!");
         return;
     }
     if(frm.month_end.value=='') {
         alert("请输入结束年月!");
         return;
    }
		frm.submit();
   }




/*资讯-添加*/
function article_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*下载发票*/
function invoice_withdraw(url) {
    if(url=='') {
          layer.msg('请先生成电子发票！', {icon: 2,time:2000});
          return;
     }else{
    	 window.open(url);
     }
    
  }
/*推送*/
function invoice_open(listids,type){
	var jsondata={'listids':listids,'billFlag':type}
	var result = JSON.stringify(jsondata);
	layer.confirm('确认要推送吗？',function(index){	
		  $.ajax({
				type: "POST",
				url: "http://localhost:8080/invoiceDemo/earmark",
				data: result,
				dataType : "json",
				contentType: 'application/json',
				success: function(data){
					if(data.code=="0"){
						layer.msg('推送成功!即将进入E税云系统...', {icon: 1,time:2000});
						setTimeout("check()",1000);	
					}else{
						layer.msg(data.message, {icon: 2,time:2000});
					}				
				},
				error: function(data){
				    layer.msg('推送失败!', {icon: 2,time:2000});
	}
			});   
	});
}

/*红冲*/
function invoice_cancel(listids,type){
	var jsondata={'listids':listids,'billFlag':type}
	var result = JSON.stringify(jsondata);
	layer.confirm('确认要作废吗？',function(index){	
		  $.ajax({
				type: "POST",
				url: "http://localhost:8080/invoiceDemo/electronic",
				data: result,
				dataType : "json",
				contentType: 'application/json',
				success: function(data){
					if(data.code=="0"){
						layer.msg('作废成功!', {icon: 1,time:2000});
						setTimeout("check()",1000);
					}else{
						layer.msg('作废失败!原因：'+data.message, {icon: 2,time:2000});
						setTimeout("check()",3000);
					}										
				},
				error: function(data){
				layer.msg('作废失败!', {icon: 2,time:2000});
	}
			});   
	});
}
/*资讯-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}
/*资讯-审核*/
function article_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}
/*资讯-下架*/
function article_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
		$(obj).remove();
		layer.msg('已下架!',{icon: 5,time:1000});
	});
}


/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script> 
</body>

</html>