/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Sala;
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
public class SalaDAO implements SalaDAORemote {
@PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Sala sala) {
        boolean sucesso = false;
        try {
            em.merge(sala);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Sala selecionar(int id) {
        Sala sala = null;
        try {
            sala = em.find(Sala.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sala;
    }

    @Override
    public void deletar(Sala value) {
        value = this.retrieve(value);
        em.remove(value);
    }
    
    @Override
    public void alterar(Sala value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }
    
    @Override
    public boolean valida(Sala value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Sala> listar() {
        List<Sala> sala = null;
        try {
            Query query = em.createQuery("Select s from Sala s");
            sala = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sala;
    }

    @Override
    public Sala retrieve(Sala value) {
        return this.selecionar(value.getId());
    }

}
