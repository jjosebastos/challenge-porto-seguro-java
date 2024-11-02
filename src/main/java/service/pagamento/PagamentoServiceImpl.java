package service.pagamento;

import config.DatabaseConnectionFactory;
import dao.pagamento.PagamentoDao;
import dao.pagamento.PagamentoDaoFactory;
import entity.Pagamento;
import exception.PagamentoNotFoundException;
import exception.PagamentoNotSavedException;
import exception.UnsupportedServiceOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PagamentoServiceImpl implements PagamentoService {

    private final PagamentoDao dao = PagamentoDaoFactory.create();

    @Override
    public Pagamento create(Pagamento pagamento) throws SQLException, PagamentoNotSavedException, UnsupportedServiceOperationException {
        if(pagamento.getIdPagamento() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try{
                pagamento = this.dao.save(pagamento,connection);
                connection.commit();
                return pagamento;
            } catch (SQLException | PagamentoNotSavedException e){
                connection.rollback();
                throw e;
            }
        } else  {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Pagamento> findAll() {
        return this.dao.readAll();
    }

    @Override
    public Pagamento update(Pagamento pagamento) throws SQLException, PagamentoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        pagamento = this.dao.update(pagamento,connection);
        connection.commit();
        return pagamento;
    }

    @Override
    public void deleteById(long id) throws SQLException, PagamentoNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
