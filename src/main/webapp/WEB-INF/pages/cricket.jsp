<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Cricket Game</h1>
	<h2>The no of innings played:${innings}</h2>
	<h2>Overs:${overs}</h2>
	<h3>Team Playing:${Team}</h3>
	<h4>Wickets:${wickets}</h4>
	<h4>totalscore:${totalscore}</h4>
	<h4>runs:${runs}</h4>
	<br>
	<c:forEach items="${balls1}" var="element">
		<h10>${element.currentBatsmen} ${element.result}</h10><br>
		<h10>OnCrease Players Runs </h10>
		<c:forEach items="${element.onCreasePlayersRuns}" var="ele">
		<h10>${ele}</h10>
		</c:forEach><br>
		<h10>Last Wicket:${element.lastWicket}</h10><br>
		<h10>Wickets in Hand:${element.leftOverWickets}</h10><br><br>
	</c:forEach>
	<br>
	<h3>Team Playing:${Team1}</h3>
	<h4>Wickets:${wickets1}</h4>
	<h4>totalscore:${totalscore1}</h4>
	<h4>allOut:${allOut1}</h4>
	<h4>runs:${runs1}</h4>
	<br>
	<c:forEach items="${balls2}" var="element">
		<h10>${element.currentBatsmen} ${element.result}</h10><br>
		<h10>OnCrease Players Runs </h10>
		<c:forEach items="${element.onCreasePlayersRuns}" var="ele">
		<h10>${ele}</h10>
		</c:forEach><br>
		<h10>Last Wicket:${element.lastWicket}</h10><br>
		<h10>Wickets in Hand:${element.leftOverWickets}</h10><br><br>
	</c:forEach>
	<br>
	<h1>${won} won the match!!!!!!!!! with ${diff_runs} runs and ${diff_wickets} wickets in hand</h1>
</body>
</html>