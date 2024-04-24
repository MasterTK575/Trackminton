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
- **Backend**: Spring Boot for RESTful API development and PostgreSQL for database management.
- **Frontend**: Angular for building the user interface and TypeScript for client-side scripting.
- **Communication**: RESTful API endpoints are used to facilitate communication between the frontend and backend.
- **Deployment**: The application can be deployed on any server capable of running Spring Boot applications and PostgreSQL databases.
