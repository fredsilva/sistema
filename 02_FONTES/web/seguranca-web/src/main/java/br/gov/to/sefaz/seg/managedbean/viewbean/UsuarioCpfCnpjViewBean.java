package br.gov.to.sefaz.seg.managedbean.viewbean;

import java.util.Objects;

/**
 * View bean para identificação de um CNPJ ou CPF baseado no usuario.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/08/2016 18:05:00
 */
public class UsuarioCpfCnpjViewBean {

    private final String id;
    private final String label;
    private final boolean cpf;

    public UsuarioCpfCnpjViewBean(String id, String label, boolean cpf) {
        this.id = id;
        this.label = label;
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioCpfCnpjViewBean that = (UsuarioCpfCnpjViewBean) o;
        return cpf == that.cpf
                && Objects.equals(id, that.id)
                && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, cpf);
    }

    @Override
    public String toString() {
        return "UsuarioCpfCnpjViewBean{"
                + "id='" + id + '\''
                + ", label='" + label + '\''
                + ", cpf=" + cpf
                + '}';
    }
}
