package br.com.fiap.loja.dao;

import br.com.fiap.loja.model.Doce;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import oracle.jdbc.proxy.annotation.Pre;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DoceDAO {

    @Inject
    private DataSource dataSource;

    public List<Doce> listar() throws SQLException{
        try(Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("select * from t_tdspw_doce");
            ResultSet rs = stmt.executeQuery();
            List<Doce> lista = new ArrayList<>();
            while(rs.next()){
                Doce doce = parseDoce(rs);
                lista.add(doce);
            }
            return lista;

        }
    }

    public void cadastrar(Doce doce) throws SQLException {
        try(Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("insert into t_tdspw_doce (cd_doce," +
                    "nm_doce, vl_doce, vl_peso, dt_validade) values (sq_tdspw_doce.nextval, ?,?,?,?)",
                    new String[]{"cd_doce"});

            stmt.setString(1, doce.getNome());
            stmt.setDouble(2,doce.getValor());
            stmt.setDouble(3,doce.getPeso());
            stmt.setObject(4,doce.getDataValidade());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                doce.setCodigo(rs.getInt(1));
            }

        }
    }
    private Doce parseDoce(ResultSet rs) throws SQLException{
        int codigo = rs.getInt("cd_doce");
        String nome = rs.getString("nm_doce");
        double peso = rs.getDouble("vl_peso");
        double valor = rs.getDouble("vl_doce");
        LocalDate dataValidade = rs.getObject("dt_validade", LocalDate.class);
        return  new Doce(codigo, nome, peso, valor, dataValidade);
    }
}
