<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<title>База данных центральной площадки</title>
</head>


<body>
<H2>
	Номенклатура
</H2>

<table>
	<th>ID</th>
	<th>Наименование</th>
	<th>UID</th>
	<tbody>
	<#list model["nomenclatures"] as nomenclature>
		<tr>
		<td>${nomenclature.id}</td>
		<td>${nomenclature.productName}</td>
		<td>${nomenclature.uid}</td>
		</tr>
	</#list>
	</tbody>
</table>

</body>
</html>  