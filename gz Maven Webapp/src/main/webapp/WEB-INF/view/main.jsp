<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>控制中心</title>
    <link rel="stylesheet" href="./js/layui/css/layui.css">
    <style>
        .layui-tab-item{width:100%; height:100%; }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">





    <div class="layui-side layui-bg-black"  style="top:0px;background-color: #33b7b1!important;">
        <div class="layui-side-scroll">
            <div title="菜单缩放" class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;"><i class="fa fa-user-circle-o fa-lg"></i> <span >生源追踪</span></a>
                    <dl class="layui-nav-child">
                        <dd><a data-url="yz_goods.do" data-id="3" data-title="测试" class="site-demo-active"  href="javascript:;" data-type="tabAdd">测试</a></dd>
                        <dd><a href="javascript:;"><i class="fa fa-clipboard fa-lg"></i> <span >学校信息</span></a></dd>
                        <dd><a href="javascript:;"><i class="fa fa-file-text fa-lg"></i> <span >工做计划</span></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-vcard fa-lg"></i> <span >学员管理</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"><i class="fa fa-th-list fa-lg"></i> <span >学员列表</span></a></dd>
                        <dd><a href="javascript:;"><i class="fa fa-user-o fa-lg"></i> <span >考勤管理</span></a></dd>
                        <dd><a href="javascript:;"><i class="fa fa-send-o fa-lg"></i> <span >沟通计划</span></a></dd>
                        <dd><a href="javascript:;"><i class="fa fa-frown-o fa-lg"></i> <span >成绩管理</span></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-diamond fa-lg"></i> <span >管理设置</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"><i class="fa fa-address-book fa-lg"></i> <span >账号管理</span></a></dd>
                        <dd><a href="javascript:;"><i class="fa fa-check-square fa-lg"></i> <span >授权管理</span></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="fa fa-gear fa-lg"></i> <span >系统管理</span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"><i class="fa fa-window-restore fa-lg"></i> <span >系统信息</span></a></dd>
                        <dd><a href="javascript:;"><i class="fa fa-database fa-lg"></i> <span >操作日志</span></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <script>
        //JavaScript代码区域
        layui.use('element', function(){
            var element = layui.element;

        });
        var isShow = true;  //定义一个标志位
        $('.kit-side-fold').click(function(){
            //选择出所有的span，并判断是不是hidden
            $('.layui-nav-item span').each(function(){
                if($(this).is(':hidden')){
                    $(this).show();
                }else{
                    $(this).hide();
                }
            });
            //判断isshow的状态
            if(isShow){
                $('.layui-side.layui-bg-black').width(60); //设置宽度
                $('.kit-side-fold i').css('margin-right', '70%');  //修改图标的位置
                //将footer和body的宽度修改
                $('.layui-body').css('left', 60+'px');
                $('.layui-footer').css('left', 60+'px');
                //将二级导航栏隐藏
                $('dd span').each(function(){
                    $(this).hide();
                });
                //修改标志位
                isShow =false;
            }else{
                $('.layui-side.layui-bg-black').width(200);
                $('.kit-side-fold i').css('margin-right', '10%');
                $('.layui-body').css('left', 200+'px');
                $('.layui-footer').css('left', 200+'px');
                $('dd span').each(function(){
                    $(this).show();
                });
                isShow =true;
            }
        });

    </script>




    <div class="layui-body" style="top:0px">
        <!-- 内容主体区域 -->
        <div class="layui-tab layui-tab-card" lay-filter="demo" lay-allowclose="true"   style="height: 100%" >
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="carInformation">实时图表</li>
            </ul>
            <div class="layui-tab-content" style="height: 100%"  >
                <div class="layui-tab-item layui-show"  style="height: 100%"  >
                    <iframe src="realtime.do" scrolling="no" frameborder="0"  style="height: 100%;width:100%"></iframe>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
      欢迎使用！
    </div>
</div>
<script src="./js/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        var $ = layui.jquery;
        //触发事件
        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function(url,id,name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="'+id+'" scrolling="no" frameborder="0" src="'+url+'" style="height: 100%;width:100%"></iframe>',
                    id: id //规定好的id
                })
                element.render('tab');

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

    });
</script>
</body>
</html>