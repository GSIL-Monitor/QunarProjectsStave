var prefix = "/finance/queryId";

$(function () {
    $("#submit").click(function () {
        queryId();
    })
});


function queryId(){
	var mobile = $("#mobile").val();
	if(mobile == null || mobile == ''){
        layer.alert("请输入手机号!");
        return false;
	}
    $.ajax({
        cache : true,
        type : "get",
        url : prefix + "/platOpenId?mobile=" + mobile,
        dataType:'json',
        async : false,
        error : function(request) {
            layer.alert("获取失败");
        },
        success : function(data) {
            $('#exampleTable').empty();
            var str = '<tr><th>手机号</th><th>userId</th><th>openId</th><th>platOpenId</th></tr>';
            $('#exampleTable').append(str);
            var userId = data.userId;
            if(data.userId == null) {
                userId = '';
            }
            var openId = data.openId;
            if(data.openId == null) {
                openId = '';
            }
            var platOpenId = data.platOpenId;
            if(data.platOpenId == null) {
                platOpenId = '';
            }
            str = '<tr><td>'+ data.mobile +'</td><td>'+ userId +
                  '</td><td>'+ openId +'</td><td>'+ platOpenId +'</td></tr>';
            $('#exampleTable').append(str);

        }
    });
    // $('#exampleTable').bootstrapTable('destroy');
    // $('#exampleTable')
    //     .bootstrapTable(
    //         {
    //             method : 'get', // 服务器数据的请求方式 get or post
    //             url : prefix + "/platOpenId?mobile=" + mobile, // 服务器数据的加载地址
    //             iconSize : 'outline',
    //             toolbar : '#exampleToolbar',
    //             striped : true, // 设置为true会有隔行变色效果
    //             dataType : "json", // 服务器返回的数据类型
    //             pageSize : 10, // 如果设置了分页，每页数据条数
    //             pageNumber : 1, // 如果设置了分布，首页页码
    //             sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
    //             columns : [
    //
    //                 {
    //                     field : 'mobile', // 列字段名
    //                     title : '手机号' // 列标题
    //                 },
    //                 {
    //                     field : 'userId',
    //                     title : 'userId'
    //                 },
    //                 {
    //                     field : 'openId',
    //                     title : 'openId'
    //                 },
    //                 {
    //                     field : 'platOpenId',
    //                     title : 'platOpenId'
    //                 }
    //             ]
    //         });
}
