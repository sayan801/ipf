/**
 * AddressUse.java
 * <p>
 * File generated from the voc::AddressUse uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration AddressUse.
 *
 */

@XmlType(name = "AddressUse")
@XmlEnum
@XmlRootElement(name = "AddressUse")
public enum AddressUse {
	@XmlEnumValue("BAD")
	BAD("BAD"),
	@XmlEnumValue("DIR")
	DIR("DIR"),
	@XmlEnumValue("H")
	H("H"),
	@XmlEnumValue("HP")
	HP("HP"),
	@XmlEnumValue("HV")
	HV("HV"),
	@XmlEnumValue("PUB")
	PUB("PUB"),
	@XmlEnumValue("TMP")
	TMP("TMP"),
	@XmlEnumValue("WP")
	WP("WP");
	
	private final String value;

    AddressUse(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static AddressUse fromValue(String v) {
        for (AddressUse c: AddressUse.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}