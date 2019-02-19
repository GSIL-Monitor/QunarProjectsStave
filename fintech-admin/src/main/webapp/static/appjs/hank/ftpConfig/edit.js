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
    $.ajax({
        cache : true,
        type : "POST",
        url : "/hank/ftpConfig/update",
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
            receiver : {
                required : true,
            },
            port : {
                digits : true
            },
            agree : "required"
        },
        messages : {

            taskType : {
                required : icon + "请输入任务类型"
            },
            receiver : {
                required : icon + "请输入文件接收方",
            },
            port : {
                digits : icon + "端口号必须为数字"
            }
        }
    })
}