import { RouterModule, Routes } from '@angular/router';
import { PlayfieldComponent } from './playfield/playfield.component';
import { NgModule } from '@angular/core';
import { AppComponent } from './AppComponent';
import { HomeComponent } from './home/home.component';
import { StartGameComponent } from './start-game/start-game.component';
import { PlayerComponent } from './player/player.component';
import { FinishScreenComponent } from './finish-screen/finish-screen.component';
import { GameResultsComponent } from './game-results/game-results.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'app', component: AppComponent },
  { path: 'home', component: HomeComponent },
  { path: 'player', component: PlayerComponent },
  { path: 'startGame', component: StartGameComponent },
  { path: 'playfield', component: PlayfieldComponent },
  { path: 'finish-screen', component: FinishScreenComponent },
  { path: 'game-results', component: GameResultsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
