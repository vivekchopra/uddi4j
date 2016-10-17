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
import org.uddi4j.datatype.tmodel.TModel;
import org.uddi4j.util.AuthInfo;
import org.uddi4j.util.UploadRegister;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the save_tModel element within the UDDI version 2.0 schema.
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
 * <p>This message is used to register or update a tModel. Either a tModel or
 * uploadRegister object is required.  Invalid if contains both types or neither type.
 *
 * @author David Melgar (dmelgar@us.ibm.com)
 */
public class SaveTModel extends UDDIElement {

   public static final String UDDI_TAG = "save_tModel";

   protected Element base = null;

   AuthInfo authInfo = null;
   // Vector of TModel objects
   Vector tModel = new Vector();
   // Vector of UploadRegister objects
   Vector uploadRegister = new Vector();

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public SaveTModel() {
   }

   /**
    * Construct the object with required fields.
    *
    * @param authInfo String
    */
   public SaveTModel(String authInfo) {
      this.authInfo = new AuthInfo( authInfo );
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
   public SaveTModel(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
      super(base);
      NodeList nl = null;
      nl = getChildElementsByTagName(base, AuthInfo.UDDI_TAG);
      if (nl.getLength() > 0) {
         authInfo = new AuthInfo((Element)nl.item(0));
      }
      nl = getChildElementsByTagName(base, TModel.UDDI_TAG);
      for (int i=0; i < nl.getLength(); i++) {
         tModel.addElement(new TModel((Element)nl.item(i)));
      }
      nl = getChildElementsByTagName(base, UploadRegister.UDDI_TAG);
      for (int i=0; i < nl.getLength(); i++) {
         uploadRegister.addElement(new UploadRegister((Element)nl.item(i)));
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
    * Set tModel vector.
    *
    * @param s  Vector of <I>TModel</I> objects.
    */
   public void setTModelVector(Vector s) {
      tModel = s;
   }

   /**
    * Set uploadRegister vector.
    *
    * @param s  Vector of <I>UploadRegister</I> objects.
    */
   public void setUploadRegisterVector(Vector s) {
      uploadRegister = s;
   }

   /**
    * Set uploadRegister.
    *
    * @param s  Vector of <I>String</I> objects.
    */
   public void setUploadRegisterStrings(Vector s) {
      uploadRegister = new Vector();
      for (int i = 0; i < s.size(); i++) {
         uploadRegister.addElement( new UploadRegister((String)s.elementAt(i)) );
      }
   }

   public AuthInfo getAuthInfo() {
      return authInfo;
   }

   public String getAuthInfoString() {
      return authInfo.getText();
   }

   /**
    * Get tModel vector.
    *
    * @return s Vector of <I>TModel</I> objects.
    */
   public Vector getTModelVector() {
      return tModel;
   }

   /**
    * Get uploadRegister vector.
    *
    * @return s Vector of <I>UploadRegister</I> objects.
    */
   public Vector getUploadRegisterVector() {
      return uploadRegister;
   }

   /**
    * Get uploadRegister.
    *
    * @return s Vector of <I>String</I> objects.
    */
   public Vector getUploadRegisterStrings() {
      Vector strings = new Vector();
      for (int i = 0; i < uploadRegister.size(); i++) {
         strings.addElement( ((UploadRegister)uploadRegister.elementAt(i)).getText());
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
      if (tModel!=null) {
        for (int i=0; i < tModel.size(); i++) {
           ((TModel)(tModel.elementAt(i))).saveToXML(base);
        }
      }
      if (uploadRegister!=null) {
        for (int i=0; i < uploadRegister.size(); i++) {
           ((UploadRegister)(uploadRegister.elementAt(i))).saveToXML(base);
        }
      }
      parent.appendChild(base);
   }
}
