/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Permissao;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface PermissaoDAORemote {
    
    public boolean gravar(Permissao permissao);

    public Permissao selecionar(int id);

    public boolean remover(Permissao permissao);

    public java.util.List<Permissao> listar();
    
}
