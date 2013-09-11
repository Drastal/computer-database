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

import computer.database.service.MachineService;
import computer.database.service.manager.ServiceManager;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/addComputer.aspx")
public class MachineController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MachineService machineService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MachineController() {
        super();
        machineService = ServiceManager.INSTANCE.getMachineService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (ici la liste d'utilisateurs)
		request.setAttribute("machines", machineService.getMachines());
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/dashboard.jsp"));// Ajout Web-inf ?
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name");
		try {
			Date introduced = df.parse(request.getParameter("introducedDate"));
			Date discontinued = df.parse(request.getParameter("discontinuedDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int company_id = Integer.parseInt(request.getParameter("company"));
		System.out.println("in doPost");
		
		//Test de validite des champs du formulaire d'ajout
//		if(name != null && !name.isEmpty() && introduced != null && discontinued != null)
//			machineService.create(new Machine.Builder().name(name).introduced(introduced)
//					.discontinued(discontinued).company(company_id).build());
//		
		//Redirection vers la page
		response.sendRedirect(response.encodeURL("addComputer.aspx"));
	}

}
