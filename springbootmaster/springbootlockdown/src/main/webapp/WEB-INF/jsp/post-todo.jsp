<%@ include file="./common/header.jspf" %>
<%@ include file="./common/navigation.jspf" %>

	<div class="container">
	Hello ${name}
	</div>
	
	<div class="container">
		<form:form method="post" modelAttribute="todo">
			<form:hidden path="id"/>
			<form:hidden path="user"/>
			<fieldset class="form-group">
				<form:label path="desc">Description</form:label> 
				<form:input type="text" path="desc" class="form-control" required="required"/>
				<form:errors path="desc" cssClass="text-danger"></form:errors>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="targetDate">Target date</form:label> 
				<form:input type="text" path="targetDate" class="form-control" required="required"/>
				<form:errors path="targetDate" cssClass="text-danger"></form:errors>
			</fieldset>
			<fieldset class="form-group">
				<button type="submit" class="btn btn-success">Send Todo</button>
			</fieldset>
		</form:form>
	</div>

<%@ include file="./common/scripts.jspf" %>

<script>
	 $('#targetDate').datepicker({format:'dd/mm/yyyy'});
</script>

<%@ include file="./common/footer.jspf" %>
	