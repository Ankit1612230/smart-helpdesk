import { useState } from "react";
import TicketList from "./components/TicketList";
import TicketForm from "./components/TicketForm";

function App() {
    const [refreshKey, setRefreshKey] = useState(0);

    return (
        <div style={{ minHeight: "100vh" }}>
            <header
                style={{
                    borderBottom: "1px solid var(--border)",
                    background: "var(--surface)",
                    padding: "18px 32px",
                }}
            >
                <div style={{ maxWidth: 1100, margin: "0 auto" }}>
                    <h1 style={{ margin: 0, fontSize: 18, fontWeight: 700 }}>Helpdesk</h1>
                    <p style={{ margin: "2px 0 0", fontSize: 13, color: "var(--text-muted)" }}>
                        Tickets are automatically triaged by AI on submission
                    </p>
                </div>
            </header>

            <div
                style={{
                    maxWidth: 1100,
                    margin: "0 auto",
                    padding: "32px",
                    display: "grid",
                    gridTemplateColumns: "320px 1fr",
                    gap: 32,
                    alignItems: "start",
                }}
            >
                <div style={{ position: "sticky", top: 32 }}>
                    <TicketForm onTicketCreated={() => setRefreshKey(refreshKey + 1)} />
                </div>
                <TicketList key={refreshKey} />
            </div>
        </div>
    );
}

export default App;