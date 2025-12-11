package es.gestocar.controllers;



import es.gestocar.beans.Usuario;
import es.gestocar.beans.Vehiculo;
import es.gestocar.dao.UsuarioDAO;
import es.gestocar.dao.VehiculoDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


/**
 *
 * autor javier
 */
@WebServlet(name = "administradorController", urlPatterns = {"/administradorController"})
public class administradorController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "JSP/accionesAdministrador.jsp";
        String boton = request.getParameter("boton");

        // Llenar las listas de usuarios y vehículos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        if ("eliminarUsuario".equals(boton)) {
            String idUsuarioStr = request.getParameter("registroUsuario");
            if (idUsuarioStr != null) {
                int idUsuario = Integer.parseInt(idUsuarioStr);
                boolean exito = usuarioDAO.updateUsuarioCampoBaja(idUsuario, true);

                if (exito) {
                    request.setAttribute("mensaje", "Usuario marcado como dado de baja correctamente");
                } else {
                    request.setAttribute("mensaje", "Error al marcar usuario como dado de baja");
                }
            } else {
                request.setAttribute("mensaje", "No se seleccionó ningún usuario");
            }
        } else if ("eliminarVehiculo".equals(boton)) {
            String idVehiculoStr = request.getParameter("registroVehiculo");
            
            System.out.println("Id vehiculo: " + idVehiculoStr );
            if (idVehiculoStr != null) {
                int idVehiculo = Integer.parseInt(idVehiculoStr);
                boolean exito = vehiculoDAO.deleteVehiculoID(idVehiculo);

                if (exito) {
                    request.setAttribute("mensaje", "Vehículo eliminado correctamente");
                } else {
                    request.setAttribute("mensaje", "Error al eliminar vehículo, tiene gastos asociados");
                }
            } else {
                request.setAttribute("mensaje", "Selecciona primero y después Elimina");
            }
        }

        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        request.setAttribute("usuario", usuarios);

        List<Vehiculo> vehiculos = vehiculoDAO.getVehiculos();
        request.setAttribute("vehiculo", vehiculos);

        request.getRequestDispatcher(url).forward(request, response);
        
    }
}