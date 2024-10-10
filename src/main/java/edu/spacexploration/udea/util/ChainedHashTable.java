package edu.spacexploration.udea.util;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChainedHashTable {

  private List<Function<String, Integer>> hashFunctions;
  private int size;

    public ChainedHashTable(int size) {
        this.size = size;
    }

  public List<Integer> add(Integer key) {
    hashFunctions=getHashFunctions();
    // Apply each hash function to the key to get a list of hash values
    List<Integer> hashValues = new ArrayList<>();
    for (Function<String, Integer> hashFunction : hashFunctions) {
      hashValues.add(hashFunction.apply(key.toString()) % size);
    }
    return hashValues;
  }

  private List<Function<String, Integer>> getHashFunctions(){
    List<Function<String, Integer>> hashFunctions = new ArrayList<>();
    hashFunctions.add(key -> {
      int sum = 0;
      for (char c : key.toCharArray()) {
        sum += (int) c;
      }
      return sum;
    });
    hashFunctions.add(key -> key.length());
    hashFunctions.add(key -> key.isEmpty() ? 0 : (int) key.charAt(0));
    return hashFunctions;
  }


}
