/*
 * XML Type:  shuffle_rule
 * Namespace: 
 * Java type: noNamespace.ShuffleRule
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * An XML shuffle_rule(@).
 *
 * This is a complex type.
 */
public class ShuffleRuleImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.ShuffleRule
{
    private static final long serialVersionUID = 1L;
    
    public ShuffleRuleImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COLUMN$0 = 
        new javax.xml.namespace.QName("", "column");
    
    
    /**
     * Gets array of all "column" elements
     */
    public java.lang.String[] getColumnArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COLUMN$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "column" element
     */
    public java.lang.String getColumnArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMN$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "column" elements
     */
    public org.apache.xmlbeans.XmlString[] xgetColumnArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COLUMN$0, targetList);
            org.apache.xmlbeans.XmlString[] result = new org.apache.xmlbeans.XmlString[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "column" element
     */
    public org.apache.xmlbeans.XmlString xgetColumnArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMN$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (org.apache.xmlbeans.XmlString)target;
        }
    }
    
    /**
     * Returns number of "column" element
     */
    public int sizeOfColumnArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COLUMN$0);
        }
    }
    
    /**
     * Sets array of all "column" element
     */
    public void setColumnArray(java.lang.String[] columnArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(columnArray, COLUMN$0);
        }
    }
    
    /**
     * Sets ith "column" element
     */
    public void setColumnArray(int i, java.lang.String column)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COLUMN$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(column);
        }
    }
    
    /**
     * Sets (as xml) array of all "column" element
     */
    public void xsetColumnArray(org.apache.xmlbeans.XmlString[]columnArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(columnArray, COLUMN$0);
        }
    }
    
    /**
     * Sets (as xml) ith "column" element
     */
    public void xsetColumnArray(int i, org.apache.xmlbeans.XmlString column)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COLUMN$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(column);
        }
    }
    
    /**
     * Inserts the value as the ith "column" element
     */
    public void insertColumn(int i, java.lang.String column)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(COLUMN$0, i);
            target.setStringValue(column);
        }
    }
    
    /**
     * Appends the value as the last "column" element
     */
    public void addColumn(java.lang.String column)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COLUMN$0);
            target.setStringValue(column);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "column" element
     */
    public org.apache.xmlbeans.XmlString insertNewColumn(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().insert_element_user(COLUMN$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "column" element
     */
    public org.apache.xmlbeans.XmlString addNewColumn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COLUMN$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "column" element
     */
    public void removeColumn(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COLUMN$0, i);
        }
    }
}
