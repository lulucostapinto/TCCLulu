/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Trabalhos_academicos;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface Trabalhos_academicosDAORemote {
    
    public boolean gravar(Trabalhos_academicos trabalhos_academicos);
    
    public boolean gravarArquivo(Trabalhos_academicos trabalhos_academicos);

    public Trabalhos_academicos selecionar(int id);

    public Trabalhos_academicos retrieve(Trabalhos_academicos value);

    void deletar(Trabalhos_academicos value);

    public java.util.List<Trabalhos_academicos> listar();

    void alterar(Trabalhos_academicos value);

    boolean valida(Trabalhos_academicos value);
    
}
