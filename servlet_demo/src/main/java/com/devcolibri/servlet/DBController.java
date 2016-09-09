package com.devcolibri.servlet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

    //private Connection conn = null;

    // Попытка соединения с БД. Если неудача - записываем ошибку и возвращаем null.
    /* ------------------------------------------------------------------------------------------------------------- */
    public Connection getConnection(String lookupString) throws Exception {

        InitialContext cxt = new InitialContext();
        if (cxt == null) {
            this.setErrorMsg("no context!");
            return null;
        }
        else {
                /*
                в файле /conf/context.xml (сервера Tomcat) ищется секция <Resource></> с именем name="jdbc/postgres"
                оттуда берутся параметры подключения к БД.
                */
                //DataSource ds = (DataSource)cxt.lookup("java:/comp/env/jdbc/postgres");
                DataSource ds = (DataSource)cxt.lookup(lookupString);

                if (ds == null) {
                    this.setErrorMsg("Data source not found!");
                    return null;
                }
                else {
                        return ds.getConnection();
                        /*
                        conn = ds.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("select * from mytable");

                        while(rs.next())
                        {
                          System.out.println(rs.getString(1) + " " +
                                             rs.getString(2) + " " +
                                             rs.getString(3) + " " +
                                             rs.getString(4) + " " +
                                             rs.getString(5) + " " +
                                             rs.getString(6) + " " +
                                             rs.getString(7));
                        }
                        conn.close();
                        */
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
            //e.printStackTrace();
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

    /* ------------------------------------------------------------------------------------------------------------- */
    /*
    public ResultSet execQuery(Connection conn, String query) {
        ResultSet rs = null;
        try
        {
            rs = conn.createStatement().executeQuery(query);
        }
        catch (SQLException e) { e.printStackTrace(); }
        return rs;
    }
    */

}
