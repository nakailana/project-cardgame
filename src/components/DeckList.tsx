"use client";
import { useState } from "react";
import { Deck } from "../types";

interface Props {
  decks: Deck[];
  onSelect: (deck: Deck) => void;
  onCreate: (name: string) => void;
  onDelete: (id: string) => void;
}

export default function DeckList({ decks, onSelect, onCreate, onDelete }: Props) {
  const [newDeckName, setNewDeckName] = useState("");

  const handleCreate = () => {
    if (newDeckName.trim() === "") return;
    onCreate(newDeckName.trim());
    setNewDeckName("");
  };

  return (
    <div className="max-w-2xl mx-auto mt-16 px-4">
      <h1 className="text-5xl font-bold mb-2 text-[#2a2d4a]">CARDS AGAINST BOREDOM</h1>
      <p className="text-[#7a77c8] mb-10 text-lg">Pick a deck or create a new one</p>

      <div className="flex gap-3 mb-10">
        <input
          type="text"
          placeholder="New deck name..."
          value={newDeckName}
          onChange={(e) => setNewDeckName(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && handleCreate()}
          className="flex-1 rounded-xl px-4 py-3 bg-white text-[#2a2d4a] placeholder-[#7a77c8] outline-none focus:ring-2 focus:ring-[#7a77c8] shadow-sm"
        />
        <button
          onClick={handleCreate}
          className="bg-[#7a77c8] text-[#e9e6ff] font-bold px-6 py-3 rounded-xl hover:bg-[#b6b3f2] hover:text-[#2a2d4a] transition-all">
          + Create
        </button>
      </div>

      {decks.length === 0 ? (
        <p className="text-[#7a77c8] text-center mt-20">No decks yet — create one above!</p>
      ) : (
        <div className="flex flex-col gap-4">
          {decks.map((deck) => (
            <div key={deck.id}
              onClick={() => onSelect(deck)}
              className="flex items-center justify-between bg-[#2a2d4a] border border-[#7a77c8] rounded-2xl px-6 py-4 hover:bg-[#7a77c8] hover:border-[#b6b3f2] transition-all cursor-pointer group shadow-md">
              <div>
                <h2 className="text-xl font-semibold text-[#e9e6ff]">{deck.name}</h2>
                <p className="text-[#b6b3f2] text-sm group-hover:text-[#e9e6ff] transition-colors">{deck.cards.length} cards</p>
              </div>
              <button
                onClick={(e) => { e.stopPropagation(); onDelete(deck.id); }}
                className="text-[#7a77c8] hover:text-[#D9789E] transition-colors text-xl px-2">
                ✕
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}