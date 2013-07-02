/*
 * An XML document type.
 * Localname: rules
 * Namespace: 
 * Java type: noNamespace.RulesDocument
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one rules(@) element.
 *
 * This is a complex type.
 */
public class RulesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.RulesDocument
{
    private static final long serialVersionUID = 1L;
    
    public RulesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RULES$0 = 
        new javax.xml.namespace.QName("", "rules");
    
    
    /**
     * Gets the "rules" element
     */
    public noNamespace.RulesDocument.Rules getRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.RulesDocument.Rules target = null;
            target = (noNamespace.RulesDocument.Rules)get_store().find_element_user(RULES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "rules" element
     */
    public void setRules(noNamespace.RulesDocument.Rules rules)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.RulesDocument.Rules target = null;
            target = (noNamespace.RulesDocument.Rules)get_store().find_element_user(RULES$0, 0);
            if (target == null)
            {
                target = (noNamespace.RulesDocument.Rules)get_store().add_element_user(RULES$0);
            }
            target.set(rules);
        }
    }
    
    /**
     * Appends and returns a new empty "rules" element
     */
    public noNamespace.RulesDocument.Rules addNewRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.RulesDocument.Rules target = null;
            target = (noNamespace.RulesDocument.Rules)get_store().add_element_user(RULES$0);
            return target;
        }
    }
    /**
     * An XML rules(@).
     *
     * This is a complex type.
     */
    public static class RulesImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.RulesDocument.Rules
    {
        private static final long serialVersionUID = 1L;
        
        public RulesImpl(org.apache.xmlbeans.SchemaType sType)
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
}
