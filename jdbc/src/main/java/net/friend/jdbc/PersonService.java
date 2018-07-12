package net.friend.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

  private String jdbcDriver;
  private String dbUrl;
  private String dbUsername;
  private String dbPassword;

  public PersonService(){
    jdbcDriver = MyProperties.getInstance().getProperty("db.driver");
    dbUrl = MyProperties.getInstance().getProperty("db.url");
    dbUsername = MyProperties.getInstance().getProperty("db.username");
    dbPassword = MyProperties.getInstance().getProperty("db.password");
  }

  public List<Person> findByName(String name){
    List<Person> personList = new ArrayList<Person>();

    Connection conn = null;
    Statement stmt = null;
    try{
      //STEP 2: Register JDBC driver
      Class.forName(jdbcDriver);

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT name, age FROM person WHERE name = '" + name +"'";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
        //Retrieve by column name
        Person person = new Person(rs.getString("name"), rs.getInt("age"));

        personList.add(person);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
    }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
    }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
    }finally{
      //finally block used to close resources
      try{
        if(stmt!=null)
          stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
        if(conn!=null)
          conn.close();
      }catch(SQLException se){
        se.printStackTrace();
      }//end finally try
    }//end try

    return personList;
  }
}
