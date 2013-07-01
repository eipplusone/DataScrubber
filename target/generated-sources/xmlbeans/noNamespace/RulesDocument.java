/*
 * An XML document type.
 * Localname: rules
 * Namespace: 
 * Java type: noNamespace.RulesDocument
 *
 * Automatically generated - do not modify.
 */
package noNamespace;


/**
 * A document containing one rules(@) element.
 *
 * This is a complex type.
 */
public interface RulesDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(RulesDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s9913B6DBE3D6A013CCDE5C91B5437343").resolveHandle("rulesa41edoctype");
    
    /**
     * Gets the "rules" element
     */
    noNamespace.RulesDocument.Rules getRules();
    
    /**
     * Sets the "rules" element
     */
    void setRules(noNamespace.RulesDocument.Rules rules);
    
    /**
     * Appends and returns a new empty "rules" element
     */
    noNamespace.RulesDocument.Rules addNewRules();
    
    /**
     * An XML rules(@).
     *
     * This is a complex type.
     */
    public interface Rules extends org.apache.xmlbeans.XmlObject
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Rules.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s9913B6DBE3D6A013CCDE5C91B5437343").resolveHandle("rules1491elemtype");
        
        /**
         * Gets array of all "shuffle_rule" elements
         */
        noNamespace.ShuffleRuleDocument.ShuffleRule[] getShuffleRuleArray();
        
        /**
         * Gets ith "shuffle_rule" element
         */
        noNamespace.ShuffleRuleDocument.ShuffleRule getShuffleRuleArray(int i);
        
        /**
         * Returns number of "shuffle_rule" element
         */
        int sizeOfShuffleRuleArray();
        
        /**
         * Sets array of all "shuffle_rule" element
         */
        void setShuffleRuleArray(noNamespace.ShuffleRuleDocument.ShuffleRule[] shuffleRuleArray);
        
        /**
         * Sets ith "shuffle_rule" element
         */
        void setShuffleRuleArray(int i, noNamespace.ShuffleRuleDocument.ShuffleRule shuffleRule);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "shuffle_rule" element
         */
        noNamespace.ShuffleRuleDocument.ShuffleRule insertNewShuffleRule(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "shuffle_rule" element
         */
        noNamespace.ShuffleRuleDocument.ShuffleRule addNewShuffleRule();
        
        /**
         * Removes the ith "shuffle_rule" element
         */
        void removeShuffleRule(int i);
        
        /**
         * Gets array of all "substitution_rule" elements
         */
        noNamespace.SubstitutionRuleDocument.SubstitutionRule[] getSubstitutionRuleArray();
        
        /**
         * Gets ith "substitution_rule" element
         */
        noNamespace.SubstitutionRuleDocument.SubstitutionRule getSubstitutionRuleArray(int i);
        
        /**
         * Returns number of "substitution_rule" element
         */
        int sizeOfSubstitutionRuleArray();
        
        /**
         * Sets array of all "substitution_rule" element
         */
        void setSubstitutionRuleArray(noNamespace.SubstitutionRuleDocument.SubstitutionRule[] substitutionRuleArray);
        
        /**
         * Sets ith "substitution_rule" element
         */
        void setSubstitutionRuleArray(int i, noNamespace.SubstitutionRuleDocument.SubstitutionRule substitutionRule);
        
        /**
         * Inserts and returns a new empty value (as xml) as the ith "substitution_rule" element
         */
        noNamespace.SubstitutionRuleDocument.SubstitutionRule insertNewSubstitutionRule(int i);
        
        /**
         * Appends and returns a new empty value (as xml) as the last "substitution_rule" element
         */
        noNamespace.SubstitutionRuleDocument.SubstitutionRule addNewSubstitutionRule();
        
        /**
         * Removes the ith "substitution_rule" element
         */
        void removeSubstitutionRule(int i);
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static noNamespace.RulesDocument.Rules newInstance() {
              return (noNamespace.RulesDocument.Rules) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static noNamespace.RulesDocument.Rules newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (noNamespace.RulesDocument.Rules) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static noNamespace.RulesDocument newInstance() {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static noNamespace.RulesDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static noNamespace.RulesDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static noNamespace.RulesDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static noNamespace.RulesDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static noNamespace.RulesDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static noNamespace.RulesDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static noNamespace.RulesDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static noNamespace.RulesDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static noNamespace.RulesDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static noNamespace.RulesDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static noNamespace.RulesDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static noNamespace.RulesDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static noNamespace.RulesDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static noNamespace.RulesDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static noNamespace.RulesDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static noNamespace.RulesDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static noNamespace.RulesDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (noNamespace.RulesDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
