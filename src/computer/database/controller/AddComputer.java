package computer.database.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import computer.database.domain.Company;
import computer.database.domain.Machine;
import computer.database.service.DatabaseService;
import computer.database.service.manager.ServiceManager;

/**
 * 
 * @author Kim & Florian
 * @version 1
 * 
 *          Controlleur pour ajouter des ordinateurs
 * 
 */
@WebServlet("/addComputer.aspx")
public class AddComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DatabaseService databaseService;

	public AddComputer() {
		super();
		databaseService = ServiceManager.INSTANCE.getMachineService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Envoyer un objet dans la requete (ici la liste des compagnies)
		request.setAttribute("companies", databaseService.getCompanies());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addComputer.jsp"));
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Recuperation et validation des champs du formulaire d'ajout

		String name = request.getParameter("name");
		// Si le nom est vide, revenir a la page d'ajout
		if (name.trim().length() == 0) {
			response.sendRedirect(response.encodeURL("addComputer.aspx"));
		} else {
			Machine.Builder builder = new Machine.Builder().name(name);

			// Dates introduced et discontinued
			String introducedString = request.getParameter("introducedDate");
			String discontinuedString = request.getParameter("discontinuedDate");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			Date introducedUtil = null;
			Date discontinuedUtil = null;
			try {
				// Conversion String to Date si la date n'est pas vide
				if (!introducedString.isEmpty() && estValide(introducedString)) {
					introducedUtil = df.parse(introducedString);
				}
				if (!discontinuedString.isEmpty() && estValide(discontinuedString)) {
					discontinuedUtil = df.parse(discontinuedString);
				}
			} catch (ParseException e) {
				response.sendRedirect(response.encodeURL("addComputer.aspx"));
				e.printStackTrace();
			}

			// Si la date introduced n'est ni nulle, ni vide, l'ajouter au builder
			if (introducedUtil != null && !introducedString.isEmpty()) {
				builder.introduced(introducedUtil);
			}
			// Si la date discontinued n'est ni nulle, ni vide, l'ajouter au builder
			if (discontinuedUtil != null && !discontinuedString.isEmpty()) {
				builder.discontinued(discontinuedUtil);
			}

			// Compagnie
			Company company;
			long company_id = Long.parseLong(request.getParameter("company"));
			// Si une compagnie est selectionnee: la recuperer a partir de son
			// id
			if (company_id != 0) {
				company = databaseService.getCompany(company_id);
				builder.company(company);
			}
			// Creation et ajout d'une machine
			Machine machine = builder.build();
			databaseService.create(machine);

			// Redirection vers la page
			response.sendRedirect(response.encodeURL("computerList.aspx"));
		}
	}
	
	public boolean estValide(String date){
		//Verifie si la date appartient au calendrier
		//Verification supplementaire cote serveur (JS desactive)
		Calendar c = Calendar.getInstance();
		c.setLenient(false);
		String dateTab[] = date.split("-");
		c.set(Integer.parseInt(dateTab[0]),Integer.parseInt(dateTab[1]),Integer.parseInt(dateTab[2]));        
		try{
		  c.getTime();  
		}
		catch(IllegalArgumentException iAE){
		  return false;
		}
		return true;
	}
}
