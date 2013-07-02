/*
 * XML Type:  dependencies_type
 * Namespace: 
 * Java type: noNamespace.DependenciesType
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * An XML dependencies_type(@).
 *
 * This is a complex type.
 */
public class DependenciesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.DependenciesType
{
    private static final long serialVersionUID = 1L;
    
    public DependenciesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RULE$0 = 
        new javax.xml.namespace.QName("", "rule");
    
    
    /**
     * Gets array of all "rule" elements
     */
    public noNamespace.Rule[] getRuleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RULE$0, targetList);
            noNamespace.Rule[] result = new noNamespace.Rule[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "rule" element
     */
    public noNamespace.Rule getRuleArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.Rule target = null;
            target = (noNamespace.Rule)get_store().find_element_user(RULE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "rule" element
     */
    public int sizeOfRuleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RULE$0);
        }
    }
    
    /**
     * Sets array of all "rule" element
     */
    public void setRuleArray(noNamespace.Rule[] ruleArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(ruleArray, RULE$0);
        }
    }
    
    /**
     * Sets ith "rule" element
     */
    public void setRuleArray(int i, noNamespace.Rule rule)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.Rule target = null;
            target = (noNamespace.Rule)get_store().find_element_user(RULE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(rule);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "rule" element
     */
    public noNamespace.Rule insertNewRule(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.Rule target = null;
            target = (noNamespace.Rule)get_store().insert_element_user(RULE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "rule" element
     */
    public noNamespace.Rule addNewRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.Rule target = null;
            target = (noNamespace.Rule)get_store().add_element_user(RULE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "rule" element
     */
    public void removeRule(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RULE$0, i);
        }
    }
}
