package com.pearson.Interface;

/**
 * Created by ruslankiselev on 10/25/14.
 * <p/>
 * May the force be with you.
 * <p/>
 * Adhering Builder patter - see Effective Java by Joshua Bloch
 */

public class DatabaseConnectionInfo {
    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }

    public static String getDefaultSchema() {
        return defaultSchema;
    }

    public static String getPort() {
        return port;
    }

    private static String username;
    private static String password;
    private static String url;
    private static String defaultSchema;
    private static String port = null;

    public String getJDBCURL() {
        StringBuilder builder = new StringBuilder("jdbc:mysql://" + url);
        if (!port.isEmpty()) builder.append(":" + port);
        return builder.toString();
    }

    @Override
    public String toString() {
        return "URL: " + getJDBCURL() + " Password " + password;
    }

    public static class Builder {
        //required
        private static String username;
        private static String password;
        private static String url;
        // port and defaultSchema is not required
        private static String defaultSchema;
        private static String port = null;

        public Builder(String username, String password, String url) {
            this.username = username;
            this.password = password;
            this.url = url;
        }

        public Builder port(String port) {
            this.port = port;
            return this;
        }

        public Builder defaultSchema(String defaultSchema) {
            this.defaultSchema = defaultSchema;
            return this;
        }

        public DatabaseConnectionInfo build() {
            return new DatabaseConnectionInfo(this);
        }
    }

    private DatabaseConnectionInfo(Builder builder) {
        username = builder.username;
        password = builder.password;
        url = builder.url;
        defaultSchema = builder.defaultSchema;
        port = builder.port;
    }
}
