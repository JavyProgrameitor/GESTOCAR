package es.gestocar.controllers;

import es.gestocar.beans.Gasto;
import es.gestocar.beans.Vehiculo;

import es.gestocar.dao.IGastoDAO;
import es.gestocar.dao.IVehiculoDAO;

import es.gestocar.daofactory.DAOFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 *
 * @author javier
 */
@WebServlet(name = "gastoController", urlPatterns = {"/gastoController"})
public class gastoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "JSP/usuario.jsp";
        HttpSession session = request.getSession();

        int usuarioId = (int) session.getAttribute("usuarioId");
        String action = request.getParameter("boton");

        DAOFactory daof = DAOFactory.getDAOFactory();
        IVehiculoDAO vehiculoDAO = daof.getVehiculoDAO();

        if ("insertarGasto".equals(action)) {
            List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosByUsuarioId(usuarioId);
            request.setAttribute("vehiculos", vehiculos);
            request.getRequestDispatcher("JSP/listarGasto.jsp").forward(request, response);
        } else if ("listarGasto".equals(action)) {

            int idvehiculo = Integer.parseInt(request.getParameter("idvehiculo"));
            request.setAttribute("idvehiculo", idvehiculo); // Pasar el id del vehículo a la siguiente página
            request.getRequestDispatcher("JSP/crearGasto.jsp").forward(request, response);
        } else if ("crearGasto".equals(action)) {

            Gasto gasto = new Gasto();
            Boolean error = false;

            IGastoDAO adao = daof.getGastoDAO();

            // Registrar un convertidor de fecha para el formato esperado
            DateConverter converter = new DateConverter(null);
            converter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(converter, Date.class);

            // Validar idvehiculo
            String idVehiculoStr = request.getParameter("idvehiculo");
            if (idVehiculoStr == null || idVehiculoStr.isEmpty()) {
                // Manejo del error, por ejemplo, redirigir de nuevo a la página anterior con un mensaje de error
                // Vuelvo a mostrar la lista porque pierdo el id . Lo intenté poner en session pero imposible....
                List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosByUsuarioId(usuarioId);
                request.setAttribute("vehiculos", vehiculos);
                request.getRequestDispatcher("JSP/listarGasto.jsp").forward(request, response);
                return; // Importante salir del método para evitar continuar con el proceso
            }

            try {

                int idvehiculo = Integer.parseInt(idVehiculoStr);
                BeanUtils.populate(gasto, request.getParameterMap());
                gasto.setIdVehiculo(idvehiculo);

                // Validar campos obligatorios
                if (gasto.getConcepto() == null || gasto.getConcepto().isEmpty()
                        || gasto.getFechaGasto() == null
                        || gasto.getDescripcion() == null || gasto.getDescripcion().isEmpty()
                        || gasto.getImporte() == 0
                        || gasto.getEstablecimiento() == null || gasto.getEstablecimiento().isEmpty()
                        || gasto.getKilometros() == null || gasto.getKilometros().isEmpty()) {
                    error = true;
                } else if (!adao.add(gasto)) {

                    error = true;
                }
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(gastoController.class.getName()).log(Level.SEVERE, null, ex);
                error = true;
            }

            if (error) {

                url = "JSP/crearGasto.jsp";  // Volver a la misma página en caso de error
            } else {
                request.setAttribute("gastoCreado", true);
                url = "JSP/crearGasto.jsp";
            }

            request.getRequestDispatcher(url).forward(request, response);
        }
    }
}
