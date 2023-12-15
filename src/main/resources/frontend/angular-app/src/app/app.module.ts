import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './AppComponent';
import { PlayerService } from './player.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PlayfieldComponent } from './playfield/playfield.component';
import { AppRoutingModule } from './app.routes';
import { HomeComponent } from './home/home.component';
import { StartGameComponent } from './start-game/start-game.component';
import { PlayerComponent } from './player/player.component';

@NgModule({
  declarations: [AppComponent, PlayfieldComponent, HomeComponent, StartGameComponent, PlayerComponent],
  imports: [BrowserModule, HttpClientModule, FormsModule, AppRoutingModule],
  providers: [PlayerService],
  bootstrap: [AppComponent],
})
export class AppModule {}
