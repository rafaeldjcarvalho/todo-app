package com.minicurso.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minicurso.todo.entity.Task;

/**
 * A interface TaskRepository é responsável por realizar as operações de acesso
 * ao banco de dados para a entidade Task. Ela utiliza o JpaRepository para
 * fornecer uma implementação padrão de CRUD e outras operações.
 */
@Repository // Indica que essa interface é um repositório Spring e será gerenciada pelo Spring Container.
public interface TaskRepository extends JpaRepository<Task, Long> {
    // A interface JpaRepository já fornece os métodos básicos de CRUD:
    // - save() -> para criar ou atualizar uma entidade.
    // - findById() -> para buscar uma entidade pelo ID.
    // - findAll() -> para listar todas as entidades.
    // - deleteById() -> para excluir uma entidade pelo ID.
    // 
    // Não é necessário implementar os métodos, pois o Spring Data JPA gera a implementação automaticamente.
}
