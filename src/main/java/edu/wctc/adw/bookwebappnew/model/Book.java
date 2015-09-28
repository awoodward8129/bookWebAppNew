/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.adw.bookwebappnew.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Alex
 */
public class Book {
    private Integer bookId;
    private String title;
    private String author;
    private Integer pageCount;
    private Date publishDate;

    public Book() {
    }

    public Book(Integer bookId, String title, String author, Integer pageCount, Date publishDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.publishDate = publishDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.bookId);
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.author);
        hash = 17 * hash + Objects.hashCode(this.pageCount);
        hash = 17 * hash + Objects.hashCode(this.publishDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.bookId, other.bookId)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.pageCount, other.pageCount)) {
            return false;
        }
        if (!Objects.equals(this.publishDate, other.publishDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "bookId=" + bookId + ", title=" + title + ", author=" + author + ", pageCount=" + pageCount + ", publishDate=" + publishDate + '}';
    }
    
    
}
