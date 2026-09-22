import java.util.Scanner;


class Book {

    int bookId;
    String title;
    String author;
    double price;


    public Book(int bookId, String title, String author, double price) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }
}


public class LibraryManagement {

    static Scanner sc = new Scanner(System.in);


    // ============================================================
    // TASK 1: REMOVE DUPLICATES
    // ============================================================

    public static int removeDuplicates(Book[] books, int n) {

        if (n == 0) {
            return 0;
        }

        int uniqueCount = 1;

        for (int i = 1; i < n; i++) {

            if (books[i].bookId != books[uniqueCount - 1].bookId) {

                books[uniqueCount] = books[i];

                uniqueCount++;
            }
        }

        return uniqueCount;
    }


    // ============================================================
    // TASK 2: SEARCH BY PARTIAL TITLE
    // ============================================================

    public static void searchByTitle(
            Book[] books,
            int count,
            String query) {

        boolean found = false;

        query = query.toLowerCase();

        System.out.println("\nSearch Results:");

        for (int i = 0; i < count; i++) {

            if (books[i].title.toLowerCase().contains(query)) {

                System.out.println(
                        "[" + books[i].bookId + "] "
                                + books[i].title
                                + " - " + books[i].author
                                + " - Rs. " + books[i].price
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No book found.");
        }
    }


    // ============================================================
    // TASK 3: SORT BY PRICE
    // SELECTION SORT
    // ============================================================

    public static void sortByPrice(
            Book[] books,
            int count) {

        int swaps = 0;

        for (int i = 0; i < count - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < count; j++) {

                if (books[j].price < books[minIndex].price) {

                    minIndex = j;
                }
            }

            if (minIndex != i) {

                Book temp = books[i];

                books[i] = books[minIndex];

                books[minIndex] = temp;

                swaps++;
            }
        }

        System.out.println("\nBooks Sorted by Price:");

        displayBooks(books, count);

        System.out.println("Total Swaps: " + swaps);
    }


    // ============================================================
    // TASK 4: SEARCH BY PRICE
    // BINARY SEARCH
    // ============================================================

    public static int searchByPrice(
            Book[] books,
            int count,
            double targetPrice) {

        int left = 0;
        int right = count - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (books[mid].price == targetPrice) {

                return mid;
            }

            if (books[mid].price < targetPrice) {

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return -1;
    }


    // ============================================================
    // TASK 5: MINIMUM CONSECUTIVE BOOKS
    // SLIDING WINDOW
    // ============================================================

    public static int minBooksForTargetCost(
            Book[] books,
            int count,
            double targetCost) {

        int left = 0;

        double currentSum = 0;

        int minLength = Integer.MAX_VALUE;


        for (int right = 0; right < count; right++) {

            currentSum += books[right].price;


            while (currentSum >= targetCost) {

                int currentLength = right - left + 1;

                if (currentLength < minLength) {

                    minLength = currentLength;
                }

                currentSum -= books[left].price;

                left++;
            }
        }


        if (minLength == Integer.MAX_VALUE) {

            return 0;
        }

        return minLength;
    }


    // ============================================================
    // DISPLAY ALL BOOKS
    // ============================================================

    public static void displayBooks(
            Book[] books,
            int count) {

        if (count == 0) {

            System.out.println("No books available.");

            return;
        }

        for (int i = 0; i < count; i++) {

            System.out.println(
                    (i + 1)
                            + ". ["
                            + books[i].bookId
                            + "] "
                            + books[i].title
                            + " - "
                            + books[i].author
                            + " - Rs. "
                            + books[i].price
            );
        }
    }


    // ============================================================
    // INSERT BOOKS
    // ============================================================

    public static int insertBooks(Book[] books) {

        System.out.print("Enter number of books: ");

        int n = sc.nextInt();

        sc.nextLine();


        for (int i = 0; i < n; i++) {

            System.out.println(
                    "\nEnter details of Book " + (i + 1)
            );


            System.out.print("Enter Book ID: ");

            int id = sc.nextInt();

            sc.nextLine();


            System.out.print("Enter Book Title: ");

            String title = sc.nextLine();


            System.out.print("Enter Author: ");

            String author = sc.nextLine();


            System.out.print("Enter Price: ");

            double price = sc.nextDouble();

            sc.nextLine();


            books[i] = new Book(
                    id,
                    title,
                    author,
                    price
            );
        }

        return n;
    }


    // ============================================================
    // MAIN METHOD
    // ============================================================

    public static void main(String[] args) {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "       LIBRARY MANAGEMENT SYSTEM"
        );

        System.out.println(
                "=========================================="
        );


        // Maximum 100 books
        Book[] books = new Book[100];

        int count = 0;


        // ========================================================
        // MAIN MENU
        // ========================================================

        while (true) {

            System.out.println(
                    "\n=========================================="
            );

            System.out.println("1. Insert Books");

            System.out.println("2. Display Books");

            System.out.println("3. Remove Duplicate Books");

            System.out.println("4. Search Book by Title");

            System.out.println("5. Sort Books by Price");

            System.out.println("6. Search Book by Price");

            System.out.println("7. Minimum Books for Target Cost");

            System.out.println("8. Exit");

            System.out.println(
                    "=========================================="
            );


            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            sc.nextLine();


            // ====================================================
            // CHOICE 1: INSERT BOOKS
            // ====================================================

            if (choice == 1) {

                count = insertBooks(books);

                System.out.println(
                        "\nBooks inserted successfully."
                );
            }


            // ====================================================
            // CHOICE 2: DISPLAY BOOKS
            // ====================================================

            else if (choice == 2) {

                System.out.println("\nBook List:");

                displayBooks(books, count);
            }


            // ====================================================
            // CHOICE 3: REMOVE DUPLICATES
            // ====================================================

            else if (choice == 3) {

                if (count == 0) {

                    System.out.println(
                            "Please insert books first."
                    );

                } else {

                    count = removeDuplicates(
                            books,
                            count
                    );

                    System.out.println(
                            "\nDuplicates removed successfully."
                    );

                    System.out.println(
                            "Unique Books Count: " + count
                    );

                    displayBooks(
                            books,
                            count
                    );
                }
            }


            // ====================================================
            // CHOICE 4: SEARCH BY TITLE
            // ====================================================

            else if (choice == 4) {

                if (count == 0) {

                    System.out.println(
                            "Please insert books first."
                    );

                } else {

                    System.out.print(
                            "Enter title or part of title: "
                    );

                    String query = sc.nextLine();

                    searchByTitle(
                            books,
                            count,
                            query
                    );
                }
            }


            // ====================================================
            // CHOICE 5: SORT BY PRICE
            // ====================================================

            else if (choice == 5) {

                if (count == 0) {

                    System.out.println(
                            "Please insert books first."
                    );

                } else {

                    sortByPrice(
                            books,
                            count
                    );
                }
            }


            // ====================================================
            // CHOICE 6: SEARCH BY PRICE
            // ====================================================

            else if (choice == 6) {

                if (count == 0) {

                    System.out.println(
                            "Please insert books first."
                    );

                } else {

                    System.out.print(
                            "Enter price to search: "
                    );

                    double price = sc.nextDouble();

                    int index = searchByPrice(
                            books,
                            count,
                            price
                    );


                    if (index != -1) {

                        System.out.println(
                                "\nBook Found!"
                        );

                        System.out.println(
                                "[" + books[index].bookId + "] "
                                        + books[index].title
                                        + " - "
                                        + books[index].author
                                        + " - Rs. "
                                        + books[index].price
                        );

                    } else {

                        System.out.println(
                                "\nBook with this price not found."
                        );
                    }
                }
            }


            // ====================================================
            // CHOICE 7: SLIDING WINDOW
            // ====================================================

            else if (choice == 7) {

                if (count == 0) {

                    System.out.println(
                            "Please insert books first."
                    );

                } else {

                    System.out.print(
                            "Enter target cost: "
                    );

                    double targetCost = sc.nextDouble();


                    int result =
                            minBooksForTargetCost(
                                    books,
                                    count,
                                    targetCost
                            );


                    if (result == 0) {

                        System.out.println(
                                "\nNo consecutive group of books "
                                        + "can reach the target cost."
                        );

                    } else {

                        System.out.println(
                                "\nMinimum Consecutive Books Needed: "
                                        + result
                        );
                    }
                }
            }


            // ====================================================
            // CHOICE 8: EXIT
            // ====================================================

            else if (choice == 8) {

                System.out.println(
                        "\nThank you for using "
                                + "Library Management System!"
                );

                break;
            }


            // ====================================================
            // INVALID CHOICE
            // ====================================================

            else {

                System.out.println(
                        "Invalid choice. Please try again."
                );
            }
        }


        sc.close();
    }
}