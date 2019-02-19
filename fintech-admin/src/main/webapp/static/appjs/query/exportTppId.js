
$(function (){
    debugger
    query();
});
function downloadFile(url){
    if(confirm("确定下载导出的文件吗？")){
        var url = "/finance/nemo/downLoadExportFile?url="+url;
        location.href = url;
    }
}
function query() {
    debugger
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url :"/finance/nemo/downloadList", // 服务器数据的加载地址
                showRefresh : true,
                showToggle : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                queryParamsType:'',
                queryParams : queryParams,
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : false, // 是否显示搜索框
                showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
                columns : [
                    {
                        field : 'startTime', // 列字段名
                        title : '操作时间' // 列标题
                    },
                    {
                        field : 'exportTaskDesc',
                        title : '任务名称'
                    },
                    {
                        field : 'attachmentTitle',
                        title : '文件名称'
                    },
                    {
                        field : 'exportStatusDesc',
                        title : '处理进度'
                    },
                    {
                        field : 'exportStatus',
                        title : '处理结果',
                        formatter : function(value, row, index){
                            if (value == '2') {
                                return "<a href='javascript:downloadFile(\"" + row.url + "\")'>下载</a>";
                            } else {
                                return "";
                            }
                        }
                    },
                    {
                        field : 'endTime',
                        title : '文件有效期',
                        formatter : function(value, row, index) {
                            return '<span style="color:#FF0000">'+value+'</span>';
                        }

                    }
                ]
            });
}
function queryParams(params) {
    debugger
    return {
        pageSize:params.pageSize,
        pageIndex:params.pageNumber
    };
}