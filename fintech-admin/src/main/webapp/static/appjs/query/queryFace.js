var prefix = "/finance/face";
//进入页面立即执行
$(function () {
    $("#face").hide();

    //输入完后自动搜索
    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#queryAuditInfo").trigger("click");
        }
    });

    $("#source").change(function () {
        $("#sourceInput").val($("#source option:selected").text());
        if ($("#token").val() == "" && $("#platOpenId").val() == "" && $("#idCode").val() == "" && ($("#startTime").val() == "" || $("#endTime").val() == "")) {
            return
        }
        queryFace();
    });
    $("#status").change(function () {
        if ($("#token").val() == "" && $("#platOpenId").val() == "" && $("#idCode").val() == "" && ($("#startTime").val() == "" || $("#endTime").val() == "")) {
            return
        }
        queryFace();
    });
    $("#faceType").change(function () {
        if ($("#token").val() == "" && $("#platOpenId").val() == "" && $("#idCode").val() == "" && ($("#startTime").val() == "" || $("#endTime").val() == "")) {
            return
        }
        queryFace();
    });
    $("#checkChannel").change(function () {
        if ($("#token").val() == "" && $("#platOpenId").val() == "" && $("#idCode").val() == "" && ($("#startTime").val() == "" || $("#endTime").val() == "")) {
            return
        }
        queryFace();
    });

})

//组合查询获取表格数据
function queryFace() {

    $('#faceTable').bootstrapTable('destroy');
    $("#face").show();
    $('#faceTable')
        .bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
                url: prefix + "/face-Info", // 服务器数据的加载地址
                showRefresh: true,
                showToggle: true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                queryParamsType: '',
                queryParams: queryParams,
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                search: true, // 是否显示搜索框
                showColumns: true, // 是否显示内容下拉框（选择显示的列）,
                responseHandler: responseHandle,
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
                columns: [
                    {
                        field: 'token', // 列字段名
                        title: '请求号 ', // 列标题
                        sortable: true,
                        formatter: tokenHandle
                    },
                    {
                        field: 'auditResult',
                        title: '人工审核结果 ',
                        sortable: true,
                        formatter: auditResultHandle
                    },
                    {
                        field: 'platOpenId',
                        title: 'platOpenId ',
                        sortable: true
                    },
                    {
                        field: 'idCode',
                        title: '身份证号 '
                        /*formatter: idCodeHandle*/
                    },
                    {
                        field: 'source',
                        title: '业务线 ',
                        sortable: true
                    },
                    {
                        field: 'status',
                        title: '识别状态 ',
                        sortable: true
                    },
                    {
                        field: 'createTime',
                        title: '人脸识别时间 ',
                        sortable: true
                    },
                    {
                        field: 'faceType',
                        title: '识别方式 ',
                        sortable: true
                    },
                    {
                        field: 'errorCode',
                        title: '状态码 ',
                        sortable: true
                    },
                    {
                        field: 'errorMsg',
                        title: '状态详情 ',
                        sortable: true
                    },
                    {
                        field: 'confidence',
                        title: '人脸识别评分 ',
                        sortable: true
                    },
                    {
                        field: 'checkChannel',
                        title: '信息来源 ',
                        sortable: true
                    }
                ]
            });
}

function responseHandle(res) {
    var obj = eval(res)
    if (obj.total == "-1") {
        layer.alert("token,platOpenId,idCode,起止时间至少一项不为空！")
    }
    if (obj.total == "-2") {
        layer.alert("结束时间应大于开始时间！")
    }
    if (obj.total == "-3") {
        layer.alert("起止时间差不得超过1小时！")
    }
    return res;
}
//构建参数列表
function queryParams(params) {
    var temp = {
        source: $("#source").val(),
        status: $("#status").val(),
        faceType: $("#faceType").val(),
        checkChannel: $("#checkChannel").val(),
        token: $("#token").val(),
        platOpenId: $("#platOpenId").val(),
        idCode: $("#idCode").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        pageSize: params.pageSize,
        pageIndex: params.pageNumber
    };

    return temp;


}

function tokenHandle(res) {
    var html = "<a href='toAudit?token=" + res + "&auditUser=" + sessionStorage.getItem("adminName") + "' target='_blank'>" + res + "</a>";
    return html;
}

var index = 0
function auditResultHandle(res, row) {

    if (res == "一致" || res == "不一致" || res == "疑似" || res == "线下审核一致") {
        index = index + 1;
        var html = "<button class='btn btn-primary' data-toggle='modal' data-target='#auditModel" + index + "'>已审核</button>";
        $("#models").append("<div class=\"modal fade\" id=\"auditModel" + index + "\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
            "    <div class=\"modal-dialog\">\n" +
            "        <div class=\"modal-content\">\n" +
            "            <div class=\"modal-header\">\n" +
            "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"false\">&times;</button>\n" +
            "                <h4 class=\"modal-title\" id=\"myModalLabel\">审核信息</h4>\n" +
            "            </div>\n" +
            "            <div class=\"modal-body\">\n" +
            "                <h4>审核人:<span id=\"auditUserModel" + index + "\"></span></h4>\n" +
            "                <h4>审核结果:<span id=\"auditResultModel" + index + "\"></span></h4>\n" +
            "                <h4>审核时间:<span id=\"auditTimeModel" + index + "\"></span></h4>\n" +
            "                <h4>审核备注:<span id=\"auditRemarkModel" + index + "\"></span></h4>\n" +
            "\n" +
            "            </div>\n" +
            "            <div class=\"modal-footer\">\n" +
            "                <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>\n" +
            "            </div>\n" +
            "        </div><!-- /.modal-content -->\n" +
            "    </div><!-- /.modal -->\n" +
            "</div>")
        $("#auditUserModel" + index + "").html(row.auditUser)
        $("#auditResultModel" + index + "").html(row.auditResult)
        $("#auditTimeModel" + index + "").html(row.auditTime)
        $("#auditRemarkModel" + index + "").html(row.auditRemark)
    }

    return html;
}

//=================清空==========================
function clearAll() {
    $("#source").val("");
    $("#faceType").val("");
    $("#checkChannel").val("");
    $("#status").val("");
    $("#token").val("");
    $("#platOpenId").val("");
    $("#idCode").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}

//=================导出 start    =============
function toExport() {
    $('#exportBtn').button('loading')
    var data = {
        platOpenId: $('#platOpenId').val() || '',
        token: $('#token').val() || '',
        idCode: $('#idCode').val(),
        startTime: $('#startTime').val(),
        endTime: $('#endTime').val(),
        result: $('#result').val(),
        source: $('#source').val(),
        faceType: $('#faceType').val()

    }
    $.ajax({
        type: "POST",
        url: "/finance/face/toExecel",
        headers: {'Content-type': 'application/json'},
        data: JSON.stringify(data),
        error: function () {
            $('#exportBtn').button('reset');
            alert('发生错误')
        },
        success: function (datas) {
            $('#exportBtn').button('reset');
            if (datas == '0000') {
                DownLoadFile({
                    url: '/finance/face/toExecel',
                    'data': data
                });
            } else {
                alert("发生错误")

            }

        }
    });
}

var DownLoadFile = function (options) {
    var config = $.extend(true, {method: 'post'}, options);
    var diframe = $('<iframe id="down-file-iframe" />');
    var dform = $('<form target="down-file-iframe" style="display:none" method="' + config.method + '" />');
    dform.attr('action', config.url);
    for (var key in config.data) {
        dform.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    diframe.append(dform);
    $(document.body).append(diframe);
    dform[0].submit();
    diframe.remove();

}



