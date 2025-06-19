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
Проводим действия по настройке Keycloack.

Заходим в административную консоль:

![1Keycloak Realm Setup](images/001.png)

В меню выбираем пункт Manage realms:

![2Keycloak Realm Setup](images/002.png)

![3Keycloak Realm Setup](images/003.png)

![4Keycloak Realm Setup](images/004.png)

![5Keycloak Realm Setup](images/005.png)

![6Keycloak Realm Setup](images/006.png)

![7Keycloak Realm Setup](images/007.png)

![8Keycloak Realm Setup](images/008.png)

![9Keycloak Realm Setup](images/009.png)

![10Keycloak Realm Setup](images/010.png)

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

![11Keycloak Realm Setup](images/011.png)

![12Keycloak Realm Setup](images/012.png)

![13Keycloak Realm Setup](images/013.png)

![14Keycloak Realm Setup](images/014.png)

![15Keycloak Realm Setup](images/015.png)

![16Keycloak Realm Setup](images/016.png)

![17Keycloak Realm Setup](images/017.png)

![18Keycloak Realm Setup](images/018.png)

![19Keycloak Realm Setup](images/019.png)

![20Keycloak Realm Setup](images/020.png)

![21Keycloak Realm Setup](images/021.png)
`