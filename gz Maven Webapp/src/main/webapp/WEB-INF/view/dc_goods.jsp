<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>东昌品种</title>
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



	<script  >


		//在页面未加载完毕之前显示的loading Html自定义内容
		var _LoadingHtml = '<div id="loadingDiv" style="display: none; "><div id="over" style=" position: absolute;top: 0;left: 0; width: 100%;height: 100%; background-color: #f5f5f5;opacity:0.5;z-index: 1000;"></div><div id="layout" style="position: absolute;top: 40%; left: 40%;width: 20%; height: 20%;  z-index: 1001;text-align:center;"><img src="./images/timg.gif" /></div></div>';
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







<script  >
$(function () {
$("#begin_date").datepicker();
$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});
jQuery(function ($) {
$.datepicker.regional['zh-CN'] = {
closeText: '关闭',
prevText: '<上月',
nextText: '下月>',
currentText: '今天',
monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
'七月', '八月', '九月', '十月', '十一月', '十二月'],
monthNamesShort: ['一', '二', '三', '四', '五', '六',
'七', '八', '九', '十', '十一', '十二'],
dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
weekHeader: '周',
dateFormat: 'yy-mm-dd',
firstDay: 1,
isRTL: false,
showMonthAfterYear: true,
yearSuffix: '年'
};
$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});
</script>


<script >
$(function () {
$("#end_date").datepicker();
$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});
jQuery(function ($) {
$.datepicker.regional['zh-CN'] = {
closeText: '关闭',
prevText: '<上月',
nextText: '下月>',
currentText: '今天',
monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
'七月', '八月', '九月', '十月', '十一月', '十二月'],
monthNamesShort: ['一', '二', '三', '四', '五', '六',
'七', '八', '九', '十', '十一', '十二'],
dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
weekHeader: '周',
dateFormat: 'yy-mm-dd',
firstDay: 1,
isRTL: false,
showMonthAfterYear: true,
yearSuffix: '年'
};
$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});
</script>



</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"></td>
		<td class="main_locbg font2">&nbsp;&nbsp;&nbsp;当前位置：东昌品种 &gt; 品种查询</td>
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
			  	<form name="dcform" method="post" id="form" action="dc_goods.do">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	开始日期：<input type="text" id="begin_date" name="begin_date" value="${goods_con.begin_date}"  />
					    	结束日期：<input type="text" id="end_date"   name="end_date" value="${goods_con.end_date}" />
					    	商品编码：<input type="text" name="goods_id" value="${goods_con.goods_id}"  />
					    	商品名称：<input type="text" name="goods_name" value="${goods_con.goods_name}"  />
					    	 <input type="submit" value="查询"/>
					    	 
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
 
	        <td>日期</td>                    
            <td>商品编码</td>
            <td>商品名称</td>
            <td>价格    </td>
            <td>活动类型</td>
            <td>活动详情</td>
            <td>效期    </td>
            <td>规格    </td>
            <td>单位    </td>
            <td>生产厂家</td>
            <td>件装量  </td>
            <td>销售单位</td>
            <td>库存    </td>

		 
			</tr>
			<c:forEach items="${requestScope.dc_goods}" var="dc_goods" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">
				
		    <td>${dc_goods.date              }</td>
            <td>${dc_goods.goods_id          }</td>
            <td>${dc_goods.goods_name        }</td>
            <td>${dc_goods.price             }</td>
            <td>${dc_goods.active_type       }</td>
            <td>${dc_goods.active_name       }</td>
            <td>${dc_goods.expire            }</td>
            <td>${dc_goods.spec              }</td>
            <td>${dc_goods.unit              }</td>
            <td>${dc_goods.producer          }</td>
            <td>${dc_goods.loads             }</td>
            <td>${dc_goods.sale_unit         }</td>
            <td>${dc_goods.stock             }</td>
             

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
	  	        submitUrl="${ctx}/dc_goods.do?pageIndex"/>
	  	        
	  </td></tr>
	  

	  
	</table>
	<div style="height:10px;"></div>
</body>
</html>