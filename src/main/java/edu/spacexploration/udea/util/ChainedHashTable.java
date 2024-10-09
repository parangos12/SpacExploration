package edu.spacexploration.udea.util;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ChainedHashTable {

  private List<Function<Integer, Integer>> hashFunctions;
  private int size;

    public ChainedHashTable(int size) {
        this.size = size;
    }

  public List<Integer> add(Integer key) {
    getHashFunctions();
    // Apply each hash function to the key to get a list of hash values
    List<Integer> hashValues = new ArrayList<>();
    for (Function<Integer, Integer> hashFunction : hashFunctions) {
      hashValues.add(hashFunction.apply(key) % size);
    }
    return hashValues;
  }

  private void getHashFunctions(){
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
  }


}
