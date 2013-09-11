package computer.database.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import computer.database.service.DatabaseService;
import computer.database.service.manager.ServiceManager;



/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/addComputer.aspx")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DatabaseService databaseService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
        databaseService = ServiceManager.INSTANCE.getMachineService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (ici la liste d'utilisateurs)
		request.setAttribute("companies", databaseService.getCompanies());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/addComputer.jsp"));
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doPost");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name");
		Date introducedUtil;
		Date discontinuedUtil;
		try {
			introducedUtil = df.parse(request.getParameter("introducedDate"));
			discontinuedUtil = df.parse(request.getParameter("discontinuedDate"));
		} catch (ParseException e) {
			introducedUtil=new Date();
			discontinuedUtil=new Date();// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(introducedUtil);
		System.out.println(discontinuedUtil);
		java.sql.Date discontinuedSql =  new java.sql.Date(discontinuedUtil.getTime());
		java.sql.Date introducedSql =  new java.sql.Date(introducedUtil.getTime());
		System.out.println(introducedSql);
		System.out.println(discontinuedSql);
//		int company_id = Integer.parseInt(request.getParameter("company"));
//		
		
		//Test de validite des champs du formulaire d'ajout
//		if(name != null && !name.isEmpty() && introducedSql != null && discontinuedSql != null)
//			machineService.create(new Machine.Builder().name(name).introduced(introducedSql)
//					.discontinued(discontinuedSql).company(company_id).build());
//		
		//Redirection vers la page
//		response.sendRedirect(response.encodeURL("addComputer.aspx"));
	}

}
