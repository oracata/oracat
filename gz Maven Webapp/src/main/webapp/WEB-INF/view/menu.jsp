<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link rel="stylesheet" href="https://static.runoob.com/assets/js/jquery-treeview/jquery.treeview.css" />
	<link rel="stylesheet" href="https://static.runoob.com/assets/js/jquery-treeview/screen.css" />

	<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="https://static.runoob.com/assets/js/jquery-treeview/jquery.cookie.js"></script>
	<script src="https://static.runoob.com/assets/js/jquery-treeview/jquery.treeview.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(document).ready(function(){
		$("#browser").treeview({
		    //prerendered: true
			toggle: function() {
				console.log("%s was toggled.", $(this).find(">span").text());
			}
		});

 
	});
	</script>


</head>



<body>




	<div id="main">

	

	<ul id="browser" class="filetree treeview-famfamfam">
		<li><span class="folder">
		
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='realtime.do';  frame.location.reload(); " >
实时
</a>
		
		</span>
			<ul>
				<li class="closed"><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='overview.do';  frame.location.reload(); " >
云中
</a>
				
				</span>
					<ul>
						<li><span class="file">
						
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='yz_goods.do';  frame.location.reload();" >
商品
</a>
						
						</span></li>
					</ul>
				</li>
				
				<li class="closed"><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript:   window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='404.html';  frame.location.reload();   " >
东昌
</a>
				
				</span>
					<ul>

								<li><span class="file">
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='dc_goods.do';  frame.location.reload(); " >
商品
</a>
								
								</span></li>
							 
							</ul>
						</li>
					 
	
				<li class="closed"><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='404.html';  frame.location.reload();   " >
对比分析
</a>				
				
				</span>
					<ul>
						<li><span class="file">
						
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='404.html';  frame.location.reload();   " >
价格对比
</a>						
						
						</span></li>



						<li><span class="file">
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='b2bprice.do';  frame.location.reload();  " >
电商价格
</a></span></li>



						
					</ul>
				</li>
				
				
		<li class="closed"><span class="folder">




				<li class="closed"><span class="folder">

<a href="javascript:void(0);" onclick="javascript:  window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='404.html';  frame.location.reload();   " >
报表
</a>
				</span>
					<ul>
						<li><span class="file">

<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='reportday.do';  frame.location.reload(); " >
日报
</a>
						</span></li>



						<li><span class="file">
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='404.html';  frame.location.reload();  " >
月报
</a></span></li>



						<li><span class="file">
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='reportyear.do';  frame.location.reload();  " >
年报
</a></span></li>






					</ul>
				</li>


				<li class="closed"><span class="folder">





<a href="javascript:void(0);" onclick="javascript:window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='404.html';  frame.location.reload();  " >
基础资料
</a>
				
				</span>
					<ul>
						<li><span class="file">
						
<a href="javascript:void(0);" onclick="javascript:window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='goodsforgoods/goodsforgoods';  frame.location.reload();  " >
云中商品对应关系
</a>
						
						</span></li>
					</ul>
				</li>			
 
			</ul>
		</li>



		<li class="closed"><span class="folder">

<a href="javascript:void(0);" onclick="javascript:  if (top.location != self.location){     top.location=self.location;  window.parent.location.href='loginform.do'   }    " >
注销
</a>
		</span></li>


	</ul>

 

	

</div>


</body>
</html>