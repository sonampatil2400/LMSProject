package lms.dao;

import lms.bean.Book;
import lms.bean.IssueDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public Book searchBooks(int bookid) {
        try (PreparedStatement pst = con.prepareStatement("select * from books where bookid=?")) {
            pst.setInt(1, bookid);
            rs = pst.executeQuery();
            System.out.println(" BookId " + "  " + " Book Name " + "  " + " Publisher " + "  " + " Quantity " + "  " + " Author");
            Book book = new Book();

            while (rs.next()) {
                int bId = rs.getInt("bookid");
                String bName = rs.getString(2);
                String bPublisher = rs.getString(3);
                int bQuantity = rs.getInt(4);
                String bAuthor = rs.getString(5);

                book.setBookId(bId);
                book.setBookName(bName);
                book.setPublisher(bPublisher);
                book.setQuantity(bQuantity);
                book.setAuthor(bAuthor);
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void returnBooks() {

    }

    public ArrayList<IssueDetails> pendingIssueRequests() {
        try (PreparedStatement pst = con.prepareStatement(" select bookid, userid from issuedetails where issuedate = null")) {
            rs = pst.executeQuery();


            ArrayList<IssueDetails> issueDetailsList = new ArrayList<>();

            while (rs.next()) {
                int bid = rs.getInt("bookid");
                int userid = rs.getInt("userid");

                IssueDetails issueDetails = new IssueDetails();
                issueDetails.setBookId(bid);
                issueDetails.setUserId(userid);

                issueDetailsList.add(issueDetails);
            }
            return issueDetailsList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void issueBook(int bookId, int userId){
        try (PreparedStatement pst = con.prepareStatement(" update issuedate=CURDATE() where bookid=?, userid=?")){
            pst.setInt(1,bookId);
            pst.setInt(2,userId);
            rs=pst.executeQuery();
            System.out.println( bookId + " Book has been issued. ");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

        public void orderBooks(int userid, int bid) {
        try (PreparedStatement pst = con.prepareStatement(" insert into issuedetails(uderid, bookid) values(?,?)")){

            pst.setInt(1,userid);
            pst.setInt(2,bid);
            rs=pst.executeQuery();
            System.out.println("Order for " + bid + " has been placed for user " + userid);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Book> viewAllBooks() {
        try (PreparedStatement pst = con.prepareStatement("select * from books")) {
            rs = pst.executeQuery();
            System.out.println(" BookId " + "  " + " Book Name " + "  " + " Publisher " + "  " + " Quantity " + "  " + " Author");

            ArrayList<Book> bookList = new ArrayList<>();

            while (rs.next()) {
                int bId = rs.getInt(1);
                String bName = rs.getString(2);
                String bPublisher = rs.getString(3);
                int bQuantity = rs.getInt(4);
                String bAuthor = rs.getString(5);

                Book book = new Book();
                book.setBookId(bId);
                book.setBookName(bName);
                book.setPublisher(bPublisher);
                book.setQuantity(bQuantity);
                book.setAuthor(bAuthor);

                bookList.add(book);

            }
            return bookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void searchStudentBooks() {

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

