<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="ingresso"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<ingresso:template>
	<jsp:body>
		<div class=" col-md-6 col-md-offset-3">
		<table class="table table-hover ">
			<thead>
				<tr>
					<th>ID</th>
					<th>Sessão</th>
					<th>Filme</th>
					<th>Sala</th>
					<th>Lugar</th>					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ingresso" items="${ingressos}">
					<tr>
						<td>${ingresso.id}</td>
						<td>${ingresso.sessao.horario}</td>
						<td>${ingresso.sessao.filme.nome}</td>
						<td>${ingresso.sessao.sala.nome}</td>	
						<td>${ingresso.lugar.fileira}${ingresso.lugar.posicao}</td>					
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>

	</jsp:body>
</ingresso:template>
