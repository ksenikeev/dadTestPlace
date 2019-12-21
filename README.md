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

Запрос на добавление номенклатуры:
==============================
`POST` на `/nomenclature/add`
<br/>

&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;productName&gt;Стул офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;9f7d5cf3-e139-4ba5-a60d-8eae58c076e1&lt;/uid&gt;<br/>
&nbsp;&nbsp;        &lt;/nomenclature&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Ответ приходит в виде XML документа (с добавлением тегов `createDate` и `modifyDate`)<br/>
<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;<br/>
&lt;envelope&gt;<br/>
&nbsp;	&lt;header/&gt;<br/>
&nbsp;	&lt;body&gt;<br/>
&nbsp;&nbsp;		&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;id&gt;8&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;createDate&gt;2019-12-21T08:30:29.995+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;modifyDate&gt;2019-12-21T08:30:29.995+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;productName&gt;Уголь антрацит&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;uid&gt;abbe5815-45e6-4b34-9cf6-a7273bb03bca&lt;/uid&gt;<br/>
&nbsp;&nbsp;		&lt;/nomenclature&gt;<br/>
&nbsp;	&lt;/body&gt;<br/>
&lt;/envelope&gt;<br/>

Запрос на получение списка номенклатуры, измененной после определенной даты:
==============================
`POST` на `/nomenclature/get`
<br/>
параметр запроса `datefrom` со значение даты и времени в формате `yyyy-MM-dd'T'HH:mm:ss.SXXX` (URL encoded)
<br/>
Например, дата `2019-01-01T00:00:01.1+03:00` <br/>
в теле запроса: `datefrom=2019-01-01T00%3A00%3A01.1%2B03%3A00`
<br/>
Ответ возвращается в виде XML документа, содержащий список номенклатуры, измененной/добавленной в центральную площадку после указанной даты
<br/>
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<envelope>
	<body>
		<nomenclatures>
			<nomenclature>
				<createDate>2019-12-14T09:57:07.650+03:00</createDate>
				<id>3</id>
				<modifyDate>2019-12-14T09:57:07.650+03:00</modifyDate>
				<productName>3D принтер</productName>
				<uid>aee07b8f-7eda-478f-8be2-9b7ac596c424</uid>
			</nomenclature>
			<nomenclature>
				<createDate>2019-12-14T09:59:52.397+03:00</createDate><id>4</id><modifyDate>2019-12-14T09:59:52.397+03:00</modifyDate><productName>Стол офисный</productName><uid>058b8777-1bc1-4b9c-8c95-34f0f3bd2623</uid></nomenclature><nomenclature><createDate>2019-12-14T10:01:10.843+03:00</createDate><id>5</id><modifyDate>2019-12-14T10:01:10.843+03:00</modifyDate><productName>Стул офисный</productName><uid>9f7d5cf3-e139-4ba5-a60d-8eae58c076e1</uid></nomenclature><nomenclature><createDate>2019-09-17T09:45:20.019+03:00</createDate><id>1</id><modifyDate>2019-09-17T09:45:20.019+03:00</modifyDate><productName>Ж/Д вагон</productName><uid>5a722f88-a1a8-4109-b252-acdcbd497d69</uid></nomenclature><nomenclature><createDate>2019-10-11T09:55:48.251+03:00</createDate><id>2</id><modifyDate>2019-10-11T09:55:48.251+03:00</modifyDate><productName>Станок фрейзерный ЧПУ</productName><uid>7aeb398b-3a3c-49c9-bed1-279474cac788</uid></nomenclature><nomenclature><createDate>2019-12-14T13:00:08.906+03:00</createDate><id>6</id><modifyDate>2019-12-14T13:00:08.906+03:00</modifyDate><productName>Компьютер</productName><uid>0e99c44d-dcf3-44f7-9f19-c4534d582dff</uid></nomenclature><nomenclature><createDate>2019-12-14T13:28:03.211+03:00</createDate><id>7</id><modifyDate>2019-12-14T13:28:03.211+03:00</modifyDate><productName>Автомобиль</productName><uid>b43f44ba-e005-480d-bf7d-306a69c39f10</uid></nomenclature><nomenclature><createDate>2019-12-21T08:30:29.995+03:00</createDate><id>8</id><modifyDate>2019-12-21T08:30:29.995+03:00</modifyDate><productName>Уголь антрацит</productName><uid>abbe5815-45e6-4b34-9cf6-a7273bb03bca</uid></nomenclature><nomenclature><createDate>2019-12-21T08:38:06.333+03:00</createDate><id>9</id><modifyDate>2019-12-21T08:38:06.333+03:00</modifyDate><productName>Уголь антрацит</productName><uid>2d10c3f8-f146-4c93-9793-60509c9681a9</uid></nomenclature></nomenclatures>
	</body>
</envelope>


