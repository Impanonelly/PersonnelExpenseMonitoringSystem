
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author ADMIN
 */



import java.rmi.Remote;
import java.rmi.RemoteException;
import model.User;
import java.util.List;


public interface UserInterface extends Remote{
    public String registerUser(User user) throws RemoteException;
    public String updateUser(User user) throws RemoteException;
    public String deleteUser(User user) throws RemoteException;
    public List<User> retrieveAll() throws RemoteException;
    public User retrieveById(Long id) throws RemoteException;
   User retrieveByUsername(String username) throws RemoteException;
    public String insertUser(User user);
    
}
