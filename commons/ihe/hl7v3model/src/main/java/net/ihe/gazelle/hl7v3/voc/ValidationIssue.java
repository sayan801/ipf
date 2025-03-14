/**
 * ValidationIssue.java
 * <p>
 * File generated from the voc::ValidationIssue uml Enumeration
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.voc;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
/**
 * Description of the enumeration ValidationIssue.
 *
 */

@XmlType(name = "ValidationIssue")
@XmlEnum
@XmlRootElement(name = "ValidationIssue")
public enum ValidationIssue {
	@XmlEnumValue("ABUSE")
	ABUSE("ABUSE"),
	@XmlEnumValue("AGE")
	AGE("AGE"),
	@XmlEnumValue("BUS")
	BUS("BUS"),
	@XmlEnumValue("CODE_DEPREC")
	CODEDEPREC("CODE_DEPREC"),
	@XmlEnumValue("CODE_INVAL")
	CODEINVAL("CODE_INVAL"),
	@XmlEnumValue("COMPLY")
	COMPLY("COMPLY"),
	@XmlEnumValue("COND")
	COND("COND"),
	@XmlEnumValue("CREACT")
	CREACT("CREACT"),
	@XmlEnumValue("DOSE")
	DOSE("DOSE"),
	@XmlEnumValue("DOSECOND")
	DOSECOND("DOSECOND"),
	@XmlEnumValue("DOSEDUR")
	DOSEDUR("DOSEDUR"),
	@XmlEnumValue("DOSEH")
	DOSEH("DOSEH"),
	@XmlEnumValue("DOSEIVL")
	DOSEIVL("DOSEIVL"),
	@XmlEnumValue("DOSEL")
	DOSEL("DOSEL"),
	@XmlEnumValue("DUPTHPCLS")
	DUPTHPCLS("DUPTHPCLS"),
	@XmlEnumValue("DUPTHPGEN")
	DUPTHPGEN("DUPTHPGEN"),
	@XmlEnumValue("DUPTHPY")
	DUPTHPY("DUPTHPY"),
	@XmlEnumValue("FORMAT")
	FORMAT("FORMAT"),
	@XmlEnumValue("FRAUD")
	FRAUD("FRAUD"),
	@XmlEnumValue("GEN")
	GEN("GEN"),
	@XmlEnumValue("GEND")
	GEND("GEND"),
	@XmlEnumValue("ILLEGAL")
	ILLEGAL("ILLEGAL"),
	@XmlEnumValue("KEY204")
	KEY204("KEY204"),
	@XmlEnumValue("KEY205")
	KEY205("KEY205"),
	@XmlEnumValue("LAB")
	LAB("LAB"),
	@XmlEnumValue("LEN_LONG")
	LENLONG("LEN_LONG"),
	@XmlEnumValue("LEN_RANGE")
	LENRANGE("LEN_RANGE"),
	@XmlEnumValue("LEN_SHORT")
	LENSHORT("LEN_SHORT"),
	@XmlEnumValue("MAXOCCURS")
	MAXOCCURS("MAXOCCURS"),
	@XmlEnumValue("MDOSE")
	MDOSE("MDOSE"),
	@XmlEnumValue("MINOCCURS")
	MINOCCURS("MINOCCURS"),
	@XmlEnumValue("MISSCOND")
	MISSCOND("MISSCOND"),
	@XmlEnumValue("MISSMAND")
	MISSMAND("MISSMAND"),
	@XmlEnumValue("NODUPS")
	NODUPS("NODUPS"),
	@XmlEnumValue("OBSA")
	OBSA("OBSA"),
	@XmlEnumValue("PLYDOC")
	PLYDOC("PLYDOC"),
	@XmlEnumValue("PLYPHRM")
	PLYPHRM("PLYPHRM"),
	@XmlEnumValue("REACT")
	REACT("REACT"),
	@XmlEnumValue("REP_RANGE")
	REPRANGE("REP_RANGE"),
	@XmlEnumValue("RREACT")
	RREACT("RREACT"),
	@XmlEnumValue("VALIDAT")
	VALIDAT("VALIDAT");
	
	private final String value;

    ValidationIssue(String v) {
        value = v;
    }
    
     public String value() {
        return value;
    }

    public static ValidationIssue fromValue(String v) {
        for (ValidationIssue c: ValidationIssue.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
	
}