<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>无资料终端开单客户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />
    <link href="./css/css.css" type="text/css" rel="stylesheet" />


    <link href="./css/pager.css" type="text/css" rel="stylesheet" />






    <link rel="stylesheet" type="text/css" href="./js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="./js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="./js/jquery-migrate-1.2.1.js"></script>
    <script src="./js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="./js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="./js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="./js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>



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


    <script type="text/javascript">
        $(function(){



            /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
            $("tr[id^='data_']").hover(function(){
                $(this).css("backgroundColor","#2ec2ff");
            },function(){
                $(this).css("backgroundColor","#ffffff");
            })


            /** 更新记录绑定点击事件 */
            $("#update").click(function(){


                /** 获取上一次选中的数据 */
                var boxs  = $("input[type='checkbox'][id^='box_']");

                var checkedBoxs = boxs.filter(":checked");
                if(checkedBoxs.length < 1){

                    alert("请选择一个需要更新的数据！");
                }
                else if(checkedBoxs.length > 1)
                {
                    alert("只能选择一行数据！");
                }else {

                    var id = checkedBoxs.map(function(){
                        return this.value;
                    })

                    var lxdh = $("#lxdh_"+id.get());
                    var lxr = $("#lxr_"+id.get());
                    var ds_lxr = $("#ds_lxr_"+id.get());
                    var ds_lxdh = $("#ds_lxdh_"+id.get());
                    var msg = "";
                    if ($.trim(lxdh.val()) == "") {
                        msg = "联系电话不能为空！";
                        lxdh.focus();
                    }
                    else if (lxdh.val().length!==11) {
                        msg = "联系电话不是11位！";
                        lxdh.focus();
                    }
                    else if ($.trim(lxr.val()) == "") {
                        msg = "联系人不能为空！";
                        lxr.focus();
                    } else if ($.trim(ds_lxr.val()) == "") {
                        msg = "电商联系人不能为空！";
                        ds_lxr.focus();
                    } else if ($.trim(ds_lxdh.val()) == "") {
                        msg = "电商联系电话不能为空！";
                        ds_lxdh.focus();
                    }
                    else if (ds_lxdh.val().length!==11) {
                        msg = "电商联系电话不是11位！";
                        ds_lxdh.focus();
                    }
                    if (msg != "") {
                       alert(msg);
                        return false;
                    } else {
                        $("#form_update_"+id.get()).submit();
                        showLoading();
                    }


                }

            })

            })









    </script>

<script>
    function updated_num() {

    var num=<%=(String)request.getAttribute("flag")%>;
    if(num!==null) {
        alert('更新条' + num + '记录！');
        }
    }
</script>



</head>
<body onload="  updated_num();">
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr><td height="10"></td></tr>
    <tr>
        <td width="15" height="32"></td>
        <td class="main_locbg font2">&nbsp;&nbsp;&nbsp;当前位置：配置 &gt; 无资料终端开单客户</td>
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
                        <form name="erpcustomform" method="post" id="form" action="erpcustom.do">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        往来单位ID：<input type="text" name="wldwid" value="${erpcustom_con.wldwid}"  />
                                        往来单位编码：<input type="text" name="wldwbh" value="${erpcustom_con.wldwbh}"  />
                                        往来单位名称：<input type="text" name="wldwname" value="${erpcustom_con.wldwname}"  />
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

                                    <td>
                                        <input id="update" type="button" value="更新"/>

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
            <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;" align="top">
                <tr class="main_trbg_tit" align="center">

                    <td>请选择一项</td>
                    <td>省份 </td>
                    <td>地市  </td>
                    <td>区县   </td>
                    <td>客户类别   </td>
                    <td>往来单位id </td>
                    <td>往来单位编号    </td>
                    <td>往来单位名称  </td>
                    <td>联系电话     </td>
                    <td>联系人      </td>
                    <td>电商联系人    </td>
                    <td>电商联系电话   </td>
                    <td>是否电商  </td>
                    <td>收货人    </td>
                    <td>收货人联系电话 </td>
                    <td>开单员 </td>




                </tr>
                <c:forEach items="${requestScope.erpcustom}" var="erpcustom" varStatus="stat">
                    <tr id="data_${stat.index}" align="center" class="main_trbg" onMouseOver="move(this);" onMouseOut="out(this);">

                    <td><input type="checkbox" id="box_${stat.index}" value="${stat.index}"></td>
                        <form id="form_update_${stat.index}" action="updateerpcustom.do"  method="post">
                    <td>${erpcustom.shengfen } </td>
                    <td>${erpcustom.chengshi  } </td>
                    <td>${erpcustom.quyufl    } </td>
                    <td>${erpcustom.kehulb    } </td>
                    <td><input type="text" id="wldwid_${stat.index}"    name="wldwid"    value="${erpcustom.wldwid   }" readonly="true" /></td>
                    <td>${erpcustom.wldwbh    } </td>
                    <td>${erpcustom.wldwname  } </td>
                    <td><input type="text" id="lxdh_${stat.index}"    name="lxdh"    value="${erpcustom.lxdh     }" />  </td>
                    <td><input type="text" id="lxr_${stat.index}"     name="lxr"     value="${erpcustom.lxr      }" />  </td>
                    <td><input type="text" id="ds_lxr_${stat.index}"  name="ds_lxr"  value="${erpcustom.ds_lxr   }" />  </td>
                    <td><input type="text" id="ds_lxdh_${stat.index}" name="ds_lxdh" value="${erpcustom.ds_lxdh  }" />  </td>
                    <td>${erpcustom.is_dssc   } </td>
                    <td>${erpcustom.shouhr   }  </td>
                    <td>${erpcustom.shr_lxdh  } </td>
                    <td>${erpcustom.kpman  } </td>

                            <!--
                        <input id="submit_${stat.index}" type="submit" value="修改" style="display:none;"/>
                        -->

                        </form>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>




</table>
<div style="height:10px;"></div>
</body>
</html>