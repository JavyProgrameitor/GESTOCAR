package es.gestocar.controllers;

import es.gestocar.beans.Vehiculo;
import es.gestocar.dao.IVehiculoDAO;
import es.gestocar.dao.MotorConverter;
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
@WebServlet(name = "modificarVehiculoController", urlPatterns = {"/modificarVehiculoController"})
public class modificarVehiculoController extends HttpServlet {

    // Registro de convertidores en un bloque estático
    static {
        ConvertUtils.register(new MotorConverter(), Vehiculo.Motor.class);
        DateConverter converter = new DateConverter(null);
        converter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(converter, Date.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        int usuarioId = (int) session.getAttribute("usuarioId");
        String action = request.getParameter("boton");

        DAOFactory daof = DAOFactory.getDAOFactory();
        IVehiculoDAO vehiculoDAO = daof.getVehiculoDAO();

        if ("modificarVehiculo".equals(action)) {
            List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosByUsuarioId(usuarioId);

            request.setAttribute("vehiculos", vehiculos);
            request.getRequestDispatcher("JSP/modificarVehiculos.jsp").forward(request, response);

        } else if ("modificar".equals(action)) {

            String registroParam = request.getParameter("registro");
            int vehiculoId = Integer.parseInt(registroParam);
            session.setAttribute("idVehiculo", vehiculoId); // Establecer el idVehiculo en la sesión

            /* Cargar los datos del vehículo
            Vehiculo vehiculo = vehiculoDAO.getVehiculoById(vehiculoId);
            request.setAttribute("vehiculo", vehiculo);
             */
            request.getRequestDispatcher("JSP/editarVehiculo.jsp").forward(request, response);

        } else if ("guardar".equals(action)) {
            try {
                Vehiculo vehiculo = new Vehiculo();
                int idVehiculo = (int) session.getAttribute("idVehiculo");  //
                boolean modificacionExitosa = false;

                vehiculo.setIdVehiculo(idVehiculo);
                BeanUtils.populate(vehiculo, request.getParameterMap());

                vehiculo.setUsuarioId(usuarioId); // Seteamos el id del usuario en el vehiculo
                vehiculoDAO.update(vehiculo);

                modificacionExitosa = true;

                // Refrescar la lista de vehículos y volver a mostrar la página de modificar
                List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosByUsuarioId(usuarioId);
                request.setAttribute("vehiculos", vehiculos);
                request.setAttribute("modificacionExitosa", modificacionExitosa);
                request.getRequestDispatcher("JSP/editarVehiculo.jsp").forward(request, response);

            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(modificarVehiculoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
