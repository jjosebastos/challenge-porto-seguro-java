package exception;

import entity.Cliente;

public class ClienteDaoException extends Exception {
    public ClienteDaoException(String message){
        super(message);
    }
}
