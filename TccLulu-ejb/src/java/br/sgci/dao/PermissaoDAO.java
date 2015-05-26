/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Permissao;
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
public class PermissaoDAO implements PermissaoDAORemote{

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Permissao permissao){
        boolean sucesso = false;
        try {
            em.merge(permissao);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Permissao selecionar(int id){
       Permissao permissao = null;
        try {
            permissao = em.find(Permissao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return permissao;
    }

    @Override
    public boolean remover(Permissao permissao){
        boolean sucesso = false;
        try {
            permissao = em.find(Permissao.class, permissao.getId());
            em.remove(permissao);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Permissao> listar() {
        List<Permissao> permissao = null;
        try {
            Query query = em.createQuery("Select p from Permissao p");
            permissao = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return permissao;
    }
}