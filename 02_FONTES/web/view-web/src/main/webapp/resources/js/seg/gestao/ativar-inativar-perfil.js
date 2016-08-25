
function resetFields() {
    closeModal();
    clearFields("consulta-usuario-form");
}

function openModal() {
    $('#usuarioSistemaModal').modal("show");
}

function closeModal() {
    $('#usuarioSistemaModal').modal('hide');
}

function resetOnSuccess(data) {
    onSuccess(data, function () {
        $('#consulta-usuario-form').each(function () {
            this.reset();
        });
        $('#ativar-desativar-filtro').each(function () {
            this.reset();
        });
        closeModal();
    });
}

function showLock(data) {
    return data != 'PENDENTE';
}

function setChecked(data, element) {
    if (data == 'ATIVO') {
        element.prop("checked", true);
    }
}

$(document).ready(function () {
    $('#usuarioSistemaModal').on('hidden.bs.modal', function () {
        clearFields("usuarioSistemaModal");
    });
});