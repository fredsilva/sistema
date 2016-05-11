function selectRow(data) {
    $("#banco-form\\:codigo").attr('readonly', true).val(data[0]);
    $("#banco-form\\:nome").val(data[1]);
    $("#banco-form\\:cnpj").val(data[2]);
    $("#banco-form\\:situacao").val(data[4]);

    showBtnUpdate('banco-form');
}

function resetFields() {
    clearFields('banco-table');
    clearFields('banco-form');

    $("#banco-form\\:codigo").attr('readonly', false);

    showBtnSave('banco-form');
}

function resetOnSuccess(data) {
    resetFields();
}