/**
 * IntramuscularInjection.java
 * <p>
 * File generated from the voc::IntramuscularInjection uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration IntramuscularInjection.
 *
 */

@XmlType(name = "IntramuscularInjection")
@XmlEnum
@XmlRootElement(name = "IntramuscularInjection")
public enum IntramuscularInjection {
	@XmlEnumValue("IM")
	IM("IM"),
	@XmlEnumValue("IMD")
	IMD("IMD"),
	@XmlEnumValue("IMZ")
	IMZ("IMZ");
	
	private final String value;

    IntramuscularInjection(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static IntramuscularInjection fromValue(String v) {
        for (IntramuscularInjection c: IntramuscularInjection.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}