//在页面未加载完毕之前显示的loading Html自定义内容
var _LoadingHtml = '<div id="loadingDiv" style="display: none; "><div id="over" style=" position: absolute;top: 0;left: 0; width: 100%;height: 100%; background-color: #f5f5f5;opacity:0.5;z-index: 1000;"></div><div id="layout" style="position: absolute;top: 40%; left: 40%;width: 20%; height: 20%;  z-index: 1001;text-align:center;"><img src="/js/timg.gif" /></div></div>';
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
