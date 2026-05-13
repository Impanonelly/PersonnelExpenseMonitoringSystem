package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.User;
import java.util.List;

public interface UserInterface extends Remote {
    String registerUser(User user) throws RemoteException;
    String updateUser(User user) throws RemoteException;
    String deleteUser(User user) throws RemoteException;
    List<User> retrieveAll() throws RemoteException; // Fixed: Removed User parameter
    User retrieveById(Long id) throws RemoteException;
    User retrieveByUsername(String username) throws RemoteException; // New method
}