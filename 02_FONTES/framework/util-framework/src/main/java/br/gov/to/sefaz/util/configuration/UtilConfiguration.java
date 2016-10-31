package br.gov.to.sefaz.util.configuration;

import br.gov.to.sefaz.util.certificado.CertificadoPackageMarker;
import br.gov.to.sefaz.util.file.FilePackageMarker;
import br.gov.to.sefaz.util.mail.MailPackageMarker;
import br.gov.to.sefaz.util.properties.AppProperties;
import br.gov.to.sefaz.util.properties.PropertiesPackageMarker;
import br.gov.to.sefaz.util.xml.XmlPackageMarker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Configurações do spring para o modulo.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Configuration
@ComponentScan(basePackageClasses = {MailPackageMarker.class, PropertiesPackageMarker.class,
        CertificadoPackageMarker.class, XmlPackageMarker.class, FilePackageMarker.class})
public class UtilConfiguration {

    /**
     * Cria um {@link JavaMailSender} configurado com os parametros de email da aplicação
     * (email.host, email.port, email.username, email.password).
     *
     * @return enviador de email configurado
     * @see AppProperties#getAppProperty(String)
     */
    @Bean
    public JavaMailSender javaMailSender() {
        String host = AppProperties.getAppProperty("email.host").orElse(StringUtils.EMPTY);
        String port = AppProperties.getAppProperty("email.port").orElse(StringUtils.EMPTY);
        String username = AppProperties.getAppProperty("email.username").orElse(StringUtils.EMPTY);
        String password = AppProperties.getAppProperty("email.password").orElse(StringUtils.EMPTY);

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(Integer.valueOf(port));
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        javaMailSender.setJavaMailProperties(props);

        return javaMailSender;
    }

}