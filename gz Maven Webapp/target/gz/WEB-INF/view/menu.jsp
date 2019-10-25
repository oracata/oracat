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
		
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='overview.do';  frame.location.reload(); " >
概览
</a>
		
		</span>
			<ul>
				<li class="closed"><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='yz_overview.do';  frame.location.reload(); " >
云中
</a>
				
				</span>
					<ul>
						<li><span class="file">
						
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='yz_goods.do';  frame.location.reload(); " >
商品
</a>
						
						</span></li>
					</ul>
				</li>
				
				<li class="closed"><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='dc_overview.do';  frame.location.reload(); " >
东昌
</a>
				
				</span>
					<ul>

								<li><span class="file">
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='dc_goods.do';  frame.location.reload(); " >
商品
</a>
								
								</span></li>
							 
							</ul>
						</li>
					 
	
				<li class="closed"><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='price.do';  frame.location.reload(); " >
对比分析
</a>				
				
				</span>
					<ul>
						<li><span class="file">
						
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='price.do';  frame.location.reload(); " >
价格对比
</a>						
						
						</span></li>
						
						


						
					</ul>
				</li>
				
				
		<li class="closed"><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='yz_overview.do';  frame.location.reload(); " >
配置
</a>
				
				</span>
					<ul>
						<li><span class="file">
						
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='goods_for_goods.do';  frame.location.reload(); " >
商品对应关系维护
</a>
						
						</span></li>
					</ul>
				</li>			
 
			</ul>
		</li>
	</ul>

 

	

</div>


</body>
</html>