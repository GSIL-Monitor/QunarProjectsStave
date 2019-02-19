var prefix = "/newmarketing/review";
var params = {};
$(function() {
    debugger;
    setParam();
    initData();
});

function setParam(){
    var nowurl = window.location.search;
    if (nowurl.indexOf("?") != -1) {
        var str = nowurl.substr(1);
        var strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            params[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
        }
    }
}
//???
function initData(){
    debugger;
    $.post("/newmarketing/review/getReviewDetail",params,function (data,status){
        console.log(data);
        initPage(JSON.parse(data.data));
    })
}
//???
function loadData(params){
	$.get(url,params,function(res){
		console.log(res)
		$('#exampleTable')
			.bootstrapTable("load",res.data)
	})
}
function filterData(){
	var params = {
        couponCode:$('#couponCode').val(),
        productionNo:$('#lineBusiness').val(),
        couponType:$('#ticketType').val(),
        couponStartTime:$('#startTime').val(),
        couponEndTime:$('#endTime').val(),
        activityName:$('#activityName').val(),
        couponOwner:$('#activityMaster').val(),
        activityMinTotalAmt:$('#MoneyRangeFrom').val(),
        activityMaxTotalAmt:$('#MoneyRangeTo').val(),
	}
	console.log(params)
	loadData(params)
	clearFilter()
}
function skipItem(i, reviewNo){
    debugger
    if(i==0){
        layer.open({
            type : 2,
            title : '编辑优惠券',
            offset: '20px',
            shadeClose : false,
            area : [ '850px', '570px' ],
            content : '/newmarketing/coupon/edit.html?reviewNo='+reviewNo
        });
    }else if(i==1){
        debugger;
        var list = [reviewNo];

        $.ajax({
            url: "/newmarketing/review/publish",
            //data:{"ids":b},这种方式我这里测试获取到的数据个数为0，倒不是为null，也不行
            dataType: "json",
            type: "post",
            data: { "reviewNos": list },//使用这种数组方式的，得加下一句才可以，使用传统方式
            traditional: true,
            success: function (data) {
                if (data.code == 0) {
                    parent.layer.msg("发布成功！");
                    parent.reLoad();
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                } else {
                    layer.alert(data.msg);
                }
            }
        })
    }
}
function submitReview() {
    debugger;
    alert($('#reviewNo').val())
    alert($('input:radio[name=commentStatus]:checked').val())
    alert($('#checkMarket').val())
    formData.set("reviewNo", $('#reviewNo').val());
    formData.set("status", $('input:radio[name=commentStatus]:checked').val());
    formData.set("comment", $('#checkMarket').val());

    $.ajax({
        cache : true,
        type : "POST",
        url : prefix + '/comment',
        processData : false,
        contentType : false,
        dataType : 'json',
        data :formData,
        async : false,
        error : function(request) {
            layer.alert("批复失败！");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("批复成功！");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                layer.alert(data.msg);
            }
        }
    });
}

function showActivityManage(){
    debugger
    var btnData = [['编辑','编辑优惠券信息',0],['发布','发布优惠券上线',1]]
    var conRes = '';

    var reviewNo = $('#reviewNo').val();

    for(var i = 0;i<btnData.length;i++){
        conRes+='<button class="btn btn-primary col-sm-8 col-sm-offset-2" onclick="skipItem(' + btnData[i][2] + ',' + reviewNo +')">'+btnData[i][0]+'</button>';
        conRes+='<p class="col-sm-8 col-sm-offset-2" style="margin: 10px 40px">'+btnData[i][1]+'</p>'
    }
    layer.open({
		type : 1,
		title : '优惠券管理',
		shadeClose : false, 
		area : [ '200px', '300px' ],
        content : conRes
	});
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}