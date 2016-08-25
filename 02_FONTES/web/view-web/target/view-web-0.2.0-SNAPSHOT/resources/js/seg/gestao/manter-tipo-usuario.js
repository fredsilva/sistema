function editRow(data) {
    openModal();

    showBtnUpdate("tipo-usuario-form");
    $("#tipo-usuario-form\\:identificacaotipoUsuarioForm").val(data[0]);
    $("#tipo-usuario-form\\:descricaoTipoUsuarioForm").val(data[1]);
}

function resetFields() {
    closeModal();
    clearFields("tipo-usuario-form");
    clearFields("tipos-usuarios-table");
}

function resetOnSuccess(data) {
    loadAllTipoUsuario();
    onSuccess(data, resetFields);
}

function openModal() {
    var modal = $('#tipoUsuarioModal').modal();
    modal.modal('toggle');
    modal.modal('show');
}
function openModalInsert() {
    showBtnSave("tipo-usuario-form");
    clearFields("tipo-usuario-form")
    $("#tipo-usuario-form\\:identificacaotipoUsuarioForm").val(null);
    $("#tipo-usuario-form\\:descricaoTipoUsuarioForm").val(null);
    openModal();
}

function closeModal() {
    var modal = $('#tipoUsuarioModal').modal();
    modal.modal('hide');
}

$(document).ready(function () {
    $('#tipoUsuarioModal').on('hidden.bs.modal', function () {
        clearFields("tipoUsuarioModal");
    });
});