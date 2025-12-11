package es.gestocar.controllers;

import es.gestocar.beans.Usuario;
import es.gestocar.dao.UsuarioDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author javier
 */
@WebServlet(name = "LogeadoController", urlPatterns = {"/LogeadoController"})
public class LogeadoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String url = "index.jsp";
        if ("Iniciar".equals(request.getParameter("boton"))) {

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = null;

            // Comprobación para el administrador
            if ("admin@iesalbarregas.es".equals(email) && "admin".equals(password)) {
                url = "JSP/administrador.jsp";
            } else {
                // Comprobación para usuario normal
                usuario = usuarioDAO.authenticate(email, DigestUtils.md5Hex(password));

                if (usuario != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioId", usuario.getIdUsuario());
                    session.setAttribute("usuario", usuario);
                    url = "JSP/usuario.jsp";
                } else {
                    url = "JSP/logeado.jsp";
                }
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

}
