/**
 * SetUpdateMode.java
 * <p>
 * File generated from the voc::SetUpdateMode uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration SetUpdateMode.
 *
 */

@XmlType(name = "SetUpdateMode")
@XmlEnum
@XmlRootElement(name = "SetUpdateMode")
public enum SetUpdateMode {
	@XmlEnumValue("ESA")
	ESA("ESA"),
	@XmlEnumValue("ESAC")
	ESAC("ESAC"),
	@XmlEnumValue("ESC")
	ESC("ESC"),
	@XmlEnumValue("ESD")
	ESD("ESD");
	
	private final String value;

    SetUpdateMode(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static SetUpdateMode fromValue(String v) {
        for (SetUpdateMode c: SetUpdateMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}