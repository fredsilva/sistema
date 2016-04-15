function clearFields(id) {
    id = '#'+id;

    $(id).find('input:text, input:password, input:file, select, textarea').val('');
    $(id).find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    $(id).find('select').each(function() {
        $(this).val($(this).find('option:first').val());
    });
}