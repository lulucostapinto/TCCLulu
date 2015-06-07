/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Monografia;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface MonografiaDAORemote {
    
    public boolean gravar(Monografia monografia);
    
    public boolean gravarArquivo(Monografia monografia);

    public Monografia selecionar(int id);

    public Monografia retrieve(Monografia value);

    void deletar(Monografia value);

    public java.util.List<Monografia> listar();

    void alterar(Monografia value);

    boolean valida(Monografia value);
    
}
