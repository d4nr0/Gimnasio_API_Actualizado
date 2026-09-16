# Proyecto: API Gimnasio (DAW II - CIBERTEC)

Cumple con los requisitos de la especificación:
- Servicio Web REST de login: usuario y password en base de datos, password cifrado con `BCryptPasswordEncoder`.
- Aplicación web (Angular) que consume servicios REST con métodos GET, POST, PUT y DELETE, persistiendo en base de datos MySQL.

## Estructura

```
gym-backend/    -> API REST en Spring Boot (Java 17, Spring Data JPA, Spring Security, JWT, MySQL)
gym-frontend/   -> Aplicación Angular (17) que consume la API
```

## 1. Backend (Spring Boot)

### Requisitos
- Java 17+
- Maven 3.8+
- MySQL 8 corriendo en `localhost:3306`

### Configuración
1. Crea la base de datos (o deja que se cree sola, ya está configurado `createDatabaseIfNotExist=true`):
   ```sql
   CREATE DATABASE db_gimnasio;
   ```
2. Ajusta usuario/contraseña de MySQL en `gym-backend/src/main/resources/application.properties` si no usas `root/root`.

### Ejecutar
```bash
cd gym-backend
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

Al iniciar por primera vez, se crea automáticamente un usuario administrador:
- **usuario:** `admin`
- **password:** `admin123`

### Endpoints principales

| Método | Endpoint              | Descripción                          | Requiere token |
|--------|-----------------------|---------------------------------------|----------------|
| POST   | /api/auth/login       | Login, devuelve JWT                   | No             |
| GET    | /api/socios           | Lista todos los socios                | Sí             |
| GET    | /api/socios/{id}      | Obtiene un socio por id               | Sí             |
| POST   | /api/socios           | Crea un socio                         | Sí             |
| PUT    | /api/socios/{id}      | Actualiza un socio                    | Sí             |
| DELETE | /api/socios/{id}      | Elimina un socio                      | Sí             |

Ejemplo de login (curl):
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

La respuesta trae un `token` que debes enviar en el header `Authorization: Bearer <token>` para las peticiones a `/api/socios/**`.

### Pruebas de la capa de acceso a datos
Ya incluidas en `src/test/java/.../SocioRepositoryTest.java` (insertar, listar, actualizar, eliminar, buscar por DNI), usando H2 en memoria. Ejecutar con:
```bash
mvn test
```

## 2. Frontend (Angular)

### Requisitos
- Node.js 18+
- Angular CLI (`npm install -g @angular/cli`)

### Instalación y ejecución
```bash
cd gym-frontend
npm install
ng serve
```

La app queda disponible en `http://localhost:4200`. Al ingresar con `admin / admin123` podrás listar, crear, editar y eliminar socios, todo persistido en MySQL a través de la API.

## Notas para tu informe

- **Diagnóstico / SEPTE, Objetivos, Justificación**: son secciones de investigación que debes redactar tú según el contexto real que elijas (ej. un gimnasio local, cadena de gimnasios, etc.). Este código cubre la parte técnica (item 4 de la especificación y los criterios "Servicio Web Rest Login" e "Implementa/Consume servicios web Rest" de la rúbrica).
- Puedes ampliar el CRUD agregando más entidades (ej. Membresías, Pagos) siguiendo el mismo patrón (Entity → Repository → Service → Controller → Angular service/componentes).

Credencial de github: git log / git push -v origin main
