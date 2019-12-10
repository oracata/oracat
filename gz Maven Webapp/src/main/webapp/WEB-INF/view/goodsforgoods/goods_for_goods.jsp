
<%@ page import="com.oracat.model.GoodsForYz" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%  	int num=0;
	//遍历List 取得记录数
	if(request.getAttribute("goodsforyz")!=null) {
		Object re = request.getAttribute("goodsforyz");
		List<GoodsForYz> ol = (List) re;
		  num = ol.size();
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>商品对应关系维护</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="../css/css.css" type="text/css" rel="stylesheet" />


	<link href="../css/pager.css" type="text/css" rel="stylesheet" />




	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>



	<script  >


		//在页面未加载完毕之前显示的loading Html自定义内容
		var _LoadingHtml = '<div id="loadingDiv" style="display: none; "><div id="over" style=" position: absolute;top: 0;left: 0; width: 100%;height: 100%; background-color: #f5f5f5;opacity:0.5;z-index: 1000;"></div><div id="layout" style="position: absolute;top: 40%; left: 40%;width: 20%; height: 20%;  z-index: 1001;text-align:center;"><img src="../images/timg.gif" /></div></div>';
		//呈现loading效果
		document.write(_LoadingHtml);

		//移除loading效果
		function completeLoading() {
			document.getElementById("loadingDiv").style.display="none";
		}
		//展示loading效果http://localhost:2006/
		function showLoading()
		{
			document.getElementById("loadingDiv").style.display="block";
		}



		$(document).ready(function(){
					//任选以form结束的form进行处理
					$("form[id$='form']").submit(function(){
						showLoading();
						return true;
					});

					//导出excel
					$("a[id$='export']").click(function(){
						showLoading();
						return true;
					});

				}

		);







	</script>





	<script type="text/javascript">
		$(function(){

			/** 增加记录绑定点击事件 */
			$("#add").click(function(){

							window.location = "${ctx }/goodsforgoods/addGoodsForYz?flag=1";

			})



			/** 获取上一次选中的部门数据 */
			var boxs  = $("input[type='checkbox'][id^='box_']");

			/** 给全选按钮绑定点击事件  */
			$("#checkAll").click(function(){
				// this是checkAll  this.checked是true
				// 所有数据行的选中状态与全选的状态一致
				boxs.attr("checked",this.checked);
			})

			/** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
			$("tr[id^='data_']").hover(function(){
				$(this).css("backgroundColor","#2ec2ff");
			},function(){
				$(this).css("backgroundColor","#ffffff");
			})


			/** 删除记录绑定点击事件 */
			$("#delete").click(function(){


				var checkedBoxs = boxs.filter(":checked");
				if(checkedBoxs.length < 1){

                   alert("请选择一个需要删除的记录！");
				}else{
					/** 得到用户选中的所有的需要删除的ids */
					var ids = checkedBoxs.map(function(){
						return this.value;
					})

                    alert("确认要删除吗?","删除记录");

							window.location = "${ctx }/goodsforgoods/deleteGoodsForYz?ids=" + ids.get();

				}
			})



		})
	</script>

	<script>
		<!-- 数据行数自适应高度 -->
		function autoheight() {
			var n = <%=num%>;
			if (Number(n) !==0) {


			$('.layui-tab-item.layui-show', parent.document).css('height', '' + Number(n) * 30 + '');
		}
		}
	</script>







</head>
<body  onload="	 autoheight()">

	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"></td>
		<td class="main_locbg font2">&nbsp;&nbsp;&nbsp;当前位置：配置 &gt; 商品对应关系维护</td>
		<td width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="goodsforgoodsform" method="get" id="goodsforgoodsform" action="goodsforgoods.do">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	佳能达商品内码：<input type="text" name="jnd_spid"  value="${goodsforyz_con.jnd_spid}"/>
							佳能达商品编码：<input type="text" name="jnd_spbm"  value="${goodsforyz_con.jnd_spbm}" />

							佳能达商品名称：<input type="text" name="jnd_spname"  value="${goodsforyz_con.jnd_spname}"  />
					    	 <input type="submit" value="查询"/>
							<td>
						  <input id="add" type="button" value="增加"/>
							<input id="delete" type="button" value="删除" align="left"/>
				     	  </td>
					    	 
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
				<td><input type="checkbox" name="checkAll" id="checkAll"></td>
				<td>佳能达商品内码</td>
				<td>佳能达商品编码</td>
            <td>佳能达商品名称</td>
            <td>云中商品编码</td>
            <td>云中商品名称 </td>
				<td>修改 </td>



		 
			</tr>

			<c:forEach items="${requestScope.goodsforyz}" var="goodsforyz" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
			<td><input type="checkbox" id="box_${stat.index}" value="${goodsforyz.jnd_spid }"></td>
		    <td>${goodsforyz.jnd_spid              }</td>
			<td>${goodsforyz.jnd_spbm              }</td>
            <td>${goodsforyz.jnd_spname          }</td>
            <td>${goodsforyz.yz_goods_id       }</td>
            <td>${goodsforyz.yz_goods_name            }</td>

  
            	 <td align="center" width="40px;"><a href="${ctx}/goodsforgoods/updateGoodsForYz?flag=1&id=${goodsforyz.jnd_spid}">
							<img title="修改" src="../images/update.gif"/></a>
					  </td> 

				</tr>

			</c:forEach>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top" align="center" ><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/yz_goods.do?pageIndex"/>
	  	        
	  </td></tr>
	  

	  
	</table>
	<div style="height:10px;"></div>
</body>
</html>