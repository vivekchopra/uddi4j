/*
 * The source code contained herein is licensed under the IBM Public License
 * Version 1.0, which has been approved by the Open Source Initiative.
 * Copyright (C) 2001, Hewlett-Packard Company
 * All Rights Reserved.
 *
 */

package org.uddi4j.request;

import java.util.Vector;

import org.uddi4j.UDDIElement;
import org.uddi4j.UDDIException;
import org.uddi4j.datatype.assertion.PublisherAssertion;
import org.uddi4j.util.AuthInfo;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the delete_publisherAssertions element within the UDDI version 2.0 schema.
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
 * <p>Used to delete specific publisher assertions from the assertion collection controlled by a particular
 * publisher account.  Deleting the assertion collection will affect the visibility of assertions from the
 * business relationships.  Deleting an assertion will cause any relationships based on that assertion to be
 * invalidated.
 *
 * @author Mahesh C S (csmahesh@india.hp.com)
 * @author Ozzy (ozzy@hursley.ibm.com)
 */
public class DeletePublisherAssertions extends UDDIElement {

    public static final String UDDI_TAG = "delete_publisherAssertions";

    protected Element base = null;

    AuthInfo authInfo = null;

    Vector publisherAssertionVector = new Vector();


   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public DeletePublisherAssertions() {
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
   public DeletePublisherAssertions(Element base) throws UDDIException {
      // Check if it is a fault. Throws an exception if it is.
       super(base);
       NodeList nl = null;
       nl = getChildElementsByTagName(base, AuthInfo.UDDI_TAG);
       if (nl.getLength() > 0) {
          authInfo = new AuthInfo((Element)nl.item(0));
       }
       nl = getChildElementsByTagName(base, PublisherAssertion.UDDI_TAG);
       for (int i=0; i < nl.getLength(); i++) {
          publisherAssertionVector.addElement(new PublisherAssertion((Element)nl.item(i)));
      }
   }

   /**
    * Construct the object with required fields.
    *
    * @param authInfo   String
    * @param pubAssertVector    Vector
    */
   public DeletePublisherAssertions(String authInfo,
                                    Vector pubAssertVector) throws UDDIException {
       this.authInfo = new AuthInfo( authInfo );
       Vector objects;
       objects = new Vector();
       for (int i = 0; i < pubAssertVector.size(); i++) {
           objects.addElement( new PublisherAssertion((Element)pubAssertVector.elementAt(i)));
       }

       this.publisherAssertionVector = objects;

   }

   /**
    * Set PublisherAssertion vector
    *
    * @param pubAssertVector Vector of <I>PublisherAssertion</I> objects.
    */
   public void setPublisherAssertionVector(Vector pubAssertVector ) {
       publisherAssertionVector = pubAssertVector;
   }

   /**
    * Get PublisherAssertion vector
    *
    * @return Vector of <I>PublisherAssertion</I> objects.
    */
   public Vector  getPublisherAssertionVector() {
       return publisherAssertionVector;
   }

   public AuthInfo getAuthInfo() {
       return authInfo;
   }

   public void setAuthInfo(AuthInfo s) {
       authInfo = s;
   }

   public String getAuthInfoString() {
       if(authInfo!=null)
         return authInfo.getText();
       else
         return null;
   }

   public void setAuthInfo(String s) {
       authInfo = new AuthInfo();
       authInfo.setText(s);
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
       if (authInfo!=null) {
          authInfo.saveToXML(base);
       }
       if (publisherAssertionVector!=null) {
         for (int i=0; i < publisherAssertionVector.size(); i++) {
            ((PublisherAssertion)(publisherAssertionVector.elementAt(i))).saveToXML(base);
         }
       }
       parent.appendChild(base);
   }
}
