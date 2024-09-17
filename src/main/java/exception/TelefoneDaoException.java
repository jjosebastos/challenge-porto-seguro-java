package exception;

import entity.Telefone;

public class TelefoneDaoException extends Exception {
    public TelefoneDaoException(String message){
        super(message);
    }
}
