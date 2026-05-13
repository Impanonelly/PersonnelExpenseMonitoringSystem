package service.implementation;

import dao.UserDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.User;
import service.UserInterface;

public class UserImpl extends UnicastRemoteObject implements UserInterface {
    public UserDao dao = new UserDao();

    public UserImpl() throws RemoteException {
        super();
    }

    @Override
    public String registerUser(User user) throws RemoteException {
        return dao.registerUser(user);
    }

    @Override
    public String updateUser(User user) throws RemoteException {
        return dao.updateUser(user);
    }

    @Override
    public String deleteUser(User user) throws RemoteException {
        return dao.deleteUser(user);
    }

    @Override
    public List<User> retrieveAll() throws RemoteException {
        return dao.retrieveAll(); 
    }

    @Override
    public User retrieveById(Long id) throws RemoteException {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return dao.retrieveById(id);
    }

    @Override
    public User retrieveByUsername(String username) throws RemoteException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return dao.retrieveByUsername(username);
    }
}