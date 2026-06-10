# Library Book App

## Настройка

Скопировать файл с переменными окружения:

```bash
cp .env.example .env
```

Открыть `.env` и задать нужные значения:

| Переменная | Описание | По умолчанию |
|------------|----------|--------------|
| `DB_USER` | Пользователь БД | `library_user` |
| `DB_PASSWORD` | Пароль БД | — |
| `DB_NAME` | Название БД | `library_db` |
| `DB_PORT` | Порт PostgreSQL | `5432` |
| `SERVER_PORT` | Порт приложения | `8080` |
| `PAGE_SIZE` | Количество записей на странице | `20` |
| `LOG_LEVEL` | Уровень логирования всего приложения | `INFO` |
| `LOG_LEVEL_APP` | Уровень логирования кода приложения | `DEBUG` |
| `LOG_LEVEL_SQL` | Уровень логирования SQL-запросов | `WARN` |

## Сборка

```bash
cd book
mvn package -DskipTests
```

## Запуск

```bash
java -jar book/target/book-1.0-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://localhost:5432/library_db \
  --spring.datasource.username=library_user \
  --spring.datasource.password=your_password
```

Приложение запустится на `http://localhost:8080`.

---

## Запуск через Docker

### Запуск

```bash
make up
```

Без Make:

```bash
docker-compose --env-file .env -f infrastructure/docker-compose.yaml up --build
```

Приложение будет доступно по адресу `http://localhost:8080`.

### Остановка и очистка

```bash
make down
```

Без Make:

```bash
docker-compose --env-file .env -f infrastructure/docker-compose.yaml down -v --rmi all
```

---

## REST API

```
GET /api/v1/borrowings?page=0
```

Возвращает список активных выдач (книги на руках) в формате JSON. Количество записей на странице задаётся через `PAGE_SIZE`.

## Веб-интерфейс

| URL | Описание |
|-----|----------|
| `/books` | Список книг |
| `/clients` | Список клиентов |
| `/borrowings` | Список выдач |
| `/borrowings/new` | Выдать книгу |
