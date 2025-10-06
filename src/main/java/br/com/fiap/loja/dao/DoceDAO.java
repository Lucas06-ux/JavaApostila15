package br.com.fiap.loja.dao;

import br.com.fiap.loja.model.Doce;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ApplicationScoped
public class DoceDAO {

    @Inject
    private DataSource dataSource;

    public void cadastrar(Doce doce) throws SQLException {
        try(Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("insert into t_tdspw_doce (cd_doce," +
                    "nm_doce, vl_doce, vl_peso, dt_validade) values (sq_tdspw_doce.nextval, ?,?,?,?)");
            stmt.setString(1, doce.getNome());
            stmt.setDouble(2,doce.getValor());
            stmt.setDouble(3,doce.getPeso());
            stmt.setObject(4,doce.getDataValidade());

            stmt.executeUpdate();

        }
    }
}
