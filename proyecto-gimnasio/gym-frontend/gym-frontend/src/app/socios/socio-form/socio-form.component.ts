import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { SocioService } from '../socio.service';

@Component({
  selector: 'app-socio-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './socio-form.component.html',
  styleUrl: './socio-form.component.css'
})
export class SocioFormComponent implements OnInit {

  form: FormGroup;
  esEdicion = false;
  socioId?: number;
  errorMsg = '';
  guardando = false;

  planes = ['MENSUAL', 'TRIMESTRAL', 'SEMESTRAL', 'ANUAL'];

  constructor(
    private fb: FormBuilder,
    private socioService: SocioService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      nombres: ['', Validators.required],
      apellidos: ['', Validators.required],
      dni: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      email: ['', Validators.email],
      telefono: [''],
      fechaNacimiento: [''],
      plan: ['MENSUAL', Validators.required],
      activo: [true]
    });
  }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.esEdicion = true;
      this.socioId = Number(idParam);
      this.cargarSocio(this.socioId);
    }
  }

  cargarSocio(id: number): void {
    this.socioService.obtenerPorId(id).subscribe({
      next: (socio) => {
        this.form.patchValue({
          ...socio,
          fechaNacimiento: socio.fechaNacimiento ? socio.fechaNacimiento.substring(0, 10) : ''
        });
      },
      error: () => this.errorMsg = 'No se pudo cargar el socio'
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.guardando = true;
    this.errorMsg = '';
    const datos = this.form.value;

    const peticion = this.esEdicion && this.socioId
      ? this.socioService.actualizar(this.socioId, datos)
      : this.socioService.crear(datos);

    peticion.subscribe({
      next: () => this.router.navigate(['/socios']),
      error: (err) => {
        this.guardando = false;
        this.errorMsg = err?.error?.mensaje || 'Ocurrió un error al guardar el socio';
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/socios']);
  }
}
