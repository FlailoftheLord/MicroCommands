package me.flail.oldmcc.lang.Throwables;

public class BankNotSupportedException extends Throwable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 990880901250791665L;

	public BankNotSupportedException() {
		super("MicroEconomy doesn't support bank accounts yet!");
	}

}
