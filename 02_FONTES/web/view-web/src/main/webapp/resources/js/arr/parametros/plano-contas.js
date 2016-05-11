function selectRow(data) {
    $("#plano-conta-form\\:idPlano").val(data[0]);
    $("#plano-conta-form\\:codigo").val(data[0]);
    $("#plano-conta-form\\:nome").val(data[0]);
    $("#plano-conta-form\\:contaHierarquica").val(data[0]);
    $("#plano-conta-form\\:codigoContabil").val(data[0]);
    $("#plano-conta-form\\:tipoConta").val(data[0]);
    $("#plano-conta-form\\:rateio").val(data[0]);
    $("#plano-conta-form\\:situacao").val(data[0]);
    $("#plano-conta-form\\:grupoCnae").val(data[0]);

    showBtnUpdate("plano-conta-form");
}

function resetFields() {
    clearFields('plano-conta-table');
    clearFields('plano-conta-form');

    $('#plano-conta-form').find("#plano-conta-form\\:codigo").attr('readonly', false);
    showBtnSave("plano-conta-form");
}

function resetOnSuccess(data) {
    resetFields();
}