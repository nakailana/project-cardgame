"use client"
import { useState, useEffect } from "react";
import { getDecks, createDeck, deleteDeck, getMe, logout } from "../api/api";
import { Deck, User } from "../types";
import AuthForm from "../components/AuthForm";
import DeckList from "../components/DeckList";
import DeckView from "../components/DeckView";

export default function App() {
  // undefined = still checking, null = logged out
  const [user, setUser] = useState<User | null | undefined>(undefined);
  const [decks, setDecks] = useState<Deck[]>([]);
  const [activeDeck, setActiveDeck] = useState<Deck | null>(null);

  // on first load, ask the backend whether we already have a logged-in session
  useEffect(() => {
    getMe().then(setUser);
  }, []);

  // load decks whenever someone logs in
  useEffect(() => {
    if (user) getDecks().then(setDecks);
  }, [user]);

  const handleLogout = async () => {
    await logout();
    setUser(null);
    setDecks([]);
    setActiveDeck(null);
  };

  const handleCreateDeck = async (name: string) => {
    const newDeck = await createDeck(name);
    setDecks([...decks, newDeck]);
  };

  const handleDeleteDeck = async (id: string) => {
    await deleteDeck(id);
    setDecks(decks.filter((d) => d.id !== id));
    if (activeDeck?.id === id) setActiveDeck(null);
  };

  const handleUpdateDeck = (updated: Deck) => {
    setDecks(decks.map((d) => (d.id === updated.id ? updated : d)));
    setActiveDeck(updated);
  };

  if (user === undefined) return null;

  if (user === null) {
    return (
      <div className="min-h-screen font-sans p-6">
        <AuthForm onLogin={setUser} />
      </div>
    );
  }

  return (
    <div className="min-h-screen font-sans p-6">
      <div className="flex justify-end items-center gap-4 text-[#7a77c8]">
        <span>Logged in as <span className="font-semibold text-[#2a2d4a]">{user.username}</span></span>
        <button onClick={handleLogout} className="font-semibold hover:text-[#2a2d4a] transition-colors">
          Log out
        </button>
      </div>
      {activeDeck ? (
        <DeckView deck={activeDeck} onUpdate={handleUpdateDeck} onBack={() => setActiveDeck(null)} />
      ) : (
        <DeckList decks={decks} onSelect={setActiveDeck} onCreate={handleCreateDeck} onDelete={handleDeleteDeck} />
      )}
    </div>
);
}
