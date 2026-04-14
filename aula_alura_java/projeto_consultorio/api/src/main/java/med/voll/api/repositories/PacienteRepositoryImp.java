package med.voll.api.repositories;

import med.voll.api.entities.Paciente;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PacienteRepositoryImp implements PacienteRepository{
    private final JdbcClient jdbcClient;

    public PacienteRepositoryImp(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }


    @Override
    public Optional<Paciente> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT * FROM pacientes WHERE id = :id")
                .param("id", id)
                .query(Paciente.class)
                .optional();
    }

    @Override
    public List<Paciente> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM paciente LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Paciente.class)
                .list();
    }

    @Override
    public Integer save(Paciente paciente) {
        return this.jdbcClient
                .sql("INSERT INTO pacientes (nome, cpf, telefone, endereco) VALUES (:nome, :cpf, :telefone :endereco)")
                .param("cpf", paciente.getCpf())
                .param("nome", paciente.getNome())
                .param("telefone", paciente.getTelefone())
                .param("cpf", paciente.getCpf())
                .param("endereco", paciente.getEndereco())
                .update();
    }

    @Override
    public Integer update(Paciente paciente, Long id) {
        return this.jdbcClient
                .sql("UPDATE pacientes SET nome = :nome, cpf = :cpf, telefone = :telefone, endereco = :endereco WHERE id = :id")
                .param("cpf", paciente.getCpf())
                .param("nome", paciente.getNome())
                .param("telefone", paciente.getTelefone())
                .param("cpf", paciente.getCpf())
                .param("endereco", paciente.getEndereco())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM pacientes where id = :id")
                .param("id", id)
                .update();
    }
}
