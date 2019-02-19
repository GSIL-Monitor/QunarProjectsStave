var prefix = "/marketing/coupon"

$(function() {
	validateRule();
	var type = $("input[name='couponType']:checked").val();
	showMore(type);
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function update() {
    if($("#startTime").val()>=$("#endTime").val()){
        layer.alert("券结束时间必须大于开始时间！");
        return;
    }
    var type = $("input[name='couponType']:checked").val();
    if(type == "FREE_INT"){
        if($("#freeIntDay").val()==null||$("#freeIntDay").val()=='0'||$("#freeIntDay").val()==''){
            if (parseFloat($("#couponAmount").val()) == 0) {
                $("#freeIntDay").val($("#freePeriod").val());
            }
            debugger
            console.log($("#couponAmount").val())
            if (parseFloat($("#couponAmount").val()) == 0) {
                $("#freeIntDay").val($("#freePeriod").val());
            }
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
    formData.append("id",document.getElementById("id").value);
    formData.append("activityCode",document.getElementById("activityCode").value);
    formData.append("couponCode",document.getElementById("couponCode").value);
    formData.append("couponName",document.getElementById("couponName").value);
    formData.append("couponDesc", document.getElementById("couponDesc").value);
    formData.append("couponDetail", document.getElementById("couponDetail").value);
    formData.append("instructions", document.getElementById("instructions").value);
    formData.append("url", document.getElementById("url").value);
    formData.append("couponType", $('input[name="couponType"]:checked').val());
    formData.append("couponDimon", $('input[name="couponDimon"]:checked').val());

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
        map = "{\""+key+"\":\""+userUseNum+"\" ";
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

    if(map){
        map = map + "}";
    }
    formData.append("ruleParams", map);

    formData.append("freeIntDay", document.getElementById("freeIntDay").value);
    formData.append("discountRate", document.getElementById("discountRate").value);
    formData.append("couponAmount", document.getElementById("couponAmount").value);
    formData.append("startTime", document.getElementById("startTime").value);
    formData.append("endTime", document.getElementById("endTime").value);
    formData.append("totalAmt", document.getElementById("totalAmt").value);
    formData.append("totalNum", document.getElementById("totalNum").value);

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
    formData.append("autoUnUse", $('input[name="autoUnUse"]:checked').val());
    formData.append("userPercept",$('input[name="userPercept"]:checked').val());
    formData.append("couponScene", $('input[name="couponScene"]:checked').val());

    // 操作人
    var adminName = sessionStorage.getItem("adminName");
    formData.append("adminName", adminName);

    	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/update",
        processData : false,
        contentType : false,
        data :formData,
		//data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			layer.alert("Connection error");
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
            couponDimon : {
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
            }
        }
	})
}

function showMore(val) {
    $(".coupon-detail").hide();
    if(val == "FREE_INT") {
        $(".coupon-detail").show();
    } else if(val == "DISCOUNT") {
        $(".discount").show();
    } else if(val == "CASH") {
        $(".cash").show();
    } else {
        //随机立减
    }
}

function showDay(val) {
    if(val != "30") {
        $(".free-int").hide();
        $(".cash").hide();
        $("#freeIntDay").val('');
    } else {
        $(".free-int").show();
        $(".cash").show();
    }
}