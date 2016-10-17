/*
 * The source code contained herein is licensed under the IBM Public License
 * Version 1.0, which has been approved by the Open Source Initiative.
 * Copyright (C) 2001, International Business Machines Corporation
 * All Rights Reserved.
 *
 */

package org.uddi4j.datatype.business;

import org.uddi4j.UDDIElement;
import org.uddi4j.UDDIException;
import org.w3c.dom.Element;

/**
 * Represents the addressLine element within the UDDI version 2.0 schema.
 * This class contains the following types of methods:
 * 
 * <ul>
 *   <li>Constructor passing required fields.
 *   <li>Constructor that will instantiate the object from an appropriate XML
 *       DOM element.
 *   <li>Get/set methods for each attribute that this element can contain.
 *   <li>A get/setVector method is provided for sets of attributes.
 *   <li>SaveToXML method. Serializes this class within a passed in element.
 * </ul>
 * 
 * Typically, this class is used to construct parameters for, or interpret
 * responses from methods in the UDDIProxy class.
 *
 * @author David Melgar (dmelgar@us.ibm.com)
 * @author Vivek Chopra (vivek@soaprpc.com)
 */
public class AddressLine extends UDDIElement {

   public static final String UDDI_TAG = "addressLine";

   protected Element base = null;

   String text = null;
   String keyName = null;
   String keyValue = null;

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public AddressLine() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param value  String value
    */
   public AddressLine(String value) {
      setText(value);
   }

   /**
    * Construct the object from a DOM tree. Used by
    * UDDIProxy to construct an object from a received UDDI
    * message.
    *
    * @param base   Element with the name appropriate for this class.
    *
    * @exception UDDIException Thrown if DOM tree contains a SOAP fault
    *  or a disposition report indicating a UDDI error.
    */

   public AddressLine(Element base) throws UDDIException {
      // Check if its a fault. Throws an exception if it is.
      super(base);
      keyName = getAttr(base,"keyName");
      keyValue = getAttr(base,"keyValue");
      text = getText(base);
   }

   private String getAttr(Element base, String attrname)
   {
     if(base.getAttributeNode(attrname)!=null && base.getAttributeNode(attrname).getSpecified() )  
     {
       return base.getAttribute(attrname);
     }
     return null;
   }


   /**
    * Set the (optional) keyName attribute
    */
   public void setKeyName (String keyName) {
      this.keyName = keyName;
   }

   /**
    * Set the (optional) keyValue attribute
    */
   public void setKeyValue (String keyValue) {
      this.keyValue = keyValue;
   }

   public void setText(String s) {
      text = s;
   }

   public String getKeyName () {
      return keyName;
   }

   public String getKeyValue () {
      return keyValue;
   }

   public String getText() {
      return text;
   }

   /**
    * Save an object to the DOM tree. Used to serialize an object
    * to a DOM tree, usually to send a UDDI message.
    *
    * <BR>Used by UDDIProxy.
    *
    * @param parent Object will serialize as a child element under the
    *  passed in parent element.
    */
   public void saveToXML(Element parent) {
      base = parent.getOwnerDocument().createElementNS(UDDIElement.XMLNS, UDDIElement.XMLNS_PREFIX + UDDI_TAG);
      // Save attributes
     
      if (keyName != null) {
         base.setAttribute("keyName", keyName);
      }

      if (keyValue != null) {
         base.setAttribute("keyValue", keyValue);
      }

      if (text!=null) {
         base.appendChild(parent.getOwnerDocument().createTextNode(text));
      }
      parent.appendChild(base);
   }
}
