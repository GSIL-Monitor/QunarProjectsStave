
var prefix = "/finance/contract";

$(function() {

    // 查询
    $("#queryContractChangeReq").click(function() {
        // 校验参数
        if(isEmpty($("#reqNo").val()) && isEmpty($("#customId").val())
            && isEmpty($("#startTime").val()) && isEmpty($("#endTime").val())) {
            layer.alert("申请流水、用户customId和申请时间不能同时为空！");
            return;
        }

        queryRecord();
    });

    // 清空
    $("#clearAll").click(function() {
        clearAll();
    });
});

function isEmpty(data) {
    return data == undefined || data == null || $.trim(data) == '';
}

function queryParams(params) {
    var reqNo = $("#reqNo").val();
    var customId = $("#customId").val();
    var productNo = $("#productNo").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var paramData = {
        pageSize:params.pageSize,
        pageNum:params.pageNumber
    };
    if (!isEmpty(reqNo)) {
        paramData.reqNo = reqNo;
    }
    if (!isEmpty(customId)) {
        paramData.customId = customId;
    }
    if (!isEmpty(productNo)) {
        paramData.productNo = productNo;
    }
    if (!isEmpty(startTime)) {
        paramData.startTime = startTime;
    }
    if (!isEmpty(endTime)) {
        paramData.endTime = endTime;
    }
    console.log(paramData)
    return paramData;
}

function queryRecord() {
    $('#platContract').bootstrapTable('destroy');
    $('#platContract').bootstrapTable({
        method : 'post', // 服务器数据的请求方式 get or post
        url : prefix + "/queryChangeRecord", // 服务器数据的加载地址
        showRefresh : false,
        showToggle : false,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : true, // 设置为true会在底部显示分页条
        queryParamsType:'',
        queryParams : queryParams,
        pageSize : 10, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        search : false, // 是否显示搜索框
        showColumns : false, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
        columns : [
            {
                field : 'createTime', // 列字段名
                title : '申请时间' // 列标题
            },
            {
                field : 'reqNo', // 列字段名
                title : '申请流水' // 列标题
            },
            {
                field : 'email', // 列字段名
                title : '申请人' // 列标题
            },
            {
                field : 'busiType', // 列字段名
                title : '申请类型', // 列标题
                formatter : function(item, index) {
                    switch(item){
                        case 1: return '合同开启';
                        case 2: return '合同关闭';
                        case 3: return '合同支付开关开启';
                        case 4: return '合同支付开关关闭';
                        default: return '-';
                    }
                }
            },
            {
                field : 'customId', // 列字段名
                title : '合同customId' // 列标题
            },
            {
                field : 'platId', // 列字段名
                title : '合同platId' // 列标题
            },
            {
                field : 'productNo',
                title : '产品编号'
            },
            {
                field : 'procStatus',
                title : '申请状态',
                formatter : function(item, index) {
                    switch(item){
                        case 0: return '提交';
                        case 1: return '审核中';
                        case 2: return '通过';
                        case 3: return '拒绝';
                        default: return '-';
                    }
                }
            },
            {
                field : 'finishTime',
                title : '完成时间'
            }
        ]
    });
}

function clearAll() {
    $("#reqNo").val("");
    $("#customId").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;

};

