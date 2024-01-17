import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Game } from './game';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  submitGameResult(gameResult: Game): Observable<any> {
    const apiUrl = `${this.apiServerUrl}/api/games`;
    return this.http.post(apiUrl, gameResult);
  }

  getGames(): Observable<Game[]> {
    const url = `${this.apiServerUrl}/api/games`;
    return this.http.get<Game[]>(url);
  }
}
