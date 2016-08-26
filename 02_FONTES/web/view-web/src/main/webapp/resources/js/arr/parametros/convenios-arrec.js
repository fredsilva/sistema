function selectRow(data) {
    $("#convenios-arrec-form\\:codigo").attr('readonly', true).val(data[0]);
    $("#convenios-arrec-form\\:descricaoConvenio").val(data[1]);
    $("#convenios-arrec-form\\:banco").val(data[6]);
    getAgenciaBanco(data[7]);
    $("#convenios-arrec-form\\:barra").val(data[8]);
    $("#convenios-arrec-form\\:situacao").val(data[9]);
    $("#convenios-arrec-form\\:versaoArquivo").val(data[10]);
    $("#convenios-arrec-form\\:tipoConvenio").val(data[11]);

    loadConvenioTarifas(data[0]);
    loadConvenioReceitas(data[0]);

    showBtnUpdate('convenios-arrec-form');
}

function resetFields() {
    clearFields('convenios-arrec-table');
    clearFields('convenios-arrec-form');
    clearFields('tarifas-convenio');
    clearFields('receitas-convenio');
    clearFields('convenios-arrec-filtro');

    $("#convenios-arrec-form\\:codigo").attr('readonly', false);
    showBtnSave('convenios-arrec-form');

    clearDtos();
}

function resetReceitasFields() {
    clearFields('tarifas-convenio');
}

function resetReceitasOnSuccess(data) {
    onSuccess(data, resetReceitasFields);
}

function resetTarifasFields() {
    clearFields('tarifas-convenio');
}

function resetTarifasOnSuccess(data) {
    onSuccess(data, resetTarifasFields);
}

function resetOnSuccess(data) {
    onSuccess(data, resetFields);
}