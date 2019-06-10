package me.flail.oldmcc.lang.Throwables;

public class InvalidPlayerException extends Exception {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1748326167159129335L;

	public InvalidPlayerException() {
		super("Invalid Player! Make sure they have joined the server at least once while MicroCommands is active and running.");
	}

}
