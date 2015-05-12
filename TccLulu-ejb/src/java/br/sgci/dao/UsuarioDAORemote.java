/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Usuario;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface UsuarioDAORemote {
    public boolean gravar(Usuario usuario);
    Usuario findByLogin(String login);
    public java.util.List<Usuario> listar();
}
