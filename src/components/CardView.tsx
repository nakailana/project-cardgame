"use client";
import { Card } from "../types";
import LocationBadge from "./LocationBadge";

interface Props {
  card: Card | null;
  onClose: () => void;
  onDelete: (card: Card) => void;
}

export default function CardView({ card, onClose, onDelete }: Props) {
  // If there's no selected card, render nothing at all.
  if (!card) return null;

  return (
    <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50 px-4" onClick={onClose}>
      <div className="bg-[#e9e6ff] rounded-2xl p-6 w-full max-w-md shadow-xl relative" onClick={(e) => e.stopPropagation()}>
        <button onClick={onClose} className="absolute top-3 right-4 text-[#7a77c8] hover:text-[#2a2d4a] text-xl font-bold">
          ✕
        </button>

        <div className="flex items-center gap-2 mb-3">
          <h2 className="text-2xl font-semibold text-[#2a2d4a]">{card.activity}</h2>
          <LocationBadge outdoor={card.outdoor} />
        </div>

        <p className="text-[#7a77c8] mb-6">{card.description}</p>

        {/* Kept small and muted in the corner so it doesn't compete with the card */}
        <button
          onClick={() => onDelete(card)}
          className="absolute bottom-3 right-4 text-xs text-[#b6b3f2] hover:text-[#D9789E] transition-colors">
          delete
        </button>
      </div>
    </div>
  );
}