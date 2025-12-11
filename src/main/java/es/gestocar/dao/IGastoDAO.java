package es.gestocar.dao;

import es.gestocar.beans.Gasto;
import java.util.List;

/**
 *
 * @author javier
 */
public interface IGastoDAO {

    /**
     *
     * @param gasto
     * @return
     */
    public Boolean add(Gasto gasto);
    /**
     * 
     * @param usuarioId
     * @return 
     */
    public Double getTotalGastosByUsuarioId(int usuarioId);
    /**
     * 
     * @param vehiculoId
     * @return 
     */
     public List<Gasto> getGastosByVehiculoId(int vehiculoId);
    /**
     * Abandona el hilo de la conexión a la base de datos
     */
     
     /**
      * 
      * 
      * @param idVehiculo
      * @param offset
      * @param limit
      * @return 
      */
       public List<Gasto> obtenerGastosPorVehiculoPaginados(int idVehiculo);
       
       /**
        * 
        * 
        */
    public void closeConnection();

}
