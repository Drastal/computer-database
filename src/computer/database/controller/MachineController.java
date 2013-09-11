package computer.database.controller;

import java.io.IOException;

import computer.database.service.MachineService;
import computer.database.service.manager.ServiceManager;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/MachineServlet")
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
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/display.jsp"));
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String login = request.getParameter("login");
//		String password = request.getParameter("password");
//		
//		//Test de validite des champs login et password
//		if(login != null && !login.isEmpty() && password != null && !password.isEmpty())
//			machineService.create(new Machine.Builder().name(name).password(password).build());
		
		//Redirection vers la page
		response.sendRedirect(response.encodeURL("MachineServlet"));
	}

}
