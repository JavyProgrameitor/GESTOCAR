package es.gestocar.controllers;

import es.gestocar.beans.Vehiculo;
import es.gestocar.dao.IVehiculoDAO;
import es.gestocar.daofactory.DAOFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javier
 */
@WebServlet(name = "visualizarVehiculoController", urlPatterns = {"/visualizarVehiculoController"})
public class visualizarVehiculoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int usuarioId = (int) session.getAttribute("usuarioId");

        String url = "JSP/verVehiculos.jsp";

        if (request.getParameter("boton").equals("visualizarVehiculo")) {
            DAOFactory daof = DAOFactory.getDAOFactory();
            IVehiculoDAO adao = daof.getVehiculoDAO();

            List<Vehiculo> vehiculos = adao.getVehiculosByUsuarioId(usuarioId);

            request.setAttribute("vehiculos", vehiculos);
        }

        request.getRequestDispatcher(url).forward(request, response);
    }
}
