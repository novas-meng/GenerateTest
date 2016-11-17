import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by novas on 16-11-16.
 */
public class LogisticRegression {
    static double[][] x;
    static double[][] y;
    public static void readData(String filename)throws IOException
    {
        BufferedReader bufferedReader=new BufferedReader(new FileReader(filename));
        String line=bufferedReader.readLine();
        ArrayList<double[]> list=new ArrayList<>();
        while (line!=null)
        {
            double[] doubles=new double[21];
            String[] var=line.split(",");
            for (int i=0;i<var.length;i++)
            {
                doubles[i]=Double.valueOf(var[i]);
            }
            list.add(doubles);
            line=bufferedReader.readLine();
        }
        bufferedReader.close();
        System.out.println(list.size());
        x=new double[list.size()][21];
        y=new double[list.size()][1];
        for (int i=0;i<list.size();i++)
        {
            double[] data=list.get(i);
            System.arraycopy(data,0,x[i],0,20);
            x[i][20]=1;
            System.arraycopy(data,20,y[i],0,1);
        }
    }
    public static double getY(double[] w,double[] x)
    {
        double y=0;
        for (int i=0;i<w.length;i++)
        {
            y=y+w[i]*x[i];
        }
        return y;
    }
    public static void norm()
    {
        double max=x[0][0];
        double min=x[0][0];
        for (int i=0;i<x.length;i++)
        {
            for (int j=0;j<x[i].length;j++)
            {
                if(x[i][j]>max)
                {
                    max=x[i][j];
                }
                if(x[i][j]<min)
                {
                    min=x[i][j];
                }
            }
/*
            if(y[i][0]>max)
            {
                max=y[i][0];
            }
            if(y[i][0]<min)
            {
                min=y[i][0];
            }
*/
        }
        double m=Math.log10(max);
        int n=(int)Math.floor(m);

        System.out.println("n="+n);
        for (int i=0;i<x.length;i++)
        {
            for (int j=0;j<x[i].length;j++)
            {
                x[i][j]=(x[i][j])/ Math.pow(10,n);
            }
            y[i][0]=(y[i][0])/Math.pow(10,n);
        }
    }
    public static void fit()
    {
        System.out.println("fit");
        double[] w=new double[21];
        int count=0;
        while (count<1)
        {
            System.out.println(count);
            for (int i=0;i<x.length;i++)
            {
                double y_pre=getY(w,x[i]);
               // System.out.println(y_pre+"  "+y[i][0]);
                for (int j=0;j<w.length;j++)
                {
                    w[j]=w[j]-0.001*(y_pre-y[i][0])*x[i][j];
                }
            }
            count++;
        }

        for (int i=0;i<w.length;i++)
        {
            System.out.println(w[i]);
        }
    }
    public static void main(String[] args)throws IOException
    {
        readData("LogisticRegression.data");
        norm();
        fit();
    }

}
