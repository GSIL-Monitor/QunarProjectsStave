var prefix = "/finance/ocr";
//进入页面立即执行
$(function () {
    $("#ocr").hide();

    //输入完后自动搜索
    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#queryOcrInfo").trigger("click");
        }
    });

    $("#source").change(function () {
        $("#sourceInput").val($("#source option:selected").text());
        if ($("#platOpenId").val() == "" && $("#idCode").val() == "" && ($("#startTime").val() == "" || $("#endTime").val() == "")) {
            return
        }
        queryOcr();
    });
    $("#errorCode").change(function () {
        if ($("#platOpenId").val() == "" && $("#idCode").val() == "" && ($("#startTime").val() == "" || $("#endTime").val() == "")) {
            return
        }
        queryOcr();
    });

})

//组合查询获取表格数据
function queryOcr() {

    $('#ocrTable').bootstrapTable('destroy');
    $("#ocr").show();
    $('#ocrTable')
        .bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
                url: prefix + "/ocr-Info", // 服务器数据的加载地址
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
                        field: 'platOpenId',
                        title: '用户Id '
                    },
                    {
                        field: 'idCode', // 列字段名
                        title: '身份证号 ',
                        formatter: idCodeHandle
                    },
                    {
                        field: 'source',
                        title: '业务线 '
                    },
                    {
                        field: 'errorCode',
                        title: '识别状态 '
                    },
                    {
                        field: 'errorMsg',
                        title: '状态变更原因 '
                    },
                    {
                        field: 'createTime',
                        title: '上传时间 '
                    },
                    {
                        field: 'updateTime',
                        title: '更新时间 '
                    }
                ]
            });
}

//构建参数列表
function queryParams(params) {
    var temp = {
        source: $("#source").val(),
        errorCode: $("#errorCode").val(),
        platOpenId: $("#platOpenId").val(),
        idCode: $("#idCode").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        pageSize: params.pageSize,
        pageIndex: params.pageNumber
    };

    return temp;


}

function responseHandle(res) {
    var obj = eval(res)
    if (obj.total == "-1") {
        layer.alert("platOpenId,idCode,起止时间至少一项不为空！")
    }
    if (obj.total == "-2") {
        layer.alert("结束时间应大于开始时间！")
    }
    if (obj.total == "-3") {
        layer.alert("起止时间差不得超过1小时！")
    }
    return res;
}

function idCodeHandle(res, row) {
    var html = "<a href='toShowOcr?token=" + row.token + "' target='_blank'>" + res + "</a>";
    return html;
}


//=================清空==========================
function clearAll() {
    $("#source").val("");
    $("#errorCode").val("");
    $("#platOpenId").val("");
    $("#idCode").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}



