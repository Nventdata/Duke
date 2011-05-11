
package no.priv.garshol.duke;

import java.util.Collection;

public class PrintMatchListener extends AbstractMatchListener {
  private int count;
  private boolean showmaybe;
  private boolean showmatches;
  private boolean progress;
  
  public PrintMatchListener(boolean showmatches, boolean showmaybe,
                            boolean progress) {
    this.count = 0;
    this.showmatches = showmatches;
    this.showmaybe = showmaybe;
    this.progress = progress;
  }
  
  public int getMatchCount() {
    return count;
  }
  
  public void matches(Record r1, Record r2, double confidence) {
    count++;
    if (showmatches)
      show(r1, r2, confidence, "\nMATCH");
    if (count % 1000 == 0 && progress)
      System.out.println("" + count + "  matches");
  }

  public void matchesPerhaps(Record r1, Record r2, double confidence) {
    if (showmaybe)
      show(r1, r2, confidence, "\nMAYBE MATCH");
  }
  
  public static void show(Record r1, Record r2, double confidence,
                          String heading) {
    System.out.println(heading + " " + confidence);      
    System.out.println(toString(r1));
    System.out.println(toString(r2));
  }
  
  public static String toString(Record r) {
    StringBuffer buf = new StringBuffer();
    for (String p : r.getProperties()) {
      Collection<String> vs = r.getValues(p);
      if (vs == null)
        continue;
      
      buf.append(p + ": ");          
      for (String v : vs)
        buf.append("'" + v + "', ");
    }
    return buf.toString();
  }
}