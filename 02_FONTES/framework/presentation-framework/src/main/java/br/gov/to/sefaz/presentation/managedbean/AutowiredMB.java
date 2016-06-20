package br.gov.to.sefaz.presentation.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

/**
 * Managed Bean que injeta as dependencias através do {@link PostConstruct}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/06/2016 17:15:00
 */
public class AutowiredMB {

    @ManagedProperty("#{springBeanFactoryMB}")
    private BeanFactoryMB beanFactoryMB;

    @PostConstruct
    protected void injectDependencies() {
        beanFactoryMB.injectBeans(this);
    }

    protected BeanFactoryMB getBeanFactoryMB() {
        return beanFactoryMB;
    }

    public void setBeanFactoryMB(BeanFactoryMB beanFactoryMB) {
        this.beanFactoryMB = beanFactoryMB;
    }
}
