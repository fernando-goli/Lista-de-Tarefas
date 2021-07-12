package com.example.todo.helper;

import com.example.todo.model.Tarefa;

import java.util.List;

public interface ITArefaDAO {

    public boolean salvar(Tarefa tarefa);
    public  boolean atualizar(Tarefa tarefa);
    public boolean deletar(Tarefa tarefa);
    public List<Tarefa> listar();

}
