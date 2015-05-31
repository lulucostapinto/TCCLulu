/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Sala;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface SalaDAORemote {

    public boolean gravar(Sala sala);

    public Sala selecionar(int id);

    public Sala retrieve(Sala value);

    void deletar(Sala value);

    public java.util.List<Sala> listar();

    void alterar(Sala value);

    boolean valida(Sala value);

}
