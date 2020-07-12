<%@ include file="./common/header.jspf" %>
<%@ include file="./common/navigation.jspf" %>

<p>Welcome to your homepage</p>
<a href="/list">Click here to manage TODOs</a>

<%@ include file="./common/scripts.jspf" %>

<script>
	 $('#todosMenuItem').removeClass("active");
	 $('#homeMenuItem').addClass("active");
</script>


<%@ include file="./common/footer.jspf" %>

