var homePage = "";

function setHomePage(url) {
    homePage = url;
}

function redirectOnSuccess(data) {
    onSuccess(data, function () {
        window.location.href = homePage;
    });
}

function authCertificadoDigital(url, errorMessage) {
    $.ajax({
        type: "GET",
        url: url,
        xhrFields: {withCredentials: true},
        success: function () {
            window.location.href = homePage;
        },
        error: function (error) {
            if (error.status == 0) {
                alertModal.putDanger(errorMessage);
                alertModal.show();
            }
        }
    });
}

function resetOnSuccess(data) {
    onSuccess(data, function () {
        clearFields('reset-password-form');
        var modal = $('#reset-password-modal').modal();
        modal.modal('hide');
    });
}

$(document).ready(function () {
    $("#login-form\\:password").keypress(function(e) {
        if(e.which == 13) {
            $('#login-form\\:submit-login').click();
        }
    });
});