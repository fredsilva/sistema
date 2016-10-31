function resetFieldsDare() {
    clearFields('dare-eletronico-form');
    clearDtos();
}

/*
 Recolher Documentos
 */
function addOrRemoveDebitoFromSelectedList(totalRecolher, element) {
    if (element.is(':checked')) {
        addDebitosToSelectedList(totalRecolher);
    } else {
        $('#dare-table-debitos-header-checkbox').prop("checked", false);
        removeDebitosFromSelectedList(totalRecolher);
    }
}

function setChecked(data, element) {
    element.prop("checked", data);
}

function checkAllDocumentoRecolher(check) {
    if (check.checked) {
        //Adiciona todos os docs para a lista de selecionados.
        addAllDocumentoRecolherToSelectedList();
    } else {
        //Remove todos os docs.
        removeAllDebitosFromSelectedList();
    }
}

function checkAllDocumentoRecolherOnSuccess() {
    getDataTable("dare-table-debitos").$(".select-row").prop("checked", true);
}

function uncheckAllDocumentoRecolherOnSuccess() {
    getDataTable("dare-table-debitos").$(".select-row").prop("checked", false);
}

function habilitarBotoesAposGerarDare() {
    $('#dare-eletronico-form\\:btnGerar').addClass('disabled');
    $('#dare-eletronico-form\\:btnAdicionarItem').addClass('disabled');
    $('#dare-eletronico-form\\:btnPrint').removeClass('disabled');
    $('#dare-eletronico-form\\:btnEmail').removeClass('disabled');
}

/*
 Email DARE
 */
function openEmailModal() {
    $("#dareEletronicoEmailModal").modal("show");
}

$(document).ready(function () {
    var field = $("#cpfCnpj");
    $(field).maskCnpjCpf();

    $('#dareEletronicoEmailModal').on('hidden.bs.modal', function () {
        clearFields("dare-email-form");
    });
});