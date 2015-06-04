/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Monografia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lulu
 */
@Stateless
public class MonografiaDAO implements MonografiaDAORemote {

   @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Monografia monografia) {
        boolean sucesso = false;
        try {
            em.merge(monografia);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Monografia selecionar(int id) {
        Monografia monografia = null;
        try {
            monografia = em.find(Monografia.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monografia;
    }

    @Override
    public void deletar(Monografia value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Monografia value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Monografia value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Monografia> listar() {
        List<Monografia> monografia = null;
        try {
            Query query = em.createQuery("Select m from Monografia m");
            monografia = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monografia;
    }

    @Override
    public Monografia retrieve(Monografia value) {
        return this.selecionar(value.getId());
    }

    @Override
    public boolean gravarArquivo(Monografia monografia) {
        Monografia monografiaTemp = this.selecionar(monografia.getId());
        monografiaTemp.setArquivo(monografia.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(monografiaTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
