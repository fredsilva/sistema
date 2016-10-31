package br.gov.to.sefaz.util.properties;

import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Properties;

/**
 * Classe que será observada por quem interessar, ou seja, pelos observadores.
 * Notifica os observadores sobre alterações nas propriedades customizadas {@link #customProperties}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/10/2016 11:39:00
 */
@Component
public class CustomPropertiesObservable extends Observable {

    private Properties customProperties;

    /**
     * Obtém as propriedades customizadas.
     *
     * @return propriedades customizadas.
     */
    public Properties getCustomProperties() {
        return this.customProperties;
    }

    /**
     * Atualiza as propriedades customizadas, notificando seus observadores.
     *
     * @param customProperties propriedades customizadas.
     */
    public void setCustomProperties(Properties customProperties) {
        this.customProperties = customProperties;

        setChanged();
        notifyObservers();
    }

}
