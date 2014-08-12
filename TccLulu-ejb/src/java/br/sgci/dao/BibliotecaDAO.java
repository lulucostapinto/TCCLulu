/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sgci.dao;

import br.sgci.bean.Agenda;
import br.sgci.bean.Biblioteca;
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
public class BibliotecaDAO implements BibliotecaDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Biblioteca biblioteca) {
        boolean sucesso = false;
        try {
            em.merge(biblioteca);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Biblioteca selecionar(int id) {
        Biblioteca biblioteca = null;
        try {
            biblioteca = em.find(Biblioteca.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return biblioteca;
    }

    @Override
    public boolean remover(Biblioteca biblioteca) {
        boolean sucesso = false;
        try {
            biblioteca = em.find(Biblioteca.class, biblioteca.getId());
            em.remove(biblioteca);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Biblioteca> listar() {
        List<Biblioteca> biblioteca = null;
        try {
            Query query = em.createQuery("Select b from Biblioteca b");
            biblioteca = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return biblioteca;
    }
}
