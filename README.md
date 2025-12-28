# 🤖 AI Tools Management System

A Spring Boot REST API application for managing AI tools with user reviews and admin moderation capabilities. The system allows users to browse AI tools, submit reviews, and provides admin functionality for tool and review management.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Endpoints](#endpoints)
- [Data Models](#data-models)
- [Admin Authentication](#admin-authentication)
- [Development](#development)

## ✨ Features

### 👥 User Features
- Browse AI tools with filtering capabilities
- Filter tools by category, pricing type, and rating range
- View detailed information about specific AI tools
- Submit reviews for AI tools (pending admin approval)

### 🔧 Admin Features
- Add, update, and delete AI tools
- Manage review moderation (approve/reject reviews)
- View all reviews with filtering by status
- View reviews by specific tool ID
- Secure admin authentication using API keys

## 🛠️ Technology Stack

- **Framework**: Spring Boot 4.0.1 ☕
- **Language**: Java 21 ☕
- **Database**: MongoDB 🍃
- **Documentation**: SpringDoc OpenAPI (Swagger UI) 📚
- **Build Tool**: Maven 🔨
- **Development Tools**: Spring Boot DevTools 🔄
- **IDE**: IntelliJ IDEA 💡
- **API Testing**: Postman 📮

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/trumio/task/aitools/
│   │   ├── cmdrunner/           # Command runner utilities
│   │   ├── controller/          # REST controllers
│   │   │   ├── admin/          # Admin-specific controllers
│   │   │   └── userController/ # User-facing controllers
│   │   ├── exceptions/         # Custom exceptions and error handling
│   │   ├── models/            # Data models and entities
│   │   ├── repositories/      # MongoDB repositories
│   │   ├── services/          # Business logic services
│   │   │   ├── adminAuth/     # Admin authentication services
│   │   │   ├── adminManagement/ # Admin management services
│   │   │   ├── filterServices/ # Tool filtering services
│   │   │   ├── ratingServices/ # Rating calculation services
│   │   │   └── userServices/   # User-facing services
│   │   ├── AdminKeyGenerator.java # Utility for generating admin keys
│   │   └── AitoolsApplication.java # Main application class
│   └── resources/
│       ├── application.properties # Main configuration
│       └── application-dev.properties.template # Development config template
└── test/ # Test classes
```

## ✅ Prerequisites

- Java 21 or higher ☕
- Maven 3.6+ 🔨
- MongoDB 4.4+ (running locally or accessible remotely) 🍃
- Git (for cloning the repository) 📂
- IntelliJ IDEA (recommended IDE) 💡
- Postman (for API testing) 📮

## 🚀 Setup and Installation

### 1. Clone the Repository 📥

```bash
git clone https://github.com/AkshayShineKrishna/AI-TOOL-FINDER.git
cd AI-TOOL-FINDER
```

### 2. Install MongoDB 🍃

**Windows:** 🪟
- Download MongoDB Community Server from [MongoDB Download Center](https://www.mongodb.com/try/download/community)
- Follow the installation wizard
- Start MongoDB service

**macOS (using Homebrew):** 🍎
```bash
brew tap mongodb/brew
brew install mongodb-community
brew services start mongodb/brew/mongodb-community
```

**Linux (Ubuntu/Debian):** 🐧
```bash
sudo apt-get update
sudo apt-get install -y mongodb
sudo systemctl start mongodb
sudo systemctl enable mongodb
```

### 3. Configure the Application ⚙️

Copy the development configuration template:
```bash
cp src/main/resources/application-dev.properties.template src/main/resources/application-dev.properties
```

Edit `src/main/resources/application-dev.properties`:
```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=aitools_db

# Logging levels
logging.level.root=INFO
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.data.mongodb=DEBUG

# Admin key for authentication (generate using AdminKeyGenerator)
admin.key=your-secure-admin-key-here
```

### 4. Generate Admin Key 🔑

Run the AdminKeyGenerator to create a secure admin key:
```bash
mvn compile exec:java -Dexec.mainClass="com.trumio.task.aitools.AdminKeyGenerator"
```

Copy the generated key and update it in your `application-dev.properties` file.

## ⚙️ Configuration

### Database Configuration 🍃

The application uses MongoDB as the primary database. Configure the connection in `application-dev.properties`:

- `spring.data.mongodb.host`: MongoDB host (default: localhost)
- `spring.data.mongodb.port`: MongoDB port (default: 27017)
- `spring.data.mongodb.database`: Database name

### Admin Authentication 🔐

Admin endpoints require authentication using the `X-ADMIN-KEY` header. The key is configured in the properties file and should be kept secure in production environments.

## ▶️ Running the Application

### Using Maven 🔨

```bash
# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

### Using Maven Wrapper (if available) 📦

**Windows:** 🪟
```cmd
mvnw.cmd spring-boot:run
```

**Linux/macOS:** 🐧🍎
```bash
./mvnw spring-boot:run
```

### Using JAR 📦

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/aitools-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080` 🌐

## 📚 API Documentation

Once the application is running, you can access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html 📖
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs 📄

## 🛣️ Endpoints

### 👥 User Endpoints

#### Get All AI Tools 🔍
```http
GET /tools
```

**Query Parameters:**
- `category` (optional): Filter by tool category
- `pricingType` (optional): Filter by pricing type (FREE, PAID, SUBSCRIPTION)
- `minRating` (optional): Minimum average rating
- `maxRating` (optional): Maximum average rating

**Example:**
```http
GET /tools?category=productivity&pricingType=FREE&minRating=4.0
```

#### Get Tool by ID 🆔
```http
GET /tools/{id}
```

#### Submit Review ⭐
```http
POST /tools/review
Content-Type: application/json

{
  "toolId": "tool-id-here",
  "rating": 5,
  "comment": "Great tool for productivity!"
}
```

### 🔧 Admin Endpoints

All admin endpoints require the `X-ADMIN-KEY` header. 🔑

#### Tool Management 🛠️

**Add Tool:** ➕
```http
POST /admin/tools
X-ADMIN-KEY: your-admin-key
Content-Type: application/json

{
  "name": "ChatGPT",
  "useCase": "Conversational AI assistant",
  "category": "AI Assistant",
  "pricingType": "SUBSCRIPTION"
}
```

**Update Tool:** ✏️
```http
PATCH /admin/tools/{id}
X-ADMIN-KEY: your-admin-key
Content-Type: application/json

{
  "name": "Updated Tool Name",
  "useCase": "Updated use case",
  "category": "Updated Category",
  "pricingType": "PAID"
}
```

**Delete Tool:** 🗑️
```http
DELETE /admin/tools/{id}
X-ADMIN-KEY: your-admin-key
```

#### Review Management 📝

**Get All Reviews:** 📋
```http
GET /admin/reviews
X-ADMIN-KEY: your-admin-key
```

**Get Reviews by Status:**
```http
GET /admin/reviews?status=PENDING
X-ADMIN-KEY: your-admin-key
```

**Get Reviews by Tool ID:**
```http
GET /admin/reviews/tool/{toolId}
X-ADMIN-KEY: your-admin-key
```

**Get Review by ID:**
```http
GET /admin/reviews/id/{reviewId}
X-ADMIN-KEY: your-admin-key
```

**Change Review Status:**
```http
PATCH /admin/reviews/{reviewId}/{status}
X-ADMIN-KEY: your-admin-key
```

Status values: `PENDING`, `APPROVED`, `REJECTED`

## Data Models

### AITool
```json
{
  "id": "string",
  "name": "string",
  "useCase": "string",
  "category": "string",
  "pricingType": "FREE|PAID|SUBSCRIPTION",
  "averageRating": 0.0,
  "createdAt": "2024-01-01T00:00:00",
  "updatedAt": "2024-01-01T00:00:00"
}
```

### Review
```json
{
  "id": "string",
  "toolId": "string",
  "rating": 5,
  "comment": "string",
  "status": "PENDING|APPROVED|REJECTED",
  "createdAt": "2024-01-01T00:00:00"
}
```

### PricingType Enum
- `FREE`: No cost
- `PAID`: One-time payment
- `SUBSCRIPTION`: Recurring payment

### ReviewStatus Enum
- `PENDING`: Awaiting admin review
- `APPROVED`: Approved by admin
- `REJECTED`: Rejected by admin

## 📊 Average Rating Calculation

The system automatically calculates and maintains average ratings for AI tools based on approved user reviews:

### How It Works
- **Only Approved Reviews Count**: Only reviews with `APPROVED` status are included in the calculation
- **Real-time Updates**: Average ratings are recalculated whenever:
  - A new review is approved
  - An existing review status changes (approved/rejected)
  - A review is deleted
- **Precision**: Ratings are calculated with decimal precision and stored as `Double` values
- **Default Value**: Tools without any approved reviews have an average rating of `0.0`

### Calculation Formula
```
Average Rating = Sum of all approved review ratings / Number of approved reviews
```

### Example
If a tool has approved reviews with ratings: 5, 4, 5, 3, 4
```
Average = (5 + 4 + 5 + 3 + 4) / 5 = 21 / 5 = 4.2
```

The rating calculation service automatically handles this process in the background, ensuring data consistency across the application.

## 🔍 Filter Services

The application provides comprehensive filtering capabilities for AI tools through dedicated filter services:

### Available Filters

#### Category Filter
- **Parameter**: `category` (String)
- **Function**: Filters tools by their category (case-insensitive)
- **Example**: `category=productivity` returns tools in the "Productivity" category

#### Pricing Type Filter
- **Parameter**: `pricingType` (Enum)
- **Values**: `FREE`, `PAID`, `SUBSCRIPTION`
- **Function**: Filters tools by their pricing model
- **Example**: `pricingType=FREE` returns only free tools

#### Rating Range Filter
- **Parameters**: 
  - `minRating` (Double): Minimum average rating (inclusive)
  - `maxRating` (Double): Maximum average rating (inclusive)
- **Function**: Filters tools within a specified rating range
- **Example**: `minRating=4.0&maxRating=5.0` returns tools rated between 4.0 and 5.0

### Filter Combinations
Filters can be combined for more precise results:
```http
GET /tools?category=AI Assistant&pricingType=SUBSCRIPTION&minRating=4.5
```

### Filter Service Architecture
- **Service Layer**: `filterServices` package contains the filtering logic
- **Repository Integration**: Filters are applied at the database level using MongoDB queries
- **Performance**: Efficient querying with proper indexing on filterable fields
- **Validation**: Input validation ensures filter parameters are within acceptable ranges

### Filter Behavior
- **Case Insensitive**: Category filtering is case-insensitive
- **Null Handling**: Missing filter parameters are ignored (no filtering applied)
- **Range Validation**: Rating filters validate that min ≤ max
- **Empty Results**: Returns empty list if no tools match the criteria

## Admin Authentication

The application uses a simple API key-based authentication for admin endpoints:

1. Generate a secure admin key using the `AdminKeyGenerator` utility
2. Configure the key in `application-dev.properties`
3. Include the key in the `X-ADMIN-KEY` header for all admin requests
