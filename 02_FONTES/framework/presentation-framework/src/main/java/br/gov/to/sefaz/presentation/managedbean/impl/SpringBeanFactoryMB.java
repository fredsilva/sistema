package br.gov.to.sefaz.presentation.managedbean.impl;

import br.gov.to.sefaz.presentation.managedbean.BeanFactoryMB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Managed Bean responsavel por fornecer ou injetar as depêndencias presentes no contexto do Spring para outros para
 * outros managed beans.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 02/05/2016 10:48:00
 */
@ManagedBean(name = "springBeanFactoryMB")
@ApplicationScoped
@Component
public class SpringBeanFactoryMB implements BeanFactoryMB {

    private final AutowireCapableBeanFactory beanFactory;

    @Autowired
    public SpringBeanFactoryMB(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public <T> T getBean(Class<T> requiredType) {
        return beanFactory.getBean(requiredType);
    }


    /**
     * {@inheritDoc}.
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return beanFactory.getBean(name, requiredType);
    }

    /**
     * {@inheritDoc}
     * Para esta implementação as dependencias do object devem esta anotadas com @{@link Autowired}.
     */
    @Override
    public void injectBeans(Object object) {
        beanFactory.autowireBean(object);
    }
}
