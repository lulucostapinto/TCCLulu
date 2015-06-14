/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Trabalhos_academicos;
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
public class Trabalhos_academicosDAO implements Trabalhos_academicosDAORemote {

   @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Trabalhos_academicos trabalhos_academicos) {
        boolean sucesso = false;
        try {
            em.merge(trabalhos_academicos);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Trabalhos_academicos selecionar(int id) {
        Trabalhos_academicos trabalhos_academicos = null;
        try {
            trabalhos_academicos = em.find(Trabalhos_academicos.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trabalhos_academicos;
    }

    @Override
    public void deletar(Trabalhos_academicos value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Trabalhos_academicos value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Trabalhos_academicos value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Trabalhos_academicos> listar() {
        List<Trabalhos_academicos> trabalhos_academicos = null;
        try {
            Query query = em.createQuery("Select ta from Trabalhos_academicos ta");
            trabalhos_academicos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trabalhos_academicos;
    }

    @Override
    public Trabalhos_academicos retrieve(Trabalhos_academicos value) {
        return this.selecionar(value.getId());
    }

   @Override
    public boolean gravarArquivo(Trabalhos_academicos trabalhos_academicos) {
        Trabalhos_academicos trabalhos_academicosTemp = this.selecionar(trabalhos_academicos.getId());
        trabalhos_academicosTemp.setArquivo(trabalhos_academicos.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(trabalhos_academicosTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
