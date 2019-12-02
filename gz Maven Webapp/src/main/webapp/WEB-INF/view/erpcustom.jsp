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
    <link href="../css/css.css" type="text/css" rel="stylesheet" />


    <link href="../css/pager.css" type="text/css" rel="stylesheet" />






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





</head>
<body >
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


    <!-- 数据展示区 -->
    <tr valign="top">
        <td height="20">
            <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr class="main_trbg_tit" align="center">
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

                    <td>${erpcustom.shengfen } </td>
                    <td>${erpcustom.chengshi  } </td>
                    <td>${erpcustom.quyufl    } </td>
                    <td>${erpcustom.kehulb    } </td>
                    <td>${erpcustom.wldwid   }</td>
                    <td>${erpcustom.wldwbh    } </td>
                    <td>${erpcustom.wldwname  } </td>
                    <td>${erpcustom.lxdh     }  </td>
                    <td>${erpcustom.lxr      }  </td>
                    <td>${erpcustom.ds_lxr   }  </td>
                    <td>${erpcustom.ds_lxdh  }  </td>
                    <td>${erpcustom.is_dssc   } </td>
                    <td>${erpcustom.shouhr   }  </td>
                    <td>${erpcustom.shr_lxdh  } </td>
                    <td>${erpcustom.kpman  } </td>


                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>




</table>
<div style="height:10px;"></div>
</body>
</html>