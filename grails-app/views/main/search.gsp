<html>
	<head>
		<title>By surviving to midnight, you've managed to outlive these people</title>
	</head>

	<body>
		<ul>
			<g:each in="${deadPeople}">
				<!-- add <span> tags -->
				<li>
					<span class="name"><a target="_blank" href="http://en.wikipedia.org/wiki/${it.name.replaceAll(' ', '_')}">${it.name}</a></span>
					-
					<span class="description">${it.description}</span>
				 </li>
			</g:each>
		</ul>
	</body>

</html>