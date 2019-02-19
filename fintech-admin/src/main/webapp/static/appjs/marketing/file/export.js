var prefix = "/hank/file"

$(function() {
    queryTaskType();
    queryReceiver(null);
	validateRule();
    load();
});

$.validator.setDefaults({
	submitHandler : function() {
		submit01();
	}
});

function submit01() {
    if($("#startTime").val() > $("#endTime").val()){
        layer.alert("终止结束时间必须大于起始时间！");
        return;
    }
    $.ajax({
        cache : true,
        type : 'post',
        url  : prefix+'/export',
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                reLoad();
            } else {
                layer.alert(data.msg)
            }

        }
    });
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
		    activityCode : {
                required : true
            },
            type : {
				required : true
			},
            taskType : {
                required : true
            },
            startTime : {
                required : true
            },
            endTime : {
                required : true
            },
            channel : {
                required : true
            },
            runTime:{
		        required : true
            }
		},
		messages : {
		    activityCode : {
                required : icon + "请输入活动code"
            },
            type : {
				required : icon + "请选择导出类型"
			},
            taskType : {
                required : icon + "请选择任务类型"
            },
            startTime : {
                required : icon + "请输入起始时间"
            },
            endTime : {
                required : icon + "请输入终止时间"
            },
            channel : {
                required : icon + "请选择渠道"
            },
            runTime:{
                required : icon + "请输入执行时间"
            }
		}
	})

}

function queryTaskType() {
    $.ajax({
        url: prefix+'/getTaskTypes',
        data:{
            receiver : null
        },
        type:'get',
        cache:false,
        dataType:'json',
        success:function(data) {
            for(var i=0;i<data.length;i++){
                $('#taskType').append("<option value="+data[i]+">"+data[i]+"</option>");
            }
        }
    });
}

//选择 taskType 字段 查询 receiver 列表
function queryReceiver(obj) {
    // 获取 taskType 对象判断是否存在查询条件
    var taskType = (obj == null) ? '' : obj.options[obj.selectedIndex].value;
    // 发起查询
    $('#receiver').empty().append('<option value="" selected="selected">请选择</option>');
    $.ajax({
        url: prefix+'/getReceivers',
        data:{
            taskType : taskType
        },
        type:'get',
        cache:false,
        dataType:'json',
        success:function(data) {
            for(var i=0;i<data.length;i++){
                $('#receiver').append("<option value="+data[i]+">"+data[i]+"</option>");
            }
        }
    });
}

function queryFileName(obj) {
    var taskType = $('#taskType').val().trim();
    var receiver = (obj == null) ? '' : obj.options[obj.selectedIndex].value.trim();

    if(taskType.length > 0 && receiver.length > 0){
        $.ajax({
            url: prefix+'/getFileName',
            data:{
                taskType : taskType,
                receiver : receiver
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                console.log(data);
                $('#fileName').val(data);
            }
        });
    }else{
        $('#fileName').val('');
    }
}

// 最近执行任务数据加载
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/getFileList", // 服务器数据的加载地址
                showRefresh : true,
                //showToggle : true,
                //showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                //singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                //showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        limit : params.limit,
                        offset : params.offset,
                        reqUser : $('#searchReqUser').val().trim(),
                        taskType : $('#searchTaskType').val().trim(),
                        receiver : $('#searchReceiver').val().trim(),
                        runTime : $('#searchRunTime').val().trim()
                    };
                },
                // "server"
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        field : 'id', // 列字段名
                        title : 'Id' // 列标题
                    },
                    {
                        field : 'reqUser',
                        title : '发起人'
                    },
                    {
                        field : 'taskType',
                        title : '任务类型'
                    },
                    {
                        field : 'receiver',
                        title : '接收方'
                    },
                    {
                        field : 'accountDate',
                        title : '账务日期'
                    },
                    {
                        field : 'runTime',
                        title : '执行时间'
                    },
                    {
                        field : 'lastRunTime',
                        title : '上次执行时间'
                    },
                    {
                        field : 'reqNums',
                        title : '请求次数'
                    },
                    {
                        field : 'status',
                        title : '执行状态',
                        visible: true,
                        formatter : function(value, row, index) {
                            if (value == '0') {
                                return '<span class="label label-primary">初始</span>';
                            } else if (value == '1') {
                                return '<span class="label label-warning">执行中</span>';
                            }else if (value == '2') {
                                return '<span class="label label-success">成功</span>';
                            }else if (value == '3') {
                                return '<span class="label label-danger">失败</span>';
                            }
                        }
                    },
                    {
                        field : 'errCode',
                        title : '错误码'
                    },
                    {
                        field : 'errMsg',
                        title : '错误信息'
                    }]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
// 条件查询
function search() {
    $('#exampleTable').bootstrapTable('selectPage',1);
}