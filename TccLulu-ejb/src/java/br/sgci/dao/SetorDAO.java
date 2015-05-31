/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Setor;
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
public class SetorDAO implements SetorDAORemote {
@PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Setor setor) {
        boolean sucesso = false;
        try {
            em.merge(setor);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Setor selecionar(int id) {
        Setor setor = null;
        try {
            setor = em.find(Setor.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return setor;
    }

    @Override
    public void deletar(Setor value) {
        value = this.retrieve(value);
        em.remove(value);
    }
    
    @Override
    public void alterar(Setor value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }
    
    @Override
    public boolean valida(Setor value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Setor> listar() {
        List<Setor> setor = null;
        try {
            Query query = em.createQuery("Select st from Setor st");
            setor = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return setor;
    }

    @Override
    public Setor retrieve(Setor value) {
        return this.selecionar(value.getId());
    }

}
