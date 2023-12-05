# Курсовой проект "Сервис перевода денег"

## Описание проекта
Rest - сервис преоставляет интерфейс для перевода денег с одной карты на другую по заранее описанной [спецификации](https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml). Работает совместно с заранее подготовенным веб - приложением [(FRONT)](https://serp-ya.github.io/card-transfer/).

## Реализация
- Приложение разработано с использованием Spring Boot
- Использован сборщик пакетов maven
- Для запуска используется docker, docker-compose
- Код покрыт unit тестами с использованием mockito
- Добавлены интеграционные тесты с использованием testcontainers
- Добавлено логирование, лог файл находится в корне проекта директория log

## Запуск приложения 
### Для запуска без Docker
- Запустить main метод в классе MoneyTransferAppApplication.java
### Для запуска через Docker
- запустить docker-compose.yml `docker-compose up -d`

---
## Описание интеграции с FRONT
FRONT доступен по [адресу](https://github.com/serp-ya/card-transfer). Можно скачать репозиторий и запустить Node.js приложение локально (в описании репозитория FRONT добавлена информация, как запустить) или использовать уже развёрнутое демо-приложение по [адресу](https://serp-ya.github.io/card-transfer/) (тогда ваш API должен быть запущен по [адресу](http://localhost:5500/)).

Весь API FRONT был описан в соответствии [YAML](https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml) файла по спецификации OpenAPI (подробнее по [ссылке 1](https://swagger.io/specification/) и [ссылке 2](https://starkovden.github.io/introduction-openapi-and-swagger.html)).

