// finish-screen.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-finish-screen',
  templateUrl: './finish-screen.component.html',
  styleUrls: ['./finish-screen.component.css'],
})
export class FinishScreenComponent implements OnInit {
  winner: string = '';
  team1Sets: number = 0;
  team2Sets: number = 0;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Subscribe to route query parameters
    this.route.queryParams.subscribe((params) => {
      this.winner = params['winner'] || '';
      this.team1Sets = +params['team1Sets'] || 0;
      this.team2Sets = +params['team2Sets'] || 0;
    });
  }
}
