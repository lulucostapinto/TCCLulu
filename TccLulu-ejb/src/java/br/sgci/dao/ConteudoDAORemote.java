/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Conteudo;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface ConteudoDAORemote {
    
    public boolean gravar(Conteudo conteudo);
    
    public boolean gravarArquivo(Conteudo conteudo);

    public Conteudo selecionar(int id);

    public Conteudo retrieve(Conteudo value);

    void deletar(Conteudo value);

    public java.util.List<Conteudo> listar();

    void alterar(Conteudo value);

    boolean valida(Conteudo value);
    
}
