package com.yohandevmeia.todolist.todolist.models.task;

public enum Priorities {
    BAIXA, MODERADA_BAIXA, MODERADA, MODERADA_ALTA, ALTA;
	
	public static String enumToString(Priorities priority) {
		switch (priority) {
		case BAIXA:
			return "b";
		case MODERADA_BAIXA:
			return "mb";
		case MODERADA:
			return "m";
		case MODERADA_ALTA:
			return "ma";
		case ALTA:
			return "a";
		default:
			return "b";
		}
	}
	
	public static Priorities stringToEnum(String priority) {
		switch (priority) {
		case "b":
			return BAIXA;
		case "mb":
			return MODERADA_ALTA;
		case "m":
			return MODERADA;
		case "ma":
			return MODERADA_ALTA;
		case "a":
			return ALTA;
		default:
			return BAIXA;
		}
	}
}
