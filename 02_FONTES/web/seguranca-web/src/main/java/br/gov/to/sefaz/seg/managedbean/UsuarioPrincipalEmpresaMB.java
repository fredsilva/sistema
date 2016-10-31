package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.UsuarioPrincipalEmpresaFacade;
import br.gov.to.sefaz.seg.managedbean.viewbean.UsuarioPrincipalEmpresaViewBean;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import br.gov.to.sefaz.util.certificado.CertificadoDigitalException;
import br.gov.to.sefaz.util.certificado.CertificadoDigitalUtil;
import br.gov.to.sefaz.util.json.JsonMapperUtils;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * ManagedBean dos tipos de usuários para a tela Solicitar para atuar como usuário principal de uma empresa.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 26/08/2016 16:20:00
 */
@ManagedBean(name = "usuarioPrincipalEmpresaMB")
@ViewScoped
public class UsuarioPrincipalEmpresaMB extends DefaultCrudMB<UsuarioPrincipalEmpresa, Long> {

    @Autowired
    private CertificadoDigitalUtil certificadoDigitalUtil;

    public UsuarioPrincipalEmpresaMB() {
        super(UsuarioPrincipalEmpresa::new);
    }

    /**
     * {@link br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB#setFacade(
     * br.gov.to.sefaz.business.facade.CrudFacade)}.
     */
    @Autowired
    public void setFacade(UsuarioPrincipalEmpresaFacade facade) {
        super.setFacade(facade);
    }

    @Autowired
    public void setCertificadoDigitalUtil(CertificadoDigitalUtil certificadoDigitalUtil) {
        this.certificadoDigitalUtil = certificadoDigitalUtil;
    }

    @Override
    protected UsuarioPrincipalEmpresaFacade getFacade() {
        return (UsuarioPrincipalEmpresaFacade) super.getFacade();
    }

    /**
     * Retorna o CNPJ da empresa, através do certificado digital.
     *
     * @throws java.security.cert.CertificateParsingException exceção ao processar o certificado digital
     * @throws CertificadoDigitalException exceção ao acessar por certificado digital
     */
    public String requestCompanyMainUser(String cnpj, String cpf) throws CertificateParsingException,
            CertificadoDigitalException {
        Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        X509Certificate[] certChain = (X509Certificate[]) requestMap.get("javax.servlet.request.X509Certificate");

        if (!StringUtils.isEmpty(cnpj)) {
            cnpj = cnpj.replaceAll("[\\D]", StringUtils.EMPTY);
        }

        if (!StringUtils.isEmpty(cpf)) {
            cpf = cpf.replaceAll("[\\D]", StringUtils.EMPTY);
        }

        getDto().setCnpjEmpresa(cnpj);

        getDto().setCpfUsuario(cpf);

        getDto().setECnpj(certificadoDigitalUtil.getCnpjEmpresa(certChain));

        UsuarioPrincipalEmpresa save = getFacade().save(getDto());

        return JsonMapperUtils.objectToJson(new UsuarioPrincipalEmpresaViewBean(save.getNomeUsuario(),
                save.getNomeEmpresa(),
                SourceBundle.getMessage(MessageUtil.SEG, "seg.gestao.usuarioPrincipalEmpresa.sucesso.operacao")));
    }
}
