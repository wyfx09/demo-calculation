package com.example.democalculation;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 1、BigDecimal的四舍五入的RoundingMode 选择的介绍：https://blog.csdn.net/well386/article/details/53945796
 * 2、数值的格式化；
 * 3、精度问题；
 * 4、科学计数法；
 *
 */
public class BigDecimalTest {
    /**
     * 实例化BIgDecimal时，参数为浮点数与字符串的区别，实验证明： 我们计算时要用字符串作为参数
     * =new BigDecimal(12.350) 与  new BigDecimal("12.350");
     */
    @Test
    public void test1(){
        //有精度问题
        BigDecimal a =new BigDecimal(12.350);
        //最佳选择方式
        BigDecimal b = new BigDecimal("12.350");
    }

    /**
     * 去掉BigDecimal后无用的零
     */
    @Test
    public  void test2(){
        BigDecimal b = new BigDecimal("12.3500005000");
        String result = b .stripTrailingZeros().toPlainString();
    }


    /**
     * 格式化
     */
    @Test
    public void test3() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位

        BigDecimal loanAmount = new BigDecimal("150.48"); //贷款金额
        BigDecimal interestRate = new BigDecimal("0.008"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘

        System.out.println("贷款金额:\t" + currency.format(loanAmount)); //贷款金额: ￥150.48
        System.out.println("利率:\t" + percent.format(interestRate));  //利率: 0.8%
        System.out.println("利息:\t" + currency.format(interest)); //利息: ￥1.20
    }

    /**
     * 科学计数法
     */
    @Test
    public void test5() {
        BigDecimal bd = new BigDecimal("3.40256010353E11");
        String result = bd.toPlainString();
        System.out.println(result);  //340256010353
    }


    /**
     * 数值格式化
     */
    @Test
    public void test6() {
        DecimalFormat df = new DecimalFormat();
        double data = 1234.56789; //格式化之前的数字

        //1、定义要显示的数字的格式（这种方式会四舍五入）
        String style = "0.0";
        df.applyPattern(style);
        System.out.println("1-->" + df.format(data));  //1234.6

        //2、在格式后添加诸如单位等字符
        style = "0.000 kg";
        df.applyPattern(style);
        System.out.println("2-->" + df.format(data));  //01234.568 kg


        //3、 模式中的"#"表示如果该位存在字符，则显示字符，如果不存在，则不显示。
        style = "##000.0 kg";
        df.applyPattern(style);
        System.out.println("3-->" + df.format(data));  //1234.568 kg

        //4、 模式中的"-"表示输出为负数，要放在最前面
        style = "-000.000";
        df.applyPattern(style);
        System.out.println("4-->" + df.format(data)); //-1234.568


        //5、 模式中的","在数字中添加逗号，方便读数字
        style = "-0,000.0#";
        df.applyPattern(style);
        System.out.println("5-->" + df.format(data));  //5-->-1,234.57


        //6、模式中的"E"表示输出为指数，"E"之前的字符串是底数的格式，
        // "E"之后的是字符串是指数的格式
        style = "0.00E000";
        df.applyPattern(style);
        System.out.println("6-->" + df.format(data));  //6-->1.23E003


        //7、 模式中的"%"表示乘以100并显示为百分数，要放在最后。
        style = "0.00%";
        df.applyPattern(style);
        System.out.println("7-->" + df.format(data));  //7-->123456.79%


        //8、 模式中的"\u2030"表示乘以1000并显示为千分数，要放在最后。
        style = "0.00\u2030";
        //在构造函数中设置数字格式
        DecimalFormat df1 = new DecimalFormat(style);
        //df.applyPattern(style);
        System.out.println("8-->" + df1.format(data));  //8-->1234567.89‰
    }

}
