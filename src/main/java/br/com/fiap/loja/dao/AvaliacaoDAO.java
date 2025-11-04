package br.com.fiap.loja.dao;


import br.com.fiap.dto.avaliacao.DetalhesAvaliacaoDto;
import br.com.fiap.dto.avaliacao.MediaAvaliacaoDto;
import br.com.fiap.exception.EntidadeNaoEncontrada;
import br.com.fiap.loja.model.Avaliacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AvaliacaoDAO {
    private Connection conexao;
    @Inject
    private DataSource dataSource;


    public List<Avaliacao> listar() throws SQLException{
        try(Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("select * from t_tdspw_avaliacao where cd_doce = ?");
            ResultSet rs = stmt.executeQuery();
            List<Avaliacao> lista = new ArrayList<>();
            while (rs.next()){
                Avaliacao avaliacao = parseAvaliacao(rs);
                lista.add(avaliacao);
            }
            return lista;
        }
    }

    public MediaAvaliacaoDto media(int codigoDoce) throws SQLException{
        try(Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("select avg (vl_nota) as media, count(*) as qntd from t_tdspw_avaliacao where cd_doce = ?");
            stmt.setInt(1, codigoDoce);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int quantidadeAvaliacoes = rs.getInt("qntd");
                double mediaNota = rs.getDouble("media");
                return new MediaAvaliacaoDto(codigoDoce, mediaNota, quantidadeAvaliacoes);
            }
            return new MediaAvaliacaoDto(codigoDoce, 0.0,0);
        }
    }

    public void cadastrar(Avaliacao avaliacao) throws SQLException{
        try(Connection conexao = dataSource.getConnection()){
            PreparedStatement stmt = conexao.prepareStatement("insert into t_tdspw_avaliacao (cd_avaliacao," +
                    "ds_avaliacao, vl_nota, dt_cadastro, cd_doce) values (sq_tdspw_avaliacao.nextval, ?,?, sysdate,?)", new String[]{"cd_avaliacao"});
            stmt.setString(1, avaliacao.getDescricao());
            stmt.setDouble(2, avaliacao.getNota());
            stmt.setInt(3, avaliacao.getCodigoDoce());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    avaliacao.setCodigo(idGerado);
                }
            }
        }
    }
    public List<Avaliacao> buscarPorDoce(int codigoDoce) throws SQLException{
        try(Connection conexao = dataSource.getConnection()) {
            PreparedStatement stmt = conexao.prepareStatement("select * from t_tdspw_avaliacao where cd_doce = ?");
            stmt.setInt(1, codigoDoce);
            ResultSet rs = stmt.executeQuery();
            List <Avaliacao> lista = new ArrayList<>();
            while(rs.next()){
                Avaliacao avaliacao = parseAvaliacao(rs);
                lista.add(avaliacao);
            }
            return lista;
        }

    }

    private Avaliacao parseAvaliacao(ResultSet rs) throws SQLException{
        int codigo = rs.getInt("cd_avaliacao");
        String descricao = rs.getString("ds_avaliacao");
        LocalDate dataCadastro = rs.getObject("dt_cadastro", LocalDate.class);
        double nota = rs.getDouble("vl_nota");
        int codigoDoce = rs.getInt("cd_doce");
        return new Avaliacao(codigo, descricao, dataCadastro, nota, codigoDoce);
    }
    
}
