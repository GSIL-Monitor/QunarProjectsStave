var prefix = "/marketing/coupon";

$(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        addInterest();
    }
});

function addInterest() {
    var formData = new FormData();
    formData.append("orgChannel",document.getElementById("orgChannel").value);
    formData.append("productNo", document.getElementById("productNo").value);
    formData.append("couponAmount", document.getElementById("couponAmount").value);
    formData.append("activityCode", document.getElementById("activityCode").value);
    formData.append("couponCode", document.getElementById("couponCode").value);
    //formData.append("couponFile", document.getElementById("couponFile").files[0]);
    formData.append("couponFile",$("#couponFile")[0].files[0]);
    $.ajax({
        cache : false,
        type : "POST",
        url : prefix + "/addInterestFree",
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

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#addFree").validate({
        rules : {
            orgChannel : {
                required : true
            },
            productNo : {
                required : true
            },
            couponAmount : {
                //required : true,
                min : 0
            },
            couponFile : {
                required : true
            }
        },
        messages : {
            orgChannel : {
                required : icon + "请选择券渠道"
            },
            productNo : {
                required : icon + "请选择券类型"
            },
            couponAmount : {
                //required : icon + "请输入券面额",
                min : $.validator.format("请输入不小于 {0} 的数值")
            },
            couponFile : {
                required : icon + "请选择券文件"
            }
        }
    })
}



