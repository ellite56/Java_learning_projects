# Shop API

Учебный backend-проект Школы 21: REST API магазина.

## Возможности

- Работа с клиентами, адресами, поставщиками и товарами.
- Управление доступным количеством товара.
- Загрузка и получение изображений.
- Валидация DTO и обработка ошибок.
- Документация OpenAPI / Swagger.

## Стек и архитектура

Java, Spring Boot, Spring Web MVC, Spring Data JPA, PostgreSQL, Jakarta Validation, springdoc-openapi, Gradle.
Код разделён на контроллеры, сервис, DTO, маппер, JPA-сущности и репозитории.

## Подготовка и запуск

1. Подготовьте PostgreSQL и JDK, указанный в build.gradle.
2. Создайте базу shop и импортируйте схему:

    psql -U postgres -d shop -f database/schema.sql

3. Задайте DB_PASSWORD, при необходимости DB_USER и DB_URL.
По умолчанию URL — jdbc:postgresql://localhost:5432/shop, пользователь — postgres.
4. Запуск из каталога проекта:

    .\gradlew.bat bootRun

Linux/macOS: sh ./gradlew bootRun.

Swagger: /swagger/index.html. Товары: /api/v1/products.

## О публикации

Источник: BE2_APIFromSpec_ID_1269320-1. Учебная реализация.
Сборочные файлы сохранены из исходника; совместимость зависимостей и запуск при подготовке портфолио не проверялись.
Пароль БД заменён переменной окружения; опубликована только схема, без строк данных.
