/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.adw.bookwebappnew.service;

import edu.wctc.adw.bookwebappnew.entity.Author;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class AuthorFacade extends AbstractFacade<Author> {
    @PersistenceContext(unitName = "book_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorFacade() {
        super(Author.class);
    }
    //paging allows you to select a given number of records
    public List<Author> findByName(String name){
    
        String jpql = "Select a from Author where a.authorName = ?1";
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter(1, name);
        return q.getResultList();
    }
}
