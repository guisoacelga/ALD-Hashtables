package UE05_LibraryHashWithList_Loesung;

import java.util.Iterator;
import java.util.LinkedList;

public class LibraryHashWithList {
    // Liste, die je Position aus einer LinkedList besteht
    private LinkedList<Book>[] list;

    public LibraryHashWithList(int size) {
        list = new LinkedList[size];
    }

    public boolean addBook(Book newBook)
    {
        int listIndex = getIndexByHashCalc(newBook.getId());

        if (list[listIndex] == null)
            list[listIndex] = new LinkedList<Book>();

        return list[listIndex].add(newBook);
    }

    public Book getBookById(int id)
    {
        Book currentBook;
        LinkedList<Book> linkedList = list[getIndexByHashCalc(id)];
        Iterator it = linkedList.iterator();
        while (it.hasNext())
        {
            currentBook = (Book)it.next();
            if (currentBook.getId() == id)
                return currentBook;
        }

        return null;
    }

    public boolean removeBook(int id)
    {
        Book book = getBookById(id);
        if (book == null)
            return false;

        int listIndex = getIndexByHashCalc(book.getId());
        LinkedList<Book> linkedList = list[listIndex];
        return linkedList.remove(book);
    }

    public LinkedList<Book> getLinkedListAtPos(int pos)
    {
        return list[pos];
    }

    private int getIndexByHashCalc(int id)
    {
        return id % list.length;
    }
}
