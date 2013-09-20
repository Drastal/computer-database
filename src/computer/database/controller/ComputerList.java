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
 * 
 * @author Kim & Florian
 * @version 1
 * 
 *          Controller pour la liste des ordinateurs, page principale du site
 *          Affiche un tableau des ordinateurs, permet de faire une recherche
 * 
 */
@WebServlet("/computerList.aspx")
public class ComputerList extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int resultPerPage = 50;// Nombre de resultats par page par defaut
	private int pageNumber = 1;// Numero de page courante
	private int totalPage = 1;// Nombre total de pages
	private String searching = null;
	private String sortType = null;
	private boolean ascending = true;

	private DatabaseService machineService;

	/**
	 * Recupere la liste des ordinateurs
	 */
	public ComputerList() {
		super();
		machineService = ServiceManager.INSTANCE.getMachineService();
	}

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI ComputerList
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Recupere le nombre de resultats par page voulu
		if (request.getParameter("resultsNb") != null)
			resultPerPage = Integer.parseInt(request.getParameter("resultsNb"));

		// S'il y a demande de recherche ou changement du nombre de resultats par page
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("Validate") || action.equals("Search"))
				pageNumber = 1;// Revient a la page 1 le cas echeant
			if (action != null && action.equals("Search"))
				// Recupere le mot a rechercher
				searching = request.getParameter("search");
		} else
			// Recupere le numero de page courante
			if (request.getParameter("page") != null)
				pageNumber = Integer.parseInt(request.getParameter("page"));
		
		// Recupere la demande de tri si existe
		if (request.getParameter("sort") != null){
			sortType = request.getParameter("sort");
			if(request.getParameter("asc").equals("true"))
				ascending = true;
			else
				ascending = false;
			pageNumber = 1;// Revient a la page 1 le cas echeant
		}

		request.setAttribute("resultsQuantity", resultPerPage);
		request.setAttribute("pageNumber", pageNumber);

		// Taille de la liste de machines
		int sizeList = machineService.getMachines(searching).size();
		request.setAttribute("sizeList", sizeList);
		totalPage = (int) Math.ceil((double) sizeList / resultPerPage);
		request.setAttribute("totalPage", totalPage);
		/*
		 * Affiche la liste des ordinateurs en prenant en compte la recherche,
		 * le nombre de resultats par page et le numero de page courante
		 */
		request.setAttribute("machines", machineService
				.getMachines(searching, resultPerPage, pageNumber, sortType, ascending));

		RequestDispatcher rd = getServletContext()
				.getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations
	 * (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
