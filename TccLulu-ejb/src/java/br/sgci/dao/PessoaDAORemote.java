/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Pessoa;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface PessoaDAORemote {
    
    public boolean gravar(Pessoa pessoa);

    public Pessoa selecionar(int id);
    
    public Pessoa retrieve(Pessoa value);

    public boolean remover(Pessoa sala);

    public java.util.List<Pessoa> listar();
    
}
