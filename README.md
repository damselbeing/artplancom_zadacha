**ABOUT**

Приложение реализует следующие сервисы:
1. создание пользователя.
2. сразу после создания текущий пользователь авторизуется в том же запросе.
3. не зарегистрированный пользователь имеет возможность проверить доступность имени через сервис валидации.
4. созданный в системе пользователь имеет возможность авторизоваться, передав в сервис имя и пароль.
5. количество неудачных попыток авторизации не превышает 10 за 1 час и сбрасывается при успешной авторизации.
6. авторизованный пользователь имеет возможность:
- создавать/редактировать/удалить животных.
- получить список своих животных.
- получить детали любого животного по id.

Все взаимодействие происходит с использованием JSON форамата данных.

Все ошибки должны имеют номера и текстовую расшифровки.
Ошибки в случае возникновения так же передаются в виде JSON объекта.
