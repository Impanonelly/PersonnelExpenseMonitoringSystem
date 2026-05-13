/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Expense;

/**
 *
 * @author ADMIN
 */
public  interface ExpenseInterface extends Remote {
    public String insertExpense(Expense expense) throws RemoteException;
     public String updateExpense(Expense expense) throws RemoteException;
    public String deleteExpense(Expense expense) throws RemoteException;
    public List<Expense> retrieveAll(Expense expense) throws RemoteException;
    public Expense retrieveById(Long id) throws RemoteException;
}
