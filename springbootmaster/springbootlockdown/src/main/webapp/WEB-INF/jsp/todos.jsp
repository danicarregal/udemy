<%@ include file="./common/header.jspf" %>
<%@ include file="./common/navigation.jspf" %>

<div class="container">
<div><h1>Hello ${name}, here your TODOS:</h1></div>
<div>
	<table class="table table-striped">
		<thead>
		<tr>
			<th>ID</th>
			<th>DESC</th>
			<th>DATE</th>
			<th>DONE</th>
			<th></th>
			<th></th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="todo" items="${todos}">
			<tr>
				<td>${todo.id}</td>
				<td>${todo.desc}</td>
				<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
				<td>${todo.done}</td>
				<td><a class="btn btn-warning" href="/delete-todo-path/${todo.id}">Remove</a></td>
				<td><a class="btn btn-primary" href="/post-todo?id=${todo.id}">Update</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div><a class="button" href="/post-todo">Add a Todo</a></div>

<%@ include file="./common/scripts.jspf" %>

<script>
	 $('#todosMenuItem').addClass("active");
	 $('#homeMenuItem').removeClass("active");
</script>

<%@ include file="./common/footer.jspf" %>