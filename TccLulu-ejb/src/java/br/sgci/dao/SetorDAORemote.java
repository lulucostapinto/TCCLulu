/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Setor;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface SetorDAORemote {
    public boolean gravar(Setor setor);

    public Setor selecionar(int id);
    
    public Setor retrieve(Setor value);

    public boolean remover(Setor setor);

    public java.util.List<Setor> listar();
    
}
