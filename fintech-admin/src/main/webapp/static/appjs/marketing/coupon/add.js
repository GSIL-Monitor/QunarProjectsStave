var prefix = "/marketing/coupon";

// 默认不用填
$(".orgChannel").hide();
$(".productNo").hide();
$(".free-coupon-type").hide();


// 生成券code
var timestamp = Date.parse(new Date());
document.getElementById("couponCode").value = timestamp;

$(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        submit01();
    }
});

function submit01() {
    if($("#startTime").val()>=$("#endTime").val()){
        layer.alert("券结束时间必须大于开始时间！");
        return;
    }
    var type = $("input[name='couponType']:checked").val();
    if(type == "FREE_INT"){
        if($("#freeIntDay").val()==null||$("#freeIntDay").val()=='0'||$("#freeIntDay").val()==''){
            //$("#freeIntDay").val($("#freePeriod").val());
        }
    }
    if(type == "DISCOUNT"){
        if($("#discountRate").val()==""||isNaN($("#discountRate").val())){
            layer.alert("请输入折扣率");
            return;
        }
        $("#freeIntDay").val("0");
        $("#couponAmount").val("0")
    }
    if(type == "CASH"){
        if($("#couponAmount").val()==""||isNaN($("#couponAmount").val())){
            layer.alert("请输入券面额");
            return;
        }
        $("#freeIntDay").val("0");
        $("#discountRate").val("0");
    }

    var formData = new FormData();
    formData.append("activityCode",document.getElementById("activityCode").value);
    formData.append("couponName",document.getElementById("couponName").value);
    formData.append("couponDesc", document.getElementById("couponDesc").value);
    formData.append("couponDetail", document.getElementById("couponDetail").value);
    formData.append("url", document.getElementById("url").value);
    formData.append("couponType", $('input[name="couponType"]:checked').val());

    formData.append("couponDimon",$('input[name="couponDimon"]:checked').val());
    formData.append("freeIntDay", document.getElementById("freeIntDay").value);
    formData.append("discountRate", document.getElementById("discountRate").value);
    formData.append("couponAmount", document.getElementById("couponAmount").value);
    formData.append("maxReduce", document.getElementById("maxReduce").value);
    formData.append("startTime", document.getElementById("startTime").value);
    formData.append("endTime", document.getElementById("endTime").value);

    // 券延长时间如果不填写默认为0
    var delayHours=document.getElementById("delayHours").value;
    if(delayHours){
        formData.append("delayHours",delayHours);
    }else {
        delayHours = 0;
        formData.append("delayHours",delayHours);
    }

    // 券有效时间如果不填写默认为0
    var availableHours=document.getElementById("availableHours").value;
    if(availableHours){
        formData.append("availableHours",availableHours);
    }else {
        availableHours = 0;
        formData.append("availableHours",availableHours);
    }

    if(delayHours > 0 && availableHours == 0 ){
        layer.alert("输入券延迟生效小时，则必须输入券有效小时，否则影响发券功能,请重新输入！");
        return;
    }

    //  拼装规则参数
    var map = "";
    var userUseNum = document.getElementById("userUseNum").value;
    var userCouponNum = document.getElementById("userCouponNum").value;
    var activityCouponNum = document.getElementById("activityCouponNum").value;
    // 暂时只绑定首期
    var freePeriod = "1";

    // 单用户最多使用张数
    if(userUseNum){
        var key = "userUseNum";
        map = "{\""+key+"\":\""+userUseNum+"\"";
    }
    // 单用户最多发放张数
    if(map == ""){
        if(userCouponNum){
            var key = "userCouponNum";
            map =  "{\""+key+"\":\""+userCouponNum+"\"";
        }
    }else {
        if(userCouponNum){
            var key = "userCouponNum";
            map =  map + ",\"" + key +"\":\""+userCouponNum+"\""
        }
    }
    // 活动单日最多发放张数
    if(map == ""){
        if(activityCouponNum){
            var key = "activityCouponNum";
            map =  "{\""+key+"\":\""+activityCouponNum+"\"";
        }
    }else {
        if(activityCouponNum){
            var key = "activityCouponNum";
            map =  map + ",\"" + key +"\":\""+activityCouponNum+"\""
        }
    }

    // 绑定期序
    // 兼容线上问题，立减券不能设置可用期序
    if(type != "RANDOM_REDUCE"){
        if(map == ""){
            if(freePeriod){
                var key = "freePeriod";
                map =  "{\""+key+"\":\""+freePeriod+"\"";
            }
        }else {
            if(freePeriod){
                var key = "freePeriod";
                map =  map + ",\"" + key +"\":\""+freePeriod+"\""
            }
        }
    }

    // 通过携程营销平台发放券校验
    var grantToCtrip = $('input[name="grantByCtrip"]:checked').val();
    var orgChannel = document.getElementById("orgChannel").value
    var productNo = document.getElementById("productNo").value
    var couponCode = document.getElementById("couponCode").value;

    var ctripMarketingRule="";
    if(grantToCtrip == "1"){
        if(couponCode == ""){
            layer.alert("请填写券Code！");
            return;
        }

        if(orgChannel == ""){
            layer.alert("请选择通道！");
            return;
        }
        if(productNo == ""){
            layer.alert("请选择产品！");
            return;
        }

        var key = "grantByCtrip";
        ctripMarketingRule = "{\"" + key + "\":" + grantToCtrip;

        key = "supportOrgChannel";
        ctripMarketingRule = ctripMarketingRule + ",\"" + key + "\":\"" + orgChannel + "\"";

        key = "supportProduct";
        ctripMarketingRule = ctripMarketingRule + ",\"" + key + "\":\"" + productNo + "\"";
        ctripMarketingRule = ctripMarketingRule + "}";

        key = "ctripMarketingRule";
        map = map + ",\"" + key + "\":" + ctripMarketingRule + "";
    }

    if(map){
        map = map + "}";
    }

    debugger;
    formData.append("couponCode", couponCode);
    formData.append("ruleParams", map);
    ///////////////////////////////////

    formData.append("instructions", document.getElementById("instructions").value);

    var ext="";
    var msgUrlValue = document.getElementById("msgUrl").value;
    var msgContentValue = document.getElementById("msgContent").value;
    var ruleTipsValue = document.getElementById("ruleTips").value;

    // 发送短信地址、短信内容、以及拿去花券展示页面适用于字段
    if(msgUrlValue) {
        var key = "msgUrl";
        ext = "{\"" + key + "\":\"" + msgUrlValue + "\"";
    }

    if(ext == ""){
        if(msgContentValue){
            var key = "couponContent";
            ext = "{\"" + key + "\":\"" + msgContentValue + "\"";
        }
    }else{
        if(msgContentValue){
            var key = "couponContent";
            ext = ext+",\"" + key + "\":\"" + msgContentValue + "\"";
        }
    }

    if(ext == ""){
        if(ruleTipsValue) {
            var key = "ruleTips";
            ext = "{\"" + key + "\":\"" + ruleTipsValue + "\"";
        }
    }else {
        if(ruleTipsValue) {
            var key = "ruleTips";
            ext = ext + ",\"" + key + "\":\"" + ruleTipsValue + "\"";
        }
    }

    if(ext){
        ext = ext + "}";
    }

    // 用户感知的情况下
    // 短信地址和短信内容不能为空
    // 用户感知的情况下
    // 短信地址和短信内容不能为空
    var userPercept = $('input[name="userPercept"]:checked').val();
    if(userPercept == 0){
        if(msgUrlValue == ""){
            layer.alert("请输入短信地址！");
            return;
        }
        if(msgContentValue == ""){
            layer.alert("请输入短信内容！");
            return;
        }
    }

    formData.append("ext", ext);
    formData.append("userPercept",$('input[name="userPercept"]:checked').val());
    formData.append("autoUnUse", $('input[name="autoUnUse"]:checked').val());
    formData.append("totalNum", document.getElementById("totalNum").value);
    formData.append("totalAmt", document.getElementById("totalAmt").value);
    formData.append("couponScene", $('input[name="couponScene"]:checked').val());

    // 操作人
    var adminName = sessionStorage.getItem("adminName");
    formData.append("adminName", adminName);

    $.ajax({
        cache : true,
        type : "POST",
        url : prefix + "/save",
        processData : false,
        contentType : false,
        data :formData,
        async : false,
        error : function(request) {
            layer.alert("添加失败");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("等待审核");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                layer.alert(data.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            couponName : {
                required : true
            },
            couponType : {
                required : true
            },
            startTime : {
                required : true
            },
            endTime : {
                required : true
            },
            totalNum : {
                required : true,
                digits : true,
                min : 0
            },
            totalAmt : {
                required : true,
                min : 0
            },
            userPercept : {
                required : true
            },
            autoUnUse : {
                required : true
            },
            couponScene : {
                required : true
            },
            freePeriod : {
                required : true
            }
        },
        messages : {
            couponName : {
                required : icon + "请输入券简称"
            },
            couponType : {
                required : icon + "请选择券状态"
            },
            startTime : {
                required : icon + "请输入券开始时间"
            },
            endTime : {
                required : icon + "请输入券结束时间"
            },
            totalNum : {
                required : icon + "请输入券总数量",
                digits : "请输入正整数",
                min : $.validator.format("请输入不小于 {0} 的正整数")
            },
            totalAmt : {
                required : icon + "请输入券总金额",
                min : $.validator.format("请输入不小于 {0} 的数值")
            },
            userPercept : {
                required : icon + "请选择是否用户感知"
            },
            autoUnUse : {
                required : icon + "请选择是否自动撤销"
            },
            couponScene : {
                required : icon + "请输入使用场景"
            },
            freePeriod : {
                required : icon + "请选择绑定期序"
            }
        }
    })

}

function showMore(val) {
    $(".coupon-detail").hide();
    $(".maxReduce").hide();
    $(".free-coupon-type").hide();
    $("#maxReduce").val('');
    if(val == "FREE_INT") {
        $(".free-coupon-type").show();
        $(".free-int").show();
        $(".maxReduce").show();
    } else if(val == "DISCOUNT") {
        $(".discount").show();
    } else if(val == "CASH") {
        $(".cash").show();
    }
}

function showGrantToCtripRule(val) {
    if(val == "0") {
        $(".orgChannel").hide();
        $(".productNo").hide();
        $("#couponCode").attr("readonly",'readonly');
    } else {
        $(".orgChannel").show();
        $(".productNo").show();
        $("#couponCode").removeAttr("readonly");
    }
}

function showFreeCouponParam(val) {
    $(".coupon-detail").hide();
    $(".maxReduce").hide();
    $("#maxReduce").val('');
    if(val == "FREE_COUPON_BY_DAY") {
        $(".free-int").show();
        $(".maxReduce").show();
    } else if(val == "FREE_COUPON_BY_DISCOUNT") {
        $(".discount").show();
        $(".maxReduce").show();
    } else if(val == "FREE_COUPON_BY_UNIT") {
        $(".cash").show();
        $(".maxReduce").show();
    }
}


function showDay(val) {
    $("#maxReduce").val('');
    $(".maxReduce").hide();
    $(".free-coupon-type").hide();
    if(val != "30") {
        $(".free-int").hide();
        $(".cash").hide();
        $("#freeIntDay").val('');
        $("#discountRate").val('');
        $("#cash").val('');
        $(".maxReduce").show();
    } else {
        $(".free-int").show();
        $(".cash").show();
        $(".maxReduce").hide();
        $("#freeIntDay").val('');
        $("#discountRate").val('');
        $("#cash").val('');
    }
}
