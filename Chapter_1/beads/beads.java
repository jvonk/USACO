/*
 ID: 100021881
 LANG: JAVA
 PROG: beads
*/

import java.io.*;

public class beads {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
        int numBeads = Integer.parseInt(br.readLine());
        String inputLine = br.readLine();
        String beads = inputLine+inputLine;
        int max = 0;
        for (int ii = 0; ii < numBeads*2; ii++) {
            int count1 = 0;
            char whitespace = 'w';
            boolean firstOfItsKind = true;
            
            loop1: for (int jj = ii; jj < 2 * numBeads; jj++) {
                if (whitespace == beads.charAt(jj) || beads.charAt(jj) == 'w') {
                    count1++;
                    if (beads.charAt(jj) != 'w') {
                        firstOfItsKind = false;
                        whitespace = beads.charAt(jj);
                    }
                }
                else if (beads.charAt(jj) != 'w') {
                    if (firstOfItsKind) {
                        firstOfItsKind = false;
                        count1++;
                        whitespace = beads.charAt(jj);
                    }
                    else {
                        break loop1;
                    }
                }
            }
            whitespace = 'w';
            firstOfItsKind = true;
            int count2 = 0;
            loop2: for (int jj = ii - 1; jj >= 0; jj--) {
                if (whitespace == beads.charAt(jj) || beads.charAt(jj) == 'w') {
                    count2++;
                    if (beads.charAt(jj) != 'w') {
                        firstOfItsKind = false;
                        whitespace = beads.charAt(jj);
                    }
                }
                else if (beads.charAt(jj) != 'w') {
                    if (firstOfItsKind) {
                        firstOfItsKind = false;
                        count2++;
                        whitespace = beads.charAt(jj);
                    }
                    else {
                        break loop2;
                    }
                }
            }
            if (count1 + count2 > max) {
                max = count1 + count2;
            }
            if (count1 + count2 >= numBeads) {
                max = numBeads;
                break;
            }
        }
        out.println(max);
        out.close();
        br.close();
    }   
}