/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Video;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface VideoDAORemote {

    public boolean gravar(Video video);
    
    public boolean gravarArquivo(Video video);

    public Video selecionar(int id);

    public Video retrieve(Video value);

    void deletar(Video value);

    public java.util.List<Video> listar();

    void alterar(Video value);

    boolean valida(Video value);

}
