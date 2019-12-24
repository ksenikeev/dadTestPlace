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
		<th>Номенклатура UID</th>
		<th>Количество</th>
		<tbody>
		<#list model["requests"] as request>
			<tr>
			<td>${request.uid}</td>
			<td><#if request.organization??>${request.organization.nameOfOrganization}</#if></td>
			<td><#if request.nomenclature??>${request.nomenclature.productName}</#if></td>
			<td><#if request.nomenclature??>${request.nomenclature.uid}</#if></td>
			<td>${request.countOfProduct}</td>
			</tr>
		</#list>
		</tbody>
	</table>

</body>
</html>  