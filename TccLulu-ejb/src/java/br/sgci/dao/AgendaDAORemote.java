/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sgci.dao;

import br.sgci.bean.Agenda;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface AgendaDAORemote {
    public boolean gravar(Agenda agenda);

    public Agenda selecionar(int id);

    public boolean remover(Agenda agenda);

    public java.util.List<Agenda> listar();
    
}
