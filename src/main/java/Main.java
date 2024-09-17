import config.DatabaseConfig;
import dao.*;
import dao.TelefoneDaoImpl;
import entity.*;
import exception.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, PessoaDaoException, EnderecoDaoException, SeguradoraDaoException {

        DatabaseConfig db = new DatabaseConfig("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                "rm557525", "121105");

        ClienteDao clienteDao = new ClienteDaoImpl(db);

        // Teste do método create
        try {
            Cliente novoCliente = new Cliente(11, "João da Silva", LocalDate.of(1985, 5, 15), "12345678901", "Masculino");
            clienteDao.create(novoCliente);
            System.out.println("Cliente criado com sucesso.");
        } catch (ClienteDaoException e) {
            e.printStackTrace();
        }

        // Teste do método readAll
        try {
            List<Cliente> clientes = clienteDao.readAll();
            System.out.println("Clientes encontrados:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } catch (ClienteDaoException e) {
            e.printStackTrace();
        }

        // Teste do método update
        try {
            Cliente clienteAtualizado = new Cliente(11, "João da Silva Atualizado", LocalDate.of(1985, 5, 15), "12345678901", "Masculino");
            clienteDao.update(clienteAtualizado);
            System.out.println("Cliente atualizado com sucesso.");
        } catch (ClienteDaoException e) {
            e.printStackTrace();
        }

        // Teste do método delete
        try {
            clienteDao.delete(1);
            System.out.println("Cliente excluído com sucesso.");
        } catch (ClienteDaoException e) {
            e.printStackTrace();
        }
    }

}