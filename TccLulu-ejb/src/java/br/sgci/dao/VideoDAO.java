/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sgci.dao;

import br.sgci.bean.Video;
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
public class VideoDAO implements VideoDAORemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean gravar(Video video) {
        boolean sucesso = false;
        try {
            em.merge(video);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public Video selecionar(int id) {
        Video video = null;
        try {
            video = em.find(Video.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return video;
    }

    @Override
    public void deletar(Video value) {
        value = this.retrieve(value);
        em.remove(value);
    }

    @Override
    public void alterar(Video value) {
        if (this.valida(value)) {
            em.merge(value);
        }
    }

    @Override
    public boolean valida(Video value) {
        boolean ret = false;
        if (value != null) {
            ret = true;
        }
        return ret;
    }

    @Override
    public List<Video> listar() {
        List<Video> video = null;
        try {
            Query query = em.createQuery("Select v from Video v");
            video = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return video;
    }

    @Override
    public Video retrieve(Video value) {
        return this.selecionar(value.getId());
    }

    @Override
    public boolean gravarArquivo(Video video) {
        Video videoTemp = this.selecionar(video.getId());
        videoTemp.setArquivo(video.getArquivo());
        boolean sucesso = false;
        try {
            em.merge(videoTemp);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

}
