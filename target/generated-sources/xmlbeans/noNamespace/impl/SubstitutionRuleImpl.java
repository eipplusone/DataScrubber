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
    private static final javax.xml.namespace.QName NUMERICVALUE$4 = 
        new javax.xml.namespace.QName("", "numeric_value");
    private static final javax.xml.namespace.QName STRINGVALUE1$6 = 
        new javax.xml.namespace.QName("", "string_value");
    private static final javax.xml.namespace.QName DATEVALUE1$8 = 
        new javax.xml.namespace.QName("", "date_value");
    private static final javax.xml.namespace.QName SUBSTITUTIONDATATYPE$10 = 
        new javax.xml.namespace.QName("", "substitution_data_type");
    private static final javax.xml.namespace.QName SUBSTITUTIONACTIONTYPE$12 = 
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
     * Gets the "numeric_value" element
     */
    public java.math.BigDecimal getNumericValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMERICVALUE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigDecimalValue();
        }
    }
    
    /**
     * Gets (as xml) the "numeric_value" element
     */
    public org.apache.xmlbeans.XmlDecimal xgetNumericValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDecimal target = null;
            target = (org.apache.xmlbeans.XmlDecimal)get_store().find_element_user(NUMERICVALUE$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "numeric_value" element
     */
    public boolean isSetNumericValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NUMERICVALUE$4) != 0;
        }
    }
    
    /**
     * Sets the "numeric_value" element
     */
    public void setNumericValue(java.math.BigDecimal numericValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMERICVALUE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NUMERICVALUE$4);
            }
            target.setBigDecimalValue(numericValue);
        }
    }
    
    /**
     * Sets (as xml) the "numeric_value" element
     */
    public void xsetNumericValue(org.apache.xmlbeans.XmlDecimal numericValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDecimal target = null;
            target = (org.apache.xmlbeans.XmlDecimal)get_store().find_element_user(NUMERICVALUE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDecimal)get_store().add_element_user(NUMERICVALUE$4);
            }
            target.set(numericValue);
        }
    }
    
    /**
     * Unsets the "numeric_value" element
     */
    public void unsetNumericValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NUMERICVALUE$4, 0);
        }
    }
    
    /**
     * Gets the "string_value" element
     */
    public java.lang.String getStringValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STRINGVALUE1$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "string_value" element
     */
    public org.apache.xmlbeans.XmlString xgetStringValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STRINGVALUE1$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "string_value" element
     */
    public boolean isSetStringValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STRINGVALUE1$6) != 0;
        }
    }
    
    /**
     * Sets the "string_value" element
     */
    public void setStringValue1(java.lang.String stringValue1)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STRINGVALUE1$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STRINGVALUE1$6);
            }
            target.setStringValue(stringValue1);
        }
    }
    
    /**
     * Sets (as xml) the "string_value" element
     */
    public void xsetStringValue1(org.apache.xmlbeans.XmlString stringValue1)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STRINGVALUE1$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(STRINGVALUE1$6);
            }
            target.set(stringValue1);
        }
    }
    
    /**
     * Unsets the "string_value" element
     */
    public void unsetStringValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STRINGVALUE1$6, 0);
        }
    }
    
    /**
     * Gets the "date_value" element
     */
    public java.math.BigInteger getDateValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATEVALUE1$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "date_value" element
     */
    public org.apache.xmlbeans.XmlInteger xgetDateValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(DATEVALUE1$8, 0);
            return target;
        }
    }
    
    /**
     * True if has "date_value" element
     */
    public boolean isSetDateValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DATEVALUE1$8) != 0;
        }
    }
    
    /**
     * Sets the "date_value" element
     */
    public void setDateValue1(java.math.BigInteger dateValue1)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATEVALUE1$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DATEVALUE1$8);
            }
            target.setBigIntegerValue(dateValue1);
        }
    }
    
    /**
     * Sets (as xml) the "date_value" element
     */
    public void xsetDateValue1(org.apache.xmlbeans.XmlInteger dateValue1)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(DATEVALUE1$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(DATEVALUE1$8);
            }
            target.set(dateValue1);
        }
    }
    
    /**
     * Unsets the "date_value" element
     */
    public void unsetDateValue1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DATEVALUE1$8, 0);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$10);
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
            target = (noNamespace.SubstitutionDataType)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$10);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SUBSTITUTIONDATATYPE$10);
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
            target = (noNamespace.SubstitutionDataType)get_store().find_attribute_user(SUBSTITUTIONDATATYPE$10);
            if (target == null)
            {
                target = (noNamespace.SubstitutionDataType)get_store().add_attribute_user(SUBSTITUTIONDATATYPE$10);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$12);
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
            target = (noNamespace.SubstitutionActionType)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$12);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$12);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SUBSTITUTIONACTIONTYPE$12);
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
            target = (noNamespace.SubstitutionActionType)get_store().find_attribute_user(SUBSTITUTIONACTIONTYPE$12);
            if (target == null)
            {
                target = (noNamespace.SubstitutionActionType)get_store().add_attribute_user(SUBSTITUTIONACTIONTYPE$12);
            }
            target.set(substitutionActionType);
        }
    }
}
