/*
 * An XML document type.
 * Localname: column
 * Namespace: 
 * Java type: noNamespace.ColumnDocument
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one column(@) element.
 *
 * This is a complex type.
 */
public class ColumnDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.ColumnDocument
{
    private static final long serialVersionUID = 1L;
    
    public ColumnDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COLUMN$0 = 
        new javax.xml.namespace.QName("", "column");
    
    
    /**
     * Gets the "column" element
     */
    public java.lang.String getColumn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMN$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "column" element
     */
    public org.apache.xmlbeans.XmlString xgetColumn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMN$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "column" element
     */
    public void setColumn(java.lang.String column)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMN$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COLUMN$0);
            }
            target.setStringValue(column);
        }
    }
    
    /**
     * Sets (as xml) the "column" element
     */
    public void xsetColumn(org.apache.xmlbeans.XmlString column)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMN$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COLUMN$0);
            }
            target.set(column);
        }
    }
}
