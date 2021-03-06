package acme.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class SpamHelper {
	
	public boolean spamChecker(final String text, final String strongSpamW, final String weakSpamW, final Double strongThreshold,final Double weakThreshold) {	
		
		final String[] words = text.split("\\s+");
		final List<String> wordsListText = this.getListGivenArray(words);
		final List<String> wordsList = wordsListText.stream().map(String::toLowerCase).collect(Collectors.toList());
		
		final String[] strongSpamWords = strongSpamW.split(",");
		final List<String> strongSpamWordsList = this.getListGivenArray(strongSpamWords); // solo terms que sean palabras únicas
		
		final List<List<String>> multipleStrongSpamWordsLists = new ArrayList<>();
		for(int i = 0; i<strongSpamWordsList.size(); i++) {
			final String aux = strongSpamWordsList.get(i);
			
			if(aux.trim().contains(" ")) {	// contiene más de una 
				multipleStrongSpamWordsLists.add(this.getListGivenArray(aux.trim().split(" ")));
				strongSpamWordsList.remove(aux);
			}
		}
	
		
	
		
;		final String[] weakSpamWords = weakSpamW.split(",");
		final List<String> weakSpamWordsList = this.getListGivenArray(weakSpamWords);  // solo terms que sean palabras únicas
		
		final List<List<String>> multipleWeakSpamWordsLists = new ArrayList<>();
		for(int i = 0; i<weakSpamWordsList.size(); i++) {
			final String aux = weakSpamWordsList.get(i);

			if(aux.trim().contains(" ")) {	// contiene más de una 
				multipleWeakSpamWordsLists.add(this.getListGivenArray(aux.trim().split(" ")));
				weakSpamWordsList.remove(aux);
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
		

		final Pair<Boolean, Boolean> res = this.isSpam(text, strongThreshold,weakThreshold, weakCont, strongCont);
		
		return !res.getFirst() && !res.getSecond();
	}
	
	public List<String> getListGivenArray(final String[] array) {
		final List<String> res = new ArrayList<>();
		for(final String s : array) {
			res.add(s.trim());
		}
		
		return res;
	}
	
	public Pair<Boolean, Boolean> isSpam(final String origin, final double strongThreshold,final double weakThreshold, final Integer weakCont, final Integer strongCont) {
		final Integer size = origin.split(" ").length;
		final double weakRatio = (double) weakCont/size;
		final double strongRatio = (double) strongCont/size;
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
