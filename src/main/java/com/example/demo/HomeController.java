package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TodoListRepository todoListRepository;

    @RequestMapping("/")
    public String listTodoLists(Model model){
        model.addAttribute("todoLists", todoListRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String todoListForm(Model model) {
        model.addAttribute("todoList", new TodoList());
        return "todoListform";
    }

    @PostMapping("/process")
    public String processForm(@Valid TodoList todoList, BindingResult result){
        if (result.hasErrors()){
            return "todoListform";
        }
        todoListRepository.save(todoList);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showTodoList(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("todoList", todoListRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("todoList", todoListRepository.findById(id).get());
        return "todoListform";
    }
    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        todoListRepository.deleteById(id);
        return "redirect:/";
    }

}
