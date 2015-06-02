/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Solicitacao;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface SolicitacaoDAORemote {
    public boolean gravar(Solicitacao solicitacao);

    public Solicitacao selecionar(int id);

    public Solicitacao retrieve(Solicitacao value);

    void deletar(Solicitacao value);

    public java.util.List<Solicitacao> listar();

    void alterar(Solicitacao value);

    boolean valida(Solicitacao value);
    
}
