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

import computer.database.domain.Company;
import computer.database.domain.Machine;
import computer.database.service.DatabaseService;
import computer.database.service.manager.ServiceManager;

/**
 * 
 * @author Kim & Florian
 * @version 1
 * 
 *          Controller pour editer des informations d'un ordinateur selectionne
 * 
 */
@WebServlet("/editComputer.aspx")
public class EditComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DatabaseService databaseService;
	private String machineRequest = null;

	public EditComputer() {
		super();
		databaseService = ServiceManager.INSTANCE.getMachineService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Envoyer un objet dans la requete (ici la liste des compagnies)
		request.setAttribute("companies", databaseService.getCompanies());

		// Recuperation de la machine
		machineRequest = request.getParameter("id");
		if (machineRequest != null && !machineRequest.trim().isEmpty()) {
			long machineId = Long.parseLong(machineRequest);
			Machine machine = databaseService.getMachine(machineId);
			request.setAttribute("machine", machine);
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/editComputer.jsp"));
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		long machineId = 0;
		if (machineRequest != null && !machineRequest.trim().isEmpty()) {
			machineId = Long.parseLong(machineRequest);

			if (action != null && action.equals("Delete"))
				databaseService.deleteMachine(machineId);
			else {// Recuperation et validation des champs du formulaire
					// d'edition
				String name = request.getParameter("name");
				// Si le nom est vide, revenir a la page d'ajout
				if (name.trim().length() == 0)
					response.sendRedirect(response
							.encodeURL("editComputer.aspx"));
				else {
					Machine.Builder builder = new Machine.Builder().id(
							machineId).name(name);

					// Dates introduced et discontinued
					String introducedString = request
							.getParameter("introducedDate");
					String discontinuedString = request
							.getParameter("discontinuedDate");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date introducedUtil = null;
					Date discontinuedUtil = null;
					try {
						// Conversion String to Date
						if (!introducedString.isEmpty())
							introducedUtil = df.parse(introducedString);
						if (!discontinuedString.isEmpty())
							discontinuedUtil = df.parse(discontinuedString);
					} catch (ParseException e) {
						response.sendRedirect(response
								.encodeURL("addComputer.aspx"));
						e.printStackTrace();
					}

					if (introducedUtil != null && !introducedString.isEmpty())
						builder.introduced(introducedUtil);
					if (discontinuedUtil != null
							&& !discontinuedString.isEmpty())
						builder.discontinued(discontinuedUtil);

					// Compagnie
					Company company;
					long company_id = Long.parseLong(request
							.getParameter("company"));
					// Si une compagnie est selectionnee: la recuperer a partir
					// de son id
					if (company_id != 0) {
						company = databaseService.getCompany(company_id);
						builder.company(company);
					}
					// Creation et MAJ d'une machine
					Machine machine = builder.build();
					databaseService.editMachine(machine);
				}
			}
			// Redirection vers la page
			response.sendRedirect(response.encodeURL("computerList.aspx"));
		}
	}
}
