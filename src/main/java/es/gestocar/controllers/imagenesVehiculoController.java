package es.gestocar.controllers;

import es.gestocar.beans.Foto;
import es.gestocar.beans.Vehiculo;

import es.gestocar.dao.IFotoDAO;
import es.gestocar.dao.IVehiculoDAO;
import es.gestocar.daofactory.DAOFactory;
import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


    @MultipartConfig
@WebServlet(name = "imagenesVehiculoController", urlPatterns = {"/imagenesVehiculoController"})
public class imagenesVehiculoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        int usuarioId = (int) session.getAttribute("usuarioId");
        String action = request.getParameter("boton");

        DAOFactory daof = DAOFactory.getDAOFactory();

        IVehiculoDAO vehiculoDAO = daof.getVehiculoDAO();
        IFotoDAO fotoDAO = daof.getFotoDAO();

        if ("subirImagenes".equals(action)) {
            List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosByUsuarioId(usuarioId);
            request.setAttribute("vehiculos", vehiculos);
            request.getRequestDispatcher("JSP/subirImagenes.jsp").forward(request, response);

        } else if ("subir".equals(action)) {

            String idVehiculoStr = request.getParameter("seleccion");

            if (idVehiculoStr == null || idVehiculoStr.isEmpty()) {
                // No se ha seleccionado ningún vehículo atributo para modal
                request.setAttribute("noSeleccion", true);
                //Listamos todos los vehículos
                List<Vehiculo> vehiculos = vehiculoDAO.getVehiculosByUsuarioId(usuarioId);
                request.setAttribute("vehiculos", vehiculos);
                request.getRequestDispatcher("JSP/subirImagenes.jsp").forward(request, response);
                //Volvemos si no se selecciona ningún vehiculo
                return;
            }

            int idVehiculo = Integer.parseInt(idVehiculoStr);

            // Directorio donde se guardan las fotos
            String rutaRelativa = "/IMAGENES";
            String rutaDirecta = getServletContext().getRealPath(rutaRelativa);
            File uploadDir = new File(rutaDirecta);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            Part filePart = request.getPart("imgCar");

            if (filePart != null && filePart.getSize() > 0 && filePart.getSize() <= 307200) {
                String extension = getFileExtension(filePart);
                if (!extension.isEmpty() && (extension.equals("png") || extension.equals("jpg"))) {

                    Foto foto = new Foto();
                    foto.setIdVehiculo(idVehiculo);
                    foto.setFoto(""); // Necesario para la inserción inicial

                    // Insertar la foto y obtener su ID
                    int idFoto = fotoDAO.insertAndGetId(foto);
                    System.out.println("El id de foto es:" + idFoto);

                    if (idFoto != -1) {
                        // Construir el nombre del archivo de la foto con el ID y la extensión
                        String fileName = "foto" + idFoto + "." + extension;
                        String filePath = Paths.get(rutaDirecta, fileName).toString();
                        System.out.println("Guardando archivo en: " + filePath);
                        filePart.write(filePath);

                        // Actualizar el nombre del archivo en la base de datos
                        foto.setFoto(fileName);
                        foto.setIdFoto(idFoto); // Actualizamos el ID de la foto
                        fotoDAO.update(foto);
                    } else {
                        System.out.println("No se pudo obtener el ID de la foto.");
                    }
                }
            }

            // Obtener la lista de fotos para el vehículo seleccionado
            List<Foto> fotos = fotoDAO.getFotosByVehiculoId(idVehiculo);
            request.setAttribute("fotos", fotos);
            request.getRequestDispatcher("JSP/verImagenes.jsp").forward(request, response);
        }
    }

    private String getFileExtension(Part filePart) {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot > 0 && lastDot < fileName.length() - 1) {
            return fileName.substring(lastDot + 1);
        }
        return "";
    }
}

