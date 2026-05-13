package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.implementation.ExpenseImpl;
import service.implementation.UserImpl;

// Cleaned-up Server class
public class Server {
    public static void main(String[] args) {
        try {
            HibernateUtil.getSessionFactory();
            System.out.println("Hibernate initialized, TABLES CREATED SUCCESSFULLY!.");

            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(5000);
                System.out.println("RMI Registry created on port 5000");
            } catch (RemoteException e) {
                // If already created, get the existing one
                registry = LocateRegistry.getRegistry(5000);
                System.out.println("RMI Registry located on port 5000");
            }

            registry.rebind("users", new UserImpl());
            registry.rebind("expenses", new ExpenseImpl());

            System.out.println("Server is running and services are bound successfully.");
            
            // Log registered users for debugging
            try {
                dao.UserDao dao = new dao.UserDao();
                java.util.List<model.User> users = dao.retrieveAll();
                System.out.println("--- REGISTERED USERS ---");
                for (model.User u : users) {
                    System.out.println("User: " + u.getUsername());
                }
                System.out.println("------------------------");
            } catch (Exception e) {
                System.out.println("Could not retrieve user list for debug: " + e.getMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Server error: " + ex.getMessage());
        }
    }
}
