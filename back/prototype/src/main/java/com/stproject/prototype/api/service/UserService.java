package com.stproject.prototype.api.service;

import com.stproject.prototype.controler.UserControler;
import com.stproject.prototype.entity.User;
import com.stproject.prototype.utile.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserService {

    @Autowired
    UserControler userControler;

    @GetMapping
    @ResponseBody
    public List<User> getAll() {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            List<User> users = userControler.getAll();
            return  users;
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getById(@PathVariable("id") int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            User user = userControler.getById(id);
            return user;
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @PostMapping
    @ResponseBody
    public User postUser(@RequestBody User user) {
        Session session = null;
        Transaction tx = null;
        System.out.println(user);
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            User newUser = userControler.insert(user.getCivility(),user.getNameFirst(),user.getNameLast(),user.getAddress(),user.getZipCode(),user.getCity(),user.getDate_creation());
            tx.commit();
            return newUser;
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public User puteUser(@RequestBody User user, @PathVariable("id") int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            User newUser = userControler.update(id,user.getCivility(),user.getNameFirst(),user.getNameLast(),user.getAddress(),user.getZipCode(),user.getCity(),user.getDate_creation());
            tx.commit();
            return newUser;
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @DeleteMapping("/{id}")
    public Boolean DeleteUser (@PathVariable("id") int id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            this.userControler.Delete(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            if(session != null) {
                session.close();
            }
        }
        return false;
    }

    public UserControler getUserControler() {
        return userControler;
    }

    public void setUserControler(UserControler userControler) {
        this.userControler = userControler;
    }
}
