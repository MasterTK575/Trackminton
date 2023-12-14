import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './AppComponent';
import { PlayerService } from './player.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PlayfieldComponent } from './playfield/playfield.component';
import { AppRoutingModule } from './app.routes';

@NgModule({
  declarations: [AppComponent, PlayfieldComponent],
  imports: [BrowserModule, HttpClientModule, FormsModule, AppRoutingModule],
  providers: [PlayerService],
  bootstrap: [AppComponent],
})
export class AppModule {}
