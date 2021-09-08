/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jeffer Cardenas <jecgdevp@gmail.com>
 */
public class Conexion {

    private static Connection con;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String usuario = "root";
    private static final String pass = "";
    private static final String url = "jdbc:mysql://localhost:3306/reto5_db";
    private static String mensaje;

    public static Connection getConnection() {
        if (con != null) {
            return con;
        }
        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, pass);
            mensaje = "Conectado a la base de datos";
            return con;

        } catch (ClassNotFoundException | SQLException e) {
            mensaje = "Error en la Conexion a la Base de Datos" + e.getMessage();
            return null;
        }
    }

    public static String getMensaje() {
        return mensaje;
    }

    public static void cerrar() {
        try {
            con.close();
            mensaje = "Conexion Cerrada";

        } catch (SQLException e) {
            mensaje = "Problema al intentar cerrar la base de datos " + e.getMessage();

        }
    }

}
