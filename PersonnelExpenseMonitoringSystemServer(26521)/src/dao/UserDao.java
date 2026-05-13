/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package dao;
import controller.HibernateUtil;
import model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class UserDao {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/personnel_expense_monitoring_system";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "Admin@12";
//    
    
    public String registerUser(User user){
        try{
            //1. Create a Session
            Session ss= HibernateUtil.getSessionFactory().openSession();
            //2.Create a transaction
            Transaction tr= ss.beginTransaction();
            ss.save(user);
            tr.commit();
            ss.close();
            return "User Registered successfully";
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
   
    
    public String updateUser(User user) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = ss.beginTransaction();
            
            // If ID is null, try to find it by username
            if (user.getId() == null || user.getId() == 0) {
                Query query = ss.createQuery("SELECT u.id FROM User u WHERE u.username = :username");
                query.setParameter("username", user.getUsername());
                Long id = (Long) query.uniqueResult();
                if (id != null) {
                    user.setId(id);
                } else {
                    return "Update Failed: User not found";
                }
            }
            
            ss.update(user);
            tr.commit();
            return "User updated successfully";
        } catch (Exception ex) {
            if (tr != null) tr.rollback();
            ex.printStackTrace();
            return "Update Failed: " + ex.getMessage();
        } finally {
            ss.close();
        }
    }
    public List<User> retrieveAll() {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        List<User> userList = ss.createQuery("FROM User").list();
        ss.close();
        return userList;
    }
   public User retrieveById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Session ss = HibernateUtil.getSessionFactory().openSession();
        User user = (User) ss.get(User.class, id);
        ss.close();
        return user;
    }
     public String deleteUser(User user){
        try{
             //1. Create session
             Session ss = HibernateUtil.getSessionFactory().openSession();
            //2. Create transaction
            Transaction tr = ss.beginTransaction();
            //3. Perform Operation
            ss.delete(user);
            //4. Commit the transaction
            tr.commit();
            //5. Close the connection
            ss.close();
        }catch(Exception ex){
            ex.printStackTrace();
            
        }
        return "User Deleted Successfully";
    }
public User retrieveByUsername(String username) {
    if (username == null || username.isEmpty()) {
        throw new IllegalArgumentException("Username cannot be null or empty");
    }
    Session ss = HibernateUtil.getSessionFactory().openSession();
    try {
        // Use the raw createQuery method and cast the result
        Query query = ss.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult(); // Cast the result to User
        System.out.println("DEBUG: Found user for username [" + username + "]: " + (user != null ? "YES" : "NO"));
        return user;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    } finally {
        ss.close();
    }
}
}
    
    
//    public void insertUser(String username, String password, String fullname, String phoneNumber) {
//        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement insert = con.prepareStatement("INSERT INTO users (username, password, fullname, phone_number) VALUES (?, ?, ?, ?)")) {
//            insert.setString(1, username);
//            insert.setString(2, password);
//            insert.setString(3, fullname);
//            insert.setString(4, phoneNumber);
//            insert.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            // Handle exception
//        }
//    }
//
//    // Method to update an existing user in the database
//    public boolean updateUser(String username, String password, String fullname, String phoneNumber) {
//    try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//         PreparedStatement update = con.prepareStatement("UPDATE users SET password = ?, fullname = ?, phone_number = ? WHERE username = ?")) {
//        update.setString(1, password);
//        update.setString(2, fullname);
//        update.setString(3, phoneNumber);
//        update.setString(4, username);
//        int rowsAffected = update.executeUpdate();
//        return rowsAffected > 0; // Return true if at least one row was affected
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        return false; // Return false in case of exception
//    }
//}
//
//
//    public void deleteUser(String username) {
//        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement delete = con.prepareStatement("DELETE FROM users WHERE username = ?")) {
//            delete.setString(1, username);
//            delete.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            // Handle exception
//        }
//    }
//    public List<User> getAllUsers() {
//        List<User> userList = new ArrayList<>();
//        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement selectAll = con.prepareStatement("SELECT * FROM users");
//             ResultSet rs = selectAll.executeQuery()) {
//            while (rs.next()) {
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//                String fullname = rs.getString("fullname");
//                String phoneNumber = rs.getString("phone_number");
//                userList.add(new User(username, password, fullname, phoneNumber));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            
//        }
//        return userList;
//    }
//    public User getUserByUsername(String username) {
//        User user = null;
//        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement selectByUsername = con.prepareStatement("SELECT * FROM users WHERE username = ?")) {
//            selectByUsername.setString(1, username);
//            ResultSet rs = selectByUsername.executeQuery();
//            if (rs.next()) {
//                String password = rs.getString("password");
//                String fullname = rs.getString("fullname");
//                String phoneNumber = rs.getString("phone_number");
//                user = new User(username, password, fullname, phoneNumber);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            
//        }
//        return user;
//    }
    