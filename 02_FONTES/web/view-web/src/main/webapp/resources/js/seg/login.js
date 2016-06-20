function authCertificadoDigital(url, home) {
    $.ajax({
        type: "GET",
        url: url,
        xhrFields: {withCredentials: true},
        success: function () {
            window.location.href = home;
        },
        error: function (error) {
            try {
                var val = $(error.responseText)[2].innerHTML;
                var jsonData = JSON.parse(val);

                $.each(jsonData, function (index, value) {
                    alertModal.putDanger(value.message);
                });
            } catch (e) {
                alertModal.putDanger('Não foi possível realizar a autenticação através do certificado digital!');
            }
            alertModal.show();
        }
    });
}

function login(url, home) {
    var username = $("#username").val();
    var password = $("#password").val();

    $.ajax({
        type: "POST",
        url: url,
        data: $("#login-form").serialize(),
        xhrFields: {withCredentials: true},
        success: function () {
            window.location.href = home;
        },
        error: function (error) {
            var val = $(error.responseText)[2].innerHTML;
            try {
                var jsonData = JSON.parse(val);

                $.each(jsonData, function (index, value) {
                    alertModal.putDanger(value.message);
                });
            } catch (e) {
                alertModal.putDanger(val);
            }
            alertModal.show();
        }
    });
}

function resetOnSuccess(data) {
    onSuccess(data, function () {
    	clearFields('reset-password-form');
        $('#reset-password-modal').modal('hide');
    });
}