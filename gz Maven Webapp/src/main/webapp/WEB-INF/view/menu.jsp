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
				<li><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='yz_overview.do';  frame.location.reload(); " >
云中
</a>
				
				</span>
					<ul>
						<li><span class="file">
						
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='yz_overview.do';  frame.location.reload(); " >
商品
</a>
						
						</span></li>
					</ul>
				</li>
				<li><span class="folder">
				
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='dc_overview.do';  frame.location.reload(); " >
东昌
</a>
				
				</span>
					<ul>
						<li><span class="folder">Subfolder 2.1</span>
							<ul id="folder21">
								<li><span class="file">
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='dc_goods.do';  frame.location.reload(); " >
商品
</a>
								
								</span></li>
								<li><span class="file">File 2.1.2</span></li>
							</ul>
						</li>
						<li><span class="folder">Subfolder 2.2</span>
							<ul>
								<li><span class="file">File 2.2.1</span></li>
								<li><span class="file">File 2.2.2</span></li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="closed"><span class="folder">Folder 3 (closed at start)</span>
					<ul>
						<li><span class="file">File 3.1</span></li>
					</ul>
				</li>
				<li><span class="file">
				
				
<a href="javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('rightFrame'); frame.src='price.do';  frame.location.reload(); " >
价格对比
</a>
				
				
				</span></li>
			</ul>
		</li>
	</ul>

 

	

</div>


</body>
</html>