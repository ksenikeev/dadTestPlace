Тестовая площадка (центральная БД) assotiation
==============================

Для работы на локальной машине:
<br/>
приложение assotiation доступно по адресу http://localhost:8080/association
<br/>
Для работы через Internet:
<br/>
приложение assotiation доступно по адресу http://185.20.227.163:8080/association
<br/>


Запрос на добавление организации:
==============================
`POST` на `/addorganization`
<br/>
`Content-Type: application/xml`
<br/>

<![CDATA[
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<envelope>
    <body>
        <organization>
            <adressOfOrganization>г. Казань, ул. Университетская, д. 35</adressOfOrganization>
            <inn>1600000001</inn>
            <kpp>1601001</kpp>
            <ogrn>1231231434351</ogrn>
            <nameOfOrganization>Производитель 1</nameOfOrganization>
            <uid>b5f5521e-bdbf-4a27-8e4b-d8de0c57127b</uid>
        </organization>
    </body>
    <header/>
</envelope>

]]>

