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
    if($("#startTime").val()>=$("#endTime").val()){
        layer.alert("活动结束时间必须大于开始时间！");
        return;
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/save",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
            layer.alert("添加失败");
		},
		success : function(data) {
			if (data.code == 0) {
                parent.layer.msg("添加成功");
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
            activityName : {
				required : true
			},
            switchStatus : {
                required : true
            },
            startTime : {
                required : true
            },
            endTime : {
                required : true
            },
            // totalNum : {
            //     required : true,
            //     digits : true,
            //     min : 0
            // },
            totalAmt : {
                required : true,
                min : 0
            },
            newAccount : {
                required : true
            }
		},
		messages : {
            activityName : {
				required : icon + "请输入活动简称"
			},
            switchStatus : {
                required : icon + "请选择活动状态"
            },
            startTime : {
                required : icon + "请输入活动开始时间"
            },
            endTime : {
                required : icon + "请输入活动结束时间"
            },
            // totalNum : {
            //     required : icon + "请输入券总数量",
            //     digits : "请输入正整数",
            //     min : $.validator.format("请输入不小于 {0} 的正整数")
            // },
            totalAmt : {
                required : icon + "请输入券总金额",
                min : $.validator.format("请输入不小于 {0} 的数值")
            },
            newAccount : {
                required : icon + "请选择是否新账务"
            }
		}
	})

}