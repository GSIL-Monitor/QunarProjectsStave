var prefix = "/finance/query";

$(function () {
    $("#save").click(function () {
        var newMobile = $("#newMobile").val();
        if(newMobile == null || newMobile == ''){
            layer.alert("新号码不能为空!");
            return false;
        }
        if(confirm("确定修改？")){
            $.ajax({
                cache : true,
                type : "POST",
                url : prefix + "/saveMobile",
                data : $('#signupForm').serialize(),
                async : false,
                error : function(request) {
                    layer.alert("修改失败");
                },
                success : function(data) {
                    if(data.returnCode != "000000"){
                        layer.alert(data.returnMsg, function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else{
                        layer.alert("修改成功", function () {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                }
            });
        }
    });
    $("#cancel").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
});


function saveMobile(){
	var newMobile = $("#newMobile").val();
	if(newMobile == null || newMobile == ''){
        layer.alert("新号码不能为空!");
        return false;
	}
	if(confirm("确定修改？")){
        $.ajax({
            cache : true,
            type : "POST",
            url : prefix + "/saveMobile",
            data : $('#signupForm').serialize(),
            async : false,
            error : function(request) {
                layer.alert("修改失败");
            },
            success : function(data) {
            	alert(data.returnCode);
                if(data.returnCode != "000000"){
                    layer.alert(data.returnMsg, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else{
                    layer.alert("修改成功", function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
				}
            }
        });
	}
}

function cancelMobile(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}