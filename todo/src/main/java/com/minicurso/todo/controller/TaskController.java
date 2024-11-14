package com.minicurso.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minicurso.todo.entity.Task;
import com.minicurso.todo.repository.TaskRepository;

/**
 * O TaskController é responsável por definir os endpoints da API REST para gerenciar
 * as operações de CRUD relacionadas à entidade Task.
 */
@RestController // Marca a classe como um controlador REST, permitindo que ela processe e retorne JSON ou outros formatos de dados.
@RequestMapping("/api/tasks") // Define o caminho base para todos os endpoints dessa classe.
@CrossOrigin(origins = "http://localhost:3000") // Permite requisições de uma origem específica (como o frontend em React, hospedado no localhost na porta 3000).
public class TaskController {

    @Autowired // Injeta o repositório TaskRepository para que possa ser usado sem inicialização manual.
    private TaskRepository taskRepository;

    /**
     * Endpoint para listar todas as tarefas.
     * 
     * @return Uma lista contendo todas as tarefas armazenadas no banco de dados.
     */
    @GetMapping // Define que esse método será chamado em requisições HTTP GET para "/api/tasks".
    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // Busca e retorna todas as tarefas do banco de dados.
    }

    /**
     * Endpoint para criar uma nova tarefa.
     * 
     * @param task Objeto Task enviado no corpo da requisição.
     * @return A tarefa recém-criada, salva no banco de dados.
     */
    @PostMapping // Define que esse método será chamado em requisições HTTP POST para "/api/tasks".
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task); // Salva a nova tarefa no banco de dados e retorna o objeto salvo.
    }

    /**
     * Endpoint para atualizar uma tarefa existente.
     * 
     * @param id ID da tarefa a ser atualizada, extraído da URL.
     * @param taskDetails Objeto Task contendo os dados atualizados da tarefa.
     * @return A tarefa atualizada.
     */
    @PutMapping("/{id}") // Define que esse método será chamado em requisições HTTP PUT para "/api/tasks/{id}".
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        // Busca a tarefa pelo ID, ou lança uma exceção se não for encontrada.
        Task task = taskRepository.findById(id).orElseThrow();
        // Atualiza os campos necessários da tarefa. 
        // task.setName(taskDetails.getName()); // Comentado, mas pode ser habilitado se o nome for editável.
        task.setCompleted(taskDetails.getCompleted()); // Atualiza o status "completed".
        return taskRepository.save(task); // Salva as alterações no banco de dados e retorna a tarefa atualizada.
    }

    /**
     * Endpoint para excluir uma tarefa pelo ID.
     * 
     * @param id ID da tarefa a ser excluída, extraído da URL.
     */
    @DeleteMapping("/{id}") // Define que esse método será chamado em requisições HTTP DELETE para "/api/tasks/{id}".
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id); // Remove a tarefa com o ID especificado do banco de dados.
    }
}