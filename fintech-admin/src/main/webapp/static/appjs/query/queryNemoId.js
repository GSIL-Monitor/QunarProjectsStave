

$(function() {
    //获取贷款提供商
    queryTppCode();
});


$("#querySubmit").on('click',function () {
    if($("#exportType").val()==0
        ||($("#platOpenId").val()==""
            &&$("#originUserId").val()==""
            &&$("#customerId").val()==""
            &&$("#tppOpenId").val()=="")){
        alert("请检查查询参数！");
        return;
    }
    query();
})

$("#exportType").bind("change",function(){
    $("#platOpenId").val("");
    $("#originUserId").val("");
    $("#customerId").val("");
    $("#tppOpenId").val("");
    $("#platOpenIdDiv").hide();
    $("#originUserIdDiv").hide();
    $("#customerIdDiv").hide();
    $("#tppOpenIdDiv").hide();
    $("#tppCodeDiv").hide();
    var exportType = $("#exportType").val();
    if(exportType==1){
        $("#tppCode option:not(:first)").removeAttr("selected");//渠道来源下拉栏清空还原
        $("#platOpenIdDiv").show();
    }else if(exportType==2){
        $("#tppCode option:not(:first)").removeAttr("selected");//渠道来源下拉栏清空还原
        $("#originUserIdDiv").show();
    }else if(exportType==3){
        $("#tppCode option:not(:first)").removeAttr("selected");//渠道来源下拉栏清空还原
        $("#customerIdDiv").show();
        $("#tppCodeDiv").show();
    }else if(exportType==4){
        $("#tppCode option:not(:first)").removeAttr("selected");//渠道来源下拉栏清空还原
        $("#tppOpenIdDiv").show();
    }
});


function query() {
    $('#exampleTable').bootstrapTable('destroy');
    $('#exampleTable').bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url :  "/finance/nemo/selectByPlatId", // 服务器数据的加载地址
                showRefresh : false,
                showToggle : false,
                silent:false,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : false, // 设置为true会在底部显示分页条
                queryParamsType:'',
                queryParams : queryParams,
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : false, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者"server"
                columns : [
                    {
                        field : 'platOpenId', // 列字段名
                        title : '平台ID' // 列标题
                    },
                    {
                        field : 'originUserId',
                        title : 'UID'
                    },
                    {
                        field : 'orgChannel',
                        title : '渠道'
                    },
                    {
                        field : 'customerId',
                        title : 'customerId'
                    },
                    {
                        field : 'tppOpenId',
                        title : '通道ID'
                    },
                    {
                        field : 'tppCode',
                        title : '通道code'
                    }
                ]
            });
}

function queryParams(params) {
    return {
        platOpenId: $("#platOpenId").val(),
        originUserId: $("#originUserId").val(),
        customerId: $("#customerId").val(),
        tppOpenId:$("#tppOpenId").val(),
        exportType:$("#exportType").val(),
        tppCode:$("#tppCode").val(),
        pageSize:params.pageSize,
        currentIndex:params.pageNumber
    };
}

$("#exportBtn").bind("click", function(){
    if($("#exportType").val()==0){
        layer.alert("请选择查询类型");
        return;
    }
    if($("#file").val()==""){
        layer.alert("请上传excel查询条件");
        return;
    }
    exportExcel();
});
function exportExcel(){
    if(confirm("您确认要生成EXCEL文件？")){
        var url = "/finance/nemo/queryIdExport";
        var $nemoQueryForm = $("#nemoQueryForm")[0];
        var a = $nemoQueryForm .file.files[0].size;
        if (a > 1024 * 1024 * 8){
            layer.alert("您上传的文件大于8MB，请重新上传！");
            return
        }
        var formData = new FormData($nemoQueryForm);
        jQuery.ajax({
            url: url,
            type: 'post',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            success: function(data){
                if(data.success){
                    layer.alert(data.msg);
                    location.href="/finance/nemo/download";
                }else{
                    layer.alert(data.msg);
                }
            },
            error: function(data, status, e){
                if(data.status == "401"){
                    layer.alert("没有权限！");
                    return;
                }
            }
        });
    }
}
// 获取贷款提供商
function queryTppCode() {
    $.ajax({
        url: '/finance/query/getTpp',
        data:{},
        type:'get',
        cache:false,
        dataType:'json',
        success:function(data) {
            for(var i=0;i<data.length;i++){
                $("#tppCode").append("<option value="+data[i].tppCode+">"+data[i].tppCode + "_"+ data[i].tppName+"</option>");
            }
        }
    });
}