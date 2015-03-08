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
    public boolean remover(Sala sala) {
        boolean sucesso = false;
        try {
            sala = em.find(Sala.class, sala.getId());
            em.remove(sala);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
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

}
