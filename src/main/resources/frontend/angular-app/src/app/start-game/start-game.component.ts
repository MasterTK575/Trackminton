import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataSharingService } from '../services/dataSharingService';
import { PlayerService } from '../player.service';

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

  constructor(
    private router: Router,
    private dataSharingService: DataSharingService,
    private playerService: PlayerService
  ) {}

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
      const user1 = this.extractUserData(this.team1Player1);
      const user2 = this.extractUserData(this.team1Player2);
      const user3 = this.extractUserData(this.team2Player1);
      const user4 = this.extractUserData(this.team2Player2);

      // Use PlayerService to add players to the backend
      this.playerService.addPlayer(user1).subscribe();
      this.playerService.addPlayer(user2).subscribe();
      this.playerService.addPlayer(user3).subscribe();
      this.playerService.addPlayer(user4).subscribe();

      const teamData = {
        team1Player1: this.team1Player1,
        team1Player2: this.team1Player2,
        team2Player1: this.team2Player1,
        team2Player2: this.team2Player2,
        startingTeam: startingTeam,
      };

      // Update the team data using the service
      this.dataSharingService.updateTeamData(teamData);

      // Redirect to playfield component
      this.router.navigate(['/playfield']);
    }
  }
  extractUserData(fullName: string): {
    firstName: string;
    lastName: string;
    userName: string;
  } {
    const spaceIndex = fullName.indexOf(' ');
    let firstName, lastName, userName;

    if (spaceIndex !== -1) {
      firstName = fullName.substring(0, spaceIndex);
      lastName = fullName.substring(spaceIndex + 1);
    } else {
      firstName = fullName;
      lastName = '';
    }

    userName = firstName.charAt(0) + lastName.charAt(0);

    return { firstName, lastName, userName };
  }
}
