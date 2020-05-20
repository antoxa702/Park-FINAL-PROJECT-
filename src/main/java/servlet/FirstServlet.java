package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.ConnectionPoolException;
import pool.ConnectionPool;
import pool.ProxyConnection;

@WebServlet (name="/list")
public class FirstServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "index2.jsp";	
		
		ConnectionPool pool = ConnectionPool.INSTANCE;			
		ProxyConnection proxyConnection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM park";
		
		try {
			proxyConnection = pool.getConnection();
			statement = proxyConnection.createStatement();
			resultSet = statement.executeQuery(query);			
			
		} catch (ConnectionPoolException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				
				if (statement != null) {
					statement.close();
				}
				
				if (proxyConnection != null) {
					proxyConnection.close();
				}			
				
			} catch (SQLException e) {
				System.out.println("something is wrong with closing connection, statement or resultSet");
			}				
		}		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
