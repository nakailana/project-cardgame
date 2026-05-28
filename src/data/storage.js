const STORAGE_KEY = "cards-against-boredom";

export const loadDecks = () => {
  const data = localStorage.getItem(STORAGE_KEY);
  return data ? JSON.parse(data) : [];
};

export const saveDecks = (decks) => {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(decks));
};