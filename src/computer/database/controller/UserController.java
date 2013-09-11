package computer.database.controller;

import java.io.IOException;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        userService = ServiceManager.INSTANCE.getUserService();
    }

	/**
	 * La methode doGet est executee lorsqu'un client execute l'URI UserServlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Envoyer un objet dans la requete (ici la liste d'utilisateurs)
		request.setAttribute("users", userService.getUsers());
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(response.encodeURL("/display.jsp"));
		rd.forward(request, response);
	}

	/**
	 * La methode doPost est executee lorsqu'un client poste des informations (en general formulaire) sur l'URI UserServlet
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		//Test de validite des champs login et password
		if(login != null && !login.isEmpty() && password != null && !password.isEmpty())
			userService.create(new User.Builder().login(login).password(password).build());
		
		//Redirection vers la page
		response.sendRedirect(response.encodeURL("UserServlet"));
	}

}
