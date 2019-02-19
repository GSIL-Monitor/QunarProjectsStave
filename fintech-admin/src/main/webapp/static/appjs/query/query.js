var prefix = "/finance/query";

$(function() {
    $("#ious").hide();
    $("#cash").hide();
    $(".loan").show();
    if($("#queryType").val() == "account"){
        $(".stime").html("签约开始时间:");
        $(".etime").html("签约结束时间:");
        //$(".loan").hide();
    }
    if($("#queryType").val() == "refund"){
        $(".stime").html("退款开始时间:");
        $(".etime").html("退款结束时间:");
        $(".loan").show();
        $("#productNo").val('IOUS');
        $("#productNo").attr("disabled",true);
    }
    if($("#queryType").val() == "repay"){
        $(".stime").html("还款开始时间:");
        $(".etime").html("还款结束时间:");
        $(".loan").show();
    }
    if($("#queryType").val() == "loan"){
        $(".stime").html("贷款开始时间:");
        $(".etime").html("贷款结束时间:");
        $(".loan").show();
    }
    if($("#queryType").val() == "repayReq"){
        $(".stime").html("还款请求开始时间:");
        $(".etime").html("还款请求结束时间:");
        $(".loan").hide();
    }
    //获取贷款提供商
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
});

function clearAll() {
    $("#mobile").val("");
    $("#userName").val("");
    $("#identity").val("");
    $("#uid").val("");
    $("#startTime").val("");
    $("#endTime").val("");
    $("input:radio[name='orgChannel']").removeAttr('checked');
}

function query() {
    var type = $("#queryType").val();
    if(($("#mobile").val() == null || $("#mobile").val().trim() == '') && ($("#identity").val() == null || $("#identity").val().trim() == '')) {
        layer.alert("手机号和身份证号至少应填一项！");
        return;
    }
    var orgChannel = $('input:radio[name="orgChannel"]:checked').val();
    if(orgChannel == null || orgChannel == '') {
        layer.alert("请选择业务来源！");
        return;
    }
    var productNo = $("#productNo").val();
    if(productNo == null || productNo == '') {
        layer.alert("请选择产品线！");
        return;
    }
    if(type == "account") {
        queryAccount();
    }
    if(type == "loan") {
        queryLoan();
    }
    if(type == "repay") {
        queryRepay();
    }
    if (type == "refund") {
        queryRefund();
    }
    if (type == "overDue") {
        queryOverDue();
    }
    if (type == "repayReq") {
        queryRepayReq();
    }
}

function queryAccount() {
    var orgChannel = $('input:radio[name="orgChannel"]:checked').val();
    $('#exampleTable').bootstrapTable('destroy');
    $('#cashLoan').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : prefix + "/account-info", // 服务器数据的加载地址
                showRefresh : true,
                showToggle : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                queryParamsType:'',
                queryParams : queryParams,
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : true, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
                columns : [
                    {
                        field : 'productNo', // 列字段名
                        title : '产品线' // 列标题
                    },
                    {
                        field : 'userName',
                        title : '姓名'
                    },
                    {
                        field : 'userId',
                        title : 'UID'
                    },
                    {
                        field : 'identity',
                        title : '身份证号'
                    },
                    {
                    	field : 'mobile',
                    	title : '手机号'
                    },
                    {
                        field : 'orgChannel',
                        title : '业务来源'
                    },
                    {
                        field : 'signTime',
                        title : '签约时间'
                    },
                    {
                        field : 'creditAmount',
                        title : '贷款通道额度'
                    },
                    {
                        field : 'mainAmount',
                        title : '客户端展示额度'
                    },
                    {
                        field : 'usedAmount',
                        title : '已用额度'
                    },
                    {
                        field : 'annualRate',
                        title : '3期利率'
                    },
                    {
                        field : 'rate6',
                        title : '6期利率'
                    },
                    {
                        field : 'rate9',
                        title : '9期利率'
                    },
                    {
                        field : 'rate12',
                        title : '12期利率'
                    },
                    {
                        field : 'updateTime',
                        title : '更新时间'
                    },
                    {
                        field : 'validDay',
                        title : '有效期'
                    },
                    {
                        field : 'debtAmountAll',
                        title : '欠款金额',
                        formatter : function(item, index) {
                            if(item === null || item === undefined || item === "") {
                                return '0.00';
                            } else {
                                return item;
                            }
                        }
                    },
                    {
                    	field : 'overDueAmountAll',
                    	title : '逾期金额',
                        formatter : function(item, index) {
                            if(item === null || item === undefined || item === "") {
                                return '0.00';
                            } else {
                                return item;
                            }
                        }
                    },
                    {
                        field : 'currentDebtCount',
                        title : '欠款笔数',
                        formatter : function(item, index) {
                            if(item === null || item === undefined || item === "") {
                                return '0';
                            }else {
                                return item;
                            }
                        }
                    },
                    {
                        field : 'currentOverdueCount',
                        title : '逾期笔数',
                        formatter : function(item, index) {
                            if(item === null || item === undefined || item === "") {
                                return '0';
                            }else {
                                return item;
                            }
                        }
                    },
                    {
                        field : 'tppCode',
                        title : '通道方'
                    },
                    {
                        field : 'paySwitch',
                        title : '是否可支付',
                        formatter : function(item, index) {
                            if(item === "ON") {
                                return '是';
                            } else {
                                return '否';
                            }

                        }
                    },
                    {
                        field : 'autoRepaySignStatus',
                        title : '自动还款开关是否打开',
                        formatter : function(item, index) {
                            if(item === "SIGNED") {
                                return '是';
                            } else {
                                return '否';
                            }

                        }
                    },
                    {
                        field : 'useStatus',
                        title : '是否主通道额度',
                        formatter : function(item, index) {
                            if(item === 1) {
                                return '是';
                            } else {
                                return '否';
                            }

                        }
                    },
                    {
                        field : 'creditType',
                        title : '授信方式',
                        formatter : function(item, index) {
                            if(item === "0") {
                                return '默认方式';
                            } else if(item === "1"){
                                return '主动授信';
                            } else if(item === "2"){
                                return '补授信';
                            } else if(item === "3"){
                                return '定时补授信';
                            } else {
                                return '';
                            }

                        }
                    },
                    {
                        field : 'tppCode',
                        title : '授信通道'
                    },
                    {
                        field : 'reqStatus',
                        title : '授信状态',
                        formatter : function(item, index) {
                            if(item === 0) {
                                return '处理中';
                            } else if(item === 1){
                                return '授信成功';
                            } else if(item === 2){
                                return '授信失败';
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field : 'status',
                        title : '合同状态'
                    },
                    {
                        field : 'contractStatus',
                        title : '是否关闭拿去花',
                        formatter : function(item, index) {
                            if(item === 0) {
                                return '关闭';
                            } else if(item === 1){
                                return '开启';
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        title : '修改',
                        field : 'id',
                        align : 'center',
                        width : '8%',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="修改手机" onclick="modifyMobile(\''
                                + row.userId + '\' ,  \'' + row.userName + '\' , \'' + row.mobile
                                + '\')"><i class="fa fa-list"></i></a> ';
                            return e;
                        }
                    }
                ]
            });
}

function queryRefund() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#cashLoan').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : prefix + "/refund-info", // 服务器数据的加载地址
                queryParamsType:'',
                queryParams : queryParams,
                showRefresh : true,
                showToggle : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : true, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                columns : [

                    {
                        field : 'productNo', // 列字段名
                        title : '产品线' // 列标题
                    },
                    {
                        field : 'userName',
                        title : '姓名'
                    },
                    {
                        field : 'identity',
                        title : '身份证号'
                    },
                    {
                        field : 'mobile',
                        title : '手机号'
                    },
                    {
                        field : 'orgChannel',
                        title : '业务来源'
                    },
                    {
                        field : 'busiOrderNo',
                        title : '业务线订单号'
                    },
                    {
                        field : 'busiName',
                        title : '业务线'
                    },
                    {
                        field : 'orderAmount',
                        title : '订单金额'
                    },
                    {
                        field : 'payTime',
                        title : '消费时间'
                    },
                    {
                        field : 'status',
                        title : '退款状态',
                        formatter : function(item, index) {
                            if(item === 0) {
                                return '初始';
                            } else if(item === 1){
                                return '成功';
                            } else if(item === 2){
                                return '失败';
                            } else if(item === 3){
                                return '处理中';
                            } else if(item === 4){
                                return '待发起';
                            }
                            else {
                                return '';
                            }

                        }
                    },
                    {
                        field : 'requestTime',
                        title : '退款发起时间'
                    },
                    {
                        field : 'refundTime',
                        title : '退款完成时间'
                    },
                    {
                        field : 'iousPayAmount',
                        title : '实际贷款金额'
                    },
                    {
                        field : 'refundAmount',
                        title : '退款总金额'
                    },
                    {
                        title : '退款去向',
                        field : 'refundDetail'
                    },
                    {
                        field : 'errorMsg',
                        title : '错误信息'
                    },
                    {
                        field : 'suggest',
                        title : '建议话术'
                    }
                ]
            });
}

function queryRepay() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#cashLoan').bootstrapTable('destroy');
    var productNo = $("#productNo").val();
    if(productNo == undefined || productNo == 'IOUS') {
        $("#ious").show();
        $('#exampleTable')
            .bootstrapTable(
                {
                    method: 'post', // 服务器数据的请求方式 get or post
                    url: prefix + "/repay-ious", // 服务器数据的加载地址
                    queryParamsType:'',
                    queryParams: queryParams,
                    //showRefresh : true,
                    //showToggle : true,
                    iconSize: 'outline',
                    //toolbar : '#exampleToolbar',
                    striped: true, // 设置为true会有隔行变色效果
                    dataType: "json", // 服务器返回的数据类型
                    pagination: true, // 设置为true会在底部显示分页条
                    pageSize: 10, // 如果设置了分页，每页数据条数
                    pageNumber: 1, // 如果设置了分布，首页页码
                    //search : true, // 是否显示搜索框
                    //showColumns : true, // 是否显示内容下拉框（选择显示的列）
                    sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                    columns: [

                        {
                            field: 'productNo', // 列字段名
                            title: '产品线' // 列标题
                        },
                        {
                            field: 'userName',
                            title: '姓名'
                        },
                        {
                            field: 'identity',
                            title: '身份证号'
                        },
                        {
                            field: 'mobile',
                            title: '手机号'
                        },
                        {
                            field : 'orgChannel',
                            title : '业务来源'
                        },
                        {
                            field: 'repayTime',
                            title: '还款时间'
                        },
                        {
                            field: 'repayAmount',
                            title: '还款金额'
                        },
                        {
                            field: 'repayType',
                            title: '还款方式'
                        },
                        {
                            field: 'statusName',
                            title: '还款状态'
                        },
                        {
                            field: 'channel',
                            title: '还款来源'
                        },
                        {
                            field: 'repayWithholeMode',
                            title: '是否转分期',
                            formatter : function(item, index) {
                                if(item == 'TRANSFER') {
                                    return '是';
                                } else {
                                    return '否';
                                }
                            }
                        },
                        {
                            field: 'errorMsg',
                            title: '错误信息'
                        },
                        {
                            field: 'suggest',
                            title: '建议话术'
                        }
                        // {
                        //     title : '支付详情',
                        //     field : 'id',
                        //     align : 'center',
                        //     width : '5%',
                        //     formatter : function(value, row, index) {
                        //         var e = '<a class="btn btn-primary btn-sm '
                        //             + '" href="#" mce_href="#" title="支付详情" onclick="showPayDetail(\''
                        //             + row.orderNo
                        //             + '\')"><i class="fa fa-list"></i></a> ';
                        //         return e;
                        //     }
                        // }
                    ]
                });
    }
    else {
        $("#cash").show();
        $('#cashLoan')
            .bootstrapTable(
                {
                    method: 'post', // 服务器数据的请求方式 get or post
                    url: prefix + "/repay-cash", // 服务器数据的加载地址
                    queryParamsType:'',
                    queryParams: queryParams,
                    //showRefresh : true,
                    //showToggle : true,
                    iconSize: 'outline',
                    //toolbar : '#exampleToolbar',
                    striped: true, // 设置为true会有隔行变色效果
                    dataType: "json", // 服务器返回的数据类型
                    pagination: true, // 设置为true会在底部显示分页条
                    pageSize: 10, // 如果设置了分页，每页数据条数
                    pageNumber: 1, // 如果设置了分布，首页页码
                    //search : true, // 是否显示搜索框
                    //showColumns : true, // 是否显示内容下拉框（选择显示的列）
                    sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                    columns: [

                        {
                            field: 'productNo', // 列字段名
                            title: '产品线' // 列标题
                        },
                        {
                            field: 'userName',
                            title: '姓名'
                        },
                        {
                            field: 'identity',
                            title: '身份证号'
                        },
                        {
                            field: 'mobile',
                            title: '手机号'
                        },
                        {
                            field : 'orgChannel',
                            title : '业务来源'
                        },
                        {
                            field: 'repayTime',
                            title: '还款时间'
                        },
                        {
                            field: 'repayAmount',
                            title: '还款金额'
                        },
                        {
                            field: 'repayType',
                            title: '还款方式'
                        },
                        {
                            field: 'statusName',
                            title: '还款状态'
                        },
                        {
                            field: 'channel',
                            title: '还款来源'
                        },
                        {
                            field: 'repayIndex',
                            title: '还款期数'
                        },
                        {
                            field: 'repayWithholeMode',
                            title: '是否转分期',
                            formatter : function(item, index) {
                                if(item == 'TRANSFER') {
                                    return '是';
                                } else {
                                    return '否';
                                }
                            }
                        },
                        {
                            field: 'endDate',
                            title: '贷款到期日'
                        },
                        {
                            field: 'tppCode',
                            title: '贷款提供商'
                        },
                        {
                            field: 'errorMsg',
                            title: '错误信息'
                        },
                        {
                            field: 'suggest',
                            title: '建议话术'
                        }
                    ]
                });
    }
}

function queryLoan() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#cashLoan').bootstrapTable('destroy');
    var productNo = $("#productNo").val();
    if(productNo == undefined || productNo == 'IOUS'){
        $("#ious").show();
        $('#exampleTable')
            .bootstrapTable(
                {
                    method : 'post', // 服务器数据的请求方式 get or post
                    url : prefix + "/loan-ious", // 服务器数据的加载地址
                    queryParamsType:'',
                    queryParams : queryParams,
                    showRefresh : false,
                    showToggle : false,
                    iconSize : 'outline',
                    //toolbar : '#exampleToolbar',
                    striped : true, // 设置为true会有隔行变色效果
                    dataType : "json", // 服务器返回的数据类型
                    pagination : true, // 设置为true会在底部显示分页条
                    pageSize : 10, // 如果设置了分页，每页数据条数
                    pageNumber : 1, // 如果设置了分布，首页页码
                    search : false, // 是否显示搜索框
                    showColumns : false, // 是否显示内容下拉框（选择显示的列）
                    sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                    columns : [

                        {
                            field : 'productNo', // 列字段名
                            title : '产品线' // 列标题
                        },
                        {
                            field : 'userName',
                            title : '姓名'
                        },
                        {
                            field : 'identity',
                            title : '身份证号'
                        },
                        {
                            field : 'mobile',
                            title : '手机号'
                        },
                        {
                            field : 'orgChannel',
                            title : '业务来源'
                        },
                        {
                            field : 'loanProvideNo',
                            title : '借据号'
                        },
                        {
                            field : 'busiOrderNo',
                            title : '业务线订单号'
                        },
                        {
                            field : 'busiTypeName',
                            title : '业务线'
                        },
                        {
                            field : 'payAmount',
                            title : '订单金额'
                        },
                        {
                            field : 'payStatus',
                            title : '订单状态',
                            formatter : function(item, index) {
                                if(item === "0") {
                                    return '初始';
                                } else if(item === "1") {
                                    return '成功';
                                } else if(item === "2") {
                                    return '失败';
                                } else{
                                    return '';
                                }
                            }
                        },
                        {
                            field : 'payTime',
                            title : '消费时间'
                        },
                        {
                            field : 'debtAmountAll',
                            title : '当前欠款金额',
                            formatter : function(item, index) {
                                if(item === null || item === undefined || item === "") {
                                    return '0.00';
                                } else {
                                    return item;
                                }
                            }
                        },
                        {
                            field : 'loanTerm',
                            title : '分期期数'
                        },
                        {
                            field : 'transFrom',
                            title : '是否转分期',
                            formatter : function(item, index) {
                                if(item != 0 ) {
                                    return '是';
                                } else {
                                    return '否';
                                }
                            }
                        },
                        {
                            field : 'intRate',
                            title : '借款利率'
                        },
                        {
                            field : 'tppCode',
                            title : '贷款提供商'
                        },
                        {
                            field : 'currentOverdueCount',
                            title : '逾期笔数',
                            formatter : function(item, index) {
                                if(item === null || item === undefined || item === "") {
                                    return '0';
                                }else {
                                    return item;
                                }
                            }

                        },
                        {
                            field : 'overDueAmount',
                            title : '逾期金额',
                            formatter : function(item, index) {
                                if(item === null || item === undefined || item === "") {
                                    return '0.00';
                                }else {
                                    return item;
                                }
                            }
                        },
                        {
                            title : '还款计划',
                            field : 'id',
                            align : 'center',
                            width : '8%',
                            formatter : function(value, row, index) {
                                var e = '<a class="btn btn-primary btn-sm '
                                    + '" href="#" mce_href="#" title="查看还款计划" onclick="showSchedule(\''
                                    + row.loanProvideNo
                                    + '\')"><i class="fa fa-list"></i></a> ';
                                return e;
                            }
                        },
                        {
                            title : '业务线详情',
                            field : 'id',
                            align : 'center',
                            width : '8%',
                            formatter : function(value, row, index) {
                                var e = '<a class="btn btn-primary btn-sm '
                                    + '" href="#" mce_href="#" title="查看业务线详情" onclick="showBusiDetail(\''
                                    + row.busiOrderNo + '\' ,  \'' + row.orderTime+ '\' ,  \'' + row.orgChannel
                                    + '\')"><i class="fa fa-list"></i></a> ';
                                return e;
                            }
                        }
                    ]
                });
    }
    else {
        $("#cash").show();
        $('#cashLoan')
            .bootstrapTable(
                {
                    method : 'post', // 服务器数据的请求方式 get or post
                    url : prefix + "/loan-cash", // 服务器数据的加载地址
                    queryParamsType:'',
                    queryParams : queryParams,
                    showRefresh : false,
                    showToggle : false,
                    iconSize : 'outline',
                    //toolbar : '#exampleToolbar',
                    striped : true, // 设置为true会有隔行变色效果
                    dataType : "json", // 服务器返回的数据类型
                    pagination : true, // 设置为true会在底部显示分页条
                    pageSize : 10, // 如果设置了分页，每页数据条数
                    pageNumber : 1, // 如果设置了分布，首页页码
                    //search : true, // 是否显示搜索框
                    //showColumns : true, // 是否显示内容下拉框（选择显示的列）
                    sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                    columns : [

                        {
                            field : 'productNo', // 列字段名
                            title : '产品线' // 列标题
                        },
                        {
                            field : 'userName',
                            title : '姓名'
                        },
                        {
                            field : 'identity',
                            title : '身份证号'
                        },
                        {
                            field : 'mobile',
                            title : '手机号'
                        },
                        {
                            field : 'orgChannel',
                            title : '业务来源'
                        },
                        {
                            field : 'loanProvideNo',
                            title : '借据号'
                        },
                        {
                            field : 'startInterestDate',
                            title : '贷款发放日期'
                        },
                        {
                            field : 'amount',
                            title : '贷款金额'
                        },
                        {
                            field : 'loanRate',
                            title : '借款利率'
                        },
                        {
                            field : 'loanTerm',
                            title : '借款周期'
                        },
                        {
                            field : 'tppCode',
                            title : '贷款提供商'
                        },
                        {
                            field : 'requestTime',
                            title : '贷款申请时间'
                        },
                        {
                            field : 'finishTime',
                            title : '贷款完成时间'
                        },
                        {
                            field : 'status',
                            title : '借款状态',
                            formatter : function(item, index) {
                                if(item === 0) {
                                    return '初始';
                                } else if(item === 1){
                                    return '成功';
                                } else if(item === 2){
                                    return '失败';
                                } else if(item === 3){
                                    return '处理中';
                                } else if(item === 4){
                                    return '已撤销';
                                }
                                else {
                                    return '';
                                }

                            }
                        },
                        {
                            field : 'remitStatus',
                            title : '汇款状态',
                            formatter : function(item, index) {
                                if(item === 0) {
                                    return '初始';
                                } else if(item === 1){
                                    return '成功';
                                } else if(item === 2){
                                    return '失败';
                                } else if(item === 3){
                                    return '处理中';
                                } else if(item === 4){
                                    return '待发起';
                                } else if(item === 5){
                                    return '打款回退';
                                } else {
                                    return '';
                                }
                            }
                        },
                        {
                            field : 'remitFinishTime',
                            title : '打款完成时间'
                        },
                        {
                            field : 'bankName',
                            title : '银行名称'
                        },
                        {
                            field : 'cardNo',
                            title : '银行卡号'
                        },
                        {
                            field : 'errorMsg',
                            title : '错误信息'
                        },
                        {
                            field : 'suggest',
                            title : '建议话术'
                        }
                    ]
                });
    }
}

function queryRepayReq() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#cashLoan').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : prefix + "/repayReq-info", // 服务器数据的加载地址
                showRefresh : true,
                showToggle : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                queryParamsType:'',
                queryParams : queryParams,
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : true, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
                columns : [
                    {
                        field : 'productNo', // 列字段名
                        title : '产品线' // 列标题
                    },
                    {
                        field : 'userName',
                        title : '姓名'
                    },
                    {
                        field : 'userId',
                        title : 'UID'
                    },
                    {
                        field : 'identity',
                        title : '身份证号'
                    },
                    {
                        field : 'mobile',
                        title : '手机号'
                    },
                    {
                        field : 'orgChannel',
                        title : '业务来源'
                    },
                    {
                        field : 'serialNo',
                        title : '还款请求流水'
                    },
                    {
                        field : 'status',
                        title : '订单状态',
                        formatter : function(item, index) {
                            if(item === 0) {
                                return '初始';
                            } else if(item === 1){
                                return '扣款中';
                            } else if(item === 2){
                                return '还款中';
                            } else if(item === 3){
                                return '还款结束';
                            } else if(item === 4){
                                return '流程结束';
                            } else if(item === 5){
                                return '用户层还款中';
                            } else {
                                return '';
                            }
                        }
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
                        field : 'repayAmt',
                        title : '应还总金额'
                    },
                    {
                        field : 'repaySuccAmt',
                        title : '还款成功金额'
                    },
                    {
                        field : 'repayEntry',
                        title : '还款类型'
                    },
                    {
                        title : '还款详情',
                        field : 'id',
                        align : 'center',
                        width : '8%',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="还款详情" onclick="repayDetail(\''
                                + row.serialNo + '\')"><i class="fa fa-list"></i></a> ';
                            return e;
                        }
                    }
                ]
            });
}

function queryParams(params) {
    var orgChannel = $('input:radio[name="orgChannel"]:checked').val();
    if(orgChannel==null || orgChannel==undefined){
        orgChannel = '';
    }
    var productNo = $("#productNo").val();
    if(productNo==null || productNo==undefined){
        productNo = '';
    }
    return {
        mobile: $("#mobile").val(),
        userId: $("#userId").val(),
        identity: $("#identity").val(),
        userName: $("#userName").val(),
        orgChannel: orgChannel,
        productNo: productNo,
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        loanMerchant: $("#loanMerchant").val(),
        tppCode: $("#tppCode").val(),
        pageSize:params.pageSize,
        pageIndex:params.pageNumber
    };
}

function showSchedule(loanProvideNo) {
    layer.open({
        type : 2,
        title : '还款计划',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/schedule/' + loanProvideNo // iframe的url
    });
}

function showBusiDetail(busiOrderNo,orderTime,orgChannel) {
    if(busiOrderNo==null || busiOrderNo==''){
        layer.alert("订单号不存在");
        return false;
    }
    if(orderTime==null || orderTime==''){
        layer.alert("订单时间不存在");
        return false;
    }
    if(orgChannel == 'CTRIP') {
        layer.open({
            type : 2,
            title : '业务线详情',
            maxmin : true,
            shadeClose : false, // 点击遮罩关闭层
            area : [ '800px', '520px' ],
            content : prefix + '/busiDetail?busiOrderNo=' + busiOrderNo + '&orderTime=' + orderTime
        });
    }
    if(orgChannel == 'QUNAR') {
        layer.open({
            type : 2,
            title : '业务线详情',
            maxmin : true,
            shadeClose : false, // 点击遮罩关闭层
            area : [ '800px', '520px' ],
            content : prefix + '/queryQunarBusi/' + busiOrderNo
        });
    }
}

function showPayDetail(orderNo) {
    if(orderNo == null || orderNo == 'null' || orderNo =='') {
        layer.alert("订单号为空！");
        return;
    }
    layer.open({
        type : 2,
        title : '支付详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/payDetail/' + orderNo
    });
}

function modifyMobile(userId,userName,mobile) {
    layer.open({
        type : 2,
        title : '修改号码',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/showMobile?userId=' + userId+ '&userName=' + userName + '&mobile=' + mobile
    });
}

function repayDetail(serialNo) {
    layer.open({
        type : 2,
        title : '还款详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1000px', '680px' ],
        content : prefix + '/repayDetail/' + serialNo
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
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

