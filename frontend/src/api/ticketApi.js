import axios from "axios";

const API_BASE = "http://localhost:8080/ticket-service/api/tickets";

export const getAllTickets = () => axios.get(API_BASE);

export const getTicketById = (id) => axios.get(`${API_BASE}/${id}`);

export const createTicket = (ticket) => axios.post(API_BASE, ticket);

export const updateTicket = (id, ticket) => axios.put(`${API_BASE}/${id}`, ticket);

export const deleteTicket = (id) => axios.delete(`${API_BASE}/${id}`);