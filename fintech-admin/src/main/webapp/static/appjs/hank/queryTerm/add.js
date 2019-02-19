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
        url : "/hank/queryTerm/save",
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
                required : true
            },
            queryMode : {
                required : true
            },
            queryGroup : {
                digits : true
            },
            groupOrder : {
                digits : true
            },
            stepLength : {
                digits : true
            },
            agree : "required"
        },
        messages : {
            taskType : {
                required : icon + "请输入任务类型"
            },
            queryMode : {
                required : icon + "请选择查询模式"
            },
            queryGroup : {
                digits : icon + "组别必须为整数"
            },
            groupOrder : {
                digits : icon + "组序必须为整数"
            },
            stepLength : {
                digits : icon + "步长必须为整数"
            }
        }
    })
}