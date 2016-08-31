function validateUserCpfCnpj(url, errorMessage) {

    $('#nomeEmpresa').text("");
    $('#nomeUsuario').text("");

    $.ajax({
        type: "GET",
        url: url,
        xhrFields: {withCredentials: true},
        data: { cpf: $('#atuar-form\\:cpfUsuarioForm').val(), cnpj: $('#atuar-form\\:cnpjUsuarioForm').val() },
        success: function (response) {
            var saved = JSON.parse(response);
            $('#nomeEmpresa').text(saved.nomeEmpresa);
            $('#nomeUsuario').text(saved.nomeUsuario);
            alertModal.putInfo(saved.message);
            alertModal.show();
        },
        error: function (error) {
            if (error.status == 0) {
                alertModal.putDanger(errorMessage);
                alertModal.show();
            }
        }
    });
}