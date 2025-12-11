package es.gestocar.dao;

import es.gestocar.beans.Vehiculo;
import java.util.List;

/**
 *
 * @author javier
 */
public interface IVehiculoDAO {

    /**
     * Añade un vehiculo
     *
     * @param vehiculo
     * @return Objeto Boolean, TRUE si todo ha ido bien y FALSE en caso
     * contrario
     */
    public Boolean add(Vehiculo vehiculo);

    /**
     * Actualiza un vehiculo
     *
     * @param vehiculo
     * @return Objeto Boolean, TRUE si todo ha ido bien y FALSE en caso
     * contrario
     */
    public void update(Vehiculo vehiculo);

    /**
     *
     * @param usuarioId
     * @return
     */
    List<Vehiculo> getVehiculosByUsuarioId(int usuarioId);

    /**
     *
     *
     * @return
     */
    public int getLastInsertedId();

    
    /**
     * 
     * @param idVehiculo
     * @return 
     */
    public boolean deleteVehiculoID(int idVehiculo);
    /**
     * Elimina un vehiculo y sus fotos asociadas
     *
     * @param vehiculo
     */
    public void delete(int idVehiculo);
    
    /**
     * 
     * @return 
     */
    public List<Vehiculo> getVehiculos();
    /**
     * Abandona el hilo de la conexión a la base de datos
     */
    public void closeConnection();

}
