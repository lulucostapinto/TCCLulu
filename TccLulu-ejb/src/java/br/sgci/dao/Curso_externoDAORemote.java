/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Curso_externo;
import br.sgci.bean.Pessoa;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface Curso_externoDAORemote {
    
    public boolean gravar(Curso_externo curso_externo);
    
    public boolean gravarArquivo(Curso_externo curso_externo);

    public Curso_externo selecionar(int id);
    
    public Curso_externo retrieve(Curso_externo value);

    public boolean remover(Curso_externo curso_externo);

    public java.util.List<Curso_externo> listar();
    
    public Long totalizar (Pessoa pessoa);
    
}
