const apiUrl = 'http://localhost:8080/api/tasks';

async function fetchTasks() {
    const response = await fetch(apiUrl);
    const tasks = await response.json();
    const taskList = document.getElementById("taskList");
    taskList.innerHTML = '';

    tasks.forEach(task => {
        const li = document.createElement("li");
        li.innerHTML = `
            <span>${task.name} - ${task.completed ? 'Done' : 'Pending'}</span>
            <button class="delete" onclick="deleteTask(${task.id})">Delete</button>
            <button onclick="toggleTask(${task.id}, ${!task.completed})">
                ${task.completed ? 'Mark as Pending' : 'Mark as Done'}
            </button>
        `;
        taskList.appendChild(li);
    });
}

async function addTask() {
    const name = document.getElementById("taskInput").value;
    if (!name) return;
    await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, completed: false })
    });
    document.getElementById("taskInput").value = '';
    fetchTasks();
}

async function deleteTask(id) {
    await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
    fetchTasks();
}

async function toggleTask(id, completed) {
    await fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ completed })
    });
    fetchTasks();
}

document.addEventListener("DOMContentLoaded", fetchTasks);
