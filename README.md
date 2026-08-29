# ImageHub 🖼️

ImageHub is a Java-based web application for **image management and user authentication**. It allows users to register and log in, upload images, view and manage their images, edit image details, and reset their passwords.

The application is built using **Java Servlets, Hibernate ORM, MySQL, JSP, and Maven**, following a layered structure that separates models, backend database operations, and request-handling servlets.

## ✨ Features

### 👤 User Management

* User login and logout
* User registration
* Session-based user management
* Password reset functionality

### 🖼️ Image Management

* Upload images
* Retrieve and display images
* Edit image information
* Delete images
* Retrieve image dimensions
* Manage images associated with users

### 🗄️ Database

* MySQL database for persistent storage
* Hibernate ORM for database interaction
* Transaction-based database operations

## 🛠️ Tech Stack

| Technology                    | Purpose                                           |
| ----------------------------- | ------------------------------------------------- |
| **Java 8**                    | Application and backend logic                     |
| **Java Servlets**             | HTTP request handling                             |
| **JSP**                       | Server-side web pages                             |
| **Hibernate ORM**             | Object-relational mapping and database operations |
| **MySQL**                     | Persistent data storage                           |
| **Maven**                     | Dependency and build management                   |
| **Apache Commons FileUpload** | Handling multipart file uploads                   |
| **JSTL**                      | JSP tag library support                           |

## 🧩 Architecture

The application follows a layered structure:

```text
                    Browser
                       │
                       ▼
                Java Servlets
                       │
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
   User/Image Handling          JSP Pages
          │
          ▼
       Hibernate
          │
          ▼
        MySQL
```

### Project Structure

```text
ImageHub/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/image/
│       │       │
│       │       ├── backend/
│       │       │   ├── ImageHandling.java
│       │       │   └── UserHandling.java
│       │       │
│       │       ├── frontend/
│       │       │   ├── EditImage.java
│       │       │   ├── GetImage.java
│       │       │   ├── ImageDelete.java
│       │       │   ├── ImageSize.java
│       │       │   ├── ImageUpload.java
│       │       │   ├── Login.java
│       │       │   ├── Logout.java
│       │       │   └── ResetPassword.java
│       │       │
│       │       ├── models/
│       │       │   ├── Image.java
│       │       │   └── User.java
│       │       │
│       │       └── session/
│       │           └── HibernateSession.java
│       │
│       └── webapp/
│
├── pom.xml
└── README.md
```

## 🔄 Application Flow

### Authentication

```text
User
  │
  ▼
Login Request
  │
  ▼
Login Servlet
  │
  ▼
UserHandling
  │
  ▼
Hibernate
  │
  ▼
MySQL
  │
  ▼
Authentication Result
  │
  ▼
User Session
```

### Image Upload

```text
User selects image
        │
        ▼
ImageUpload Servlet
        │
        ▼
Multipart File Processing
        │
        ▼
Image Model
        │
        ▼
ImageHandling
        │
        ▼
Hibernate Transaction
        │
        ▼
MySQL
```

### Image Retrieval

```text
Request
   │
   ▼
GetImage Servlet
   │
   ▼
ImageHandling
   │
   ▼
Hibernate
   │
   ▼
MySQL
   │
   ▼
Image Data
   │
   ▼
Response
```

## 🗃️ Data Model

The application primarily works with two entities:

### User

Represents a registered application user and contains information required for authentication and user management.

### Image

Represents an uploaded image and stores image-related information associated with a user.

Hibernate maps these Java model classes to the relational database.

## 🔧 Hibernate Integration

Hibernate is used as the ORM layer between the Java application and MySQL.

Instead of manually writing SQL for every database operation, the application works with Java objects such as:

```text
User
Image
```

and uses Hibernate sessions and transactions to persist and retrieve these objects.

Database operations are organized through:

```text
UserHandling
ImageHandling
```

while:

```text
HibernateSession
```

is responsible for creating and managing Hibernate sessions.

## 🔐 Session Management

After authentication, the application uses HTTP sessions to maintain information about the logged-in user across requests.

This allows protected operations such as image management to be associated with the current user.

## 📤 File Upload

Image uploads are handled using multipart HTTP requests.

The application uses **Apache Commons FileUpload** to process uploaded files before passing the relevant image information to the backend layer.

## 🚀 Getting Started

### Prerequisites

* Java 8 or compatible JDK
* Maven
* MySQL
* Apache Tomcat or another compatible Servlet container

### 1. Clone the Repository

```bash
git clone https://github.com/sanvicoder/ImageHub.git
cd ImageHub
```

### 2. Configure MySQL

Create a MySQL database for the application and configure the database connection used by Hibernate.

The database configuration should contain:

```text
Database URL
Database username
Database password
```

### 3. Build the Application

```bash
mvn clean package
```

This generates the deployable WAR file.

### 4. Deploy

Deploy the generated WAR file to a compatible Servlet container such as Apache Tomcat.

Start the server and open the application through the corresponding local Tomcat URL.

## 🧪 Testing

The project can be tested manually through the main application workflows:

* User registration
* Login/logout
* Password reset
* Image upload
* Image retrieval
* Image editing
* Image deletion
* Image dimension retrieval

## 🎯 What I Learned

This project helped me understand the fundamentals of building a Java web application using a traditional Servlet-based architecture.

Key areas explored include:

* Java Servlets and HTTP request handling
* JSP-based web applications
* Hibernate ORM
* Object-relational mapping
* MySQL database integration
* Database transactions
* Session management
* Multipart file uploads
* CRUD operations
* Maven-based Java projects
* Separation of request handling and database operations

## 🔮 Possible Improvements

For a more production-oriented implementation, the application could be improved by:

* Using modern password hashing such as BCrypt or Argon2
* Adding stronger authentication and authorization
* Using prepared/parameterized database operations where applicable
* Improving exception handling and structured logging
* Adding input validation and file-type/size validation
* Adding CSRF protection
* Introducing a service layer between servlets and database operations
* Moving database credentials to environment variables or secure configuration
* Adding automated unit and integration tests
* Upgrading to a modern Spring Boot-based architecture
* Storing images in object storage such as Amazon S3 instead of the application/database layer

## 📄 License

This project was created as a learning project to explore Java web development, Hibernate, MySQL, and image management.
