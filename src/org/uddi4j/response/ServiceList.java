/*
 * The source code contained herein is licensed under the IBM Public License
 * Version 1.0, which has been approved by the Open Source Initiative.
 * Copyright (C) 2001, International Business Machines Corporation
 * All Rights Reserved.
 *
 */

package org.uddi4j.response;

import org.uddi4j.UDDIElement;
import org.uddi4j.UDDIException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the serviceList element within the UDDI version 2.0 schema.
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
 * <p>This message is used to return results of a find_service request.
 * 
 * @author David Melgar (dmelgar@us.ibm.com)
 * @author Ozzy (ozzy@hursley.ibm.com)
 */
public class ServiceList extends UDDIElement {

   public static final String UDDI_TAG = "serviceList";

   protected Element base = null;

   String operator = null;
   String truncated = null;
   ServiceInfos serviceInfos = null;

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */

   public ServiceList() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param operator String
    * @param ServiceInfos ServiceInfos object
    */
   public ServiceList(String operator,
      ServiceInfos serviceInfos) {
      this.operator = operator;
      this.serviceInfos = serviceInfos;
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

   public ServiceList(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
      super(base);
      operator = base.getAttribute("operator");
      truncated = base.getAttribute("truncated");
      NodeList nl = null;
      nl = getChildElementsByTagName(base, ServiceInfos.UDDI_TAG);
      if (nl.getLength() > 0) {
         serviceInfos = new ServiceInfos((Element)nl.item(0));
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

   public void setServiceInfos(ServiceInfos s) {
      serviceInfos = s;
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

   public ServiceInfos getServiceInfos() {
      return serviceInfos;
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
      if (serviceInfos!=null) {
         serviceInfos.saveToXML(base);
      }
      parent.appendChild(base);
   }
}
