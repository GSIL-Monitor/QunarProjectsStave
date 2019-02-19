
var prefix = "/finance/contract";

$(function() {

    //获取贷款提供商
    queryTppCode();

    // 查询
    $("#queryContract").bind("click", function () {
        queryContract();
    });

    // 清空
    $("#clearAll").bind("click", function () {
        clearAll();
    });
});

// 查询合同
function queryContract() {
    // 校验参数
    if(!checkQueryParam()) {
        return;
    }
    // 查询平台合同
    queryPlatAccount();
    // 查询通道合同
    queryChannelAccount();
}

// 查询平台合同
function queryPlatAccount() {
    $('#platContract').bootstrapTable('destroy');
    $('#platContract').bootstrapTable({
        method : 'post', // 服务器数据的请求方式 get or post
        url : prefix + "/queryPlatAccount", // 服务器数据的加载地址
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
                field : 'contractType', // 列字段名
                title : '合同类型' // 列标题
            },
            {
                field : 'productNo',
                title : '产品线'
            },
            {
                field : 'customName',
                title : '姓名'
            },
            {
                field : 'customId',
                title : 'CustomID'
            },
            {
                field : 'userGroup',
                title : '用户群组'
            },
            {
                field : 'identityCode',
                title : '身份证号'
            },
            {
                field : 'mobile',
                title : '手机号'
            },
            {
                field : 'tppCode',
                title : '通道方'
            },
            {
                field : 'platStatus',
                title : '拿去花状态',
                formatter : function(item, index) {
                    if(item === 0 ) {
                        return '关闭';
                    } else if(item === 1){
                        return '开启';
                    }else{
                        return '-';
                    }
                }
            },
            {
                title : '打开/关闭拿去花',
                field : 'operate',
                align : 'center',
                formatter: function(value, row, index){
                    var buttonName = row.platStatus === 0 ? "开启" : "关闭";
                    return '<a href=\'javascript:;\' class="confirmUpdatePlatStatus btn-primary btn-sm">' + buttonName + '</span></a>';
                },
                events: {
                    'click .confirmUpdatePlatStatus': function (e, value, row, index) {
                        var toStatusName = "";
                        var oldStatusName = "";
                        var toStatus = "";
                        if(row.platStatus === 0){
                            toStatusName  = "开启";
                            oldStatusName = "关闭";
                            toStatus = 1;
                        }else{
                            toStatusName  = "关闭";
                            oldStatusName = "开启";
                            toStatus = 2;
                        }
                        confirmUpdateStatus(row.customId, row.productNo, row.orgChannel, row.tppCode,
                            toStatus, oldStatusName, toStatusName);
                    }
                }
            },
            {
                field : 'totalAmount',
                title : '贷款总额度'
            },
            {
                field : 'usedAmount',
                title : '已用额度'
            },
            {
                field : 'freezeAmount',
                title : '冻结额度'
            },
            {
                field : 'balanceAmount',
                title : '可用额度'
            },
            {
                field : 'procStatus',
                title : '处理状态',
                formatter : function(item, index) {
                    switch(item){
                        case 0: return '初始';
                        case 1: return '授信中';
                        case 2: return '授信成功';
                        case 3: return '授信失败';
                        case 4: return '激活中';
                        case 5: return '激活成功';
                        case 6: return '激活失败';
                        default: return '-';
                    }
                }
            },
            {
                field : 'activated',
                title : '是否激活',
                formatter : function(item, index) {
                    if(item) {
                        return '是';
                    } else {
                        return '否';
                    }
                }
            },
            {
                field : 'startTime',
                title : '开始时间'
            },
            {
                field : 'endTime',
                title : '结束时间'
            },
            {
                field : 'expired',
                title : '是否逾期',
                formatter : function(item, index) {
                    if(item) {
                        return '是';
                    } else {
                        return '否';
                    }
                }
            },
            {
                field : 'paySwitch',
                title : '支付开关',
                formatter : function(item, index) {
                    if(item === 1) {
                        return '打开';
                    } else{
                        return '关闭';
                    }
                }
            },
            {
                title : '打开/关闭支付开关',
                field : 'paySwitchOperate',
                align : 'center',
                formatter: function(value, row, index){
                    var buttonName = row.paySwitch === 0 ? "开启" : "关闭";
                    return '<a href=\'javascript:;\' class="confirmUpdatePaySwitch btn-primary btn-sm">' + buttonName + '</span></a>';
                },
                events: {
                    'click .confirmUpdatePaySwitch': function (e, value, row, index) {
                        var toStatusName = "";
                        var oldStatusName = "";
                        var toStatus = "";
                        if(row.paySwitch === 0){
                            toStatusName  = "开启";
                            oldStatusName = "关闭";
                            toStatus = 1;
                        }else{
                            toStatusName  = "关闭";
                            oldStatusName = "开启";
                            toStatus = 0;
                        }
                        confirmUpdatePaySwitch(row.customId, row.productNo, row.orgChannel, row.tppCode,
                            toStatus, oldStatusName, toStatusName);
                    }
                }
            },
            {
                field : 'autoRepaySwitch',
                title : '自动还款',
                formatter : function(item, index) {
                    if(item === 1) {
                        return '打开';
                    } else {
                        return '关闭';
                    }

                }
            }
        ]
    });

    // 合同开关
    function confirmUpdateStatus (customId, productNo, orgChannel, tppCode, toStatus, oldStatusName, toStatusName) {
        var textList = [];
        textList.push('用户CID:' + customId);
        textList.push('产品编码:' + productNo);
        textList.push('通道:' + tppCode);
        textList.push('将更改合同状态:' + oldStatusName + ' -> ' + toStatusName );

        var param = fillParam(customId, null, productNo, orgChannel, tppCode, toStatus);
        confirmUpdate(productNo, tppCode, textList, "/finance/contract/modifyPlatContractStatus", param,
            function (data) {
                var operName = toStatus === 2 ? "关闭" : "开启";
                layer.alert(data.result ? "操作平台合同"+operName+"成功" : "操作平台合同"+operName+"失败，请重新查询后重试")
                queryPlatAccount();
            }, function (data) {
                layer.alert('后台操作平台合同出错！');
                queryPlatAccount();
            }
        );
    }

    // 支付开关
    function confirmUpdatePaySwitch (customId, productNo, orgChannel, tppCode, toStatus, oldStatusName, toStatusName) {
        var textList = [];
        textList.push('用户CID:' + customId);
        textList.push('产品编码:' + productNo);
        textList.push('通道:' + tppCode);
        textList.push('将更改合同支付开关:' + oldStatusName + ' -> ' + toStatusName );

        var param = fillParam(customId, null, productNo, orgChannel, tppCode, toStatus);
        confirmUpdate(productNo, tppCode, textList, "/finance/contract/modifyPaySwitch", param,
            function (data) {
                var operName = toStatus === 0 ? "关闭" : "开启";
                layer.alert(data.result ? "操作合同支付"+operName+"成功" : "操作合同支付"+operName+"失败，请重新查询后重试");
                queryPlatAccount();
            }, function (data) {
                layer.alert('后台操作平台合同出错！');
                queryPlatAccount();
            }
        );
    }
}

// 查询通道合同
function queryChannelAccount() {
    $('#channelContract').bootstrapTable('destroy');
    $('#channelContract').bootstrapTable({
        method: 'post', // 服务器数据的请求方式 get or post
        url: prefix + "/queryChannelAccount", // 服务器数据的加载地址
        showRefresh: false,
        showToggle: false,
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true, // 设置为true会有隔行变色效果
        dataType: "json", // 服务器返回的数据类型
        pagination: true, // 设置为true会在底部显示分页条
        queryParamsType: '',
        queryParams: queryParams,
        pageSize: 10, // 如果设置了分页，每页数据条数
        pageNumber: 1, // 如果设置了分布，首页页码
        search: false, // 是否显示搜索框
        showColumns: false, // 是否显示内容下拉框（选择显示的列）
        sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
        columns: [
            {
                field: 'contractType', // 列字段名
                title: '合同类型' // 列标题
            },
            {
                field: 'productNo', // 列字段名
                title: '产品线' // 列标题
            },
            {
                field: 'platId',
                title: 'UID'
            },
            {
                field: 'customId',
                title: 'CID'
            },
            {
                field: 'userGroup',
                title: '用户组'
            },
            {
                field: 'orgChannel',
                title: '业务来源'
            },
            {
                field: 'tppCode',
                title: '通道方'
            },
            {
                field: 'channelStatus',
                title: '合同状态',
                formatter: function (item, index) {
                    if (item === 0) {
                        return '关闭';
                    } else if (item === 1) {
                        return '开启';
                    }

                }
            },
            {
                title: '打开/关闭通道合同',
                field: 'operate',
                align: 'center',
                formatter: function (value, row, index) {
                    var buttonName = row.channelStatus === 0 ? "开启" : "关闭";
                    return '<a href=\'javascript:;\' class="confirmChannelUpdateStatus btn-primary btn-sm">' + buttonName + '</span></a>';
                },
                events: {
                    'click .confirmChannelUpdateStatus': function (e, value, row, index) {
                        var toStatusName = "";
                        var oldStatusName = "";
                        var toStatus;
                        if (row.channelStatus === 0) {
                            toStatusName = "开启";
                            oldStatusName = "关闭";
                            toStatus = 1;
                        } else {
                            toStatusName = "关闭";
                            oldStatusName = "开启";
                            toStatus = 0;
                        }
                        confirmChannelUpdateStatus(row.customId, row.platId, row.productNo, row.orgChannel, row.tppCode,
                            toStatus, oldStatusName, toStatusName);
                    }
                }
            },
            {
                field: 'totalAmount',
                title: '贷款总额度'
            },
            {
                field: 'usedAmount',
                title: '已用额度'
            },
            {
                field: 'freezeAmount',
                title: '冻结额度'
            },
            {
                field: 'balanceAmount',
                title: '可用额度'
            },
            {
                field: 'procStatus',
                title: '处理状态',
                formatter: function (item, index) {
                    switch (item) {
                        case 0: return '初始';
                        case 1: return '授信中';
                        case 2: return '授信成功';
                        case 3: return '授信失败';
                        case 4: return '激活中';
                        case 5: return '激活成功';
                        case 6: return '激活失败';
                        default: return '-';
                    }
                }
            },
            {
                field: 'creditStartTime',
                title: '授信开始时间'
            },
            {
                field: 'creditEndTime',
                title: '授信结束时间'
            },
            {
                field: 'creditFinishTime',
                title: '授信完成时间'
            },
            {
                field: 'activateStartTime',
                title: '激活开始时间'
            },
            {
                field: 'activateEndTime',
                title: '激活结束时间'
            },
            {
                field: 'activateFinishTime',
                title: '激活完成时间'
            }
        ]
    });

    function confirmChannelUpdateStatus(customId, platId, productNo, orgChannel, tppCode, toStatus, oldStatusName, toStatusName) {
        var textList = [];
        textList.push('用户CID:' + customId);
        textList.push('产品编码:' + productNo);
        textList.push('通道:' + tppCode);
        textList.push('将更改通道合同状态:' + oldStatusName + ' -> ' + toStatusName);

        var param = fillParam(customId, platId, productNo, orgChannel, tppCode, toStatus);
        confirmUpdate(productNo, tppCode, textList, "/finance/contract/modifyChannelContractStatus", param,
            function (data) {
                var operName = toStatus === 0 ? "关闭" : "开启";
                layer.alert(data.result ? "操作通道合同"+operName+"成功" : "操作通道合同"+operName+"失败，请重新查询后重试");
                queryChannelAccount();
            }, function (data) {
                layer.alert('后台操作通道合同出错！');
                queryChannelAccount();
            }
        );
    }
}

function isEmpty(data) {
    return isNull(data) || $.trim(data) == '';
}

function isNull(data) {
    return data == null || data == undefined;
}

function clearAll() {
    $("#customId").val("");
    $("#platId").val("");
    $("#mobile").val("");
    $("#identityCode").val("");
    $("#orgChannel option:not(:first)").removeAttr("selected");
    $("#productNo option:not(:first)").removeAttr("selected");
}

function checkQueryParam() {
    if(isEmpty($("#customId").val()) && isEmpty($("#platId").val()) && isEmpty($("#mobile").val())
        && isEmpty($("#identityCode").val())) {
        layer.alert("用户platId、用户customId、手机号和身份证号至少应填一项！");
        return false;
    }

    if (!isEmpty($("#mobile").val()) && isEmpty($("#orgChannel").val())) {
        layer.alert("使用手机号查询,业务来源不能为空！");
        return false;
    }

    return true;
}

function queryParams(params) {
    var platId = $("#platId").val();
    var customId = $("#customId").val();
    var orgChannel = $("#orgChannel").val();
    var productNo = $("#productNo").val();
    var mobile = $("#mobile").val();
    var identityCode = $("#identityCode").val();
    var tppCode = $("#tppCode").val();

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

    if (!isEmpty(mobile)) {
        paramData.mobile = mobile.trim();
    }

    if (!isEmpty(identityCode)) {
        paramData.identityCode = identityCode.trim();
    }

    if (!isEmpty(tppCode)) {
        paramData.tppCode = tppCode.trim();
    }
    return paramData;
}

function fillParam(customId, platId, productNo, orgChannel, tppCode, tarStatus) {
    var paramData = {};
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

    if (!isEmpty(tppCode)) {
        paramData.tppCode = tppCode.trim();
    }

    if (!isEmpty(tarStatus)) {
        paramData.tarStatus = tarStatus;
    }
    return paramData;
}

function confirmUpdate (productNo, tppCode, textList, url, param, sucFun, errFun) {
    // 创建弹窗
    fillPopUpInfo('confirmDialog', productNo, tppCode, textList, function () {
            $.ajax({
                type: "POST",
                url: url,
                data: param,
                success: sucFun,
                error: errFun
            });
        }
    );

    // 弹出弹窗
    popUp('confirmDialog');
}

// 获取贷款提供商
function queryTppCode() {
    $.ajax({
        url: '/finance/query/getTpp',
        data:{},
        type:'get',
        cache:false,
        dataType:'json',
        success:function(data) {
            for(var i=0;i<data.length;i++){
                $("#tppCode").append("<option value="+data[i].tppCode+">"+data[i].tppName+"</option>");
            }
        }
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

