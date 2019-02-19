var prefix = "/marketing/activity"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						showRefresh : true,
						showToggle : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						search : true, // 是否显示搜索框
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
									field : 'activityCode', // 列字段名
									title : '活动code' // 列标题
								},
								{
									field : 'activityName',
									title : '活动简称',
									width : '15%'
								},
								{
									field : 'activityDesc',
									title : '活动描述'
								},
								// {
								// 	field : 'activityUrl',
								// 	title : '活动地址'
								// },
								{
									field : 'activityDetail',
									title : '活动详情'
								},
								{
									field : 'switchStatus',
									title : '开关状态'
								},
								{
									field : 'startTime',
									title : '活动开始时间'
								},
								{
									field : 'endTime',
									title : '活动结束时间'
								},
								{
									field : 'totalAmt',
									title : '活动总金额'
								},
								{
									field : 'activityAccount',
									title : '活动剩余金额'
								},
								// {
								// 	field : 'grantedAmt',
								// 	title : '已发放金额'
								// },
								{
									field : 'merchantCode',
									title : '商户号'
								},
								{
									field : 'newAccount',
									title : '是否新账务',
                                    formatter : function(item, index) {
										if(item === 1) {
											return '是';
										} else {
											return '否';
										}

                                    }
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
                                    width : '8%',
									formatter : function(value, row, index) {
                                        var e = '<a class="btn btn-primary btn-sm '
                                            + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                            + row.id
                                            + '\')"><i class="fa fa-edit"></i></a> ';
                                        var f = '<a class="btn btn-primary btn-sm '
                                            + '" href="#" mce_href="#" title="添加券" onclick="addCoupon(\''
                                            + row.activityCode
                                            + '\')"><i class="fa fa-plus"></i></a> ';
                                        var g = '<a class="btn btn-primary btn-sm '
                                            + '" href="/marketing/coupon/list?activityCode='+row.activityCode+'" mce_href="#" title="查看券"'
                                            + row.activityCode
                                            + '><i class="fa fa-list"></i></a> ';
                                        var h = '<a class="btn btn-primary btn-sm '
                                            + '" href="#" mce_href="#" title="编辑账务报警配置" onclick="editAlarmConfig(\''
                                            + row.id
                                            + '\')"><i class="fa fa-exclamation-triangle"></i></a> ';
                                        return e + f +g+h ;
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
											+ row.id + '\' ,  \'' + row.merchantCode+ '\' ,  \'' + row.newAccount
											+ '\')"><i class="fa fa-chevron-up"></i></a> ';
										var f = '<a class="btn btn-primary btn-sm '
											+ '" href="#" mce_href="#" title="转出金额" onclick="accountOut(\''
											+ row.id + '\' ,  \'' + row.activityAccount+ '\' ,  \'' + row.newAccount
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
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add' // iframe的url
    });
}

function edit(id){
	layer.open({
		type : 2,
		title : '编辑活动',
		maxmin : true,
		shadeClose : false, 
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id
	});
}

function addCoupon(activityCode) {
    layer.open({
        type : 2,
        title : '添加券',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : '/marketing/coupon/add/' + activityCode
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
        content : '/marketing/activity/accountIn?id=' + id + '&merchantCode=' + merchantCode
    });
}

function accountOut(id,activityAccount,newAccount) {
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
        content : '/marketing/activity/accountOut?id=' + id + '&activityAccount=' + activityAccount
    });
}

function editAlarmConfig(id){
    layer.open({
        type : 2,
        title : '编辑账务报警配置',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : prefix + '/editAlarmConfig/' + id
    });
}