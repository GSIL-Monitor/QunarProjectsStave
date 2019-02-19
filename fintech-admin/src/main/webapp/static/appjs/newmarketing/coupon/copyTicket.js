var prefix = "/marketing/activity"
$(function () {
    load()
});

// function searchData(){
//     var idx = $('#ticketCode').val()
//     console.log(idx)
//     reLoad()
// }

function load(idx) {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
                url: "/newmarketing/coupon/getAllCouponByCondition", // 服务器数据的加载地址
                showRefresh: true,
                //showToggle : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                //data:datas,
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                search: true, // 是否显示搜索框
                //showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: {
                    couponCode: idx
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        field: 'num',
                        title: '序号',
                        formatter: function (element, row, index) {
                            return index + 1;
                        }
                    },
                    {
                        field: 'couponCode', // 列字段名
                        title: '券号' // 列标题
                    },
                    {
                        field: 'supportProductNoList',
                        title: '业务线',
                    },
                    {
                        field: 'activityCode',
                        searchable: false,
                        title: '活动号'
                    },
                    {
                        field: 'couponName',
                        title: '券名称'
                    },
                    {
                        field: 'couponStartTime',
                        title: '开始时间'
                    },
                    {
                        field: 'couponEndTime',
                        title: '结束时间'
                    },
                    {
                        field: 'activityAccountName',
                        title: '营销子账户'
                    },
                    {
                        field: 'couponOwner',
                        title: '负责人'
                    },
                    {
                        field: 'couponGrantedNum',
                        title: '实际发券数量'
                    },
                    {
                        field: 'couponTotalNum',
                        title: '预计发券数量'
                    },
                    {
                        field: 'activityRemainAmt',
                        title: '账户剩余金额'
                    },
                    {
                        field: 'couponTotalAmt',
                        title: '预计发券金额'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm table-btn'
                                + '" href="#" mce_href="#" title="编辑" onclick="">复制</a><br>';
                            return e;
                        }
                    }

                ]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

