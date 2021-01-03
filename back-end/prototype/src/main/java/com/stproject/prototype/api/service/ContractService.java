package com.stproject.prototype.api.service;

import com.stproject.prototype.controler.ContractControler;
import com.stproject.prototype.entity.Contract;
import com.stproject.prototype.utile.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractService {

    @Autowired
    ContractControler contractControler;

    @GetMapping
    @ResponseBody
    public List<Contract> getAll() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            List<Contract> contracts = contractControler.getAll();
            return contracts;
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

    @GetMapping("/{id}")
    @ResponseBody
    public Contract getByID(@PathVariable("id") int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Contract contract = contractControler.getByID(id);
            return contract;
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
    public Contract postContract(@RequestBody Contract contract) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Contract newcontract = contractControler.insert(contract.getPrice(),contract.getDate(),contract.getElectricalConsumption(),contract.getUser_idUser().getIdUser());
            return newcontract;
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
    public Contract putContact(@RequestBody Contract contract, @PathVariable("id") int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            Contract newcontract = contractControler.update(id,contract.getPrice(),contract.getDate(),contract.getElectricalConsumption(),contract.getUser_idUser().getIdUser());
            tx.commit();
            return newcontract;
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
            this.contractControler.delete(id);
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

    public ContractControler getContractControler() {
        return contractControler;
    }

    public void setContractControler(ContractControler contractControler) {
        this.contractControler = contractControler;
    }
}
