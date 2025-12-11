package es.gestocar.controllers;

import es.gestocar.beans.Gasto;
import es.gestocar.beans.Vehiculo;
import es.gestocar.dao.IGastoDAO;
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
@WebServlet(name = "visualizarGastoController", urlPatterns = {"/visualizarGastoController"})
public class visualizarGastoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int usuarioId = (int) session.getAttribute("usuarioId");

        String url = "JSP/verGastosVehiculos.jsp";

        // Verificar si se pulsó el botón desde verGastosVehiculos.jsp
        if ("visualizarGasto".equals(request.getParameter("boton"))) {
            DAOFactory daof = DAOFactory.getDAOFactory();
            IVehiculoDAO adao = daof.getVehiculoDAO();

            List<Vehiculo> vehiculos = adao.getVehiculosByUsuarioId(usuarioId);

            request.setAttribute("vehiculo", vehiculos);
        }

        // Verificar si se pulsó el botón desde verGastos.jsp
        if ("visualizarGastoVehiculo".equals(request.getParameter("boton"))) {
            String vehiculoIdStr = request.getParameter("registroVehiculo");
            if (vehiculoIdStr == null || vehiculoIdStr.isEmpty()) {
                request.setAttribute("llevasID", true);
            } else {
                int vehiculoId = Integer.parseInt(vehiculoIdStr);
                System.out.println("ID del vehiculo : " + vehiculoId);  //El id es recibido

                session.setAttribute("idVehiculo", vehiculoId); // Establecer el idVehiculo en la sesión

                DAOFactory daof = DAOFactory.getDAOFactory();
                IGastoDAO gastoDAO = daof.getGastoDAO();

                List<Gasto> gastos = gastoDAO.getGastosByVehiculoId(vehiculoId);

                // Verificar si gastos es null antes de agregarlo al request
                if (gastos != null) {
                    request.setAttribute("gastos", gastos);
                    url = "JSP/verGastos.jsp";
                } else {
                    // Si gastos es null, podrías redirigir o mostrar un mensaje de error
                    request.setAttribute("error", "No se encontraron gastos para el vehículo seleccionado.");
                }
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
    }
}
