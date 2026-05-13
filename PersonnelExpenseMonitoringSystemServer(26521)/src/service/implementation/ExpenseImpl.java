package service.implementation;

import dao.ExpenseDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Expense;
import service.ExpenseInterface;

public class ExpenseImpl extends UnicastRemoteObject implements ExpenseInterface {
    private transient ExpenseDao dao = new ExpenseDao(); // Marked as transient

    public ExpenseImpl() throws RemoteException {
        super();
    }

    @Override
    public String insertExpense(Expense expense) throws RemoteException {
       Logger.getLogger(ExpenseImpl.class.getName()).log(Level.INFO, 
            "Received expense with user: {0}", 
            expense.getUser() != null ? expense.getUser().getUsername() : "null");
        return dao.insertExpense(expense);
    }

    @Override
    public String updateExpense(Expense expense) throws RemoteException {
        return dao.updateExpense(expense);
    }

    @Override
    public String deleteExpense(Expense expense) throws RemoteException {
        return dao.deleteExpense(expense); // Updated to match case
    }

    @Override
public List<Expense> retrieveAll(Expense expense) throws RemoteException {
    return dao.retrieveAll(expense);
}

    @Override
    public Expense retrieveById(Long id) throws RemoteException {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return dao.retrieveById(id);
    }
}