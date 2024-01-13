// data-sharing.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataSharingService {
  private teamDataSubject = new BehaviorSubject<any>(null);
  teamData$ = this.teamDataSubject.asObservable();

  updateTeamData(data: any): void {
    this.teamDataSubject.next(data);
  }
}
