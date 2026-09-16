import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Socio } from './socio.model';

@Injectable({ providedIn: 'root' })
export class SocioService {

  private baseUrl = `${environment.apiUrl}/socios`;

  constructor(private http: HttpClient) {}

  // GET /api/socios
  listar(): Observable<Socio[]> {
    return this.http.get<Socio[]>(this.baseUrl);
  }

  // GET /api/socios/{id}
  obtenerPorId(id: number): Observable<Socio> {
    return this.http.get<Socio>(`${this.baseUrl}/${id}`);
  }

  // POST /api/socios
  crear(socio: Socio): Observable<Socio> {
    return this.http.post<Socio>(this.baseUrl, socio);
  }

  // PUT /api/socios/{id}
  actualizar(id: number, socio: Socio): Observable<Socio> {
    return this.http.put<Socio>(`${this.baseUrl}/${id}`, socio);
  }

  // DELETE /api/socios/{id}
  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
