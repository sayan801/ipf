/**
 * OropharyngealRoute.java
 * <p>
 * File generated from the voc::OropharyngealRoute uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration OropharyngealRoute.
 *
 */

@XmlType(name = "OropharyngealRoute")
@XmlEnum
@XmlRootElement(name = "OropharyngealRoute")
public enum OropharyngealRoute {
	@XmlEnumValue("OROPHARTA")
	OROPHARTA("OROPHARTA");
	
	private final String value;

    OropharyngealRoute(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static OropharyngealRoute fromValue(String v) {
        for (OropharyngealRoute c: OropharyngealRoute.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}