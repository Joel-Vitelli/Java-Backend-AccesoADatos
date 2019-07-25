/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesodedatos;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dante
 */
public class MySQLAcces {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  
 public void readDatabase(){
     try {
        Class.forName("com.mysql.jdbc.Driver");
         
        connect = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://localhost/feedback", "root", "");
         
        statement = (Statement) connect.createStatement();//creamos sencentias
         
        resultSet = statement.executeQuery("SELECT * FROM feedback.comments");
         
        writeResulSet(resultSet);
        
        preparedStatement = (PreparedStatement) connect.prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "TestEmail");
        preparedStatement.setString(3, "TestWebpage");
        preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
        preparedStatement.setString(5, "TestSummary");
        preparedStatement.setString(6, "TestComment");
        preparedStatement.executeUpdate();
        preparedStatement = (PreparedStatement) connect.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
        resultSet = preparedStatement.executeQuery();
        writeResulSet(resultSet);
        //Delete
        preparedStatement = (PreparedStatement) connect.prepareStatement("DELETE from feedback.comments where myuser= ? ; ");
        preparedStatement.setString(1, "Test");
        preparedStatement.executeUpdate();
        resultSet = statement.executeQuery("select * from feedback.comments");
        writeMetaData(resultSet);
        //Update
        
        
     } catch (Exception e) {
     }
 }
 public void writeResulSet(ResultSet resultSet) throws SQLException{
     while (resultSet.next()) {
      String user = resultSet.getString("myuser");
      String email = resultSet.getString("email");  
      String website = resultSet.getString("webpage");
      String summary = resultSet.getString("summary");
      Date date = resultSet.getDate("datum");
      String comment = resultSet.getString("comments");
      System.out.println("MyUser: " + user);
      System.out.println("E-Mail: " + email);
      System.out.println("Website: " + website);
      System.out.println("Summary: " + summary);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + comment);
         
     }
 }
 private void writeMetaData(ResultSet resultSet) throws SQLException {
        
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }
 private void closed(){
     try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
 }
    
}
