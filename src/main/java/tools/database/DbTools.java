package tools.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DbTools {

    public static int createDatabase(String nodeName,int portNumber,String user,String password,String dbName, DbType dbType) throws SQLException, ClassNotFoundException {
        Class.forName(getClass(dbType));
        String dbUrl = getDbUrl(nodeName, portNumber, null, dbType);
        try {
            System.out.println(user+" "+password+" "+dbUrl);
            return DriverManager.getConnection(dbUrl, user,password).createStatement().executeUpdate("CREATE DATABASE "+dbName);
        } catch (SQLException e){
            if(e.getMessage().indexOf("database exists")>0){
                System.out.println("Database "+dbName+" already exists.");
                return 0;
            }
            else {
                throw e;
            }
        }
    }

    public static void createMySqlTable(Connection connection, String tableName,
                            Map<String, DbDataFields.MySqlDataFields> field_names_and_types)
            throws Exception {
        if (field_names_and_types.size() == 0)
            throw new Exception("table fields not null");
        Statement statement = connection.createStatement();
        String createTableQuery = "CREATE TABLE `" + tableName
                + "` (`_id` int(11) NOT NULL auto_increment ,";
        for (Map.Entry<String, DbDataFields.MySqlDataFields> field : field_names_and_types
                .entrySet()) {
            createTableQuery += "`" + field.getKey() + "` "
                    + field.getValue().getValue()
                    + " collate utf8_unicode_ci NOT NULL ,";
        }
        createTableQuery += "PRIMARY KEY  (`_id`) ) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ;";// AUTO_INCREMENT=4899
        int rs = statement.executeUpdate(createTableQuery);
        statement.close();
        if (rs == 0)
            System.out.println("table " + tableName + " created");

    }

    public static void addMySQlIndex(Connection connection, String tableName, String column) throws SQLException {
        Statement statement = connection.createStatement();
        System.out.println("Adding index for column " + column + " on table " + tableName + " ...");
        int rs = statement.executeUpdate("ALTER TABLE `" + tableName + "` ADD INDEX ( `" + column + "` ) ;");
        statement.close();
        if (rs == 0)
            System.out.println("Index added for column " + column + ".");
    }

    private static final String				APOSTROPHE				= "\u0027";
    private static final String				REVERSE_SOLIDUS			= "\\";
    public static String getValidateQuery(String text) {
        if (text != null) {
            text = text.replace(APOSTROPHE, APOSTROPHE + APOSTROPHE).replace(REVERSE_SOLIDUS, REVERSE_SOLIDUS + REVERSE_SOLIDUS);
        }
        return text;
    }

    public static String getDbUrl(String nodeName, int portNumber,String dbName,DbType dbType){
        String db = (dbName == null || dbName.length()==0) ? "mysql" : dbName;
        switch (dbType){
            case MYSQl:
                return "jdbc:mysql://" + nodeName + ":" + portNumber + "/" + db
                        + "?characterEncoding=UTF-8";
        }
        return null;
    }

    public static String getClass(DbType dbType){
        switch (dbType) {
            case MYSQl:
                return "com.mysql.jdbc.Driver";
        }
        return null;
    }

    public static int getDefaultPort(DbType dbType){
        switch (dbType){
            case MYSQl:
                return 3306;
        }
        return -1;
    }

    public enum DbType{
        MYSQl
    }
}
