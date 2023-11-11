package api.rest.firebase.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.rest.firebase.model.Transacao;
import api.rest.firebase.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;

	@PostMapping
	public Transacao salvarTransacao(@RequestBody Transacao transacao) throws InterruptedException, ExecutionException {
		return transacaoService.salvarTransacao(transacao);
	}

	@GetMapping
	public List<Transacao> listarTransacoes() {
		return transacaoService.listarTransacoes();
	}

	@GetMapping("/{id}")
	public Transacao listarTransacaoPorId(@PathVariable(value = "id") Long id)
			throws InterruptedException, ExecutionException {
		return transacaoService.listarTransacaoPorId(id);
	}

	@PutMapping
	public Transacao atualizarTransacao(@RequestBody Transacao transacao)
			throws InterruptedException, ExecutionException {
		return transacaoService.atualizarTransacao(transacao);
	}

	@DeleteMapping("/{id}")
	public String deletarTransacao(@PathVariable Long id) throws InterruptedException, ExecutionException {
		return transacaoService.deletarTransacao(id);
	}

}
