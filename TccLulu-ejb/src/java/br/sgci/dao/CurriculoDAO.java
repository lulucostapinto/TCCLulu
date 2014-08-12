/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sgci.dao;

import br.sgci.bean.Curriculo;
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
public class CurriculoDAO implements CurriculoDAORemote {

  @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Curriculo curriculo) {
        boolean sucesso = false;
        try {
            em.merge(curriculo);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Curriculo selecionar(int id) {
        Curriculo curriculo = null;
        try {
            curriculo = em.find(Curriculo.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return curriculo;
    }

    @Override
    public boolean remover(Curriculo curriculo) {
        boolean sucesso = false;
        try {
            curriculo = em.find(Curriculo.class, curriculo.getId());
            em.remove(curriculo);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Curriculo> listar() {
        List<Curriculo> curriculo = null;
        try {
            Query query = em.createQuery("Select c from Curriculo c");
            curriculo = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return curriculo;
    }
}
