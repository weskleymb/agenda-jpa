package br.senac.rn.agenda.controller;

import java.util.List;

import br.senac.rn.agenda.dao.ContatoDAO;
import br.senac.rn.agenda.model.Contato;

public class ContatoCTRL {

    private ContatoDAO dao;
    
    public ContatoCTRL() {
        dao = new ContatoDAO();
    }
    
    public boolean adcionar(String nome, String fone) {
        Contato contato = new Contato();
        contato.setNome(nome.toUpperCase());
        contato.setFone(fone);
        return dao.insert(contato);
    }
    
    public boolean atualizar(int id, String nome, String fone) {
        Contato contato = new Contato();
        contato.setId(id);
        contato.setNome(nome.toUpperCase());
        contato.setFone(fone);
        return dao.update(contato);
    }
    
    public List<Contato> listar(String filter) {
        if (filter == null) {
            return dao.selectAll();
        } else {
            return dao.selectFilter(filter);
        }
    }
    
    public boolean remover(int id) {
        Contato contato = dao.select(id);
        return dao.delete(contato);
    }
    
}
