var prefix = "/finance/bill";

$(function() {
    $.ajax({
        url: prefix + '/getTpp',
        data:{},
        type:'get',
        cache:false,
        dataType:'json',
        success:function(data) {
            for(var i=0;i<data.length;i++){
                $("#minRepayTpp").append("<option value="+data[i]+">"+data[i]+"</option>");
            }
        }
    });
});

function clearAll() {
    $("#mobile").val("");
    $("#uid").val("");
    $("#startDate").val("");
    $("#endDate").val("");
}

function query() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : prefix + "/queryMinRepayOrder", // 服务器数据的加载地址
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
                        field : 'serialNo', // 列字段名
                        title : '转债流水' // 列标题
                    },
                    {
                        field : 'processStatus',
                        title : '转债状态',
                        formatter : function(item, index) {
                            if(item === 0) {
                                return '初始';
                            } else if(item === 1) {
                                return '成功';
                            } else if(item === 2) {
                                return '失败';
                            } else if(item === 3) {
                                return '处理中';
                            } else if(item === 4) {
                                return '先失败后成功';
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field : 'createTime',
                        title : '转债发起时间'
                    },
                    {
                        field : 'userName',
                        title : '姓名'
                    },
                    {
                    	field : 'userId',
                    	title : '用户UID'
                    },
                    {
                        field : 'mobile',
                        title : '手机号'
                    },
                    {
                        field : 'totalTransAmount',
                        title : '合并转债金额'
                    },
                    {
                        field : 'newTppcode',
                        title : '接受通道'
                    },
                    {
                        title : '详情',
                        field : 'id',
                        align : 'center',
                        width : '8%',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="详情" onclick="minDetail(\''
                                + row.serialNo + '\')"><i class="fa fa-list"></i></a> ';
                            return e;
                        }
                    }
                ]
            });
}


function queryParams(params) {
    return {
        processStatus: $("#processStatus").val(),
        minRepayTpp: $("#minRepayTpp").val(),
        mobile: $("#mobile").val(),
        userId: $("#userId").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
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

function minDetail(serialNo) {
    if(serialNo == null || serialNo ==''){
        layer.alert("转债流水为空！");
        return;
    }
    layer.open({
        type : 2,
        title : '详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '1300px', '660px' ],
        content : prefix + '/minDetail/' + serialNo // iframe的url
    });
}

