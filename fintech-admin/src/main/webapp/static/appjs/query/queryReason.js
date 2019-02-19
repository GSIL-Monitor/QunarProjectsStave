var prefix = "/finance/cantUse";

function query(){
    $('#exampleTable').bootstrapTable('destroy');
	var mobile = $("#mobile").val();
    var userId = $("#userId").val();
    var identity = $("#identity").val();
    var orgChannel = $("input:radio[name='orgChannel']:checked").val();
    var productNo = $("#productNo").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
	if((mobile == null || mobile == '') && (userId == null || userId == '') && (identity == null || identity == '')){
        layer.alert("uid，手机号和身份证号至少填一个");
        return false;
	}
	if(orgChannel == null || orgChannel =="") {
	    layer.alert("请选择业务来源！");
	    return false;
    }
    if(productNo == null || productNo =="") {
        layer.alert("请选择产品线！");
        return false;
    }
    if(startTime == null || startTime == '') {
	    layer.alert("请输入开始时间！");
	    return false;
    }
    if(endTime == null || endTime == '') {
        layer.alert("请输入结束时间！");
        return false;
    }
    if(startTime >= endTime) {
	    layer.alert("开始时间不能晚于结束时间！");
	    return false;
    }
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : prefix + "/reason", // 服务器数据的加载地址
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
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者"server"
                columns : [
                    {
                        field : 'userId', // 列字段名
                        title : '用户ID' // 列标题
                    },
                    {
                        field : 'orgChannel',
                        title : '业务来源'
                    },
                    {
                        field : 'productNo',
                        title : '产品线'
                    },
                    {
                        field : 'busiOrderNo',
                        title : '业务线订单号',
                        formatter : function(item, index) {
                            if(item == 'NA') {
                                return '';
                            }
                        }
                    },
                    {
                        field : 'createTime',
                        title : '发起时间'
                    },
                    {
                        field : 'errorCode',
                        title : '错误码'
                    },
                    {
                        field : 'errorMsg',
                        title : '错误信息'
                    }
                ]
            });
}

function queryParams() {
    return {
        mobile: $("#mobile").val(),
        userId: $("#userId").val(),
        identity: $("#identity").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        orgChannel: $("input:radio[name='orgChannel']:checked").val(),
        productNo: $("#productNo").val()
    };
}

function clearAll() {
    $("#mobile").val("");
    $("#identity").val("");
    $("#uid").val("");
    $("#startTime").val("");
    $("#endTime").val("");
    $("input:radio[name='orgChannel']").removeAttr('checked');
}
