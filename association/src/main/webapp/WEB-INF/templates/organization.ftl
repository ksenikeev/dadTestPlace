<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<title>База данных центральной площадки</title>
</head>


<body>
<H2>
	Организации
</H2>

<table>
	<th>ID</th>
	<th>Наименование</th>
	<th>ИНН</th>
	<tbody>
	<#list model["organizations"] as organization>
		<tr>
		<td>${organization.id}</td>
		<td>${organization.nameOfOrganization}</td>
		<td>${organization.inn}</td>
		</tr>
	</#list>
	</tbody>
</table>

</body>
</html>  