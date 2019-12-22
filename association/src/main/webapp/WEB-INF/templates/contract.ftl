<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8"/>
	<title>База данных центральной площадки</title>
</head>


<body>
<H2>
	Контракты
</H2>

	<table>
		<th>UID контракта</th>
		<th>Организация поставщик</th>
		<th>Организация заказчик</th>
		<th>Номенклатура</th>
		<th>UID Номенклатуры</th>
		<th>Количество</th>
		<tbody>
		<#list model["contracts"] as contract>
			<tr>
			<td>${contract.uid}</td>
			<td>${contract.offer.organization.nameOfOrganization}</td>
			<td>${contract.request.organization.nameOfOrganization}</td>
			<td>${contract.nomenclature.productName}</td>
			<td>${contract.nomenclature.uid}</td>
			<td>${contract.countOfProduct}</td>
			</tr>
		</#list>
		</tbody>
	</table>

</body>
</html>  