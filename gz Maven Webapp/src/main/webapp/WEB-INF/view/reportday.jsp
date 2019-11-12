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
    <link href="${ctx}/view/css/css.css" type="text/css" rel="stylesheet" />

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="${ctx}/view/css/pager.css" type="text/css" rel="stylesheet" />

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
                                        省份：<input type="text" name="shengfen" value="${reportday_con.shengfen}"  />
                                        地市：<input type="text" name="chengshi" value="${reportday_con.chengshi}"  />
                                        区县：<input type="text" name="quyufl" value="${reportday_con.quyufl}"  />
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