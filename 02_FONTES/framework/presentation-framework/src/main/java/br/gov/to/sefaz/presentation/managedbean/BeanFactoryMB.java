package br.gov.to.sefaz.presentation.managedbean;

/**
 * Managed Bean responsavel por fornecer ou injetar as depêndencias de outros managed beans.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 02/05/2016 10:54:00
 */
public interface BeanFactoryMB {

    /**
     * <p>Cria uma instancia de um bean do spring baseado no tipo do bean.</p>
     *
     * <p>Exemplo:</p>
     * <pre>
     * {@code MeuBean mb = beanFactoryMB.getBean(MeuBean.class);}
     * </pre>
     *
     * @param requiredType *.class do tipo do bean que sera criado.
     * @param <T> tipo do bean que sera criado.
     * @return instancia do bean do tipo que foi passado por parametro.
     */
    <T> T getBean(Class<T> requiredType);

    /**
     * <p>Cria uma instancia de um bean do spring baseado no tipo do bean.</p>
     *
     * <p>Exemplo:</p>
     * <pre>
     * {@code MeuBeanInterface mb = beanFactoryMB.getBean("meuBeanDefault", MeuBeanInterface.class);}
     * </pre>
     *
     * @param name nome do bean que será injetado, utilizado para identficar qual das implementações do
     *      do tipo informado será instanciada.
     * @param requiredType *.class do tipo do bean que sera criado
     * @param <T> tipo do bean que sera criado, pode ser uma interface, super-classe ou implementação.
     * @return instancia do bean do tipo que foi passado por parametro.
     */
    <T> T getBean(String name, Class<T> requiredType);

    /**
     * <p>Injeta todas as depêndencias devidamente anotadas, de acordo com o framework de injeção de dependencia
     * utilizado, no objeto passado por parametro.</p>
     *
     * <p>Exemplo</p>
     * <pre>
     * {@code
     *  MeuManagedBean mmb = new MeuManagedBean();
     *  beanFactoryMB.getBean(mmb);
     *
     *  MinhaDependencia1 md = mmb.getMinhaDependencia1();
     *  MinhaOutraDependencia md = mmb.getMinhaOutraDependencia();
     * }
     * </pre>
     *
     * @param object objeto que terá todas as suas dependências injetadas após a execução do método.
     */
    void injectBeans(Object object);
}
