/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Usuario;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface UsuarioDAORemote {

    public boolean gravar(Usuario usuario);

    public Usuario selecionar(int id);

    public Usuario selecionarPorLogin(String nome);

    public boolean remover(Usuario usuario);

    public java.util.List<Usuario> listar();
}
