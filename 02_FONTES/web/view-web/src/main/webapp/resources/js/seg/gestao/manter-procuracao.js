function disableFieldsOnSuccess(data) {
    onSuccess(data, function () {
        $("#procurador-form\\:de").attr("disabled", true);
        $("#procurador-form\\:para").attr("disabled", true);
        $("#procurador-form\\:carregar-cpf").attr("disabled", true);
    });
}

function checkOpcao(isUsed, element) {
    if (JSON.parse(isUsed)) {
        element.attr("checked", "checked");
    }
}