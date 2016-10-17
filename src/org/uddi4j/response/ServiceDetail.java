/*
 * The source code contained herein is licensed under the IBM Public License
 * Version 1.0, which has been approved by the Open Source Initiative.
 * Copyright (C) 2001, International Business Machines Corporation
 * All Rights Reserved.
 *
 */

package org.uddi4j.response;

import java.util.Vector;

import org.uddi4j.UDDIElement;
import org.uddi4j.UDDIException;
import org.uddi4j.datatype.service.BusinessService;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the serviceDetail element within the UDDI version 2.0 schema.
 * This class contains the following types of methods:
 * 
 * <ul>
 *   <li>A constructor that passes the required fields.
 *   <li>A Constructor that will instantiate the object from an appropriate XML
 *       DOM element.
 *   <li>Get/set methods for each attribute that this element can contain.
 *   <li>A get/setVector method is provided for sets of attributes.
 *   <li>A SaveToXML method that serializes this class within a passed in
 *       element.
 * </ul>
 * 
 * Typically, this class is used to construct parameters for, or interpret
 * responses from, methods in the UDDIProxy class.
 *
 * <p><b>Element description:</b>
 * <p>This structure is used to return all businessService details.
 * 
 * @author David Melgar (dmelgar@us.ibm.com)
 * @author Ozzy (ozzy@hursley.ibm.com)
 */
public class ServiceDetail extends UDDIElement {

   public static final String UDDI_TAG = "serviceDetail";

   protected Element base = null;

   String operator = null;
   String truncated = null;
   // Vector of BusinessService objects
   Vector businessService = new Vector();

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */

   public ServiceDetail() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param operator String
    */
   public ServiceDetail(String operator) {
      this.operator = operator;
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

   public ServiceDetail(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
      super(base);
      operator = base.getAttribute("operator");
      truncated = base.getAttribute("truncated");
      NodeList nl = null;
      nl = getChildElementsByTagName(base, BusinessService.UDDI_TAG);
      for (int i=0; i < nl.getLength(); i++) {
         businessService.addElement(new BusinessService((Element)nl.item(i)));
      }
   }

   public void setOperator(String s) {
      operator = s;
   }

   public void setTruncated(String s) {
      truncated = s;
   }
   public void setTruncated(boolean s) {
      if (s) {
         truncated = "true";
      } else {
         truncated = "false";
      }
   }

   /**
    * Set businessService vector
    *
    * @param s  Vector of <I>BusinessService</I> objects.
    */
   public void setBusinessServiceVector(Vector s) {
      businessService = s;
   }

   public String getOperator() {
      return operator;
   }


   public String getTruncated() {
      return truncated;
   }

   public boolean getTruncatedBoolean() {
      return "true".equals(truncated);
   }

   /**
    * Get businessService
    *
    * @return s Vector of <I>BusinessService</I> objects.
    */
   public Vector getBusinessServiceVector() {
      return businessService;
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
      base.setAttribute("generic", UDDIElement.GENERIC);
      if (operator!=null) {
         base.setAttribute("operator", operator);
      }
      if (truncated!=null) {
         base.setAttribute("truncated", truncated);
      }
      if (businessService!=null) {
         for (int i=0; i < businessService.size(); i++) {
            ((BusinessService)(businessService.elementAt(i))).saveToXML(base);
		 }
      }
      parent.appendChild(base);
   }
}
