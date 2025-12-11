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
        <title>CrearGasto</title>
    </head>
    <body class="bg-custom-green  min-h-screen">
        <c:import url="../INC/nav.jsp"/> 
           <c:if test="${gastoCreado}">
            <div id="modal" class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
                <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                    <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
                    <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                    <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                        <div class="bg-green-600 p-4">
                            <h3 class="text-lg leading-6 font-medium text-white" id="modal-title">
                                Gasto Insertado en tu Base de Datos
                            </h3>
                        </div>
                        <div class="bg-white px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
                            <button type="button" onclick="window.location.href = '${contextPath}/JSP/usuario.jsp'" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-green-600 text-base font-medium text-white hover:bg-green-700 focus:outline-none sm:ml-3 sm:w-auto sm:text-sm">
                                Volver
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="flex flex items-center justify-center m-20">
            <form action="<c:out value='${contextPath}' />/gastoController" method="post" class="bg-white p-6 rounded-lg shadow-lg ">
                <label class="block text-green-600 text-2xl font-black mb-4">Gastos de tu Vehículo:</label>

                <!-- idvehiculo -->
                <input type="hidden" id="idvehiculo" name="idvehiculo" value="${idvehiculo}"/>
                <!-- Concepto -->
                <label class="block text-green-600 text-lg font-bold mb-2">* Concepto:</label>
                <input type="text" id="conceptoInput" list="conceptos" name="concepto" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black"  />
                <datalist id="conceptos">
                    <option value="Combustible"></option>
                    <option value="Reparaciones"></option>
                    <option value="Ruedas"></option>
                    <option value="Trámites"></option>
                    <option value="Mantenimiento"></option>
                </datalist>
                <!-- Fecha Gasto -->
                <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Fecha del Gasto:</label>
                <input id="fechaGasto" type="date" name="fechaGasto" placeholder="dd-mm-aaaa" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black"  />
                <!-- Descripcion -->
                <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Descripcion:</label>
                <input type="text" id="descripcion" name="descripcion" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black"  />
                <!-- Importe -->
                <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Importe:</label>
                <input type="number" id="importe" name="importe" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black"  />
                <!-- Establecimiento -->
                <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Establecimiento:</label>
                <input type="text" id="establecimiento" name="establecimiento" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black"  />
                <!-- Kilometros -->
                <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Kilometros:</label>
                <input type="text" id="kilometros" name="kilometros" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" />
                <!-- Botón -->
                <div class="flex justify-center text-shadow-black mt-4">
                    <button type="submit" value="crearGasto" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                        CREAR GASTO
                    </button>
                </div>
            </form>
        </div>
        <c:if test="${error}">
            <div id="modal" class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
                <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                    <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
                    <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                    <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                        <div class="bg-red-600 p-4">
                            <h3 class="text-lg leading-6 font-medium text-white" id="modal-title">
                                Debes de rellenar todos los campos
                            </h3>
                        </div>
                        <div class="bg-white px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
                            <button type="button" onclick="window.location.href = '${contextPath}/JSP/usuario.jsp'" class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-green-600 text-base font-medium text-white hover:bg-green-700 focus:outline-none sm:ml-3 sm:w-auto sm:text-sm">
                                OK
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <c:import url="/INC/footer.jsp"/> 
    </body>
</html>
