export interface Card {
  id: string;
  activity: string;
  description: string;
  outdoor: boolean;
}

export interface Deck {
  id: string;
  name: string;
  cards: Card[];
}