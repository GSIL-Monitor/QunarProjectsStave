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

$(function () {
    setParam();
    initData();
});

function initData() {
    debugger
    var activityId = params['activityId'];
    if (params['activityId'] != null) {
        $.post("/newmarketing/coupon/detail", params, function (data, status) {
            console.log(data)
            if (data.code != 0) {
                layer.alert(data.msg);
            }
            console.log(data.data)
            data = JSON.parse(data.data);
            initPage(data)
        })
    } else {
        debugger;
        $.post("/newmarketing/review/getReviewDetail", params, function (data, status) {
            console.log(data);
            if (data.code != 0) {
                layer.alert(data.msg);
            }
            console.log(data.data)
            initPage(JSON.parse(data.data));
        })
    }
}

function showCouponManage() {
    var btnData = [['编辑', '编辑优惠券信息', 0], ['发券', '上传用户发券', 2]]
    var conRes = '';
    debugger;
    var activityId = $('#activityId').val();
    var couponId = $('#couponId').val();
    var couponCode = $('#couponCode').val();
    var couponSwitchStatus = $('#couponSwitchStatus').val();

    for (var i = 0; i < btnData.length; i++) {
        conRes += '<button class="btn btn-primary col-sm-8 col-sm-offset-2" onclick="skipItem(' + btnData[i][2] + ',' + activityId + ','
            + couponId + ',' + couponCode + ')">' + btnData[i][0] + '</button>';
        conRes += '<p class="col-sm-8 col-sm-offset-2" style="margin: 10px 40px">' + btnData[i][1] + '</p>'
    }

    debugger
    if (couponSwitchStatus == 0) {
        conRes += '<button class="btn btn-primary col-sm-8 col-sm-offset-2" onclick="closeOrOpen(' + activityId + ','
            + 1 + ')">' + '关闭' + '</button>';
        conRes += '<p class="col-sm-8 col-sm-offset-2" style="margin: 10px 40px">' + '关闭优惠券' + '</p>'
    } else {
        conRes += '<button class="btn btn-primary col-sm-8 col-sm-offset-2" onclick="closeOrOpen(' + activityId + ','
            + 0 + ')">' + '开启' + '</button>';
        conRes += '<p class="col-sm-8 col-sm-offset-2" style="margin: 10px 40px">' + '开启优惠券' + '</p>'
    }

    layer.open({
        type: 1,
        title: '优惠券管理',
        shadeClose: false,
        area: ['200px', '300px'],
        content: conRes
    });
}

//此出没有通过id获取
function skipItem(i, activityId, couponId, couponCode) {
    debugger
    if (i == 0) {
        layer.open({
            type: 2,
            title: '编辑优惠券',
            offset: '20px',
            shadeClose: false,
            area: ['850px', '570px'],
            content: './edit.html?activityId=' + activityId + '&couponId=' + couponId + '&couponCode=' + couponCode
        });
    } else if (i == 2) {
        layer.open({
            type: 2,
            title: '发券',
            offset: '20px',
            shadeClose: false,
            area: ['800px', '500px'],
            content: './sendTicket.html'
        });
    }
}

function closeOrOpen(activityId, couponSwitchStatus) {
    debugger
    var temp = {};
    temp["activityId"] = activityId;
    temp["couponSwitchStatus"] = couponSwitchStatus;
    $.post("/newmarketing/coupon/alterCouponSwitchStatus", temp, function (data, status) {
        console.log(data)
        if (data.code != 0) {
            alert(data.msg);
        } else {
            parent.layer.msg("成功！");
            parent.reLoad();
            var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
            parent.layer.close(index);
        }
    })
}
