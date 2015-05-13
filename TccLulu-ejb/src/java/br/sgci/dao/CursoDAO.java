/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sgci.dao;

import br.sgci.bean.Curso;
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
public class CursoDAO implements CursoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Curso curso) {
        boolean sucesso = false;
        try {
            em.merge(curso);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Curso selecionar(int id) {
        Curso curso = null;
        try {
            curso = em.find(Curso.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return curso;
    }

    @Override
    public boolean remover(Curso curso) {
        boolean sucesso = false;
        try {
            curso = em.find(Curso.class, curso.getId());
            em.remove(curso);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Curso> listar() {
        List<Curso> cursos = null;
        try {
            Query query = em.createQuery("Select c from Curso c order by c.data_inicio");
            cursos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursos;
    }
    
    @Override
    public Curso retrieve(Curso value) {
        Curso curso = em.find(Curso.class, value.getId());
        return curso;
    }
    
    @Override
    public void update(Curso value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }
    
    @Override
    public boolean valida(Curso value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

}
