/*
 * An XML document type.
 * Localname: rule_type
 * Namespace: 
 * Java type: noNamespace.RuleTypeDocument
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one rule_type(@) element.
 *
 * This is a complex type.
 */
public class RuleTypeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.RuleTypeDocument
{
    private static final long serialVersionUID = 1L;
    
    public RuleTypeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RULETYPE$0 = 
        new javax.xml.namespace.QName("", "rule_type");
    
    
    /**
     * Gets the "rule_type" element
     */
    public noNamespace.RuleTypeDocument.RuleType.Enum getRuleType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RULETYPE$0, 0);
            if (target == null)
            {
                return null;
            }
            return (noNamespace.RuleTypeDocument.RuleType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "rule_type" element
     */
    public noNamespace.RuleTypeDocument.RuleType xgetRuleType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.RuleTypeDocument.RuleType target = null;
            target = (noNamespace.RuleTypeDocument.RuleType)get_store().find_element_user(RULETYPE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "rule_type" element
     */
    public void setRuleType(noNamespace.RuleTypeDocument.RuleType.Enum ruleType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RULETYPE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RULETYPE$0);
            }
            target.setEnumValue(ruleType);
        }
    }
    
    /**
     * Sets (as xml) the "rule_type" element
     */
    public void xsetRuleType(noNamespace.RuleTypeDocument.RuleType ruleType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.RuleTypeDocument.RuleType target = null;
            target = (noNamespace.RuleTypeDocument.RuleType)get_store().find_element_user(RULETYPE$0, 0);
            if (target == null)
            {
                target = (noNamespace.RuleTypeDocument.RuleType)get_store().add_element_user(RULETYPE$0);
            }
            target.set(ruleType);
        }
    }
    /**
     * An XML rule_type(@).
     *
     * This is an atomic type that is a restriction of noNamespace.RuleTypeDocument$RuleType.
     */
    public static class RuleTypeImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements noNamespace.RuleTypeDocument.RuleType
    {
        private static final long serialVersionUID = 1L;
        
        public RuleTypeImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected RuleTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
