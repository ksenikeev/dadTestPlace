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
			<td><#if contract.offer??> ${contract.offer.organization.nameOfOrganization}</#if></td>
			<td><#if contract.request??>${contract.request.organization.nameOfOrganization}</#if></td>
			<td><#if contract.nomenclature??>${contract.nomenclature.productName}</#if></td>
			<td><#if contract.nomenclature??>${contract.nomenclature.uid}</#if></td>
			<td>${contract.count}</td>
			</tr>
		</#list>
		</tbody>
	</table>

</body>
</html>  