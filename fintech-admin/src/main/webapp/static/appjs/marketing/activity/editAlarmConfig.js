var prefix = "/marketing/activity"

$("#editAlarmConfig").submit(function () {
    var formData = new FormData();
    formData.append("operType", document.getElementById("operType").value);
    formData.append("activityId", document.getElementById("activityId").value);
    formData.append("receiver", document.getElementById("receiver").value);

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

    formData.append("alertMode", alertMode);
    formData.append("extEmailReceiver", document.getElementById("extEmailReceiver").value);
    formData.append("extMobileReceiver", document.getElementById("extMobileReceiver").value);
    formData.append("notifyInterval", document.getElementById("notifyInterval").value);
    formData.append("startTime", document.getElementById("startTime").value);
    formData.append("endTime", document.getElementById("endTime").value);
    formData.append("model", document.getElementById("model").value);
    formData.append("amount", document.getElementById("amount").value);
    formData.append("remark", document.getElementById("remark").value);
    formData.append("useStatus", document.getElementById("useStatus").value);

    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/addOrUpdateAlarmConfig/",
        processData: false,
        contentType: false,
        data: formData,
        async: false,
        error: function (request) {
            layer.alert("配置账务报警失败！");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("配置账务报警成功！");
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                layer.alert(data.msg);
            }
        }
    });
});

$(window).load(function(){
    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/queryAlarmConfig",
        data: $('#editAlarmConfig').serialize(),
        async: false,
        error: function (request) {
            alert("查询报警配置失败！");
        },
        success: function (json) {
            if (json.code == 0) {
                var dataJson = JSON.parse(json.data);
                $("#receiver").val(dataJson.receiver);

                var alertMode = dataJson.alertMode;
                var email = 8;
                if (alertMode - email >= 0) {
                    alertMode = alertMode - 8;
                    $("input[id='email']").attr("checked", "checked");
                }

                var qmq = 4;
                if (alertMode - qmq >= 0) {
                    alertMode = alertMode - qmq;
                    $("input[id='qmq']").attr("checked", "checked");
                }

                var sms = 2;
                if (alertMode - sms >= 0) {
                    alertMode = alertMode - sms;
                    $("input[id='sms']").attr("checked", "checked");
                }

                var qtalk = 1;
                if (alertMode - qtalk >= 0) {
                    alertMode = alertMode - qtalk;
                    $("input[id='qtalk']").attr("checked", "checked");
                }

                $("#extEmailReceiver").val(dataJson.extEmailReceiver);
                $("#extMobileReceiver").val(dataJson.extMobileReceiver);
                $("#notifyInterval").val(dataJson.notifyInterval);
                $("#startTime").val(dataJson.startTime);
                $("#endTime").val(dataJson.endTime);

                var model = dataJson.model;
                if (model == "MAX") {
                    $("#model").val("MAX");
                } else {
                    $("#model").val("MIN");
                }

                $("#amount").val(dataJson.amount);
                $("#remark").val(dataJson.remark);

                var model = dataJson.useStatus;
                if (model == "UNUSED") {
                    $("#useStatus").val("UNUSED");
                } else {
                    $("#useStatus").val("USED");
                }

                $("#operType").text('更新');
                $("#operType").attr('value', '1');
            } else {
                $("#operType").text('新增');
                $("#operType").attr('value', '0');
            }
        }
    });
});