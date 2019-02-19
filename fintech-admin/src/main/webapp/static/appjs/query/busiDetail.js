var prefix = "/finance/query";

$(function() {
	var busiOrderNo = $("#busiOrderNo").val();
	var occurTime = toTime($("#occurTime").val());
    $.ajax({
        url: 'http://ws.boss.cf.ctripcorp.com/external/order/query?busiOrderno='+busiOrderNo+'&occurTime='+occurTime,
        //url: 'http://ws.boss.cf.ctripcorp.com/external/order/query?busiOrderno=5562377692&occurTime=20180131',
        type:'get',
        cache:false,
        success:function(data) {
            $(".busi-table").hide();
            if(data==null || data=="" || data==undefined){
                return false;
            }
            if(data.retCode != "0000") {
                layer.alert(data.retMsg,function () {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
            }
            if(data.data.busiTypeId === 'flight'){
                $(".flight").show();
                var flight = "<tr><td>"+data.data.busiTypeId+"</td><td>"+data.data.userId+
                    "</td><td>"+data.data.busiOrderno+"</td><td>"+data.data.orderAmount+
                    "</td><td>"+transList(data.data.air_SCity)+"</td><td>"+transList(data.data.air_ECity,false)+
                    "</td><td>"+transList(data.data.air_STime,true)+"</td><td>"+transList(data.data.air_FlightNo,false)+
                    "</td><td>"+transList(data.data.pas_name)+"</td><td>"+data.data.contactPhone+
                    "</td><td>"+formatTime(data.data.orderTime)+"</td></tr>";
                $("#busiTable").append(flight);
            } else if(data.data.busiTypeId === 'hotel'){
                $(".hotel").show();
                var hotel = "<tr><td>"+data.data.busiTypeId+"</td><td>"+data.data.userId+
                    "</td><td>"+data.data.busiOrderno+"</td><td>"+data.data.orderAmount+
                    "</td><td>"+data.data.hotelName+
                    "</td><td>"+transList(data.data.pas_name)+"</td><td>"+data.data.contactPhone+
                    "</td><td>"+formatTime(data.data.orderTime)+"</td></tr>";
                $("#busiTable").append(hotel);
            } else if(data.data.busiTypeId === 'train'){
                $(".train").show();
                var train = "<tr><td>"+data.data.busiTypeId+"</td><td>"+data.data.userId+
                    "</td><td>"+data.data.busiOrderno+"</td><td>"+data.data.orderAmount+
                    "</td><td>"+data.data.trainFrom+
                    "</td><td>"+data.data.trainTo+"</td><td>"+transList(data.data.pas_name)+
                    "</td><td>"+data.data.contactPhone+"</td><td>"+formatTime(data.data.orderTime)+"</td></tr>";
                $("#busiTable").append(train);
            } else if(data.data.busiTypeId === 'minshuku'){
                $(".hotel").show();
                var home = "<tr><td>"+data.data.busiTypeId+"</td><td>"+data.data.userId+
                    "</td><td>"+data.data.busiOrderno+"</td><td>"+data.data.orderAmount+
                    "</td><td>"+data.data.hotelName+
                    "</td><td>"+transList(data.data.pas_name)+"</td><td>"+data.data.contactPhone+
                    "</td><td>"+formatTime(data.data.orderTime)+"</td></tr>";
                $("#busiTable").append(home);
            } else {
                return '';
            }
        }
    });
	if(orgChannel =='QUNAR') {
		$.ajax({
            cache : true,
            type : "get",
            url : prefix + "/queryQunarBusi/" + busiOrderNo,
            async : false,
            error : function(request) {
                layer.alert("查找失败");
            },
            success : function(data) {
                $(".qunar").show();
                if(data == null || data.size() <=0) {
                    layer.alert("没有记录",function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
                var str = "<tr><td>"+data[0].busiTypeName+"</td><td>"+data[0].userId+
                    "</td><td>"+data[0].busiOrderNo+"</td><td>"+data[0].payAmount+
                    "</td><td>"+data[0].productName+"</td><td>" +
                    "</td><td>"+formatTime(data[0].orderTime)+"</td></tr>";
                $("#busiTable").append(str);
			}
		})
	}
});

function formatTime(time) {
	if(time==null || time=="" || time==undefined || time.length<=8){
		return false;
	}
	var year = time.substr(0,4);
	var month = time.substr(4,2);
	var day = time.substr(6,2);
	var hour = time.substr(8,2);
	var minute = time.substr(10,2);
	var second = time.substr(12,2);
	return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}

function toTime(time){
    if(time==null || time=="" || time==undefined){
        return false;
    }
    var year = time.substr(0,4);
    var month = time.substr(5,2);
    var day = time.substr(8,2);
    return year+month+day;
}

function transList(list,flag){
	if(list == null || list == undefined) {
		return '';
	}
	var str = '';
	for(var i=0;i<list.length;i++) {
		var a = list[i];
		if(flag){
			a = formatTime(list[i]);
		}
		if(i==list.length-1){
            str = str + a;
		}
		else{
			str = str + a + ',';
		}
	}
	return str;
}
