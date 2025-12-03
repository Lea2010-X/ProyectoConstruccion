# Lumina - POS

Lumina es una aplicación de escritorio desarrollada en Java diseñada para ayudar a negocios nuevos a gestionar sus transacciones de manera eficiente. El sistema permite registrar un inventario de productos, administrar una base de datos de clientes y realizar ventas, generando comprobantes y permitiendo consultas históricas.

El objetivo principal es ofrecer una herramienta ligera, monousuario y fácil de usar para emprendimientos que inician su digitalización.

---

## Tabla de Contenidos

1. [Información General](#información-general)
2. [Arquitectura del Sistema](#arquitectura-del-sistema)
3. [Estructura de Paquetes](#estructura-de-paquetes)
4. [Componentes Principales](#componentes-principales)
5. [Patrones de Diseño Implementados](#patrones-de-diseño-implementados)
6. [Configuración del Sistema](#configuración-del-sistema)
7. [Base de Datos](#base-de-datos)
8. [Testing y Pruebas](#testing-y-pruebas)
9. [Librerías Externas](#librerías-externas)
10. [Manual de Instalación](#manual-de-instalación)
11. [Manual de Uso](#manual-de-uso)
12. [Documentación de Clases Principales](#documentación-de-clases-principales)
13. [Integrantes del Proyecto](#integrantes-del-proyecto)

---

## Información General

| **Campo** | **Valor** |
|-----------|-----------|
| **Nombre del Proyecto** | ProyectoConstrucción - Sistema Lumina POS |
| **Tipo de Sistema** | Sistema de Punto de Venta (Point of Sale) |
| **Lenguaje** | Java 8+ |
| **Framework de UI** | Swing |
| **Base de Datos** | MySQL |
| **Arquitectura** | MVC (Modelo-Vista-Controlador) |
| **Herramientas de Construcción** | Apache Ant + NetBeans |
| **Framework de Testing** | JUnit 5 + Mockito |

### Descripción
Sistema integral para la gestión de ventas que incluye administración de clientes, productos, procesamiento de ventas y generación de reportes en PDF. Implementa las mejores prácticas de construcción de software incluyendo arquitectura limpia, testing comprehensivo y documentación completa.

---

## Arquitectura del Sistema

El sistema implementa el patrón **MVC (Modelo-Vista-Controlador)** con las siguientes capas:

### Capa de Presentación (Vista)
- Formularios Swing para interacción con el usuario
- Aplicación de temas modernos y UI consistente
- Validación de entrada en tiempo real

### Capa de Control (Controlador)
- Coordinación entre Vista y Modelo
- Implementación de lógica de negocio
- Manejo de eventos y acciones del usuario

### Capa de Modelo (Modelo + DAO)
- Modelos de datos con validaciones integradas
- Acceso a datos mediante patrón DAO
- Abstracción de la capa de persistencia

### Capa de Utilidades
- Servicios transversales (PDF, configuración, constantes)
- Temas y estilos visuales
- Manejo centralizado de mensajes

---

## Estructura de Paquetes

```
src/
├── Main.java                          # Punto de entrada de la aplicación
├── Configuracion/                     # Configuración y conexión a BD
│   └── CConexion.java
├── Controlador/                       # Lógica de negocio y coordinación
│   ├── ControladorBase.java
│   ├── ControladorCliente.java
│   ├── ControladorProducto.java
│   ├── ControladorReportes.java
│   └── ControladorVenta.java
├── DAO/                              # Acceso a datos
│   ├── BaseDAO.java
│   ├── ClienteDAO.java
│   ├── DetalleDAO.java
│   ├── FacturaDAO.java
│   ├── ProductoDAO.java
│   └── ReportesDAO.java
├── Modelo/                           # Modelos de datos
│   ├── ModeloCliente.java
│   ├── ModeloDetalleVenta.java
│   ├── ModeloItemCarrito.java
│   ├── ModeloProductoInventario.java
│   ├── ModeloReporteFactura.java
│   └── ModeloReporteVentasPeriodo.java
├── Util/                             # Utilidades del sistema
│   ├── Constantes.java
│   ├── GeneradorPDF.java
│   ├── Mensajes.java
│   └── TemaModerno.java
└── Vista/                            # Interfaces de usuario
    ├── FrmClientes.java
    ├── FrmMenuPrincipal.java
    ├── FrmProducto.java
    ├── FrmReporteFactura.java
    ├── FrmReporteVentasPeriodo.java
    └── FrmVentas.java

test/                                 # Pruebas unitarias
├── Configuracion/
├── Controlador/
├── DAO/
├── Modelo/
└── Util/
```

---

## Componentes Principales

### Main.java
**Propósito**: Punto de entrada de la aplicación. Inicializa el formulario principal en el Event Dispatch Thread de Swing para asegurar thread safety.

### FrmMenuPrincipal
**Propósito**: Ventana principal del sistema con:
- Menú lateral moderno con gradientes
- Navegación a todos los módulos
- Interfaz responsive y accesible

### Controladores
- **ControladorBase**: Clase abstracta que define el contrato base
- **ControladorVenta**: Orquesta el proceso completo de venta
- **ControladorCliente**: Gestiona operaciones CRUD de clientes
- **ControladorProducto**: Administra inventario y productos
- **ControladorReportes**: Maneja generación de reportes

### Modelos de Datos
- Implementan validaciones de negocio
- Encapsulación con getters/setters
- Inmutabilidad donde es apropiado
- Validaciones en constructores y setters

### DAOs (Data Access Objects)
- **BaseDAO**: Infraestructura común de conexión
- DAOs específicos para cada entidad
- Inyección de dependencias para testing
- Manejo de excepciones SQL

---

## Patrones de Diseño Implementados

### Patrón MVC (Modelo-Vista-Controlador)
Separación clara de responsabilidades entre presentación, lógica de negocio y datos.

### Patrón DAO (Data Access Object)
Abstrae el acceso a datos y proporciona una interfaz uniforme.

### Patrón Template Method
BaseDAO y ControladorBase definen esqueletos que las subclases implementan.

### Patrón Factory
GeneradorPDF actúa como factory para crear documentos PDF específicos.

### Inyección de Dependencias
Constructores alternativos para testing con mocks.

### Patrón Singleton Implícito
Clases de utilidad como Mensajes y TemaModerno.

---

## Configuración del Sistema

### Archivo: config.properties
- **Ubicación**: Raíz del proyecto
- **Propósito**: Configuración de conexión a base de datos

#### Propiedades configurables:
| **Propiedad** | **Descripción** | **Por defecto** |
|---------------|-----------------|-----------------|
| `db.usuario` | Usuario de MySQL | root |
| `db.contrasena` | Contraseña de MySQL | (vacía) |
| `db.host` | Servidor de BD | localhost |
| `db.puerto` | Puerto de MySQL | 3306 |
| `db.nombre` | Nombre de la base de datos | dbpos |

### Clase: CConexion
- Carga configuración desde archivo properties
- Maneja conexiones MySQL
- Soporte para configuraciones de prueba
- Manejo de errores de conexión

---

## Base de Datos

### Especificaciones
- **Motor**: MySQL 5.7+
- **Nombre**: dbpos (configurable)

### Tablas Principales
| **Tabla** | **Propósito** |
|-----------|---------------|
| `clientes` | Información de clientes |
| `productos` | Catálogo de productos |
| `facturas` | Cabeceras de ventas |
| `detalle_venta` | Líneas de detalle de ventas |

### Archivos SQL
- `dbpos.sql`: Estructura y datos de producción
- `dbpos_test.sql`: Base de datos para pruebas

### Características
- Integridad referencial con foreign keys
- Campos requeridos y validaciones a nivel BD
- Índices para optimización de consultas

---

## Testing y Pruebas

### Framework
- **Principal**: JUnit 5 + Mockito
- **Cobertura**: Modelos, Controladores, DAOs, Utilidades

### Tipos de Pruebas

#### Pruebas Unitarias de Modelos
- Validación de reglas de negocio
- Testing de constructores y setters
- Verificación de inmutabilidad

#### Pruebas de Controladores
- Mocking de dependencias DAO
- Verificación de orquestación
- Testing de lógica de negocio

#### Pruebas de DAOs
- Base de datos de prueba independiente
- Testing de operaciones CRUD
- Manejo de excepciones SQL

#### Pruebas de Utilidades
- Generación de PDFs
- Validaciones de entrada
- Funciones helper

### Configuración de Testing
- **BaseDAOTest**: Infraestructura común para pruebas de BD
- **CConexionTest**: Conexión específica para testing
- Limpieza automática de tablas entre pruebas

---

## Librerías Externas

### Producción
| **Librería** | **Versión** | **Propósito** |
|--------------|-------------|---------------|
| MySQL Connector/J | 9.1.0 | Driver JDBC para MySQL |
| iText PDF | 5.5.13.3 | Generación de documentos PDF |
| JCalendar | - | Componente de selección de fechas |

### Desarrollo y Testing
| **Librería** | **Versión** | **Propósito** |
|--------------|-------------|---------------|
| JUnit | 5.11.0 | Framework de pruebas unitarias |
| Mockito | 5.11.0 | Framework de mocking |
| Byte Buddy | - | Generación dinámica de código |

### Construcción
- **Apache Ant**: Herramienta de construcción
- **NetBeans**: IDE y gestión de proyecto

---

## Manual de Instalación

### Prerrequisitos
1. Java JDK 8 o superior
2. MySQL Server 5.7 o superior
3. NetBeans IDE (opcional, recomendado)

### Pasos de Instalación

#### 1. Preparar Base de Datos
```sql
CREATE DATABASE dbpos;
-- Ejecutar script dbpos.sql para crear estructura y datos
```

#### 2. Configurar Conexión
Editar `config.properties` con los datos de tu BD:
```properties
db.usuario=tu_usuario
db.contrasena=tu_contraseña
db.host=localhost
db.puerto=3306
db.nombre=dbpos
```

#### 3. Compilar Proyecto
```bash
# Opción 1: Usar script batch
compile.bat

# Opción 2: Usar NetBeans
# Abrir proyecto y usar Build
```

#### 4. Ejecutar Aplicación
```bash
# Opción 1: Usar script batch
run.bat

# Opción 2: Desde NetBeans
# Usar Run Project
```

### Verificación
- El sistema debe mostrar el menú principal
- Verificar conexión a BD navegando a cualquier módulo
- Probar funcionalidades básicas (agregar cliente, producto)

---

## Manual de Uso

### Menú Principal
- **Lumina POS**: Sistema de punto de venta moderno
- Navegación lateral con opciones principales
- Interfaz intuitiva y responsive

### Módulos Principales

#### Ventas
1. Seleccionar cliente de lista desplegable
2. Agregar productos al carrito
3. Procesar venta y generar factura
4. Validación automática de stock

#### Clientes
1. Registrar nuevos clientes
2. Editar información existente
3. Búsqueda y filtrado
4. Validaciones de campos requeridos

#### Productos
1. Administrar catálogo de productos
2. Control de inventario y precios
3. Actualización de stock
4. Cálculo automático de valores

#### Reportes
1. Buscar comprobante por número
2. Consulta de ventas por rango de fechas
3. Generación automática de PDFs
4. Exportación a carpeta `reportes_pdf/`

---

## Documentación de Clases Principales

### Clase: Main
- **Propósito**: Punto de entrada de la aplicación
- **Métodos Principales**:
  - `main(String[] args)`: Inicia aplicación en EDT

### Clase: FrmMenuPrincipal
- **Propósito**: Ventana principal con navegación
- **Responsabilidades**:
  - Mostrar menú lateral moderno
  - Navegación entre módulos
  - Aplicar tema visual consistente
  - Manejo de eventos de menú

### Clase: ControladorVenta
- **Propósito**: Orquesta el proceso completo de venta
- **Dependencias**: ClienteDAO, ProductoDAO, FacturaDAO, DetalleDAO
- **Métodos Principales**:
  - `buscarClientes(String)`: Búsqueda de clientes
  - `buscarProductos(String)`: Búsqueda de productos
  - `procesarVenta(int, List<ModeloItemCarrito>)`: Procesa venta completa
  - `validarDependencias()`: Verifica inyección de dependencias

### Clase: BaseDAO
- **Propósito**: Infraestructura común para acceso a datos
- **Características**:
  - Manejo centralizado de conexiones
  - Soporte para inyección de dependencias
  - Constructor para testing con BD de prueba
  - Método helper `getConnection()`

### Clase: ModeloCliente
- **Propósito**: Representa un cliente del sistema
- **Validaciones**:
  - Nombre, apellidos requeridos y no vacíos
  - Trim automático de espacios
  - IllegalArgumentException en valores inválidos

### Clase: GeneradorPDF
- **Propósito**: Generación de reportes en PDF
- **Características**:
  - Plantillas para facturas y reportes de ventas
  - Estilos y formatos predefinidos
  - Creación automática de directorio de salida
  - Manejo de excepciones de documento

### Clase: TemaModerno
- **Propósito**: Aplicar estilos visuales modernos
- **Características**:
  - Paleta de colores Material Design
  - Fuentes Segoe UI consistentes
  - Métodos helper para estilizar componentes
  - Constantes para reutilización

---

## Integrantes del Proyecto

| Nombre del Integrante | Foto |
|----------------------|------|
| Esteban Canto Vázquez |<img src="Assets/Integrantes/Esteban.jpeg" alt="Esteban" width="150" height="150" style="border-radius: 50%; object-fit: cover;">|
| José Manuel Ceballos Medina |<img src="Assets/Integrantes/Jose.jpeg" alt="Jose" width="150" height="150" style="border-radius: 50%; object-fit: cover;">|
| Ángel Leandro Puch Uribe |<img src="Assets/Integrantes/Angel.jpg" alt="Angel" width="150" height="150" style="border-radius: 50%; object-fit: cover;">|
| Mauricio Emiliano Ramírez Ceciliano |<img src="Assets/Integrantes/Mauricio.jpeg" alt="Mauricio" width="150" height="150" style="border-radius: 50%; object-fit: cover;"> |

---

## Información de Desarrollo

**Desarrollado como proyecto académico** para la materia Construcción de Software.

### Implementa las mejores prácticas de desarrollo:
- Código limpio y legible
- Arquitectura extensible
- Testing comprehensivo
- Documentación completa
- Patrones de diseño apropiados

### Contacto
Para soporte técnico o consultas sobre implementación, consultar la documentación de código fuente inline (JavaDoc).

---

**Última actualización**: Diciembre 2025
**Versión**: 1.0

