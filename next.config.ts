import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  // forward /api/* to the Spring backend, so the browser sees one site
  // (and sends the login cookie along without any CORS setup)
  // BACKEND_URL is set on the hosting platform; locally it defaults to the dev backend
  async rewrites() {
    const backend = process.env.BACKEND_URL ?? "http://localhost:8080";
    return [{ source: "/api/:path*", destination: `${backend}/api/:path*` }];
  },
};

export default nextConfig;
