<html>
	<head>
		<title>Outliving</title>
	</head>

	<div class="flash">
		${flash.message}
	</div>

	<g:form action="search" method="GET">
		<g:select id="year" name="year" from="${1900..2015}" noSelection="['':'Year']" />
		<g:select id="month" name="month" from="${1..12}" noSelection="['':'Month']" />
		<g:select id="day" name="day" from="${1..31}" noSelection="['':'Day']" />

		<input type="submit" name="submit" value="Submit" />
	</g:form>

</html>