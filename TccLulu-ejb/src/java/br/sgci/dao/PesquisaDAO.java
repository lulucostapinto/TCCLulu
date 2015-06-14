/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Pesquisa;
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
public class PesquisaDAO implements PesquisaDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Pesquisa pesquisa) {
        boolean sucesso = false;
        try {
            em.merge(pesquisa);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Pesquisa selecionar(int id) {
        Pesquisa pesquisa = null;
        try {
            pesquisa = em.find(Pesquisa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pesquisa;
    }

    @Override
    public void deletar(Pesquisa value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Pesquisa value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Pesquisa value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Pesquisa> listar() {
        List<Pesquisa> pesquisa = null;
        try {
            Query query = em.createQuery("Select p from Pesquisa p");
            pesquisa = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pesquisa;
    }

    @Override
    public Pesquisa retrieve(Pesquisa value) {
        return this.selecionar(value.getId());
    }

    @Override
    public boolean gravarArquivo(Pesquisa pesquisa) {
        Pesquisa pesquisaTemp = this.selecionar(pesquisa.getId());
        pesquisaTemp.setArquivo(pesquisa.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(pesquisaTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

}
