package com.stproject.prototype.controler;

import com.stproject.prototype.api.error.NotFoundExeption;
import com.stproject.prototype.entity.User;
import com.stproject.prototype.utile.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class UserControler {

    public List<User> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<User> query = session.createQuery("select u from user u", User.class);
        return query.getResultList();
    }

    public User getById (int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = session.get(User.class, id);
        if(user == null) {
            throw new NotFoundExeption(id);
        }
        return user;
    }

    public User insert (String civility, String nameFirst, String nameLast, String address, String zipCode, String city, Date date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = new User(civility,nameFirst,nameLast,address,zipCode,city,date);
        session.save(user);
        return user;
    }

    public User update (int id, String civility, String nameFirst, String nameLast, String address, String zipCode, String city, Date date){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = session.get(User.class, id);
        if(user == null) {
            throw new NotFoundExeption(id);
        }
        user.setCivility(civility);
        user.setNameFirst(nameFirst);
        user.setNameLast(nameLast);
        user.setAddress(address);
        user.setZipCode(zipCode);
        user.setCity(city);
        user.setDate_creation(date);
        return (User) session.merge(user);
    }

    public void Delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = session.get(User.class,id);
        if(user == null) {
            throw new NotFoundExeption(id);
        }
        session.delete(user);
    }
}
