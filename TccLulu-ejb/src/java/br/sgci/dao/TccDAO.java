/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Tcc;
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
public class TccDAO implements TccDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Tcc tcc) {
        boolean sucesso = false;
        try {
            em.merge(tcc);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Tcc selecionar(int id) {
        Tcc tcc = null;
        try {
            tcc = em.find(Tcc.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tcc;
    }

    @Override
    public void deletar(Tcc value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Tcc value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Tcc value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Tcc> listar() {
        List<Tcc> tcc = null;
        try {
            Query query = em.createQuery("Select t from Tcc t");
            tcc = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tcc;
    }

    @Override
    public Tcc retrieve(Tcc value) {
        return this.selecionar(value.getId());
    }

    @Override
    public boolean gravarArquivo(Tcc tcc) {
        Tcc tccTemp = this.selecionar(tcc.getId());
        tccTemp.setArquivo(tcc.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(tccTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
