/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Agenda;
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
public class AgendaDAO implements AgendaDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Agenda agenda) {
        boolean sucesso = false;
        try {
            em.merge(agenda);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Agenda selecionar(int id) {
        Agenda agenda = null;
        try {
            agenda = em.find(Agenda.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return agenda;
    }

    @Override
    public boolean remover(Agenda agenda) {
        boolean sucesso = false;
        try {
            agenda = em.find(Agenda.class, agenda.getId());
            em.remove(agenda);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Agenda> listar() {
        List<Agenda> agenda = null;
        try {
            Query query = em.createQuery("Select a from Agenda a");
            agenda = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return agenda;
    }

}
