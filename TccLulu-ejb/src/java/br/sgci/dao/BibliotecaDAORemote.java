/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.sgci.dao;


import br.sgci.bean.Biblioteca;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface BibliotecaDAORemote {
    
    public boolean gravar(Biblioteca biblioteca);

    public Biblioteca selecionar(int id);

    public boolean remover(Biblioteca biblioteca);

    public java.util.List<Biblioteca> listar();
    
}
