import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private clientDAO clientDAO = new clientDAO();
	    private treeRequestDAO treeRequestDAO = new treeRequestDAO();
	    private quoteDAO QuoteDAO = new quoteDAO();
	    private orderOfWorkDAO orderOfWorkDAO = new orderOfWorkDAO();
	    private billDAO billDAO = new billDAO();
	    
	    
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	clientDAO = new clientDAO();
	    	treeRequestDAO = new treeRequestDAO();
	    	QuoteDAO = new quoteDAO();
	    	orderOfWorkDAO = new orderOfWorkDAO();
	    	billDAO = new billDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/registerClient":
        		registerClient(request, response);
        		break;
        	case "/registerTreeRequest":
        		registerTreeRequest(request, response);
        		break;
        	case "/registerQuote":
        		registerQuote(request, response);
        		break;
        	case "/AgreeServlet":
        		AgreeServlet(request, response);
        		break;
        	case "/QuitServlet":
        		QuitServlet(request, response);
        		break;
        	case "/ResponseServlet":
        		ResponseServlet(request, response);
        		break;
        	case "/initialize":
        		Initialize(request,response);
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    

		private void listQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listQuotes started: 00000000000000000000000000000000000");

	     
	        List<quote> listQuote = QuoteDAO.listAllQuotes();
	        request.setAttribute("listQuote", listQuote);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("currentQuoteRequests.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listQuotes finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
			request.setAttribute("listClients", clientDAO.listAllClients());
			request.setAttribute("listQuotes", QuoteDAO.listAllQuotes());
			request.setAttribute("listTreeRequests", treeRequestDAO.listAllRequests());
			request.setAttribute("listOrderOfWork", orderOfWorkDAO.listAllOrders());
			request.setAttribute("listBills", billDAO.listAllBills());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String username = request.getParameter("username");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (username.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", username);
				 rootPage(request, response, "");
				 request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    	 }
	    	 else if(username.equals("David Smith")){
	    		 System.out.println("Login Successful! Redirecting to David Smith");
	    		 request.getRequestDispatcher("davidSmith.jsp").forward(request, response);
	    	 }
	    	 else if(userDAO.isValid(username, password)) 
	    	 {
			 	 
			 	 currentUser = username;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("clientregistration.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	    
	    private void Initialize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	initializeDB.initializeDatabase();
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	    	dispatcher.forward(request, response);

	    }

	    
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String username = request.getParameter("username");
	   	 	String password = request.getParameter("password");
	   	 	String role = request.getParameter("role"); 	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkUsername(username)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(username, password, role);
		   	 		userDAO.insert(users);
		   	 		
		   	 		if ("client".equals(role)) {
		   	 			response.sendRedirect("clientregistration.jsp");
		   	 		} else {
		   	 			response.sendRedirect("login.jsp");
		   	 		}
			   	 	if ("david smith".equals(role)) {
		   	 			response.sendRedirect("davidSmith.jsp");
		   	 		} else {
		   	 			response.sendRedirect("login.jsp");
		   	 		}
	   	 	}	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    
	    private void registerClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String address = request.getParameter("address");
	        String creditCard = request.getParameter("creditCard");
	        String phoneNumber = request.getParameter("phoneNumber");
	        String email = request.getParameter("email");
	        client Client = new client(firstName, lastName, address, creditCard, phoneNumber, email);
	        
	        clientDAO.insert(Client);
	        System.out.println("Client Registration Successful!");
	        response.sendRedirect("clientportal.jsp");
	        
	    }
	    
	    private void registerQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String initialPrice = request.getParameter("initialPrice");
	        String timeWindow = request.getParameter("timeWindow");
	        String note = request.getParameter("note");

	        quote Quote = new quote(initialPrice, note, timeWindow);
	        
	        QuoteDAO.insert(Quote);
	        System.out.println("Quote Registration Successful!");
	        response.sendRedirect("treeRequest.jsp");
	    }
	    
	    private void registerTreeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String size = request.getParameter("size");
	        String height = request.getParameter("height");
	        String location = request.getParameter("location");
	        String nearHouse = request.getParameter("nearHourse");
	        String note = request.getParameter("note");
	        
	        treeRequest request1 = new treeRequest();
	        
	        treeRequestDAO.insert(request1);
	        System.out.println("Quote Registration Successful!");
	        response.sendRedirect("treeRequest.jsp");
	    }
	    
	    private void AgreeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	quote Quote = new quote();
	    	QuoteDAO.update(Quote);
	    }
	    
	    private void QuitServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	quote Quote = new quote();
	    	QuoteDAO.update(Quote);
	    }
	    
	    private void ResponseServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	quote Quote = new quote();
	    	QuoteDAO.update(Quote);
	    }


	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
    
}
	        
	        
	    
	        
	        
	        
	    


