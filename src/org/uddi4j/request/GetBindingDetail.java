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
import org.uddi4j.util.BindingKey;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the get_bindingDetail element within the UDDI version 2.0 schema.
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
 * <p>This message is used to get the detailed information that is registered
 * about bindingTemplate data that matches specific key value(s).
 *
 * @author David Melgar (dmelgar@us.ibm.com)
 */
public class GetBindingDetail extends UDDIElement {

   public static final String UDDI_TAG = "get_bindingDetail";

   protected Element base = null;

   // Vector of BindingKey objects
   Vector bindingKey = new Vector();

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public GetBindingDetail() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param bindingKeyStrings Vector of BindingKey Strings.
    */
   public GetBindingDetail(Vector bindingKeyStrings) {
      Vector objects;
      objects = new Vector();
      for (int i = 0; i < bindingKeyStrings.size(); i++) {
         objects.addElement( new BindingKey((String)bindingKeyStrings.elementAt(i)) );
      }

      this.bindingKey = objects;
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
   public GetBindingDetail(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
      super(base);
      NodeList nl = null;
      nl = getChildElementsByTagName(base, BindingKey.UDDI_TAG);
      for (int i=0; i < nl.getLength(); i++) {
         bindingKey.addElement(new BindingKey((Element)nl.item(i)));
      }
   }

   /**
    * Set bindingKey vector.
    *
    * @param s  Vector of <I>BindingKey</I> objects.
    */
   public void setBindingKeyVector(Vector s) {
      bindingKey = s;
   }

   /**
    * Set bindingKey.
    *
    * @param s  Vector of <I>String</I> objects.
    */
   public void setBindingKeyStrings(Vector s) {
      bindingKey = new Vector();
      for (int i = 0; i < s.size(); i++) {
         bindingKey.addElement( new BindingKey((String)s.elementAt(i)) );
      }
   }

   /**
    * Get bindingKey vector.
    *
    * @return s Vector of <I>BindingKey</I> objects.
    */
   public Vector getBindingKeyVector() {
      return bindingKey;
   }

   /**
    * Get bindingKey.
    *
    * @return s Vector of <I>String</I> objects.
    */
   public Vector getBindingKeyStrings() {
      Vector strings = new Vector();
      for (int i = 0; i < bindingKey.size(); i++) {
         strings.addElement( ((BindingKey)bindingKey.elementAt(i)).getText());
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
      // Save attributes.
      base.setAttribute("generic", UDDIElement.GENERIC);
      if (bindingKey!=null) {
        for (int i=0; i < bindingKey.size(); i++) {
           ((BindingKey)(bindingKey.elementAt(i))).saveToXML(base);
        }
      }
      parent.appendChild(base);
   }
}
