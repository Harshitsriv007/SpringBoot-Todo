# TODO Application

A simple Spring Boot application that demonstrates a RESTful API for managing TODO items. This project allows users to create, update, list, and delete TODO items.

## Features

* **Create TODO**: Add a new TODO item.
* **List TODOs**: Retrieve a list of all TODO items.
* **Update TODO**: Update the content of an existing TODO.
* **Delete TODO**: Remove a TODO by its ID.

---

## Prerequisites

Before you begin, ensure you have the following installed:

* Java 8 or later
* Maven 3.6+
* A code editor (e.g., IntelliJ IDEA, VS Code)

---

## Getting Started

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your-username/todo-application.git
   cd todo-application
   ```

2. **Build the Application**:

   ```bash
   mvn clean install
   ```

3. **Run the Application**:

   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**:
   The API will be available at `http://localhost:8080`.

---

## API Endpoints

### Create a TODO

* **URL**: `POST /todos`
* **Request Body**:

  ```json
  {
      "id": 1,
      "content": "Learn Spring Boot"
  }
  ```
* **Response**:

  ```json
  {
      "id": 1,
      "content": "Learn Spring Boot"
  }
  ```

### List All TODOs

* **URL**: `GET /todos`
* **Response**:

  ```json
  [
      {
          "id": 1,
          "content": "Learn Spring Boot"
      }
  ]
  ```

### Update a TODO

* **URL**: `PATCH /todos`
* **Request Body**:

  ```json
  {
      "id": 1,
      "content": "Master Spring Boot"
  }
  ```
* **Response**:

  ```json
  [
      {
          "id": 1,
          "content": "Master Spring Boot"
      }
  ]
  ```

### Delete a TODO

* **URL**: `DELETE /todos/{id}`
* **Response**:

  ```plaintext
  Removed TODO with ID: 1
  ```

---

## Project Structure

```
src/main/java/com/example/demo/
├── controller/      # REST controllers
├── model/           # Domain models
├── services/        # Business logic and service layer
└── DemoApplication  # Main Spring Boot application
```

---

## Technologies Used

* **Java 8+**
* **Spring Boot**
* **Maven**
* **RESTful API**

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Description of changes"`).
4. Push to the branch (`git push origin feature-name`).
5. Create a Pull Request.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

If you have any questions or feedback, feel free to reach out:

* **Author**: Harshit Srivastava
* **Email**: [harshit.srivastv726@gmail.com](mailto:harshit.srivastv726@gmail.com)
* **GitHub**: [Harshitsriv007](https://github.com/Harshitsriv007)

---
