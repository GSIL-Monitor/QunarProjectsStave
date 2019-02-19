var prefix = "/marketing/coupon"
$(function() {
	load($("#activityCode").val());
});

function load(activityCode) {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listCoupon?activityCode=" + activityCode, // 服务器数据的加载地址
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
									field : 'couponCode', // 列字段名
									title : '券code' // 列标题
								},
								{
									field : 'couponName',
									title : '券简称'
								},
								{
									field : 'couponDesc',
									title : '券描述'
								},
								{
									field : 'couponDetail',
									title : '券规则'
								},
								{
									field : 'instructions',
									title : '使用说明',
									width : 500
								},
								{
									field : 'url',
									title : '跳转地址'
								},
								{
									field : 'freeIntDay',
									title : '免息天数'
								},
								{
									field : 'discountRate',
									title : '折扣率'
								},
								{
									field : 'couponAmount',
									title : '券面额'
								},
								{
									field : 'couponType',
									title : '券类型'
								},
								{
									field : 'couponDimon',
									title : '券维度',
                                    formatter : function(item, index) {
                                        if(item == 'CUSTOMER') {
                                            return '自然人维度';
                                        } else {
                                            return '平台维度';
                                        }
                                    }
								},
								{
									field : 'availableHours',
									title : '券有效小时'
								},
								{
									field : 'startTime',
									title : '券开始时间'
								},
								{
									field : 'endTime',
									title : '券结束时间'
								},
								{
									field : 'totalNum',
									title : '券总数量'
								},
								{
									field : 'grantedNum',
									title : '已发放数量'
								},
								{
									field : 'totalAmt',
									title : '券总金额'
								},
								{
									field : 'grantedAmt',
									title : '已发放金额'
								},
								{
									field : 'ext',
									title : '扩展信息'
								},
								{
									field : 'autoUnUse',
									title : '是否自动撤销',
                                    formatter : function(item, index) {
                                        if(item === 0) {
                                            return '是';
                                        } else {
                                            return '否';
                                        }
                                    }
								},
								{
									field : 'userPercept',
									title : '是否用户感知',
                                    formatter : function(item, index) {
                                        if(item === 0) {
                                            return '是';
                                        } else {
                                            return '否';
                                        }
                                    }
								},
								{
									field : 'couponScene',
									title : '使用场景'
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
                                        var e = '<a class="btn btn-primary btn-sm '
                                            + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                            + row.id
                                            + '\')"><i class="fa fa-edit"></i></a> ';
                                        var f = '<a class="btn btn-primary btn-sm '
                                            + '" href="#" mce_href="#" title="发券" onclick="addInterestFreeFile(\''
                                            + row.id
                                            + '\')"><i class="fa fa-plus"></i></a> ';
                                        return e+f ;
									}
								}]
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
function addInterestFreeFile(id){
    layer.open({
        type : 2,
        title : '发券',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : prefix + '/addInterestFreeFile/' + id
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