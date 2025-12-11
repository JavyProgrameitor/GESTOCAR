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
                src: url('IMG/bobby_jones/BobbyJones/BobbyJonesSoft.otf') format('opentype');
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
        <title>Usuario</title>
    </head>
    <body class="bg-custom-green min-h-screen">
        <c:import url="../INC/nav.jsp"/> 
        <c:if test="${vehiculoCreado}">
            <div id="modal" class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
                <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
                    <div class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
                    <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
                    <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
                        <div class="bg-green-600 p-4">
                            <h3 class="text-lg leading-6 font-medium text-white" id="modal-title">
                                Vehiculo creado con éxito
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
        <div class="container mx-auto p-4">
            <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-3 gap-3">
                <!-- Primer Bloque: Crear Vehículo -->
                <div class="bg-custom-gray rounded-lg p-4 md:p-4 mb-6 form-border text-shadow-black  ml-4">

                    <label class="block text-green-600 text-2xl font-black mt-4">Introduce tu Veh&iacute;culo :</label>
                    <div>
                        <hr class="w-full border-t-1 border-white mt-4">
                    </div>
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/crearVehiculoController" method="post" > 
                            <input type="hidden" id="idusuario" name="idusuario" value="${sessionScope.usuarioId}"/>
                            <!-- Marca -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Marca : </label>
                            <input type="text" id="marca" name="marca" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" required />
                            <!-- Modelo -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Modelo :</label>
                            <input type="text" id="modelo" name="modelo" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" required />
                            <!-- Motor -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Motor :</label>
                            <select id="motor" name="motor" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" required>
                                <option value="GASOLINA">Gasolina</option>
                                <option value="GASOIL">Gasoil</option>
                                <option value="ELECTRICO">Eléctrico</option>
                            </select>
                            <!-- Matricula -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Matricula :</label>
                            <input type="text" id="matricula" name="matricula" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" required />
                            <!-- Cilindrada -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Cilindrada :</label>
                            <input type="text" id="cilindrada" name="cilindrada" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" required />
                            <!-- Caballos -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Caballos :</label>
                            <input type="text" id="caballos" name="caballos" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" />
                            <!-- Color -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Color :</label>
                            <input type="color" id="color" name="color" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" />
                            <!-- Fecha Compra -->
                            <div class="border border-green-600 text-green-600 text-lg font-bold rounded w-full mb-2 mt-4">
                                <label for="fechaMatriculacion">* Fecha de Matriculación </label>
                                <input id="fechaMatriculacion" type="date" name="fechaCompra" placeholder="dd-mm-aaaa" required />
                            </div>
                            <!-- Fecha Venta -->
                            <div class="border border-green-600 text-green-600 text-lg font-bold rounded w-full mb-2 mt-4">
                                <label for="fechaVenta">Fecha Venta 'si está vendido'</label>
                                <input id="fechaVenta" type="date" name="fechaVenta" placeholder="dd-mm-aaaa">
                            </div>
                            <!-- Precio Compra -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">* Precio Compra :</label>
                            <input type="text" id="precioCompra" name="preciocompra" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" required />
                            <!-- Precio Venta -->
                            <label class="block text-green-600 text-lg font-bold mb-2 mt-4">Precio Venta :</label>
                            <input type="text" id="precioVenta" name="precioventa" class="border border-green-600 rounded w-full py-2 px-3 leading-tight focus:outline-none text-shadow-black" />
                            <!-- Botón -->
                            <div class="flex justify-center text-shadow-black mt-4">
                                <button type="submit" value="crearVehiculo" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                    CREAR VEHICULO
                                </button>
                            </div>
                        </form>

                    </div>
                    <div>
                        <hr class="w-full border-t-1 border-white mt-4">
                    </div>
                </div>

                <!-- Segundo Bloque: Listar, Modificar, Eliminar Vehículo -->
                <div class="bg-custom-gray rounded-lg p-4 md:p-4 mb-6 form-border text-shadow-black  ml-4">
                    <label class="block text-green-600 text-2xl font-black mb-2 mt-4">Selecciona una opci&oacute;n :</label>
                    <div>
                        <hr class="w-full border-t-1 border-white mt-4">
                    </div>

                    <!-- Visualizar Vehículo -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/visualizarVehiculoController" method="post"> 
                            <button type="submit" value="visualizarVehiculo" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                LISTAR SUS VEHICULOS
                            </button>
                        </form>
                    </div>

                    <!-- Modificar Vehículo -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/modificarVehiculoController" method="post"> 
                            <button type="submit" value="modificarVehiculo" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                MODIFICAR VEHICULO
                            </button>
                        </form>
                    </div>

                    <!-- Eliminar Vehículo -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/eliminarVehiculoController" method="post"> 
                            <button type="submit" value="eliminarVehiculo" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                ELIMINAR VEHICULO
                            </button>
                        </form>
                    </div>

                    <!-- Subir Imagenes -->

                    <div class="flex justify-center text-shadow-black m-4">
                        <form action="<c:out value='${contextPath}' />/imagenesVehiculoController" method="post">            
                            <div class="flex flex items center justify-center" >      
                                <button type="submit" value="subirImagenes" name="boton" class=" bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                    SUBIR IMAGENES
                                </button>
                            </div>
                        </form>
                    </div>

                    <!-- Boton Salir -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/Return" method="post"> 
                            <button type="submit" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                SALIR
                            </button>
                        </form>
                    </div>  
                    <div>
                        <hr class="w-full border-t-1 border-white mt-4">
                    </div>
                </div>
                <!--<!-- Tercer bloque -->
                <div class="bg-custom-gray rounded-lg p-4 md:p-4 mb-6 form-border text-shadow-black  ml-4">

                    <label class="block text-green-600 text-2xl font-black mb-2 mt-4">Gastos en su Vehculo:</label>
                    <div>
                        <hr class="w-full border-t-1 border-white mt-4">
                    </div>
                    <!-- Crear Gastos -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/gastoController" method="post"> 
                            <button type="submit" value="insertarGasto" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                INSERTAR GASTOS DE UN VEHICULO
                            </button>
                        </form>
                    </div>


                    <!-- Visualizar Gastos -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/visualizarGastoController" method="post"> 
                            <button type="submit" value="visualizarGasto" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                LISTAR TODOS LOS GASTOS DEL VEHICULO
                            </button>
                        </form>
                    </div>

                    <!-- Todo Gastos -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/todoGastoController" method="post"> 
                            <button type="submit" value="todoGasto" name="boton" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                TOTAL DE GASTOS DE TODOS SUS VEHICULO
                            </button>
                        </form>
                    </div>

                    <!-- Todo Gastos -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/ajaxGastoController" method="post"> 
                            <button type="submit" value="tabla" name="accion" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                               CONCEPTOS DE GASTOS
                            </button>
                        </form>
                    </div>

                    <!-- Boton Salir -->
                    <div class="flex justify-center text-shadow-black mt-4">
                        <form action="<c:out value='${contextPath}' />/Return" method="post"> 
                            <button type="submit" class="bg-green-700 hover:bg-green-600 text-shadow-black font-bold py-2 px-4 rounded focus:outline-none mt-4 text-shadow-black">
                                SALIR
                            </button>
                        </form>
                    </div>  

                    <div>
                        <hr class="w-full border-t-1 border-white mt-4">
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
