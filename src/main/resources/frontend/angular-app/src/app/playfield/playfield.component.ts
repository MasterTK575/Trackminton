import { Component, OnInit, ɵisStandalone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataSharingService } from '../services/dataSharingService';

@Component({
  selector: 'app-playfield',
  templateUrl: './playfield.component.html',
  styleUrls: ['./playfield.component.css'],
})
export class PlayfieldComponent implements OnInit {
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private dataSharingService: DataSharingService
  ) {}

  team1Player1: string = '';
  team1Player2: string = '';
  team2Player1: string = '';
  team2Player2: string = '';
  startingTeam: string = '';

  ngOnInit(): void {
    // Subscribe to changes in the team data
    this.dataSharingService.teamData$.subscribe((teamData) => {
      if (teamData) {
        console.log('Team data received in playfield component');
        this.team1Player1 = teamData.team1Player1;
        this.team1Player2 = teamData.team1Player2;
        this.team2Player1 = teamData.team2Player1;
        this.team2Player2 = teamData.team2Player2;
        this.startingTeam = teamData.startingTeam;

        // Update currentServe when data arrives
        this.currentServe = this.startingTeam === 'Team 1' ? true : false;
        this.lastServe = this.currentServe;
      }
    });
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
  currentServe = true;
  lastServe = true;

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

  team1ButtonClick(): void {
    if (!this.isBreak) {
      this.lastServe = this.currentServe;
      this.lastTeam1Score = this.team1Score;
      this.team1Score += 1;
      this.lastInput = 1;
      this.currentServe = true;
      this.updateScores();
    }
  }

  team2ButtonClick(): void {
    if (!this.isBreak) {
      this.lastServe = this.currentServe;
      this.lastTeam2Score = this.team2Score;
      this.team2Score += 1;
      this.lastInput = 2;
      this.currentServe = false;
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
    this.currentServe = this.lastServe;
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
