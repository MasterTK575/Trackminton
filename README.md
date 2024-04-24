# Badminton Score Tracker

## Overview
This project is a web application designed to assist a sports club in managing and tracking badminton games during their biannual doubles tournament. The application provides features to count points, track game progress, and manage player positions on the court. It is built with a Spring Boot backend and PostgreSQL database for persistence, along with an Angular frontend.

## Features
- **Game Management**: Allows users to create, update, and delete badminton games.
- **Score Tracking**: Automatically counts points and displays the current score for each game.
- **Player Position Management**: Tracks player positions on the court and updates them based on game progress.
- **Serve Management**: Indicates which team has the serve based on the current score.
- **Set Management**: Manages sets, including determining set winners and handling set changes.
- **Pause Management**: Allows for pauses during the game and displays when a pause is available.

## Technologies Used
- **Backend**: Spring Boot for RESTful API development and PostgreSQL for database management (dockerized for easy testing).
- **Frontend**: Angular for building the user interface and TypeScript for client-side scripting.
- **Communication**: RESTful API endpoints are used to facilitate communication between the frontend and backend.
- **Deployment**: The application can be deployed on any server capable of running a Spring Boot application (and that has docker installed).

## Starting the Web App

### Backend (Spring Boot) Server:

1. **Install Docker**: If you don't have Docker installed on your computer, you'll need to install it first. Docker provides installation instructions for various operating systems on their website: [Install Docker](https://docs.docker.com/get-docker/).

2. **Start Backend Services with Docker Compose**:
    - Open a terminal or command prompt.
    - Navigate to the directory where you've pulled the repository containing the Docker Compose file (`docker-compose.yml`).
    - Run the following command to start the backend services (PostgreSQL and Adminer) defined in the Docker Compose file:
      ```bash
      docker-compose up -d
      ```
3. **Run the Spring Boot Application**: After starting the Docker image, you can run the Spring Boot application for example via the IDE.

### Frontend (Angular) Server:

4. **Install Angular CLI**: If you haven't installed Angular CLI globally on your computer yet, you'll need to do so. Open a terminal or command prompt and run the following command:
         ```bash
         npm install -g @angular/cli
         ```
5. **Install Frontend Dependencies**:
   - Navigate to the directory where you've pulled the Angular project (where the `package.json` file is located).
   - Run the following command to install the project dependencies:
     ```bash
     npm install
     ```
6. **Start Angular Development Server**:
   - After installing the dependencies, run the following command to start the Angular development server:
     ```bash
     ng serve
     ```
7. **Accessing the Web App**:
   - Once both the backend (Spring Boot) and frontend (Angular) servers are running, you can access the web app through your web browser.
   - For the frontend, navigate to `http://localhost:4200`.
   - For the backend, the Spring Boot server should be running on port `8080`, but you may need to check the documentation or source code for any custom configuration.
