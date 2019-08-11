$.validator.setDefaults({
    highlight: function (e) {
        $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
    }, success: function (e) {
        e.closest(".form-group").removeClass("has-error").addClass("has-success")
    }, errorElement: "span", errorPlacement: function (e, r) {
        e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
    }, errorClass: "help-block m-b-none", validClass: "help-block m-b-none"
}), $().ready(function () {
    $("#commentForm").validate();
    var e = "<i class='fa fa-times-circle'></i> ";
    $("#infoForm").validate({
        rules: {
            username: {required: !0, minlength: 2},
            password: {required: !0, minlength: 5},
            confirm_password: {required: !0, minlength: 5, equalTo: "#password"},
            topic: {required: "#newsletter:checked", minlength: 2},
            agree: "required"
        },
        messages: {

            username: {required: e + "请输入您的用户名", minlength: e + "用户名必须两个字符以上"},
            password: {required: e + "请输入您的密码", minlength: e + "密码必须5个字符以上"},
            confirm_password: {required: e + "请再次输入密码", minlength: e + "密码必须5个字符以上", equalTo: e + "两次输入的密码不一致"},
            agree: {required: e + "必须同意协议后才能注册", element: "#agree-error"}
        }
    })
});





$.validator.setDefaults({
    highlight: function (e) {
        $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
    }, success: function (e) {
        e.closest(".form-group").removeClass("has-error").addClass("has-success")
    }, errorElement: "span", errorPlacement: function (e, r) {
        e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
    }, errorClass: "help-block m-b-none", validClass: "help-block m-b-none"
}), $().ready(function () {
    $("#commentForm").validate();
    var e = "<i class='fa fa-times-circle'></i> ";
    $("#infoForm1").validate({
        rules: {
            username: {required: !0, minlength: 2},
            password: {required: !0, minlength: 5},
            confirm_password: {required: !0, minlength: 5, equalTo: "#password1"},
            topic: {required: "#newsletter:checked", minlength: 2},
            agree: "required"
        },
        messages: {

            username: {required: e + "请输入您的用户名", minlength: e + "用户名必须两个字符以上"},
            password: {required: e + "请输入您的密码", minlength: e + "密码必须5个字符以上"},
            confirm_password: {required: e + "请再次输入密码", minlength: e + "密码必须5个字符以上", equalTo: e + "两次输入的密码不一致"},
            agree: {required: e + "必须同意协议后才能注册", element: "#agree-error"}
        }
    })
});

