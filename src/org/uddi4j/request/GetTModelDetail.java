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
import org.uddi4j.util.TModelKey;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the get_tModelDetail element within the UDDI version 2.0 schema.
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
 * <p>This message is used to request the details about a specific tModel.
 * The results are returned in a tModelDetail message.
 *
 * @author David Melgar (dmelgar@us.ibm.com)
 */
public class GetTModelDetail extends UDDIElement {

   public static final String UDDI_TAG = "get_tModelDetail";

   protected Element base = null;

   // Vector of TModelKey objects
   Vector tModelKey = new Vector();

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public GetTModelDetail() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param tModelKeyStrings  Vector of TModelKey Strings.
    */
   public GetTModelDetail(Vector tModelKeyStrings) {
      Vector objects;
      objects = new Vector();
      for (int i = 0; i < tModelKeyStrings.size(); i++) {
         objects.addElement( new TModelKey((String)tModelKeyStrings.elementAt(i)) );
      }

      this.tModelKey = objects;
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
   public GetTModelDetail(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
      super(base);
      NodeList nl = null;
      nl = getChildElementsByTagName(base, TModelKey.UDDI_TAG);
      for (int i=0; i < nl.getLength(); i++) {
         tModelKey.addElement(new TModelKey((Element)nl.item(i)));
      }
   }

   /**
    * Set tModelKey vector.
    *
    * @param s  Vector of <I>TModelKey</I> objects.
    */
   public void setTModelKeyVector(Vector s) {
      tModelKey = s;
   }

   /**
    * Set tModelKey.
    *
    * @param s  Vector of <I>String</I> objects.
    */
   public void setTModelKeyStrings(Vector s) {
      tModelKey = new Vector();
      for (int i = 0; i < s.size(); i++) {
         tModelKey.addElement( new TModelKey((String)s.elementAt(i)) );
      }
   }

   /**
    * Get tModelKey vector.
    *
    * @return s Vector of <I>TModelKey</I> objects.
    */
   public Vector getTModelKeyVector() {
      return tModelKey;
   }

   /**
    * Get tModelKey.
    *
    * @return s Vector of <I>String</I> objects.
    */
   public Vector getTModelKeyStrings() {
      Vector strings = new Vector();
      for (int i = 0; i < tModelKey.size(); i++) {
         strings.addElement( ((TModelKey)tModelKey.elementAt(i)).getText());
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
      if (tModelKey!=null) {
        for (int i=0; i < tModelKey.size(); i++) {
           ((TModelKey)(tModelKey.elementAt(i))).saveToXML(base);
        }
      }
      parent.appendChild(base);
   }
}
