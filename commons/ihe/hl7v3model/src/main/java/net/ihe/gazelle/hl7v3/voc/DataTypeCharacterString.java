/**
 * DataTypeCharacterString.java
 * <p>
 * File generated from the voc::DataTypeCharacterString uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration DataTypeCharacterString.
 *
 */

@XmlType(name = "DataTypeCharacterString")
@XmlEnum
@XmlRootElement(name = "DataTypeCharacterString")
public enum DataTypeCharacterString {
	@XmlEnumValue("ADXP")
	ADXP("ADXP"),
	@XmlEnumValue("ON")
	ON("ON"),
	@XmlEnumValue("PNXP")
	PNXP("PNXP"),
	@XmlEnumValue("ST")
	ST("ST");
	
	private final String value;

    DataTypeCharacterString(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static DataTypeCharacterString fromValue(String v) {
        for (DataTypeCharacterString c: DataTypeCharacterString.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}