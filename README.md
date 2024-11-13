# Diaries App (Ongoing Development)

A personal diary application for Android that enables users to securely log and organize diary entries, manage moods, and integrate with MongoDB for backend support. This project is actively being developed, and new features and enhancements are continuously added.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [Roadmap](#roadmap)

## Project Overview
Diaries App provides a platform for users to maintain daily diary entries, capture their moods, and view past entries in an organized manner. Authentication is handled via Google Sign-In, and entries are stored and retrieved using MongoDB.

## Features
- **Diary Entries**: Users can create, view, and delete diary entries.
- **Mood Tracking**: Each entry can include a mood indicator.
- **Authentication**: Google Sign-In integration.
- **Navigation**: Smooth in-app navigation using `NavGraph`.
- **MongoDB Integration**: Stores user data on MongoDB with `MongoDB.kt` and `MongoRepo.kt`.
- **UI Components**: Custom dialogs, buttons, and gallery views enhance the user experience.

## Installation
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd Diaries-App
   ```

2. **Add Google Services Configuration**:
   Place your `google-services.json` file in the `app/` directory for Firebase and Google Sign-In.

3. **Build the Project**:
   Open the project in Android Studio, let the Gradle sync, and build the project.

## Usage
- **Sign In**: Use Google Sign-In to authenticate.
- **Create Entries**: Navigate to the diary screen to add new entries.
- **Manage Moods**: Select a mood while creating entries.
- **Navigation**: Use the bottom navigation for different sections.

## Technologies Used
- **Kotlin**: Programming language for Android development.
- **Android Jetpack**: Components like `Navigation`, `ViewModel`, and `LiveData`.
- **MongoDB**: NoSQL database for backend storage.
- **Google Sign-In**: User authentication and account management.

## Contributing
This project is ongoing, and contributions are welcome. Please follow the contribution guidelines outlined in `CONTRIBUTING.md` (if available).

## Roadmap
- **Upcoming Features**:
  - Enhanced mood tracking with visual charts.
  - Entry search and filtering.
  - Integration with cloud storage for media attachments.
- **Planned Improvements**:
  - UI/UX refinement for better usability.
  - Performance optimizations for faster load times.
