$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});
function save() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/hank/tppConfig/save",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            taskType : {
                required : true,
                remote : {
                    url : "/hank/tppConfig/exit", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式
                    data : { // 要传递的数据
                        taskType : function() {
                            return $("#taskType").val();
                        },
                        receiver : function(){
                            return $("#receiver").val();
                        }
                    }
                }
            },
            receiver : {
                required : true,
                remote : {
                    url : "/hank/tppConfig/exit", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式
                    data : { // 要传递的数据
                        taskType : function() {
                            return $("#taskType").val();
                        },
                        receiver : function(){
                            return $("#receiver").val();
                        }
                    }
                }
            },
            agree : "required"
        },
        messages : {

            taskType : {
                required : icon + "请输入任务类型",
                remote : icon + "指定文件已经存在（taskType && receiver）"
            },
            receiver : {
                required : icon + "请输入文件接收方",
                remote : icon + "指定文件已经存在（taskType && receiver）"
            }
        }
    })
}