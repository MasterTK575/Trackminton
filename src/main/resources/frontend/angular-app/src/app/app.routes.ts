import { RouterModule, Routes } from '@angular/router';
import { PlayfieldComponent } from './playfield/playfield.component';
import { NgModule } from '@angular/core';
import { AppComponent } from './AppComponent';

const routes: Routes = [
  { path: '', redirectTo: '/app', pathMatch: 'full' },
  { path: 'app', component: AppComponent },
  { path: 'playfield', component: PlayfieldComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
