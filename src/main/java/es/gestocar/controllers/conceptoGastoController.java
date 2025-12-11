package es.gestocar.controllers;

import com.google.gson.Gson;
import es.gestocar.beans.Gasto;
import es.gestocar.dao.GastoDAO;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author javier
 */
@WebServlet(name = "conceptoGastoController", urlPatterns = {"/conceptoGastoController"})
public class conceptoGastoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idVehiculo = Integer.parseInt(request.getParameter("idVehiculo"));

        GastoDAO gastoDAO = new GastoDAO();
        List<Gasto> listaGastos = gastoDAO.obtenerGastosPorVehiculoPaginados(idVehiculo);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(listaGastos);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
