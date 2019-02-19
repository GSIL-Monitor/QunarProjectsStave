function initPage(data) {
    debugger;

    // $('.ibox-content input,.ibox-content textarea,.ibox-content select').each(function () {
    //     $(this).attr('disabled', true);
    // })
    for (var key in data) {
        //优惠券类型
        if (key === 'couponType') {
            if (data[key] == 0) {
                $('#changeTicket [value=free-interest]').attr('selected', true);
                $('.free-interest').addClass('show')
            } else if (data[key] == 2) {
                $('#changeTicket [value=cash-ticket]').attr('selected', true);
                $('.cash-ticket').addClass('show')
            } else if (data[key] == 3) {
                $('#changeTicket [value=random-minus]').attr('selected', true);
                $('.random-minus').addClass('show')
            }
        }
        //是否发送短信
        if (key === 'noticeUserByMsg') {
            $('[name=isSendMessage]:eq(' + data[key] + ')').attr('checked', true)
        }
        //是否在qunar展示
        if (key === 'noticeQunarPublic') {
            $('[name=qunarisShow]:eq(' + data[key] + ')').attr('checked', true)
        }
        //发放渠道
        if (key === 'supportOrgChannelList') {
            var channelData = data[key];
            console.log(channelData)
            for(var key in channelData){
                console.log(key)
                $('.send-channel :input[name=' + channelData[key] + ']').attr('checked', true)
            }
        }
        //业务线
        if (key === 'supportProductNoList') {
            $('.lineSelect [value=' + data[key] + ']').attr('checked', true)
        }
        //报警模式?
        // if(key === 'alertMode'){
        //     console.log($('[name=qunarisShow]:eq('+data[key]+')'))
        //     $('[name=qunarisShow]:eq('+data[key]+')').attr('checked',true)
        // }
        //券纬度
        if (key === 'couponDimon') {
            $('#ticketLatitude [value=' + data[key] + ']').attr('selected', true)
            if (data[key] === 'PLAT') {
                $('.send-channel').addClass('show')
            }
        }
        //使用期数?
        if (key === 'repayIndexList') {
            if (data[key] == 1) {
                $("#firstDate").attr('checked', true)
            } else if (data[key] == -1) {
                $("lastDate").attr('checked', true)
            } else if (data[key] == 'ALL') {
                $("#wholeDate").attr('checked', true)
            }
        }
        //免息券类型?
        if (key === 'repayIndexList') {
            console.log($('[name=qunarisShow]:eq(' + data[key] + ')'))
            $('[name=qunarisShow]:eq(' + data[key] + ')').attr('checked', true)
        }

        var alertMode = data.accountAlarmNoticeMethod;
        var email = 8;
        if (alertMode - email >= 0) {
            alertMode = alertMode - 8;
            $("input[id='email']").attr("checked", true);
        }

        var qmq = 4;
        if (alertMode - qmq >= 0) {
            alertMode = alertMode - qmq;
            $("input[id='qmq']").attr("checked", true);
        }

        var sms = 2;
        if (alertMode - sms >= 0) {
            alertMode = alertMode - sms;
            $("input[id='sms']").attr("checked", true);
        }

        var qtalk = 1;
        if (alertMode - qtalk >= 0) {
            alertMode = alertMode - qtalk;
            $("input[id='qtalk']").attr("checked", true);
        }

        $('#' + key).val(data[key]);
    }
}