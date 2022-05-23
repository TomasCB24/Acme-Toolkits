package acme.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.features.any.item.AnyItemRepository;

@Service
public class SpamHelper {
	
	@Autowired
	protected AnyItemRepository anyItemRepository;
	
	public boolean spamChecker(final String text) {	
		
		final String[] words = text.split(" ");
		final List<String> wordsListText = this.getListGivenArray(words);
		final List<String> wordsList = wordsListText.stream().map(String::toLowerCase).collect(Collectors.toList());
		
		final SystemConfiguration sc = this.anyItemRepository.findSystemConfiguration();
		
		final String[] strongSpamWords = sc.getStrongSpamWords().split(",");
		final List<String> strongSpamWordsList = this.getListGivenArray(strongSpamWords); // solo terms que sean palabras únicas
		
		final List<List<String>> multipleStrongSpamWordsLists = new ArrayList<>();
		for(int i = 0; i<strongSpamWordsList.size(); i++) {
			String aux = strongSpamWordsList.get(i);
			aux = aux.trim();
			if(aux.contains(" ")) {	// contiene más de una 
				multipleStrongSpamWordsLists.add(this.getListGivenArray(aux.split(" ")));
				strongSpamWordsList.remove(i);
			}
		}
	
		
	
		
;		final String[] weakSpamWords = sc.getWeakSpamWords().split(",");
		final List<String> weakSpamWordsList = this.getListGivenArray(weakSpamWords);  // solo terms que sean palabras únicas
		
		final List<List<String>> multipleWeakSpamWordsLists = new ArrayList<>();
		for(int i = 0; i<weakSpamWordsList.size(); i++) {
			String aux = weakSpamWordsList.get(i);
			aux = aux.trim();
			if(aux.contains(" ")) {	// contiene más de una 
				multipleWeakSpamWordsLists.add(this.getListGivenArray(aux.split(" ")));
				weakSpamWordsList.remove(i);
			}
		}

		Integer weakCont = 0;
		Integer strongCont = 0;
		for(int i = 0; i<wordsList.size(); i++) {
			
			final String word = wordsList.get(i);  // posición i del texto del campo
			
			if(strongSpamWordsList.contains(word)) {
				strongCont++;
			}  else if(weakSpamWordsList.contains(word)) {
				weakCont++;
			} else {	// here i have to check wether it is in multipleStrongSpamWordsList or multipleWeakSpamWordsList
				
				// primero recorremos la lista con un for, para obtener la lista hija (términos multiples, por ej: [hard, core])
				
				for(final List<String> aux : multipleStrongSpamWordsLists) { // [son, of, a, bitch]
					
					final List<String> copy = this.checkMultiple(aux, i, wordsList);

					if(copy.isEmpty()) {
						strongCont+= aux.size();
					}
	
				}
							
				for(final List<String> aux : multipleWeakSpamWordsLists) { 
	
					final List<String> copy = this.checkMultiple(aux, i, wordsList);
						
					if(copy.isEmpty()) {
						weakCont+= aux.size();
					}
		
				}
					
			}
			
		}
		

		final Pair<Boolean, Boolean> res = this.isSpam(text, sc, weakCont, strongCont);
		
		return !res.getFirst() && !res.getSecond();
	}
	
	public List<String> getListGivenArray(final String[] array) {
		final List<String> res = new ArrayList<>();
		for(final String s : array) {
			res.add(s.trim());
		}
		
		return res;
	}
	
	public Pair<Boolean, Boolean> isSpam(final String origin, final SystemConfiguration sc, final Integer weakCont, final Integer strongCont) {
		final Integer size = origin.split(" ").length;
		final double weakRatio = (double) weakCont/size;
		final double strongRatio = (double) strongCont/size;
		final double weakThreshold = sc.getWeakSpamThreshold();
		final double strongThreshold = sc.getStrongSpamThreshold();
		Boolean isWeakSpam = false;
		Boolean isStrongSpam = false;
		if(weakRatio > weakThreshold) isWeakSpam = true;
		if(strongRatio > strongThreshold) isStrongSpam = true;
		
		
		return Pair.of(isWeakSpam, isStrongSpam);
	}
	
	
	
	public List<String> checkMultiple(final List<String> aux, final int i, final List<String> wordsList) {
		final List<String> copy = new ArrayList<>(aux);
		
		int a = i;
		while(!copy.isEmpty() && a-i < aux.size()) {
			final String s = copy.get(0).toLowerCase().trim();
			if(a < wordsList.size()) {
				if(s.equals(wordsList.get(a).trim().toLowerCase())) copy.remove(0);
			} else {
				break;
			}
			
			
			a++;
			
		}
		
		return copy;
	}
	
	
	
	 
	 
	
	
	

}
