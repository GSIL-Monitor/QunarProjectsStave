var prefix = "/finance/query";

function clearAll() {
    $("#mobile").val("");
    $("#uid").val("");
    $("#startTime").val("");
    $("#endTime").val("");
}

function query() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : prefix + "/queryUserRepayWithhold", // 服务器数据的加载地址
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
                        field : 'reqSerialNo', // 列字段名
                        title : '用户还款请求流水' // 列标题
                    },
                    {
                        field : 'userId',
                        title : 'UID'
                    },
                    {
                        field : 'userName',
                        title : '姓名'
                    },
                    {
                        field : 'mobile',
                        title : '手机号'
                    },
                    {
                    	field : 'orderNo',
                    	title : '交易订单号'
                    },
                    {
                        field : 'orderDate',
                        title : '交易日期'
                    },
                    {
                        field : 'orderAmount',
                        title : '订单金额'
                    },
                    {
                        field : 'refAmount',
                        title : '已退金额'
                    },
                    {
                        field : 'payStatus',
                        title : '扣款状态',
                        formatter : function(item, index) {
                            if(item == "0") {
                                return '处理中';
                            } else if(item == "1") {
                                return '支付成功';
                            } else if(item == "2") {
                                return '支付失败';
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field : 'pmCode',
                        title : '支付方式'
                    },
                    {
                        field : 'tppCode',
                        title : '支付通道'
                    },
                    {
                        field : 'payId',
                        title : '支付流水'
                    },
                    {
                        field : 'createTime',
                        title : '生成时间'
                    },
                    {
                        field : 'updateTime',
                        title : '更新时间'
                    },
                    {
                        title : '支付详情',
                        field : 'id',
                        align : 'center',
                        width : '8%',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="支付详情" onclick="minDetail(\''
                                + row.orderNo + '\')"><i class="fa fa-list"></i></a> ';
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
    return {
        mobile: $("#mobile").val(),
        userId: $("#userId").val(),
        orderNo: $("#orderNo").val(),
        reqSerialNo: $("#reqSerialNo").val(),
        orgChannel: orgChannel,
        status: $("#status").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        pageSize:params.pageSize,
        pageIndex:params.pageNumber
    };
}

function minDetail(orderNo) {
    if(orderNo == null || orderNo ==''){
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

