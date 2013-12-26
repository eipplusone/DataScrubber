/*
 * XML Type:  substitution_rule
 * Namespace: 
 * Java type: noNamespace.SubstitutionRule
 *
 * Automatically generated - do not modify.
 */
package noNamespace;


/**
 * An XML substitution_rule(@).
 *
 * This is a complex type.
 */
public interface SubstitutionRule extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SubstitutionRule.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s58FAB3B4D6AA99BEB84FAA29DCB5C0BC").resolveHandle("substitutionrule97b5type");
    
    /**
     * Gets the "column" element
     */
    java.lang.String getColumn();
    
    /**
     * Gets (as xml) the "column" element
     */
    org.apache.xmlbeans.XmlString xgetColumn();
    
    /**
     * Sets the "column" element
     */
    void setColumn(java.lang.String column);
    
    /**
     * Sets (as xml) the "column" element
     */
    void xsetColumn(org.apache.xmlbeans.XmlString column);
    
    /**
     * Gets the "file_path" element
     */
    java.lang.String getFilePath();
    
    /**
     * Gets (as xml) the "file_path" element
     */
    org.apache.xmlbeans.XmlString xgetFilePath();
    
    /**
     * True if has "file_path" element
     */
    boolean isSetFilePath();
    
    /**
     * Sets the "file_path" element
     */
    void setFilePath(java.lang.String filePath);
    
    /**
     * Sets (as xml) the "file_path" element
     */
    void xsetFilePath(org.apache.xmlbeans.XmlString filePath);
    
    /**
     * Unsets the "file_path" element
     */
    void unsetFilePath();
    
    /**
     * Gets the "numeric_value" element
     */
    java.math.BigDecimal getNumericValue();
    
    /**
     * Gets (as xml) the "numeric_value" element
     */
    org.apache.xmlbeans.XmlDecimal xgetNumericValue();
    
    /**
     * True if has "numeric_value" element
     */
    boolean isSetNumericValue();
    
    /**
     * Sets the "numeric_value" element
     */
    void setNumericValue(java.math.BigDecimal numericValue);
    
    /**
     * Sets (as xml) the "numeric_value" element
     */
    void xsetNumericValue(org.apache.xmlbeans.XmlDecimal numericValue);
    
    /**
     * Unsets the "numeric_value" element
     */
    void unsetNumericValue();
    
    /**
     * Gets the "string_value" element
     */
    java.lang.String getStringValue1();
    
    /**
     * Gets (as xml) the "string_value" element
     */
    org.apache.xmlbeans.XmlString xgetStringValue1();
    
    /**
     * True if has "string_value" element
     */
    boolean isSetStringValue1();
    
    /**
     * Sets the "string_value" element
     */
    void setStringValue1(java.lang.String stringValue1);
    
    /**
     * Sets (as xml) the "string_value" element
     */
    void xsetStringValue1(org.apache.xmlbeans.XmlString stringValue1);
    
    /**
     * Unsets the "string_value" element
     */
    void unsetStringValue1();
    
    /**
     * Gets the "date_value" element
     */
    java.math.BigInteger getDateValue1();
    
    /**
     * Gets (as xml) the "date_value" element
     */
    org.apache.xmlbeans.XmlInteger xgetDateValue1();
    
    /**
     * True if has "date_value" element
     */
    boolean isSetDateValue1();
    
    /**
     * Sets the "date_value" element
     */
    void setDateValue1(java.math.BigInteger dateValue1);
    
    /**
     * Sets (as xml) the "date_value" element
     */
    void xsetDateValue1(org.apache.xmlbeans.XmlInteger dateValue1);
    
    /**
     * Unsets the "date_value" element
     */
    void unsetDateValue1();
    
    /**
     * Gets the "substitution_data_type" attribute
     */
    noNamespace.SubstitutionDataType.Enum getSubstitutionDataType();
    
    /**
     * Gets (as xml) the "substitution_data_type" attribute
     */
    noNamespace.SubstitutionDataType xgetSubstitutionDataType();
    
    /**
     * Sets the "substitution_data_type" attribute
     */
    void setSubstitutionDataType(noNamespace.SubstitutionDataType.Enum substitutionDataType);
    
    /**
     * Sets (as xml) the "substitution_data_type" attribute
     */
    void xsetSubstitutionDataType(noNamespace.SubstitutionDataType substitutionDataType);
    
    /**
     * Gets the "substitution__action_type" attribute
     */
    noNamespace.SubstitutionActionType.Enum getSubstitutionActionType();
    
    /**
     * Gets (as xml) the "substitution__action_type" attribute
     */
    noNamespace.SubstitutionActionType xgetSubstitutionActionType();
    
    /**
     * Sets the "substitution__action_type" attribute
     */
    void setSubstitutionActionType(noNamespace.SubstitutionActionType.Enum substitutionActionType);
    
    /**
     * Sets (as xml) the "substitution__action_type" attribute
     */
    void xsetSubstitutionActionType(noNamespace.SubstitutionActionType substitutionActionType);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static noNamespace.SubstitutionRule newInstance() {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static noNamespace.SubstitutionRule newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static noNamespace.SubstitutionRule parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static noNamespace.SubstitutionRule parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static noNamespace.SubstitutionRule parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static noNamespace.SubstitutionRule parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static noNamespace.SubstitutionRule parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static noNamespace.SubstitutionRule parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static noNamespace.SubstitutionRule parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static noNamespace.SubstitutionRule parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static noNamespace.SubstitutionRule parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static noNamespace.SubstitutionRule parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static noNamespace.SubstitutionRule parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static noNamespace.SubstitutionRule parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static noNamespace.SubstitutionRule parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static noNamespace.SubstitutionRule parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static noNamespace.SubstitutionRule parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static noNamespace.SubstitutionRule parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (noNamespace.SubstitutionRule) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
