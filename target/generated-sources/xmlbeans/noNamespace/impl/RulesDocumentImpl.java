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
        
        private static final javax.xml.namespace.QName SHUFFLERULE$0 = 
            new javax.xml.namespace.QName("", "shuffle_rule");
        private static final javax.xml.namespace.QName SUBSTITUTIONRULE$2 = 
            new javax.xml.namespace.QName("", "substitution_rule");
        
        
        /**
         * Gets array of all "shuffle_rule" elements
         */
        public noNamespace.ShuffleRuleDocument.ShuffleRule[] getShuffleRuleArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(SHUFFLERULE$0, targetList);
                noNamespace.ShuffleRuleDocument.ShuffleRule[] result = new noNamespace.ShuffleRuleDocument.ShuffleRule[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "shuffle_rule" element
         */
        public noNamespace.ShuffleRuleDocument.ShuffleRule getShuffleRuleArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.ShuffleRuleDocument.ShuffleRule target = null;
                target = (noNamespace.ShuffleRuleDocument.ShuffleRule)get_store().find_element_user(SHUFFLERULE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "shuffle_rule" element
         */
        public int sizeOfShuffleRuleArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(SHUFFLERULE$0);
            }
        }
        
        /**
         * Sets array of all "shuffle_rule" element
         */
        public void setShuffleRuleArray(noNamespace.ShuffleRuleDocument.ShuffleRule[] shuffleRuleArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(shuffleRuleArray, SHUFFLERULE$0);
            }
        }
        
        /**
         * Sets ith "shuffle_rule" element
         */
        public void setShuffleRuleArray(int i, noNamespace.ShuffleRuleDocument.ShuffleRule shuffleRule)
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.ShuffleRuleDocument.ShuffleRule target = null;
                target = (noNamespace.ShuffleRuleDocument.ShuffleRule)get_store().find_element_user(SHUFFLERULE$0, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(shuffleRule);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "shuffle_rule" element
         */
        public noNamespace.ShuffleRuleDocument.ShuffleRule insertNewShuffleRule(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.ShuffleRuleDocument.ShuffleRule target = null;
                target = (noNamespace.ShuffleRuleDocument.ShuffleRule)get_store().insert_element_user(SHUFFLERULE$0, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "shuffle_rule" element
         */
        public noNamespace.ShuffleRuleDocument.ShuffleRule addNewShuffleRule()
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.ShuffleRuleDocument.ShuffleRule target = null;
                target = (noNamespace.ShuffleRuleDocument.ShuffleRule)get_store().add_element_user(SHUFFLERULE$0);
                return target;
            }
        }
        
        /**
         * Removes the ith "shuffle_rule" element
         */
        public void removeShuffleRule(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(SHUFFLERULE$0, i);
            }
        }
        
        /**
         * Gets array of all "substitution_rule" elements
         */
        public noNamespace.SubstitutionRuleDocument.SubstitutionRule[] getSubstitutionRuleArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                java.util.List targetList = new java.util.ArrayList();
                get_store().find_all_element_users(SUBSTITUTIONRULE$2, targetList);
                noNamespace.SubstitutionRuleDocument.SubstitutionRule[] result = new noNamespace.SubstitutionRuleDocument.SubstitutionRule[targetList.size()];
                targetList.toArray(result);
                return result;
            }
        }
        
        /**
         * Gets ith "substitution_rule" element
         */
        public noNamespace.SubstitutionRuleDocument.SubstitutionRule getSubstitutionRuleArray(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.SubstitutionRuleDocument.SubstitutionRule target = null;
                target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().find_element_user(SUBSTITUTIONRULE$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                return target;
            }
        }
        
        /**
         * Returns number of "substitution_rule" element
         */
        public int sizeOfSubstitutionRuleArray()
        {
            synchronized (monitor())
            {
                check_orphaned();
                return get_store().count_elements(SUBSTITUTIONRULE$2);
            }
        }
        
        /**
         * Sets array of all "substitution_rule" element
         */
        public void setSubstitutionRuleArray(noNamespace.SubstitutionRuleDocument.SubstitutionRule[] substitutionRuleArray)
        {
            synchronized (monitor())
            {
                check_orphaned();
                arraySetterHelper(substitutionRuleArray, SUBSTITUTIONRULE$2);
            }
        }
        
        /**
         * Sets ith "substitution_rule" element
         */
        public void setSubstitutionRuleArray(int i, noNamespace.SubstitutionRuleDocument.SubstitutionRule substitutionRule)
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.SubstitutionRuleDocument.SubstitutionRule target = null;
                target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().find_element_user(SUBSTITUTIONRULE$2, i);
                if (target == null)
                {
                    throw new IndexOutOfBoundsException();
                }
                target.set(substitutionRule);
            }
        }
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "substitution_rule" element
         */
        public noNamespace.SubstitutionRuleDocument.SubstitutionRule insertNewSubstitutionRule(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.SubstitutionRuleDocument.SubstitutionRule target = null;
                target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().insert_element_user(SUBSTITUTIONRULE$2, i);
                return target;
            }
        }
        
        /**
         * Appends and returns a new empty value (as xml) as the last "substitution_rule" element
         */
        public noNamespace.SubstitutionRuleDocument.SubstitutionRule addNewSubstitutionRule()
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.SubstitutionRuleDocument.SubstitutionRule target = null;
                target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().add_element_user(SUBSTITUTIONRULE$2);
                return target;
            }
        }
        
        /**
         * Removes the ith "substitution_rule" element
         */
        public void removeSubstitutionRule(int i)
        {
            synchronized (monitor())
            {
                check_orphaned();
                get_store().remove_element(SUBSTITUTIONRULE$2, i);
            }
        }
    }
}
