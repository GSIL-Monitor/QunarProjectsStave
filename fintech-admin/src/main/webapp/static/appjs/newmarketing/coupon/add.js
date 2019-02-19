var prefix = "/marketing/activity"
var formData = new FormData();

$(function () {
    validateRule();
    navInit();
    initTicketType();
});

function fileCon() {
    console.log(this.files)
}

var atimestamp = Date.parse(new Date());
$("#activityCode").val(atimestamp);
var ctimestamp = Date.parse(new Date());
$("#couponCode").val(ctimestamp);

//切换导航项
function chooseNav(e, pageNum) {
    var navNum;
    if (e) {
        navNum = e.target.className.substr(-1, 1);
    } else {
        navNum = pageNum
    }
    for (var i = 1; i <= 4; i++) {
        $('.nav-' + i).hide();
        $('.nav-tab' + i).parent().removeClass('active');
    }
    $('.nav-' + navNum).show();
    $('.nav-tab' + navNum).parent().addClass('active');
    if (navNum == 4) {
        $('.check-area').removeClass('hide')
        $('.check-area').addClass('show')
    } else {
        $('.check-area').removeClass('show')
        $('.check-area').addClass('hide')
    }
}

//初始化导航项
function navInit() {

    for (var i = 1; i <= 4; i++) {
        $('.nav-' + i).hide();
    }
    $('.nav-1').show();
}

//初始化优惠券类型
function initTicketType() {
    $('.ticketType').hide();
    $('.interestItem').hide();
    $('.maxFreeInterest').hide();
    $('.send-channel').hide()
    $("#interestTypeSelect input").click(function () {
        var showTicket = $('#interestTypeSelect input:radio:checked').val();
        $('.interestItem').each(function () {
            var self = $(this);
            if (showTicket === '') {
                self.hide();
                $('.maxFreeInterest').hide();
            } else if (self[0].className.indexOf(showTicket) < 0) {
                self.hide();
            } else {
                self.show();
                $('.maxFreeInterest').show();
            }
        })
    })
}

//改变优惠券类型
function changeTicketType() {
    var showTicket = $('#changeTicket').val();
    $('.ticketType').each(function () {
        var self = $(this);
        if (self[0].className.indexOf(showTicket) < 0 || showTicket === '') {
            self.hide();
        } else {
            self.show();
        }
    })
}

//改变券纬度
function changeSendChannel() {
    var latitude = $('#ticketLatitude').val();
    if (latitude === 'PLAT') {
        $('.send-channel').show()
    } else {
        $('.send-channel').hide()
    }
}

function submitCoupon() {
    debugger;
    console.log(formData.get("noticeQunarPublic"));
    console.log(formData.get("noticeUserByMsg"));
    $.ajax({
        cache : false,
        type : "POST",
        url :"/newmarketing/coupon/addOrUpdateCoupon",
        data :formData,
        dataType : 'json',
        mimeType: "multipart/form-data",
        processData:false,//告诉jQuery不要去处理发送的数据
        contentType: false,// 告诉jQuery不要去设置Content-Type请求头
        async : false,
        error : function(request) {
            layer.alert("发券失败");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                layer.alert(data.msg);
            }
        }
    });
}

// function submit01() {
//     if($("#startTime").val()>=$("#endTime").val()){
//         layer.alert("活动结束时间必须大于开始时间！");
//         return;
//     }
// 	$.ajax({
// 		cache : true,
// 		type : "POST",
// 		url : prefix + "/save",
// 		data : $('#signupForm').serialize(),
// 		async : false,
// 		error : function(request) {
//             layer.alert("添加失败");
// 		},
// 		success : function(data) {
// 			if (data.code == 0) {
//                 parent.layer.msg("添加成功");
// 				parent.reLoad();
// 				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
// 				parent.layer.close(index);

// 			} else {
//                 layer.alert(data.msg);
// 			}
// 		}
// 	});
// }
//关闭当前页面
function closeNow() {
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(index); //再执行关闭
    return false;
}

function openImgTag() {
    layer.open({
        type: 1,
        title: '编辑活动',
        maxmin: true,
        shadeClose: false,
        area: ['400px', '500px'],
        content: '<img src="../../../../resources/img/marketing/adminHint.png"/><img src="../../../../resources/img/marketing/adminHint2.png"/>'
    });
}

function copyOldActivity() {
    layer.open({
        type: 2,
        title: '复制已有活动',
        shadeClose: false,
        area: ['480px', '500px'],
        content: './copyTicket.html'
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm1").validate({
        rules: {
            // activityCode : {
            // 	required : true
            // },
            // activityName : {
            // 	required : true
            // },
            // activityDesc : {
            // 	required : true
            // },
            // userDesc : {
            // 	required : true
            // },
            // userFeature : {
            // 	required : true
            // },
            // activityTotalAmt : {
            //     required : true
            // }
        },
        messages: {
            // activityCode : {
            // 	required : icon + "请输入活动code"
            // },
            // activityName : {
            // 	required : icon + "请输入活动简称"
            // },
            // activityDesc : {
            //     required : icon + "请选择活动概述"
            // },
            // activityAim : {
            //     required : icon + "请输入活动目标"
            // },
            // activityCustomers : {
            //     required : icon + "请输入目标客群特征描述"
            // },
            // totalBudget : {
            //     required : icon + "请选择活动总预算"
            // }
        },
        submitHandler: function () {
            chooseNav(null, 2);
            formData.set("requestMenuId", 121);
            formData.set("reviewNo", Date.parse(new Date()));
            formData.set("activityCode", $('#activityCode').val());
            formData.set("activityName", $('#activityName').val());
            formData.set("activityDesc", $('#activityDesc').val());
            formData.set("userDesc", $('#userDesc').val());
            formData.set("userFeature", $('#userFeature').val());
            formData.set("activityTotalAmt", $('#activityTotalAmt').val());
            formData.set("userInfoFile", $('#userInfoFile')[0].files[0]);
            // console.log(formData.get("userInfoFile"))
        }
    })

    $("#signupForm2").validate({
        rules: {
            // couponTotalAmt : {
            // 	required : true
            // },
            // expectedGrantedNum : {
            //     required : true
            // },
            // expectedUsedNum : {
            //     required : true
            // },
            // marketingAccountName : {
            //     required : true
            // },
            // couponStartTime : {
            //     required : true
            // },
            // couponEndTime : {
            //     required : true
            // }
        },
        messages: {
            // ticketBudget : {
            // 	required : icon + "请输入费用预算"
            // },
            // ticketPlanGiveNum : {
            //     required : icon + "请输入券预计发放数量"
            // },
            // ticketPlanUseNum : {
            //     required : icon + "请输入券预计使用数量"
            // },
            // marketingAccount : {
            //     required : icon + "请输入营销账户"
            // },
            // startTime : {
            //     required : icon + "请选择活动开始时间"
            // },
            // endTime : {
            //     required : icon + "请选择活动结束时间"
            // }
        },
        submitHandler: function () {
            chooseNav(null, 3)
            formData.set("couponTotalAmt", $('#couponTotalAmt').val());
            formData.set("couponTotalNum", $('#couponTotalNum').val());
            formData.set("couponExpectedUsedNum", $('#couponExpectedUsedNum').val());
            formData.set("activityAccountName", $('#activityAccountName').val());
            formData.set("couponStartTime", $('#couponStartTime').val());
            formData.set("couponEndTime", $('#couponEndTime').val());
            // console.log(formData.get("userInfoFile"))
            // console.log(formData.get("couponStartTime"))
        }
    })

    $("#signupForm3").validate({
        rules: {
            // couponName : {
            //     required : true
            // },
            // instructions : {
            //     required : true
            // },
            // useState : {
            //     required : true
            // },
            // ruleTips : {
            //     required : true
            // },
            // useUrl : {
            //     required : true
            // },
            // couponType : {
            //     required : true
            // },
            // freeIntDays : {
            //     required : true
            // },
            // msgUrl : {
            //     required : true
            // },
            // msgContent : {
            //     required : true
            // },
            // interestTypeSelect: {
            //     required : true
            // },
            // availableDays: {
            //     required : true
            // },
            // freeInterestRate: {
            //     required : true
            // },
            // discountRate: {
            //     required : true
            // },
            // couponAmount: {
            //     required : true
            // },
            // noticeUserByMsg: {
            //     required : true
            // },
            // noticeQunarPublic: {
            //     required : true
            // },
            // maxFreeAmt: {
            //     required : true
            // }
        },
        messages: {
            // ticketName : {
            //     required : icon + "请输入券简称"
            // },
            // ticketDes : {
            //     required : icon + "请输入券使用说明"
            // },
            // useState : {
            //     required : icon + "请输入券使用说明"
            // },
            // applyTo : {
            //     required : icon + "请输入券适用于"
            // },
            // ticketUrl : {
            //     required : icon + "请输入券跳转地址"
            // },
            // changeTicket : {
            //     required : icon + "请输入券类型"
            // },
            // ticketValidDays : {
            //     required : icon + "请输入券有效天数"
            // },
            // messageUrl : {
            //     required : icon + "请输入短信内地址"
            // },
            // messageCon : {
            //     required : icon + "请输入短信内容"
            // }
        },
        submitHandler: function () {
            chooseNav(null, 4)
            formData.set("couponCode", $('#couponCode').val());
            formData.set("couponName", $('#couponName').val());
            formData.set("couponDesc", $('#couponDesc').val());
            formData.set("instructions", $('#instructions').val());
            formData.set("ruleTips", $('#ruleTips').val());
            formData.set("couponUseUrl", $('#couponUseUrl').val());
            formData.set("freeIntDays", $('#freeIntDays').val());
            formData.set("discountRate", $('#discountRate').val());
            formData.set("couponAmount", $('#couponAmount').val());
            formData.set("maxReduceAmount", $('#maxReduceAmount').val());
            formData.set("minReduceAmount", $('#minReduceAmount').val());
            formData.set("avgReduceAmount", $('#avgReduceAmount').val());
            formData.set("reduceRate", $('#reduceRate').val());
            formData.set("maxFreeAmt", $('#maxFreeAmt').val());
            formData.set("delayDays", $('#delayDays').val());
            formData.set("availableDays", $('#availableDays').val());
            formData.set("dayGrantNumLimit", $('#dayGrantNumLimit').val());
            formData.set("userGrantNumLimit", $('#userGrantNumLimit').val());
            formData.set("userUseNumLimit", $('#userUseNumLimit').val());
            formData.set("msgUrl", $('#msgUrl').val());
            formData.set("msgContent", $('#msgContent').val());
            //免息券类型
            if ($('#changeTicket').val() === "free-interest") {
                formData.set("couponType", 0)
            } else if ($('#changeTicket').val() === "cash-ticket") {
                formData.set("couponType", 2)
            } else if ($('#changeTicket').val() === "random-minus") {
                formData.set("couponType", 3)
            }
            formData.set("repayIndexList", $(".useScene-area :checked").val());
            formData.set("supportProductNoList", $(".lineSelect :checked").val());
            formData.set("couponDimon", $("#ticketLatitude").val());

            formData.set("noticeUserByMsg", $(".send-msg :checked").val());
            formData.set("noticeQunarPublic", $(".qunar-show :checked").val());
            var sendChanelChecked = $(".send-channel :checked");
            var supportOrgChannelList = [];
            for (var i = 0; i < sendChanelChecked.length; i++) {
                supportOrgChannelList.push(sendChanelChecked[i].value)
            }
            formData.set("supportOrgChannelList", supportOrgChannelList);

            // console.log(formData.get("repayIndexList"))
            // console.log(formData.get("supportProductNoList"))
            // console.log(formData.get("couponDimon"))
            // console.log(formData.get("supportOrgChannelList"))
            // console.log(formData.get("noticeUserByMsg"))
            // console.log(formData.get("noticeQunarPublic"))
            // console.log(formData.get("couponType"))

        }
    });

    $("#editAlarmConfig").validate({
        rules: {
            receiver: {
                required: true
            },
            notifyInterval: {
                required: true
            },
            startTime: {
                required: true
            },
            endTime: {
                required: true
            },
            amount: {
                required: true
            },
            remark: {
                required: true
            }
        },
        messages: {
            // activityName : {
            // 	required : icon + "请输入活动简称"
            // },
            // switchStatus : {
            //     required : icon + "请选择活动状态"
            // },
            // startTime : {
            //     required : icon + "请输入活动开始时间"
            // },
            // endTime : {
            //     required : icon + "请输入活动结束时间"
            // },
            // totalNum : {
            //     required : icon + "请输入券总数量",
            //     digits : "请输入正整数",
            //     min : $.validator.format("请输入不小于 {0} 的正整数")
            // },
            // totalAmt : {
            //     required : icon + "请输入券总金额",
            //     min : $.validator.format("请输入不小于 {0} 的数值")
            // },
            // newAccount : {
            //     required : icon + "请选择是否新账务"
            // }
        },
        submitHandler: function () {
            // formData.set("operType", document.getElementById("operType").value);
            // formData.set("activityId", document.getElementById("activityId").value);
            formData.set("accountAlarmReceiver", document.getElementById("receiver").value);

            var alertMode = 0;
            var qtalkModel = 1;
            if ($("input[id='qtalk']").is(':checked')) {
                alertMode = alertMode + qtalkModel;
            }

            var smsModel = 2;
            if ($("input[id='sms']").is(':checked')) {
                alertMode = alertMode + smsModel;
            }

            var qmqModel = 4;
            if ($("input[id='qmq']").is(':checked')) {
                alertMode = alertMode + qmqModel;
            }

            var emailModel = 8;
            if ($("input[id='email']").is(':checked')) {
                alertMode = alertMode + emailModel;
            }

            formData.set("accountAlarmNoticeMethod", alertMode);
            formData.set("accountAlarmExtEmailReceiver", document.getElementById("extEmailReceiver").value);
            formData.set("accountAlarmExtMobileReceiver", document.getElementById("extMobileReceiver").value);
            formData.set("accountAlarmNotifyInterval", document.getElementById("notifyInterval").value);
            formData.set("accountAlarmStartTime", document.getElementById("startTime").value);
            formData.set("accountAlarmEndTime", document.getElementById("endTime").value);
            formData.set("accountAlarmModel", document.getElementById("model").value);
            formData.set("accountAlarmAmount", document.getElementById("amount").value);
            formData.set("accountAlarmRemark", document.getElementById("remark").value);
            // formData.set("accountAlarmUseStatus", document.getElementById("useStatus").value);
            console.log("11")
        }
    });
}