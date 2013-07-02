/*
 * An XML attribute type.
 * Localname: target
 * Namespace: 
 * Java type: noNamespace.TargetAttribute
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one target(@) attribute.
 *
 * This is a complex type.
 */
public class TargetAttributeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.TargetAttribute
{
    private static final long serialVersionUID = 1L;
    
    public TargetAttributeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TARGET$0 = 
        new javax.xml.namespace.QName("", "target");
    
    
    /**
     * Gets the "target" attribute
     */
    public java.lang.String getTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TARGET$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "target" attribute
     */
    public org.apache.xmlbeans.XmlString xgetTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TARGET$0);
            return target;
        }
    }
    
    /**
     * True if has "target" attribute
     */
    public boolean isSetTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(TARGET$0) != null;
        }
    }
    
    /**
     * Sets the "target" attribute
     */
    public void setTarget(java.lang.String targetValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TARGET$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TARGET$0);
            }
            target.setStringValue(targetValue);
        }
    }
    
    /**
     * Sets (as xml) the "target" attribute
     */
    public void xsetTarget(org.apache.xmlbeans.XmlString targetValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TARGET$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(TARGET$0);
            }
            target.set(targetValue);
        }
    }
    
    /**
     * Unsets the "target" attribute
     */
    public void unsetTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(TARGET$0);
        }
    }
}
