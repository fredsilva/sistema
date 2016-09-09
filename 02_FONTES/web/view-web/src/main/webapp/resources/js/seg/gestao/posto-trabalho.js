function editRow(data) {
    openModal();

    showBtnUpdate("posto-trabalho-form");
    $("#postoTrabalhoModalTitleInsert").hide();
    $("#postoTrabalhoModalTitleEdit").show();
    $("#posto-trabalho-form\\:identificacaoPostoTrabalhoForm").val(data[0]);
    $("#posto-trabalho-form\\:nomePostoTrabalhoForm").val(data[2]);
    $("#posto-trabalho-form\\:identificacaoUnidOrganizacForm").val(data[3]);
}

function resetFields() {
    closeModal();
    clearFields("posto-trabalho-form");
    clearFields("postos-trabalho-table");
}

function resetOnSuccess(data) {
    loadAllUnidadeOrganizacional();
    onSuccess(data, resetFields);
}

function openModal() {
    var modal = $('#postoTrabalhoModal').modal();
    modal.modal('toggle');
    modal.modal('show');
}
function openModalInsert() {
    showBtnSave("posto-trabalho-form");
    clearFields("posto-trabalho-form");
    $("#postoTrabalhoModalTitleEdit").hide();
    $("#postoTrabalhoModalTitleInsert").show();
    $("#posto-trabalho-form\\:identificacaoPostoTrabalhoForm").val(null);
    $("#posto-trabalho-form\\:identificacaoUnidOrganizacForm").val(null);
    openModal();
}

function closeModal() {
    var modal = $('#postoTrabalhoModal').modal();
    modal.modal('hide');
}

function openUnidadeSearchOnSuccess(data) {
    onSuccess(data, function () {
        $('#unidadeOrganizacionalModal').modal();
        getDataTable("posto-trabalho-form").draw();
    });
}

$(document).ready(function () {
    $('#unidadeOrganizacionalModal').on('hidden.bs.modal', function () {
        clearFields("postoTrabalhoModal");
    });
});