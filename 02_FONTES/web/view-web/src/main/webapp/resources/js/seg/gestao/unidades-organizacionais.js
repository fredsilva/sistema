function selectRow(data) {
    openModal();
    showBtnSave("unidade-organizacional-form");
    $('#unidadeOrganizacionalModalTitleInserir').show();
    $('#unidadeOrganizacionalModalTitleEditar').hide();
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
    $('#unidadeOrganizacionalModalTitleInserir').hide();
    $('#unidadeOrganizacionalModalTitleEditar').show();
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

function openModal() {
    var modal = $('#unidadeOrganizacionalModal').modal();
    modal.modal('toggle');
    modal.modal('show');
}
function openModalInsert() {
    showBtnSave("unidade-organizacional-form");
    $('#unidadeOrganizacionalModalTitleInserir').show();
    $('#unidadeOrganizacionalModalTitleEditar').hide();
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