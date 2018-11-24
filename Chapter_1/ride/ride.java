/*
ID: 10002181
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
  public static void main (String [] args) throws IOException {
    BufferedReader bf = new BufferedReader(new FileReader("ride.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    StringTokenizer comet = new StringTokenizer(bf.readLine());
    StringTokenizer group = new StringTokenizer(bf.readLine());
    String cometName = comet.nextToken();
    String groupName = group.nextToken();
    int cometProduct=1;
    for (Character c : cometName.toCharArray()) {
        cometProduct*=(int)c-(int)'A'+1;
    }
    int groupProduct=1;
    for (Character g : groupName.toCharArray()) {
        groupProduct*=(int)g-(int)'A'+1;
    }
    cometProduct%=47;
    groupProduct%=47;
    if (cometProduct == groupProduct) {
        out.println("GO");
    } else {
        out.println("STAY");
    }   
    out.close();
    bf.close();
  }
}