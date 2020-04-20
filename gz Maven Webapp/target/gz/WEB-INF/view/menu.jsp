<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<link rel="stylesheet" href="https://static.runoob.com/assets/js/jquery-treeview/jquery.treeview.css" />
	<link rel="stylesheet" href="https://static.runoob.com/assets/js/jquery-treeview/screen.css" />

	<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="https://static.runoob.com/assets/js/jquery-treeview/jquery.cookie.js"></script>
	<script src="https://static.runoob.com/assets/js/jquery-treeview/jquery.treeview.js" type="text/javascript"></script>


	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


	<link rel="stylesheet" href="./js/layui/css/layui.css">
	<script src="./js/layui/layui.js"/>
	<script src="./js/layui/layui.all.js"/>

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
						
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='pricepare.do';  frame.location.reload();   " >
价格对比
</a>						
						
						</span></li>



						<li><span class="file">
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='b2bprice.do';  frame.location.reload();  " >
电商价格
</a></span></li>



						<li><span class="file">
<a href="javascript:void(0);" onclick="javascript: window.parent.document.getElementById('rightFrame').contentWindow.showLoading();  var frame=window.parent.document.getElementById('rightFrame'); frame.src='pricenotin.do';  frame.location.reload();  " >
无价格待上架商品
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


						<li><span class="file">

<a href="javascript:void(0);" onclick="javascript:window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='erpcustom.do';  frame.location.reload();  " >
无资料终端开单客户
</a>

						</span></li>




						<li><span class="file">

<a href="javascript:void(0);" onclick="javascript:window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='fgscustom.do';  frame.location.reload();  " >
无资料分公司转总部客户
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




		<li class="closed"><span class="folder">

<a href="javascript:void(0);" onclick="javascript:window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='tab.do';  frame.location.reload();  " >
动态tab
</a>
		</span></li>




	</ul>

 

	

</div>



<--! test -->

	<script type="javascript">
		layui.use('element', function() {
			var $ = layui.jquery;
			var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

			//触发事件
			var active = {
				//在这里给active绑定几项事件，后面可通过active调用这些事件
				tabAdd: function(url,id,name) {
					//新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
					//关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
					element.tabAdd('demo', {
						title: name,
						content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'.html" style="width:100%;height:99%;"></iframe>',
						id: id //规定好的id
					})
					CustomRightClick(id); //给tab绑定右击事件
					FrameWH();  //计算ifram层的大小
				},
				tabChange: function(id) {
					//切换到指定Tab项
					element.tabChange('demo', id); //根据传入的id传入到指定的tab项
				},
				tabDelete: function (id) {
					element.tabDelete("demo", id);//删除
				}
				, tabDeleteAll: function (ids) {//删除所有
					$.each(ids, function (i,item) {
						element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
					})
				}
			};


			//当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
			$('.site-demo-active').on('click', function() {
				var dataid = $(this);

				//这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
				if ($(".layui-tab-title li[lay-id]").length <= 0) {
					//如果比零小，则直接打开新的tab项
					active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
				} else {
					//否则判断该tab项是否以及存在

					var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
					$.each($(".layui-tab-title li[lay-id]"), function () {
						//如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
						if ($(this).attr("lay-id") == dataid.attr("data-id")) {
							isData = true;
						}
					})
					if (isData == false) {
						//标志为false 新增一个tab项
						active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"),dataid.attr("data-title"));
					}
				}
				//最后不管是否新增tab，最后都转到要打开的选项页面上
				active.tabChange(dataid.attr("data-id"));
			});

			function CustomRightClick(id) {
				//取消右键  rightmenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
				$('.layui-tab-title li').on('contextmenu', function () { return false; })
				$('.layui-tab-title,.layui-tab-title li').click(function () {
					$('.rightmenu').hide();
				});
				//桌面点击右击
				$('.layui-tab-title li').on('contextmenu', function (e) {
					var popupmenu = $(".rightmenu");
					popupmenu.find("li").attr("data-id",id); //在右键菜单中的标签绑定id属性

					//判断右侧菜单的位置
					l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
					t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
					popupmenu.css({ left: l, top: t }).show(); //进行绝对定位
					//alert("右键菜单")
					return false;
				});
			}

			$(".rightmenu li").click(function () {

				//右键菜单中的选项被点击之后，判断type的类型，决定关闭所有还是关闭当前。
				if ($(this).attr("data-type") == "closethis") {
					//如果关闭当前，即根据显示右键菜单时所绑定的id，执行tabDelete
					active.tabDelete($(this).attr("data-id"))
				} else if ($(this).attr("data-type") == "closeall") {
					var tabtitle = $(".layui-tab-title li");
					var ids = new Array();
					$.each(tabtitle, function (i) {
						ids[i] = $(this).attr("lay-id");
					})
					//如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
					active.tabDeleteAll(ids);
				}

				$('.rightmenu').hide(); //最后再隐藏右键菜单
			})
			function FrameWH() {
				var h = $(window).height() -41- 10 - 60 -10-44 -10;
				$("iframe").css("height",h+"px");
			}

			$(window).resize(function () {
				FrameWH();
			})
		});

	</script>

	<div class="layui-tab layui-tab-card site-demo-button" style="position: relative;">
		<ul class="layui-nav layui-nav-tree layui-nav-side">
			<li class="layui-nav-item layui-nav-itemed">
				<a href="javascript:;">默认展开</a>
				<dl class="layui-nav-child">
					<dd>
						<a href="javascript:void(0);" onclick="javascript:window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='tab.do';  frame.location.reload();  " >
							测试
						</a>
					</dd>
					<dd>
						<a href="#" data-url="b" data-title="选项b"  data-id="22" class="site-demo-active" data-type="tabAdd">选项b</a>
					</dd>
					<dd>
						<a href="">跳转</a>
					</dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="javascript:;">解决方案</a>
				<dl class="layui-nav-child">
					<dd>
						<a href="javascript:void(0);" onclick="javascript:window.parent.document.getElementById('rightFrame').contentWindow.showLoading(); var frame=window.parent.document.getElementById('rightFrame'); frame.src='tab.do';  frame.location.reload();  " >
							测试
						</a>
					</dd>
					<dd>
						<a href="">后台模版</a>
					</dd>
					<dd>
						<a href="">电商平台</a>
					</dd>
				</dl>
			</li>
			<li class="layui-nav-item">
				<a href="#" data-url="c" data-title="选项c"  data-id="33" class="site-demo-active" data-type="tabAdd">产品c</a>
			</li>
			<li class="layui-nav-item">
				<a href="">大数据</a>
			</li>
		</ul>

		<div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin-left: 200px;">
			<ul class="layui-tab-title">
			</ul>
			<ul class="rightmenu" style="display: none;position: absolute;">
				<li data-type="closethis">关闭当前</li>
				<li data-type="closeall">关闭所有</li>
			</ul>
			<div class="layui-tab-content">
			</div>
		</div>

	</div>

	<--! test -->
</body>
</html>