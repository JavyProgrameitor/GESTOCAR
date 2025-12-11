package es.gestocar.dao;

import es.gestocar.beans.Gasto;

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
 * @author javier
 */
public class GastoDAO implements IGastoDAO {

    private Connection conexion;

    @Override
    public Boolean add(Gasto gasto) {
        Boolean retorno = true;
        String sql = "INSERT INTO gastos (idvehiculo, concepto, fechagasto, descripcion, importe, establecimiento, kilometros)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, gasto.getIdVehiculo());
            preparada.setString(2, gasto.getConcepto());
            preparada.setDate(3, gasto.getFechaGasto() != null ? new Date(gasto.getFechaGasto().getTime()) : null);
            preparada.setString(4, gasto.getDescripcion());
            preparada.setDouble(5, gasto.getImporte());
            preparada.setString(6, gasto.getEstablecimiento());
            preparada.setString(7, gasto.getKilometros());

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
    public List<Gasto> getGastosByVehiculoId(int vehiculoId) {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT idvehiculo, concepto, fechagasto, descripcion, importe, establecimiento, kilometros FROM gastos WHERE idvehiculo = ?";

        PreparedStatement preparada;

        try {

            conexion = ConnectionFactory.getConnection();
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, vehiculoId);
            ResultSet rs = preparada.executeQuery();
            while (rs.next()) {
                Gasto gasto = new Gasto();
                gasto.setConcepto(rs.getString("concepto"));
                gasto.setFechaGasto(rs.getDate("fechagasto"));
                gasto.setDescripcion(rs.getString("descripcion"));
                gasto.setImporte(rs.getDouble("importe"));
                gasto.setEstablecimiento(rs.getString("establecimiento"));
                gasto.setKilometros(rs.getString("kilometros"));
                gasto.setIdVehiculo(rs.getInt("idvehiculo"));
                gastos.add(gasto);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return gastos;
    }

    @Override
    public Double getTotalGastosByUsuarioId(int usuarioId) {
        Double total = 0.0;
        String sql = "SELECT SUM(importe) FROM gastos g JOIN vehiculos v ON g.idvehiculo = v.idvehiculo WHERE v.idusuario = ?";

        PreparedStatement preparada = null;
        ResultSet rs = null;

        try {
            conexion = ConnectionFactory.getConnection();
            preparada = conexion.prepareStatement(sql);

            //  parámetro de la consulta preparada
            preparada.setInt(1, usuarioId);

            // Ejecuta la consulta
            rs = preparada.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparada != null) {
                    preparada.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return total;
    }

    @Override
    public List<Gasto> obtenerGastosPorVehiculoPaginados(int idVehiculo) {
        List<Gasto> listaGastos = new ArrayList<>();
        String sql = "SELECT idvehiculo, concepto, fechagasto, descripcion, importe, establecimiento, kilometros "
                + "FROM gastos WHERE idvehiculo = ? ";
        try (Connection conexion = ConnectionFactory.getConnection(); PreparedStatement preparada = conexion.prepareStatement(sql)) {
            preparada.setInt(1, idVehiculo);

            try (ResultSet rs = preparada.executeQuery()) {
                while (rs.next()) {
                    Gasto gasto = new Gasto();
                    gasto.setIdVehiculo(rs.getInt("idvehiculo"));
                    gasto.setConcepto(rs.getString("concepto"));
                    gasto.setFechaGasto(rs.getDate("fechagasto"));
                    gasto.setDescripcion(rs.getString("descripcion"));
                    gasto.setImporte(rs.getDouble("importe"));
                    gasto.setEstablecimiento(rs.getString("establecimiento"));
                    gasto.setKilometros(rs.getString("kilometros"));
                    listaGastos.add(gasto);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeConnection();
            }

            return listaGastos;
        } catch (SQLException ex) {
            Logger.getLogger(GastoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
