/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Tcc;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface TccDAORemote {
    
    public boolean gravar(Tcc tcc);
    
    public boolean gravarArquivo(Tcc tcc);

    public Tcc selecionar(int id);

    public Tcc retrieve(Tcc value);

    void deletar(Tcc value);

    public java.util.List<Tcc> listar();

    void alterar(Tcc value);

    boolean valida(Tcc value);
    
}
