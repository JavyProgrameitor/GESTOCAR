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
        <title>Total de Gastos</title>
    </head>
    <body class="bg-custom-green font-bobby-jones min-h-screen">
        <c:import url="../INC/nav.jsp"/> 
        <div class="container mx-auto px-4 py-8 text-center">
            <h1 class="text-2xl font-bold text-green-700 mb-4">Total de Gastos</h1>
            <div class="bg-white p-6 rounded-lg shadow-lg">
                <h2 class="text-xl font-bold mb-4">Total de Gastos de Todos los Vehículos:</h2>
                <p class="text-2xl text-green-700">
                    Total: <c:out value="${totalGastos}" /> €
                </p>
                <!-- Boton Salir -->
                <div class="flex justify-center text-shadow-black mt-4">
                    <form action="<c:out value='${contextPath}' />/ReturnUsu" method="post"> 
                        <button type="submit" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                            SALIR
                        </button>
                    </form>
                </div> 
            </div>
        </div>
        <c:import url="/INC/footer.jsp"/> 
    </body>
</html>
