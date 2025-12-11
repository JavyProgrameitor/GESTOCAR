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
@WebServlet(name = "ajaxGastoController", urlPatterns = {"/ajaxGastoController"})
public class ajaxGastoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {

            String tabla = request.getParameter("tabla");
            String opcion = request.getParameter("accion");

            switch (opcion) {

                case "tabla":
                    HttpSession session = request.getSession();

                    int usuarioId = (int) session.getAttribute("usuarioId");
                    String action = request.getParameter("boton");

                    DAOFactory daof = DAOFactory.getDAOFactory();
                    IVehiculoDAO vehiculoDAO = daof.getVehiculoDAO();

                    List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosByUsuarioId(usuarioId);
                    for (Vehiculo vehiculo : vehiculos) {
                        System.out.println("Vehiculo ID: " + vehiculo.getIdVehiculo());

                        request.setAttribute("vehiculos", vehiculos);

                        request.getRequestDispatcher("JSP/listaGastosAjax.jsp").forward(request, response);
                        break;
                    }
            }
            //Fin de case........
            }else {
            request.getRequestDispatcher("JSP/listaGastosAjax.jsp").forward(request, response);
        }
        }

    }
