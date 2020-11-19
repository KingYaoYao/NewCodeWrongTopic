package niukewang.nov19;

import java.util.Arrays;

/*
* java语言的下面几种数组复制方法中，哪个效率最高？
* for 循环逐一复制    遍历数组逐一复制
* System.arraycopy  本地方法。native
* Array.copyOf
* 使用clone方法
*
*
* */
public class CopySpeed implements Cloneable{
    /*
     *
     * 数组复制速度 1.System.arraycopy() 2.clone() 3.Arrays.copyof() 4.for()
     *
     *
     *
     */

    int i = 0;
    String value = "123";

    /*
     * 基本数据类型测试
     *
     * */
    public long[] CopySpeedTest(int[] t) {
        /*
         * 初始化所有
         *
         */
        int length = t.length;
        int[] systemCopy = new int[t.length];
        int[] clone = new int[t.length];
        int[] arraysCopyOf = new int[t.length];
        int[] For = new int[t.length];

        /*
         * 执行复制操作,并统计时间
         *
         */

        long begin = System.currentTimeMillis();
        System.arraycopy(t, 0, systemCopy, 0, t.length);
        long end = System.currentTimeMillis();
        long arraycopyTimes = end - begin;

        begin = System.currentTimeMillis();
        clone = t.clone();
        end = System.currentTimeMillis();
        long cloneTimes = end - begin;

        begin = System.currentTimeMillis();
        arraysCopyOf = Arrays.copyOf(t, t.length);
        end = System.currentTimeMillis();
        long arraysCopyOfTimes = end - begin;

        /*
         * 为了方便查看,这里设定下面程序只执行一次
         *
         */
        if (i == 0) {

            /*
             * 查看哈希值
             */
            System.out.println("t:\t" + t.hashCode());
            System.out.println("systemCopy:\t" + systemCopy.hashCode());
            System.out.println("clone:\t" + clone.hashCode());
            System.out.println("arraysCopyOf:\t" + arraysCopyOf.hashCode());

            i++;
        }

        /*
         * 运行时间统计
         *
         */
        long[] times = new long[3];
        times[0] = arraycopyTimes;
        times[1] = cloneTimes;
        times[2] = arraysCopyOfTimes;

        return times;

    }


    /*
     *
     * 引用数据类型结果
     *
     * */

    public long[] CopySpeedTest(CopySpeed[] t) {

        /*
         * 初始化所有
         *
         */
        int length = t.length;
        CopySpeed[] systemCopy = new CopySpeed[length];
        CopySpeed[] clone = new CopySpeed[length];
        CopySpeed[] arraysCopyOf = new CopySpeed[length];

        /*
         * 执行复制操作,并统计时间
         *
         */
        long begin = System.currentTimeMillis();
        System.arraycopy(t, 0, systemCopy, 0, t.length);
        long end = System.currentTimeMillis();
        long arraycopyTimes = end - begin;

        begin = System.currentTimeMillis();
        clone = t.clone();
        end = System.currentTimeMillis();
        long cloneTimes = end - begin;

        begin = System.currentTimeMillis();
        arraysCopyOf = Arrays.copyOf(t, t.length);
        end = System.currentTimeMillis();
        long arraysCopyOfTimes = end - begin;

        /*
         * 为了方便查看,这里设定下面程序只执行一次
         *
         */
        if (i == 1) {

            /*
             * 查看哈希值
             */
            System.out.println("t[0]:\t" + t[0].hashCode());
            System.out.println("systemCopy[0]:\t" + systemCopy[0].hashCode());
            System.out.println("clone[0]:\t" + clone[0].hashCode());
            System.out.println("arraysCopyOf[0]:\t" + arraysCopyOf[0].hashCode());

            /*
             * 修改新对象的值来判断是浅复制还是深复制
             *
             */
            System.out.println("深浅复制判断,以value属性判断");
            System.out.println("修改前t[0]:\t" + t[0].value);
            System.out.println("修改前systemCopy[0]:\t" + systemCopy[0].value);
            System.out.println("修改前clone[0]:\t" + clone[0].value);
            System.out.println("修改前arraysCopyOf[0]:\t" + arraysCopyOf[0].value);

            System.out.println("---------------------------");
            t[0].value = "t";
            systemCopy[0].value = "systemCopy";
            clone[0].value = "clone";
            arraysCopyOf[0].value = "arraysCopyOf";
            System.out.println("修改后t[0]:\t" + t[0].value);
            System.out.println("修改后systemCopy[0]:\t" + systemCopy[0].value);
            System.out.println("修改后clone[0]:\t" + clone[0].value);
            System.out.println("修改后arraysCopyOf[0]:\t" + arraysCopyOf[0].value);

            i++;
        }

        /*
         * 运行时间统计
         */
        long[] times = new long[3];
        times[0] = arraycopyTimes;
        times[1] = cloneTimes;
        times[2] = arraysCopyOfTimes;

        return times;

    }



    public static void main(String args[]) {

        CopySpeed speed = new CopySpeed();

        System.out.println("基本类型");
        long[] baseTimes = new long[] { 0, 0, 0 };
        int[] baseArrays = new int[10000000];
        for (int i = 0; i < baseArrays.length; i++) {
            baseArrays[i] = i;
        }
        for (int i = 0; i < 20; i++) {
            // System.out.print(i+"次");
            long[] temp = speed.CopySpeedTest(baseArrays);
            baseTimes[0] += temp[0];
            baseTimes[1] += temp[2];
            baseTimes[2] += temp[1];
            // System.out.println();
        }

        baseTimes[0] /= 20;
        baseTimes[1] /= 20;
        baseTimes[2] /= 20;
        System.out.println(Arrays.toString(baseTimes));

        System.out.println("引用类型");
        long[] ObjectTimes = new long[] { 0, 0, 0 };
        CopySpeed[] ObjectArrays = new CopySpeed[10000000];
        for (int i = 0; i < ObjectArrays.length; i++) {
            ObjectArrays[i] = new CopySpeed();
        }
        for (int i = 0; i < 20; i++) {
            // System.out.print(i+"次");
            long[] temp = speed.CopySpeedTest(ObjectArrays);
            ObjectTimes[0] += temp[0];
            ObjectTimes[1] += temp[1];
            ObjectTimes[2] += temp[2];
            // System.out.println();
        }

        ObjectTimes[0] /= 20;
        ObjectTimes[1] /= 20;
        ObjectTimes[2] /= 20;
        System.out.println(Arrays.toString(ObjectTimes));

    }
        /*基本类型
t:	1552787810
systemCopy:	1361960727
clone:	739498517
arraysCopyOf:	125130493
[10, 14, 15]

对于基本数据类型可以看到4个对象的hashcode完全不同，也就验证了前面的深复制的猜想

而10，14，15的运行时间也能证明他们的效率差别，当然因为是基本数据类型，
而且数据量不算恐怖，所以差距并不大，特别是clone和arraysCopyOf
可以明显的看到，clone因为JNI的耽误，而Arrays.CopyOf因为System.Copy的加成速度也不赖。


引用类型
t[0]:	166239592
systemCopy[0]:	166239592
clone[0]:	166239592
arraysCopyOf[0]:	166239592

对于引用类型，额可以看到4个对象的hashcode值完全相同，说明指向的是同一块内存

深浅复制判断,以value属性判断
修改前t[0]:	123
修改前systemCopy[0]:	123
修改前clone[0]:	123
修改前arraysCopyOf[0]:	123
---------------------------
修改后t[0]:	t
修改后systemCopy[0]:	t
修改后clone[0]:	t
修改后arraysCopyOf[0]:	t
[22, 31, 55]

这里以对象的属性value做测试，可以看到t[0]里面的数据更改后，所有的数据都更改了，

说明3种方法对于对象都是浅复制
        * */
}
