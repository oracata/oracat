<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>增加记录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />


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


    <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>




    <link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript">

        $(function(){
            /** 部门表单提交 */
            $("#goodsforyzForm").submit(function(){

                var jnd_spid = $("#jnd_spid");
                var jnd_spname = $("#jnd_spname");
                var yz_goods_id = $("#yz_goods_id");
                var yz_goods_name = $("#yz_goods_name");
                var msg = "";
                if ($.trim(jnd_spid.val()) == ""){
                    msg = "佳能达商品id不能为空！";
                    jnd_spid.focus();
                }else if ($.trim(jnd_spname.val()) == ""){
                    msg = "佳能达商品名称不能为空！";
                    jnd_spid.focus();
                }else if ($.trim(yz_goods_id.val()) == ""){
                    msg = "云中商品id不能为空！";
                    yz_goods_id.focus();
                }else if ($.trim(yz_goods_name.val()) == ""){
                    msg = "云中商品名称不能为空！";
                    yz_goods_name.focus();
                }
                if (msg != ""){
                    $.ligerDialog.error(msg);
                    return false;
                }else{
                    return true;
                }
                $("#goodsforyzForm").submit();
            });




        });

    </script>


    <script type="text/javascript">

        function show_jnd_spid() {
            $.ajax({
                url: 'selectGoodsForYzNotin.do',
                type: 'get',
                data: {},
                dataType: 'json',
                //成功回调函数的参数data是一个json数组，长度是json数组里面的对象的个数。
                success: function (data) {

                    console.log(JSON.stringify(data));
                    console.log(data.length);

                    $("#jnd_spid").val(data[0]['id']);
                    $("#jnd_spname").val(data[0]['name']);
                    $("#manufacturer").val(data[0]['manufacturer']);
                    $("#spec").val(data[0]['spec']);

                    $("#yz_goods_name").val('');
                    $("#yz_goods_id").val('');
                }
            })
        }



        function select_yz_goods_id() {

            var jnd_spname=$("#jnd_spname").val().split('(')[0];

            var manufacturer=$("#manufacturer").val().split('(')[0];
            var spec=$("#spec").val();

            $.ajax({
                url: 'selectGoodsForYzId.do',
                type: 'get',
                data: {'jnd_spname':jnd_spname,'manufacturer':manufacturer},
                dataType: 'json',
                //成功回调函数的参数data是一个json数组，长度是json数组里面的对象的个数。
                success: function (data) {

                    console.log(JSON.stringify(data));
                    console.log(data.length);

                    var t="<option value=''>----请选择----</option>";
                    for(var i=0;i<data.length;i++){

                            t+="<option value="+data[i]['goods_id']+">"+data[i]['goods_id']+"|"+data[i]['goods_name']+"|"+data[i]['producer']+"|"+data[i]['spec']+"| 佳能达规格："+spec+"</option>";
                    }

                    $("#yz_goods_id").html(t);
                }
            })
        }



        function select_yz_goods_name() {

            var yz_goods_id=$("#yz_goods_id").val();
            $.ajax({
                url: 'selectGoodsForYzName.do',
                type: 'get',
                data: {'yz_goods_id':yz_goods_id},
                dataType: 'json',
                //成功回调函数的参数data是一个json数组，长度是json数组里面的对象的个数。
                success: function (data) {

                    console.log(JSON.stringify(data));
                    console.log(data.length);

                    $("#yz_goods_name").val(data[0]);
                }
            })
        }


        function remove_spid(){

            var jnd_spid=$("#jnd_spid").val();
            $.ajax({
                url: 'removeGoodsForYzId.do',
                type: 'get',
                data: {'jnd_spid':jnd_spid},
                dataType: 'json',
                //成功回调函数的参数data是一个json数组，长度是json数组里面的对象的个数。
                success: function (data) {

                    console.log(JSON.stringify(data));
                    console.log(data.length);

                  alert("移除成功！");
                }
            })
        }


    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：基础资料  &gt; 增加云中商品对应关系</td>
        <td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form action="${ctx}/goodsforgoods/addGoodsForYz" id="goodsforyzForm" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="flag" value="2">

                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr><td class="font3 fftd">
                        <table>
                            <tr>
                                <td class="font3 fftd">佳能达商品id：<input onclick="show_jnd_spid()" type="text" name="jnd_spid" id="jnd_spid" size="20" value="${goodsforyz.jnd_spid }"/></td>
                                <td class="font3 fftd">佳能达商品名称：<input type="text" name="jnd_spname" id="jnd_spname" size="20" value="${goodsforyz.jnd_spname }"/></td>
                                <input type="hidden"  id="manufacturer" name="manufacturer" value="" />
                                <input type="hidden" id="spec"  name="spec" value="" />

                                <td class="font3 fftd">云中商品id：  <select  onmouseover="select_yz_goods_id()" onchange="select_yz_goods_name()"  name="yz_goods_id" id="yz_goods_id" > <option></option></select></td>
                                <input type="hidden" id="yz_name"  name="yz_name" value="" />
                                <td class="font3 fftd">云中商品名称：<input type="text" name="yz_goods_name" id="yz_goods_name" size="20" value="${goodsforyz.yz_goods_name }"/></td>

                            </tr>

                        </table>
                    </td></tr>
                    <tr><td class="main_tdbor"></td></tr>

                    <tr><td align="right" class="fftd"><input type="submit" value="确定增加">&nbsp;&nbsp;<input id="rr" type="reset" value="取消 "> &nbsp;&nbsp;<input type="button" onclick="remove_spid()" value="找不到对应关系，移除SPID"> <input type="button" onclick="javascript:   var frame=window.parent.document.getElementById('10'); frame.src='goodsforgoods/goodsforgoods';  frame.location.reload(); " value="返回"></td></tr>
                </table>
            </form>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>