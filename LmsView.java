package lms.view;

import lms.bean.Book;
import lms.dao.LmsDao;

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
            System.out.println("************* Library Management System ****************");
            System.out.println(" ---------- Login As ------------");
            System.out.println("\t   1. Admin   ");
            System.out.println("\t   2. Student    ");
            System.out.println("\t  3. Exit    ");
            System.out.println("\t ------------------");
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();

            if (choice == 1) {
                String userName;
                userloop1:
                do {
                    System.out.println("Enter your User name : ");
                    userName = sc.next();
                    if (userName == null) {
                        System.out.println("Invalid User ... Please re enter user name");
                        continue userloop1;
                    }
                } while (userName == null);
                if (userName != null) {
                    System.out.println("Welcome " + userName);
                    int choice1 = 1;
                    do {
                        System.out.println("\t____  Options  ____");
                        System.out.println("\t    1. Add Books ");
                        System.out.println("\t    2. Update Books");
                        System.out.println("\t    3. Delete Books");
                        System.out.println("\t    4. Search Books");
                        System.out.println("\t    5. View Books");
                        System.out.println("\t    6.Go to Main Menu ");
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
                        }
                    } while (choice1 != 6);
                }
            }
        } while (choice != 3);
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

        Book book = new Book();
        System.out.println("\t Enter book id to search: ");
        book.setBookId(sc.nextInt());

        lmsDao.searchBooks(book);
    }
}
