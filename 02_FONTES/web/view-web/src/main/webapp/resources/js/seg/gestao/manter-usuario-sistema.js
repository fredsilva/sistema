function resetFields() {
    closeModal();
    clearFields("manter-usuario-form");
}

function openModal() {
    $('#manterUsuarioSistemaModal').modal("show");

    var data = $('#manter-usuario-form\\:tipoUsuarioForm').val();

    validateTipoUsuario(data);
}

function openModalCreate() {
    clearFields("manter-usuario-form");
    showBtnSave('manter-usuario-form');
    $("#manter-usuario-form").find('.btn-save').prop('value','Criar Usuário');
    /**
     * Esconde os campos de Solicitação
     */
    $("#manter-usuario-form\\:numeroSolicitacaoLabel").hide();
    $("#manter-usuario-form\\:btnCreate").show();
    $("#manter-usuario-form\\:btnUpdate").hide();
    $("#manter-usuario-form\\:numeroSolicitaoUsuarioForm").hide();
    $("#manter-usuario-form\\:dataSolicitacaoLabel").hide();
    $("#manter-usuario-form\\:dataSolicitacaoUsuarioForm").hide();
    $("#manterUsuarioSistemaModalTitleUpdate").hide();
    $("#manterUsuarioSistemaModalTitleCreate").show();

    /**
     * Habilita os campos para edição
     */
    $("#manter-usuario-form\\:unidadeOrganizacionalForm").prop("disabled", true)
    $("#manter-usuario-form\\:postoTrabalhoForm").prop("disabled", true)
    $("#manter-usuario-form\\:complementoUsuarioForm").prop('readonly', true);
    $("#manter-usuario-form\\:enderecoContatoUsuarioForm").prop('readonly', true);
    $("#manter-usuario-form\\:telefoneResidencialUsuarioForm").prop('readonly', true);
    $("#manter-usuario-form\\:celularUsuarioForm").prop('readonly', true);
    $("#manter-usuario-form\\:crcUsuarioForm").prop('readonly', true);
    $("#manter-usuario-form\\:cnpjEmpresaUsuarioForm").prop('readonly', true);
    $("#manter-usuario-form\\:inscricaoEstadualUsuarioForm").prop('readonly', true);
    $("#manter-usuario-form\\:apartamentoUsuarioForm").prop('readonly', true);
    /**
     * Remove o apenas leitura para edição dos campos.
     */
    $("#manter-usuario-form\\:nomeCompletoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:cpfUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:cepUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:logradouroUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:enderecoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:numeroEnderecoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:bairroUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:estadoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:cidadeUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:correioEletronicoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:tipoUsuarioUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:justificacaoCriacaoUsuarioForm").removeProp('readonly');

    /**
     * Altera a label do nome completo, de acordo com a documentação.
     */

    $("#manter-usuario-form\\:nomeCompletoFormLabelWith").show();
    $("#manter-usuario-form\\:nomeCompletoFormLabelWithout").hide();

    openModal();

}

function openModalUpdate() {
    showBtnUpdate('manter-usuario-form');
    $("#manter-usuario-form").find('.btn-update').prop('value','Salvar');

    /**
     * Mostra o título correto.
     */
    $("#manterUsuarioSistemaModalTitleUpdate").show();
    $("#manterUsuarioSistemaModalTitleCreate").hide();
    /**
     * Mostra os campos de Solicitação
     */
    $("#manter-usuario-form\\:numeroSolicitacaoLabel").show();
    $("#manter-usuario-form\\:numeroSolicitaoUsuarioForm").show();
    $("#manter-usuario-form\\:dataSolicitacaoLabel").show();
    $("#manter-usuario-form\\:dataSolicitacaoUsuarioForm").show();

    /**
     * Desabilita os campos de UsuarioPostoTrabalho
     */
    $("#manter-usuario-form\\:unidadeOrganizacionalForm").prop("disabled", false)
    $("#manter-usuario-form\\:postoTrabalhoForm").prop("disabled", false)

    /**
     * Define os readOnly da tela.
     */
    $("#manter-usuario-form\\:apartamentoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:complementoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:enderecoContatoUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:telefoneResidencialUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:celularUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:crcUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:cnpjEmpresaUsuarioForm").removeProp('readonly');
    $("#manter-usuario-form\\:inscricaoEstadualUsuarioForm").removeProp('readonly');

    /**
     * Altera a label do nome completo, de acordo com a documentação.
     */

    $("#manter-usuario-form\\:nomeCompletoFormLabelWithout").show();
    $("#manter-usuario-form\\:nomeCompletoFormLabelWith").hide();


    openModal();

}

function closeModal() {
    $('#manterUsuarioSistemaModal').modal('hide');
}

function resetOnSuccess(data) {
    onSuccess(data, function () {
        $('#manter-usuario-form').each(function () {
            this.reset();
        });
        $('#manter-usuario-sistema-filtro').each(function () {
            this.reset();
        });
        closeModal();
    });
}

function isActive(data) {
    return data != 'ATIVO';
}

function validateTipoUsuario(data) {
    var unidadeOrganizacional = $("#manter-usuario-form\\:unidadeOrganizacionalForm");
    var postoTrabalho = $("#manter-usuario-form\\:postoTrabalhoForm");
    var unidadeOrganizacionalLabel = $("#manter-usuario-form\\:unidadeOrganizacionalLabel");
    var postoTrabalhoLabel = $("#manter-usuario-form\\:postoTrabalhoLabel");

    if ( data !== '4' ) {

        unidadeOrganizacionalLabel.hide();
        unidadeOrganizacional.hide();
        unidadeOrganizacional.val(null);

        postoTrabalhoLabel.hide();
        postoTrabalho.hide();
        postoTrabalho.val(null);

    } else {

        unidadeOrganizacionalLabel.show();
        unidadeOrganizacional.show();

        postoTrabalhoLabel.show();
        postoTrabalho.show();

    }
}

$(document).ready(function () {
    $('#manterUsuarioSistemaModal').on('hidden.bs.modal', function () {
        clearFields("usuarioSistemaModal");
    });
});