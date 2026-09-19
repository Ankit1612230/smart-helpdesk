import { useEffect, useState } from "react";
import { getAllTickets, deleteTicket } from "../api/ticketApi";

const STATUS_STYLES = {
    OPEN: { bg: "#fff4e5", text: "#b45309" },
    IN_PROGRESS: { bg: "#eaf2ff", text: "#1d4ed8" },
    RESOLVED: { bg: "#e9f9ee", text: "#15803d" },
    CLOSED: { bg: "#eef0f2", text: "#4b5563" },
};

const CATEGORY_STYLES = {
    BILLING: { bg: "#f4f0ff", text: "#6d28d9" },
    TECHNICAL: { bg: "#eaf2ff", text: "#1d4ed8" },
    ACCOUNT: { bg: "#e6f7f5", text: "#0f6b5c" },
    GENERAL: { bg: "#eef0f2", text: "#4b5563" },
    BUG_REPORT: { bg: "#fdeeee", text: "#b42318" },
};

const SENTIMENT_STYLES = {
    POSITIVE: { bg: "#e9f9ee", text: "#15803d" },
    NEUTRAL: { bg: "#eef0f2", text: "#4b5563" },
    NEGATIVE: { bg: "#fdeeee", text: "#b42318" },
};

function Pill({ label, style }) {
    if (!label) return null;
    return (
        <span
            style={{
                display: "inline-block",
                fontSize: 12,
                fontWeight: 500,
                padding: "3px 9px",
                borderRadius: 20,
                background: style?.bg || "#eef0f2",
                color: style?.text || "#4b5563",
                whiteSpace: "nowrap",
            }}
        >
      {label.replaceAll("_", " ").toLowerCase()}
    </span>
    );
}

function TicketRow({ ticket, onDelete }) {
    const statusStyle = STATUS_STYLES[ticket.status];

    return (
        <div
            style={{
                background: "var(--surface)",
                border: "1px solid var(--border)",
                borderLeft: `3px solid ${statusStyle?.text || "var(--border)"}`,
                borderRadius: 6,
                padding: "14px 16px",
                marginBottom: 10,
            }}
        >
            <div style={{ display: "flex", justifyContent: "space-between", gap: 12 }}>
                <div style={{ minWidth: 0 }}>
                    <div style={{ display: "flex", alignItems: "center", gap: 8, flexWrap: "wrap" }}>
                        <h3 style={{ margin: 0, fontSize: 15, fontWeight: 600 }}>{ticket.title}</h3>
                        <Pill label={ticket.status} style={statusStyle} />
                        <Pill label={ticket.category || "Analyzing"} style={CATEGORY_STYLES[ticket.category]} />
                        {ticket.sentiment && <Pill label={ticket.sentiment} style={SENTIMENT_STYLES[ticket.sentiment]} />}
                    </div>
                    <p style={{ margin: "6px 0 0", fontSize: 13.5, color: "var(--text-muted)" }}>
                        {ticket.description}
                    </p>
                    <p style={{ margin: "8px 0 0", fontSize: 12, color: "var(--text-muted)" }}>
                        {ticket.createdBy} · {new Date(ticket.createdAt).toLocaleString()}
                    </p>
                </div>
                <button
                    onClick={() => onDelete(ticket.id)}
                    style={{
                        border: "none",
                        background: "none",
                        color: "var(--text-muted)",
                        fontSize: 13,
                        padding: 4,
                        height: "fit-content",
                    }}
                >
                    Delete
                </button>
            </div>

            {ticket.suggestedReply && (
                <div
                    style={{
                        marginTop: 10,
                        padding: "10px 12px",
                        background: "#f5f6f8",
                        border: "1px solid var(--border)",
                        borderRadius: 6,
                        fontSize: 13,
                    }}
                >
                    <span style={{ fontWeight: 600, color: "var(--text)" }}>Suggested reply — </span>
                    <span style={{ color: "var(--text-muted)" }}>{ticket.suggestedReply}</span>
                </div>
            )}
        </div>
    );
}

function TicketList() {
    const [tickets, setTickets] = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchTickets = async () => {
        const response = await getAllTickets();
        setTickets(response.data.slice().reverse());
        setLoading(false);
    };

    useEffect(() => {
        fetchTickets();
        const interval = setInterval(fetchTickets, 5000);
        return () => clearInterval(interval);
    }, []);

    const handleDelete = async (id) => {
        await deleteTicket(id);
        fetchTickets();
    };

    if (loading) {
        return <p style={{ color: "var(--text-muted)", fontSize: 14 }}>Loading tickets…</p>;
    }

    if (tickets.length === 0) {
        return (
            <div
                style={{
                    border: "1px dashed var(--border)",
                    borderRadius: 8,
                    padding: 32,
                    textAlign: "center",
                    color: "var(--text-muted)",
                    fontSize: 14,
                }}
            >
                No tickets yet — submit one to get started.
            </div>
        );
    }

    return (
        <div>
            {tickets.map((ticket) => (
                <TicketRow key={ticket.id} ticket={ticket} onDelete={handleDelete} />
            ))}
        </div>
    );
}

export default TicketList;