var prefix = "/marketing/activity"
var url = "/newmarketing/coupon/getAllCouponByCondition";
$(function() {
	debugger
	$.post(url,function(res){
        if (res.code != 0) {
            layer.alert(res.msg);
        }
		console.log(res)
		load(res.data)
	})
});
//重新加载数据
function loadData(params){
	$.post(url,params,function(res){
		console.log(res)
		debugger
        if (res.code != 0) {
            layer.alert(res.msg);
        }
		$('#exampleTable')
			.bootstrapTable("load",res.data)
	})
}
//筛选数据
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
function load(datas) {
	$('#exampleTable')
			.bootstrapTable(
					{
						//method : 'get', // 服务器数据的请求方式 get or post
						//url : "http://yapi.corp.qunar.com/mock/4144/getCouponList.do", // 服务器数据的加载地址
						showRefresh : true,
						showToggle : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						data:datas,
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
						cache: false,
						// "server"
						// queryParams : {
						// 	couponCode:'1666666'
						// },
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
							{
								field : 'num',
								title : '序号',
								formatter : function(element, row, index) { return index+1; }
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
								title : '营销子账户'
							},
							{
								field : 'couponOwner',
								title : '负责人'
							},
                            {
                                field : 'activityRemainAmt',
                                title : '账户剩余金额'
                            },
                            {
                                field : 'couponTotalNum',
                                title : '预计发券量'
                            },
							{
								field : 'couponGrantedNum',
								title : '实际发券量'
							},
							{
								field : 'couponTotalAmt',
								title : '预计发券金额'
							},
                            {
                                field : 'couponGrantedAmt',
                                title : '实际发券金额'
                            },
							{
								title : '优惠券操作',
								field : 'id',
								align : 'center',
								formatter : function(value, row, index) {
									var e = '<a class="btn btn-primary btn-sm table-btn'
										+ '" href="#" mce_href="#"  onclick="watchTicket('+row.activityId+','+row.couponId+')">查看优惠券</a>';
									return e;
								}
							},
                            {
                                title : '活动账务',
                                field : 'id',
                                align : 'center',
                                width : '5%',
                                formatter : function(value, row, index) {
                                    var e = '<a class="btn btn-primary btn-sm '
                                        + '" href="#" mce_href="#" title="转入金额" onclick="accountIn(\''
                                        + row.activityId + '\' ,  \'' + row.activityAccountName+ '\' ,  \'' + row.newAccount
                                        + '\')"><i class="fa fa-chevron-up"></i></a> ';
                                    var f = '<a class="btn btn-primary btn-sm '
                                        + '" href="#" mce_href="#" title="转出金额" onclick="accountOut(\''
                                        + row.activityId + '\' ,  \'' + row.activityRemainAmt+ '\' ,  \'' + row.newAccount
                                        + '\')"><i class="fa fa-chevron-down"></i></a> ';
                                    return e + f ;
                                }
                            }
						]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function code(tableName) {
	location.href = prefix + "/code/" + tableName;
}
function add() {
    layer.open({
        type : 2,
        title : '增加活动',
        shadeClose : false, // 点击遮罩关闭层
        area : [ '850px', '570px' ],
        content : './coupon/add.html'// iframe的url
    });
}

function watchTicket(activityId,couponId){
	debugger
	console.log(code)
    layer.open({
        type : 2,
        title : '查看优惠券',
        shadeClose : false,
        area : [ '850px', '570px' ],
		content : './coupon/content.html?activityId='+activityId+'&'+'couponId='+couponId
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

function checkContent(activityCode){
	layer.open({
        type : 2,
        title : '审核详情',
        maxmin : true,
        shadeClose : false,
        area : [ '820px', '540px' ],
        content : './coupon/checkCon.html'
    });
}

function accountIn(id,merchantCode,newAccount) {
    if(newAccount == "0"){
        layer.alert("只有新账务能转账！");
        return false;
    }
    if(merchantCode==null||merchantCode==undefined||merchantCode==""){
        layer.alert("没有商户号！");
        return false;
    }
    layer.open({
        type : 2,
        title : '转入金额',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : '/newmarketing/coupon/accountIn?id=' + id + '&merchantCode=' + merchantCode
    });
}

function accountOut(id,activityAccount,newAccount) {
    debugger
    if(newAccount == "0"){
        layer.alert("只有新账务能转账！");
        return false;
    }
    if(activityAccount=='null'||activityAccount==undefined||activityAccount==""){
        layer.alert("没有商户号!");
        return false;
    }
    layer.open({
        type : 2,
        title : '转出金额',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : '/newmarketing/coupon/accountOut?id=' + id + '&activityAccount=' + activityAccount
    });
}
