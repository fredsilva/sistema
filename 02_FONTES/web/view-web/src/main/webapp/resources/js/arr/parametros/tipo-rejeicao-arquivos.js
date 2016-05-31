function selectRow(data) {
    $("#tipo-rejeicao-form\\:codigo").attr('readonly', true).val(data[0]);
    $("#tipo-rejeicao-form\\:motivo").val(data[1]);
    $("#tipo-rejeicao-form\\:situacao").val(data[3]);

    showBtnUpdate('tipo-rejeicao-form');
}

function resetFields() {
    clearFields('tipo-rejeicao-table');
    clearFields('tipo-rejeicao-form');

    $("#tipo-rejeicao-form\\:codigo").attr('readonly', false);

    showBtnSave('tipo-rejeicao-form');
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}