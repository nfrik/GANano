import java.util.Random;

/**
 * Created by NF on 9/3/2016.
 */
public class PlotTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello");
        Random rand = new Random();
        for(int i=0;i<2000000;i++){
            PltSeries.getInstance().plotYt(Math.random());
            PltXYScatter.getInstance().plotXY(rand.nextGaussian(),rand.nextGaussian());
            Thread.sleep(10);
        }

    }
}
