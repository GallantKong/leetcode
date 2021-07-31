package org.gallant.leetcode.algorithms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author : 会灰翔的灰机
 * @date : 2021/7/31
 */
public class LeastFrequentlyUsedCache {

  private final Map<Integer, CountableData> DATA = new HashMap<>();
  private final Map<Integer, List<CountableData>> COUNTING_SORT = new HashMap<>();

  public void put(Integer key, String value) {
    CountableData countableData = DATA.get(key);
    if (countableData == null) {
      countableData = new CountableData();
      countableData.setCount(0);
      DATA.put(key, countableData);
      initCountingSort(countableData.getCount());
      COUNTING_SORT.get(countableData.getCount()).add(countableData);
    } else {
      int oldCount = countableData.getCount();
      int newCount = oldCount + 1;
      countableData.setCount(newCount);
      initCountingSort(oldCount, newCount);
      COUNTING_SORT.get(oldCount).remove(countableData);
      COUNTING_SORT.get(newCount).add(countableData);
    }
    countableData.setKey(key);
    countableData.setData(value);
  }

  public CountableData get(Integer key) {
    int oldCount = 0;
    int newCount = 1;
    CountableData countableData = DATA.get(key);
    if (countableData == null) {
      return null;
    } else {
      oldCount = countableData.getCount();
      newCount = oldCount + 1;
      countableData.setCount(newCount);
    }
    initCountingSort(oldCount, newCount);
    COUNTING_SORT.get(oldCount).remove(countableData);
    COUNTING_SORT.get(newCount).add(countableData);
    return countableData;
  }

  private void initCountingSort(Integer... counts) {
    Arrays.stream(counts).forEach(count -> COUNTING_SORT.computeIfAbsent(count, k -> new ArrayList<>()));
  }

  public void printData() {
    System.out.println(DATA);
    System.out.println(COUNTING_SORT);
    System.out.println("-----------");
  }

  public void expel() {
    for (Entry<Integer, List<CountableData>> integerListEntry : COUNTING_SORT.entrySet()) {
      Integer k = integerListEntry.getKey();
      List<CountableData> v = integerListEntry.getValue();
      if (v != null && v.size() > 0) {
        v.forEach(countableData -> {
          DATA.remove(countableData.getKey());
          COUNTING_SORT.remove(countableData.getCount());
        });
        break;
      }
    }
  }

  private static class CountableData implements Serializable {

    private static final long serialVersionUID = -1449571685883654388L;
    private Integer key;
    private String data;
    private Integer count;

    public Integer getKey() {
      return key;
    }

    public void setKey(Integer key) {
      this.key = key;
    }

    public Object getData() {
      return data;
    }

    public void setData(String data) {
      this.data = data;
    }

    public Integer getCount() {
      return count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }

    @Override
    public String toString() {
      return "{" + data +
          ", " + count +
          '}';
    }
  }

  public static void main(String[] args) {
    LeastFrequentlyUsedCache cache = new LeastFrequentlyUsedCache();
    for (int i = 0; i < 10; i++) {
      cache.put(i, "data-" + i);
    }
    cache.printData();
    for (int i = 0; i < 3; i++) {
      cache.get(0);
    }
    for (int i = 0; i < 5; i++) {
      cache.get(3);
    }
    cache.printData();
    cache.expel();
    System.out.println("驱逐数据后");
    cache.printData();
  }

}
