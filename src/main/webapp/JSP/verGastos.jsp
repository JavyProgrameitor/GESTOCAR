<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.include file="../INC/metas.inc"/>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
<!DOCTYPE html>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.css"
              integrity="sha512-KOWhIs2d8WrPgR4lTaFgxI35LLOp5PRki/DxQvb7mlP29YZ5iJ5v8tiLWF7JLk5nDBlgPP1gHzw96cZ77oD7zQ=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/daisyui@4.10.1/dist/full.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.tailwindcss.com"></script>
        <script>
            tailwind.config = {
                theme: {
                    extend: {
                        colors: {
                            'custom-green': '#A6B6A0',
                            'custom-gray': '#C4C4C4',
                        },
                        fontFamily: {
                            'bobby-jones': ['Bobby-Jones', 'sans-serif']
                        }
                    },
                }
            }
            function closeModal() {
                document.getElementById('modal').style.display = 'none';
            }
        </script>
        <style>
            @font-face {
                font-family: 'Bobby-Jones';
                src: url('${contextPath}/IMG/bobby_jones/BobbyJones/BobbyJonesSoft.otf') format('opentype');
            }
            .text-shadow-black {
                text-shadow: 1px 1px 1px rgba(1, 1, 1, 1);
            }
            .text-shadow-white {
                text-shadow: 1px 1px 1px rgb(253, 253, 252);
            }
            .font-bobby-jones {
                font-family: 'Bobby-Jones', sans-serif;
            }
        </style>
        <title>Visualizar Gastos</title>
    </head>
    <body class="bg-custom-green font-bobby-jones min-h-screen">
        <c:import url="../INC/nav.jsp"/> 
       
            <div class="container mx-auto px-4 py-8">
                <h1 class="text-2xl font-bold mb-6 text-center">Gastos del Vehículo</h1>
                <table class="min-w-full bg-white">
                    <thead>
                        <tr>
                            <th class="py-2">Concepto</th>
                            <th class="py-2">Fecha</th>
                            <th class="py-2">Descripción</th>
                            <th class="py-2">Importe</th>
                            <th class="py-2">Establecimiento</th>
                            <th class="py-2">Kilómetros</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="gasto" items="${gastos}">
                            <tr class="text-center">
                                <td class="py-2"><c:out value="${gasto.concepto}"/></td>
                                <td class="py-2"><c:out value="${gasto.fechaGasto}"/></td>
                                <td class="py-2"><c:out value="${gasto.descripcion}"/></td>
                                <td class="py-2"><c:out value="${gasto.importe}"/></td>
                                <td class="py-2"><c:out value="${gasto.establecimiento}"/></td>
                                <td class="py-2"><c:out value="${gasto.kilometros}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
                <!-- Boton Salir -->
                <div class="flex justify-center text-shadow-black mt-4">
                    <form action="<c:out value='${contextPath}' />/ReturnUsu" method="post"> 
                        <button type="submit" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                            SALIR
                        </button>
                    </form>
                </div> 
            <c:if test="${empty gastos}">
                <p>No se encontraron gastos para el vehículo seleccionado.</p>
            </c:if>
            <c:import url="/INC/footer.jsp"/> 
    </body>
</html>
