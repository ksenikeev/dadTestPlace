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
`POST` на `/organization/add`
<br/>
`Content-Type: application/xml`
<br/>


&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;        &lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;adressOfOrganization&gt;г. Казань, ул. Университетская, д. 35&lt;/adressOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;inn&gt;1600000001&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;ogrn&gt;1231231434351&lt;/ogrn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;nameOfOrganization&gt;Производитель 1&lt;/nameOfOrganization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            &lt;uid&gt;b5f5521e-bdbf-4a27-8e4b-d8de0c57127b&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;        &lt;/organization&gt;<br/>
&nbsp;&nbsp;    &lt;/body&gt;<br/>
&nbsp;&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>



