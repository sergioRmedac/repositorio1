/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import alumnos.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public class ControladorAlumno {
    private ConexionMySQL conexion;

    public ControladorAlumno(ConexionMySQL conexion) {
        this.conexion = conexion;
    }
    
    public ArrayList<Alumno> obtenerTodosAlumnos()throws SQLException{
        ArrayList<Alumno> alumnos=new ArrayList<>();
        String consulta="SELECT * FROM alumnos";
        ResultSet resultado=conexion.ejecutarSelect(consulta);
        
        while(resultado.next()){
            String dni=resultado.getString("dni");
            String nombre=resultado.getString("nombre");
            Integer edad=resultado.getInt("edad");
            
            Alumno a1=new Alumno(dni,nombre,edad);
            alumnos.add(a1);
        }
        return alumnos;
    }
}
