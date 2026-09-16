export interface Socio {
  id?: number;
  nombres: string;
  apellidos: string;
  dni: string;
  email?: string;
  telefono?: string;
  fechaNacimiento?: string;
  plan: string;
  fechaInscripcion?: string;
  activo?: boolean;
}
