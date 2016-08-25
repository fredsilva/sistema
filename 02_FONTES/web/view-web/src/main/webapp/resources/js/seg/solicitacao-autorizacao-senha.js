function formatCep() {
    if ($('#cepUsuarioForm').val() == 5) {
        var cep = $('#cepUsuarioForm').val();
        cep = cep + '-';
        $('#cepUsuarioForm').value = cep;
        return true;
    }
}

function authUserCertificate(url, errorMessage) {
    var respose = {};
    $.ajax({
        type: "GET",
        url: url,
        datatype: JSON,
        success: function (response) {
            var user = JSON.parse(response);
            editUser(user.cpfUsuario, user.nomeUsuario, user.emailUsuario);
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
        window.location.href = 'login.jsf';
    });
}