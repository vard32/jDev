package com.devcolibri.servlet;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

/**
 * Created by Максим on 03.09.2016.
 */
public class DBController {

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorMsg;

    // Попытка соединения с БД. Если неудача - записываем ошибку и возвращаем null.
    /* ------------------------------------------------------------------------------------------------------------- */
    public Connection getConnection(String lookupString) throws Exception {

        InitialContext cxt = new InitialContext();
        if (cxt == null) {
            this.setErrorMsg("no context!");
            return null;
        }
        else {
                DataSource ds = (DataSource)cxt.lookup(lookupString);
                if (ds == null) {
                    this.setErrorMsg("Data source not found!");
                    return null;
                }
                else {
                        this.setErrorMsg("Connection successful!");
                        return ds.getConnection();
                }
        }
    }

    /* ------------------------------------------------------------------------------------------------------------- */
    public ResultSet getResultSet(Connection conn, String query) {
        try
        {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        }
        catch (SQLException e)
        {
            this.setErrorMsg(e.getMessage());
            return null;
        }
    }

    /* ------------------------------------------------------------------------------------------------------------- */
    public int insert(Connection conn, String table, Map<String, String> values) {

        String columns = "";
        String vals = "";
        int result = 0;

        // Переделать со StringBuilder!!!
        for (String col : values.keySet()) {
            columns = columns + "\"" + col + "\", ";
            vals = vals + "'" + values.get(col) + "', ";
        }

        columns = columns.substring(0, columns.length() - 2);
        vals = vals.substring(0, vals.length() - 2);

        String query = String.format("INSERT INTO %s (%s) VALUES (%s)", table, columns, vals);

        try { result = conn.createStatement().executeUpdate(query); }
        catch (SQLException e) { e.printStackTrace(); }

        return result;

    }

}
