import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.Random;
import java.util.jar.Pack200;

/**
 * Created by novas on 16-11-16.
 */
public class LogisticRegressionTest {
    //随机生成权重系数
    public static double[] generateW(int n)
    {
        double[] w=new double[n];
        Random random=new Random();
        for (int i=0;i<n;i++)
        {
            w[i]=random.nextDouble()*1000;
        }
        return w;
    }
    //随机生成权重系数
    public static double[] generate(int n)
    {
        double[] w=new double[n];
        Random random=new Random();
        for (int i=0;i<n;i++)
        {
            w[i]=random.nextDouble()*100;
        }
        return w;
    }
    public static double getY(double[] x,double[] w)
    {
        double b=56.4;
        double y_res=0;
        for (int i=0;i<x.length;i++)
        {
            y_res=y_res+x[i]*w[i]+b;
        }
        return y_res;
    }
    public static void writeW(double[] w)throws IOException
    {
        FileWriter f=new FileWriter("LogisticRegression.des");
        for (int i=0;i<w.length;i++)
        {
            f.write(w[i]+"\r\n");
        }
        f.close();
    }
    public static void generateTest(int n)throws IOException
    {
        double[] w=generateW(20);
        writeW(w);
        FileWriter filewriter=new FileWriter("LogisticRegression.data");
        for (int i=0;i<n;i++) {
            double[] x=generate(20);
            double y=getY(x,w);
            for (int j=0;j<x.length;j++)
            {
                filewriter.write(x[j]+",");
            }
            filewriter.write(y+"\r\n");
        }
        filewriter.close();
    }
    public static void main(String[] args)throws IOException
    {
        generateTest(10000000);
    }
}
