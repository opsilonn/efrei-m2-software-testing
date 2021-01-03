package com.stproject.prototype.controler;

import com.stproject.prototype.api.error.NotFoundExeption;
import com.stproject.prototype.entity.Contract;
import com.stproject.prototype.entity.User;
import com.stproject.prototype.utile.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class ContractControler {

    public List<Contract> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Contract> query = session.createQuery("select c from contract c", Contract.class);
        return query.getResultList();
    }

    public Contract getByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Contract contract = session.get(Contract.class,id);
        if(contract == null) {
            throw new NotFoundExeption(id);
        }
        return contract;
    }

    public Contract insert(float price, Date date, float electricalConsumption, int idUser) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = session.get(User.class, idUser);
        Contract contract = new Contract(price,date,electricalConsumption,user);
        session.save(contract);
        return contract;
    }

    public Contract update( int id, float price, Date date, float electricalConsumption, int idUser) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = session.get(User.class,idUser);
        Contract contract = session.get(Contract.class,id);
        if(user == null) {
            throw new NotFoundExeption(idUser);
        }
        if(contract == null) {
            throw new NotFoundExeption(id);
        }
        contract.setPrice(price);
        contract.setDate(date);
        contract.setElectricalConsumption(electricalConsumption);
        contract.setUser_idUser(user);
        return (Contract) session.merge(contract);
    }

    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Contract contract = session.get(Contract.class,id);
        if(contract == null) {
            // throw new NotFoundExeption(id);
        }
        session.delete(contract);
    }
}
