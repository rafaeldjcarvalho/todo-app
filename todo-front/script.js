// URL base da API onde os endpoints do backend estão disponíveis.
const apiUrl = 'http://localhost:8080/api/tasks';

/**
 * Função para buscar todas as tarefas da API e exibi-las na interface.
 */
async function fetchTasks() {
    // Faz uma requisição GET para buscar as tarefas.
    const response = await fetch(apiUrl); 
    // Converte a resposta da API em um objeto JSON.
    const tasks = await response.json(); 

    // Seleciona a lista de tarefas no DOM.
    const taskList = document.getElementById("taskList");
    // Limpa o conteúdo existente antes de renderizar os dados atualizados.
    taskList.innerHTML = ''; 

    // Itera sobre a lista de tarefas retornadas pela API.
    tasks.forEach(task => {
        // Cria um elemento <li> para cada tarefa.
        const li = document.createElement("li");

        // Define o conteúdo do item da lista, incluindo nome, status e botões.
        li.innerHTML = `
            <span>${task.name} - ${task.completed ? 'Done' : 'Pending'}</span>
            <button class="delete" onclick="deleteTask(${task.id})">Delete</button>
            <button onclick="toggleTask(${task.id}, ${!task.completed})">
                ${task.completed ? 'Mark as Pending' : 'Mark as Done'}
            </button>
        `;

        // Adiciona o item <li> à lista de tarefas no DOM.
        taskList.appendChild(li);
    });
}

/**
 * Função para adicionar uma nova tarefa à lista.
 */
async function addTask() {
    // Captura o valor do campo de entrada da nova tarefa.
    const name = document.getElementById("taskInput").value;
    if (!name) return; // Retorna sem fazer nada se o campo estiver vazio.

    // Faz uma requisição POST para a API para criar uma nova tarefa.
    await fetch(apiUrl, {
        method: 'POST', // Define o método HTTP como POST.
        headers: {
            'Content-Type': 'application/json' // Indica que o corpo da requisição está em JSON.
        },
        body: JSON.stringify({ name, completed: false }) // Envia o nome da tarefa e define como "não concluída".
    });

    // Limpa o campo de entrada após adicionar a tarefa.
    document.getElementById("taskInput").value = '';
    // Atualiza a lista de tarefas exibida na interface.
    fetchTasks();
}

/**
 * Função para excluir uma tarefa específica.
 * 
 * @param {number} id - O ID da tarefa a ser excluída.
 */
async function deleteTask(id) {
    // Faz uma requisição DELETE para a API para remover a tarefa pelo ID.
    await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
    // Atualiza a lista de tarefas após a exclusão.
    fetchTasks();
}

/**
 * Função para alternar o status de conclusão de uma tarefa (feito/pendente).
 * 
 * @param {number} id - O ID da tarefa.
 * @param {boolean} completed - O novo status de conclusão da tarefa.
 */
async function toggleTask(id, completed) {
    // Faz uma requisição PUT para atualizar o status da tarefa.
    await fetch(`${apiUrl}/${id}`, {
        method: 'PUT', // Define o método HTTP como PUT.
        headers: {
            'Content-Type': 'application/json' // Indica que o corpo da requisição está em JSON.
        },
        body: JSON.stringify({ completed }) // Envia o novo status de conclusão.
    });

    // Atualiza a lista de tarefas após a alteração.
    fetchTasks();
}

/**
 * Executa a função fetchTasks quando a página é carregada.
 * Garante que a lista de tarefas seja exibida automaticamente.
 */
document.addEventListener("DOMContentLoaded", fetchTasks);
