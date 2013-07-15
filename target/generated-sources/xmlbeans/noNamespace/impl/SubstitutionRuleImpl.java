/*
 * XML Type:  substitution_rule
 * Namespace: 
 * Java type: noNamespace.SubstitutionRule
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * An XML substitution_rule(@).
 *
 * This is a complex type.
 */
public class SubstitutionRuleImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.SubstitutionRule
{
    private static final long serialVersionUID = 1L;
    
    public SubstitutionRuleImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COLUMN$0 = 
        new javax.xml.namespace.QName("", "column");
    private static final javax.xml.namespace.QName FILEPATH$2 = 
        new javax.xml.namespace.QName("", "file_path");
    private static final javax.xml.namespace.QName SUBSTITUTIONDATATYPE$4 = 
        new javax.xml.namespace.QName("", "substitution_data_type");
    private static final javax.xml.namespace.QName SUBSTITUTIONACTIONTYPE$6 = 
        new javax.xml.namespace.QName("", "substitution__action_type");
    
    
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
    
    /**
     * Gets the "file_path" element
     */
    public java.lang.String getFilePath()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FILEPATH$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "file_path" element
     */
    public org.apache.xmlbeans.XmlString xgetFilePath()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FILEPATH$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "file_path" element
     */
    public boolean isSetFilePath()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(FILEPATH$2) != 0;
        }
    }
    
    /**
     * Sets the "file_path" element
     */
    public void setFilePath(java.lang.String filePath)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(FILEPATH$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(FILEPATH$2);
            }
            target.setStringValue(filePath);
        }
    }
    
    /**
     * Sets (as xml) the "file_path" element
     */
    public void xsetFilePath(org.apache.xmlbeans.XmlString filePath)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(FILEPATH$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(FILEPATH$2);
            }
            target.set(filePath);
        }
    }
    
    /**
     * Unsets the "file_path" element
     */
    public void unsetFilePath()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(FILEPATH$2, 0);
        }
    }
    
    /**
     * Gets the "substitution_data_type" attribute
     */
    public noNamespace.SubstitutionDataType.Enum getSubstitutionDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$4);
            if (target == null)
            {
                return null;
            }
            return (noNamespace.SubstitutionDataType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "substitution_data_type" attribute
     */
    public noNamespace.SubstitutionDataType xgetSubstitutionDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionDataType target = null;
            target = (noNamespace.SubstitutionDataType)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$4);
            return target;
        }
    }
    
    /**
     * Sets the "substitution_data_type" attribute
     */
    public void setSubstitutionDataType(noNamespace.SubstitutionDataType.Enum substitutionDataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SUBSTITUTIONDATATYPE$4);
            }
            target.setEnumValue(substitutionDataType);
        }
    }
    
    /**
     * Sets (as xml) the "substitution_data_type" attribute
     */
    public void xsetSubstitutionDataType(noNamespace.SubstitutionDataType substitutionDataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionDataType target = null;
            target = (noNamespace.SubstitutionDataType)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$4);
            if (target == null)
            {
                target = (noNamespace.SubstitutionDataType)get_store().add_attribute_user(SUBSTITUTIONDATATYPE$4);
            }
            target.set(substitutionDataType);
        }
    }
    
    /**
     * Gets the "substitution__action_type" attribute
     */
    public noNamespace.SubstitutionActionType.Enum getSubstitutionActionType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$6);
            if (target == null)
            {
                return null;
            }
            return (noNamespace.SubstitutionActionType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "substitution__action_type" attribute
     */
    public noNamespace.SubstitutionActionType xgetSubstitutionActionType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionActionType target = null;
            target = (noNamespace.SubstitutionActionType)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$6);
            return target;
        }
    }
    
    /**
     * Sets the "substitution__action_type" attribute
     */
    public void setSubstitutionActionType(noNamespace.SubstitutionActionType.Enum substitutionActionType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SUBSTITUTIONACTIONTYPE$6);
            }
            target.setEnumValue(substitutionActionType);
        }
    }
    
    /**
     * Sets (as xml) the "substitution__action_type" attribute
     */
    public void xsetSubstitutionActionType(noNamespace.SubstitutionActionType substitutionActionType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionActionType target = null;
            target = (noNamespace.SubstitutionActionType)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$6);
            if (target == null)
            {
                target = (noNamespace.SubstitutionActionType)get_store().add_attribute_user(SUBSTITUTIONACTIONTYPE$6);
            }
            target.set(substitutionActionType);
        }
    }
}
