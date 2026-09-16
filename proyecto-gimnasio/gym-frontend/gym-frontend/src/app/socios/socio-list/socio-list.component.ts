import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Socio } from '../socio.model';
import { SocioService } from '../socio.service';

@Component({
  selector: 'app-socio-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './socio-list.component.html',
  styleUrl: './socio-list.component.css'
})
export class SocioListComponent implements OnInit {

  socios: Socio[] = [];
  cargando = false;
  errorMsg = '';

  constructor(
    private socioService: SocioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarSocios();
  }

  cargarSocios(): void {
    this.cargando = true;
    this.socioService.listar().subscribe({
      next: (data) => {
        this.socios = data;
        this.cargando = false;
      },
      error: () => {
        this.errorMsg = 'No se pudo cargar la lista de socios';
        this.cargando = false;
      }
    });
  }

  nuevo(): void {
    this.router.navigate(['/socios/nuevo']);
  }

  editar(id?: number): void {
    if (id) this.router.navigate(['/socios/editar', id]);
  }

  eliminar(socio: Socio): void {
    if (!socio.id) return;
    const confirmar = confirm(`¿Eliminar al socio ${socio.nombres} ${socio.apellidos}?`);
    if (!confirmar) return;

    this.socioService.eliminar(socio.id).subscribe({
      next: () => this.cargarSocios(),
      error: () => alert('No se pudo eliminar el socio')
    });
  }
}
