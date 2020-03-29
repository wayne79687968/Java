package myjava.homework;
import com.opencsv.CSVReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class JFreeChart extends JFrame{
    public static void main(String[] args) throws Exception{
        HashMap<String, Integer> products = new HashMap<>();
        HashMap<String, Integer> sexs = new HashMap<>();
        HashMap<String, pnum> ids = new HashMap<>();
        CSVReader reader = new CSVReader(new FileReader("src\\myjava\\homework\\query_result.csv"));
        String [] nextLine;
        String product;
        String sex;
        String id;
        DecimalFormat df = new DecimalFormat("0.00");
        while ((nextLine = reader.readNext()) != null) {
            //product資料匯入
            product = nextLine[1];
            int pcount = 1;//默認一個單字就是出現一次
            if (products.containsKey(product)) {//判断刚输入的单词是否已经存在
                pcount = products.get(product) + 1;//如果已经存在，新的个数就在已有的个数上加1
            }
            products.put(product, pcount);//插入新的数据

            //sex資料匯入
            sex = nextLine[2];
            int scount = 1;//默認一個單字就是出現一次
            if (sexs.containsKey(sex)) {//判断刚输入的单词是否已经存在
                scount = sexs.get(sex) + 1;//如果已经存在，新的个数就在已有的个数上加1
            }
            sexs.put(sex, scount);//插入新的数据

            //id資料匯入
            pnum pnum = new pnum();
            id = nextLine[0];
            if (ids.containsKey(id)) {
                if (nextLine[1].equals("Product_A"))
                    pnum.A = ids.get(id).A + 1;
                else if (nextLine[1].equals("Product_B"))
                    pnum.B = ids.get(id).B + 1;
                else if (nextLine[1].equals("Product_C"))
                    pnum.C = ids.get(id).C + 1;
            }else {
                if (nextLine[1].equals("Product_A"))
                    pnum.A = 1;
                else if (nextLine[1].equals("Product_B"))
                    pnum.B = 1;
                else if (nextLine[1].equals("Product_C"))
                    pnum.C = 1;
            }
            ids.put(nextLine[0], pnum);//插入新的数据
        }

        while(true) {
            System.out.println("which data of Market you want Graphic ? (1. Products 2. ID_Sex 3. ID purchase history)");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Which kind of Graph ? 1.Bar Chart 2.Pie Chart(3D)");
                    String p_option = scanner.nextLine();
                    switch (p_option){
                        case "1":
                            DefaultCategoryDataset pbdataset = new DefaultCategoryDataset();
                            pbdataset.addValue(products.get("Product_A"), "", "ProductA");
                            pbdataset.addValue(products.get("Product_B"), "", "ProductB");
                            pbdataset.addValue(products.get("Product_C"), "", "ProductC");
                            org.jfree.chart.JFreeChart pbchart = ChartFactory.createBarChart(
                                    "Products sale situation", // 圖的標題
                                    "Product Compare",  // x 座標標題
                                    "Quantity", // y 座標標題
                                    pbdataset, // 你放數據的地方
                                    PlotOrientation.VERTICAL, // 圖表方向：水平、垂直
                                    false,     // 是否顯示圖例
                                    true,     // 是否 tooltips 工具
                                    false     // 是否生成URL
                            );
                            JFrame pbframe = new JFrame("Products sale situation");
                            ChartPanel pbPanel = new ChartPanel(pbchart);
                            pbframe.setSize(1000, 600);
                            pbframe.add(pbPanel);
                            pbframe.setVisible(true);

                            break;
                        case "2":
                            DefaultPieDataset ppdataset = new DefaultPieDataset();
                            ppdataset.setValue("ProductA : " + products.get("Product_A") + "(" + df.format((double)products.get("Product_A")/(products.get("Product_A") + products.get("Product_B") + products.get("Product_C")) * 100) + "%)", products.get("Product_A"));
                            ppdataset.setValue("ProductB : " + products.get("Product_B") + "(" + df.format((double)products.get("Product_B")/(products.get("Product_A") + products.get("Product_B") + products.get("Product_C")) * 100) + "%)", products.get("Product_B"));
                            ppdataset.setValue("ProductC : " + products.get("Product_C") + "(" + df.format((double)products.get("Product_C")/(products.get("Product_A") + products.get("Product_B") + products.get("Product_C")) * 100) + "%)", products.get("Product_C"));
                            org.jfree.chart.JFreeChart ppchart = ChartFactory.createPieChart3D(
                                    "Products sale situation" ,  // chart title
                                    (PieDataset) ppdataset,         // data
                                    true ,            // include legend
                                    true,
                                    false
                            );
                            JFrame ppframe = new JFrame("Products sale situation");
                            ChartPanel ppPanel = new ChartPanel(ppchart);
                            ppframe.setSize(1000, 600);
                            ppframe.add(ppPanel);
                            ppframe.setVisible(true);

                            break;
                        default:
                            System.out.println("Wrong Input.Please try again.");
                            break;
                    }
                    break;
                case "2":
                    System.out.println("Which kind of Graph ? 1.Bar Chart 2.Pie Chart(3D)");
                    String s_option = scanner.nextLine();
                    switch (s_option){
                        case "1":
                            DefaultCategoryDataset sbdataset = new DefaultCategoryDataset();
                            sbdataset.addValue(sexs.get("F"), "", "Female");
                            sbdataset.addValue(sexs.get("M"), "", "Male");
                            org.jfree.chart.JFreeChart sbchart = ChartFactory.createBarChart(
                                    "id_sex", // 圖的標題
                                    "Sex Compare",  // x 座標標題
                                    "Quantity", // y 座標標題
                                    sbdataset, // 你放數據的地方
                                    PlotOrientation.VERTICAL, // 圖表方向：水平、垂直
                                    false,     // 是否顯示圖例
                                    true,     // 是否 tooltips 工具
                                    false     // 是否生成URL
                            );
                            JFrame sbframe = new JFrame("Products sale situation");
                            ChartPanel sbPanel = new ChartPanel(sbchart);
                            sbframe.setSize(1000, 600);
                            sbframe.add(sbPanel);
                            sbframe.setVisible(true);

                            break;
                        case "2":
                            DefaultPieDataset spdataset = new DefaultPieDataset();
                            spdataset.setValue("Male : " + sexs.get("M") + "(" + df.format((double)sexs.get("M")/(sexs.get("F") + sexs.get("M")) * 100) + "%)", sexs.get("M"));
                            spdataset.setValue("Female : " + sexs.get("F") + "("  + df.format((double)sexs.get("F")/(sexs.get("F") + sexs.get("M")) * 100) + "%)", sexs.get("F"));
                            org.jfree.chart.JFreeChart spchart = ChartFactory.createPieChart3D(
                                    "id_sex" ,  // chart title
                                    (PieDataset) spdataset,         // data
                                    true,            // include legend
                                    true,
                                    false
                            );
                            JFrame spframe = new JFrame("Products sale situation");
                            ChartPanel spPanel = new ChartPanel(spchart);
                            spframe.setSize(1000, 600);
                            spframe.add(spPanel);
                            spframe.setVisible(true);

                            break;
                        default:
                            System.out.println("Wrong Input.Please try again.");
                            break;
                    }
                    break;
                case "3":
                    System.out.println("Input ID :");
                    String i_id = scanner.nextLine();
                    int productA = 0, productB = 0, productC = 0;
                    for(Object key : ids.keySet()){
                        if(key.equals(i_id)){
                            productA = ids.get(key).A;
                            productB = ids.get(key).B;
                            productC = ids.get(key).C;
                        }
                    }

                    DefaultCategoryDataset ibdataset = new DefaultCategoryDataset();
                    ibdataset.addValue(productA, "", "ProductA");
                    ibdataset.addValue(productB, "", "ProductB");
                    ibdataset.addValue(productC, "", "ProductC");
                    org.jfree.chart.JFreeChart ibchart = ChartFactory.createBarChart(
                            i_id + " purchase history", // 圖的標題
                            "Products",  // x 座標標題
                            "Quantity", // y 座標標題
                            ibdataset, // 你放數據的地方
                            PlotOrientation.VERTICAL, // 圖表方向：水平、垂直
                            false,     // 是否顯示圖例
                            true,     // 是否 tooltips 工具
                            false     // 是否生成URL
                    );
                    JFrame ibframe = new JFrame("Products sale situation");
                    ChartPanel ibPanel = new ChartPanel(ibchart);
                    ibframe.setSize(1000, 600);
                    ibframe.add(ibPanel);
                    ibframe.setVisible(true);

                    break;
                default:
                    System.out.println("Wrong Input.Please try again.");
                    break;
            }
        }
    }
}