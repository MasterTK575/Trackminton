import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-start-game',
  templateUrl: './start-game.component.html',
  styleUrls: ['./start-game.component.css'],
})
export class StartGameComponent implements OnInit {
  team1Player1: string = '';
  team1Player2: string = '';
  team2Player1: string = '';
  team2Player2: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Initialization logic if needed
  }

  startGame(startingTeam: string): void {
    // Validation logic
    if (
      this.team1Player1 &&
      this.team1Player2 &&
      this.team2Player1 &&
      this.team2Player2
    ) {
      // Redirect to playfield component with necessary data
      this.router.navigate(['/playfield'], {
        state: {
          team1Player1: this.team1Player1,
          team1Player2: this.team1Player2,
          team2Player1: this.team2Player1,
          team2Player2: this.team2Player2,
          startingTeam: startingTeam,
        },
      });
      console.log('Team 1 Player 1:', this.team1Player1);
    }
  }
}
