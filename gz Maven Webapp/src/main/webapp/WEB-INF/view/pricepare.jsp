<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.oracat.model.ReportDay" %>
<%@ page import="java.util.List" %>
<%@ page import="com.oracat.util.FusionCharts" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>日报</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="./css/css.css" type="text/css" rel="stylesheet" />




    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


    <link href="./css/pager.css" type="text/css" rel="stylesheet" />

    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>


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


    <!--查询条件 三级联动-->
    <script type="text/javascript">

        function show_1(){
            $.ajax({
                url:'selectAllProvince.do',
                type:'post',
                data:{'typeId':0},
                dataType:'json',
                //成功回调函数的参数data是一个json数组，长度是json数组里面的对象的个数。
                success:function(data){

                    console.log(JSON.stringify(data));
                    console.log(data.length);
                    var t="<option value=''>----请选择省----</option>";
                    for(var i=0;i<data.length;i++){
                        if(data[i]==' 合计'){
                            t+="<option value=' 合计'>合计</option>";
                        }
                        else{
                            t+="<option value="+data[i]+">"+data[i]+"</option>";
                        }

                    }
                    $("#p").html(t);
                }
            })

        }
        function show_2(){

            var provinceId=$("#p").val();

            $.ajax({
                url:'selectAllCityProvince.do',
                type:'post',
                data:{'provinceId':provinceId},
                dataType:'json',
                success:function(data){
                    var t="<option value=''>----请选择城市----</option>";
                    for(var i=0;i<data.length;i++){
                        if(data[i]==' 合计'){
                            t+="<option value=' 合计'>合计</option>";
                        }
                        else{
                            t+="<option value="+data[i]+">"+data[i]+"</option>";
                        }
                    }

                    $("#c").html(t);
                },
                error:function(data){
                    alert("查询城市失败了！");
                }
            })

        }

        function show_3(){

            var provinceId=$("#p").val();
            var cityId=$("#c").val();
            $.ajax({
                url:'selectAllAreaProvince.do',
                type:'post',
                data:{'provinceId':provinceId,"cityId":cityId},
                dataType:'json',
                success:function(data){

                    console.log(JSON.stringify(data));
                    var t="<option value=''>----请选择市区----</option>";
                    for(var i=0;i<data.length;i++){
                        if(data[i]==' 合计'){
                            t+="<option value=' 合计'>合计</option>";
                        }
                        else{
                            t+="<option value="+data[i]+">"+data[i]+"</option>";
                        }
                    }
                    $("#a").html(t);
                }
            })

        }



    </script>





</head>
<body   >
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"></td>
        <td class="main_locbg font2">&nbsp;&nbsp;&nbsp;当前位置：报表 &gt; 日报</td>
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
                        <form name="reportdayform" method="post" id="form" action="reportday.do">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">

                                <tr>
                                    <td class="font3">
                                        开始日期：<input type="text" id="begin_date" name="begin_date" value="${reportday_con.begin_date}"  />
                                        结束日期：<input type="text" id="end_date"   name="end_date" value="${reportday_con.end_date}" />
                                        省份：
                                        <select id="p" name="shengfen" onmouseover="show_1();" onchange="show_2();" ><option value="${reportday_con.shengfen}">${reportday_con.shengfen}</option></select>
                                        地市：
                                        <select id="c" name="chengshi"  onchange="show_3()"><option value="${reportday_con.chengshi}">${reportday_con.chengshi}</option></select>
                                        区县：
                                        <select id="a" name="quyufl" ><option value="${reportday_con.quyufl}">${reportday_con.quyufl}</option></select>
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


    <!-- 图表展示区 -->
    <tr><td>
        <div id="chart"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig = new HashMap<String, String>();
            chartConfig.put("caption", "日报");
            chartConfig.put("subCaption", "");
            chartConfig.put("xAxisName", "时间");
            chartConfig.put("yAxisName", "下单金额");
            chartConfig.put("formatNumberScale", "0");
            chartConfig.put("numberSuffix", "");
            chartConfig.put("theme", "fusion");



            //store label-value pair
            //LinkedHashMap 保证数据顺序
            Map<String, Double> dataValuePair = new LinkedHashMap<String, Double>();


            //遍历List
            Object re = request.getAttribute("reportday");
            List<ReportDay> ol= (List)re;
            for(int i=0;i<ol.size();i++){
                ReportDay      ov = ol.get(i);


                dataValuePair.put(""+ov.getRq()+"", ov.getOrder_pay_price());

            }
            StringBuilder jsonData = new StringBuilder();
            StringBuilder data = new StringBuilder();

            // json data to use as chart data source
            jsonData.append("{'chart':{");
            for(Map.Entry conf:chartConfig.entrySet())
            {
                jsonData.append("'" + conf.getKey()+"':'"+conf.getValue() + "',");
            }

            jsonData.replace(jsonData.length() - 1, jsonData.length() ,"},");

            // build  data object from label-value pair
            data.append("'data':[");

            for(Map.Entry pair:dataValuePair.entrySet())
            {

                data.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");

            }
            data.replace(data.length() - 1, data.length(),"]");

            jsonData.append(data.toString());
            jsonData.append("}");


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart = new FusionCharts(
                    "line",
                    "first_chart",
                    "100%",
                    "50%",
                    "chart",
                    "json",
                    jsonData.toString()
            );
        %>
        <%= firstChart.render() %>

    </td></tr>


    <!-- 数据展示区 -->
    <tr valign="top">
        <td height="20">
            <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr class="main_trbg_tit" align="center">

                    <td>日期                </td>
                    <td>省份                </td>
                    <td>地市                </td>
                    <td>区县                </td>
                    <td>电商客户数          </td>
                    <td>登录率(%)           </td>
                    <td>登录-下单客户数     </td>
                    <td>登录-未下单客户数   </td>
                    <td>订单-支付-客户数    </td>
                    <td>订单-支付-金额      </td>
                    <td>订单-未支付-客户数  </td>
                    <td>订单-未支付-金额    </td>
                    <td>购物车-客户数       </td>
                    <td>购物车-金额         </td>


                </tr>
                <c:forEach items="${requestScope.reportday}" var="reportday" varStatus="stat">
                    <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">

                        <td>${reportday.rq                       }</td>
                        <td>${reportday.shengfen                 }</td>
                        <td>${reportday.chengshi                 }</td>
                        <td>${reportday.quyufl                   }</td>
                        <td>${reportday.custom_num               }</td>
                        <td>${reportday.login_rate               }</td>
                        <td>${reportday.login_pay_custom         }</td>
                        <td>${reportday.login_nopay_custom       }</td>
                        <td>${reportday.order_pay_custom         }</td>
                        <td>${reportday.order_pay_price          }</td>
                        <td>${reportday.order_nopay_custom       }</td>
                        <td>${reportday.order_nopay_price        }</td>
                        <td>${reportday.shopping_cart_custom     }</td>
                        <td>${reportday.shopping_cart_price      }</td>


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
                submitUrl="${ctx}/reportday.do?pageIndex"/>

    </td></tr>



</table>
<div style="height:10px;"></div>
</body>
</html>