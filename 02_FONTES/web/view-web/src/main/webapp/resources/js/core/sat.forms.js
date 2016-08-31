function clearFields(id) {
    id = '#' + id;

    // Campos HIDDEN não devem ser limpos pos o JSF usa diverssos inputs hidden para se achar.
    $(id).find('input:text, input:password, input:file, select, textarea').val('');
    $(id).find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    $(id).find('select').each(function () {
        $(this).val($(this).find('option:first').val());
    });
}

function showBtnSave(formId) {
    var form = $('#' + formId);

    form.find('.btn-save').show();
    form.find('.btn-update').hide();
}

function showBtnUpdate(formId) {
    var form = $('#' + formId);

    form.find('.btn-save').hide();
    form.find('.btn-update').show();
}

function setRadioValue(name, SelectdValue) {
    $('input[name="' + name+ '"][value="' + SelectdValue + '"]').prop('checked', true);
}

function findTab(navId, tabId) {
    if (tabId) {
        return $("#" + navId).find("[href='#" + tabId + "']");
    }
    tabId = navId;

    return $("[href='#" + tabId + "']");
}

function disableTab(navId, tabId) {
    findTab(navId, tabId).removeAttr("data-toggle").parents("li").addClass("disabled");
}

function enableTab(navId, tabId) {
    findTab(navId, tabId).attr("data-toggle", "tab").parents("li").removeClass("disabled");
}

$(document).on("keypress", ":input:not(textarea)", function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
    }
});

$(document).on("keypress", ".justInteger", function (e) {
    return (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) ? false : true;
});

$(document).on("paste", ".justInteger", function (e) {
    e.preventDefault();
    e.target.value = e.originalEvent.clipboardData.getData('text').replace(/\D/g, '');
    return false;

});

$(document).on("keypress", ".justCharacter", function(e){
    var inputValue = e.which;
    if(!((inputValue >= 65 && inputValue <= 90) || (inputValue >= 97 && inputValue <= 122))) {
        event.preventDefault();
    }
});

$(document).ready(function () {
    $('.cpf').mask('000.000.000-00', {placeholder: "___.___.___-__", reverse: true});
});

$(document).ready(function () {
    $('.cep').mask('00000-000', {placeholder: "_____-___", reverse: true});
});

$(document).ready(function () {
    $(".decimal").maskMoney({thousands: '.', decimal: ',', allowZero: true});
});