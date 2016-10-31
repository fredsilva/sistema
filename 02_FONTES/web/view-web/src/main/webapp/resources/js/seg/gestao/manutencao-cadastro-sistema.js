/**
 * Created by fabio.fucks on 05/07/2016.
 */

function editRow(data) {
    openModal();

    showBtnUpdate("manutencao-cadastro-sistema-form");
    $('#manutencaoCadastroSistemaModalTitleInsert').hide();
    $('#manutencaoCadastroSistemaModalTitleEdit').show();
    $("#manutencao-cadastro-sistema-form\\:identificacaoModuloSistemaForm").val(data[0]);
    $("#manutencao-cadastro-sistema-form\\:abreviacaoForm").val(data[1]);
    $("#manutencao-cadastro-sistema-form\\:descricaoForm").val(data[2]);
}

function openModalInsert() {
    showBtnSave("manutencao-cadastro-sistema-form");
    $('#manutencaoCadastroSistemaModalTitleInsert').show();
    $('#manutencaoCadastroSistemaModalTitleEdit').hide();
    openModal();
}

function openModal() {
    var modal = $('#manutencaoCadastroSistemaModal').modal();
    modal.modal('toggle');
    modal.modal('show');
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}

function resetFields() {
    closeModal();
    clearFields('manutencao-cadastro-sistema-form');
    clearFields('manutencao-cadastro-sistema-table');
}

function closeModal() {
    var modal = $('#manutencaoCadastroSistemaModal').modal();
    modal.modal('hide');
}

$(document).ready(function () {
    $('#manutencaoCadastroSistemaModal').on('hidden.bs.modal', function () {
        clearFields("manutencaoCadastroSistemaModal");
    });
});