/**
 * WorkPlaceAddressUse.java
 * <p>
 * File generated from the voc::WorkPlaceAddressUse uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration WorkPlaceAddressUse.
 *
 */

@XmlType(name = "WorkPlaceAddressUse")
@XmlEnum
@XmlRootElement(name = "WorkPlaceAddressUse")
public enum WorkPlaceAddressUse {
	@XmlEnumValue("DIR")
	DIR("DIR"),
	@XmlEnumValue("PUB")
	PUB("PUB"),
	@XmlEnumValue("WP")
	WP("WP");
	
	private final String value;

    WorkPlaceAddressUse(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static WorkPlaceAddressUse fromValue(String v) {
        for (WorkPlaceAddressUse c: WorkPlaceAddressUse.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}