/*
ID: 100021881
LANG: JAVA
PROG: backforth
*/

// if you want to turn all the debug on, just find and replace "// DEBUG " with nothing

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
/*
 * backforth
 */
public class backforth {
    public static void main(String[] args) throws Exception {
        // set startTime to measure how long the program takes
        long startTime = System.currentTimeMillis();

        // create input BufferedReader from file
        BufferedReader br = new BufferedReader(new FileReader("backforth.in"));
        
        Deque<Barn[]> barn = new LinkedList<Barn[]>();
        barn.add(new Barn[] {
            new Barn(1000, Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList())), 
            new Barn(1000, Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList())) //https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java
        });

        br.close();
        Deque<Barn[]> newbarn = new LinkedList<Barn[]>();
        
        while(!barn.isEmpty()) {
            Barn[] b = barn.remove();
            for (int i = 0; i < b[0].buckets.size(); i++) {
                List<Integer> buck = new ArrayList<Integer>();
                buck.addAll(b[0].buckets);
                buck.remove(i);
                List<Integer> buck2 = new ArrayList<Integer>();
                buck2.addAll(b[1].buckets);
                buck2.add(b[0].buckets.get(i));
                newbarn.add(new Barn[] {
                    new Barn(b[0].tank-b[0].buckets.get(i), buck),
                    new Barn(b[1].tank+b[0].buckets.get(i), buck2)
                });
            }
        }
        barn.addAll(newbarn);
        newbarn = new LinkedList<Barn[]>();
        while(!barn.isEmpty()) {
            Barn[] b = barn.remove();
            for (int i = 0; i < b[1].buckets.size(); i++) {
                List<Integer> buck = new ArrayList<Integer>();
                buck.addAll(b[1].buckets);
                buck.remove(i);
                List<Integer> buck2 = new ArrayList<Integer>();
                buck2.addAll(b[0].buckets);
                buck2.add(b[1].buckets.get(i));
                newbarn.add(new Barn[] {
                    new Barn(b[0].tank+b[1].buckets.get(i), buck2),
                    new Barn(b[1].tank-b[1].buckets.get(i), buck)
                });
            }
        }
        barn.addAll(newbarn);
        newbarn = new LinkedList<Barn[]>();
        
        while(!barn.isEmpty()) {
            Barn[] b = barn.remove();
            for (int i = 0; i < b[0].buckets.size(); i++) {
                List<Integer> buck = new ArrayList<Integer>();
                buck.addAll(b[0].buckets);
                buck.remove(i);
                List<Integer> buck2 = new ArrayList<Integer>();
                buck2.addAll(b[1].buckets);
                buck2.add(b[0].buckets.get(i));
                newbarn.add(new Barn[] {
                    new Barn(b[0].tank-b[0].buckets.get(i), buck),
                    new Barn(b[1].tank+b[0].buckets.get(i), buck2)
                });
            }
        }
        barn.addAll(newbarn);
        newbarn = new LinkedList<Barn[]>();
        while(!barn.isEmpty()) {
            Barn[] b = barn.remove();
            for (int i = 0; i < b[1].buckets.size(); i++) {
                List<Integer> buck = new ArrayList<Integer>();
                buck.addAll(b[1].buckets);
                buck.remove(i);
                List<Integer> buck2 = new ArrayList<Integer>();
                buck2.addAll(b[0].buckets);
                buck2.add(b[1].buckets.get(i));
                newbarn.add(new Barn[] {
                    new Barn(b[0].tank+b[1].buckets.get(i), buck2),
                    new Barn(b[1].tank-b[1].buckets.get(i), buck)
                });
            }
        }
        barn.addAll(newbarn);
        Set<Integer> caps = new HashSet<Integer>();
        while(!barn.isEmpty())
            caps.add((barn.remove()[0]).tank);

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
        out.println(caps.size());
        out.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }
    public static class Barn {
        int tank;
        List<Integer> buckets;
        public Barn (int t, List<Integer> buck) {
            tank=t;
            buckets=buck;
        }
    }
}