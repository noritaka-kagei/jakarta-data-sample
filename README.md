# Jakarta Data 1.0 Sample Code

This project provides sample code for **Jakarta Data 1.0**, targeting **Jakarta EE 11**.

## Project Structure

- `src/main/java`: Java source code
- `src/main/resources`: Configuration files (persistence.xml, etc.)
- `src/test/java`: Unit/Integration tests
- `pom.xml`: Maven build configuration
- `docker-compose.yml`: Database container configuration
- `src/main/webapp/WEB-INF/glassfish-resources.xml`: JDBC Resource definition

## Getting Started

Follow these steps to set up, build, deploy, and verify the application.

### 1. Prerequisites
Ensure you have the following installed:
- **Java JDK 21**
- **Maven 3.9+** (Required for building the application)
- **Container Engine** (for database)
- **GlassFish 8 Server**

### 2. Database Setup
The application requires a PostgreSQL database named `book` accessible on port **5432**.

**Using Docker (Recommended):**
```bash
docker compose up -d
```
> [!IMPORTANT]
> Port **5432** must be free on your host machine. If you have a local PostgreSQL installed, please stop its service before starting Docker.

**Using Local PostgreSQL:**
If you prefer using a local installation, ensure you have:
- Database: `book`
- User: `tester`
- Password: `password`
- Port: `5432`

### 3. Build and Deploy

1.  **Build the application**:
    ```bash
    mvn clean package
    ```

2.  **Start GlassFish** (if not running):
    ```bash
    asadmin start-domain
    ```

3.  **Deploy the WAR file**:
    ```bash
    asadmin deploy target/jakarta-data-sample.war
    ```

### 4. Verification

Once deployed, you can access the REST API to verify the application is working.

**Endpoint**: `http://localhost:8080/jakarta-data-sample/api/books`

**Test with curl**:
```bash
curl -v http://localhost:8080/jakarta-data-sample/api/books
```

**Expected Output**:
A JSON array of books (initially empty or containing sample data if loaded).

```json
[]
```

To add a book (example):
```bash
curl -X POST -H "Content-Type: application/json" \
     -d '{"title":"Enjoy Jakarta Data!!!", "author":"Duke", "isbn":"123-1-123-12345-1", "publishedDate":"2025-12-15", "price": 45.0}' \
     http://localhost:8080/jakarta-data-sample/api/books
```

To get a book has specified isbn
```bash
curl -v http://localhost:8080/jakarta-data-sample/api/books/isbn/123-1-123-12345-1
```

To get book has title based specified pattern
```bash
curl -v http://localhost:8080/jakarta-data-sample/api/books/search?title=Java
```
