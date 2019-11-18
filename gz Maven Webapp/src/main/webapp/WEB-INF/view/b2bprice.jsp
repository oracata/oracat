<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>电商价格</title>
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



</head>
<body  >
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"></td>
        <td class="main_locbg font2">&nbsp;&nbsp;&nbsp;当前位置：电商价格 &gt; 品种查询</td>
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
                        <form name="b2bpriceform" method="post" id="form" action="b2bprice.do">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        商品ID：<input type="text" name="id" value="${b2bprice_con.id}"  />
                                        商品编码：<input type="text" name="no" value="${b2bprice_con.no}"  />
                                        商品名称：<input type="text" name="name" value="${b2bprice_con.name}"  />
                                        <input type="submit" value="查询"/>

                                     <!--
                                        <form name="exportform" method="post" id="exportform" action="export.do">

                                                        <input type="submit" value="导出excel"/>
                                        </form>
                                        -->

                                        <a href="export" id="export">导出excel</a>

                                        <!--
                                        <form name="exportform" method="post" id="exportform" action="export.do">

                                            <input type="submit" value="导出excel"/>
                                        </form>
                                     -->
                                        <!--
                                        <a href=  "javascript:void(0);" onclick="javascript:   var frame=window.parent.document.getElementById('exportform'); frame.src='export.do';   ">导出excel</a>

                                       -->
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

                    <td>商品id</td>
                    <td>商品编码</td>
                    <td>商品名称</td>
                    <td>规格    </td>
                    <td>生产厂家</td>
                    <td>电商价格  </td>
                    <td>终端近7天平均开票价    </td>
                    <td>与终端销价对比率    </td>
                    <td>erp进价</td>
                    <td>erp最低销售价    </td>

                    <td>电商库存   </td>


                </tr>
                <c:forEach items="${requestScope.b2bprice}" var="b2bprice" varStatus="stat">
                    <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">

                        <td>${b2bprice.id              }</td>
                        <td>${b2bprice.no              }</td>
                        <td>${b2bprice.name            }</td>
                        <td>${b2bprice.spec                        }</td>
                        <td>${b2bprice.manufacturer                                }</td>
                        <td>${b2bprice.pfpj                                }</td>
                        <td>${b2bprice.hshj                                }</td>
                        <td>${b2bprice.abs_rate                                }</td>
                        <td>${b2bprice.cankcbj                                }</td>
                        <td>${b2bprice.zdxshj                                }</td>

                        <td>${b2bprice.stock_num                                }</td>



                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>



</table>
<div style="height:10px;"></div>
</body>
</html>