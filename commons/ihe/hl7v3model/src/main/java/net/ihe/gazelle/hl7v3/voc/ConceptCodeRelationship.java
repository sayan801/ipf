/**
 * ConceptCodeRelationship.java
 * <p>
 * File generated from the voc::ConceptCodeRelationship uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration ConceptCodeRelationship.
 *
 */

@XmlType(name = "ConceptCodeRelationship")
@XmlEnum
@XmlRootElement(name = "ConceptCodeRelationship")
public enum ConceptCodeRelationship {
	@XmlEnumValue("hasPart")
	HASPART("hasPart"),
	@XmlEnumValue("hasSubtype")
	HASSUBTYPE("hasSubtype"),
	@XmlEnumValue("smallerThan")
	SMALLERTHAN("smallerThan");
	
	private final String value;

    ConceptCodeRelationship(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static ConceptCodeRelationship fromValue(String v) {
        for (var c: ConceptCodeRelationship.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}