$(function () {

    $("#send").click(function () {
        if($("#msgMobile").val().trim()==''){
            layer.alert("请输入手机号");
            return;
        }
        if($("#remark").val().trim()==''){
            layer.alert("请输入备注");
            return;
        }
        layer.confirm('确定提交？', {title: '提交', btn: ['确定', '取消'], icon: 3}, function (index) {
            layer.close(index);
            $.ajax({
                url: '/finance/overDue/launchUrgeRepay',
                data: $('#signupForm').serializeArray(),
                type:'post',
                async: false,
                cache:false,
                dataType:'text',
                success:function(data) {
                    console.log(data);
                    data = JSON.parse(data);
                    console.log(data);
                    layer.closeAll('loading');
                    if (data.returnCode == '000000') {
                        layer.confirm(data.returnMsg, {title: '结果', btn: ['关闭'], icon: 3}, function (index) {
                            closeDialog();
                        })
                    } else {
                        if(data.returnMsg == null){
                            layer.confirm('发送失败', {title: '结果', btn: ['关闭'], icon: 3}, function (index) {
                                closeDialog();
                            })
                        }else{
                            layer.confirm(data.returnMsg, {title: '结果', btn: ['关闭'], icon: 3}, function (index) {
                                closeDialog();
                            })
                        }
                    }
                },
                error: function (data) {
                    layer.closeAll('loading');
                    layer.confirm("系统出错!", {title: '结果', btn: ['关闭'], icon: 3}, function (index) {
                        closeDialog();
                    })
                }
            });
        });
    });
    $("#cancel").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
});

//关闭页面
function closeDialog() {
    var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
    parent.layer.close(index);
}
