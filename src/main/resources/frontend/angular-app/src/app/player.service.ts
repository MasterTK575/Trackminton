import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Player } from './player';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  public getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.apiServerUrl}/api/players`);
  }

  public addPlayer(player: Player): Observable<Player> {
    return this.http
      .post<Player>(`${this.apiServerUrl}/api/players`, player)
      .pipe(
        catchError((error) => {
          console.error('Error adding player:', error);
          throw error;
        })
      );
  }
}
