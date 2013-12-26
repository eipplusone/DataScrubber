/*
 * XML Type:  rule
 * Namespace: 
 * Java type: noNamespace.Rule
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * An XML rule(@).
 *
 * This is a complex type.
 */
public class RuleImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.Rule
{
    private static final long serialVersionUID = 1L;
    
    public RuleImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ID$0 = 
        new javax.xml.namespace.QName("", "id");
    private static final javax.xml.namespace.QName SHUFFLE$2 = 
        new javax.xml.namespace.QName("", "shuffle");
    private static final javax.xml.namespace.QName SUBSTITUTE$4 = 
        new javax.xml.namespace.QName("", "substitute");
    private static final javax.xml.namespace.QName DEPENDENCIES$6 = 
        new javax.xml.namespace.QName("", "dependencies");
    private static final javax.xml.namespace.QName TARGET$8 = 
        new javax.xml.namespace.QName("", "target");
    private static final javax.xml.namespace.QName RULETYPE$10 = 
        new javax.xml.namespace.QName("", "rule_type");
    private static final javax.xml.namespace.QName DISABLED$12 = 
        new javax.xml.namespace.QName("", "disabled");
    
    
    /**
     * Gets the "id" element
     */
    public java.lang.String getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "id" element
     */
    public org.apache.xmlbeans.XmlString xgetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ID$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "id" element
     */
    public void setId(java.lang.String id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ID$0);
            }
            target.setStringValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "id" element
     */
    public void xsetId(org.apache.xmlbeans.XmlString id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ID$0);
            }
            target.set(id);
        }
    }
    
    /**
     * Gets the "shuffle" element
     */
    public noNamespace.ShuffleRule getShuffle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.ShuffleRule target = null;
            target = (noNamespace.ShuffleRule)get_store().find_element_user(SHUFFLE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "shuffle" element
     */
    public boolean isSetShuffle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SHUFFLE$2) != 0;
        }
    }
    
    /**
     * Sets the "shuffle" element
     */
    public void setShuffle(noNamespace.ShuffleRule shuffle)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.ShuffleRule target = null;
            target = (noNamespace.ShuffleRule)get_store().find_element_user(SHUFFLE$2, 0);
            if (target == null)
            {
                target = (noNamespace.ShuffleRule)get_store().add_element_user(SHUFFLE$2);
            }
            target.set(shuffle);
        }
    }
    
    /**
     * Appends and returns a new empty "shuffle" element
     */
    public noNamespace.ShuffleRule addNewShuffle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.ShuffleRule target = null;
            target = (noNamespace.ShuffleRule)get_store().add_element_user(SHUFFLE$2);
            return target;
        }
    }
    
    /**
     * Unsets the "shuffle" element
     */
    public void unsetShuffle()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SHUFFLE$2, 0);
        }
    }
    
    /**
     * Gets the "substitute" element
     */
    public noNamespace.SubstitutionRule getSubstitute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionRule target = null;
            target = (noNamespace.SubstitutionRule)get_store().find_element_user(SUBSTITUTE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "substitute" element
     */
    public boolean isSetSubstitute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SUBSTITUTE$4) != 0;
        }
    }
    
    /**
     * Sets the "substitute" element
     */
    public void setSubstitute(noNamespace.SubstitutionRule substitute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionRule target = null;
            target = (noNamespace.SubstitutionRule)get_store().find_element_user(SUBSTITUTE$4, 0);
            if (target == null)
            {
                target = (noNamespace.SubstitutionRule)get_store().add_element_user(SUBSTITUTE$4);
            }
            target.set(substitute);
        }
    }
    
    /**
     * Appends and returns a new empty "substitute" element
     */
    public noNamespace.SubstitutionRule addNewSubstitute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.SubstitutionRule target = null;
            target = (noNamespace.SubstitutionRule)get_store().add_element_user(SUBSTITUTE$4);
            return target;
        }
    }
    
    /**
     * Unsets the "substitute" element
     */
    public void unsetSubstitute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SUBSTITUTE$4, 0);
        }
    }
    
    /**
     * Gets the "dependencies" element
     */
    public noNamespace.DependenciesType getDependencies()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.DependenciesType target = null;
            target = (noNamespace.DependenciesType)get_store().find_element_user(DEPENDENCIES$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "dependencies" element
     */
    public boolean isSetDependencies()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DEPENDENCIES$6) != 0;
        }
    }
    
    /**
     * Sets the "dependencies" element
     */
    public void setDependencies(noNamespace.DependenciesType dependencies)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.DependenciesType target = null;
            target = (noNamespace.DependenciesType)get_store().find_element_user(DEPENDENCIES$6, 0);
            if (target == null)
            {
                target = (noNamespace.DependenciesType)get_store().add_element_user(DEPENDENCIES$6);
            }
            target.set(dependencies);
        }
    }
    
    /**
     * Appends and returns a new empty "dependencies" element
     */
    public noNamespace.DependenciesType addNewDependencies()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.DependenciesType target = null;
            target = (noNamespace.DependenciesType)get_store().add_element_user(DEPENDENCIES$6);
            return target;
        }
    }
    
    /**
     * Unsets the "dependencies" element
     */
    public void unsetDependencies()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DEPENDENCIES$6, 0);
        }
    }
    
    /**
     * Gets the "target" attribute
     */
    public java.lang.String getTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TARGET$8);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TARGET$8);
            return target;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TARGET$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TARGET$8);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(TARGET$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(TARGET$8);
            }
            target.set(targetValue);
        }
    }
    
    /**
     * Gets the "rule_type" attribute
     */
    public noNamespace.RuleType.Enum getRuleType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULETYPE$10);
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
            target = (noNamespace.RuleType)get_store().find_attribute_user(RULETYPE$10);
            return target;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULETYPE$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RULETYPE$10);
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
            target = (noNamespace.RuleType)get_store().find_attribute_user(RULETYPE$10);
            if (target == null)
            {
                target = (noNamespace.RuleType)get_store().add_attribute_user(RULETYPE$10);
            }
            target.set(ruleType);
        }
    }
    
    /**
     * Gets the "disabled" attribute
     */
    public boolean getDisabled()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DISABLED$12);
            if (target == null)
            {
                return false;
            }
            return target.getBooleanValue();
        }
    }
    
    /**
     * Gets (as xml) the "disabled" attribute
     */
    public org.apache.xmlbeans.XmlBoolean xgetDisabled()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(DISABLED$12);
            return target;
        }
    }
    
    /**
     * True if has "disabled" attribute
     */
    public boolean isSetDisabled()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(DISABLED$12) != null;
        }
    }
    
    /**
     * Sets the "disabled" attribute
     */
    public void setDisabled(boolean disabled)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DISABLED$12);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(DISABLED$12);
            }
            target.setBooleanValue(disabled);
        }
    }
    
    /**
     * Sets (as xml) the "disabled" attribute
     */
    public void xsetDisabled(org.apache.xmlbeans.XmlBoolean disabled)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(DISABLED$12);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_attribute_user(DISABLED$12);
            }
            target.set(disabled);
        }
    }
    
    /**
     * Unsets the "disabled" attribute
     */
    public void unsetDisabled()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(DISABLED$12);
        }
    }
}
