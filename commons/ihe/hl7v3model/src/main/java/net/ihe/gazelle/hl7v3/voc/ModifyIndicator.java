/**
 * ModifyIndicator.java
 * <p>
 * File generated from the voc::ModifyIndicator uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration ModifyIndicator.
 *
 */

@XmlType(name = "ModifyIndicator")
@XmlEnum
@XmlRootElement(name = "ModifyIndicator")
public enum ModifyIndicator {
	@XmlEnumValue("M")
	M("M"),
	@XmlEnumValue("N")
	N("N");
	
	private final String value;

    ModifyIndicator(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static ModifyIndicator fromValue(String v) {
        for (ModifyIndicator c: ModifyIndicator.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}