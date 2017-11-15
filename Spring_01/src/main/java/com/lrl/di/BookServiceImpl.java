package com.lrl.di;

public class BookServiceImpl implements BookService {
    //1.方式1
//    private BookDao bookDao = new BookDaoImpl();
    //2.方式2
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void addBook() {
        this.bookDao.save();

    }
}
