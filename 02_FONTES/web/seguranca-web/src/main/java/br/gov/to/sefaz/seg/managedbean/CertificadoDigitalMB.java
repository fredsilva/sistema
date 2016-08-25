package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.seg.business.authentication.facade.LoginSistemaFacade;
import br.gov.to.sefaz.seg.managedbean.viewbean.UsuarioCertificadoViewBean;
import br.gov.to.sefaz.util.certificado.CertificadoDigitalException;
import br.gov.to.sefaz.util.certificado.CertificadoDigitalUtil;
import br.gov.to.sefaz.util.json.JsonMapperUtils;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Optional;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * ManagedBean de login através do certificado digital.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/06/2016 17:19:00
 */
@ManagedBean(name = "certificadoDigitalMB")
@Component
public class CertificadoDigitalMB {

    @Autowired
    private LoginSistemaFacade loginSistemaFacade;
    @Autowired
    private CertificadoDigitalUtil certificadoDigitalUtil;

    /**
     * Retorna o CPF do usuário através do certificado digital.
     *
     * @return cpf do Usuário
     * @throws CertificateParsingException exceção ao processar o certificado digital
     * @throws CertificadoDigitalException exceção ao acessar por certificado digital
     */
    public String authenticate() throws CertificateParsingException, CertificadoDigitalException {
        Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        X509Certificate[] certChain = (X509Certificate[]) requestMap.get("javax.servlet.request.X509Certificate");
        Optional<String> optionalCpf = certificadoDigitalUtil.getCpf(certChain);

        if (optionalCpf.isPresent()) {
            String cpf = optionalCpf.get();
            loginSistemaFacade.certAuthenticate(cpf);
            return cpf;
        }

        throw new CertificadoDigitalException(
                SourceBundle.getMessage(MessageUtil.SEG, "login.certificadoDigital.cpf.invalido"));
    }

    /**
     * Retorna o CPF, nomeCompleto e EMail do usuário, através do certificado digital.
     *
     * @return Lista de dados que será utilizada em tela.
     * @throws CertificateParsingException exceção ao processar o certificado digital
     * @throws CertificadoDigitalException exceção ao acessar por certificado digital
     */
    public String requestUser() throws CertificateParsingException, CertificadoDigitalException {
        Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        X509Certificate[] certChain = (X509Certificate[]) requestMap.get("javax.servlet.request.X509Certificate");

        UsuarioCertificadoViewBean retorno = new UsuarioCertificadoViewBean(certificadoDigitalUtil.getCpf(certChain)
                .orElse(""),certificadoDigitalUtil.getNomeCompleto(certChain),
                certificadoDigitalUtil.getMail(certChain));

        return JsonMapperUtils.objectToJson(retorno);
    }

}
