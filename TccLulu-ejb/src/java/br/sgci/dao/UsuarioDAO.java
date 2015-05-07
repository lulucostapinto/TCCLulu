/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lulu
 */
@Stateless
public class UsuarioDAO implements UsuarioDAORemote {
    @PersistenceContext
    private EntityManager em;

    public Usuario findByLogin(String login) {
        Query query = em.createNamedQuery("Usuario.findLogin");
        try {
            Usuario usuario = (Usuario) query.setParameter("login", login).getSingleResult();
            return usuario;
        } catch (NoResultException nre) {
            return null;
        }
    }

    
    public List<Usuario> listar() {
        List<Usuario> sala = null;
        try {
            Query query = em.createQuery("Select u from Usuario u");
            sala = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sala;
    }

}
