// 以下为官方示例
$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {
    $("#accountTime")
    $.ajax({
        cache : true,
        type : "POST",
        url : "/hank/fileTask/update",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.msg(data.msg);
            }

        }
    });

}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            taskType : {
                required : true
            },
            startId : {
                digits : true
            },
            endId : {
                digits : true
            },
            agree : "required"
        },
        messages : {
            taskType : {
                required : icon + "请输入任务类型"
            },
            startId : {
                digits : icon + "起始ID必须为整数"
            },
            endId : {
                digits : icon + "结束ID必须为整数"
            }
        }
    })
}