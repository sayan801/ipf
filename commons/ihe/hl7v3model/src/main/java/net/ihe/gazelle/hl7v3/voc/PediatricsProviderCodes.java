/**
 * PediatricsProviderCodes.java
 * <p>
 * File generated from the voc::PediatricsProviderCodes uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration PediatricsProviderCodes.
 *
 */

@XmlType(name = "PediatricsProviderCodes")
@XmlEnum
@XmlRootElement(name = "PediatricsProviderCodes")
public enum PediatricsProviderCodes {
	@XmlEnumValue("208000000X")
	_208000000X("208000000X"),
	@XmlEnumValue("2080A0000X")
	_2080A0000X("2080A0000X"),
	@XmlEnumValue("2080I0007X")
	_2080I0007X("2080I0007X"),
	@XmlEnumValue("2080N0001X")
	_2080N0001X("2080N0001X"),
	@XmlEnumValue("2080P0006X")
	_2080P0006X("2080P0006X"),
	@XmlEnumValue("2080P0008X")
	_2080P0008X("2080P0008X"),
	@XmlEnumValue("2080P0201X")
	_2080P0201X("2080P0201X"),
	@XmlEnumValue("2080P0202X")
	_2080P0202X("2080P0202X"),
	@XmlEnumValue("2080P0203X")
	_2080P0203X("2080P0203X"),
	@XmlEnumValue("2080P0204X")
	_2080P0204X("2080P0204X"),
	@XmlEnumValue("2080P0205X")
	_2080P0205X("2080P0205X"),
	@XmlEnumValue("2080P0206X")
	_2080P0206X("2080P0206X"),
	@XmlEnumValue("2080P0207X")
	_2080P0207X("2080P0207X"),
	@XmlEnumValue("2080P0208X")
	_2080P0208X("2080P0208X"),
	@XmlEnumValue("2080P0210X")
	_2080P0210X("2080P0210X"),
	@XmlEnumValue("2080P0214X")
	_2080P0214X("2080P0214X"),
	@XmlEnumValue("2080P0216X")
	_2080P0216X("2080P0216X"),
	@XmlEnumValue("2080S0010X")
	_2080S0010X("2080S0010X"),
	@XmlEnumValue("2080T0002X")
	_2080T0002X("2080T0002X");
	
	private final String value;

    PediatricsProviderCodes(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static PediatricsProviderCodes fromValue(String v) {
        for (PediatricsProviderCodes c: PediatricsProviderCodes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}