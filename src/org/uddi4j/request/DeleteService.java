/*
 * The source code contained herein is licensed under the IBM Public License
 * Version 1.0, which has been approved by the Open Source Initiative.
 * Copyright (C) 2001, International Business Machines Corporation
 * All Rights Reserved.
 *
 */

package org.uddi4j.request;

import java.util.Vector;

import org.uddi4j.UDDIElement;
import org.uddi4j.UDDIException;
import org.uddi4j.util.AuthInfo;
import org.uddi4j.util.ServiceKey;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the delete_service element within the UDDI version 2.0 schema.
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
 * <p>This message is used to delete information about a previously
 * registered businessService structure.
 *
 * @author David Melgar (dmelgar@us.ibm.com)
 * @author Ozzy (ozzy@hursley.ibm.com)
 */
public class DeleteService extends UDDIElement {

   public static final String UDDI_TAG = "delete_service";

   protected Element base = null;

   AuthInfo authInfo = null;
   // Vector of ServiceKey objects
   Vector serviceKey = new Vector();

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public DeleteService() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param authInfo String
    * @param serviceKeyStrings Vector of ServiceKey Strings.
    */
   public DeleteService(String authInfo,
      Vector serviceKeyStrings) {
      this.authInfo = new AuthInfo( authInfo );
      Vector objects;
      objects = new Vector();
      for (int i = 0; i < serviceKeyStrings.size(); i++) {
         objects.addElement( new ServiceKey((String)serviceKeyStrings.elementAt(i)) );
      }

      this.serviceKey = objects;
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
   public DeleteService(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
      super(base);
      NodeList nl = null;
      nl = getChildElementsByTagName(base, AuthInfo.UDDI_TAG);
      if (nl.getLength() > 0) {
         authInfo = new AuthInfo((Element)nl.item(0));
      }
      nl = getChildElementsByTagName(base, ServiceKey.UDDI_TAG);
      for (int i=0; i < nl.getLength(); i++) {
         serviceKey.addElement(new ServiceKey((Element)nl.item(i)));
      }
   }

   public void setAuthInfo(AuthInfo s) {
      authInfo = s;
   }
   public void setAuthInfo(String s) {
      authInfo = new AuthInfo();
      authInfo.setText(s);
   }

   /**
    * Set serviceKey vector.
    *
    * @param s  Vector of <I>ServiceKey</I> objects.
    */
   public void setServiceKeyVector(Vector s) {
      serviceKey = s;
   }

   /**
    * Set serviceKey.
    *
    * @param s  Vector of <I>String</I> objects.
    */
   public void setServiceKeyStrings(Vector s) {
      serviceKey = new Vector();
      for (int i = 0; i < s.size(); i++) {
         serviceKey.addElement( new ServiceKey((String)s.elementAt(i)) );
      }
   }

   public AuthInfo getAuthInfo() {
      return authInfo;
   }

   public String getAuthInfoString() {
      if(authInfo!=null)
        return authInfo.getText();
      else
        return null;
   }

   /**
    * Get serviceKey vector.
    *
    * @return s Vector of <I>ServiceKey</I> objects.
    */
   public Vector getServiceKeyVector() {
      return serviceKey;
   }

   /**
    * Get serviceKey.
    *
    * @return s Vector of <I>String</I> objects.
    */
   public Vector getServiceKeyStrings() {
      Vector strings = new Vector();
      for (int i = 0; i < serviceKey.size(); i++) {
         strings.addElement( ((ServiceKey)serviceKey.elementAt(i)).getText());
      }
      return strings;
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
      if (authInfo!=null) {
         authInfo.saveToXML(base);
      }
      if (serviceKey!=null) {
        for (int i=0; i < serviceKey.size(); i++) {
           ((ServiceKey)(serviceKey.elementAt(i))).saveToXML(base);
        }
      }
      parent.appendChild(base);
   }
}
