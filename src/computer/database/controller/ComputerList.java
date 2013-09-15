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
 * Controlleur pour la liste des ordinateurs, page principale du site
 * Affiche un tableau des ordinateurs, permet de faire une recherche
 */
@WebServlet("/computerList.aspx")
public class ComputerList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int resultPerPage = 50;//Nombre de résultat par page, par défaut
	private int pageNumber = 1;//Numéro de page courante
	private int totalPage = 1;//Nombre total de pages

	private DatabaseService machineService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerList() {
        super();
        machineService = ServiceManager.INSTANCE.getMachineService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI ComputerList
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (ici la liste d'utilisateurs)
		if(request.getParameter("resultsNb")!=null)
			resultPerPage = Integer.parseInt(request.getParameter("resultsNb"));
		if(request.getParameter("pageNumber")!=null)
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		request.setAttribute("resultsQuantity", resultPerPage);
		String searching = request.getParameter("search");
		
		if(searching==null||searching.trim().isEmpty()){
			request.setAttribute("machines", machineService.getMachines(resultPerPage, pageNumber));
			request.setAttribute("sizeList", machineService.getMachines().size());
			totalPage = (int)Math.ceil((double)machineService.getMachines().size() / resultPerPage);
			request.setAttribute("totalPage", totalPage);
		} else {
			request.setAttribute("machines", machineService.getMachines(searching, resultPerPage, pageNumber));
			request.setAttribute("sizeList", machineService.getMachines(searching).size());
			totalPage = (int)Math.ceil((double)machineService.getMachines(searching).size() / resultPerPage);
			request.setAttribute("totalPage", totalPage);
		}
		
		//A suppr après validation pagination
		System.out.println(pageNumber);
		System.out.println(totalPage);
		System.out.println(resultPerPage);
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/WEB-INF/dashboard.jsp"));
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
