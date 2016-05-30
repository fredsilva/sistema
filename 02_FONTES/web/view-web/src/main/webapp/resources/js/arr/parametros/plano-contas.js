function selectRow(data) {
    $("#plano-conta-form\\:idPlano").val(data[6]);
    $("#plano-conta-form\\:codigo").val(data[0]);
    $("#plano-conta-form\\:nome").val(data[1]);
    $("#plano-conta-form\\:contaHierarquica").val(data[4]);
    $("#plano-conta-form\\:codigoContabil").val(data[2]);
    $("#plano-conta-form\\:tipoConta").val(data[10]);
    $("#plano-conta-form\\:rateio").prop('checked', JSON.parse(data[9]));
    $("#plano-conta-form\\:situacao").val(data[8]);
    $("#plano-conta-form\\:grupoCnae").val(data[7]);

    showBtnUpdate("plano-conta-form");
}

function resetFields() {
    clearFields('plano-conta-table');
    clearFields('plano-conta-form');

    showBtnSave("plano-conta-form");
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}