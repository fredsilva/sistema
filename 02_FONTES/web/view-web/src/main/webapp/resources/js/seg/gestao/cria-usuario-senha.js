
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
        $('#usuario-sistema-filtro').each(function () {
            this.reset();
        });
        closeModal();
    });
}

function showLock(data) {
    return data != 'PENDENTE';
}

function createUser() {
    updateUser();
}

$(document).ready(function () {
    $('#usuarioSistemaModal').on('hidden.bs.modal', function () {
        clearFields("usuarioSistemaModal");
    });
});