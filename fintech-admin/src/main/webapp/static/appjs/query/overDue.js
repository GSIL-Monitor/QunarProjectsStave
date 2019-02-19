var prefix = "/finance/query";

$(function() {
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
    $("#send").bind("click", function () {
        sendMsg()
    });
});

function clearAll() {
    $("#mobile").val("");
    $("#uid").val("");
    $("input:radio[name='orgChannel']").removeAttr('checked');
}

function query() {
    queryOverDue();
}


function queryOverDue() {
    if($("#userId").val()==''&&$("#mobile").val()==''){
        layer.alert("用户uid和手机号至少须填一项！");
        return false;
    }
    var orgChannel = $('input:radio[name="orgChannel"]:checked').val();
    var productNo = $("#productNo").val();
    if(orgChannel==undefined){
        layer.alert("请选择业务来源！");
        return false;
    }
    if(productNo==undefined){
        layer.alert("请选择产品线！");
        return false;
    }
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'post', // 服务器数据的请求方式 get or post
                url : "/finance/overDue/queryOverdueRepayPlan", // 服务器数据的加载地址
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
                columns: [
                    {
                        checkbox : true
                    },
                    {title: '催收短信状态', field: 'msgStatus'},
                    {title: '用户UID', field: 'userId' },
                    {title: '姓名', field: 'userName' },
                    {title: '手机号', field: 'mobile' },
                    {title: '渠道', field: 'orgChannel',
                        formatter: function (item, index)
                        {
                            if (item.orgChannel == "CTRIP") {
                                return "携程";
                            } else {
                                return "去哪儿网";
                            }
                        }
                    },
                    {title: '产品编号', field: 'productNo',
                        formatter: function (item, index)
                        {
                            if (item.productNo == "BUSI") {
                                return "商旅贷";
                            } else if (item.productNo == "CASH") {
                                return "借趣花";
                            } else if (item.productNo == "IOUS"){
                                return "拿去花";
                            } else if (item.productNo == "MART"){
                                return "贷款超市";
                            } else if (item.productNo == "ELITE"){
                                return "精英白领贷";
                            } else if (item.productNo == "BOSS") {
                                return "小企业主贷";
                            } else {
                                return "";
                            }

                        }
                    },
                    {title: '贷款通道', field: 'tppCode'},
                    {title: '贷款单号', field: 'loanProvideNo'},
                    {title: '贷款日期', field: 'createTime'},
                    {title: '到期日', field: 'dueDate'},
                    {title: '逾期天数', field: 'overDueDays'},
                    {title: '分期期数', field: 'term'},
                    {title: '总还款金额', field:'repayTotalAmt'},
                    {title: '本金金额', field: 'prcpAmt'},
                    {title: '服务费', field: 'intAmt'},
                    {title: '逾期罚息', field: 'fineAmt'},
                    {title: '利差', field: 'spreadsAmt'},
                    {title: '锁定状态', field: 'lockStatus',
                        formatter: function (item, index)
                        {
                            if (item === 0) {
                                return "未锁定";
                            } else if (item === 1) {
                                return "还款锁定";
                            } else if (item === 2) {
                                return "退款锁定";
                            } else if (item === 3) {
                                return "转分期锁定";
                            } else if (item === 4) {
                                return "保还锁定";
                            } else if (item === 5) {
                                return "提前还款锁定";
                            }
                        }
                    }
                ]
                // width: '97%',
                // rownumbers:true,
                // delayLoad:true,
                // isChecked: f_isChecked,
                // onCheckRow: f_onCheckRow,
                // onCheckAllRow: f_onCheckAllRow,
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

function sendMsg() {
    var rows = $("#exampleTable").bootstrapTable('getSelections', function (row) {
        return row;
    });
    var reqList = [];
    for (var i = 0; i < rows.length; i++) {
        if (rows[i] == null)
            continue;
        if (rows[i].lockStatus != "0") {
            alert("有逾期订单为正在催收中");
            return;
        }
        var dueDate = rows[i].dueDate;
        if(dueDate!='' || dueDate!=null){
            // rows[i].dueDate = dueDate.substring(0,10);
            dueDate = getTimeStamp(dueDate);
        }
        reqList.push({
            userId: rows[i].userId, loanProvideNo: rows[i].loanProvideNo,
            productNo: rows[i].productNo, orgChannel: rows[i].orgChannel,
            dueDate: dueDate.toString()
        });
    }
    if (rows.length >= 1) {
        layer.open({
            type: 2,
            title: "发送催收短信",
            closeBtn: 1,
            maxmin: 1,
            shade: [0],
            area: ['800px', '520px'],
            content: '/finance/overDue/checkSelectedRepayPlan?repayReqVoListStr=' + JSON.stringify(reqList).replace(/\"/g,"")
        });
    }
}

function getTimeStamp(time){
    time = time.replace(/-/g,'/');
    var date = new Date(time);
    return date.getTime();
}






