/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesodedatos;

/**
 *
 * @author Dante
 */
public class AccesoDeDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        MySQLAcces dao = new MySQLAcces();
        dao.readDatabase();
    }
    
}
