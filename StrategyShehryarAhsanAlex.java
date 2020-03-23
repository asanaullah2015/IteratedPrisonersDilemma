
import java.io.*;
import java.util.*;
import java.text.*;
public class StrategyShehryarAhsanAlex extends Strategy {
        public StrategyShehryarAhsanAlex() {
            name = "Shehryar, Ahsan, Alex";
        }

        public int nextMove() {
            if (Math.random() < 0.5) {
                return 1;
            }
            else {
                return 0;
            }
        }
}
