package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.DriverDAO;
import entities.Driver;

/**
 * Servlet implementation class TestJPA
 */
@WebServlet("/TestJPA")
public class TestJPA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestJPA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.JPA);
		DriverDAO drDAO = factory.getDriverDAO();
		Driver driver = new Driver();
		driver.setFullName("Vasya Petrov");
		driver.setCitizenship(entities.Citizenship.Russia);
		driver.setPassportCode("AS1095A");
		driver.setStartDate(new Date(System.nanoTime()));
		drDAO.create(driver);
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Заголовок</title>");
	            out.println("</head>");
	            out.println("<body>");
	            Driver driv = drDAO.find(3);
	            out.println("findByID " + driv.getIdDriver() + "</br>");
	            out.println("findByID " + driv.getFullName() + "</br>");
	            out.println("findByID " + driv.getPassportCode() + "</br>");
	            out.println("findByID " + driv.getCitizenship() + "</br>");
	            out.println("findByID " + driv.getStartDate() + "</br>");
	            driv.setFullName("aaaa");
	            drDAO.update(driv);
	            out.println("</br>");
	            out.println("Пример сервлета"+drDAO.findAll());
	            out.println("</br>");
	            out.println("</br>");
	            out.println("FindDriverByName" + drDAO.findByName("Vasya Petrov"));
	            out.println("</body>");
	            out.println("</html>");
	        } finally {
	            out.close();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
