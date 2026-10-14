"use client";

interface Props {
  outdoor: boolean;
}

// Smiling sun for outdoor activities.
function SunIcon() {
  return (
    <svg viewBox="0 0 24 24" className="w-4 h-4" aria-hidden="true">
      <g stroke="#F4A93B" strokeWidth="2" strokeLinecap="round">
        <line x1="19" y1="12" x2="21" y2="12" />
        <line x1="5" y1="12" x2="3" y2="12" />
        <line x1="12" y1="19" x2="12" y2="21" />
        <line x1="12" y1="5" x2="12" y2="3" />
        <line x1="16.95" y1="16.95" x2="18.36" y2="18.36" />
        <line x1="7.05" y1="16.95" x2="5.64" y2="18.36" />
        <line x1="7.05" y1="7.05" x2="5.64" y2="5.64" />
        <line x1="16.95" y1="7.05" x2="18.36" y2="5.64" />
      </g>
      <circle cx="12" cy="12" r="5" fill="#FFD36B" />
      <circle cx="10.3" cy="11.3" r="0.7" fill="#2a2d4a" />
      <circle cx="13.7" cy="11.3" r="0.7" fill="#2a2d4a" />
      <path d="M10.4 13.3 q1.6 1.4 3.2 0" stroke="#2a2d4a" strokeWidth="0.9" strokeLinecap="round" fill="none" />
    </svg>
  );
}

// Cozy house with a heart door for indoor activities.
function HouseIcon() {
  return (
    <svg viewBox="0 0 24 24" className="w-4 h-4" aria-hidden="true">
      <path
        d="M4 11 L12 4 L20 11 V19 a1 1 0 0 1 -1 1 H5 a1 1 0 0 1 -1 -1 Z"
        fill="#e9e6ff"
        stroke="#e9e6ff"
        strokeWidth="1.5"
        strokeLinejoin="round"
      />
      <path d="M12 18.2 l-2.6 -2.5 a1.6 1.6 0 0 1 2.6 -1.9 a1.6 1.6 0 0 1 2.6 1.9 Z" fill="#D9789E" />
    </svg>
  );
}

export default function LocationBadge({ outdoor }: Props) {
  return (
    <span className={
      outdoor
        ? "inline-flex items-center gap-1 text-xs font-bold px-2 py-0.5 rounded-full bg-[#c7d6ff] text-[#2a2d4a]"
        : "inline-flex items-center gap-1 text-xs font-bold px-2 py-0.5 rounded-full bg-[#7a77c8] text-[#e9e6ff]"
    }>
      {outdoor ? <SunIcon /> : <HouseIcon />}
      {outdoor ? "Outdoor" : "Indoor"}
    </span>
  );
}
