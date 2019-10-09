package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface TodoListRepository extends CrudRepository<TodoList, Long> {}
