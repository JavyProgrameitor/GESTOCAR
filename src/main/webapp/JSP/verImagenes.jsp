<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>VisualizarImagenes</title>
    </head>
<body class="bg-custom-green font-bobby-jones min-h-screen">
    <c:import url="../INC/nav.jsp"/>
    <div class="flex flex-col items-center justify-center p-4 mt-18 mb-10">
        <div class="overflow-x-auto w-full max-w-7xl">
            <h1 class="flex items-center justify-center text-shadow-black text-green-700 text-3xl font-black">Imágenes del Vehículo</h1>
            <div class="flex justify-center flex-wrap">
                <c:if test="${not empty fotos}">
                    <c:forEach items="${fotos}" var="foto">
                        <div class="m-2 w-64 h-64">
                            <img src="${pageContext.request.contextPath}/IMAGENES/${foto.foto}"/>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="flex justify-center space-x-7 mt-7">
                <form action="<c:out value='${contextPath}' />/ReturnUsu" method="post"> 
                    <button type="submit" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4">
                        SALIR
                    </button>
                </form>
            </div>
        </div>
    </div>
        <c:import url="/INC/footer.jsp"/> 
        <div>
            <hr class="w-full border-t-1 border-white">
        </div>
    </body>
</html>
