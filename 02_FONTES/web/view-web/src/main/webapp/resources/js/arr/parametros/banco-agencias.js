function selectRowAgencias(data) {
	$("#bancoAgencias-form\\:estado").val(data[10]).change();
	setTimeout(function(){$("#bancoAgencias-form\\:municipio").val(data[11]);}, 50000000000);
	$("#bancoAgencias-form\\:idAgencia").attr('readonly', true).val(data[7]);
	$("#bancoAgencias-form\\:nomeAgencia").val(data[1]);
	$("#bancoAgencias-form\\:cnpjAgencia").val(data[2]);
	$("#bancoAgencias-form\\:email").val(data[5]);
	$("#bancoAgencias-form\\:digitoAgencia").val(data[8]);
	$("#bancoAgencias-form\\:situacaoAgencia").val(data[9]);
	$("#bancoAgencias-form\\:centralizadora").prop("checked", JSON.parse(data[12]));
	$("#bancoAgencias-form\\:numeroContaCorrente").val(data[13]);
	$("#bancoAgencias-form\\:digitoContaCorrente").val(data[14]);
	$("#banco-form\\:idAgencia").attr('readonly', true);
	showBtnUpdate('bancoAgencias-form');
}

function resetFieldsAgencias() {
	clearFields('bancoAgencias-form');
	$("#bancoAgencias-form\\:estado").change();
	$("#bancoAgencias-form\\:idAgencia").attr('readonly', false);
	showBtnSave('bancoAgencias-form');
}

function resetOnSuccessAgencias(data) {
	onSuccess(data, resetFieldsAgencias);
}