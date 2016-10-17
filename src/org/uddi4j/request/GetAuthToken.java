/*
 * The source code contained herein is licensed under the IBM Public License
 * Version 1.0, which has been approved by the Open Source Initiative.
 * Copyright (C) 2001, International Business Machines Corporation
 * All Rights Reserved.
 *
 */

package org.uddi4j.request;

import org.uddi4j.UDDIElement;
import org.uddi4j.UDDIException;
import org.w3c.dom.Element;

/**
 * Represents the get_authToken element within the UDDI version 2.0 schema.
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
 * <p>This optional message is used to request an authentication token.  The
 * response is an authToken message.
 *
 * @author David Melgar (dmelgar@us.ibm.com)
 */
public class GetAuthToken extends UDDIElement {

   public static final String UDDI_TAG = "get_authToken";

   protected Element base = null;

   String userID = null;
   String cred = null;

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public GetAuthToken() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param userID String
    * @param cred String
    */
   public GetAuthToken(String userID,
      String cred) {
      this.userID = userID;
      this.cred = cred;
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
   public GetAuthToken(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
      super(base);
      userID = base.getAttribute("userID");
      cred = base.getAttribute("cred");
   }

   public void setUserID(String s) {
      userID = s;
   }

   public void setCred(String s) {
      cred = s;
   }

   public String getUserID() {
      return userID;
   }


   public String getCred() {
      return cred;
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
      // Save attributes.
      base.setAttribute("generic", UDDIElement.GENERIC);
      if (userID!=null) {
         base.setAttribute("userID", userID);
      }
      if (cred!=null) {
         base.setAttribute("cred", cred);
      }
      parent.appendChild(base);
   }
}
