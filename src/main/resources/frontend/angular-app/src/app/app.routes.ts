import { RouterModule, Routes } from '@angular/router';
import { PlayfieldComponent } from './playfield/playfield.component';
import { NgModule } from '@angular/core';
import { AppComponent } from './AppComponent';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'app', component: AppComponent },
  { path: 'home', component: HomeComponent },
  { path: 'playfield', component: PlayfieldComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
