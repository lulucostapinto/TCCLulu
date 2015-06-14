/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Conteudo;
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
public class ConteudoDAO implements ConteudoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Conteudo conteudo) {
        boolean sucesso = false;
        try {
            em.merge(conteudo);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Conteudo selecionar(int id) {
        Conteudo conteudo = null;
        try {
            conteudo = em.find(Conteudo.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conteudo;
    }

    @Override
    public void deletar(Conteudo value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Conteudo value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Conteudo value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Conteudo> listar() {
        List<Conteudo> conteudo = null;
        try {
            Query query = em.createQuery("Select c from Conteudo c");
            conteudo = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conteudo;
    }

    @Override
    public Conteudo retrieve(Conteudo value) {
        return this.selecionar(value.getId());
    }

    @Override
    public boolean gravarArquivo(Conteudo conteudo) {
        Conteudo conteudoTemp = this.selecionar(conteudo.getId());
        conteudoTemp.setArquivo(conteudo.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(conteudoTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

}
