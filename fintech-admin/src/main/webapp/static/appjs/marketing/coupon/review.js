var prefix = "/market/review"
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/queryAllReviews", // 服务器数据的加载地址
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
                        field : 'contentKey',
                        title : '券号'
                    },
                    {
                        field : 'operType',
                        title : '操作类型',
                        formatter : function(item, index) {
                            if(item == '0') {
                                return '创建券';
                            } else{
                                return '更新券';
                            }
                        }
                    },
                    {
                        field : 'createTime',
                        title : '提交时间'
                    },
                    {
                        field : 'commitUser',
                        title : '提交人'
                    },
                    {
                        field : 'updateTime',
                        title : '审核时间'
                    },
                    {
                        field : 'status',
                        title : '审核状态',
                        formatter : function(item, index) {
                            if(item == '0') {
                                return '待审核';
                            } else if(item == '3') {
                                return '初审通过';
                            } else if(item == '1') {
                                return '审核通过';
                            }else{
                                return '审核不通过';
                            }
                        }
                    },
                    {
                        field : 'reviewUser',
                        title : '审核人',
                        formatter : function(item, index) {
                            if(item == null) {
                                return "无";
                            }else{
                                return item;
                            }
                        }
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {

                            var e = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="详情" onclick="detail(\''
                                + row.id
                                + '\')"><i class="fa fa-bars"></i></a> ';
                            var f = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="通过" onclick="pass(\''
                                + row.id
                                + '\')"><i class="fa fa-check"></i></a> ';
                            var g = '<a class="btn btn-primary btn-sm '
                                + '" href="#" mce_href="#" title="拒绝" onclick="reject(\''
                                + row.id
                                + '\')"><i class="fa fa-close"></i></a> ';

                            // 初始和初审的时候都需要展示"通过"和"拒绝"按钮
                            if(row.status == '0' || row.status == '3' ){
                                return e+f+g ;
                            }else{
                                return e
                            }
                        }
                    }]
            });
}

function detail(id) {
    layer.open({
        type : 2,
        title : '审核详情',
        maxmin : true,
        shadeClose : false,
        area : [ '800px', '520px' ],
        content : prefix + '/detail/' + id
    });
}

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