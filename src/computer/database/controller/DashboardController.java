package computer.database.controller;

import java.io.IOException;

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
@WebServlet("/computerList.aspx")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int resultPerPage = 50;
	private int totalPage = 1;

	private DatabaseService machineService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        machineService = ServiceManager.INSTANCE.getMachineService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (ici la liste d'utilisateurs)
		if(request.getParameter("resultsNb")!=null)
			resultPerPage = Integer.parseInt(request.getParameter("resultsNb"));
		//int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		request.setAttribute("resultsQuantity", resultPerPage);
		String searching = request.getParameter("search");
		if(searching==null||searching.trim().isEmpty()){
			request.setAttribute("machines", machineService.getMachines());
			request.setAttribute("sizeList", machineService.getMachines().size());
			totalPage = (int)Math.ceil((double)machineService.getMachines().size() / resultPerPage);
		} else {
			request.setAttribute("machines", machineService.getMachines(searching));
			request.setAttribute("sizeList", machineService.getMachines(searching).size());
			totalPage = (int)Math.ceil((double)machineService.getMachines(searching).size() / resultPerPage);
		}
		System.out.println(totalPage);
		System.out.println(resultPerPage);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));// Ajout Web-inf ?
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Redirection vers la page
//		response.sendRedirect(response.encodeURL("computerList.aspx"));
	}

}
