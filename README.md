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
Для контроля содержимого базы данных центральной площадки доступны веб-страницы:<br/>
http://localhost:8080/association/gui/nomenclature<br/>
http://localhost:8080/association/gui/organization<br/>
http://localhost:8080/association/gui/request<br/>
http://localhost:8080/association/gui/offer<br/>


Структура данных
==============================
<p>Структура данных отобажена в виде набора классов в пакете ru.kpfu.icmit.association.model. Этой структуре соответствуют теги XML документов для обмена информацией.
<p>В том случае, если ваша модель данных отличается от приведенной, ситуацию можно нормализовать с помощью аннотаций, например:
<br/>
<table>
	<th>Базовая модель</th>
	<th>XML документ</th>
	<th>"Своя" модель</th>
	<tbody>
	<tr>
		<td>
			class Nomenclature { <br/>
			...<br/>
			String productName;<br/>
			...<br/>
			String getProductName() {<br/>
				return productName;<br/>
			}<br/>
		</td>
		<td>			
			&lt;nomenclature&gt;<br/>
			&nbsp;&nbsp;&nbsp;...<br/>
			&nbsp;&nbsp;&nbsp;&lt;productName&gt;Уголь антрацит&lt;/productName&gt;<br/>
			&nbsp;&nbsp;&nbsp;...<br/>
			&lt;/nomenclature&gt;<br/>
		</td>
		<td>
			class Nomenclature { <br/>
			...<br/>
			String nomenclatureName;<br/>
			...<br/>
			<b>@XmlElement(value="productName")</b><br/>
			String getNomenclatureName() {<br/>
				return nomenclatureName;<br/>
			}<br/>
		</td>
	</tr>
	<tr></tr>
	<tr></tr>
</tbody>

</table>


Запрос на добавление организации:
==============================
`POST` на `/organization/add`
<br/>
`Content-Type: application/xml`
<br/>
в теле POST-запроса отправляем XML документ<br/>

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
в теле POST-запроса отправляем XML документ<br/>
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
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?><br/>
&lt;envelope&gt;<br/>
&nbsp;	&lt;body&gt;<br/>
&nbsp;&nbsp;		&lt;nomenclatures&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;createDate&gt;2019-12-14T09:57:07.650+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;id&gt;3&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;modifyDate&gt;2019-12-14T09:57:07.650+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;productName&gt;3D принтер&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;uid&gt;aee07b8f-7eda-478f-8be2-9b7ac596c424&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;createDate&gt;2019-12-14T09:59:52.397+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;id&gt;4&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;modifyDate&gt;2019-12-14T09:59:52.397+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;productName&gt;Стол офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;createDate&gt;2019-12-14T10:01:10.843+03:00&lt;/createDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;id&gt;5&lt;/id&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;modifyDate&gt;2019-12-14T10:01:10.843+03:00&lt;/modifyDate&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;productName&gt;Стул офисный&lt;/productName&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;				&lt;uid&gt;9f7d5cf3-e139-4ba5-a60d-8eae58c076e1&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;			&lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;		&lt;/nomenclatures&gt;<br/>
&nbsp;	&lt;/body&gt;<br/>
&lt;/envelope&gt;<br/>

Отправка на центральную площадку запроса на приобретение товаров/услуг
==============================
`POST` на `/request/add`
<br/>
в теле POST-запроса отправляем XML документ<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;request&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;dateOfPerformance&gt;2020-01-12T00:00:00+03:00&lt;/dateOfPerformance&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;                &lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;                &lt;inn&gt;1600000002&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;               &lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;            &lt;/organization&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;priceOfProduct&gt;1250.0&lt;/priceOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;countOfProduct&gt;100.0&lt;/countOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;46c6074b-b93f-467f-af39-26ef27825020&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;unitCode&gt;piece&lt;/unitCode&gt;<br/>
&nbsp;&nbsp;        &lt;/request&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>

Отправка на центральную площадку предложения товаров и услуг товаров/услуг
==============================
`POST` на `/offer/add`
<br/>
в теле POST-запроса отправляем XML документ<br/>
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;&lt;envelope&gt;<br/>
&nbsp;    &lt;body&gt;<br/>
&nbsp;&nbsp;        &lt;offer&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;dateOfPerformance&gt;2020-01-02T00:00:00+03:00&lt;/dateOfPerformance&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;                &lt;uid&gt;058b8777-1bc1-4b9c-8c95-34f0f3bd2623&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;/nomenclature&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;organization&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;                &lt;inn&gt;1600000002&lt;/inn&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;               &lt;kpp&gt;1601001&lt;/kpp&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;            &lt;/organization&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;priceOfProduct&gt;1270.0&lt;/priceOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;countOfProduct&gt;1000.0&lt;/countOfProduct&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;uid&gt;46c6074b-b93f-467f-af39-26ef27825020&lt;/uid&gt;<br/>
&nbsp;&nbsp;&nbsp;            &lt;unitCode&gt;piece&lt;/unitCode&gt;<br/>
&nbsp;&nbsp;        &lt;/offer&gt;<br/>
&nbsp;    &lt;/body&gt;<br/>
&nbsp;    &lt;header/&gt;<br/>
&lt;/envelope&gt;<br/>


