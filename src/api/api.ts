import { Card, Deck, User } from "../types";

// same-origin path; next.config.ts forwards it to the backend
const BASE_URL = "/api";

// returns the logged-in user, or null if nobody is logged in
export const getMe = async (): Promise<User | null> => {
  const res = await fetch(`${BASE_URL}/auth/me`);
  if (!res.ok) return null;
  return res.json();
};

// logs in (or registers, then logs in); throws with the server's message on failure
const sendCredentials = async (path: string, username: string, password: string): Promise<User> => {
  const res = await fetch(`${BASE_URL}/auth/${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
};

export const login = (username: string, password: string) => sendCredentials("login", username, password);

export const register = (username: string, password: string) => sendCredentials("register", username, password);

export const logout = async (): Promise<void> => {
  await fetch(`${BASE_URL}/auth/logout`, { method: "POST" });
};

export const getDecks = async (): Promise<Deck[]> => {
  const res = await fetch(`${BASE_URL}/decks`);
  return res.json();
};

export const createDeck = async (name: string): Promise<Deck> => {
  const res = await fetch(`${BASE_URL}/decks`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name }),
  });
  return res.json();
};

export const deleteDeck = async (id: string): Promise<void> => {
  await fetch(`${BASE_URL}/decks/${id}`, { method: "DELETE" });
};

export const getCards = async (deckId: string): Promise<Card[]> => {
  const res = await fetch(`${BASE_URL}/decks/${deckId}/cards`);
  return res.json();
};

export const addCard = async (deckId: string, card: Omit<Card, "id">): Promise<Card> => {
  const res = await fetch(`${BASE_URL}/decks/${deckId}/cards`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(card),
  });
  return res.json();
};

export const deleteCard = async (deckId: string, cardId: string): Promise<void> => {
  await fetch(`${BASE_URL}/decks/${deckId}/cards/${cardId}`, {
    method: "DELETE",
  });
};

export const drawCard = async (deckId: string, outdoor: boolean | null = null): Promise<Card | null> => {
  const url = outdoor !== null
    ? `${BASE_URL}/decks/${deckId}/draw?outdoor=${outdoor}`
    : `${BASE_URL}/decks/${deckId}/draw`;
  const res = await fetch(url);
  if (res.status === 404) return null;
  return res.json();
};