package computer.database.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import computer.database.dao.manager.DaoManager;
import computer.database.domain.Company;
import computer.database.domain.Machine;
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
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/addComputer.jsp"));
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des champs du formulaire d'ajout
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name");
		Date introducedUtil=null;
		Date discontinuedUtil=null;
		try {
			// Conversion String to Date
			introducedUtil = df.parse(request.getParameter("introducedDate"));
			discontinuedUtil = df.parse(request.getParameter("discontinuedDate"));
		} catch (ParseException e) {
			introducedUtil=new Date();
			discontinuedUtil=new Date();
			e.printStackTrace();
		}
		long company_id = Long.parseLong(request.getParameter("company"));
		
		// Récupération de la compagnie à partir de l'id
		Company company = databaseService.getCompany(company_id);
		
//		System.out.println(name);
//		System.out.println(introducedSql);
//		System.out.println(discontinuedSql);
//		System.out.println(company_id);
//		System.out.println(company.getName());
		
		//Test de validite des champs du formulaire d'ajout
		if(name.length()!=0 && introducedUtil!=null && discontinuedUtil!=null && company!=null)
			databaseService.create(new Machine.Builder().name(name).introduced(introducedUtil)
					.discontinued(discontinuedUtil).company(company).build());
		
		//Redirection vers la page
		response.sendRedirect(response.encodeURL("addComputer.aspx"));
	}

}
