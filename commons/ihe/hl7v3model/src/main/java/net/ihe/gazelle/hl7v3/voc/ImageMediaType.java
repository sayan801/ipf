/**
 * ImageMediaType.java
 * <p>
 * File generated from the voc::ImageMediaType uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration ImageMediaType.
 *
 */

@XmlType(name = "ImageMediaType")
@XmlEnum
@XmlRootElement(name = "ImageMediaType")
public enum ImageMediaType {
	@XmlEnumValue("image/g3fax")
	IMAGEG3FAX("image/g3fax"),
	@XmlEnumValue("image/gif")
	IMAGEGIF("image/gif"),
	@XmlEnumValue("image/jpeg")
	IMAGEJPEG("image/jpeg"),
	@XmlEnumValue("image/png")
	IMAGEPNG("image/png"),
	@XmlEnumValue("image/tiff")
	IMAGETIFF("image/tiff");
	
	private final String value;

    ImageMediaType(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static ImageMediaType fromValue(String v) {
        for (ImageMediaType c: ImageMediaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}