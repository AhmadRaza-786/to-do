package com.example.todo.halper;

import com.example.todo.model.Task;

import java.util.List;

public interface InterfaceData {

    public boolean save(Task task);
    public boolean update(Task task);
    public boolean delete(Task task);
    public List<Task> list();

}
