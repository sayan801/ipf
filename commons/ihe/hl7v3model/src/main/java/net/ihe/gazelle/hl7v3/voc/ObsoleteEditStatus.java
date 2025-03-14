/**
 * ObsoleteEditStatus.java
 * <p>
 * File generated from the voc::ObsoleteEditStatus uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration ObsoleteEditStatus.
 *
 */

@XmlType(name = "ObsoleteEditStatus")
@XmlEnum
@XmlRootElement(name = "ObsoleteEditStatus")
public enum ObsoleteEditStatus {
	@XmlEnumValue("O")
	O("O");
	
	private final String value;

    ObsoleteEditStatus(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static ObsoleteEditStatus fromValue(String v) {
        for (ObsoleteEditStatus c: ObsoleteEditStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}