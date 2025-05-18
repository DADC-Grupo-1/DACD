## Descripción del Proyecto y Propuesta de Valor

Este proyecto implementa un sistema de recomendación de recetas con sus respectivos costes a partir de ingredientes disponibles utilizando múltiples fuentes de datos y principios de diseño orientado a eventos. El objetivo es permitir a los usuarios recibir sugerencias de recetas personalizadas, optimizando así su experiencia culinaria y evitando el desperdicio de alimentos.

### Propuesta de valor:

- Integración de datos de productos (Mercadona) y recetas (Spoonacular).
- Modelo de eventos para asegurar escalabilidad y flexibilidad.
- Modularidad para facilitar mantenimiento y expansión.

## Justificación Elección APIs y Estructura del Datamart

### APIs Utilizadas:

- **MercadonaProducts**: Extrae y estructura productos disponibles en tiendas con sus respectivos precios actualizados en el mercado real.
- **SpoonacularRecipes**: Obtiene recetas y metadatos desde la API de Spoonacular.

### Estructura del Datamart:

- Se utiliza una base de datos **SQLite** (`identifier.sqlite`) como datamart para integrar productos e ingredientes mediante identificadores unificados.
- Esta base permite consultas eficientes al momento de realizar recomendaciones cruzando ingredientes disponibles con los requeridos por las recetas.

## Instrucciones de Compilación y Ejecución

1. Ejecutar `main` de `eventStoreBuilder` para crear la suscripción del usuario
2. Ejecutar `main` de cada feeder (`MercadonaProducts`, `SpoonacularRecipes`)
3. Instalar `node`(`npm install --legacy-peer-deps)
4. Ejecutar en la carpeta frontend (`npm run dev)
5. Ejecutar `App.java` en `App/src/java/infraestructure/store.

⚠️ Es normal que de un error `HTTTP 429`, eso sucede porque la API no puede procesar tantas solicitudes en tan poco tiempo.

## Ejemplos de uso.  

EL usuario puede consultar recetas con sus respectivos precios de compra de los ingredientes. Además, el usuario es capaz de filtar recetas para obtener recetas Veganas, Vegetarianas, libres de Gluten y libres de Lactosa. El usuario también es capaz de buscar recetas por ingredientes, y obtener recetas con los alimentos que ya tiene disponibles. El programa provee al usuario de los pasos detallados de la receta, las raciones que obtendrá, y tanto el costo del monto general como el de cada ración.

## Arquitectura de sistema y arquitectura de la aplicación

- **Orientada a eventos**: Basada en el patrón Event Sourcing.
- **Modular**: Separación por responsabilidades (fuentes de datos, core de lógica, persistencia).
- **Persistencia**: Base de datos local SQLite para portabilidad y rendimiento.

## Principios y patrones de diseño aplicados en cada módulo

- **Event Sourcing**: Gestión de eventos para auditar y reconstruir el estado.
- **DAO Pattern**: Acceso a datos desacoplado de la lógica del negocio.
- **Builder Pattern**: Empleado en `EventStoreBuilder` para construir el Event Store.

## Diagrama de Clases:

![FoodApp](https://github.com/user-attachments/assets/96137db7-6f0f-4978-979b-ef627048101c)
