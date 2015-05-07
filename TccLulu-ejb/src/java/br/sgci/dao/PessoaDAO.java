/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Pessoa;
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
public class PessoaDAO implements PessoaDAORemote {
@PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Pessoa pessoa) {
        boolean sucesso = false;
        try {
            em.merge(pessoa);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Pessoa selecionar(int id) {
        Pessoa pessoa = null;
        try {
            pessoa = em.find(Pessoa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    @Override
    public boolean remover(Pessoa pessoa) {
        boolean sucesso = false;
        try {
            pessoa = em.find(Pessoa.class, pessoa.getId());
            em.remove(pessoa);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Pessoa> listar() {
        List<Pessoa> pessoa = null;
        try {
            Query query = em.createQuery("Select p from Pessoa p");
            pessoa = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    @Override
    public Pessoa retrieve(Pessoa value) {
        return this.selecionar(value.getId());
    }

}
