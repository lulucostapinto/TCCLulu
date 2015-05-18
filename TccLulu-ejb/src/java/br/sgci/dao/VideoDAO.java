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
    public boolean remover(Video video) {
        boolean sucesso = false;
        try {
            video = em.find(Video.class, video.getId());
            em.remove(video);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    @Override
    public List<Video> listar() {
        List<Video> video = null;
        try {
            Query query = em.createQuery("Select p from Video p");
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

}