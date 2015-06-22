/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Certificado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lulu
 */
@Stateless
public class CertificadoDAO implements CertificadoDAORemote {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Certificado certificado) {
        boolean sucesso = false;
        try {
            em.merge(certificado);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Certificado selecionar(int id) {
        Certificado certificado = null;
        try {
            certificado = em.find(Certificado.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return certificado;
    }

    @Override
    public void deletar(Certificado value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Certificado value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Certificado value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Certificado> listar() {
        List<Certificado> certificado = null;
        try {
            Query query = em.createQuery("Select c from Certificado c");
            certificado = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return certificado;
    }

    @Override
    public Certificado retrieve(Certificado value) {
        return this.selecionar(value.getId());
    }

@Override
    public boolean gravarArquivo(Certificado certificado) {
        Certificado certificadoTemp = this.selecionar(certificado.getId());
        certificadoTemp.setArquivo(certificado.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(certificadoTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }
}
