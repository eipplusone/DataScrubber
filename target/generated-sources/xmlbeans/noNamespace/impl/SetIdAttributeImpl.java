/*
 * An XML attribute type.
 * Localname: set_id
 * Namespace: 
 * Java type: noNamespace.SetIdAttribute
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one set_id(@) attribute.
 *
 * This is a complex type.
 */
public class SetIdAttributeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.SetIdAttribute
{
    private static final long serialVersionUID = 1L;
    
    public SetIdAttributeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SETID$0 = 
        new javax.xml.namespace.QName("", "set_id");
    
    
    /**
     * Gets the "set_id" attribute
     */
    public java.lang.String getSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SETID$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "set_id" attribute
     */
    public org.apache.xmlbeans.XmlString xgetSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SETID$0);
            return target;
        }
    }
    
    /**
     * True if has "set_id" attribute
     */
    public boolean isSetSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(SETID$0) != null;
        }
    }
    
    /**
     * Sets the "set_id" attribute
     */
    public void setSetId(java.lang.String setId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SETID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SETID$0);
            }
            target.setStringValue(setId);
        }
    }
    
    /**
     * Sets (as xml) the "set_id" attribute
     */
    public void xsetSetId(org.apache.xmlbeans.XmlString setId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(SETID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(SETID$0);
            }
            target.set(setId);
        }
    }
    
    /**
     * Unsets the "set_id" attribute
     */
    public void unsetSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(SETID$0);
        }
    }
}
