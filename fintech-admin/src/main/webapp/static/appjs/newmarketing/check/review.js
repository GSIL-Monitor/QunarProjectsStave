var url = "/newmarketing/review/list/";
$(function() {
    debugger
    // 查询所有的审核记录
    let filterType = 0;
    var firsturl = url+filterType;

	$.post(firsturl,function(res){
		console.log(res)
        if (res.code != 0) {
            layer.alert(res.msg);
        }
		load(res.data)
	})
});
//重新加载数据
function loadData(params){
    debugger
    var newurl = url;
    console.log($('#sizerType').val())
    if($('#sizerType').val()==1){
        console.log(1)
        newurl = url+1;
    }else if($('#sizerType').val()==2){
        console.log(2)
        newurl = url+2
    }else if($('#sizerType').val()==3){
        console.log(3)
        newurl = url+3
    }else{
        newurl = url+0
    }
    console.log(newurl)
	$.post(newurl,params,function(res){
		console.log(res)

        if (res.code != 0) {
            layer.alert(res.msg);
        }

		$('#exampleTable')
			.bootstrapTable("load",res.data)
    })

}
//筛选数据
function filterData(){
    debugger
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
	// clearFilter()
}
function load(datas) {
    $('#exampleTable')
        .bootstrapTable(
            {
                //method : 'get', // 服务器数据的请求方式 get or post
                //url : prefix + "/queryAllReviews", // 服务器数据的加载地址
                showRefresh : true,
                showToggle : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                //dataType : "json", // 服务器返回的数据类型
                data : datas,
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                
                // "server"
                // queryParams : queryParams,
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        title : '',
                        field : 'checkbox',
                        // align : 'center',
                        // formatter : function(value, row, index) {
                        //     var e = 
                        //     '<input id="check'+index+'" name="check'+index+'" type="checkbox">'
                        //     return e;
                        // }
                        checkbox:true,
                        formatter:function(value,row,index){
                            if(row.status != "INIT"){
                                return {
                                    disabled: true
                                }
                            }else{
                                return {
                                    disabled: false
                                }
                            }
                        }
                    },
                    {
                        field : 'num',
                        title : '序号',
                        formatter : function(element, row, index) { return index+1; }
                    },
                    {
                        field : 'reviewNo', // 列字段名
                        title : '审核流水' // 列标题
                    },
                    {
                        field : 'couponCode', // 列字段名
                        title : '券号' // 列标题
                    },
                    {
                        field : 'supportProductNoList',
                        title : '业务线',
                    },
                    {
                        field : 'status',
                        title : '状态',
                    },
                    {
                        field : 'activityCode',
                        searchable:false,
                        title : '活动号'
                    },
                    {
                        field : 'couponName',
                        title : '券名称'
                    },
                    {
                        field : 'couponStartTime',
                        title : '开始时间'
                    },
                    {
                        field : 'couponEndTime',
                        title : '结束时间'
                    },
                    {
                        field : 'activityAccountName',
                        title : '营销账户'
                    },
                    {
                        field : 'owner',
                        title : '负责人'
                    },
                    {
                        field : 'couponGrantedNum',
                        title : '实际发券数量'
                    },
                    {
                        field : 'couponTotalNum',
                        title : '预计发券数量'
                    },
                    {
                        field : 'activityRemainAmt',
                        title : '账户剩余金额'
                    },
                    {
                        field : 'couponTotalAmt',
                        title : '预计发券金额'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm table-btn'
                                + '" href="#" mce_href="#" title="查看优惠券" onclick="watchTicket('+row.reviewNo+')">查看优惠券</a><br>';
                            var f = '<a class="btn btn-primary btn-sm table-btn" onclick="checkTicket('+row.reviewNo+')">审核优惠券</a><br>';
                            var g = '<a class="btn btn-primary btn-sm table-btn'
                                + '" href="#" mce_href="#" title="审批详情" onclick="checkContent(\''
                                + row.reviewNo
                                + '\')">审批详情</a><br>';
                            var s = '<a class="btn btn-primary btn-sm table-btn'
                            + '" href="#" mce_href="#" title="提交优惠券" onclick="submitTicket('+row.reviewNo+')">提交</a> ';
                            if(row.status==="INIT"){
                                return e+s;
                            }else {
                                return f+g;
                            }
                            //return e+g+s;
                            // 初始和初审的时候都需要展示"通过"和"拒绝"按钮
                            // if(row.status == '0' || row.status == '3' ){
                            //     return e+g ;
                            // }else{
                            //     return f+g
                            // }
                        }
                    }]
            });
}
function checkTicket(reviewNo){
    layer.open({
        type : 2,
        title : '审核优惠券',
        shadeClose : false,
        area : [ '850px', '570px' ],
        content : './review/check.html?reviewNo='+reviewNo
    });
}
function watchTicket(reviewNo){
	console.log(reviewNo)
    layer.open({
        type : 2,
        title : '查看优惠券',
        shadeClose : false,
        area : [ '850px', '570px' ],
		content : './coupon/content.html?reviewNo='+reviewNo
	});
}

function batchSubmit(){
    debugger
    var submitData = $('#exampleTable').bootstrapTable('getSelections');
    var list = [];
    for(var i =0;i<submitData.length;i++){
        list[i] = submitData[i].reviewNo;
    }

    var submitUrl = "/newmarketing/review/submit";

    $.ajax({
        url: submitUrl,
        //data:{"ids":b},这种方式我这里测试获取到的数据个数为0，倒不是为null，也不行
        dataType: "json",
        type: "post",
        data: { "reviewNos": list },//使用这种数组方式的，得加下一句才可以，使用传统方式
        traditional: true,
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("审核成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                layer.alert(data.msg);
            }
        }
    })
}
function submitTicket(reviewNo){
	layer.confirm('请确定上传信息与通过线下审批活动内容一致，确定提交后不可修改', {icon: 0, title:'提示'}, function(index){

        var list = [reviewNo];
        var submitUrl = "/newmarketing/review/submit";

        $.ajax({
            url: submitUrl,
            //data:{"ids":b},这种方式我这里测试获取到的数据个数为0，倒不是为null，也不行
            dataType: "json",
            type: "post",
            data: { "reviewNos": list },//使用这种数组方式的，得加下一句才可以，使用传统方式
            traditional: true,
            success: function (data) {
                if (data.code == 0) {
                    parent.layer.msg("提交审核成功");
                    parent.reLoad();
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                } else {
                    layer.alert(data.msg);
                }
            }
        })



		console.log(index)
		layer.close(index);
	  });
}
function checkContent(reviewNo){
	layer.open({
        type : 2,
        title : '审核详情',
        shadeClose : false,
        area : [ '820px', '540px' ],
        content : './review/checkCon.html?reviewNo='+reviewNo
    });
}

// function clearFilter(){
// 	$('#couponCode').val('');
// 	$('#activityName').val('');
// 	$('#startTime').val('');
// 	$('#endTime').val('');
// 	$('#activityName').val('');
// 	$('#MoneyRangeFrom').val('');
// 	$('#MoneyRangeTo').val('');
// 	$('#activityMaster').val('');
// 	$('#nowStatus').val('');
// 	$('#ticketType').val('');
// 	$('#lineBusiness').val('');
// }

function pass(id) {
    // 操作人
    var formData = new FormData();
    var adminName = sessionStorage.getItem("adminName");
    formData.append("adminName", adminName);
    formData.append("id", id);

    $.ajax({
        cache : true,
        type : "POST",
        url : prefix + '/pass',
        processData : false,
        contentType : false,
        data :formData,
        async : false,
        error : function(request) {
            layer.alert("审核失败");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("审核成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                layer.alert(data.msg);
            }
        }
    });
}

function reject(id) {
    // 操作人
    var formData = new FormData();
    var adminName = sessionStorage.getItem("adminName");
    formData.append("adminName", adminName);
    formData.append("id", id);

    $.ajax({
        cache : true,
        type : "POST",
        url : prefix + '/reject',
        processData : false,
        contentType : false,
        data :formData,
        async : false,
        error : function(request) {
            layer.alert("审核失败");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("审核成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            } else {
                layer.alert(data.msg);
            }
        }
    });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}