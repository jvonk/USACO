/*
ID: 10002181
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
  public static void main(String[] args) throws IOException {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
    Map<String, Integer> people = new LinkedHashMap<>();
    ArrayList<String> els = new ArrayList<String>();

    String line = null;
    while ((line = br.readLine()) != null) {
      StringTokenizer st = new StringTokenizer(line);
      while (st.hasMoreElements()) {
        els.add(st.nextToken());
      }
    }
    int np = Integer.parseInt(els.get(0));
    for (int ii = 1; ii <= np + 1; ii++) {
      people.put(els.get(ii), 0);
    }
    int offset = np;
    while (offset < els.size() - 1) {
      String name = els.get(offset + 1);
      int amount = Integer.parseInt(els.get(offset + 2));
      int total = Integer.parseInt(els.get(offset + 3));
      if (total > 0 && amount != 0) {
        int am = Math.floorDiv(amount, total);
        for (int ii = offset + 4; ii < total + offset + 4; ii++) {
          people.computeIfPresent(els.get(ii), (k, v) -> v + am);
        }
        people.computeIfPresent(name, (k, v) -> v - am * total);
      }
      offset = total + offset + 3;
    }
    for (Map.Entry<String, Integer> person : people.entrySet()) {
      out.println(person.getKey() + " " + person.getValue());
    }
    br.close();
    out.close();
  }
}
