
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

$(document).ready(function () {
    $('#usuarioSistemaModal').on('hidden.bs.modal', function () {
        clearFields("usuarioSistemaModal");
    });
});