/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;


import br.sgci.bean.Inscricao;
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
public class InscricaoDAO implements InscricaoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Inscricao inscricao) {
        boolean sucesso = false;
        try {
            em.merge(inscricao);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Inscricao selecionar(int id) {
        Inscricao inscricao = null;
        try {
            inscricao = em.find(Inscricao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inscricao;
    }

    @Override
    public boolean remover(Inscricao inscricao) {
        boolean sucesso = false;
        try {
            inscricao = em.find(Inscricao.class, inscricao.getId());
            em.remove(inscricao);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Inscricao> listar() {
        List<Inscricao> inscricoes = null;
        try {
            Query query = em.createQuery("Select i from Incricao i");
            inscricoes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inscricoes;
    }
}
