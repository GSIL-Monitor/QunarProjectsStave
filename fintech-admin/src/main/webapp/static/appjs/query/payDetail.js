var prefix = "/finance/query";

$(function () {
    var orderNo = $("#orderNo").val();
    $.ajax({
        cache : true,
        type : "get",
        url : prefix + "/queryPayDetail/" + orderNo,
        async : false,
        error : function(request) {
            layer.alert("查询失败");
        },
        success : function(data) {
            if(data == null || data == ''){
                $("#tips").html("无支付数据");
                return;
            }
            var date = new Date(data.createTime);
            var createTime = date.Format("yyyy-MM-dd HH:mm:ss");
            console.log(createTime);
            $('iframe').attr('src','http://tradeadmin.corp.qunar.com/orderInfo/queryPayInfo.do?orderNo='+data.orderNo+
                '&batchOrderId='+data.batchOrderId+'&orderTime='+createTime+'&batchOrderNo='+data.batchOrderNo);

        }
    });
});

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