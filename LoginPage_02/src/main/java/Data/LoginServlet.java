package Data;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String Name= request.getParameter("name");
		String Email= request.getParameter("email");
		String Password= request.getParameter("password");
		boolean su=request.getParameter("signup") != null;
		boolean si=request.getParameter("signin") !=null;
		PrintWriter pw = response.getWriter();
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","root");
			Statement st= c.createStatement();
			if(su) {
				pw.write("hello");
				
				String queary="select * from reg5";
				ResultSet rs = st.executeQuery(queary);
				while(rs.next()) {
					String email=rs.getString("Email");
					pw.write("Entered Whileloop");
					if(email.equals(Email)) {
						pw.write("Email Already In Use!");
					}
					else {
						pw.write("Signup Process");
						String queary1="insert into reg5 values ('"+Name+"','"+Email+"','"+Password+"')";
						int i=st.executeUpdate(queary1);
						if(i>=1) {
							pw.write("\nRegistraion Compleate!!!");
						}
						else {
							pw.write("\nRegistraion Faild!!!!");
						}
					}
			}
			
			}
			else if (si) {
				//PrintWriter out= response.getWriter();
				boolean validCredentials = false;

				String queary="select * from reg5";
				ResultSet rs = st.executeQuery(queary);
				while(rs.next()) {
					String email=rs.getString("Email");
					String pas=rs.getString("Password");
					if(email.equals(Email)&&pas.equals(Password)) {
//						RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
//						rd.forward(request, response);
//						response.sendRedirect("welcome.jsp");
						validCredentials = true;
                        break;
						
					}
					  
		            }
				if (validCredentials) {
                    response.sendRedirect("welcome.jsp");
                } else {
                    response.sendRedirect("welcome1.jsp");
                }
//					else {
//						if(!response.isCommitted()) {
////							RequestDispatcher ra=request.getRequestDispatcher("welcome1.jsp");
//							response.sendRedirect("welcome1.jsp");
//						}
//						
//					}
					
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
