package postgresql;

import java.sql.*;
import java.util.Scanner;

public class Postgresql {

    public static void main(String[] args) {
        
        try{
            
            //Conexion a la base de datos 'alumnos' 
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/alumnos", "postgres", "admin");
            
            //Menu principal del programa que permite seleccionar una operacion a realizar
            System.out.println("1. Listar alumnos.\n"
                    + "2. Buscar alumno.\n"
                    + "3. Eliminar alumno.\n");
            System.out.println("Seleccione una opcion: ");
            Scanner sc = new Scanner(System.in); //Leer opcion introducida por el usuario
            
            int opt = sc.nextInt();
            
            switch(opt){
                case 1:
                    //Se realiza una consulta a la base de datos que retorna a los alumnos registrados
                    PreparedStatement statement = con.prepareStatement("select * from alumnos");
                    ResultSet Rs = statement.executeQuery();
                    
                    //Se imprimen los resultados en consola
                    while (Rs.next()){
                        
                        System.out.println("ID: " + Rs.getInt("id") + " " +
                        "Cuenta: " + Rs.getString("numcuenta") + " " +
                        "Paterno: " + Rs.getString("apepat") + " " +
                        "Materno: " + Rs.getString("apemat") + " " +
                        "Nombre: " + Rs.getString("nombre") + " " +
                        "Creditos: " + Rs.getString("creditos"));
                        
                    }
                    
                    break;
                    
                case 2:
                    
                    System.out.println("Introduzca el numero de cuenta o nombre del alumno que desea buscar: ");
                    Scanner sc1 = new Scanner(System.in);
                    String option = sc1.next();
                    
                    //Se realiza una busqueda en la base de datos por nombre o numero de cuenta del alumno
                    PreparedStatement statement1 = con.prepareStatement("select * from alumnos where numcuenta like ? OR nombre like ?");
                    statement1.setString(1, option);
                    statement1.setString(2, option);
                    ResultSet Rs1 = statement1.executeQuery();
                    
                    while (Rs1.next()){
                        //Se imprimen los resultados de la busqueda
                        System.out.println("ID: " + Rs1.getInt("id") + " " +
                        "Cuenta: " + Rs1.getString("numcuenta") + " " +
                        "Paterno: " + Rs1.getString("apepat") + " " +
                        "Materno: " + Rs1.getString("apemat") + " " +
                        "Nombre: " + Rs1.getString("nombre") + " " +
                        "Creditos: " + Rs1.getString("creditos"));
                    
                    }
                    
                    break;
                    
                case 3:
                    
                    PreparedStatement statement2 = con.prepareStatement("select * from alumnos");
                    ResultSet Rs2 = statement2.executeQuery();
                    
                    while (Rs2.next()){
                        //Se imprimen los alumnos registrados para visualizar el id y posteriormente eliminarlo
                        System.out.println("ID: " + Rs2.getInt("id") + " " +
                        "Cuenta: " + Rs2.getString("numcuenta") + " " +
                        "Paterno: " + Rs2.getString("apepat") + " " +
                        "Materno: " + Rs2.getString("apemat") + " " +
                        "Nombre: " + Rs2.getString("nombre") + " " +
                        "Creditos: " + Rs2.getString("creditos"));
                        
                    }
                    
                    //Se pide el id del alumno que se desea eliminar y posteriormente se hace un delete a la base de datos
                    System.out.println("Introduzca el id del alumno que desea eliminar: ");
                    Scanner sc2 = new Scanner(System.in);
                    
                    int id = sc2.nextInt();
                    
                    PreparedStatement statement3 = con.prepareStatement("delete from alumnos where id=?");
                    statement3.setInt(1, id);
                    statement3.executeUpdate();
                    
                    System.out.println("El alumno fue eliminado satisfactoriamente.");
                    
                    break;
                    
                default:
                    System.out.println("Seleccione una opcion valida.");
            }
            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
