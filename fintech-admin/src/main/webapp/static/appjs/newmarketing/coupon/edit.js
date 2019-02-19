var prefix = "/marketing/activity"

$(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        update();
    }
});

function update() {
    if ($("#startTime").val() >= $("#endTime").val()) {
        layer.alert("活动结束时间必须大于开始时间！");
        return;
    }
    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/update",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("修改成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                layer.alert(data.msg)
            }
        }
    });
}

// function validateRule() {
//     var icon = "<i class='fa fa-times-circle'></i> ";
//     $("#signupForm2").validate({
//         rules: {
//             couponTotalAmt: {
//                 required: true
//             },
//             expectedGrantedNum: {
//                 required: true
//             },
//             expectedUsedNum: {
//                 required: true
//             },
//             marketingAccountName: {
//                 required: true
//             },
//             couponStartTime: {
//                 required: true
//             },
//             couponEndTime: {
//                 required: true
//             }
//         },
//         messages: {
//             // activityCode : {
//             //     required : icon + "请输入活动code"
//             // },
//             // activityName : {
//             //     required : icon + "请输入活动简称"
//             // },
//             // switchStatus : {
//             //     required : icon + "请选择活动状态"
//             // },
//             // startTime : {
//             //     required : icon + "请输入开始时间"
//             // },
//             // endTime : {
//             //     required : icon + "请输入结束时间"
//             // }
//         }
//     })
// }
