import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CursosComponent } from './components/cursos/cursos.component';
import { DescuentosComponent } from './components/descuentos/descuentos.component';
import { ModalidadesComponent } from './components/modalidades/modalidades.component';

const routes: Routes = [
  {path: '', component: CursosComponent},
  {path: 'curso', component: CursosComponent},
  {path: 'modalidad', component: ModalidadesComponent},
  {path: 'descuento', component: DescuentosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
