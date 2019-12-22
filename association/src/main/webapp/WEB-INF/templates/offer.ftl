<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<title>База данных центральной площадки</title>
</head>


<body>
<H2>
	Предложение товаров/услуг
</H2>

	<table>
		<th>UID Предложения</th>
		<th>Наименование организации</th>
		<th>Номенклатура</th>
		<th>UID Номенклатуры</th>
		<th>Количество</th>
		<tbody>
		<#list model["offers"] as offer>
			<tr>
			<td>${offer.uid}</td>
			<td>${offer.organization.nameOfOrganization}</td>
			<td>${offer.nomenclature.productName}</td>
			<td>${offer.nomenclature.uid}</td>
			<td>${offer.countOfProduct}</td>
			</tr>
		</#list>
		</tbody>
	</table>

</body>
</html>  