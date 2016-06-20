package br.gov.to.sefaz.util.certificado;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.x509.extension.X509ExtensionUtil;
import org.springframework.stereotype.Component;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utilitário para obter informações do certificado digital.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 14/06/2016 18:09:00
 */
@Component
public class CertificadoDigitalUtil {

    private static final ASN1ObjectIdentifier OID_PF_DADOS_TITULAR = new ASN1ObjectIdentifier("2.16.76.1.3.1");

    /**
     * Retorna o CPF do usuário do certificado digital através do {@link java.security.cert.X509Certificate}, caso o
     * certificado digital não contenha um CPF o valor retornado será um {@link java.util.Optional#empty()}.
     *
     * @param certChain cadeia de certificado digital
     * @return CPF do certificado digital
     * @throws CertificateParsingException exception ao realizar o parser do certificdo digital
     */
    public Optional<String> getCpf(X509Certificate... certChain) throws CertificateParsingException {
        List<Optional<String>> cpfs = new ArrayList<>();

        for (X509Certificate certificate : certChain) {
            cpfs.addAll(setCpfFromCertificate(certificate));
        }

        return cpfs.stream()
                .filter(Optional::isPresent)
                .findAny()
                .orElse(Optional.empty());
    }

    private List<Optional<String>> setCpfFromCertificate(X509Certificate certificate)
            throws CertificateParsingException {
        Collection<?> alternativeNames = X509ExtensionUtil.getSubjectAlternativeNames(certificate);
        List<Optional<String>> cpfs = new ArrayList<>();
        alternativeNames.stream().filter(alternativeName -> alternativeName instanceof ArrayList)
                .forEach(alternativeName -> {
                    ArrayList<?> listOfValues = (ArrayList<?>) alternativeName;
                    Object value = listOfValues.get(1);
                    cpfs.add(valueIsCpf(value));
                });

        return cpfs.stream().filter(Optional::isPresent).collect(Collectors.toList());
    }

    private Optional<String> valueIsCpf(Object value) {
        if (value instanceof DLSequence) {
            DLSequence derSequence = (DLSequence) value;
            ASN1ObjectIdentifier derObjectIdentifier = (ASN1ObjectIdentifier) derSequence.getObjectAt(0);
            ASN1TaggedObject derTaggedObject = (ASN1TaggedObject) derSequence.getObjectAt(1);
            ASN1Object derObject = derTaggedObject.getObject();

            String valueOfTag = getValueOfTag(derObject);
            if (StringUtils.isNotBlank(valueOfTag)
                    && (derObjectIdentifier.equals(OID_PF_DADOS_TITULAR))) {
                return Optional.of(valueOfTag.substring(8, 19));
            }
        }
        return Optional.empty();
    }

    private String getValueOfTag(ASN1Object derObject) {
        String valueOfTag = StringUtils.EMPTY;
        if (derObject instanceof DEROctetString) {
            DEROctetString octet = (DEROctetString) derObject;
            valueOfTag = new String(octet.getOctets());
        } else if (derObject instanceof DERPrintableString) {
            DERPrintableString octet = (DERPrintableString) derObject;
            valueOfTag = new String(octet.getOctets());
        } else if (derObject instanceof DERUTF8String) {
            DERUTF8String str = (DERUTF8String) derObject;
            valueOfTag = str.getString();
        }
        return valueOfTag;
    }
}
