package es.gestocar.dao;

import es.gestocar.beans.Usuario;
import static es.gestocar.dao.ConnectionFactory.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *
 * @author javier
 */
public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public void add(Usuario usuario) {

        //Método para insertar usuarios en gestocardb
        Connection conexion = null;
        String sql = "INSERT INTO usuarios (nombre,apellidos,email,password,dni) VALUES (?,?,?,?,?)";
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);

            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setString(1, usuario.getNombre());
            preparada.setString(2, usuario.getApellidos());
            preparada.setString(3, usuario.getEmail());
            preparada.setString(4, usuario.getPassword());
            preparada.setString(5, usuario.getDni());

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
            this.closeConnection();
        }

    }

    //Método para combrobar si exite el email en la base de datos gestocardb..
    // en caso de que exista creo el atributo de sesion....
    public Usuario authenticate(String email, String password) {
        Usuario usuario = null;
        String sql = "SELECT idusuarios FROM usuarios WHERE email = ? AND password = ?";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setString(1, email);
            preparada.setString(2, password);
            ResultSet resultSet = preparada.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("idusuarios"));
                // Puedes establecer otros atributos del usuario aquí si es necesario
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(); // Asegúrate de tener un método closeConnection adecuado
        }

        return usuario;
    }

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            ResultSet rs = preparada.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idusuarios"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setDni(rs.getString("dni"));
                usuario.setCampoBaja(rs.getBoolean("campobaja"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }

        return usuarios;
    }

    /**
     *
     * @param idUsuario
     * @param estado
     * @param usuarioId
     * @param campoBaja
     * @return
     */
    @Override
    public boolean updateUsuarioCampoBaja(int idUsuario, boolean estado) {
        boolean exito = false;
        String sql = "UPDATE usuarios SET campobaja = ? WHERE idusuarios = ?";

        try (Connection conexion = ConnectionFactory.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, estado ? "T" : "F");
            ps.setInt(2, idUsuario);

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                exito = true;
                conexion.commit();
            }

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

        return exito;
    }

    @Override
    public Usuario getUsuarioById(int id) {

        return null;
    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public List<Usuario> getUsuariosByCondicion(String[] registros) {

        return null;
    }

    @Override
    public void delete(Usuario usuario) {

    }

    @Override
    public void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
