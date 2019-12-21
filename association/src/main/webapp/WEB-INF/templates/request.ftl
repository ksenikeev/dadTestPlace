<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<title>База данных центральной площадки</title>
</head>


<body>
<H2>
	Запросы на товары/услуги
</H2>

	<table>
		<th>UID</th>
		<th>Наименование организации</th>
		<th>Номенклатура</th>
		<th>Количество</th>
		<tbody>
		<#list model["requests"] as request>
			<tr>
			<td>${request.uid}</td>
			<td>${request.organization.nameOfOrganization}</td>
			<td>${request.nomenclature.productName}</td>
			<td>${request.countOfProduct}</td>
			</tr>
		</#list>
		</tbody>
	</table>

</body>
</html>  