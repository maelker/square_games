Square Games

Square Games is a Java-based web application for managing and playing various games. It provides functionality for user authentication, game management, and user interaction.
Table of Contents

    1. Features
    2. Installation
    3. Usage
    4. Contributing
    5. License

1. Features
    
        Authentication: Secure user authentication using JWT tokens.
        Game Management: CRUD operations for managing games.
        User Management: CRUD operations for managing users.
        DTOs: Data Transfer Objects for efficient data exchange.
        Security: Implements security measures to protect user data and application endpoints.
        Services: Business logic encapsulated in service classes.
        Repositories: Data access layer for interacting with the database.
        Frontend: Frontend components for user interface.

2. Installation

        Clone the Repository:
    
        bash

    git clone https://github.com/maelker/square-games.git
    
    Navigate to the Project Directory:
    
    bash
    
    cd square-games
    
    Build the Application:
    
    bash
    
    ./gradlew build
    
    Run the Application:
    
    bash
    
        java -jar build/libs/square-games.jar
    
        Access the Application:
        Open a web browser and go to http://localhost:8080 to access the application.

3. Usage
    
        API Endpoints: The application provides various API endpoints for user authentication, game management, and user management. Refer to the API documentation for detailed usage instructions.
        Frontend Interface: Navigate to the frontend directory to explore the user interface components.

4. Contributing

    Contributions are welcome! Please follow these steps to contribute to the project:
    
        Fork the repository.
        Create a new branch (git checkout -b feature/feature-name).
        Make your changes.
        Commit your changes (git commit -am 'Add new feature').
        Push to the branch (git push origin feature/feature-name).
        Create a new Pull Request.

5. License
    
    This project is licensed under the MIT License.
    
    Feel free to customize this README according to your project's specific details and requirements. Include additional sections or information as needed.
