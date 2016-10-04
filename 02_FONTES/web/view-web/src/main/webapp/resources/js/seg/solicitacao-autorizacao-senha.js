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

$(document).ready(function () {
    $("#cpfInvalido").show();
    $("#solicita-autorizacao-form\\:btn-continuar").addClass('disabled');

    $("#solicita-autorizacao-form\\:cpfUsuarioCadastroForm").on("keyup blur", function(event) {
        if (validaCPF(this.value)) {
            $("#cpfInvalido").hide();
            $("#solicita-autorizacao-form\\:btn-continuar").removeClass('disabled');
        } else {
            $("#cpfInvalido").show();
            $("#solicita-autorizacao-form\\:btn-continuar").addClass('disabled');
        }
    });
});

function validaCPF(cpf) {
    var numeros, digitos, soma, i, resultado;
    if (!cpf) {
        return false;
    }

    cpf = cpf.replace(/[.-]/g, '');

    if (cpf.length != 11) {
        return false;
    }

    numeros = cpf.substring(0,9);
    digitos = cpf.substring(9);
    soma = 0;
    for (i = 10; i > 1; i--) {
        soma += numeros.charAt(10 - i) * i;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0)) {
        return false;
    }
    numeros = cpf.substring(0,10);
    soma = 0;
    for (i = 11; i > 1; i--) {
        soma += numeros.charAt(11 - i) * i;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

    return resultado == digitos.charAt(1);

}