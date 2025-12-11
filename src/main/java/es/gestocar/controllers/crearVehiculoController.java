package es.gestocar.controllers;

import es.gestocar.beans.Vehiculo;
import es.gestocar.dao.IVehiculoDAO;
import es.gestocar.dao.MotorConverter;

import es.gestocar.daofactory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

@WebServlet(name = "crearVehiculoController", urlPatterns = {"/crearVehiculoController"})
public class crearVehiculoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int usuarioId = (int) session.getAttribute("usuarioId");

        String url = "JSP/usuario.jsp";
        Vehiculo vehiculo = new Vehiculo();
        Boolean error = false;

        if (request.getParameter("boton").equals("crearVehiculo")) {

            DAOFactory daof = DAOFactory.getDAOFactory();
            IVehiculoDAO adao = daof.getVehiculoDAO();
            Enumeration<String> campos = request.getParameterNames();
            // Registrar un convertidor para el tipo de motor
            ConvertUtils.register(new MotorConverter(), Vehiculo.Motor.class);
            // Registrar un convertidor de fecha para el formato esperado
            DateConverter converter = new DateConverter(null);
            converter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(converter, Date.class);

            try {
                BeanUtils.populate(vehiculo, request.getParameterMap());
                vehiculo.setUsuarioId(usuarioId); // Seteamos el id del usuario en el vehiculo
                if (!adao.add(vehiculo)) {
                    error = true;
                } else {
                    // Obtener el ID del vehículo recién creado
                    int vehiculoId = adao.getLastInsertedId(); 
                    session.setAttribute("vehiculoId", vehiculoId); // Establecer el id del vehículo en la sesión
                    request.setAttribute("vehiculoCreado", true);
                
                }
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(crearVehiculoController.class.getName()).log(Level.SEVERE, null, ex);
                error = true;
            }

        }
        request.getRequestDispatcher(url).forward(request, response);
    }
}
