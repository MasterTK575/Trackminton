import { Component, OnInit, ɵisStandalone } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataSharingService } from '../services/dataSharingService';
import { GameService } from '../gameService';

@Component({
  selector: 'app-playfield',
  templateUrl: './playfield.component.html',
  styleUrls: ['./playfield.component.css'],
})
export class PlayfieldComponent implements OnInit {
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private dataSharingService: DataSharingService,
    private gameService: GameService
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
        this.currentServingPlayer = this.startingTeam === 'Team 1' ? 2 : 3;
        this.lastServingPlayer = this.currentServingPlayer;
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
  setScores: { scoreTeam1: number; scoreTeam2: number }[] = [];
  mirrorLayout = false;
  mirrorteam1 = false;
  lastMirrorteam1 = false;
  lastMirrorteam2 = false;
  mirrorteam2 = false;
  thirdSetChange = false;
  currentServingPlayer = 2;
  lastServingPlayer = 2;

  updateScores(): void {
    if (!this.isGameFinished) {
      if (
        this.team1Sets === 1 &&
        this.team2Sets === 1 &&
        ((this.team1Score === 11 && !this.thirdSetChange) ||
          (this.team2Score === 11 && !this.thirdSetChange))
      ) {
        this.switchSides();
        this.showBreakPopup();
        this.thirdSetChange = true;
      }
      if (this.team1Score >= 21 || this.team2Score >= 21) {
        if (
          Math.abs(this.team1Score - this.team2Score) >= 2 ||
          this.team1Score === 30 ||
          this.team2Score === 30
        ) {
          if (this.team1Score > this.team2Score) {
            this.team1Sets += 1;
          } else {
            this.team2Sets += 1;
          }
          this.switchSides();
          // Save the set scores
          this.setScores.push({
            scoreTeam1: this.team1Score,
            scoreTeam2: this.team2Score,
          });
          // Reset the scores
          this.resetScores();

          if (this.team1Sets === 2 || this.team2Sets === 2) {
            console.log('Navigating to finish screen');
            this.isGameFinished = true;
            // Update the set scores using the service
            this.dataSharingService.updateSetScores(this.setScores);

            //generate game object and send to backend
            this.sendGameToBackend();

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

  private sendGameToBackend() {
    console.log('Sending game results to the backend');

    // Erstellt ein Game-Objekt mit den relevanten Daten
    const gameData = {
      teams: [
        {
          name:
            this.generateUsername(this.team1Player1) +
            ' & ' +
            this.generateUsername(this.team1Player2),
          teamMembers: [
            {
              firstName: this.generateFirstname(this.team1Player1),
              lastName: this.generateLastname(this.team1Player1),
              userName: this.generateUsername(this.team1Player1),
            },
            {
              firstName: this.generateFirstname(this.team1Player2),
              lastName: this.generateLastname(this.team1Player2),
              userName: this.generateUsername(this.team1Player2),
            },
          ],
        },
        {
          name:
            this.generateUsername(this.team2Player1) +
            ' & ' +
            this.generateUsername(this.team2Player2),
          teamMembers: [
            {
              firstName: this.generateFirstname(this.team2Player1),
              lastName: this.generateLastname(this.team2Player1),
              userName: this.generateUsername(this.team2Player1),
            },
            {
              firstName: this.generateFirstname(this.team2Player2),
              lastName: this.generateLastname(this.team2Player2),
              userName: this.generateUsername(this.team2Player2),
            },
          ],
        },
      ],
      gameSets: this.setScores.map((set) => {
        return {
          scoreTeam1: set.scoreTeam1,
          scoreTeam2: set.scoreTeam2,
        };
      }),
      winner:
        this.team1Sets === 2
          ? {
              name:
                this.generateUsername(this.team1Player1) +
                ' & ' +
                this.generateUsername(this.team1Player2),
              teamMembers: [
                {
                  firstName: this.generateFirstname(this.team1Player1),
                  lastName: this.generateLastname(this.team1Player1),
                  userName: this.generateUsername(this.team1Player1),
                },
                {
                  firstName: this.generateFirstname(this.team1Player2),
                  lastName: this.generateLastname(this.team1Player2),
                  userName: this.generateUsername(this.team1Player2),
                },
              ],
            }
          : {
              name:
                this.generateUsername(this.team2Player1) +
                ' & ' +
                this.generateUsername(this.team2Player2),
              teamMembers: [
                {
                  firstName: this.generateFirstname(this.team2Player1),
                  lastName: this.generateLastname(this.team2Player1),
                  userName: this.generateUsername(this.team2Player1),
                },
                {
                  firstName: this.generateFirstname(this.team2Player2),
                  lastName: this.generateLastname(this.team2Player2),
                  userName: this.generateUsername(this.team2Player2),
                },
              ],
            },
    };

    this.gameService.submitGameResult(gameData).subscribe(
      (response) => {
        console.log('Game results successfully sent to the backend:', response);
      },
      (error) => {
        console.error('Error sending game results to the backend:', error);
      }
    );
  }

  switchSides(): void {
    this.mirrorLayout = !this.mirrorLayout;
  }

  switchPositionsTeam1(): void {
    this.mirrorteam1 = !this.mirrorteam1;
  }
  switchPositionsTeam2(): void {
    this.mirrorteam2 = !this.mirrorteam2;
  }

  resetScores(): void {
    this.team1Score = 0;
    this.team2Score = 0;
    this.mirrorteam1 = false;
    this.mirrorteam2 = false;
  }

  team1ButtonClick(): void {
    if (!this.isBreak) {
      this.lastServe = this.currentServe;
      this.lastTeam1Score = this.team1Score;
      this.team1Score += 1;
      this.lastInput = 1;
      this.lastServingPlayer = this.currentServingPlayer;
      this.lastMirrorteam1 = this.mirrorteam1;

      if ((this.team1Score + this.team2Score) % 2 === 0) {
        //spieler nicht getauscht und ball zurückgewonnen
        if (this.mirrorteam1 == false && this.currentServe == false) {
          this.currentServingPlayer = 2;
        }
        //spieler getauscht und ball zurückgewonnen
        else if (this.mirrorteam1 == true && this.currentServe == false) {
          this.currentServingPlayer = 1;
        }
        //spieler nicht getauscht und weiterhin ballbesitz
        else if (this.mirrorteam1 == false && this.currentServe == true) {
          this.mirrorteam1 = !this.mirrorteam1;
        }
        //spieler sind getauscht und weiterhin ballbesitz
        else if (this.mirrorteam1 == true && this.currentServe == true) {
          this.mirrorteam1 = !this.mirrorteam1;
        }
      }
      if ((this.team1Score + this.team2Score) % 2 != 0) {
        //spieler nicht getauscht und ball zurückgewonnen
        if (this.mirrorteam1 == false && this.currentServe == false) {
          this.currentServingPlayer = 1;
        }
        //spieler getauscht und ball zurückgewonnen
        else if (this.mirrorteam1 == true && this.currentServe == false) {
          this.currentServingPlayer = 2;
        }
        //spieler nicht getauscht und weiterhin ballbesitz
        else if (this.mirrorteam1 == false && this.currentServe == true) {
          this.mirrorteam1 = !this.mirrorteam1;
        }
        //spieler sind getauscht und weiterhin ballbesitz
        else if (this.mirrorteam1 == true && this.currentServe == true) {
          this.mirrorteam1 = !this.mirrorteam1;
        }
      }
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
      this.lastServingPlayer = this.currentServingPlayer;
      this.lastMirrorteam2 = this.mirrorteam2;

      if ((this.team1Score + this.team2Score) % 2 === 0) {
        //spieler nicht getauscht und ball zurückgewonnen
        if (this.mirrorteam2 == false && this.currentServe == true) {
          this.currentServingPlayer = 3;
        }
        //spieler getauscht und ball zurückgewonnen
        else if (this.mirrorteam2 == true && this.currentServe == true) {
          this.currentServingPlayer = 4;
        }
        //spieler nicht getauscht und weiterhin ballbesitz
        else if (this.mirrorteam2 == false && this.currentServe == false) {
          this.mirrorteam2 = !this.mirrorteam2;
          this.currentServingPlayer = 4;
        }
        //spieler sind getauscht und weiterhin ballbesitz
        else if (this.mirrorteam2 == true && this.currentServe == false) {
          this.mirrorteam2 = !this.mirrorteam2;
          this.currentServingPlayer = 3;
        }
      }
      if ((this.team1Score + this.team2Score) % 2 != 0) {
        //spieler nicht getauscht und ball zurückgewonnen
        if (this.mirrorteam2 == false && this.currentServe == true) {
          this.currentServingPlayer = 4;
        }
        //spieler getauscht und ball zurückgewonnen
        else if (this.mirrorteam2 == true && this.currentServe == true) {
          this.currentServingPlayer = 3;
        }
        //spieler nicht getauscht und weiterhin ballbesitz
        else if (this.mirrorteam2 == false && this.currentServe == false) {
          this.mirrorteam2 = !this.mirrorteam2;
        }
        //spieler sind getauscht und weiterhin ballbesitz
        else if (this.mirrorteam2 == true && this.currentServe == false) {
          this.mirrorteam2 = !this.mirrorteam2;
        }
      }
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
    this.currentServingPlayer = this.lastServingPlayer;
    this.currentServe = this.lastServe;
    this.mirrorteam1 = this.lastMirrorteam1;
    this.mirrorteam2 = this.lastMirrorteam2;
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

  generateUsername(name: string): string {
    const nameParts = name.split(' ');

    if (nameParts.length === 1) {
      return nameParts[0][0].toUpperCase();
    } else {
      return nameParts[0][0].toUpperCase() + nameParts[1][0].toUpperCase();
    }
  }

  generateFirstname(name: string): string {
    const nameParts = name.split(' ');
    return nameParts[0];
  }

  generateLastname(name: string): string {
    const nameParts = name.split(' ');

    if (nameParts.length === 1) {
      return '';
    } else {
      return nameParts[1];
    }
  }
}
