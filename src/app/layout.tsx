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
      <body className="bg-[#c7d6ff] text-[#e9e6ff]">{children}</body>
    </html>
  );
}