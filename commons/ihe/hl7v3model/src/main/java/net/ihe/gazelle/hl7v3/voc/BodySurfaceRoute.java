/**
 * BodySurfaceRoute.java
 * <p>
 * File generated from the voc::BodySurfaceRoute uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration BodySurfaceRoute.
 *
 */

@XmlType(name = "BodySurfaceRoute")
@XmlEnum
@XmlRootElement(name = "BodySurfaceRoute")
public enum BodySurfaceRoute {
	@XmlEnumValue("DRESS")
	DRESS("DRESS"),
	@XmlEnumValue("ELECTOSMOS")
	ELECTOSMOS("ELECTOSMOS"),
	@XmlEnumValue("IONTO")
	IONTO("IONTO"),
	@XmlEnumValue("SOAK")
	SOAK("SOAK"),
	@XmlEnumValue("SWAB")
	SWAB("SWAB"),
	@XmlEnumValue("TOPICAL")
	TOPICAL("TOPICAL");
	
	private final String value;

    BodySurfaceRoute(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static BodySurfaceRoute fromValue(String v) {
        for (BodySurfaceRoute c: BodySurfaceRoute.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}