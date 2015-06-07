/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Trabalho;
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
public class TrabalhoDAO implements TrabalhoDAORemote {

   @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Trabalho trabalho) {
        boolean sucesso = false;
        try {
            em.merge(trabalho);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Trabalho selecionar(int id) {
        Trabalho trabalho = null;
        try {
            trabalho = em.find(Trabalho.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trabalho;
    }

    @Override
    public void deletar(Trabalho value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Trabalho value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Trabalho value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Trabalho> listar() {
        List<Trabalho> trabalho = null;
        try {
            Query query = em.createQuery("Select m from Trabalho m");
            trabalho = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trabalho;
    }

    @Override
    public Trabalho retrieve(Trabalho value) {
        return this.selecionar(value.getId());
    }

    @Override
    public boolean gravarArquivo(Trabalho trabalho) {
        Trabalho trabalhoTemp = this.selecionar(trabalho.getId());
        trabalhoTemp.setArquivo(trabalho.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(trabalhoTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
