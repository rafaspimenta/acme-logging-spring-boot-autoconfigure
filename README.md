# Acme Logging Spring Boot Auto-Configuration

Auto-configuration module for the Acme Logging library that automatically configures `AcmeLogger` beans in Spring Boot applications.

## Project Structure

This project is part of the Acme Logging starter architecture:

- **acme-logging** - Core library containing `AcmeLogger` and `LogLevel` classes
  - GitHub repo: https://github.com/rafaspimenta/acme-logging
- **acme-logging-spring-boot-autoconfigure** - Auto-configuration module (this project)
  - GitHub repo: https://github.com/rafaspimenta/acme-logging-spring-boot-autoconfigure
- **acme-logging-spring-boot-starter** - Starter module that imports the auto-configuration
  - GitHub repo: https://github.com/rafaspimenta/acme-logging-spring-boot-starter

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

```xml
<dependency>
    <groupId>com.pimenta</groupId>
    <artifactId>acme-logging-spring-boot-autoconfigure</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Requirements

- Java 25+
- Spring Boot 4.0.0+
- `acme-logging` core library
