// API Base URL
const API_BASE_URL = 'http://localhost:8080/api/tasks';

// DOM Elements
const taskForm = document.getElementById('taskForm');
const taskList = document.getElementById('taskList');
const refreshBtn = document.getElementById('refreshBtn');

// Load tasks when page loads
document.addEventListener('DOMContentLoaded', loadTasks);

// Event Listeners
taskForm.addEventListener('submit', addTask);
refreshBtn.addEventListener('click', loadTasks);

// Function to load all tasks
async function loadTasks() {
    try {
        const response = await fetch(API_BASE_URL);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const tasks = await response.json();
        displayTasks(tasks);
        showMessage('Tasks loaded successfully!', 'success');
    } catch (error) {
        console.error('Error loading tasks:', error);
        showMessage('Error loading tasks: ' + error.message, 'error');
    }
}

// Function to add a new task
async function addTask(event) {
    event.preventDefault();
    
    const title = document.getElementById('taskTitle').value;
    const description = document.getElementById('taskDescription').value;
    
    const taskData = {
        title: title,
        description: description
    };
    
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(taskData)
        });
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const newTask = await response.json();
        showMessage('Task added successfully!', 'success');
        
        // Clear form
        taskForm.reset();
        
        // Refresh task list
        loadTasks();
    } catch (error) {
        console.error('Error adding task:', error);
        showMessage('Error adding task: ' + error.message, 'error');
    }
}

// Function to display tasks in the DOM
function displayTasks(tasks) {
    taskList.innerHTML = '';
    
    if (tasks.length === 0) {
        taskList.innerHTML = '<p>No tasks found.</p>';
        return;
    }
    
    tasks.forEach(task => {
        const taskElement = document.createElement('div');
        taskElement.className = 'task-item';
        taskElement.innerHTML = `
            <div class="task-title">${task.title}</div>
            <div class="task-description">${task.description}</div>
        `;
        taskList.appendChild(taskElement);
    });
}

// Function to show messages
function showMessage(message, type) {
    // Remove existing messages
    const existingMessage = document.querySelector('.error, .success');
    if (existingMessage) {
        existingMessage.remove();
    }
    
    // Create new message
    const messageElement = document.createElement('div');
    messageElement.className = type;
    messageElement.textContent = message;
    
    // Insert at the top of the container
    const container = document.querySelector('.container');
    container.insertBefore(messageElement, container.firstChild);
    
    // Auto-remove after 3 seconds
    setTimeout(() => {
        if (messageElement.parentNode) {
            messageElement.remove();
        }
    }, 3000);
}
