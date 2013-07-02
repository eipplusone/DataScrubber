/*
 * An XML document type.
 * Localname: date_created
 * Namespace: 
 * Java type: noNamespace.DateCreatedDocument
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one date_created(@) element.
 *
 * This is a complex type.
 */
public class DateCreatedDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.DateCreatedDocument
{
    private static final long serialVersionUID = 1L;
    
    public DateCreatedDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DATECREATED$0 = 
        new javax.xml.namespace.QName("", "date_created");
    
    
    /**
     * Gets the "date_created" element
     */
    public java.util.Calendar getDateCreated()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATECREATED$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "date_created" element
     */
    public org.apache.xmlbeans.XmlDate xgetDateCreated()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(DATECREATED$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "date_created" element
     */
    public void setDateCreated(java.util.Calendar dateCreated)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATECREATED$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DATECREATED$0);
            }
            target.setCalendarValue(dateCreated);
        }
    }
    
    /**
     * Sets (as xml) the "date_created" element
     */
    public void xsetDateCreated(org.apache.xmlbeans.XmlDate dateCreated)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDate target = null;
            target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(DATECREATED$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDate)get_store().add_element_user(DATECREATED$0);
            }
            target.set(dateCreated);
        }
    }
}
