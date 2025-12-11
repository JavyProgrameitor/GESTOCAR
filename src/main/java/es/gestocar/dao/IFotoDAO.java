package es.gestocar.dao;

import es.gestocar.beans.Foto;
import es.gestocar.beans.Vehiculo;
import java.util.List;

/**
 *
 * @author javier
 */
public interface IFotoDAO {

    /**
     * Obtiene las fotos cuyo id está dentro de un rango de valores
     *
     * @param registros Valores de los diferentes id que se pretenden obtener
     * @return Lista de objetos fotos
     */
    public List<Foto> getFotosByVehiculoId(int idVehiculo);

    
    /**
     * 
     * @param foto
     * @return id de foto
     */
    public int insertAndGetId(Foto foto);
    /**
     *
     * @param foto
     */
 
    public void update(Foto foto);
       /**
     * 
     * 
     * @param foto 
     */
    public void delete(Foto foto);

    /**
     * Abandona el hilo de la conexión a la base de datos
     */
    public void closeConnection();

}
