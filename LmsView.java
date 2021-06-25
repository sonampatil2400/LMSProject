package lms.view;

import lms.bean.Book;
import lms.bean.IssueDetails;
import lms.dao.LmsDao;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LmsView {

    Scanner sc;
    LmsDao lmsDao;

    public LmsView() {
        sc = new Scanner(System.in);
        lmsDao = new LmsDao();
    }

    public void startApp() {
        int choice;
        Lable1:
        do {
            System.out.println();
            System.out.println("************* Library Management System ****************");
            System.out.println(" ---------- Login As ------------");
            System.out.println("\t   1. Admin   ");
            System.out.println("\t   2. Student    ");
            System.out.println("\t  3. Exit    ");
            System.out.println("\t ------------------");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();

            if (choice == 1) {
                Integer userId;
                userloop1:
                do {
                    System.out.println("Enter your User Id : ");
                    userId = sc.nextInt();
                    if (userId == null) {
                        System.out.println("Invalid User ... Please re enter user name");
                        continue userloop1;
                    }
                } while (userId == null);
                if (userId != null) {
                    System.out.println("Welcome " + userId);
                    int choice1 = 1;
                    do {
                        System.out.println();
                        System.out.println("\t____  Options  ____");
                        System.out.println("\t    1. Add Books ");
                        System.out.println("\t    2. Update Books");
                        System.out.println("\t    3. Delete Books");
                        System.out.println("\t    4. Search Books");
                        System.out.println("\t    5. View Books");
                        System.out.println("\t    6. Issue Books");
                        System.out.println("\t    7.Go to Main Menu ");
                        System.out.println("\t_______________");
                        System.out.println("\tEnter your choice : ");
                        choice1 = sc.nextInt();
                        if (choice1 == 1) {
                            addBooks();
                        } else if (choice1 == 2) {
                            updateBooks();
                        } else if (choice1 == 3) {
                            deleteBooks();
                        } else if (choice1 == 4) {
                            searchBooks();
                        } else if (choice1 == 6) {
                            issueBooks();
                        }
                    } while (choice1 != 7);
                }
            } else if (choice == 2) {
                Integer userId;
                userloop1:
                do {
                    System.out.println("Enter your User Id : ");
                    userId = sc.nextInt();
                    if (userId == null) {
                        System.out.println("Invalid User ... Please re enter user name");
                        continue userloop1;
                    }
                } while (userId == null);
                if (userId != null) {
                    System.out.println("Welcome " + userId);
                    int choice1 = 1;
                    do {
                        System.out.println("\t____  Options  ____");
                        System.out.println("\t    1. View All Books ");
                        System.out.println("\t    2. Search Books");
                        System.out.println("\t    3. Order Books");
                        System.out.println("\t    4. Return Ordered Books");
                        System.out.println("\t    5. View Issued Books Information");
                        System.out.println("\t    6.Go to Main Menu ");
                        System.out.println("\t_______________");
                        System.out.println("\tEnter your choice : ");
                        choice1 = sc.nextInt();
                        if (choice1 == 1) {
                            viewAllBooks();
                        } else if (choice1 == 2) {
                            searchStudentBooks();
                        } else if (choice1 == 3) {
                            orderBooks(userId);
                        } else if (choice1 == 4) {
                            returnBooks();
                        } else if (choice1 == 5) {
                            viewIssuedBookInformation();
                        }
                    } while (choice1 != 6);
                }
            }
        } while (choice != 3);
    }

    private void viewAllBooks() {
        List<Book> b = lmsDao.viewAllBooks();

        for (Book book : b) {
            System.out.println(book);
        }
    }

    private void searchStudentBooks() {

    }

    private void orderBooks(int userid) {
        List<Book> bookList = lmsDao.viewAllBooks();

        for (Book book : bookList) {
            System.out.println("\t  " + book.getBookId() + "     " + book.getBookName() + "   " + book.getPublisher() + "    " + book.getQuantity() + "    " + book.getAuthor());
        }
        System.out.println("\t Enter book id to be ordered: ");
        int bid = sc.nextInt();

        lmsDao.orderBooks(userid, bid);
    }

    private void issueBooks() {
        System.out.println(" Enter book id to be issued");
        int bid = sc.nextInt();
        System.out.println("Enter userid whose book is to be issued ");
        int userid = sc.nextInt();

        ArrayList<IssueDetails> pendingIssueRequestsList = lmsDao.pendingIssueRequests();
        for (IssueDetails issueDetail : pendingIssueRequestsList) {
            System.out.println("  " + issueDetail.getBookId() + "   " + issueDetail.getUserId());
        }

        lmsDao.issueBook(bid, userid);
    }

    private void viewIssuedBookInformation() {
        List<IssueDetails> issueDetails = lmsDao.viewIssuedBookInformation();
        System.out.println(" Issue Id  " + "  " + " Book Id  " + "  " + " User Id " + "  " + "  Issue date " + "  " + " Return Date");
        for (IssueDetails details : issueDetails) {
            System.out.println(details.getIssueId() + "   " + details.getBookId() + "  " + details.getUserId() + "  " + details.getIssueDate() + "  " + details.getReturnDate());
        }
    }

    private void returnBooks() {
        Book book = new Book();
        System.out.println("\t Enter book id to return. ");
        book.setBookId(sc.nextInt());

        lmsDao.returnBooks();
    }

    private void addBooks() {
        Book book = new Book();
        System.out.println("\t Enter Book name : ");
        book.setBookName(sc.next());
        System.out.println("\t Enter Book author : ");
        book.setAuthor(sc.next());
        System.out.println("\t Enter Book publisher : ");
        book.setPublisher(sc.next());
        System.out.println("\t Enter Book quantity : ");
        book.setQuantity(sc.nextInt());

        lmsDao.addBooks(book);
    }


    private void updateBooks() {
        Book book = new Book();
        System.out.println("\t Enter book id to update. ");
        book.setBookId(sc.nextInt());
        System.out.println(" Enter book name : ");
        book.setBookName(sc.next());
        System.out.println("\t Enter Book publisher : ");
        book.setPublisher(sc.next());
        System.out.println("\t Enter Book quantity : ");
        book.setQuantity(sc.nextInt());
        System.out.println("\t Enter Book author : ");
        book.setAuthor(sc.next());

        lmsDao.updateBooks(book);
    }

    private void deleteBooks() {
        Book book = new Book();
        System.out.println("\t Enter book id to delete. ");
        book.setBookId(sc.nextInt());

        lmsDao.deleteBooks(book);
    }

    private void searchBooks() {
        System.out.println("\t Enter book id to search: ");
        int bookid = sc.nextInt();

        Book b = lmsDao.searchBooks(bookid);
        System.out.println(" \t   " + b.getBookId() + "  \t   " + b.getBookName() + "  \t  " + b.getPublisher() + "  \t  " + b.getQuantity() + "  \t    " + b.getAuthor());
        System.out.println();
    }
}
