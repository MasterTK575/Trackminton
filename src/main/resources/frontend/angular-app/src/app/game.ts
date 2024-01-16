import { Team } from './team';
import { GameSet } from './gameset';

interface Game {
  id: number;
  teams: Team[];
  gameSets: GameSet[];
  winner: Team; // Assuming winner can be null if the game is not won yet
}

export { Game };
