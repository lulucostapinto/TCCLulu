/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Inscricao;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface InscricaoDAORemote {
    public boolean gravar(Inscricao inscricao);

    public Inscricao selecionar(int id);

    public boolean remover(Inscricao inscricao);

    public java.util.List<Inscricao> listar();
    
    public Inscricao retrieve(Inscricao value);
    
}
