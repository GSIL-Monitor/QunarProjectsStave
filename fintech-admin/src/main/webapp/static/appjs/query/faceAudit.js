function doAudit() {
    var result = $('#result').val();
    var auditResult = $('input:radio[name="auditResult"]:checked').val();
    var faceType = $('#faceType').val();
    if (auditResult != null) {
        var formData = {
            token: $("#token").text(),
            auditResult: auditResult,
            auditUser: sessionStorage.getItem("adminName"),
            faceType: faceType,
            idCode: $("#idCode").text(),
            auditRemark: $('#auditRemark').val()
        }

        if (auditResult == '1') {

            submitAudit(formData);
        } else if (auditResult == '4') {
            formData.x = x;
            formData.y = y;
            formData.x2 = x2;
            formData.y2 = y2;
            formData.height = height;
            formData.width = width;
            formData.base64IdPositiveImg = base64IdPositiveImg;
            formData.base64IdNegativeImg = base64IdNegativeImg;
            formData.base64HoldIdImg = base64HoldIdImg;
            formData.auditRemark = $('#upAuditRemark').val();
            formData.holdIdImgName = holdIdImgName;
            formData.idPositiveImgName = idPositiveImgName;
            formData.idNegativeImgName = idNegativeImgName;

            if (!base64HoldIdImg) {
                alert("请上传手持照片");
                return;
            }

            if (!base64IdNegativeImg) {
                alert("请上传反面照片");
                return;
            }
            if (!base64IdPositiveImg) {
                alert("请上传正面照片");
                return;
            }
            if (!x || !y || !x2 || !y2 || !height || !width) {
                alert("请截图");
                return;
            }
            if (window.confirm("审核结果一致，确认将本次街衢图片作为下次识别对比照？")) {
                submitAudit(formData);
            } else
                return;
        } else
            submitAudit(formData);
    } else {
        alert("审核结果不能为空")
    }
}

function submitAudit(formData) {
    $('#submitBtn').button('loading');
    $.ajax({
        type: "POST",
        url: "/finance/face/doAudit",
        headers: {'Content-type': 'application/json'},
        data: JSON.stringify(formData),
        error: function () {
            alert('发生错误');
            $('#submitBtn').button('reset');
        },
        success: function (datas) {
            $('#submitBtn').button('reset');
            if (datas == '0000') {
                /*if (formData.auditResult != '4'){*/
                alert("操作成功");
                window.close();
                //}else{
                //     window.location.href='/face/qryIdImages.do?identityCode='+formData.identityCode;
                // }


            } else {
                alert("操作失败!")
            }

        }
    });


}

$(function () {

    //正面照片
    var input = document.getElementById("upPositiveIdImg");
    input.addEventListener('change', function () {
        var file = this.files[0];
        readFile(file, 'upPositiveIdImg')
    }, false);
    //反面照片
    var input2 = document.getElementById("upNegativeIdImg");
    input2.addEventListener('change', function () {
        var file = this.files[0];
        readFile(file, 'upNegativeIdImg')
    }, false);
    //手持照片
    var input3 = document.getElementById("upHoldIdImg");
    input3.addEventListener('change', function () {
        var file = this.files[0];
        readFile(file, 'holdIdImg')
    }, false);

});
var base64IdPositiveImg = null;
var base64IdNegativeImg = null;
var base64HoldIdImg = null;
var holdIdImgName = null, idPositiveImgName = null, idNegativeImgName = null;
var jcrop_api = null;

function readFile(file, type) {

    if (!/image\/\w+/.test(file.type)) {
        alert("文件必须为图片！");
        return false;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function (e) {

        var rs = e.target.result;
        var rsArray = rs.split(",");

        if (type == 'upPositiveIdImg') {
            idPositiveImgName = file.name;

            base64IdPositiveImg = rsArray[1];
            $('#previewDiv').html('<img id="upIdImgPreView" src="' + rs + '"/>');
            $('#upIdImgPreView').Jcrop({onSelect: showCoords}, function () {
                jcrop_api = this;
            });
        } else if (type == 'upNegativeIdImg') {
            base64IdNegativeImg = rsArray[1];
            idNegativeImgName = file.name;
        } else {
            holdIdImgName = file.name;
            base64HoldIdImg = rsArray[1];
        }


    }
}

var x, y, x2, y2, width, height;

function showCoords(c) {
    x = c.x + "";
    y = c.y + "";
    x2 = c.x2 + "";
    y2 = c.y2 + "";
    width = c.w + "";
    height = c.h + "";
}

function okModal() {
    doAudit();
}

function closeModal() {
    base64IdImg = null;
    base64HoldIdImg = null;
    x = y = x2 = y2 = width = height = "";
    if (jcrop_api)
        jcrop_api.destroy();
    $('#uploadModal').modal('hide');
}