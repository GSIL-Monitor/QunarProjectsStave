var prefix = "/marketing/activity"

$(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		submit01();
	}
});

function submit01() {
    if(eval($("#cash")).val()<eval($("#amount").val())){
        layer.alert("转入金额不得大于现金户余额！");
        return;
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/account-in",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
            layer.alert("转入失败");
		},
		success : function(data) {
			if (data.code == 0) {
                parent.layer.msg("转入成功");
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
            amount : {
				required : true,
                min : 0
			}
		},
		messages : {
            amount : {
                required : icon + "请输入转入金额",
                min : $.validator.format("请输入不小于 {0} 的数值")
            }
		}
	})

}