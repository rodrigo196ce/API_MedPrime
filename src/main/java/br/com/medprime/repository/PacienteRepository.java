package br.com.medprime.repository;

import br.com.medprime.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    @Query("select p from Paciente p where p.ativo = true")
    Page<Paciente> findByIdAndAtivo(Pageable pageable);

}
