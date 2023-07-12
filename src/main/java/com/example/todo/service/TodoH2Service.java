package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.todo.model.*;
import com.example.todo.repository.*;

@Service
public class TodoH2Service implements TodoRepository{

    @Autowired
    private JdbcTemplate db;

	@Override
	public ArrayList<Todo> getAllTodos() {
		// TODO Auto-generated method stub
		List<Todo> l=db.query("select * from TODOLIST",new TodoRowMapper());
		ArrayList<Todo> todos=new ArrayList<>(l);
		return todos;
	}

	@Override
	public Todo getTodoById(int id) {
		try{
			Todo t=db.queryForObject("select * from TODOLIST where ID=?",new TodoRowMapper(),id);
			return t;
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public Todo addTodo(Todo todo) {
		db.update("insert into TODOLIST(TODO,STATUS,PRIORITY) values (?,?,?)",todo.getTodo(),todo.getStatus(),todo.getPriority());
		Todo t=db.queryForObject("select * from TODOLIST where TODO=? and STATUS=? and PRIORITY=?",new TodoRowMapper(),todo.getTodo(),todo.getStatus(),todo.getPriority());
		return t;
	}

	@Override
	public Todo updateTodo(int id, Todo todo) {
		if (todo.getTodo()!=null){
			db.update("update TODOLIST set TODO=? where ID=?",todo.getTodo(),id);
		}
		if (todo.getStatus()!=null){
			db.update("update TODOLIST set STATUS=? where ID=?",todo.getStatus(),id);
		}
		if (todo.getPriority()!=null){
			db.update("update TODOLIST set PRIORITY=? where ID=?",todo.getPriority(),id);
		}
		return getTodoById(id);
	}

	@Override
	public void deleteTodo(int id) {
		try{
			db.update("delete from TODOLIST where ID=?",id);
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}

    

}

