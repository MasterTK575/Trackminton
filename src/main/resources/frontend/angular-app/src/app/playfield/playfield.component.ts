import { Component, OnInit, ɵisStandalone } from '@angular/core';

@Component({
  selector: 'app-playfield',
  templateUrl: './playfield.component.html',
  styleUrls: ['./playfield.component.css'],
})
export class PlayfieldComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

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

  updateScores(): void {
    if (this.team1Score >= 21 || this.team2Score >= 21) {
      if (Math.abs(this.team1Score - this.team2Score) >= 2) {
        // Determine the set winner
        if (this.team1Score > this.team2Score) {
          // Team 1 wins the set
          this.team1Sets += 1;
        } else {
          // Team 2 wins the set
          this.team2Sets += 1;
        }

        // Reset scores for the next set
        this.resetScores();

        // Check if the game should continue or if there's a break
        this.showBreakPopup();
      }
    }
  }

  resetScores(): void {
    this.team1Score = 0;
    this.team2Score = 0;
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
