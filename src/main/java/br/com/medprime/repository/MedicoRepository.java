package br.com.medprime.repository;

import br.com.medprime.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico,Long> {

    @Query("select m from Medico m where m.ativo = true")
    Page<Medico> findAllByAtivo(Pageable pageable);

    @Query("select m from Medico m where m.id = :id and m.ativo = true")
    Optional<Medico> findByIdAndAtivo(Long id);

}
