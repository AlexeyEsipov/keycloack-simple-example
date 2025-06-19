# Инструкция по запуску Keycloak и Spring Boot приложения

## Предварительные требования
- Установленный Docker Desktop

## Пошаговая инструкция

### Шаг 1: Запуск Keycloak
Запустите контейнер с Keycloak:
```bash
docker-compose up -d keycloak
```

### Шаг 2: Настройка Keycloak через админ-консоль
Проводим действия по настройке Keycloack

![Keycloak Realm Setup](images/001.png)
![Keycloak Realm Setup](images/002.png)
![Keycloak Realm Setup](images/003.png)
![Keycloak Realm Setup](images/004.png)
![Keycloak Realm Setup](images/005.png)
![Keycloak Realm Setup](images/006.png)
![Keycloak Realm Setup](images/007.png)
![Keycloak Realm Setup](images/008.png)
![Keycloak Realm Setup](images/009.png)
![Keycloak Realm Setup](images/010.png)

### Шаг 3: Создание файла env.properties
Заполняем файл env.properties

### Шаг 4: Запуск Spring Boot приложения
Собираем и запускаем приложение
```bash
docker-compose build app
docker-compose up -d app
```
### Шаг 5: Проверка работоспособности приложения
Нам потребуется Postman.
![Keycloak Realm Setup](images/011.png)
![Keycloak Realm Setup](images/012.png)
![Keycloak Realm Setup](images/013.png)
![Keycloak Realm Setup](images/014.png)
![Keycloak Realm Setup](images/015.png)
![Keycloak Realm Setup](images/016.png)
![Keycloak Realm Setup](images/017.png)
![Keycloak Realm Setup](images/018.png)
![Keycloak Realm Setup](images/019.png)
![Keycloak Realm Setup](images/020.png)
![Keycloak Realm Setup](images/021.png)
`