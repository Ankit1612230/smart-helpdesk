import { useState } from "react";
import { createTicket } from "../api/ticketApi";

const inputStyle = {
    width: "100%",
    padding: "9px 11px",
    fontSize: 14,
    border: "1px solid var(--border)",
    borderRadius: 6,
    color: "var(--text)",
    background: "var(--surface)",
    outline: "none",
};

const labelStyle = {
    display: "block",
    fontSize: 13,
    fontWeight: 500,
    marginBottom: 6,
    color: "var(--text)",
};

function TicketForm({ onTicketCreated }) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [createdBy, setCreatedBy] = useState("");
    const [submitting, setSubmitting] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSubmitting(true);
        try {
            await createTicket({ title, description, createdBy });
            setTitle("");
            setDescription("");
            setCreatedBy("");
            onTicketCreated();
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <form
            onSubmit={handleSubmit}
            style={{
                background: "var(--surface)",
                border: "1px solid var(--border)",
                borderRadius: 8,
                padding: 20,
                display: "flex",
                flexDirection: "column",
                gap: 14,
            }}
        >
            <h2 style={{ margin: 0, fontSize: 15, fontWeight: 600 }}>New ticket</h2>

            <div>
                <label style={labelStyle}>Title</label>
                <input
                    style={inputStyle}
                    placeholder="Short summary of the issue"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
            </div>

            <div>
                <label style={labelStyle}>Description</label>
                <textarea
                    style={{ ...inputStyle, minHeight: 90, resize: "vertical" }}
                    placeholder="What's going wrong?"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
            </div>

            <div>
                <label style={labelStyle}>Your email</label>
                <input
                    style={inputStyle}
                    type="email"
                    placeholder="you@company.com"
                    value={createdBy}
                    onChange={(e) => setCreatedBy(e.target.value)}
                    required
                />
            </div>

            <button
                type="submit"
                disabled={submitting}
                style={{
                    marginTop: 4,
                    padding: "10px 14px",
                    fontSize: 14,
                    fontWeight: 600,
                    color: "#fff",
                    background: submitting ? "var(--primary-hover)" : "var(--primary)",
                    border: "none",
                    borderRadius: 6,
                }}
            >
                {submitting ? "Submitting…" : "Submit ticket"}
            </button>
        </form>
    );
}

export default TicketForm;