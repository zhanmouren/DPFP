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
  <form  name="frm" >
<div class="page-container">
	 <div class="text-c"> 		
		<input type="text" value="" name="ctmnum" id="ctmnum" placeholder="请输入户号" style="width:250px" class="input-text">
		<input type="text" value="0" name="zzflag" id="zzflag" style="display:none"  class="input-text">
		<button name="Search" id="Search" onclick="findUserAjax()" class="btn btn-primary radius"  type="button"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
	</div> 
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 	
	 <span class="r">共有数据：<strong id="count"></strong> 条
	 </span>
	  </div>
	  <div id="filename" style="line-height:70px"></div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover" id="table">
			<thead>
				<tr class="text-c">
					<th width="100">户号</th>
					<th width="150">户名</th>
					<th width="180">地址</th>				
					<th width="30">操作</th>
				</tr>
			</thead>
			
<tbody id="tbody"> </tbody>		
			
		</table>
	</div>
</div>
 </form>
<script type="text/javascript" src="${path}/static/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${path}/static/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="${path}/static/lib/laypage/1.2/laypage.js"></script> 
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

$("document").ready(function(){  
	$("#btn1").click(function(){  
	$("[name='box']").attr("checked",'true');//全选  
})
});
$("#count").append("0");


/*查询用户*/
function findUserAjax(){
	 var num = document.getElementById("ctmnum").value; 
	 if(num==""||num==null){
		 layer.msg("请输入户号!", {time:2000});
	 }else{
		 $("#table").load(location.href+" #table"); 
		 //$("#table tbody").html("");
	     $("#count").load(location.href+" #count"); //重新提交刷新table
 	    var data={ctmnum:num};
 	    $.ajax({
 			type: "POST",
 			contentType: "application/json; charset=utf-8", 
 			url: "http://localhost:8080/unit/user/findCtmNum",
 			data: JSON.stringify(data),
 			success: function(data){
 				var tempHtml = "";
 				if(data.code=="0"){
 	 				if(data.data[0]["user_type"]==1){
 	 					for(var i = 0; i< data.data.length; i++)
 	                     { 
 	                          tempHtml += "<tr class='text-c'>";
 	                          tempHtml += "<td>"+data.data[i]["ctm_num"]+"</td>";
 	                          tempHtml += "<td>"+data.data[i]["ctm_name"]+"</td>";
 	                          tempHtml += "<td>"+data.data[i]["ctm_addr"]+"</td>";
 	                          tempHtml += "<td><a style='text-decoration:none' class='ml-5' onClick='user("+data.data[i]["ctm_num"]+")' href='javascript:;' title='审核'>审核</a>	</td></tr>";        
 	                     }

 	 	 				}else{
 	 	 	 				
 					for(var i = 0; i< data.data.length; i++)
                     { 
                          tempHtml += "<tr class='text-c'>";
                          tempHtml += "<td>"+data.data[i]["ctm_num"]+"</td>";
                          tempHtml += "<td>"+data.data[i]["ctm_name"]+"</td>";
                          tempHtml += "<td>"+data.data[i]["ctm_addr"]+"</td>";
                          tempHtml += "<td><a style='text-decoration:none' class='ml-5' onClick='user_add("+data.data[i]["ctm_num"]+")' href='javascript:;' title='添加'>添加</a>	</td></tr>";        
                     }
 	 	 			}
 					$("#tbody").append(tempHtml); 
 					$("#count").append(data.data.length);
 				}else{
 					layer.msg(data.message, {time:2000});
 					setTimeout('refresh()',2000);
 					/*  layer.msg('大部分参数都是可以公用的<br>合理搭配，展示不一样的风格', {
 				        time: 20000, //20s后自动关闭
 				        btn: ['明白了', '知道了']
 				      }); */
 				}
 				
 			},
 			error: function(result) {
 			   alert("出错了，"+result.message)
 			}
 		});

		 
	}

}

/* 自动刷新页面 */
function refresh()
{
   window.location.reload();
}


function user(ctmnum){
	layer.confirm('该户为普通用户，是否提交申请？',function(index){
		layer.msg("已提交申请，耐心等待审核!", {icon: 1,time:2000});
	});  

	
}

/*增加用户*/
function user_add(ctmnum){
	var jsondata={'unit_id':<%=request.getParameter("unit_id") %>,'ctmnum':ctmnum}
	var result = JSON.stringify(jsondata);
	layer.confirm('确认要添加该用户吗？',function(index){
		  $.ajax({
				type: "POST",
				url: "http://localhost:8080/unit/user/addUser",
				data: result,
				dataType : "json",
				contentType: 'application/json',
				success: function(data){
					if(data.code=="0"){					
						layer.msg("已添加!", {icon: 1,time:2000});
					}else{		
						layer.msg(data.message, {icon: 2,time:2000});
					}				
				},
				error: function(data){
				    layer.msg('添加失败!', {icon: 2,time:2000});
	}
			});  
	});
}


/*删除*/
function unit_del(obj,id){
	//alert(id.length);
	var jsondata={'b_id':id}
	var result = JSON.stringify(jsondata);
	layer.confirm('确认要删除吗？',function(index){
		  $.ajax({
				type: "POST",
				url: "http://localhost:8080/unit/delete",
				data: result,
				dataType : "json",
				contentType: 'application/json',
				success: function(data){
					if(data.code=="0"){
						if(obj!=0){
							$(obj).parents("tr").remove();
						}else{
							checkAjax();
						}	
						layer.msg("已删除!", {icon: 1,time:2000});
					}else{
						layer.msg(data.message, {icon: 2,time:2000});
					}				
				},
				error: function(data){
				    layer.msg('删除失败!', {icon: 2,time:2000});
	}
			});  
	});
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
function invoice_down(url) {
    if(url=='') {
          layer.msg('请先生成电子发票！', {icon: 2,time:2000});
          return;
     }else{
    	 window.open(url);
     }
    
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

/*用户-编辑*/
function user_edit(abj){
	layer_show('编辑单位用户','invoice_list.jsp','750','550','150');
	
}

/*单位资料-编辑*/
function unit_edit(id){
	var jsondata={'b_id':id}
	var result = JSON.stringify(jsondata);
		  $.ajax({
				type: "POST",
				url: "http://localhost:8080/unit/findbyid",
				data: result,
				dataType : "json",
				contentType: 'application/json',
				success: function(data){
					if(data.code=="0"){
						layer_show('编辑单位资料','unit_edit.jsp?b_id='+id+'&name='+data.data.ctm_name+'&addr='+data.data.ctm_addr+'&tax_num='+data.data.tax_num+'&link_tel='+data.data.link_tel+'&bank_name='+data.data.bank_name+'&bank_num='+data.data.bank_num,'850','500','150');					
					}else{
						layer.msg(data.message, {icon: 2,time:2000});
					}				
				},
				error: function(data){
				    layer.msg('开票失败!', {icon: 2,time:2000});
	}
			});   
	
}

/*单位资料-删除*/
/* function unit_del(id){
	var jsondata={'b_id':id}
	var result = JSON.stringify(jsondata);
	layer.confirm('确认要删除吗？',function(index){

		  $.ajax({
				type: "POST",
				url: "http://localhost:8080/unit/deletebyid",
				data: result,
				dataType : "json",
				contentType: 'application/json',
				success: function(data){
					if(data.code=="0"){
						$(obj).parents("tr").remove();
						layer.msg('已删除!',id);
					}else{
						layer.msg(data.message, {icon: 2,time:2000});
					}				
				},
				error: function(data){
				    layer.msg('删除失败!', {icon: 2,time:2000});
	}
			});   
	      });
	
} */

/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script> 
</body>

</html>