//��ҳ��δ�������֮ǰ��ʾ��loading Html�Զ�������
var _LoadingHtml = '<div id="loadingDiv" style="display: none; "><div id="over" style=" position: absolute;top: 0;left: 0; width: 100%;height: 100%; background-color: #f5f5f5;opacity:0.5;z-index: 1000;"></div><div id="layout" style="position: absolute;top: 40%; left: 40%;width: 20%; height: 20%;  z-index: 1001;text-align:center;"><img src="./timg.gif" /></div></div>';
//����loadingЧ��
document.write(_LoadingHtml);

//�Ƴ�loadingЧ��
function completeLoading() {
    document.getElementById("loadingDiv").style.display="none";
}
//չʾloadingЧ��http://localhost:2006/
function showLoading()
{
    document.getElementById("loadingDiv").style.display="block";
}



$(document).ready(function(){
        //��ѡ��form������form���д���
        $("form[id$='form']").submit(function(){
            showLoading();
            return true;
        });

        //����excel
        $("a[id$='export']").click(function(){
            showLoading();
            return true;
        });

    }

);
