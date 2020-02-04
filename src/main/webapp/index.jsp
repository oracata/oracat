<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/25 0020
  Time: ���� 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>��ʱ����</title>
    <link rel="stylesheet" href="${basePath}/static/bootstrap-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/static/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/static/css/bootstrap-table.min.css">
    <script src="${basePath}/static/js/jquery-2.0.3.min.js"></script>
    <script src="${basePath}/static/bootstrap-dist/js/bootstrap.min.js"></script>
    <script src="${basePath}/static/js/bootstrap-table.min.js"></script>
    <script src="${basePath}/static/js/bootstrap-table-zh-CN.js"></script>
</head>
<body>

<div id="rrapp" style="margin: 100px;">
    <div id="showList">
        <div class="grid-btn" style="height:34px;">
            <a class="btn btn-primary" onclick="update();"><i class="fa fa-pencil-square-o"></i>&nbsp;�޸�</a>
            <a class="btn btn-primary" onclick="pause();"><i class="fa fa-pause"></i>&nbsp;��ͣ</a>
            <a class="btn btn-primary" onclick="resume();"><i class="fa fa-play"></i>&nbsp;�ָ�</a>
            <a class="btn btn-primary" onclick="add();"><i class="fa fa-pencil-square-o"></i>&nbsp;���</a>
            <a class="btn btn-primary" onclick="runOnce();"><i class="fa fa-arrow-circle-right"></i>&nbsp;����ִ��</a>
        </div>
        <table id="table"></table>
    </div>

    <!-- �޸�ʱ��ģ̬�� -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">�޸Ķ�ʱ����ʱ��</h4>
                </div>
                <div class="modal-body">
                    <input type="text" id="modalId" style="display:none"/>
                    Cron���ʽ
                    <input type="text" class="form-control" id="modalCron">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="updateCron();">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <!-- �޸�ʱ��ģ̬�� -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="addModalLabel">��Ӷ�ʱ����</h4>
                </div>
                <div class="modal-body">
                    <a>������</a> <input type="text" class="form-control" id="jobName"><br>
                    <a>������</a> <input type="text" class="form-control" id="jobGroup"><br>
                    <a>������</a> <input type="text" class="form-control" id="methodName"><br>
                    <a>����·��</a> <input type="text" class="form-control" id="beanClass"><br>
                    <a>ʱ��</a> <input type="text" class="form-control" id="cronExpression">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addCron();">Save</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    $(function () {
        //��ʼ�����
        initTable();
    });

    function addCron(){
        $('#addModal').modal('hide');
        var queryUrl = "${basePath}/scheduleJob/addScheduleJob.do";
        $.ajax({
            type: 'POST',
            data: {
                cronExpression: $("#cronExpression").val(),
                jobName:$("#jobName").val(),
                jobGroup:$("#jobGroup").val(),
                methodName:$("#methodName").val(),
                beanClass:$("#beanClass").val(),
                status:0

            },
            url: queryUrl,
            success: function (result) {
                //ˢ�±��
                var opt = {
                    url: '${basePath}/scheduleJob/listAllJob.do'
                };
                $("#table").bootstrapTable('refresh', opt);
            }
        })
    }

    function  add(){
        $('#addModal').modal('toggle');
    }
    //�޸İ�ť
    function update() {
        //��ȡѡ�е���
        var a = $("#table").bootstrapTable('getSelections');
        if (a.length == 0) {
            alert("����ѡ����Ҫ�޸ĵ���");
            return false;
        } else if (a.length > 1) {
            alert("ֻ��ѡ��һ��");
            return false;
        }
        $("#modalId").val(a[0].id);
        $('#myModal').modal('toggle');
    }

    //���¶�ʱ����ʱ��
    function updateCron() {
        $('#myModal').modal('hide');
        var id = $("#modalId").val();
        var queryUrl = "${basePath}/scheduleJob/updateCron.do";
        $.ajax({
            type: 'POST',
            data: {
                id: id,
                cronExpression: $("#modalCron").val(),
            },
            url: queryUrl,
            success: function (result) {
                //ˢ�±��
                var opt = {
                    url: '${basePath}/scheduleJob/listAllJob.do'
                };
                $("#table").bootstrapTable('refresh', opt);
            }
        })
    }

    //��ͣһ����ʱ����
    function pause() {
        var queryUrl = '${basePath}/scheduleJob/pauseJob.do';
        commonSubmit(queryUrl);
    }

    //�ָ�һ����ʱ����
    function resume() {
        var queryUrl = '${basePath}/scheduleJob/resumeJob.do';
        commonSubmit(queryUrl);
    }

    //����ִ��һ����ʱ����
    function runOnce() {
        var queryUrl = "${basePath}/scheduleJob/runOnce.do";
        commonSubmit(queryUrl);
    }

    //��ͣ���ָ�������ִ���ύ����
    function commonSubmit(queryUrl) {
        //��ȡѡ�е���
        var a = $("#table").bootstrapTable('getSelections');
        if (a.length == 0) {
            alert("����ѡ����Ҫ�޸ĵ���");
            return false;
        } else if (a.length > 1) {
            alert("ֻ��ѡ��һ��");
            return false;
        }
        var obj = a[0];
        $.ajax({
            type: 'post',
            data: {
                jobId: obj.id
            },
            url: queryUrl,
            success: function (result) {
                //ˢ�±��״̬���
                var opt = {
                    url: '${basePath}/scheduleJob/listAllJob.do'
                };
                $("#table").bootstrapTable('refresh', opt);
            }
        });
    }

    //�������
    function initTable() {
        var queryUrl = '${basePath}/scheduleJob/listAllJob.do';
        $('#table').bootstrapTable({
            method: 'POST',//����ʽ��*��
            contentType: "application/x-www-form-urlencoded;charset=UTF-8",//�ڷ���˷�ҳʱ��������
            dataType: 'json',
            //toolbar: '#toolbar',//���߰�ť���ĸ�����
            striped: true,//�Ƿ���ʾ�м��ɫ
            cache: false,//�Ƿ�ʹ�û��棬Ĭ��Ϊtrue������һ���������Ҫ����һ��������ԣ�*��
            pagination: true,//�Ƿ���ʾ��ҳ��*����
            onlyInfoPagination: false,//����Ϊtrueʱֻ��ʾ�����ݣ�������ʾ��ҳ��ť
            showPaginationSwitch: false,
            sortable: true,//�Ƿ���������
            sortOrder: "asc",//����ʽ
            sidePagination: "server",//��ҳ��ʽ��client�ͻ��˷�ҳ��server����˷�ҳ��*��
            pageNumber: 1,//��ʼ�����ص�һҳ��Ĭ�ϵ�һҳ,����¼
            pageSize: 10,//ÿҳ�ļ�¼������*��
            pageList: [10, 25, 50, 100],//�ɹ�ѡ���ÿҳ��������*��
            url: queryUrl,//�����̨��URL��*��
            search: false,//�Ƿ���ʾ�������
            strictSearch: true,
            showColumns: false,//�Ƿ���ʾ���е��У�ѡ����ʾ���У�
            showRefresh: false,//�Ƿ���ʾˢ�°�ť
            minimumCountColumns: 1,//�������������
            clickToSelect: true,//�Ƿ����õ��ѡ����
            //height: 500,                      //�иߣ����û������height���ԣ�����Զ����ݼ�¼�������ñ��߶�
            uniqueId: "ID",//ÿһ�е�Ψһ��ʶ��һ��Ϊ������
            showToggle: false, //�Ƿ���ʾ��ϸ��ͼ���б���ͼ���л���ť
            cardView: false,//�Ƿ���ʾ��ϸ��ͼ
            detailView: false,//�Ƿ���ʾ���ӱ�
            paginationDetailHAlign: "left",//����ҳ��������Ϣλ��,Ĭ�������
            showExport: false,                     //�Ƿ���ʾ����
            exportDataType: "selected",              //basic', 'all', 'selected'.
            //��ȡ��ѯ����
            queryParams: function queryParams(params) {
                //����ļ������ֺͿ������ı���������һ�£���߸Ķ���������Ҳ��Ҫ�ĳ�һ����
                var param = {
                    pageSize: params.limit,                       //ҳ���С
                    pageNumber: (params.offset / params.limit) + 1,   //ҳ��
                    menuName: $("#menuNameQuery").val(), //�˵�����
                    parentName: $("#parentNameQuery").val(),//�ϼ��˵�����
                };
                return param;
            },
            columns: [
                {
                    field: 'Number',
                    title: '',
                    align: 'center',
                    width: 20,
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },
                {
                    checkbox: true,
                    visible: true                  //�Ƿ���ʾ��ѡ��
                }, {
                    field: 'id',
                    title: '����ID',
                    width: 50,
                    align: 'center'
                }, {
                    field: 'jobName',
                    title: 'JobName',
                    width: 150,
                    align: 'center'
                }, {
                    field: 'jobGroup',
                    title: 'JobGroup',
                    width: 50,
                    align: 'center'
                }, {
                    field: 'beanClass',
                    title: 'BeanClass',
                    align: 'center'
                }, {
                    field: 'methodName',
                    title: 'MethodName',
                    width: 100,
                    align: 'center'
                }, {
                    field: 'params',
                    title: '����',
                    width: 100,
                    align: 'center'
                }, {
                    field: 'cronExpression',
                    title: 'cron���ʽ',
                    width: 100,
                    align: 'center'
                }, {
                    field: 'status',
                    title: '״̬',
                    width: 50,
                    align: 'center',
                    formatter: function (value, row, index) {
                        if (value == 0) {
                            return "<a href='javascript:void(0);' class='btn btn-primary btn-xs'>����</a>";
                        }
                        if (value == 1) {
                            return "<a href='javascript:void(0);' class='btn btn-danger btn-xs'>��ͣ</a>";
                        }
                    }
                }, {
                    field: 'remark',
                    title: '��ע',
                    width: 100,
                    align: 'center'
                }],
        });
    }
</script>
</body>
</html>