var backGroundDivName = 'EV_bgModeAlertDiv';
//弹出对话窗口(msgID-要显示的div的id)
function popUp(msgID){
    //背景框满窗口显示
    EV_Show_bgDiv();
    //把要显示的div居中显示
    EV_Show_msgDiv(msgID);
}

//关闭对话窗口
function closeDiv(){
    var bgObj=document.getElementById(backGroundDivName);
    document.body.removeChild(bgObj);
}

var initPopPage = function () {
    var topTexts = document.getElementById("topTexts");
    if (topTexts != null) {
        topTexts.innerHTML = "";
        document.getElementById("confirmProductNo").value = "";
        document.getElementById("confirmTppCode").value = "";
    }
}
function fillPopUpInfo(divId, productNo, tppCode, topTextList, callback) {
    initPopPage();

    var msgObj=document.getElementById(divId);
    var topTexts = document.getElementById("topTexts");
    var localLabel = null;
    for (var index in topTextList) {
        localLabel = document.createElement("label");
        localLabel.innerText = topTextList[index];
        topTexts.appendChild(localLabel);
        topTexts.appendChild(document.createElement("br"));
    }
    var confirmButton = document.getElementById("popConfirmButton")
    confirmButton.onclick = function () {
        // 校验输入是否一致
        var inputProduct = document.getElementById("confirmProductNo").value;
        var inputTppCode = document.getElementById("confirmTppCode").value;
        if (productNo.toLowerCase() != inputProduct.toLowerCase()
            || inputTppCode.toLowerCase() != tppCode.toLowerCase()) {
            localLabel = document.createElement("label");
            localLabel.innerText = '输入不一致!';
            msgObj.appendChild(localLabel);
            msgObj.appendChild(document.createElement("br"));
        } else {
            msgObj.style.display  = "none";
            closeDiv();
            callback();
        }

    }

    var cancelButton = document.getElementById("popCancelButton")

    cancelButton.onclick = function () {

        closeDiv();
        msgObj.style.display  = "none";
    };

}

//把要显示的div居中显示
function EV_Show_msgDiv(msgID){
    var msgObj   = document.getElementById(msgID);
    msgObj.style.display  = "block";
    var msgHeight= msgObj.scrollHeight;
    var bgTop=EV_myScrollTop();
    var bgLeft=EV_myScrollLeft();
    var bgWidth=EV_myClientWidth();
    var bgHeight=EV_myClientHeight();
    var msgTop=bgTop+Math.round((bgHeight-msgHeight)/2);
    var msgLeft=bgLeft+Math.round((bgWidth-300)/2);
    msgObj.style.position = "absolute";
    msgObj.style.top      = msgTop+"px";
    msgObj.style.left     = msgLeft+"px";
    msgObj.style.zIndex   = "10001";
    msgObj.style.backgroundColor = "#FFFFFF";
    msgObj.style.overflow = "hidden";
    msgObj.style.border = "2px solid #9bdf70;background:#f0fbeb";
}
//背景框满窗口显示
function EV_Show_bgDiv(){
    //创建背景框
    var bgObj=document.createElement("div");
    bgObj.setAttribute('id',backGroundDivName);
    document.body.appendChild(bgObj);

    var bgWidth=EV_myClientWidth();
    var bgHeight=EV_myClientHeight();
    var bgTop=EV_myScrollTop();
    var bgLeft=EV_myScrollLeft();
    bgObj.style.position   = "absolute";
    bgObj.style.top        = bgTop+"px";
    bgObj.style.left       = bgLeft+"px";
    bgObj.style.width      = bgWidth + "px";
    bgObj.style.height     = bgHeight + "px";
    bgObj.style.zIndex     = "10000";
    bgObj.style.background = "#777";
    bgObj.style.filter     = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=60,finishOpacity=60);";
    bgObj.style.opacity    = "0.6";
}
//网页被卷去的上高度
function EV_myScrollTop(){
    var n=window.pageYOffset
        || document.documentElement.scrollTop
        || document.body.scrollTop || 0;
    return n;
}
//网页被卷去的左宽度
function EV_myScrollLeft(){
    var n=window.pageXOffset
        || document.documentElement.scrollLeft
        || document.body.scrollLeft || 0;
    return n;
}
//网页可见区域宽
function EV_myClientWidth(){
    var n=document.documentElement.clientWidth
        || document.body.clientWidth || 0;
    return n;
}
//网页可见区域高
function EV_myClientHeight(){
    var n=document.documentElement.clientHeight
        || document.body.clientHeight || 0;
    return n;
}