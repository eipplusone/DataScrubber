/*
 * An XML document type.
 * Localname: substitution_rule
 * Namespace: 
 * Java type: noNamespace.SubstitutionRuleDocument
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one substitution_rule(@) element.
 *
 * This is a complex type.
 */
public class SubstitutionRuleDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.SubstitutionRuleDocument
{
    private static final long serialVersionUID = 1L;
    
    public SubstitutionRuleDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBSTITUTIONRULE$0 = 
        new javax.xml.namespace.QName("", "substitution_rule");
    
    
    /**
     * Gets the "substitution_rule" element
     */
    public noNamespace.SubstitutionRuleDocument.SubstitutionRule getSubstitutionRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionRuleDocument.SubstitutionRule target = null;
            target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().find_element_user(SUBSTITUTIONRULE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "substitution_rule" element
     */
    public void setSubstitutionRule(noNamespace.SubstitutionRuleDocument.SubstitutionRule substitutionRule)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionRuleDocument.SubstitutionRule target = null;
            target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().find_element_user(SUBSTITUTIONRULE$0, 0);
            if (target == null)
            {
                target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().add_element_user(SUBSTITUTIONRULE$0);
            }
            target.set(substitutionRule);
        }
    }
    
    /**
     * Appends and returns a new empty "substitution_rule" element
     */
    public noNamespace.SubstitutionRuleDocument.SubstitutionRule addNewSubstitutionRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionRuleDocument.SubstitutionRule target = null;
            target = (noNamespace.SubstitutionRuleDocument.SubstitutionRule)get_store().add_element_user(SUBSTITUTIONRULE$0);
            return target;
        }
    }
    /**
     * An XML substitution_rule(@).
     *
     * This is a complex type.
     */
    public static class SubstitutionRuleImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.SubstitutionRuleDocument.SubstitutionRule
    {
        private static final long serialVersionUID = 1L;
        
        public SubstitutionRuleImpl(org.apache.xmlbeans.SchemaType sType)
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
}
