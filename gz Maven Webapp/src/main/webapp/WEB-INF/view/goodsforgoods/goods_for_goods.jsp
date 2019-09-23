<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
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
	<link href="${ctx}/view/css/css.css" type="text/css" rel="stylesheet" />

   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link href="${ctx}/view/css/pager.css" type="text/css" rel="stylesheet" />
	






</head>
<body>
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
			  	<form name="dcform" method="post" id="form" action="yz_goods.do">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	佳能达商品内码：<input type="text" name="goods_condition" value="${goods_condition.jnd_id}"  />
					    	佳能达商品名称：<input type="text" name="jnd_goods_name" value="${goods_condition.jnd_goods_name}"  />
					    	 <input type="submit" value="查询"/>
					    	 <input id="delete" type="button" value="删除"/>
					    	 
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
         	
	        <td>佳能达商品内码</td>                    
            <td>佳能达商品名称</td>
            <td>云中商品编码</td>
            <td>云中商品名称 </td>
            <td>东昌商品编码</td>
            <td>东昌商品名称</td>


		 
			</tr>
			<c:forEach items="${requestScope.yz_goods}" var="yz_goods" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
			<td><input type="checkbox" id="box_${stat.index}" value="${dept.id}"></td>	
		    <td>${goods_for_goods.jnd_id              }</td>
            <td>${goods_for_goods.jnd_goods_name          }</td>
            <td>${goods_for_goods.yz_id       }</td>
            <td>${goods_for_goods.yz_goods_name            }</td>
            <td>${goods_for_goods.dc_id      }</td>
            <td>${goods_for_goods.dc_goods_name      }</td>
  
            	 <td align="center" width="40px;"><a href="${ctx}/dept/updateDept?flag=1&id=${dept.id}">
							<img title="修改" src="${ctx}/images/update.gif"/></a>
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