Практическая работа, которую я выполнил, обучаясь на платформе Skillbox:
Написать консольное приложение на Spring "Контакты".
Контакт представляет собой ФИО Номер телефона Email. Данное приложение умеет работать с контактами:
- Сохранять
- Удалять
- Выводить на экран все имеющиеся контакты
Приложение имеет два режима работы: init и default.
В режиме default (стоит по умолчанию) контакты после завершения программы не сохраняются,
т.е. они будут существовать, пока приложение запущено.
В режиме init контакты из внутреннего файла, подгружаются в оперативную память.
Если завершить программу командой EXIT, то файл с контактами полностью перезапишется
всеми контактами, находящимися в оперативной памяти.
В приложении доступны следующие команды:
HELP - справка.
ADD Фамилия Имя Отчество (+7/8)9998887777 example@example.example - добавить новый контакт.
REMOVE example@example.example - удалить контакт по email.
FINDALL - получить список всех сохраненных контактов.
EXIT - завершить программу и сохранить изменения.