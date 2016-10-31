package br.gov.to.sefaz.util.pdf.handler;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Classe para armazenamento e operações basicas de um variaveis de contexto para a iteração de busca realizada pela
 * classe {@link TextExpressionHandler}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/08/2016 14:30:00
 */
public class LogicalExpressionFinderContext {

    private int opensCount;
    private int closesCount;
    private String expressionName;
    private String expressionParams;
    private int offset;
    private int outerStartIdx;
    private int outerEndIdx;
    private int innerStartIdx;
    private int innerEndIdx;

    public LogicalExpressionFinderContext() {
        this.opensCount = 0;
        this.closesCount = 0;
        this.expressionName = EMPTY;
        this.expressionParams = EMPTY;
        this.offset = 0;
        this.outerStartIdx = 0;
        this.outerEndIdx = 0;
        this.innerStartIdx = 0;
        this.innerEndIdx = 0;
    }

    /**
     * Seta as variaveis para o estado de inicio de um find, Aplicando os offsets onde precisa.
     *
     * @param replaceLength tamanho do texto que substituira o original, afeta diretamente o tamanho do offset
     */
    public void setToNextFind(int replaceLength) {
        opensCount = 0;
        closesCount = 0;

        expressionName = EMPTY;
        expressionParams = EMPTY;

        offset += replaceLength - (outerEndIdx - outerStartIdx);
        outerStartIdx = offset;
        outerEndIdx = offset;
        innerStartIdx = offset;
        innerEndIdx = offset;
    }

    public String getExpressionName() {
        return expressionName;
    }

    public void setExpressionName(String expressionName) {
        this.expressionName = expressionName;
    }

    public String getExpressionParams() {
        return expressionParams;
    }

    public void setExpressionParams(String expressionParams) {
        this.expressionParams = expressionParams;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOuterStartIdx() {
        return outerStartIdx;
    }

    public int getOuterEndIdx() {
        return outerEndIdx;
    }

    public int getInnerStartIdx() {
        return innerStartIdx;
    }

    public int getInnerEndIdx() {
        return innerEndIdx;
    }

    /**
     * Incrementa 1 a quantidade de aberturas encontrados.
     */
    public void increaseOpensCount() {
        opensCount++;
    }

    /**
     * Incrementa 1 a quantidade de fechamentos encontrados.
     */
    public void increaseClosesCount() {
        closesCount++;
    }

    public boolean isFirstOpen() {
        return opensCount == 1;
    }

    /**
     * <p>Soma a nova posição de inicio da tag de abertura. Referente a expressão '{@link #expressionName}'.</p>
     *
     * {@code
     * Ex: OuterStartIdx->#{expression:param}${expressionBody}#{:expression}
     * }
     *
     * @param newStart novo inicio, que será incrementado ao '{@link #offset}' antes de ser setado.
     */
    public void sumOuterStartIdx(int newStart) {
        outerStartIdx += newStart;
    }

    /**
     * <p>Soma a nova posição de fim da tag de abertura. Referente a expressão '{@link #expressionName}'.</p>
     *
     * {@code
     * Ex: #{expression:param}<-InnerStartIdx ${expressionBody} #{:expression}
     * }
     *
     * @param newEnd novo fim, que será incrementado ao '{@link #offset}' antes de ser setado.
     */
    public void sumInnerStartIdx(int newEnd) {
        innerStartIdx += newEnd;
    }

    /**
     * <p>Soma a nova posição de inicio da tag de fechamento. Referente a expressão '{@link #expressionName}'.</p>
     *
     * {@code
     * Ex: #{expression:param}${expressionBody} InnerEndIdx->#{:expression}
     * }
     *
     * @param newStart novo inicio, que será incrementado ao '{@link #offset}' antes de ser setado.
     */
    public void sumInnerEndIdx(int newStart) {
        innerEndIdx += newStart;
    }

    /**
     * <p>Soma a nova posição de fim da tag de fechamento. Referente a expressão '{@link #expressionName}'.</p>
     *
     * {@code
     * Ex: #{expression:param} ${expressionBody} #{:expression}<-OuterEndIdx
     * }
     *
     * @param newEnd novo fim, que será incrementado ao '{@link #offset}' antes de ser setado.
     */
    public void sumOuterEndIdx(int newEnd) {
        outerEndIdx += newEnd;
    }

    public boolean isWantedClosure() {
        return opensCount == closesCount;
    }

    /**
     * Verifica se o nome passado é o mesmo que {@link #expressionName}.
     *
     * @param name nome de expressão achada
     * @return se o nome é o mesmo de {@link #expressionName}
     */
    public boolean isExpressionName(String name) {
        return expressionName.equals(name);
    }

    public int getOrigOuterStartIdx() {
        return outerStartIdx - getOffset();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogicalExpressionFinderContext that = (LogicalExpressionFinderContext) o;
        return opensCount == that.opensCount
                && closesCount == that.closesCount
                && offset == that.offset
                && outerStartIdx == that.outerStartIdx
                && outerEndIdx == that.outerEndIdx
                && innerStartIdx == that.innerStartIdx
                && innerEndIdx == that.innerEndIdx
                && Objects.equals(expressionName, that.expressionName)
                && Objects.equals(expressionParams, that.expressionParams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opensCount, closesCount, expressionName, expressionParams, offset, outerStartIdx,
                outerEndIdx, innerStartIdx, innerEndIdx);
    }

    @Override
    public String toString() {
        return "LogicalExpressinFinderBean{"
                + "opensCount=" + opensCount
                + ", closesCount=" + closesCount
                + ", expressionName='" + expressionName + '\''
                + ", expressionParams='" + expressionParams + '\''
                + ", offset=" + offset
                + ", outerStartIdx=" + outerStartIdx
                + ", outerEndIdx=" + outerEndIdx
                + ", innerStartIdx=" + innerStartIdx
                + ", innerEndIdx=" + innerEndIdx
                + '}';
    }
}
