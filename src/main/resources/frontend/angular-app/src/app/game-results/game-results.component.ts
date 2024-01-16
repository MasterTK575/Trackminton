import { Component, OnInit } from '@angular/core';
import { GameService } from '../gameService';
import { Game } from '../game';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-game-results',
  templateUrl: './game-results.component.html',
  styleUrls: ['./game-results.component.css'],
})
export class GameResultsComponent implements OnInit {
  games: Game[] = [];

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    // Lade alle Spiele beim Initialisieren der Komponente
    this.loadGames();
  }

  loadGames(): void {
    this.gameService.getGames().subscribe({
      next: (response: Game[]) => {
        console.log('Received game data:', response);
        this.games = response;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching games:', error);
        alert(error.message);
      },
    });
  }
}
