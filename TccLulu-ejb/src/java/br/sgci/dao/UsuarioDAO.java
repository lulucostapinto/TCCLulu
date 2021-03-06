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

    @Override
    public boolean gravar(Usuario usuario) {
        boolean sucesso = false;
        try {
            em.merge(usuario);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Usuario selecionar(int id) {
        Usuario usuario = null;
        try {
            usuario = em.find(Usuario.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    @Override
    public boolean remover(Usuario usuario) {
        boolean sucesso = false;
        try {
            usuario = em.find(Usuario.class, usuario.getId());
            em.remove(usuario);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = null;
        try {
            Query query = em.createQuery("Select u from Usuario u");
            usuarios = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public Usuario selecionarPorLogin(String login) {

        try {
            Query query = em.createNamedQuery("Usuario.procuraPorLogin");
            Usuario usuario = (Usuario) query.setParameter("login", login).getSingleResult();
            return usuario;
        } catch (Exception e) {
            return null;
        }


    }
}
