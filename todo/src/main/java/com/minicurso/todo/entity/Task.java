package com.minicurso.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * A classe Task representa uma entidade no banco de dados para uma tarefa.
 * Cada instância dessa classe corresponde a um registro na tabela do banco.
 */
@Entity // Indica que essa classe será mapeada para uma tabela no banco de dados.
public class Task {
	
	@Id // Marca o campo 'id' como a chave primária da tabela.
    @GeneratedValue(strategy = GenerationType.AUTO) // Gera o valor do 'id' automaticamente.
    private Long id; // Identificador único da tarefa.

    private String name; // Nome ou descrição da tarefa.
    
    private Boolean completed; // Indica se a tarefa foi concluída (true) ou não (false).
    
    /**
     * Construtor padrão sem argumentos.
     * Necessário para que o JPA possa instanciar objetos dessa classe.
     */
	public Task() {
		super();
	}
	
    /**
     * Construtor com argumentos.
     * Permite criar uma instância da classe fornecendo valores para os atributos.
     * 
     * @param id Identificador único da tarefa.
     * @param name Nome ou descrição da tarefa.
     * @param completed Status da tarefa (true para concluída, false para pendente).
     */
	public Task(Long id, String name, Boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.completed = completed;
	}
	
    /**
     * Getter para o atributo 'id'.
     * 
     * @return O identificador único da tarefa.
     */
	public Long getId() {
		return id;
	}
	
    /**
     * Setter para o atributo 'id'.
     * Permite alterar o identificador único da tarefa.
     * 
     * @param id O novo identificador único da tarefa.
     */
	public void setId(Long id) {
		this.id = id;
	}
	
    /**
     * Getter para o atributo 'name'.
     * 
     * @return O nome ou descrição da tarefa.
     */
	public String getName() {
		return name;
	}
	
    /**
     * Setter para o atributo 'name'.
     * Permite alterar o nome ou descrição da tarefa.
     * 
     * @param name O novo nome ou descrição da tarefa.
     */
	public void setName(String name) {
		this.name = name;
	}
	
    /**
     * Getter para o atributo 'completed'.
     * 
     * @return O status da tarefa (true para concluída, false para pendente).
     */
	public Boolean getCompleted() {
		return completed;
	}
	
    /**
     * Setter para o atributo 'completed'.
     * Permite alterar o status da tarefa.
     * 
     * @param completed O novo status da tarefa.
     */
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
}
