
package es.gestocar.daofactory;

import es.gestocar.dao.IFotoDAO;
import es.gestocar.dao.IGastoDAO;
import es.gestocar.dao.IUsuarioDAO;
import es.gestocar.dao.IVehiculoDAO;

/**
 *
 * @author javier
 */
public abstract class DAOFactory {
    
    
      /**
     * Objeto DAO de Usuario
     * @return interface de dicho objeto DAO
     */
    public abstract IUsuarioDAO getUsuarioDAO();
    /**
     * Objeto DAO de Vehiculo
     * @return interface de dicho objeto DAO
     */
    public abstract IVehiculoDAO getVehiculoDAO();
    /**
     * Objeto DAO de Foto
     * @return interface de dicho objeto DAO
     */
    public abstract IFotoDAO getFotoDAO();
     /**
     * Objeto DAO de Gasto
     * @return interface de dicho objeto DAO
     */
    public abstract IGastoDAO getGastoDAO();
    
    /**
     * Obtiene la fábrica concreta a la fuente de datos
     * @return la fábrica concreta
     */
    public static DAOFactory getDAOFactory() {
        
        DAOFactory daof = null;

        daof = new MySQLDAOFactory();
        
        return daof;

        
    }
    
}
