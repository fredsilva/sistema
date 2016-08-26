function selectRow(data) {
    $("#receitas-form\\:idReceita").attr('readonly', true).val(data[0]);
    $("#receitas-form\\:descricaoReceita").val(data[1]);
    $("#receitas-form\\:classificaoReceita").val(data[7]);
    $("#receitas-form\\:tipoReceita").val(data[8]);
    $("#receitas-form\\:situacao").val(data[9]);

    $("#receitas-form\\:dare").prop("checked", JSON.parse(data[10]));
    $("#receitas-form\\:codigoBarra").val(data[11]);
    $("#receitas-form\\:planoContas").val(data[12]);
    $("#receitas-form\\:permiteSubCodigo").prop("checked", JSON.parse(data[13]));

    loadReceitasTaxas(data[0]);
    loadReceitasRepasse(data[0]);

    enableDisableTaxasFields();
    showBtnUpdate('receitas-form');
}

function selectTaxaRow(data) {
    clearDtos();
    $("#receitas-form\\:subCodigo").val(data[0]);
    $("#receitas-form\\:descricaoSubCodigo").val(data[1]);
    $("#receitas-form\\:unidade").val(data[2]);
    $("#receitas-form\\:valorUnitario").val(data[3]);
    $("#receitas-form\\:valorLimite").val(data[4]);
    $("#receitas-form\\:valorAcrescimo").val(data[5]);

    showBtnUpdate('receitas-taxas');
}

function selectRepasseRow(data) {
    $("#receitas-form\\:tipoRepasse").val(data[5]);
    $("#receitas-form\\:percentual").val(data[1]);
    $("#receitas-form\\:dataInicio").val(data[2]);
    $("#receitas-form\\:dataFinal").val(data[3]);
    $("#receitas-form\\:principal").prop("checked", JSON.parse(data[7]));
    $("#receitas-form\\:multa").prop("checked", JSON.parse(data[8]));
    $("#receitas-form\\:juros").prop("checked", JSON.parse(data[9]));
    $("#receitas-form\\:correcao").prop("checked", JSON.parse(data[10]));
    $("#receitas-form\\:taxas").prop("checked", JSON.parse(data[11]));

    showBtnUpdate('receitas-repasses');
}

function resetFields() {
    clearFields('receitas-table');
    clearFields('receitas-form');
    clearFields('receitas-taxas');
    clearFields('receitas-repasses');
    clearFields('receitas-filtro');

    $("#receitas-form\\:idReceita").attr('readonly', false);
    disableTaxasFields();

    showBtnSave('receitas-form');

    clearDtos();
}

function enableDisableTaxasFields() {
    if ($("#receitas-form\\:permiteSubCodigo").is(":checked")) {
        enableTaxasFields();
        $("#receitas-form\\:save-add-taxa").attr('disabled', false);
        $("#receitas-form\\:update-add-taxa").attr('disabled', false);
    } else {
        disableTaxasFields();
        $("#receitas-form\\:add-taxa").attr('disabled', true);
        $("#receitas-form\\:update-add-taxa").attr('disabled', false);
    }
}
function disableTaxasFields() {
    $("#receitas-form\\:subCodigo").attr('readonly', true);
    $("#receitas-form\\:descricaoSubCodigo").attr('readonly', true);
    $("#receitas-form\\:unidade").attr('readonly', true);
    $("#receitas-form\\:valorUnitario").attr('readonly', true);
    $("#receitas-form\\:valorLimite").attr('readonly', true);
    $("#receitas-form\\:valorAcrescimo").attr('readonly', true);
    $("#receitas-form\\:valorAcrescimo").attr('readonly', true);
    $("#receitas-form\\:add-taxa").attr('disabled', true);
    $("#receitas-form\\:update-add-taxa").attr('disabled', false);

}

function enableTaxasFields() {
    $("#receitas-form\\:subCodigo").attr('readonly', false);
    $("#receitas-form\\:descricaoSubCodigo").attr('readonly', false);
    $("#receitas-form\\:unidade").attr('readonly', false);
    $("#receitas-form\\:valorUnitario").attr('readonly', false);
    $("#receitas-form\\:valorLimite").attr('readonly', false);
    $("#receitas-form\\:valorAcrescimo").attr('readonly', false);
    $("#receitas-form\\:save-add-taxa").attr('disabled', false);
    $("#receitas-form\\:update-add-taxa").attr('disabled', false);
}

function resetTaxasFields() {
    clearFields('receitas-taxas');
    showBtnSave('receitas-taxas');
}

function resetTaxasOnSuccess(data) {
    onSuccess(data, resetTaxasFields);
}

function resetRepassesFields() {
    clearFields('receitas-repasses');
    showBtnSave('receitas-repasses');
}

function resetRepassesOnSuccess(data) {
    onSuccess(data, resetRepassesFields);
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}

$(document).ready(function () {
    disableTaxasFields();
});