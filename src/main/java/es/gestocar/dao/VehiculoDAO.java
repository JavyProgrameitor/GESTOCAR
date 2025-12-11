package es.gestocar.dao;

import es.gestocar.beans.Vehiculo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @autor javier
 */
public class VehiculoDAO implements IVehiculoDAO {

    private Connection conexion;

    @Override
    public Boolean add(Vehiculo vehiculo) {
        Boolean retorno = true;
        String sql = "INSERT INTO vehiculos (idusuario, marca, modelo, motor, matricula, cilindrada, caballos, color, fechacompra, fechaventa, preciocompra, precioventa)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, vehiculo.getUsuarioId());
            preparada.setString(2, vehiculo.getMarca());
            preparada.setString(3, vehiculo.getModelo());
            preparada.setString(4, vehiculo.getMotor().name());
            preparada.setString(5, vehiculo.getMatricula());
            preparada.setString(6, vehiculo.getCilindrada());
            preparada.setString(7, vehiculo.getCaballos());
            preparada.setString(8, vehiculo.getColor());
            preparada.setDate(9, vehiculo.getFechaCompra() != null ? new Date(vehiculo.getFechaCompra().getTime()) : null);
            preparada.setDate(10, vehiculo.getFechaVenta() != null ? new Date(vehiculo.getFechaVenta().getTime()) : null);
            preparada.setDouble(11, vehiculo.getPreciocompra() != null ? vehiculo.getPreciocompra() : 0.0);
            preparada.setDouble(12, vehiculo.getPrecioventa() != null ? vehiculo.getPrecioventa() : 0.0);

            preparada.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } finally {
            closeConnection();
        }
        return retorno;
    }

    @Override
    public List<Vehiculo> getVehiculosByUsuarioId(int usuarioId) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE idusuario = ?";

        PreparedStatement preparada;
        try {
            conexion = ConnectionFactory.getConnection();
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, usuarioId);
            ResultSet rs = preparada.executeQuery();
            MotorConverter motorConverter = new MotorConverter();
            while (rs.next()) {

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("idvehiculo"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setMotor((Vehiculo.Motor) motorConverter.convert(Vehiculo.Motor.class, rs.getString("motor")));
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setCilindrada(rs.getString("cilindrada"));
                vehiculo.setCaballos(rs.getString("caballos"));
                vehiculo.setColor(rs.getString("color"));
                vehiculo.setFechaCompra(rs.getDate("fechacompra"));
                vehiculo.setFechaVenta(rs.getDate("fechaventa"));
                vehiculo.setPreciocompra(rs.getDouble("preciocompra"));
                vehiculo.setPrecioventa(rs.getDouble("precioventa"));
                vehiculo.setUsuarioId(rs.getInt("idusuario"));
                vehiculos.add(vehiculo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return vehiculos;
    }

    @Override
    public void update(Vehiculo vehiculo) {

        String sql = "UPDATE vehiculos SET marca=?, modelo=?, motor=?, matricula=?, cilindrada=?, caballos=?, color=?, fechacompra=?, fechaventa=?, preciocompra=?, precioventa=? "
                + "WHERE idvehiculo=?";

        PreparedStatement preparada;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            preparada = conexion.prepareStatement(sql);
            MotorConverter motorConverter = new MotorConverter();

            preparada.setString(1, vehiculo.getMarca());
            preparada.setString(2, vehiculo.getModelo());
            preparada.setString(3, vehiculo.getMotor().name());
            preparada.setString(4, vehiculo.getMatricula());
            preparada.setString(5, vehiculo.getCilindrada());
            preparada.setString(6, vehiculo.getCaballos());
            preparada.setString(7, vehiculo.getColor());
            preparada.setDate(8, vehiculo.getFechaCompra() != null ? new Date(vehiculo.getFechaCompra().getTime()) : null);
            preparada.setDate(9, vehiculo.getFechaVenta() != null ? new Date(vehiculo.getFechaVenta().getTime()) : null);
            preparada.setDouble(10, vehiculo.getPreciocompra() != null ? vehiculo.getPreciocompra() : 0.0);
            preparada.setDouble(11, vehiculo.getPrecioventa() != null ? vehiculo.getPrecioventa() : 0.0);
            preparada.setInt(12, vehiculo.getIdVehiculo());

            preparada.executeUpdate();
            conexion.commit();
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

    }

    @Override
    public void delete(int idVehiculo) {

        String sql = "DELETE FROM vehiculos WHERE idVehiculo=?";

        try (Connection conexion = ConnectionFactory.getConnection(); PreparedStatement preparada = conexion.prepareStatement(sql)) {

            conexion.setAutoCommit(false);
            preparada.setInt(1, idVehiculo);
            preparada.executeUpdate();
            conexion.commit();

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean deleteVehiculoID(int idVehiculo) {
        boolean exito = false;
        String sql = "DELETE FROM vehiculos WHERE idvehiculo = ?";

        try (Connection conexion = ConnectionFactory.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idVehiculo);

            int filasEliminadas = ps.executeUpdate();
            if (filasEliminadas > 0) {
                exito = true;
                conexion.commit();
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo básico de la excepción, se debe mejorar en producción

            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            closeConnection();
        }

        return exito;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT idvehiculo, marca, modelo, motor, matricula, cilindrada, caballos, color, fechacompra, fechaventa, preciocompra, precioventa FROM vehiculos";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            ResultSet rs = preparada.executeQuery();
            MotorConverter motorConverter = new MotorConverter();
            while (rs.next()) {

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("idvehiculo"));
                vehiculo.setMarca(rs.getString("marca"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setMotor((Vehiculo.Motor) motorConverter.convert(Vehiculo.Motor.class, rs.getString("motor")));
                vehiculo.setMatricula(rs.getString("matricula"));
                vehiculo.setCilindrada(rs.getString("cilindrada"));
                vehiculo.setCaballos(rs.getString("caballos"));
                vehiculo.setColor(rs.getString("color"));
                vehiculo.setFechaCompra(rs.getDate("fechacompra"));
                vehiculo.setFechaVenta(rs.getDate("fechaventa"));
                vehiculo.setPreciocompra(rs.getDouble("preciocompra"));
                vehiculo.setPrecioventa(rs.getDouble("precioventa"));

                vehiculos.add(vehiculo);

            }

        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return vehiculos;
    }

    @Override
    public int getLastInsertedId() {
        int id = 0;
        String sql = "SELECT LAST_INSERT_ID()";
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            ResultSet rs = preparada.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            // ... manejo de excepciones ...
        } finally {
            closeConnection();
        }
        return id;
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
