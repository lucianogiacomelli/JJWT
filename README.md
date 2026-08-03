# 🛡️ Spring Security - Roles & Permissions (RBAC)

Este repositorio contiene un proyecto de práctica desarrollado con Spring Boot que implementa un sistema de autorización basado en Roles y Permisos (RBAC) con persistencia en PostgreSQL.

Checklist — lo que encontrarás en este README
- [x] Resumen del proyecto orientado a un lector no técnico.
- [x] Tecnologías y herramientas usadas.
- [x] Qué se construyó y por qué importa (valor del proyecto).
- [x] Resumen de la arquitectura y decisiones clave de seguridad.
- [x] Cómo ejecutar la aplicación (instrucciones mínimas, enfocadas en demostrar que funciona).
- [x] Dónde mirar en el código para validar el trabajo (puntos de interés para revisión).

Este proyecto muestra la implementación de un sistema de control de acceso basado en roles y permisos dentro de una API REST construida con Spring Boot. Un usuario puede tener varios roles, y cada rol puede agrupar múltiples permisos; ambos (roles y permisos) se almacenan en la base de datos y se usan de forma dinámica para conceder o denegar acceso a recursos.

Por qué es relevante
- Muestra comprensión de conceptos fundamentales de seguridad (autenticación, autorización, cifrado de contraseñas).
- Aplica diseño persistente para usuarios/roles/permiso, lo que permite una gestión dinámica en tiempo de ejecución (útil en entornos empresariales).
- Incluye configuración para APIs stateless y consumo mediante clientes (Postman/cURL).

Qué incluye (resumen de funcionalidades)
- Gestión persistente de Usuarios (`UserSec`), Roles (`Roles`) y Permisos (`Permissions`).
- Mapeo dinámico de authorities: los roles se exponen como `ROLE_<NOMBRE>` y los permisos como su propio nombre (ej. `CREATE`).
- Endpoints CRUD para administrar permisos, roles y usuarios (expuestos bajo `/api/`).
- Autenticación multicanal: `httpBasic()` para APIs y `formLogin()` habilitado; contraseñas encriptadas.
- Configuración orientada a APIs: sesión stateless y CSRF desactivado para facilitar clientes sin cookies.

Stack tecnológico
- Java 25
- Spring Boot 4.1.0
- Spring Security 6
- Spring Data JPA / Hibernate
- PostgreSQL 17
- Gradle (wrapper incluido)
- Lombok

Aspectos destacados
- Seguridad dinámica: la clase `UserDetailsServiceImp` construye las authorities a partir de roles y permisos almacenados en BD, lo que permite cambiar permisos sin recompilar.
- Diseño modular: controladores, servicios y repositorios separados por responsabilidades.
- Persistencia automática para desarrollo: `spring.jpa.hibernate.ddl-auto=update` (se recomienda usar migraciones en producción).

Uso de tokens (JWT)

El proyecto incluye soporte para emisión y validación de tokens JWT. La autenticación mediante token se realiza desde el endpoint POST `/auth/login` que devuelve un objeto con el token de acceso (campo `accessToken`).

Comportamiento:
- El token se firma con una clave configurada en `security.jwt.private.key` y se emite por un identificador configurado en `security.jwt.user.generator`.
- Duración por defecto: 30 minutos desde la emisión.

Funciones principales disponibles (clase `JwtUtils`):
- `createToken(Authentication authentication)`: genera y firma un JWT a partir de la Authentication (se usa en el proceso de login).
- `validateToken(String token)`: valida la firma y el issuer; devuelve un `DecodedJWT` o lanza excepción en caso de token inválido.
- `extractUsename(DecodedJWT decodedJWT)`: extrae el `subject` (username) del token.
- `getSpecificClaim(DecodedJWT, String claimName)`: obtiene una claim específica.
- `returnAllClaims(DecodedJWT)`: devuelve todas las claims como un Map.

Filtro y validación en peticiones
- Existe un filtro/validador (`JwtTokenValidator`) que usa `JwtUtils` para validar tokens recibidos en cabecera.
- Uso típico en clientes: enviar el token en la cabecera `Authorization: Bearer <token>` para acceder a endpoints protegidos.

Ejemplo rápido con cURL
- Login y obtención de token:

```bash
curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password"}'
```

Respuesta esperada (ejemplo):

```json
{
  "username": "admin",
  "message": "Login OK",
  "accessToken": "<JWT_TOKEN_AQUI>",
  "success": true
}
```

- Usar el token para llamar a un endpoint protegido:

```bash
curl -H "Authorization: Bearer <JWT_TOKEN_AQUI>" http://localhost:8080/api/users
```

Notas de configuración
- Las propiedades relevantes están en `src/main/resources/application.properties` y usan las claves `security.jwt.private.key` y `security.jwt.user.generator`.