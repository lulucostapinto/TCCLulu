/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Curso_externo;
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
public class Curso_externoDAO implements Curso_externoDAORemote {
@PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Curso_externo curso_externo) {
        boolean sucesso = false;
        try {
            em.merge(curso_externo);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Curso_externo selecionar(int id) {
        Curso_externo curso_externo = null;
        try {
            curso_externo = em.find(Curso_externo.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return curso_externo;
    }

    @Override
    public boolean remover(Curso_externo curso_externo) {
        boolean sucesso = false;
        try {
            curso_externo = em.find(Curso_externo.class, curso_externo.getId());
            em.remove(curso_externo);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Curso_externo> listar() {
        List<Curso_externo> curso_externo = null;
        try {
            Query query = em.createQuery("Select p from Curso_externo p");
            curso_externo = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return curso_externo;
    }

    @Override
    public Curso_externo retrieve(Curso_externo value) {
        return this.selecionar(value.getId());
    }
    
    @Override
    public boolean gravarArquivo(Curso_externo curso_externo) {
        Curso_externo curso_externoTemp = this.selecionar(curso_externo.getId());
        curso_externoTemp.setArquivo(curso_externo.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(curso_externoTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

}