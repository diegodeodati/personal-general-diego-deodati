package serviseatl.connection;

public class DAOException extends RuntimeException
{
	static final long serialVersionUID = -1881205326001000001L;

	public DAOException(String message)
	{
		super(message);
	}

	public DAOException(Throwable cause)
	{
		super(cause);
	}

	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
