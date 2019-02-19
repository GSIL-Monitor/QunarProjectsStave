
var prefix = "/bruce/querySummaryAmount";

$(function() {
    //获取贷款提供商
    queryTppCode();

    // 查询
    $("#queryStatistic").bind("click", function () {
        queryStatistic();
    });

    // 清空
    $("#clearAll").bind("click", function () {
        clearAll();
    });

    //导出文件
    $("#exportFile").bind("click", function () {
        exportFile();
    });
});

// 查询
function queryStatistic() {
    // 校验参数
    if(!checkQueryParam()) {
        return;
    }
    document.getElementById("queryTable").style.display="none";
    document.getElementById("loading").style.display="block";
    $('#icon').addClass('fa fa-circle-o-notch fa-spin fa-3x');
    // 查询表格
    queryStatisticTable();
}
// 查询表格展示
function queryStatisticTable() {
    $('#queryStatisticTable').bootstrapTable('destroy');
    $('#queryStatisticTable').bootstrapTable({
        method : 'post', // 服务器数据的请求方式 get or post
        url : prefix + "/queryStatistic", // 服务器数据的加载地址
        showRefresh : false,
        showToggle : false,
        iconSize : 'outline',
        toolbar : '#exampleToolbar',
        striped : true, // 设置为true会有隔行变色效果
        dataType : "json", // 服务器返回的数据类型
        pagination : false, // 设置为true会在底部显示分页条
        queryParamsType:'',
        queryParams : queryParams,
        pageSize : 40, // 如果设置了分页，每页数据条数
        pageNumber : 1, // 如果设置了分布，首页页码
        search : false, // 是否显示搜索框
        showColumns : true, // 是否显示内容下拉框（选择显示的列）
        sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
        columns : [
            {
                field : 'staticTime',
                title : '时间'
            },
            {
                field : 'tppCode', // 列字段名
                title : '贷款提供方' // 列标题
            },
            {
                field : 'loanBalanceAmt', // 列字段名
                title : '在贷余额' // 列标题
            },
            {
                field : 'loanAmt', // 列字段名
                title : '贷款金额' // 列标题
            },
            {
                field : 'totalRepayAmt', // 列字段名
                title : '还款总额' // 列标题
            },
            {
                field : 'actPrcpAmt', // 列字段名
                title : '还款本金' // 列标题
            },
            {
                field : 'actIntAmt', // 列字段名
                title : '利息' // 列标题
            },
            {
                field : 'actFineAmt', // 列字段名
                title : '罚息' // 列标题
            },
            {
                field : 'actFeeAmt', // 列字段名
                title : '手续费' // 列标题
            },
            {
                field : 'actSpreadsAmt', // 列字段名
                title : '息差' // 列标题
            },
            {
                field : 'overdue90Amt', // 列字段名
                title : '逾期90+' // 列标题
            },
            {
                field : 'overdue60Amt', // 列字段名
                title : '逾期60+' // 列标题
            }
        ],
        onLoadSuccess: function(){  //加载成功时执行
            $('#icon').removeClass('fa fa-circle-o-notch fa-spin fa-3x');
            document.getElementById("loading").style.display="none";
            document.getElementById("queryTable").style.display="block";
            console.info("加载成功");
        },
        onLoadError: function(){  //加载失败时执行
            $('#icon').removeClass('fa fa-circle-o-notch fa-spin fa-3x');
            document.getElementById("loading").style.display="none";
            document.getElementById("queryTable").style.display="block";
            console.info("加载数据失败");
        }
    });
}

//清空
function clearAll() {
    $("#orgChannel option:not(:first)").removeAttr("selected");//渠道来源下拉栏清空还原
    $("#tppCode option:not(:first)").removeAttr("selected");//贷款提供方下拉栏清空还原
    $("#startTime").val("");
    $("#endTime").val("");//清空
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
                $("#tppCode").append("<option value="+data[i].tppCode+">"+data[i].tppCode + "_"+ data[i].tppName+"</option>");
            }
        }
    });
}

//日期相关
Date.prototype.Format = function (fmt) {
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

//查输入条件是否为空
function isEmpty(data) {
    return isNull(data) || $.trim(data) == '';
}

function isNull(data) {
    return data == null || data == undefined;
}

function checkQueryParam() {
    if( true == isEmpty($("#tppCode").val())) {
        layer.alert("tppCode不能为空！");
        return false;
    }
    if(  true == isEmpty($("#startTime").val())) {
        layer.alert("起始时间不能为空！");
        return false;
    }
    if(true ==isEmpty($("#endTime").val()) ) {
        layer.alert("终止时间不能为空！");
        return false;
    }
    if(($("#startTime").val() > $("#endTime").val()) || ($("#startTime").val() == $("#endTime").val())){
        layer.alert("终止时间必须大于起始时间至少一天！");
        return false;
    }
    var startTime = new Date($("#startTime").val()).getTime();
    var endTime = new Date($("#endTime").val()).getTime();
    if(endTime > (startTime + 31*24*3600*1000)){
        layer.alert("时间范围不应超过一个月！");
        return false;
    }
    return true;
}
//传参
function queryParams(params) {
    return {
        orgChannel : $("#orgChannel").val(),
        tppCode : $("#tppCode").val(),
        startTime : $("#startTime").val(),
        endTime : $("#endTime").val(),
    };
}

var isClock = false;
function exportFile() {
    if(isClock == false){
        isClock = true;
        // 校验参数
        if(!checkQueryParam()) {
            isClock = false;
            return;
        }
        var myform = $("<form></form>");
        myform.attr('method','post')
        myform.attr('action',"/bruce/querySummaryAmount/export");

        var orgChannel = $("<input type='hidden' name='orgChannel' />")
        orgChannel.attr('value',$("#orgChannel").val());
        var tppCode = $("<input type='hidden' name='tppCode' />")
        tppCode.attr('value',$("#tppCode").val());
        var startTime = $("<input type='hidden' name='startTime' />")
        startTime.attr('value',$("#startTime").val());
        var endTime = $("<input type='hidden' name='endTime' />")
        endTime.attr('value',$("#endTime").val());

        myform.append(orgChannel);
        myform.append(tppCode);
        myform.append(startTime);
        myform.append(endTime);
        myform.appendTo('body').submit();

        setTimeout(function(){
            isClock = false;
        }, 3000);
    }else{
        layer.alert("操作频繁，请稍后再试！");
    }

}
