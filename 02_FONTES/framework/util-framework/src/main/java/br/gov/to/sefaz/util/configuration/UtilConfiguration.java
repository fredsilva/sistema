package br.gov.to.sefaz.util.configuration;

import br.gov.to.sefaz.util.certificado.CertificadoPackageMarker;
import br.gov.to.sefaz.util.mail.MailPackageMarker;
import br.gov.to.sefaz.util.properties.AppProperties;
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
@ComponentScan(basePackageClasses = {MailPackageMarker.class, CertificadoPackageMarker.class})
public class UtilConfiguration {

    /**
     * Cria um {@link JavaMailSender} configurado com os parametros de email da aplicação
     * (email.host, email.port, email.username, email.password).
     *
     * @return enviador de email configurado
     * @see AppProperties#getProperty(String)
     */
    @Bean
    public JavaMailSender javaMailSender() {
        String host = AppProperties.getProperty("email.host").orElse("");
        String port = AppProperties.getProperty("email.port").orElse("");
        String username = AppProperties.getProperty("email.username").orElse("");
        String password = AppProperties.getProperty("email.password").orElse("");

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