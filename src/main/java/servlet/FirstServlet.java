package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Park;
import exception.ConnectionPoolException;
import pool.ConnectionPool;
import pool.ProxyConnection;

@WebServlet (name="list", urlPatterns="/list")
public class FirstServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "index2.jsp";	
		
		ConnectionPool pool = ConnectionPool.INSTANCE;			
		ProxyConnection proxyConnection = null;
		Statement statement = null;
		ResultSet resultSet = null;		
		ArrayList<Park> parkList = null;
		String query = "SELECT * FROM park";
		
		try {
			proxyConnection = pool.getConnection();
			statement = proxyConnection.createStatement();
			resultSet = statement.executeQuery(query);			

			int id;
			String parkName;
			double parkArea;
			
			parkList = new ArrayList<>();			
			
			while(resultSet.next()) {				
				id = resultSet.getInt("id");
				parkName = resultSet.getString("name");
				parkArea = resultSet.getDouble("area");
				parkList.add(new Park(id, parkName, parkArea));				
			}
			
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
		
		request.setAttribute("parks", parkList);
		request.getRequestDispatcher(path).forward(request, response);	
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
