# Acme Logging Spring Boot Auto-Configuration

Auto-configuration module for the Acme Logging library that automatically configures `AcmeLogger` beans in Spring Boot applications.

## Project Structure

This project is part of the Acme Logging starter architecture:

- **acme-logging** - Core library containing `AcmeLogger` and `LogLevel` classes
  - GitHub repo: git@github.com:rafaspimenta/acme-logging.git
- **acme-logging-spring-boot-autoconfigure** - Auto-configuration module (this project)
  - GitHub repo: git@github.com:rafaspimenta/acme-logging-spring-boot-autoconfigure.git
- **acme-logging-spring-boot-starter** - Starter module that imports the auto-configuration
  - GitHub repo: (TBD)

## Features

- Automatically creates `AcmeLogger` beans when the `acme-logging` library is on the classpath
- Configurable via Spring Boot properties
- Respects user-defined `AcmeLogger` beans (allows override)

## Configuration

Configure via `application.properties` or `application.yml`:

```properties
acme.logging.enabled=true
acme.logging.level=INFO
```

**Supported log levels**: `DEBUG`, `INFO` (default), `WARN`, `ERROR`

## Usage

### Recommended: Use the Starter

```xml
<dependency>
    <groupId>com.pimenta</groupId>
    <artifactId>acme-logging-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Direct Dependency (Advanced)

```xml
<dependency>
    <groupId>com.pimenta</groupId>
    <artifactId>acme-logging-spring-boot-autoconfigure</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

**Note**: Also include `acme-logging` core dependency.

## Requirements

- Java 25+
- Spring Boot 4.0.0+
- `acme-logging` core library
