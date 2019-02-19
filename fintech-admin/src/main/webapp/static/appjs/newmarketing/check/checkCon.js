var url = "/newmarketing/review/getReviewNodeList";
var checkItem = ['B岗','QA','法务','财务','主管','产品待发布']
var pageData = {};
var params = {};

function setParam() {
    var nowurl = window.location.search;
    if (nowurl.indexOf("?") != -1) {
        var str = nowurl.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            params[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
}

$(function() {
    setParam();
	initData();
});

function initData(){
	debugger
	$.post("/newmarketing/review/getReviewNodeList", params,function (res){
		pageData = res.data;
		console.log(pageData)
		initPage()
	})
}
function initPage(){
	var pos = 4;
	for(var i =0;i<6;i++){
		var imgele = '';
		if(!pageData[i]){
			if(i===5 && pageData[i-1].status==='PASS'){
				imgele = '<img src="../../../../resources/img/marketing/admin-icon-active.png"/>';
			}else{
				imgele = '<img src="../../../../resources/img/marketing/admin-icon.png"/>';
			}
		}else{
			if(pageData[i].status==='INIT'){
				imgele = '<img src="../../../../resources/img/marketing/admin-icon.png"/>';
				pos--;
			}else if(pageData[i].status==='REJECT'){
				imgele = '<img src="../../../../resources/img/marketing/admin-icon-reject.png" width="50px" onclick="showNowCheck('+i+')"/>';
			}else if(pageData[i].status==='PASS'){
				imgele = '<img src="../../../../resources/img/marketing/admin-icon-active.png"  onclick="showNowCheck('+i+')"/>';
			}else if(pageData[i].status==='REVIEWING'){
				imgele = '<img src="../../../../resources/img/marketing/admin-icon-active.png"/>';
			}
		}
		if(i==5){
			imgele += '<p>'+checkItem[i]+'</p>';
		}else{
			imgele += '<img src="../../../../resources/img/marketing/admin-arrow.png"/>'+'<p>'+checkItem[i]+'审核</p>';
		}
		$('.process-area').append(
			'<div class="process-bt col-sm-2">'+
				imgele
			+'</div>'
		)
	}
	showNowCheck(pos)
}
function showNowCheck(i){
	var itemTitle = [{name:'roleName',title:'审批角色'},{name:'reviewer',title:'审批人'},{name:'time',title:'审批时间'},{name:'status',title:'审批结果'},{name:'comment',title:'审批备注'}];
	var item = pageData[i];
	var res = '';
	if(!item){
		return
	}else{
		for(var i = 0;i<itemTitle.length;i++){
			res += '<tr><td>'+itemTitle[i].title+'</td><td>'+item[itemTitle[i].name]+'</td></tr>'
		}
	}
	$('.table-check-res').html(res)		
}