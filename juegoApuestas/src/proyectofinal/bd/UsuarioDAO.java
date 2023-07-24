package proyectofinal.bd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectofinal.domain.Apuesta;
import proyectofinal.domain.Usuario;

public class UsuarioDAO {
    private Conexion conexion;

    private final String SQL_SELECT = "SELECT * FROM usuarios";
    private final String SQL_SEARCH = "SELECT * FROM usuarios WHERE documento = ?";
    private final String SQL_INSERT = "INSERT INTO usuarios (nombre, documento, correo, contrasenia, saldo, historial_apuestas) VALUES(?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE usuarios SET nombre = ?, documento = ?, correo = ?, contrasenia = ?, saldo = ?, historial_apuestas = ? WHERE id_usuario = ?";
    private final String SQL_DELETE = "DELETE FROM usuarios WHERE id_usuarios = ?";

    
    public UsuarioDAO(){
        conexion = new Conexion();
    }
    
    public ArrayList<Usuario> select() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        Usuario usuario = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idJugador = rs.getInt("id_usuarios");
                String nombre = rs.getString("nombre");
                String documento = rs.getString("documento");
                String correo = rs.getString("correo");
                String contrasenia = rs.getString("contrasenia");
                double saldo = rs.getDouble("saldo");
                
                Blob blob = rs.getBlob("historial_apuestas");
                byte[] data = blob.getBytes(1, (int)blob.length());
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bais);
                ArrayList<Apuesta> historialApuestas = (ArrayList<Apuesta>) ois.readObject();

                usuario = new Usuario(idJugador, nombre, documento, correo, contrasenia, saldo, historialApuestas);

                lista.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar con la base de datos...");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close(rs);
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                System.out.println("Error al cerrar conexiones con la base de datos");
            }
        }
        return lista;
    }
    
    public boolean search (Usuario usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean encontrado = false;
        
        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SEARCH);
            stmt.setString(1, usuario.getDocumento());
            rs = stmt.executeQuery();
            encontrado = rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar con la base de datos...");
        } finally {
            try {
                conexion.close(rs);
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                System.out.println("Error al cerrar conexiones con la base de datos...");
            }
        }
        return encontrado;
    }

    public int insert(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getDocumento());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasenia());
            stmt.setDouble(5, usuario.getSaldo());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(usuario.getHistorialApuestas());
            byte[] data = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            stmt.setBlob(6, bais);

            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar con la base de datos...");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                System.out.println("Error al cerrar conexiones de la base de datos...");
            }
        }

        return registros;
    }
    
    public int update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getDocumento());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasenia());
            stmt.setDouble(5, usuario.getSaldo());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(usuario.getHistorialApuestas());
            byte[] data = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            stmt.setBlob(6, bais);

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar a la base de datos");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al leer el archivo");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                System.out.println("Error al cerrar conexiones");
            }
        }
        return registros;
    }

    public int delete(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;
        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, usuario.getIdUsuario());

            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar con la base de datos");
        } finally {
            try {
                conexion.close(stmt);
                conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                System.out.println("Error al cerrar conexiones");
            }
        }

        return registros;
    }
}
