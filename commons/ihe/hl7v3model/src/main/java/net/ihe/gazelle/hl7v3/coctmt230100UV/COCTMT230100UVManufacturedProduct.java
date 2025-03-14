/**
 * COCTMT230100UVManufacturedProduct.java
 * <p>
 * File generated from the coctmt230100UV::COCTMT230100UVManufacturedProduct uml Class
 * Generated by IHE - europe, gazelle team
 */
package net.ihe.gazelle.hl7v3.coctmt230100UV;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.ihe.gazelle.gen.common.ConstraintValidatorModule;

import org.w3c.dom.Document;
import org.w3c.dom.Node;


/**
 * Description of the class COCTMT230100UVManufacturedProduct.
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COCT_MT230100UV.ManufacturedProduct", propOrder = {
	"realmCode",
	"typeId",
	"templateId",
	"manufacturer",
	"subjectOf1",
	"subjectOf2",
	"subjectOf3",
	"classCode",
	"nullFlavor"
})
@XmlRootElement(name = "COCT_MT230100UV.ManufacturedProduct")
public class COCTMT230100UVManufacturedProduct implements java.io.Serializable {
	
	/**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;

	
	@XmlElement(name = "realmCode", namespace = "urn:hl7-org:v3")
	public List<net.ihe.gazelle.hl7v3.datatypes.CS> realmCode;
	@XmlElement(name = "typeId", namespace = "urn:hl7-org:v3")
	public net.ihe.gazelle.hl7v3.datatypes.II typeId;
	@XmlElement(name = "templateId", namespace = "urn:hl7-org:v3")
	public List<net.ihe.gazelle.hl7v3.datatypes.II> templateId;
	@XmlElement(name = "manufacturer", required = true, namespace = "urn:hl7-org:v3")
	public net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVManufacturer manufacturer;
	@XmlElement(name = "subjectOf1", namespace = "urn:hl7-org:v3")
	public List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject25> subjectOf1;
	@XmlElement(name = "subjectOf2", namespace = "urn:hl7-org:v3")
	public List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject15> subjectOf2;
	@XmlElement(name = "subjectOf3", namespace = "urn:hl7-org:v3")
	public List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject16> subjectOf3;
	@XmlAttribute(name = "classCode", required = true)
	public net.ihe.gazelle.hl7v3.voc.RoleClassManufacturedProduct classCode;
	@XmlAttribute(name = "nullFlavor")
	public net.ihe.gazelle.hl7v3.voc.NullFlavor nullFlavor;
	
	/**
	 * An attribute containing marshalled element node
	 */
	@XmlTransient
	private org.w3c.dom.Node _xmlNodePresentation;
	
	
	/**
	 * Return realmCode.
	 * @return realmCode
	 */
	public List<net.ihe.gazelle.hl7v3.datatypes.CS> getRealmCode() {
		if (realmCode == null) {
	        realmCode = new ArrayList<>();
	    }
	    return realmCode;
	}
	
	/**
	 * Set a value to attribute realmCode.
     */
	public void setRealmCode(List<net.ihe.gazelle.hl7v3.datatypes.CS> realmCode) {
	    this.realmCode = realmCode;
	}
	
	
	
	/**
	 * Add a realmCode to the realmCode collection.
	 * @param realmCode_elt Element to add.
	 */
	public void addRealmCode(net.ihe.gazelle.hl7v3.datatypes.CS realmCode_elt) {
	    this.realmCode.add(realmCode_elt);
	}
	
	/**
	 * Remove a realmCode to the realmCode collection.
	 * @param realmCode_elt Element to remove
	 */
	public void removeRealmCode(net.ihe.gazelle.hl7v3.datatypes.CS realmCode_elt) {
	    this.realmCode.remove(realmCode_elt);
	}
	
	/**
	 * Return typeId.
	 * @return typeId
	 */
	public net.ihe.gazelle.hl7v3.datatypes.II getTypeId() {
	    return typeId;
	}
	
	/**
	 * Set a value to attribute typeId.
     */
	public void setTypeId(net.ihe.gazelle.hl7v3.datatypes.II typeId) {
	    this.typeId = typeId;
	}
	
	
	
	
	/**
	 * Return templateId.
	 * @return templateId
	 */
	public List<net.ihe.gazelle.hl7v3.datatypes.II> getTemplateId() {
		if (templateId == null) {
	        templateId = new ArrayList<>();
	    }
	    return templateId;
	}
	
	/**
	 * Set a value to attribute templateId.
     */
	public void setTemplateId(List<net.ihe.gazelle.hl7v3.datatypes.II> templateId) {
	    this.templateId = templateId;
	}
	
	
	
	/**
	 * Add a templateId to the templateId collection.
	 * @param templateId_elt Element to add.
	 */
	public void addTemplateId(net.ihe.gazelle.hl7v3.datatypes.II templateId_elt) {
	    this.templateId.add(templateId_elt);
	}
	
	/**
	 * Remove a templateId to the templateId collection.
	 * @param templateId_elt Element to remove
	 */
	public void removeTemplateId(net.ihe.gazelle.hl7v3.datatypes.II templateId_elt) {
	    this.templateId.remove(templateId_elt);
	}
	
	/**
	 * Return manufacturer.
	 * @return manufacturer
	 */
	public net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVManufacturer getManufacturer() {
	    return manufacturer;
	}
	
	/**
	 * Set a value to attribute manufacturer.
     */
	public void setManufacturer(net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVManufacturer manufacturer) {
	    this.manufacturer = manufacturer;
	}
	
	
	
	
	/**
	 * Return subjectOf1.
	 * @return subjectOf1
	 */
	public List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject25> getSubjectOf1() {
		if (subjectOf1 == null) {
	        subjectOf1 = new ArrayList<>();
	    }
	    return subjectOf1;
	}
	
	/**
	 * Set a value to attribute subjectOf1.
     */
	public void setSubjectOf1(List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject25> subjectOf1) {
	    this.subjectOf1 = subjectOf1;
	}
	
	
	
	/**
	 * Add a subjectOf1 to the subjectOf1 collection.
	 * @param subjectOf1_elt Element to add.
	 */
	public void addSubjectOf1(net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject25 subjectOf1_elt) {
	    this.subjectOf1.add(subjectOf1_elt);
	}
	
	/**
	 * Remove a subjectOf1 to the subjectOf1 collection.
	 * @param subjectOf1_elt Element to remove
	 */
	public void removeSubjectOf1(net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject25 subjectOf1_elt) {
	    this.subjectOf1.remove(subjectOf1_elt);
	}
	
	/**
	 * Return subjectOf2.
	 * @return subjectOf2
	 */
	public List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject15> getSubjectOf2() {
		if (subjectOf2 == null) {
	        subjectOf2 = new ArrayList<>();
	    }
	    return subjectOf2;
	}
	
	/**
	 * Set a value to attribute subjectOf2.
     */
	public void setSubjectOf2(List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject15> subjectOf2) {
	    this.subjectOf2 = subjectOf2;
	}
	
	
	
	/**
	 * Add a subjectOf2 to the subjectOf2 collection.
	 * @param subjectOf2_elt Element to add.
	 */
	public void addSubjectOf2(net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject15 subjectOf2_elt) {
	    this.subjectOf2.add(subjectOf2_elt);
	}
	
	/**
	 * Remove a subjectOf2 to the subjectOf2 collection.
	 * @param subjectOf2_elt Element to remove
	 */
	public void removeSubjectOf2(net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject15 subjectOf2_elt) {
	    this.subjectOf2.remove(subjectOf2_elt);
	}
	
	/**
	 * Return subjectOf3.
	 * @return subjectOf3
	 */
	public List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject16> getSubjectOf3() {
		if (subjectOf3 == null) {
	        subjectOf3 = new ArrayList<>();
	    }
	    return subjectOf3;
	}
	
	/**
	 * Set a value to attribute subjectOf3.
     */
	public void setSubjectOf3(List<net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject16> subjectOf3) {
	    this.subjectOf3 = subjectOf3;
	}
	
	
	
	/**
	 * Add a subjectOf3 to the subjectOf3 collection.
	 * @param subjectOf3_elt Element to add.
	 */
	public void addSubjectOf3(net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject16 subjectOf3_elt) {
	    this.subjectOf3.add(subjectOf3_elt);
	}
	
	/**
	 * Remove a subjectOf3 to the subjectOf3 collection.
	 * @param subjectOf3_elt Element to remove
	 */
	public void removeSubjectOf3(net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject16 subjectOf3_elt) {
	    this.subjectOf3.remove(subjectOf3_elt);
	}
	
	/**
	 * Return classCode.
	 * @return classCode
	 */
	public net.ihe.gazelle.hl7v3.voc.RoleClassManufacturedProduct getClassCode() {
	    return classCode;
	}
	
	/**
	 * Set a value to attribute classCode.
     */
	public void setClassCode(net.ihe.gazelle.hl7v3.voc.RoleClassManufacturedProduct classCode) {
	    this.classCode = classCode;
	}
	
	
	
	
	/**
	 * Return nullFlavor.
	 * @return nullFlavor
	 */
	public net.ihe.gazelle.hl7v3.voc.NullFlavor getNullFlavor() {
	    return nullFlavor;
	}
	
	/**
	 * Set a value to attribute nullFlavor.
     */
	public void setNullFlavor(net.ihe.gazelle.hl7v3.voc.NullFlavor nullFlavor) {
	    this.nullFlavor = nullFlavor;
	}
	
	
	
	
	
	public Node get_xmlNodePresentation() {
		if (_xmlNodePresentation == null){
				JAXBContext jc;
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				DocumentBuilder db = null;
				Document doc = null;
				try {
					db = dbf.newDocumentBuilder();
					doc = db.newDocument();
				} catch (ParserConfigurationException ignored) {}
				try {
					jc = JAXBContext.newInstance("net.ihe.gazelle.hl7v3.coctmt230100UV");
					Marshaller m = jc.createMarshaller();
					m.marshal(this, doc);
					_xmlNodePresentation = doc.getElementsByTagNameNS("urn:hl7-org:v3", "COCT_MT230100UV.ManufacturedProduct").item(0);
				} catch (JAXBException e) {
					try{
						db = dbf.newDocumentBuilder();
						_xmlNodePresentation = db.newDocument();
					}
					catch(Exception ignored){}
				}
			}
			return _xmlNodePresentation;
	}
	
	public void set_xmlNodePresentation(Node _xmlNodePresentation) {
		this._xmlNodePresentation = _xmlNodePresentation;
	}
	
	
	

	
	/**
     * validate by a module of validation
     * 
     */
   public static void validateByModule(COCTMT230100UVManufacturedProduct cOCTMT230100UVManufacturedProduct, String _location, ConstraintValidatorModule cvm, List<net.ihe.gazelle.validation.Notification> diagnostic){
   		if (cOCTMT230100UVManufacturedProduct != null){
   			cvm.validate(cOCTMT230100UVManufacturedProduct, _location, diagnostic);
			{
				int i = 0;
				for (net.ihe.gazelle.hl7v3.datatypes.CS realmCode: cOCTMT230100UVManufacturedProduct.getRealmCode()){
					net.ihe.gazelle.hl7v3.datatypes.CS.validateByModule(realmCode, _location + "/realmCode[" + i + "]", cvm, diagnostic);
					i++;
				}
			}
			
			net.ihe.gazelle.hl7v3.datatypes.II.validateByModule(cOCTMT230100UVManufacturedProduct.getTypeId(), _location + "/typeId", cvm, diagnostic);
			{
				int i = 0;
				for (net.ihe.gazelle.hl7v3.datatypes.II templateId: cOCTMT230100UVManufacturedProduct.getTemplateId()){
					net.ihe.gazelle.hl7v3.datatypes.II.validateByModule(templateId, _location + "/templateId[" + i + "]", cvm, diagnostic);
					i++;
				}
			}
			
			net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVManufacturer.validateByModule(cOCTMT230100UVManufacturedProduct.getManufacturer(), _location + "/manufacturer", cvm, diagnostic);
			{
				int i = 0;
				for (net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject25 subjectOf1: cOCTMT230100UVManufacturedProduct.getSubjectOf1()){
					net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject25.validateByModule(subjectOf1, _location + "/subjectOf1[" + i + "]", cvm, diagnostic);
					i++;
				}
			}
			
			{
				int i = 0;
				for (net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject15 subjectOf2: cOCTMT230100UVManufacturedProduct.getSubjectOf2()){
					net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject15.validateByModule(subjectOf2, _location + "/subjectOf2[" + i + "]", cvm, diagnostic);
					i++;
				}
			}
			
			{
				int i = 0;
				for (net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject16 subjectOf3: cOCTMT230100UVManufacturedProduct.getSubjectOf3()){
					net.ihe.gazelle.hl7v3.coctmt230100UV.COCTMT230100UVSubject16.validateByModule(subjectOf3, _location + "/subjectOf3[" + i + "]", cvm, diagnostic);
					i++;
				}
			}
			
    	}
    }

}