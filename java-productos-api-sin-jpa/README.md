## Limpia y Reconstruye el Proyecto
mvn clean
mvn install

## Compilar y Empaquetar el Proyecto
mvn clean package

## ejecutar
java -jar target/demo-0.0.1-SNAPSHOT.jar

curl -X GET http://localhost:3000/productos/1

## postman
# Obtener Todos los Productos
http://localhost:3000/productos

# Crear un Nuevo Producto
Método: POST
URL: http://localhost:3000/productos
Headers: Content-Type: application/json
Body (raw, JSON)
{
"nombre": "Nuevo Producto",
"descripcion": "Descripción del nuevo reserva",
"precio": 19.99,
"stock": 100
}

# Actualizar un Producto
Método: PUT
URL: http://localhost:3000/productos
Headers:
Key: Content-Type
Value: application/json
{
"id": 1,
"nombre": "Producto Actualizado",
"descripcion": "Descripción actualizada",
"precio": 29.99,
"stock": 50
}

# Eliminar un Producto
Método: DELETE
URL: http://localhost:3000/productos/1


