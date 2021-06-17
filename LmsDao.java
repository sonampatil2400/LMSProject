package lms.dao;

import lms.bean.Book;

import java.sql.*;
import java.util.Scanner;

public class LmsDao {


    public static Connection con;
    ResultSet rs, rs1;
    static Statement st, st1;
    PreparedStatement pst, pst1;
    CallableStatement cst;
    Scanner sc;
    int id;

    static {
        con = DBConnection.getConnection();
        System.out.println("Database Connection established...");
        try {
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st1 = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public int addBooks(Book book) {
        int rowsAffected = 0;

        try (PreparedStatement pst = con.prepareStatement("insert into Books(bookid, bookname, publisher, quantity, author)"
                + "values(?,?,?,?,?)")) {

            pst.setInt(1, book.getBookId());
            pst.setString(2, book.getBookName());
            pst.setString(3, book.getPublisher());
            pst.setInt(4, book.getQuantity());
            pst.setString(5, book.getAuthor());

            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowsAffected;
    }

    public int updateBooks(Book book) {

        int rowsupdated = 0;
        try (PreparedStatement pst = con.prepareStatement("update books set bookName=?, publisher=?, quantity=?, author=? where bookId = ?")) {
            pst.setString(1, book.getBookName());
            pst.setString(2, book.getPublisher());
            pst.setInt(3, book.getQuantity());
            pst.setString(4, book.getAuthor());
            pst.setInt(5, book.getBookId());

            rowsupdated = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update handled: " + e);
            e.printStackTrace();
        }

        return rowsupdated;
    }

    public int deleteBooks(Book book) {

        int rowsupdated = 0;
        try (PreparedStatement pst = con.prepareStatement("delete from books where bookid=?")) {
            pst.setInt(1, book.getBookId());

            rowsupdated = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsupdated;
    }

    public int searchBooks(Book book) {

        int rowsupdated = 0;
        try (PreparedStatement pst = con.prepareStatement("select * from books where bookid=?")) {
            pst.setInt(1, book.getBookId());

            rowsupdated = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsupdated;
    }

    public void closeResources() {
        try {
            if (rs1 != null)
                rs1.close();
            if (rs != null)
                rs.close();
            if (pst != null)
                pst.close();
            if (st != null)
                st.close();
            if (con != null)
                con.close();
            System.out.println("Connection with Database closed successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

