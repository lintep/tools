package tools.database;


import java.sql.*;

public class DbConnection {

    private Connection con;

    private String dbUrl;

    private String user;

    private String password;

    private String dbClass;

    public DbConnection(String dbUrl, String dbClass,
                        String username, String pass) throws ClassNotFoundException,
            SQLException {
        this.dbUrl = dbUrl;
        this.dbClass = dbClass;
        this.user = username;
        this.password = pass;
        connect();
    }


    public static synchronized DbConnection getInstance(String dbUrl, String dbClass,
                                                        String username, String pass)
            throws ClassNotFoundException, SQLException {

        return new DbConnection(dbUrl, dbClass, username, pass);
    }

    private void connect() throws ClassNotFoundException,
            SQLException {
        Class.forName(dbClass);
        con = DriverManager.getConnection(dbUrl, user,
                password);
        Statement st = (Statement) con.createStatement();
        st.executeUpdate("SET CHARACTER set  UTF8");
        st.executeUpdate("SET NAMES 'utf8'");
    }

    public Connection getConnection() {
        try {
            if (con.isClosed()) {
                System.out.println("Connection closed, going to reconnect ...");
                reconnect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public boolean execDDLQuery(String query) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int rs = statement.executeUpdate(query);
        statement.close();
        connection.close();
        if (rs == 0)
            return true;
        else
            return false;
    }

    public boolean isConnected() {
        try {
            return con != null || con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void reconnect() throws ClassNotFoundException,
            SQLException {
        connect();
    }

    public void disconnect() throws SQLException {
        con.close();
    }

    protected boolean openConnection() {
        try {
            if (con.isClosed()) {
                connect();
            }
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("can not connect to " + dbUrl);
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String selectUniqueValue(String query) throws SQLException {
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query);
        String result = "";
        if (rs.next())
            result = rs.getString(1);
        rs.close();
        stmt.close();
        return result;
    }

    public PreparedStatement getPreparedStatement(String query) {
        try {
            return getConnection().prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] selectUniqueByteArrayValue(String query) throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        byte[] result = null;
        if (rs.next())
            result = rs.getBytes(1);
        rs.close();
        stmt.close();
        return result;
    }

    public Table select(String query) throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        java.sql.ResultSetMetaData rsm = rs.getMetaData();
        String[] fieldNames = new String[rsm.getColumnCount()];
        for (int i = 0; i < rsm.getColumnCount(); i++)
            fieldNames[i] = rsm.getColumnName(i + 1);
        Table result_tbl = new Table(rsm.getTableName(1), fieldNames);

        while (rs.next()) {

            String row[] = new String[rsm.getColumnCount()];
            for (int i = 1; i <= rsm.getColumnCount(); i++)
                row[i - 1] = rs.getString(i);
            try {
                result_tbl.insertRow(row);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // note that getString anomalously starts counting at 1, not 0
        rs.close();
        stmt.close();
        return result_tbl;
    }

    public Statement getStatement(boolean usePopulate) {
        Statement stmn;
        try {
            if (usePopulate)
                stmn = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            else
                stmn = getConnection().createStatement();

            return stmn;
        } catch (SQLException e) {
            System.out.println("Getting a statement. Message : " + e.getMessage());
            return null;
        }
    }
}
