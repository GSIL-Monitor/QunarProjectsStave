
var prefix = "/finance/contract";

$(function() {

    // 查询
    $("#queryUp").bind("click", function () {
        // 校验参数
        if(isEmpty($("#platId").val()) && isEmpty($("#customId").val())) {
            layer.alert("用户platId和用户customId不能同时为空！");
            return;
        }

        queryUserProduct();
    });

    // 清空
    $("#clearAll").bind("click", function () {
        $("#platId").val("");
        $("#customId").val("");
        $("#orgChannel option:not(:first)").removeAttr("selected");
    });
});

function isEmpty(data) {
    return data == undefined || data == null || $.trim(data) == '';
}

function queryParams(params) {
    var platId = $("#platId").val();
    var customId = $("#customId").val();
    var orgChannel = $("#orgChannel").val();
    var productNo = $("#productNo").val();

    var paramData = {
        pageSize:params.pageSize,
        pageNum:params.pageNumber
    };
    if (!isEmpty(platId)) {
        paramData.platId = platId.trim();
    }
    if (!isEmpty(customId)) {
        paramData.customId = customId.trim();
    }
    if (!isEmpty(orgChannel)) {
        paramData.orgChannel = orgChannel.trim();
    }

    if (!isEmpty(productNo)) {
        paramData.productNo = productNo.trim();
    }
    return paramData;
}

function queryUserProduct() {
    $('#platContract').bootstrapTable('destroy');
    $('#platContract').bootstrapTable({
        method : 'post', // 服务器数据的请求方式 get or post
        url : prefix + "/queryUserProduct", // 服务器数据的加载地址
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
                field : 'customId', // 列字段名
                title : '金融customId' // 列标题
            },
            {
                field : 'platId', // 列字段名
                title : '金融platId' // 列标题
            },
            {
                field : 'productNo',
                title : '产品编号'
            },
            {
                field : 'bindOrgChannel',
                title : '绑定渠道'
            },
            {
                field : 'activateOrgChannel',
                title : '激活渠道'
            },
            {
                field : 'paySwitch',
                title : '支付开关',
                formatter : function(item, index) {
                    switch(item){
                        case 0: return '关闭';
                        case 1: return '使用中';
                        default: return '-';
                    }
                }
            },
            {
                field : 'bindStatus',
                title : '绑定状态',
                formatter : function(item, index) {
                    switch(item){
                        case 0: return '未绑定';
                        case 1: return '已绑定';
                        default: return '-';
                    }
                }
            },
            {
                field : 'bindSrc',
                title : '绑定来源'
            },
            {
                field : 'bindTime',
                title : '绑定时间'
            },
            {
                field : 'createTime',
                title : '创建时间'
            },
            {
                field : 'updateTime',
                title : '更新时间'
            },
            {
                field : 'procStatus',
                title : '合同处理状态',
                formatter : function(item, index) {
                    switch(item){
                        case 0: return '初始';
                        case 1: return '授信中';
                        case 2: return '授信成功';
                        case 3: return '授信失败';
                        case 4: return '待激活';
                        case 5: return '激活中';
                        case 6: return '激活成功';
                        case 7: return '激活失败';
                        default: return '-';
                    }
                }
            },
            {
                field : 'platStatus',
                title : '平台状态',
                formatter : function(item, index) {
                    switch(item){
                        case 0: return '不可用';
                        case 1: return '可用';
                        case 2: return '关闭';
                        case 9: return '作废';
                        default: return '-';
                    }
                }
            },
            {
                field : 'unBindTime',
                title : '解绑时间'
            }
        ]
    });
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