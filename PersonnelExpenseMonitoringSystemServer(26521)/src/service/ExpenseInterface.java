package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Expense;

public interface ExpenseInterface extends Remote {
    String insertExpense(Expense expense) throws RemoteException;
    String updateExpense(Expense expense) throws RemoteException;
    String deleteExpense(Expense expense) throws RemoteException;
    List<Expense> retrieveAll(Expense expense) throws RemoteException; // Removed Expense parameter
    Expense retrieveById(Long id) throws RemoteException; // Changed to Long id
}