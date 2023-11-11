package api.rest.firebase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.rest.firebase.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
	
}
