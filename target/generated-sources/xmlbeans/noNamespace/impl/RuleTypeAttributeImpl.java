/*
 * An XML attribute type.
 * Localname: rule_type
 * Namespace: 
 * Java type: noNamespace.RuleTypeAttribute
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one rule_type(@) attribute.
 *
 * This is a complex type.
 */
public class RuleTypeAttributeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.RuleTypeAttribute
{
    private static final long serialVersionUID = 1L;
    
    public RuleTypeAttributeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RULETYPE$0 = 
        new javax.xml.namespace.QName("", "rule_type");
    
    
    /**
     * Gets the "rule_type" attribute
     */
    public noNamespace.RuleType.Enum getRuleType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULETYPE$0);
            if (target == null)
            {
                return null;
            }
            return (noNamespace.RuleType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "rule_type" attribute
     */
    public noNamespace.RuleType xgetRuleType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.RuleType target = null;
            target = (noNamespace.RuleType)get_store().find_attribute_user(RULETYPE$0);
            return target;
        }
    }
    
    /**
     * True if has "rule_type" attribute
     */
    public boolean isSetRuleType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(RULETYPE$0) != null;
        }
    }
    
    /**
     * Sets the "rule_type" attribute
     */
    public void setRuleType(noNamespace.RuleType.Enum ruleType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULETYPE$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RULETYPE$0);
            }
            target.setEnumValue(ruleType);
        }
    }
    
    /**
     * Sets (as xml) the "rule_type" attribute
     */
    public void xsetRuleType(noNamespace.RuleType ruleType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.RuleType target = null;
            target = (noNamespace.RuleType)get_store().find_attribute_user(RULETYPE$0);
            if (target == null)
            {
                target = (noNamespace.RuleType)get_store().add_attribute_user(RULETYPE$0);
            }
            target.set(ruleType);
        }
    }
    
    /**
     * Unsets the "rule_type" attribute
     */
    public void unsetRuleType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(RULETYPE$0);
        }
    }
}
