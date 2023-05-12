/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author Sergio
 * @version 1.0
 * La clase ConexionMySQL establece la conexion con una base de datos
 */
public class ConexionMySQL {
    /**
     * nombre de la base de datos
     */
    private String BD;
    /**
     * nombre del usuario
     */
    private String USUARIO;
    /**
     * contraseña del usuario
     */
    private String PASS;
    /**
     * conexion con la base de datos
     */
    private Connection conexion=null;
    /**
     * Contructor de la clase.
     * 
     * @param BD nombre de la base de datos
     * @param USUARIO conexion con la base de datos
     * @param PASS contraseña del usuario
     */
    public ConexionMySQL(String BD, String USUARIO, String PASS) {
        this.BD = BD;
        this.USUARIO = USUARIO;
        this.PASS = PASS;
    }
    /**
     * Metodo que conecta con la base de datos
     * 
     * @throws SQLException Error que salta cuando ocurre un error con la base de datos
     */
    public void conectar() throws SQLException {
        if(conexion==null||conexion.isClosed()){
        Calendar now = Calendar.getInstance();
        TimeZone zonahoraria = now.getTimeZone();
        conexion = (Connection) DriverManager.getConnection(
                "jdbc:mysql://localhost/" + BD + "?user="
                + USUARIO + "&password=" + PASS
                + "&useLegacyDatetimeCode=false&serverTimezone="
                + zonahoraria.getID());
        }
    }
    /**
     * Metodo que sirve para desconectarse de la base de datos
     * 
     * @throws SQLException Error que salta cuando ocurre un error con la base de datos
     * 
     * 
     */
    public void desconectar() throws SQLException {
        if (conexion.isClosed()||conexion==null) {
            System.out.println("La conexion esta ya cerrada anteriormente");
        } else {
            conexion.close();
            System.out.println("La conexion cerrada correctamente");
        }
    }
    /**
     * Metodo que sirve para ejecutar un SELECT en la base de datos
     * 
     * @param consulta Consulta SELECT que se le pasa por parametro.
     * @return Devuelve un ResultSet con el resultado de la consulta
     * @throws SQLException Error que salta cuando ocurre un error con la base de datos
     */
    public ResultSet ejecutarSelect(String consulta)throws SQLException{
         Statement stmt = conexion.createStatement();
         ResultSet resultado = stmt.executeQuery(consulta);
         return resultado;
    }
    /**
     * 
     * @param consulta Consulta, ya sea INSERT, UPDATE O DELETE, que se le pasa al metodo por parametro.
     * @return Devuelve el numero de filas afectadad por la "consulta" pasada por parametro en int.
     * @throws SQLException Error que salta cuando ocurre un error con la base de datos
     */
    public int ejecutarInsertUpdateDelete(String consulta)throws SQLException{
        Statement stmt = conexion.createStatement();
        int filas=0;
        int fila_afectada = stmt.executeUpdate(consulta);
        filas=filas+fila_afectada;
        return filas;
    }
}
