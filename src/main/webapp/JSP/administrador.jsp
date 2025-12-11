<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.include file="../INC/metas.inc" />
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.css"
              integrity="sha512-KOWhIs2d8WrPgR4lTaFgxI35LLOp5PRki/DxQvb7mlP29YZ5iJ5v8tiLWF7JLk5nDBlgPP1gHzw96cZ77oD7zQ=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.css"
              integrity="sha512-KOWhIs2d8WrPgR4lTaFgxI35LLOp5PRki/DxQvb7mlP29YZ5iJ5v8tiLWF7JLk5nDBlgPP1gHzw96cZ77oD7zQ=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/daisyui@4.10.1/dist/full.min.css" rel="stylesheet" type="text/css" />
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
                src: url('${pageContext.request.contextPath}/IMG/bobby_jones/BobbyJones/BobbyJonesSoft.otf') format('opentype');
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
        <title>Administrador</title>
    </head>
    <body class="bg-custom-green font-bobby-jones h-auto">
        <c:import url="../INC/nav.jsp" />
        <div class="flex flex-col items-center justify-center p-4 mt-18 mb-10">
            <h1 class="flex items-center justify-center text-shadow-black text-green-700 text-4xl font-black m-4">ACCESO AL SISTEMA COMO ADMINISTRADOR DE LA APLICACION:</h1>
            <div class="flex flex-wrap justify-evenly overflow-x-auto w-full max-w-7xl">
                
                <form action="administradorController" method="post">
                    <button type="submit" name="boton" value="eliminarVehiculo"
                            class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none m-4">
                        ENTRAR COMO ADMINISTRADOR
                    </button>
                </form>

                <form action="<c:out value='${contextPath}' />/ReturnUsu" method="post">
                    <button type="submit" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4">
                        SALIR
                    </button>
                </form>
                    
            </div>
        </div>
        <div>
            <hr class="w-full border-t-1 border-white mt-4">
        </div>

        <div class="flex justify-center items-center mt-10 mb-10">
            <div class="carousel w-1/2 max-h-96">
                <!-- Carousel items -->
                <div id="slide1" class="carousel-item relative w-full">
                    <img src="IMG/CapturaLogo.png" class="w-full" />
                    <div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                        <a href="#slide4" class="btn btn-circle">❮</a>
                        <a href="#slide2" class="btn btn-circle">❯</a>
                    </div>
                </div>
                <div id="slide2" class="carousel-item relative w-full">
                    <img src="IMG/concesionario3.jpg" class="w-full" />
                    <div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                        <a href="#slide1" class="btn btn-circle">❮</a>
                        <a href="#slide3" class="btn btn-circle">❯</a>
                    </div>
                </div>
                <div id="slide3" class="carousel-item relative w-full">
                    <img src="IMG/concesionario4.jpg" class="w-full" />
                    <div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                        <a href="#slide2" class="btn btn-circle">❮</a>
                        <a href="#slide4" class="btn btn-circle">❯</a>
                    </div>
                </div>
                <div id="slide4" class="carousel-item relative w-full">
                    <img src="IMG/concesionario2.jpg" class="w-full" />
                    <div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                        <a href="#slide3" class="btn btn-circle">❮</a>
                        <a href="#slide5" class="btn btn-circle">❯</a>
                    </div>
                </div>
                <div id="slide5" class="carousel-item relative w-full">
                    <img src="IMG/concesionario5.jpg" class="w-full" />
                    <div class="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                        <a href="#slide4" class="btn btn-circle">❮</a>
                        <a href="#slide1" class="btn btn-circle">❯</a>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/INC/footer.jsp"/> 
        <div>
            <hr class="w-full border-t-1 border-white">
        </div>
    </body>
</html>

