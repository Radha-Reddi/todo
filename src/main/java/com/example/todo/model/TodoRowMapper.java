package com.example.todo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
 

public class TodoRowMapper implements RowMapper<Todo>{

	@Override
	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Todo(
            rs.getInt("ID"),
            rs.getString("TODO"),
            rs.getString("STATUS"),
            rs.getString("PRIORITY")
        );
	}

    

}
