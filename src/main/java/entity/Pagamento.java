package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Pagamento {
    private Long idPagamento;
    private LocalDate dataPagamento;
    private double valor;
    private String status;
    private Long idCliente;
    private Long idSeguradora;
    private Long idAutorizada;
    private String tipoPagamento;



}