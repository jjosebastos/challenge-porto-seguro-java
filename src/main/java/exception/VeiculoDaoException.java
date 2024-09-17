package exception;

import dao.VeiculoDao;

public class VeiculoDaoException extends Exception {
    public VeiculoDaoException(String message){
        super(message);
    }
}
