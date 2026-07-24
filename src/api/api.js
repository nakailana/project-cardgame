//Helper file for all fetch calls

const BASE_URL = "http://localhost:8080/api";

export const getDecks = async () => {
  const res = await fetch(`${BASE_URL}/decks`);
  return res.json();
};

export const createDeck = async (name) => {
  const res = await fetch(`${BASE_URL}/decks`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name }),
  });
  return res.json();
};

export const deleteDeck = async (id) => {
    await fetch(`${BASE_URL}/decks/${id}`, { method: "DELETE" });
};

export const getCards = async (deckId) => {
  const res = await fetch(`${BASE_URL}/decks/${deckId}/cards`);
  return res.json();
};

export const addCard = async (deckId, card) => {
  const res = await fetch(`${BASE_URL}/decks/${deckId}/cards`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(card),
  });
  return res.json();
};

export const deleteCard = async (deckId, cardId) => {
  await fetch(`${BASE_URL}/decks/${deckId}/cards/${cardId}`, {
    method: "DELETE",
  });
};

export const drawCard = async (deckId, outdoor = null) => {
  const url = outdoor !== null
    ? `${BASE_URL}/decks/${deckId}/draw?outdoor=${outdoor}`
    : `${BASE_URL}/decks/${deckId}/draw`;
  const res = await fetch(url);
  if (res.status === 404) return null;
  return res.json();
};