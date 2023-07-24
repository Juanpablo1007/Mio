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
import proyectofinal.domain.*;

public class EquipoDAO {
    private Conexion conexion;

    private final String SQL_SELECT = "SELECT * FROM equipos";
    private final String SQL_SEARCH = "SELECT * FROM equipos WHERE nombre = ?";
    private final String SQL_INSERT = "INSERT INTO equipos (nombre, codigo, jugadores, puntaje, escudo) VALUES(?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE equipos SET nombre = ?, codigo = ?, jugadores = ?, puntaje = ?, escudo = ? WHERE nombre = ?";
    private final String SQL_DELETE = "DELETE FROM equipos WHERE nombre = ?";

    
    public EquipoDAO(){
        conexion = new Conexion();
    }
    
    public ArrayList<Equipo> select() {
        ArrayList<Equipo> lista = new ArrayList<>();
        Equipo equipo = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idEquipo = rs.getInt("id_equipo");
                String nombre = rs.getString("nombre");
                String codigo = rs.getString("codigo");
                
                Blob blob = rs.getBlob("jugadores");
                byte[] data = blob.getBytes(1, (int)blob.length());
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bais);
                JugadorEquipo[] jugadores = (JugadorEquipo[]) ois.readObject();
                
                int puntaje = rs.getInt("puntaje");
                String escudo = rs.getString("escudo");

                equipo = new Equipo(idEquipo, nombre, codigo, jugadores, puntaje, escudo);

                lista.add(equipo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar con la base de datos...");
        } catch (IOException ex) {
            Logger.getLogger(EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean search (Equipo equipo){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean encontrado = false;
        
        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SEARCH);
            stmt.setString(1, equipo.getNombre());
            rs = stmt.executeQuery();
            while(rs.next()){
                equipo.setId(rs.getInt("id_cliente"));
                encontrado = true;
            }
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

    public int insert(Equipo equipo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getCodigo());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(equipo.getJugadores());
            byte[] data = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            stmt.setBlob(3, bais);
            
            stmt.setInt(4, equipo.getPuntaje());
            stmt.setString(5, equipo.getEscudo());

            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar con la base de datos...");
        } catch (IOException ex) {
            Logger.getLogger(EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public int update(Equipo equipo) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getCodigo());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(equipo.getJugadores());
            byte[] data = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            stmt.setBlob(3, bais);
            
            stmt.setInt(4, equipo.getPuntaje());
            stmt.setString(5, equipo.getEscudo());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al conectar a la base de datos");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
            System.out.println("Error al leer el archivo");
        } catch (IOException ex) {
            Logger.getLogger(EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conexion.close(stmt);
                conexion.close(conn);
                System.out.println("Información del equipo - " + equipo.getNombre() + " - ha sido actualizada");
            } catch (SQLException ex) {
                ex.printStackTrace(System.err);
                System.out.println("Error al cerrar conexiones");
            }
        }
        return registros;
    }

    public int delete(Equipo usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        int registros = 0;
        try {
            conn = conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setString(1, usuario.getNombre());

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

