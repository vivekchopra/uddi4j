/*
 * The source code contained herein is licensed under the IBM Public License
 * Version 1.0, which has been approved by the Open Source Initiative.
 * Copyright (C) 2001, International Business Machines Corporation
 * All Rights Reserved.
 *
 */

package org.uddi4j.datatype.business;

import java.util.Vector;

import org.uddi4j.UDDIElement;
import org.uddi4j.UDDIException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Represents the contacts element within the UDDI version 2.0 schema.
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
 * <p><b>Element description:</b>
 * Service element: accessor for one or more contacts.
 *
 * @author David Melgar (dmelgar@us.ibm.com)
 */
public class Contacts extends UDDIElement {

   public static final String UDDI_TAG = "contacts";

   protected Element base = null;

   // Vector of Contact objects
   Vector contact = new Vector();

   /**
    * Default constructor.
    * Avoid using the default constructor for validation. It does not validate
    * required fields. Instead, use the required fields constructor to perform
    * validation.
    */
   public Contacts() {
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
   public Contacts(Element base) throws UDDIException {
      // Check if its a fault. Throws an exception if it is.
      super(base);
      NodeList nl = null;
      nl = getChildElementsByTagName(base, Contact.UDDI_TAG);
      for (int i=0; i < nl.getLength(); i++) {
         contact.addElement(new Contact((Element)nl.item(i)));
      }
   }

   /**
    * Set contact vector.
    *
    * @param s  Vector of <I>Contact</I> objects.
    */
   public void setContactVector(Vector s) {
      contact = s;
   }

   /**
    * Get contact.
    *
    * @return s Vector of <I>Contact</I> objects.
    */
   public Vector getContactVector() {
      return contact;
   }

   /**
    * Add a Contact object to the collection
    * @param c Contact to be added
    */
   public void add (Contact c) {
      contact.add (c);
   }

   /**
    * Remove a Contact object from the collection
    * @param c Contact to be removed
    * @return True if object was removed, false if it
    *         was not found in the collection.
    */
   public boolean remove (Contact c) {
      return contact.remove (c);
   }

   /**
    * Retrieve the Contact at the specified index within the collection.
    * @param index Index to retrieve from.
    * @return Contact at that index
    */
   public Contact get (int index) {
      return (Contact) contact.get (index);
   }

   /**
    * Return current size of the collection.
    * @return Number of Contacts in the collection
    */
   public int size () {
      return contact.size ();
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
      if (contact!=null) {
        for (int i=0; i < contact.size(); i++) {
           ((Contact)(contact.elementAt(i))).saveToXML(base);
        }
      }
      parent.appendChild(base);
   }
}
