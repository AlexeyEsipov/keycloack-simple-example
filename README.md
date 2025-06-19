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
![1Keycloak Admin ConcoleEntry](images/001.PNG)

Первоначально мы попадаем в master realm
![2Keycloak Realm Setup](images/002.png)

Нам надо создать собственную realm. Для этого 
в меню выбираем пункт Manage realms и нажимаем кнопку Create realm:
![2Keycloak Realm Setup](images/001a.png)



![3Keycloak Realm Setup](images/003.PNG)
![4Keycloak Realm Setup](images/004.PNG)
![5Keycloak Realm Setup](images/005.PNG)
![6Keycloak Realm Setup](images/006.PNG)
![7Keycloak Realm Setup](images/007.PNG)
![8Keycloak Realm Setup](images/008.PNG)
![9Keycloak Realm Setup](images/009.PNG)
![10Keycloak Realm Setup](images/010.PNG)

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

![11Keycloak Realm Setup](images/011.PNG)
![12Keycloak Realm Setup](images/012.PNG)
![13Keycloak Realm Setup](images/013.PNG)
![14Keycloak Realm Setup](images/014.PNG)
![15Keycloak Realm Setup](images/015.PNG)
![16Keycloak Realm Setup](images/016.PNG)
![17Keycloak Realm Setup](images/017.PNG)
![18Keycloak Realm Setup](images/018.PNG)
![19Keycloak Realm Setup](images/019.PNG)
![20Keycloak Realm Setup](images/020.PNG)
![21Keycloak Realm Setup](images/021.PNG)
`