//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.02.05 at 07:42:35 PM GMT 
//


package inc.kraken.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personInfo" type="{gs_ws.kraken.inc}personInfo" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "personInfo"
})
@XmlRootElement(name = "getAllPersonsResponse")
public class GetAllPersonsResponse {

    @XmlElement(required = true)
    protected List<PersonInfo> personInfo;

    /**
     * Default no-arg constructor
     * 
     */
    public GetAllPersonsResponse() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public GetAllPersonsResponse(final List<PersonInfo> personInfo) {
        this.personInfo = personInfo;
    }

    /**
     * Gets the value of the personInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonInfo }
     * 
     * 
     */
    public List<PersonInfo> getPersonInfo() {
        if (personInfo == null) {
            personInfo = new ArrayList<PersonInfo>();
        }
        return this.personInfo;
    }

}
