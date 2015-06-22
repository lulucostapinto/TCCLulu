/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Certificado;
import javax.ejb.Remote;

/**
 *
 * @author Lulu
 */
@Remote
public interface CertificadoDAORemote {
    
    public boolean gravar(Certificado certificado);
    
    public boolean gravarArquivo(Certificado certificado);

    public Certificado selecionar(int id);

    public Certificado retrieve(Certificado value);

    void deletar(Certificado value);

    public java.util.List<Certificado> listar();

    void alterar(Certificado value);

    boolean valida(Certificado value);
    
}
