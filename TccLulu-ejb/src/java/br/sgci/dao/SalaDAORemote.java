/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Sala;
import javax.ejb.Local;

/**
 *
 * @author Lulu
 */
@Local
public interface SalaDAORemote {
    public boolean gravar(Sala sala);

    public Sala selecionar(int id);

    public boolean remover(Sala sala);

    public java.util.List<Sala> listar();
    
}
