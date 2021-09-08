/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.cj.xdevapi.Result;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Conexion;
import modelo.Viajero;

/**
 *
 * @author Jeffer Cardenas <jecgdevp@gmail.com>
 */
public class DatosViajero {

    private Connection con;
    private String mensaje;
    private PreparedStatement ps;
    private ResultSet rs;

    public DatosViajero() {

        con = Conexion.getConnection();
    }

    public boolean crearViajero(Viajero viajero) {
        boolean creado = false;

        String sql = "insert into viajero values(null,?,?,?)";

        try {

            ps = this.con.prepareStatement(sql);
            ps.setString(1, viajero.getNombre());
            ps.setString(2, viajero.getEspecie());
            ps.setString(3, viajero.getGenero());

            if (ps.executeUpdate() > 0) {

                creado = true;

            }

        } catch (SQLException e) {

            this.mensaje = "Ha ocurrido un error al momento de insertar a la BD: " + e.getMessage();
        }
        return creado;
    }

    public List<Viajero> listarViajeros() {

        List<Viajero> viajeros = new ArrayList<>();

        try {
            String sql = "select * from viajero";

            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Viajero viajero = new Viajero();
                viajero.setId(rs.getInt("v_id"));
                viajero.setNombre(rs.getString("v_nombre"));
                viajero.setEspecie(rs.getString("v_especie"));
                viajero.setGenero(rs.getString("v_genero"));

                viajeros.add(viajero);
            }
        } catch (SQLException e) {
            this.mensaje = "Ha ocurrido un error al momento de obtener datos de la BD:" + e.getMessage();
        }

        return viajeros;

    }

    public boolean actualizarViajero(Viajero viajero) {

        boolean actualizado = false;

        PreparedStatement ps;
        String consulta = "update viajero SET v_nombre=?, v_especie=?, v_genero=?" + " WHERE v_identidad=? ";
        try {
            ps = this.con.prepareStatement(consulta);
            ps.setString(1, viajero.getNombre());
            ps.setString(2, viajero.getEspecie());
            ps.setString(3, viajero.getGenero());
            ps.setInt(4, viajero.getId());

            if (ps.executeUpdate() > 0) {
                actualizado = true;
            }

        } catch (SQLException e) {
            this.mensaje = "Problema al actualizar el viajero" + e.getMessage();
        }

        return actualizado;
    }
    
    public boolean eliminarViajero(int id){
        boolean eliminado = false;
        
        PreparedStatement ps;
        String consulta = "DELETE from viajero where v_identidad = ?";
        try {
            ps = this.con.prepareStatement(consulta);
            ps.setInt(1, id);
            
            if(ps.executeUpdate() > 0){
                eliminado = true;
            }
            
        } catch (SQLException e) {
            
            this.mensaje = "Problema al eliminar el viajero" + e.getMessage();
            
        }
        
        return eliminado;
    }
}
