function selectRow(data) {
	$("#banco-form\\:codigo").attr('readonly', true).val(data[0]);
	$("#banco-form\\:nome").val(data[1]);
	$("#banco-form\\:cnpj").val(data[2]);
	$("#banco-form\\:situacao").val(data[4]);
	
	// Aba de agências
	$("#agencias").removeClass("hidden");
	$("#banco-selecionado-form\\:idBanco").val(data[0]);
	$("#banco-selecionado-form\\:nomeBanco").val(data[1]);
	loadBancoAgenciasFromSelected(data[0]);

	showBtnUpdate('banco-form');
}

function resetFields() {
	clearFields('banco-table');
	clearFields('banco-form');

	$("#banco-form\\:codigo").attr('readonly', false);

	// Aba de agências
	clearFields('bancoAgencias-form');
	$("#agencias").addClass("hidden");

	showBtnSave('banco-form');
}

function resetOnSuccess(data) {
	onSuccess(data, resetFields);
}