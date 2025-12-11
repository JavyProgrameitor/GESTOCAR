
package es.gestocar.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author javier
 */
public class Utilidades {
    
    
        /**
     * Convierte un objeto java.util.Date en un objeto java.sql.Date
     * @param fecha Objeto java.util.Date
     * @return Objeto java.sql.Date
     */
    public static java.sql.Date utilDateToSqlDate(java.util.Date fecha) {
        return new java.sql.Date(fecha.getTime());
    }

    
    
    /**
     * Elimina los atributos de sesión
     * @param request Objeto HttpServletRequest necesario para eliminar los atributos
     */
    public static void limpiarSesion(HttpServletRequest request) {
        
        if (request.getSession().getAttribute("usuario") != null) {
            request.getSession().removeAttribute("usuario");
        }
        if (request.getSession().getAttribute("fotosEliminar") != null) {
            request.getSession().removeAttribute("fotosEliminar");
        }
        if (request.getSession().getAttribute("vehiculos") != null) {
            request.getSession().removeAttribute("vehiculos");
        }
    }
    
    /**
     * Convierte un String en un array de bytes necesario para almacenar una imagen en binario
     * @param imageName String que recibe
     * @return Array de bytes que representan la imagen
     * @throws IOException Excepción que puede lanzar
     */
    public static byte[] extractBytes(String imageName) throws IOException {
        
        File imgPath = new File(imageName);

        FileInputStream myStream = new FileInputStream(imgPath);

        return IOUtils.toByteArray(myStream);
    }
    
        /**
     * Comprueba que todos los campos del formulario estén rellenos
     * @param campos Enumeration con los nombre de los controles del formulario
     * @param request Objeto HttpServletRequest necesario para obtener el valor del campo
     * @return Objeto Boolean, TRUE si existe algún error y FALSE en caso contrario
     */
    public static Boolean isFormCompleto(Enumeration<String> campos, HttpServletRequest request) {
        Boolean error = Boolean.FALSE;
        while (campos.hasMoreElements() && !error) {
            String nombre = campos.nextElement();
            if (request.getParameter(nombre).length() == 0) {
                error = Boolean.TRUE;
            }
        }
        return error;
    }
    
    
}
