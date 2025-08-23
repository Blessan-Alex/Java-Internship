# Task 8: Frontend Integration with REST API

## Overview
A simple HTML/JavaScript frontend that integrates with the Spring Boot REST API for task management.

## Features
- **Real-time Task Display**: Shows all tasks from the API
- **Add New Tasks**: Form to create new tasks
- **Dynamic Updates**: UI updates without page reload
- **Error Handling**: User-friendly error messages
- **Responsive Design**: Clean, modern interface

## Files
- `index.html` - Main HTML page
- `styles.css` - CSS styling
- `script.js` - JavaScript with Fetch API
- `InterviewAnswers.md` - Interview Q&A

## How to Run

### Prerequisites
- Spring Boot REST API running on `http://localhost:8080`
- Modern web browser

### Steps
1. **Start the Spring Boot API** (from task7):
   ```bash
   cd task7
   mvn spring-boot:run
   ```

2. **Open the frontend**:
   - Open `index.html` in a web browser
   - Or serve it using a local server

3. **Test the application**:
   - Add new tasks using the form
   - View tasks in the list
   - Refresh to see updates

## API Integration

### Endpoints Used
- `GET /api/tasks` - Fetch all tasks
- `POST /api/tasks` - Create new task

### JavaScript Features
- **Fetch API**: Modern HTTP requests
- **Async/Await**: Clean asynchronous code
- **DOM Manipulation**: Dynamic UI updates
- **Error Handling**: Try-catch with user feedback

## Key Concepts Demonstrated

### 1. Fetch API Usage
```javascript
const response = await fetch(API_BASE_URL);
const tasks = await response.json();
```

### 2. POST Request
```javascript
const response = await fetch(API_BASE_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(taskData)
});
```

### 3. DOM Manipulation
```javascript
taskList.innerHTML = '';
tasks.forEach(task => {
    const taskElement = document.createElement('div');
    taskElement.innerHTML = `<div>${task.title}</div>`;
    taskList.appendChild(taskElement);
});
```

### 4. Error Handling
```javascript
try {
    const response = await fetch(API_BASE_URL);
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
} catch (error) {
    showMessage('Error: ' + error.message, 'error');
}
```

## Testing

### Manual Testing
1. **Load Tasks**: Page should display existing tasks
2. **Add Task**: Fill form and submit, task should appear in list
3. **Error Handling**: Disconnect API, should show error message
4. **Refresh**: Click refresh button to reload tasks

### Browser Developer Tools
- Check Network tab for API calls
- Check Console for JavaScript errors
- Test with different network conditions

## Learning Outcomes
- Understanding frontend-backend integration
- Fetch API and async JavaScript
- DOM manipulation and dynamic UI updates
- Error handling in web applications
- REST API consumption from frontend
