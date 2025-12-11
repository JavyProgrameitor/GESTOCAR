package es.gestocar.controllers;

import es.gestocar.beans.Usuario;
import es.gestocar.dao.IUsuarioDAO;

import es.gestocar.daofactory.DAOFactory;
import es.gestocar.models.Utilidades;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author javier
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "JSP/login.jsp"; // Cambia a la página de login por defecto

        if (request.getParameter("boton").equals("crearCuenta")) {
            Usuario usuario = new Usuario();
            DAOFactory daof = DAOFactory.getDAOFactory();
            IUsuarioDAO udao = daof.getUsuarioDAO();
            Boolean error = false;
            Boolean dniError = false;

            Enumeration<String> campos = request.getParameterNames();
            // Comprobamos que todos los campos del formulario estén rellenos
            if (!Utilidades.isFormCompleto(campos, request)) {

                try {

                    // Validación del DNI
                    String dni = request.getParameter("dni");
                    if (dni.length() > 9) {
                        dniError = true;

                    } else {

                        BeanUtils.populate(usuario, request.getParameterMap());

                        // Aquí es donde encriptamos la contraseña con MD5
                        String password = request.getParameter("password");
                        String encryptedPassword = DigestUtils.md5Hex(password);
                        usuario.setPassword(encryptedPassword);

                    }

                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    error = true;
                }

                if (!error && !dniError) {
                    udao.add(usuario);
                    request.setAttribute("usuarioCreado", true);
                } else {
                    request.setAttribute("dniError", "El DNI no puede ser más de 9 caracteres.");
                }
            }

        }
        request.getRequestDispatcher(url).forward(request, response);

    }

}
