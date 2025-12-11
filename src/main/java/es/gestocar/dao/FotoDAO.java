package es.gestocar.dao;

import es.gestocar.beans.Foto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class FotoDAO implements IFotoDAO {

    private Connection conexion;

    @Override
    public List<Foto> getFotosByVehiculoId(int idVehiculo) {

     List<Foto> fotos = new ArrayList<>();
    String sql = "SELECT idFotos, foto, idVehiculo FROM fotos WHERE idVehiculo = ?";
    try {
        conexion = ConnectionFactory.getConnection();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idVehiculo);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Foto foto = new Foto();
            foto.setIdFoto(rs.getInt("idFotos"));
            foto.setFoto(rs.getString("foto"));
            foto.setIdVehiculo(rs.getInt("idVehiculo"));
            fotos.add(foto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        closeConnection();
    }
    return fotos;
}


@Override
public int insertAndGetId(Foto foto) {
 String sql = "INSERT INTO fotos (idvehiculo, foto) VALUES (?, ?)";
    int idFoto = -1;

    try {
        conexion = ConnectionFactory.getConnection();
        PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, foto.getIdVehiculo());
        ps.setString(2, foto.getFoto());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            idFoto = rs.getInt(1);
        } else {
            System.out.println("No se generó ningún ID.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        closeConnection();
    }

    return idFoto;
}
   
    

    /**
     *
     * @param foto
     */
    @Override
    public void update(Foto foto) {
      String sql = "UPDATE fotos SET foto = ? WHERE idfotos = ?";
    try {
        conexion = ConnectionFactory.getConnection();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, foto.getFoto());
        ps.setInt(2, foto.getIdFoto());
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        closeConnection();
    }
    }
    
    
    

    @Override
    public void delete(Foto foto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
