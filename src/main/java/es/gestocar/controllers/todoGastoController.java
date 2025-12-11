package es.gestocar.controllers;

import es.gestocar.dao.IGastoDAO;

import es.gestocar.daofactory.DAOFactory;
import java.io.IOException;

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
@WebServlet(name = "todoGastoController", urlPatterns = {"/todoGastoController"})
public class todoGastoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int usuarioId = (int) session.getAttribute("usuarioId");

        DAOFactory daof = DAOFactory.getDAOFactory();
        IGastoDAO gastoDAO = daof.getGastoDAO();

        Double totalGastos = gastoDAO.getTotalGastosByUsuarioId(usuarioId);  

        request.setAttribute("totalGastos", totalGastos);
        request.getRequestDispatcher("JSP/todoGastos.jsp").forward(request, response);
    }

}
