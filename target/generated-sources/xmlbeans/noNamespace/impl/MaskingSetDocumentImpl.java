/*
 * An XML document type.
 * Localname: masking_set
 * Namespace: 
 * Java type: noNamespace.MaskingSetDocument
 *
 * Automatically generated - do not modify.
 */
package noNamespace.impl;
/**
 * A document containing one masking_set(@) element.
 *
 * This is a complex type.
 */
public class MaskingSetDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.MaskingSetDocument
{
    private static final long serialVersionUID = 1L;
    
    public MaskingSetDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MASKINGSET$0 = 
        new javax.xml.namespace.QName("", "masking_set");
    
    
    /**
     * Gets the "masking_set" element
     */
    public noNamespace.MaskingSetDocument.MaskingSet getMaskingSet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.MaskingSetDocument.MaskingSet target = null;
            target = (noNamespace.MaskingSetDocument.MaskingSet)get_store().find_element_user(MASKINGSET$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "masking_set" element
     */
    public void setMaskingSet(noNamespace.MaskingSetDocument.MaskingSet maskingSet)
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.MaskingSetDocument.MaskingSet target = null;
            target = (noNamespace.MaskingSetDocument.MaskingSet)get_store().find_element_user(MASKINGSET$0, 0);
            if (target == null)
            {
                target = (noNamespace.MaskingSetDocument.MaskingSet)get_store().add_element_user(MASKINGSET$0);
            }
            target.set(maskingSet);
        }
    }
    
    /**
     * Appends and returns a new empty "masking_set" element
     */
    public noNamespace.MaskingSetDocument.MaskingSet addNewMaskingSet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            noNamespace.MaskingSetDocument.MaskingSet target = null;
            target = (noNamespace.MaskingSetDocument.MaskingSet)get_store().add_element_user(MASKINGSET$0);
            return target;
        }
    }
    /**
     * An XML masking_set(@).
     *
     * This is a complex type.
     */
    public static class MaskingSetImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements noNamespace.MaskingSetDocument.MaskingSet
    {
        private static final long serialVersionUID = 1L;
        
        public MaskingSetImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName NAME$0 = 
            new javax.xml.namespace.QName("", "name");
        private static final javax.xml.namespace.QName DATECREATED$2 = 
            new javax.xml.namespace.QName("", "date_created");
        private static final javax.xml.namespace.QName RULES$4 = 
            new javax.xml.namespace.QName("", "rules");
        
        
        /**
         * Gets the "name" element
         */
        public java.lang.String getName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "name" element
         */
        public org.apache.xmlbeans.XmlString xgetName()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "name" element
         */
        public void setName(java.lang.String name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NAME$0);
                }
                target.setStringValue(name);
            }
        }
        
        /**
         * Sets (as xml) the "name" element
         */
        public void xsetName(org.apache.xmlbeans.XmlString name)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(NAME$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(NAME$0);
                }
                target.set(name);
            }
        }
        
        /**
         * Gets the "date_created" element
         */
        public java.util.Calendar getDateCreated()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATECREATED$2, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getCalendarValue();
            }
        }
        
        /**
         * Gets (as xml) the "date_created" element
         */
        public org.apache.xmlbeans.XmlDate xgetDateCreated()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDate target = null;
                target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(DATECREATED$2, 0);
                return target;
            }
        }
        
        /**
         * Sets the "date_created" element
         */
        public void setDateCreated(java.util.Calendar dateCreated)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DATECREATED$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DATECREATED$2);
                }
                target.setCalendarValue(dateCreated);
            }
        }
        
        /**
         * Sets (as xml) the "date_created" element
         */
        public void xsetDateCreated(org.apache.xmlbeans.XmlDate dateCreated)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlDate target = null;
                target = (org.apache.xmlbeans.XmlDate)get_store().find_element_user(DATECREATED$2, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlDate)get_store().add_element_user(DATECREATED$2);
                }
                target.set(dateCreated);
            }
        }
        
        /**
         * Gets the "rules" element
         */
        public noNamespace.RulesDocument.Rules getRules()
        {
            synchronized (monitor())
            {
                check_orphaned();
                noNamespace.RulesDocument.Rules target = null;
                target = (noNamespace.RulesDocument.Rules)get_store().find_element_user(RULES$4, 0);
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
                target = (noNamespace.RulesDocument.Rules)get_store().find_element_user(RULES$4, 0);
                if (target == null)
                {
                    target = (noNamespace.RulesDocument.Rules)get_store().add_element_user(RULES$4);
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
                target = (noNamespace.RulesDocument.Rules)get_store().add_element_user(RULES$4);
                return target;
            }
        }
    }
}
