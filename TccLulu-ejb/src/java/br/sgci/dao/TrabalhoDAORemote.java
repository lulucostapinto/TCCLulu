/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Trabalho;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface TrabalhoDAORemote {
    
    public boolean gravar(Trabalho trabalho);
    
    public boolean gravarArquivo(Trabalho trabalho);

    public Trabalho selecionar(int id);

    public Trabalho retrieve(Trabalho value);

    void deletar(Trabalho value);

    public java.util.List<Trabalho> listar();

    void alterar(Trabalho value);

    boolean valida(Trabalho value);
    
}
