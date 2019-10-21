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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 单位资料管理 <span class="c-gray en">&gt;</span> 单位资料列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
  <form  name="frm" >
<div class="page-container">

	<div class="text-c"> 		
		<input type="text" value="" name="ctmnum" id="ctmnum" placeholder=" 名称\地址\电话\税号" style="width:250px" class="input-text">
		<input type="text" value="0" name="zzflag" id="zzflag" style="display:none"  class="input-text">
		<button name="Search" id="Search" onclick="checkAjax()" class="btn btn-success"  type="button"><i class="Hui-iconfont">&#xe665;</i> 查 询</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	<span class="l"><a href="javascript:;" onclick="unit_dels()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>  
	<a href="javascript:;" onclick="unit_add('添加','unit_add.jsp','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a></span>
	 <span class="r">共有数据：<strong id="count"></strong> 条
	 </span>
	  </div>
	  <div id="filename" style="line-height:70px"></div>
	<div class="mt-20" id="tab">
		<table class="table table-border table-bordered table-bg table-hover" id="table">
			<thead>
				<tr class="text-c">
				    <th width="25"><input type="checkbox" name="btn1" id="btn1" value=""></th>
					<th width="70">单位号</th>
					<th width="120">单位名称</th>
					<th width="50">单位地址</th>
					<th width="50">税号</th>
					<th width="80">电话</th>
					<th width="65">银行名称</th>
					<th width="55">银行帐号</th>	
					<th width="55">户数</th>
					<th width="110">操作</th>
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
$(document).ready(function(e) {
	$('#Search').click();
});

$("document").ready(function(){  
	$("#btn1").click(function(){  
	$("[name='box']").attr("checked",'true');//全选  
})
});


/*添加*/
function unit_add(title,url,w,h){
	layer_show(title,url,w,h);
}

function uploadfile(){
    $("#choosefile").click();
    
    //jquery获取input file文件名
    var file = $("#choosefile").val();
    alert(file);
    var fileName = getFileName(file);
    alert(fileName);
    $("#filename").html(fileName);
}


     function checkAjax(){    	   
   	        $("#table").load(location.href+" #table"); 
   	        $("#count").load(location.href+" #count"); //重新提交刷新table
    	    var queryInfo = document.getElementById("ctmnum").value; 
    	    var data={'queryInfo':queryInfo};
    	  $.ajax({
    			type: "POST",
    			contentType: "application/json; charset=utf-8", 
    			url: "http://localhost:8080/unit/findAll",
    			data: JSON.stringify(data),
    			success: function(data){
    				var tempHtml = "";
    				if(data.code=="0"){
    					for(var i = 0; i< data.data.length; i++)
                        { 
                             tempHtml += "<tr class='text-c'><td><input type='checkbox' value='"+data.data[i]["b_id"]+"' name='box'></td>";
                             tempHtml += "<td>"+data.data[i]["b_id"]+"</td>";
                             tempHtml += "<td>"+data.data[i]["ctm_name"]+"</td>";
                             tempHtml += "<td>"+data.data[i]["ctm_addr"]+"</td>";
                             tempHtml += "<td>"+data.data[i]["tax_num"]+"</td>";
                             tempHtml += "<td>"+data.data[i]["link_tel"]+"</td>";
                             tempHtml += "<td>"+data.data[i]["bank_name"]+"</td>";
                             tempHtml += "<td>"+data.data[i]["bank_num"]+"</td>";
                             if(data.data[i]["total"]>0){
                            	 tempHtml += "<td><a style='text-decoration:none' class='ml-5'  onClick='user_edit("+data.data[i]["b_id"]+")' href='javascript:;'>"+data.data[i]["total"]+"</a></td>";  
                             }else{
                            	 tempHtml += "<td><a style='text-decoration:none' class='ml-5'  onClick='user_edit("+data.data[i]["b_id"]+")' href='javascript:;'>0</a></td>";  
                             }     
                             tempHtml += "<td class='f-14 td-manage'><a style='text-decoration:none' class='ml-5' onClick='unit_edit("+data.data[i]["b_id"]+")' href='javascript:;' title='编辑'>编辑</a>";  
                             tempHtml += "<a style='text-decoration:none' class='ml-5' onClick='unit_del(this,["+data.data[i]["b_id"]+"])' href='javascript:;' title='删除'>删除</a>	</td></tr>";        
                        }
    					$("#tbody").append(tempHtml); 
    					$("#count").append(data.data.length);
    				}else{
    	                alert("查询失败");
    					}
    				
    			},
    			error: function(result) {
    			   alert(result.message)
    			}
    		});
    
    	}


    function chk(){    
    	  var chk_value =[];    
    	  $('input[name="box"]:checked').each(function(){    
    	   chk_value.push($(this).val());    
    	  });    
    	  alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value);    
    }   
    
/*批量删除*/
function unit_dels(){
    	  var chk_id =[];    
    	  $('input[name="box"]:checked').each(function(){    
    	   chk_id.push($(this).val());    
    	  });    
    	  //alert(chk_id.length==0 ?'你还没有选择任何内容！':chk_id);   
    	  unit_del(0,chk_id);     
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
function user_edit(id){
	layer_show('编辑单位用户','unitUser_list.jsp?id='+id,'950','550','150');
	
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

/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script> 
</body>

</html>