# Инструкция по запуску Keycloak и Spring Boot приложения
Приложение демонстрирует пример создания пользователя, его авторизации и доступа к защищенному ресурсу 
с использованием **KeyCloack** и **SpringBoot**.

Версии приложений: 

SpringBoot - **3.5.0**, 

KeyClock - **26.2.5**, 

библиотека **org.keycloak:keycloak-admin-client** версии **26.0.5** 

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
в меню выбираем пункт **Manage realms** и нажимаем кнопку **Create realm**:
![2Keycloak Realm Setup](images/001a.PNG)

Нам надо создать realm с именем test-realm.  
Для этого сначала вводим имя **test-realm** в поле **Realm name**:
![3Keycloak Realm Setup](images/003.PNG)

Затем в поле Resource file загрузим json-файл с готовыми настройками realm.
Для этого нажмите кнопку **Browse** и выберите для загрузки файл **realm-export.json**. 
Он находится в корне проекта:
![4Keycloak Realm Setup](images/004.PNG)

После загрузки json-файла проверьте, что переключатель **Enabled** находится в положении **ON**
и нажмите кнопку **Create**
![5Keycloak Realm Setup](images/005.PNG)

Далее мы видим, что созданная нами test-realm становится текущей realm:
![6Keycloak Realm Setup](images/006.PNG)

В главном меню (слева) выбираем пункт **Clients** и убеждаемся, что есть созданный нами через 
настройки json-файла клиент **spring-boot-app** 
![7Keycloak Realm Setup](images/007.PNG)

Заходим в него и переходим на вкладку **Credentials**:
![8Keycloak Realm Setup](images/008.PNG)
Так как при первоначальном создании клиента у него не сгенерирован секрет, 
то нам надо его сгенерировать. Для этого в поле **Client Secret** нажимаем на кнопку **Regenerate** 
и далее выбираем **Yes**: 
![9Keycloak Realm Setup](images/009.PNG)
Сгенерированный секрет (katBmr....) копируем в буфер обмена. Этот секрет понадобится нам в следующих настройках.
![10Keycloak Realm Setup](images/010.PNG)

### Шаг 3: Создание файла env.properties
Заполняем файл env.properties.
Этот файл находится в корне проекта, он уже предзаполнен. Надо только добавить сохраненный на предыдущем шаге секрет в свойство по ключу
KEYCLOAK_SECRET

### Шаг 4: Запуск Spring Boot приложения
Собираем и запускаем приложение
```bash
docker-compose build app
docker-compose up -d app
```
### Шаг 5: Проверка работоспособности приложения
Приложение будет запущено на порту **8090**. Для проверки работоспособности  
нам потребуется Postman.

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