/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Curso;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface CursoDAORemote {

    public boolean gravar(Curso curso);

    public Curso selecionar(int id);

    void deletar(Curso value);

    public java.util.List<Curso> listar();

    Curso retrieve(Curso value);

    void alterar(Curso value);

    boolean valida(Curso value);

}
