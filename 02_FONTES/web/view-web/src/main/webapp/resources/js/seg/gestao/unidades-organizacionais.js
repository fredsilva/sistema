function selectRow(data) {
    openModal();
    showBtnSave("unidade-organizacional-form");
    $("#unidade-organizacional-form\\:identificacaoUnidOrganizacForm").val(null);
    $("#unidade-organizacional-form\\:nomeUnidOrganizacForm").val(data[1]);
    $("#unidade-organizacional-form\\:enderecoForm").val(data[4]);
    $("#unidade-organizacional-form\\:chefeGeralForm").val(data[5]);
    $("#unidade-organizacional-form\\:telefoneForm").val(data[6]);
    $("#unidade-organizacional-form\\:nomeUnidOrganizacPaiForm").val(data[7]);
    $("#unidade-organizacional-form\\:tipoUnidadeForm").val(data[8]);
}

function editRow(data) {
    openModal();
    showBtnUpdate("unidade-organizacional-form");
    $("#unidade-organizacional-form\\:identificacaoUnidOrganizacForm").val(data[0]);
    $("#unidade-organizacional-form\\:nomeUnidOrganizacForm").val(data[1]);
    $("#unidade-organizacional-form\\:enderecoForm").val(data[4]);
    $("#unidade-organizacional-form\\:chefeGeralForm").val(data[5]);
    $("#unidade-organizacional-form\\:telefoneForm").val(data[6]);
    $("#unidade-organizacional-form\\:nomeUnidOrganizacPaiForm").val(data[7]);
    $("#unidade-organizacional-form\\:tipoUnidadeForm").val(data[8]);
}

function resetFields() {
    closeModal();
    showBtnSave("unidade-organizacional-form");
    clearFields('unidade-organizacional-form');
    clearFields('unidades-organizacionais-table');
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}

function phoneMask() {
    $("#unidade-organizacional-form\\:telefoneForm").mask('(00) 00000-0009');
    if ($("#unidade-organizacional-form\\:telefoneForm").val().length == 15) { // Celular com 9 dígitos + 2 dígitos DDD e 4 da máscara
        $("#unidade-organizacional-form\\:telefoneForm").mask('(00) 00000-0009');
    } else {
        $("#unidade-organizacional-form\\:telefoneForm").mask('(00) 0000-00009');
    }
}

function openModal() {
    var modal = $('#unidadeOrganizacionalModal').modal();
    modal.modal('toggle');
    modal.modal('show');
}
function openModalInsert() {
    showBtnSave("unidade-organizacional-form");
    openModal();
}

function closeModal() {
    var modal = $('#unidadeOrganizacionalModal').modal();
    modal.modal('hide');
}

function openUnidadeSearchOnSuccess(data) {
    onSuccess(data, function () {
        $('#unidadeOrganizacionalModal').modal();
        getDataTable("unidade-organizacional-form").draw();
    });
}

$(document).ready(function () {
    $('#unidadeOrganizacionalModal').on('hidden.bs.modal', function () {
        clearFields("unidadeOrganizacionalModal");
    });
});