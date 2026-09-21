import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "Cards Against Boredom",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className="bg-[#c7d6ff] text-[#2a2d4a] overflow-x-hidden">
        {/* Decorative scattered letters, mimicking the CAH box art.
            Fixed = stays put even if the page scrolls.
            pointer-events-none = clicks pass through to real content.
            -z-10 = sits behind everything else on the page. */}
        <div className="fixed inset-0 overflow-hidden pointer-events-none -z-10">
        
          {/* Top-right cluster */}
          <span className="absolute -top-10 -right-6 text-[10rem] font-black text-[#2a2d4a]/10 rotate-12 select-none">C</span>
          <span className="absolute top-20 right-28 text-[7rem] font-black text-[#7a77c8]/20 -rotate-6 select-none">A</span>
          <span className="absolute top-44 right-4 text-[6rem] font-black text-[#2a2d4a]/10 rotate-45 select-none">B</span>
          <span className="absolute -top-4 right-52 text-[5rem] font-black text-[#7a77c8]/15 rotate-90 select-none">R</span>
          <span className="absolute top-64 right-40 text-[4.5rem] font-black text-[#2a2d4a]/10 -rotate-12 select-none">E</span>

          {/* Bottom-left cluster */}
          <span className="absolute -bottom-10 -left-6 text-[10rem] font-black text-[#2a2d4a]/10 -rotate-12 select-none">D</span>
          <span className="absolute bottom-25 left-25 text-[7rem] font-black text-[#7a77c8]/20 rotate-6 select-none">S</span>
          <span className="absolute bottom-4 left-42 text-[6rem] font-black text-[#2a2d4a]/10 -rotate-45 select-none">A</span>
          <span className="absolute bottom-52 left-4 text-[5rem] font-black text-[#7a77c8]/15 rotate-12 select-none">M</span>
          <span className="absolute -bottom-2 left-69 text-[4.5rem] font-black text-[#2a2d4a]/10 rotate-90 select-none">T</span>
          </div>

        <main className="relative z-10">{children}</main>
      </body>
    </html>
  );
}