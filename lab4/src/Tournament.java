
interface Tournament {

}

class Game implements Tournament {
  Team winner;
  Tournament game1;
  Tournament game2;
}

class Team implements Tournament {
  String name;
}