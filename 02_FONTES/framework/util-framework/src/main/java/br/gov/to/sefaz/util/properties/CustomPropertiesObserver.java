package br.gov.to.sefaz.util.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.Properties;

/**
 * Classe que recebe as atualizações das propriedades customizadas {@link #customProperties}.
 * Também pode avisar seus observadores quando for atualizada.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 11/10/2016 15:27:00
 */
@Component
public class CustomPropertiesObserver extends Observable implements Observer {

    private Properties customProperties;

    @Autowired
    public CustomPropertiesObserver(CustomPropertiesObservable customPropertiesObservable) {
        customPropertiesObservable.addObserver(this);

        this.customProperties = AppProperties.getCustomProperties();

    }

    @Override
    public void update(Observable o, Object arg) {
        CustomPropertiesObservable observable = (CustomPropertiesObservable) o;
        customProperties = observable.getCustomProperties();

        setChanged();
        notifyObservers();
    }

    public Properties getCustomProperties() {
        return customProperties;
    }

    /**
     * Retorna um parâmetro de configuração customizado da aplicação dado o nome da propriedade.
     *
     * @param name nome da propriedade.
     * @return valor da propriedade.
     */
    public Optional<String> getCustomProperty(String name) {
        return Optional.ofNullable(getCustomProperties().getProperty(name));
    }

}
