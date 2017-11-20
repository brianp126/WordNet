/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.princeton.cs.algs4.*;
import java.util.*;
/**
 *
 * @author brianpifer
 */
public class WordNet {
   private HashMap<Integer,String> hash;
   private HashSet<String> hashNouns;
   private Digraph g;
   private In synsInput;
   private In hyperInput;
   

   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
       
       String[] splitLine;
       this.hash = new HashMap();
       this.hashNouns = new HashSet();
       
       if (synsets == null || hypernyms == null) {
           throw new java.lang.IllegalArgumentException("One or more inputs is null");
       }
       try {
           synsInput = new In(synsets);
           
           while (!synsInput.isEmpty()) {
               splitLine = synsInput.readLine().split(",");
               hash.put(Integer.parseInt(splitLine[0]), splitLine[1]);

           }
           
           g = new Digraph(hash.size());
           
           hyperInput = new In(hypernyms);
           
           while (!hyperInput.isEmpty()) {
               splitLine = hyperInput.readLine().split(",");
               for (int i = 1; i < splitLine.length; i++ ) {
                   g.addEdge(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[i]));
               }
               buildHashSet(splitLine[1], hashNouns);
           }
           
       }
       catch(IllegalArgumentException e) {
           throw new IllegalArgumentException(e);
       }
       
       if (!isRootedDAG(g)) {
           throw new java.lang.IllegalArgumentException("This is not a rooted DAG");
       }
   }
   
   //check if rooted DAG
   private boolean isRootedDAG(Digraph G) {
       int count = 0;
       for (int i = 0; i < G.V(); i++) {
           if (G.indegree(i) == 0) {
               count++;
           }
       }
       return count == 1;
   }
   
   //build noun set
   private void buildHashSet(String inputLine, HashSet hs) {
       String[] separatedInputLine = inputLine.split(" ");
       for (String word : separatedInputLine) {
            if (!hs.contains(word)) {
                hs.add(word);
            }
       }
   }

   // returns all WordNet nouns
   public Iterable<String> nouns() {
       return hash.values();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
       return hash.containsValue(word);
   }

   // distance between nounA and nounB (defined below) 
   public int distance(String nounA, String nounB) {
       
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
       
   }

   // do unit testing of this class
   public static void main(String[] args) {
       
   }
}
