import { Component, OnInit, ɵisStandalone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-playfield',
  templateUrl: './playfield.component.html',
  styleUrls: ['./playfield.component.css'],
})
export class PlayfieldComponent implements OnInit {
  constructor(private router: Router, private route: ActivatedRoute) {}

  team1Player1: string = '';
  team1Player2: string = '';
  team2Player1: string = '';
  team2Player2: string = '';
  startingTeam: string = '';

  ngOnInit(): void {
    const state = this.findRouteDataState(this.route);
    console.log('Received state in playfield:', state);
    if (state) {
      this.team1Player1 = state.team1Player1;
      this.team1Player2 = state.team1Player2;
      this.team2Player1 = state.team2Player1;
      this.team2Player2 = state.team2Player2;
      this.startingTeam = state.startingTeam;
    }
  }

  private findRouteDataState(route: ActivatedRoute): any {
    let currentRoute = route;
    while (currentRoute) {
      const state = currentRoute.snapshot?.data?.['state'];
      if (state) {
        return state;
      }
      currentRoute = currentRoute.firstChild!;
    }
    return null;
  }

  //
  team1Score = 0;
  team2Score = 0;
  lastTeam1Score = 0;
  lastTeam2Score = 0;
  team1Sets = 0;
  team2Sets = 0;
  breakTime = 5 * 60; // 5 minutes in seconds
  isBreak = false;
  lastInput = 0;
  isGameFinished = false;
  isLeftTeamServe = true;

  //TODO Seitenwechsel nach Satz, bei dritten Satz noch ein Wechsel nach 11 Punkten

  updateScores(): void {
    if (!this.isGameFinished) {
      if (this.team1Score >= 21 || this.team2Score >= 21) {
        if (Math.abs(this.team1Score - this.team2Score) >= 2) {
          if (this.team1Score > this.team2Score) {
            this.team1Sets += 1;
          } else {
            this.team2Sets += 1;
          }

          this.resetScores();

          if (this.team1Sets === 2 || this.team2Sets === 2) {
            console.log('Navigating to finish screen');
            this.isGameFinished = true;
            this.router.navigate(['/finish-screen'], {
              queryParams: {
                winner: this.team1Sets === 2 ? 'Team 1' : 'Team 2',
                team1Sets: this.team1Sets,
                team2Sets: this.team2Sets,
              },
            });
            return;
          } else {
            this.showBreakPopup();
          }
        }
      }
    }
  }

  resetScores(): void {
    this.team1Score = 0;
    this.team2Score = 0;
  }

  toggleServe(): void {
    this.isLeftTeamServe = !this.isLeftTeamServe;
  }

  team1ButtonClick(): void {
    if (!this.isBreak) {
      this.lastTeam1Score = this.team1Score;
      this.team1Score += 1;
      this.lastInput = 1;
      this.updateScores();
    }
  }

  team2ButtonClick(): void {
    if (!this.isBreak) {
      this.lastTeam2Score = this.team2Score;
      this.team2Score += 1;
      this.lastInput = 2;
      this.updateScores();
    }
  }

  resetButtonClick(): void {
    // Reset only the last point
    if (this.lastInput === 1) {
      this.team1Score = this.lastTeam1Score;
    }
    if (this.lastInput === 2) {
      this.team2Score = this.lastTeam2Score;
    }
  }

  showBreakPopup(): void {
    if (!this.isBreak) {
      this.isBreak = window.confirm('Do you want to continue the game?');

      if (this.isBreak) {
        this.startBreakTimer();
      }
    }
  }

  startBreakTimer(): void {
    const intervalId = setInterval(() => {
      this.breakTime--;

      if (this.breakTime <= 0) {
        clearInterval(intervalId);
        this.isBreak = false;
        this.breakTime = 5 * 60; // Reset break time for the next break
      }
    }, 1000);
  }

  cancelBreak(): void {
    this.isBreak = false;
    this.breakTime = 5 * 60; // Reset break time
  }

  formatTime(timeInSeconds: number): string {
    const minutes = Math.floor(timeInSeconds / 60);
    const seconds = timeInSeconds % 60;
    return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  }
}
