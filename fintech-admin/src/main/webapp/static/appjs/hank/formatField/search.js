var prefix = "/hank/formatField"
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
                //showRefresh : true,
                //showToggle : true,
                //showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams : function(params) {
                    return {
                        limit : params.limit,
                        offset : params.offset,
                        id : $('#searchId').val(),
                        taskType : $("#searchTaskType").val().trim(),
                        receiver : $("#searchReceiver").val().trim()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        field : 'id', // 列字段名
                        title : '序号', // 列标题
                        visible: true
                    },
                    {
                        field : 'taskType',
                        title : '任务类型',
                        visible: true
                    },
                    {
                        field : 'receiver',
                        title : '文件接收方',
                        visible: true
                    },
                    {
                        field : 'outOrder',
                        title : '域输出序',
                        visible: true
                    },
                    {
                        field : 'outKey',
                        title : 'JSON 输出key',
                        visible: false
                    },
                    {
                        field : 'outRemark',
                        title : '输出项含义',
                        visible: false
                    },
                    {
                        field : 'operateField',
                        title : '操作域',
                        visible: false
                    },
                    {
                        field : 'operateCommand',
                        title : '操作命令',
                        visible: true
                    },
                    {
                        field : 'status',
                        title : '配置状态',
                        visible: true,
                        formatter : function(value, row, index) {
                            if (value == '1') {
                                return '<span class="label label-primary">正常</span>';
                            } else if (value == '2') {
                                return '<span class="label label-danger">失效</span>';
                            }
                        }
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        visible: true,
                        formatter : function(value, row, index) {
                            var e = '<a shiro:hasPermission="hank:formatField:edit" class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            return e;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function search() {
    $('#exampleTable').bootstrapTable('selectPage',1);
}
// 编辑
function edit(id) {
    layer.open({
        type : 2,
        title : '更新输出字段规则',
        maxmin : true,
        shadeClose : true, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}
// 添加新的数据文件
function add() {
    // iframe层
    layer.open({
        type : 2,
        title : '新增输出字段规则',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add'
    });
}