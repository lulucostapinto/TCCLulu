/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Pesquisa;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface PesquisaDAORemote {
    
    public boolean gravar(Pesquisa pesquisa);
    
    public boolean gravarArquivo(Pesquisa pesquisa);

    public Pesquisa selecionar(int id);

    public Pesquisa retrieve(Pesquisa value);

    void deletar(Pesquisa value);

    public java.util.List<Pesquisa> listar();

    void alterar(Pesquisa value);

    boolean valida(Pesquisa value);
    
}
