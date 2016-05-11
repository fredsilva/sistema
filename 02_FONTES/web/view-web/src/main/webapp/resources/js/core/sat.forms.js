function clearFields(id) {
    id = '#'+id;

    $(id).find('input:text, input:password, input:file, select, textarea').val('');
    $(id).find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    $(id).find('select').each(function() {
        $(this).val($(this).find('option:first').val());
    });
}

$(document).on("keypress", ".justInteger", function(e) {
    return (e.which != 44 && e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) ? false : true;
});

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