/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Solicitacao;
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
public class SolicitacaoDAO implements SolicitacaoDAORemote {
@PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Solicitacao solicitacao) {
        boolean sucesso = false;
        try {
            em.merge(solicitacao);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Solicitacao selecionar(int id) {
        Solicitacao solicitacao = null;
        try {
            solicitacao = em.find(Solicitacao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return solicitacao;
    }

    @Override
    public void deletar(Solicitacao value) {
        value = this.retrieve(value);
        em.remove(value);
    }
    
    @Override
    public void alterar(Solicitacao value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }
    
    @Override
    public boolean valida(Solicitacao value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Solicitacao> listar() {
        List<Solicitacao> solicitacao = null;
        try {
            Query query = em.createQuery("Select sl from Solicitacao sl");
            solicitacao = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return solicitacao;
    }

    @Override
    public Solicitacao retrieve(Solicitacao value) {
        return this.selecionar(value.getId());
    }

}
