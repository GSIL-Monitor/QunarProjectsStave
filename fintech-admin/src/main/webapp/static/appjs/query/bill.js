var prefix = "/finance/bill";

function clearAll() {
    $("#mobile").val("");
    $("#uid").val("");
    $("#startTime").val("");
    $("#endTime").val("");
    $("input:radio[name='orgChannel']").removeAttr('checked');
}

function query() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : prefix + "/queryBillList", // 服务器数据的加载地址
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
                        field : 'billNo', // 列字段名
                        title : '账单流水' // 列标题
                    },
                    {
                        field : 'billStatus',
                        title : '账单状态'
                    },
                    {
                        field : 'billDate',
                        title : '出账时间'
                    },
                    {
                        field : 'updateTime',
                        title : '更新时间'
                    },
                    {
                    	field : 'repayDate',
                    	title : '还款日'
                    },
                    {
                        field : 'userName',
                        title : '用户姓名'
                    },
                    {
                        field : 'userId',
                        title : '用户id'
                    },
                    {
                        field : 'mobile',
                        title : '手机号'
                    },
                    {
                        field : 'billPrcpAmt',
                        title : '出账本金'
                    },
                    {
                        field : 'billIntAmt',
                        title : '出账利息'
                    },
                    {
                        field : 'billFineAmt',
                        title : '违约金'
                    },
                    {
                        title : '账单详情',
                        field : 'id',
                        align : 'center',
                        width : '8%',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="账单详情" onclick="billDetail(\''
                                + row.billNo + '\')"><i class="fa fa-list"></i></a> ';
                            return e;
                        }
                    }
                ]
            });
}


function queryParams(params) {
    return {
        billStatus: $("#billStatus").val(),
        mobile: $("#mobile").val(),
        userId: $("#userId").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        pageSize:params.pageSize,
        pageIndex:params.pageNumber
    };
}

function billDetail(billNo) {
    if(billNo == null || billNo ==''){
        layer.alert("账单流水为空！");
        return;
    }
    layer.open({
        type : 2,
        title : '账单详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1300px', '660px' ],
        content : prefix + '/billDetail/' + billNo // iframe的url
    });
}
