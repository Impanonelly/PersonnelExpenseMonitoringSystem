package dao;

import controller.HibernateUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Expense;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExpenseDao {


public String insertExpense(Expense expense) {
    Session ss = HibernateUtil.getSessionFactory().openSession();
    try {
        if (expense == null) {
            Logger.getLogger(ExpenseDao.class.getName()).log(Level.SEVERE, "Expense is null");
            return "Expense is null";
        }
        if (expense.getUser() == null) {
            Logger.getLogger(ExpenseDao.class.getName()).log(Level.SEVERE, "User is null for expense: {0}", expense);
            return "User is null";
        }
        if (expense.getUser().getId() == null) {
            Logger.getLogger(ExpenseDao.class.getName()).log(Level.INFO, 
                "User ID missing for {0}, attempting lookup...", expense.getUser().getUsername());
            User dbUser = retrieveByUsername(expense.getUser().getUsername());
            if (dbUser != null) {
                expense.setUser(dbUser);
            } else {
                Logger.getLogger(ExpenseDao.class.getName()).log(Level.SEVERE, 
                    "Could not find user in database: {0}", expense.getUser().getUsername());
                return "User not found in database";
            }
        }
        Transaction tr = ss.beginTransaction();
        ss.save(expense);
        tr.commit();
        return "Expense inserted successfully";
    } catch (Exception ex) {
        Logger.getLogger(ExpenseDao.class.getName()).log(Level.SEVERE, "Error inserting expense", ex);
        return "Error: " + ex.getMessage();
    } finally {
        if (ss != null && ss.isOpen()) {
            ss.close();
        }
    }
}
    
    
    public String updateExpense(Expense expense) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        try {
            if (expense.getUser() != null && expense.getUser().getId() == null) {
                User dbUser = retrieveByUsername(expense.getUser().getUsername());
                if (dbUser != null) expense.setUser(dbUser);
            }
            Transaction tr = ss.beginTransaction();
            ss.update(expense);
            tr.commit();
            return "Expense updated successfully";
        } catch (Exception ex) {
            Logger.getLogger(ExpenseDao.class.getName()).log(Level.SEVERE, "Update error", ex);
            return "Error: " + ex.getMessage();
        } finally {
            if (ss != null && ss.isOpen()) {
                ss.close();
            }
        }
    }

    public String deleteExpense(Expense expense) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        try {
            if (expense.getUser() != null && expense.getUser().getId() == null) {
                User dbUser = retrieveByUsername(expense.getUser().getUsername());
                if (dbUser != null) expense.setUser(dbUser);
            }
            Transaction tr = ss.beginTransaction();
            ss.delete(expense);
            tr.commit();
            return "Expense Deleted successfully";
        } catch (Exception ex) {
            Logger.getLogger(ExpenseDao.class.getName()).log(Level.SEVERE, "Delete error", ex);
            return "Error: " + ex.getMessage();
        } finally {
            if (ss != null && ss.isOpen()) {
                ss.close();
            }
        }
    }




    public Expense retrieveById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Session ss = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Expense) ss.get(Expense.class, id);
        } finally {
            if (ss != null && ss.isOpen()) {
                ss.close();
            }
        }
    }
    
    
    
    public User retrieveByUsername(String username) {
        Session ss = HibernateUtil.getSessionFactory().openSession();
        try {
            User user = (User) ss.createQuery("SELECT u FROM User u WHERE u.username = :username")
                                 .setParameter("username", username)
                                 .uniqueResult();
            if (user != null) {
                Logger.getLogger(UserDao.class.getName()).log(Level.INFO, 
                    "Retrieved user: ID={0}, Username={1}", 
                    new Object[]{user.getId(), user.getUsername()});
            } else {
                Logger.getLogger(UserDao.class.getName()).log(Level.WARNING, 
                    "No user found for username: {0}", username);
            }
            return user;
        } catch (Exception ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error retrieving user", ex);
            return null;
        } finally {
            if (ss != null && ss.isOpen()) {
                ss.close();
            }
        }
    }
    
    
    public void createPDF(List<Expense> expenseList) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("ExpensesReport.pdf"));
        document.open();

        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Paragraph title = new Paragraph("Report For All Expenses Made", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Phrase("\n"));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        float[] columnWidths = {1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);

        table.addCell("Description"); // Updated to match Expense class
        table.addCell("Amount");
        table.addCell("Date");
        table.addCell("User"); // Simplified; adjust if needed

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Expense expense : expenseList) {
            table.addCell(expense.getDescription());
            table.addCell(String.valueOf(expense.getAmount()));
            table.addCell(dateFormat.format(new Date())); // Placeholder; update if Expense has a date field
            table.addCell(expense.getUser() != null ? expense.getUser().getUsername() : "N/A");
        }

        document.add(table);
        document.close();
    }
    
    public List<Expense> retrieveAll(Expense expense) {
    Session ss = HibernateUtil.getSessionFactory().openSession();
    try {
        if (expense == null || expense.getUser() == null || expense.getUser().getId() == null || expense.getUser().getId() == 0) {
            return ss.createQuery("SELECT e FROM Expense e").list();
        }
        return ss.createQuery("SELECT e FROM Expense e WHERE e.user.id = :userId")
                 .setParameter("userId", expense.getUser().getId())
                 .list();
    } catch (Exception ex) {
        Logger.getLogger(ExpenseDao.class.getName()).log(Level.SEVERE, "Error retrieving expenses", ex);
        return new ArrayList<>();
    } finally {
        if (ss != null && ss.isOpen()) {
            ss.close();
        }
    }
}
}