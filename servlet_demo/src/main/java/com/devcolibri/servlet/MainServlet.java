package com.devcolibri.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/mypage")
public class MainServlet extends HttpServlet {

    Connection connection = null;

    /* ------------------------------------------------------------------------------------------------------------- */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        DBController db = new DBController();
        try {
                connection = db.getConnection("java:/comp/env/jdbc/postgres");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* ------------------------------------------------------------------------------------------------------------- */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*

        DBController db = new DBController();
        ResultSet rs = null;

        if(db.isConnected()) {

           rs = db.execQuery("select * from myTable");
           request.setAttribute("result", rs);
           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mypage.jsp");
           dispatcher.forward(request, response);

        }
        */

    }

    /* ------------------------------------------------------------------------------------------------------------- */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        Map columns = new HashMap();
        columns.put("FirstName", request.getParameter("FirstName"));
        columns.put("SecondName", request.getParameter("SecondName"));
        columns.put("LastName", request.getParameter("LastName"));
        columns.put("Receiver", request.getParameter("Receiver"));
        columns.put("Theme", request.getParameter("Theme"));
        columns.put("Message", request.getParameter("Message"));

        boolean hasConnected;

        DBController db = new DBController();
        hasConnected = db.isConnected();

        if(hasConnected) {
            System.out.println("DB connected");
            System.out.println(db.insert("mytable", columns));
        }
        else System.out.println("Connection failed!");

        doGet(request, response);
        */
    }

}
