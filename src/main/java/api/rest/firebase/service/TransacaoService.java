package api.rest.firebase.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import api.rest.firebase.model.Transacao;
import api.rest.firebase.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;
	private static final String COLLECTION_NAME = "transacoes";

	public Transacao salvarTransacao(Transacao transacao) throws InterruptedException, ExecutionException {

		transacaoRepository.save(transacao);
		Firestore dbFirestore = FirestoreClient.getFirestore();

		dbFirestore.collection(COLLECTION_NAME).document(String.valueOf(transacao.getId())).set(transacao);

		return transacao;
	}

	public List<Transacao> listarTransacoes() {
		return transacaoRepository.findAll();
	}

	public Transacao listarTransacaoPorId(Long id) throws InterruptedException, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();

		DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(String.valueOf(id));

		ApiFuture<DocumentSnapshot> future = documentReference.get();

		DocumentSnapshot document = future.get();

		Transacao transacao = null;

		if (document.exists()) {
			transacao = document.toObject(Transacao.class);
			return transacao;
		} else {
			return null;
		}

	}

	public Transacao atualizarTransacao(Transacao transacao) throws InterruptedException, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();

		dbFirestore.collection(COLLECTION_NAME).document(String.valueOf(transacao.getId())).set(transacao);

		return transacao;
	}
	
	public String deletarTransacao(Long id) throws InterruptedException, ExecutionException {

		Firestore dbFirestore = FirestoreClient.getFirestore();

		dbFirestore.collection(COLLECTION_NAME).document(String.valueOf(id)).delete();

		return "A transaçao cujo ID é " + id + " foi deletada com sucesso.";
	}

}
