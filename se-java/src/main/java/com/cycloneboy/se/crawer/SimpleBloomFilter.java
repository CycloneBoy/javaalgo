package com.cycloneboy.se.crawer;

import java.util.BitSet;

/**
 * Created by CycloneBoy on 2017/10/11.
 * Java网络爬虫（十）--海量URL去重之布隆过滤器
 * http://blog.csdn.net/championhengyi/article/details/72885500
 *
 * 1.创建一个布隆过滤器，开辟一个足够的位空间（二进制向量）；
 2.设计一些种子数，用来产生一些列不同的映射函数（哈希函数）；
 3.设计每个url占多少位，给每个url分配多少字节的存储空间；
 4.使用一系列的哈希函数对此url中的每一个元素进行计算，产生一系列的随机数，也就是信息指纹；
 5.将信息指纹分配到刚才的url所占的字节空间；
 6.将信息指纹所对应的一系列十进制数字对应到布隆过滤器的相应位置上，判断是否已经存在于布隆过滤其之中。
 */
public class SimpleBloomFilter {
    //设置每个字符串在布隆过滤器中所占的位的大小（24位）
    private static final int DEFAULT_SIZE = 2 << 24;
    //产生随机数的种子，可产生6个不同的随机数产生器
    private static final int[] seeds = new int[] {7, 11, 13, 31, 37, 61};
    //Java中的按位存储的思想，其算法的具体实现（布隆过滤器）
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    //根据随机数的种子，创建6个哈希函数
    private SimpleHash[] func = new SimpleHash[seeds.length];

    //设置布隆过滤器所对应k（6）个哈希函数
    public SimpleBloomFilter() {
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    // 将URL添加进来
    public void add(String value){
        for(SimpleHash f : func){
            bits.set(f.hash(value),true);
        }
    }

    //是否已经包含该URL
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }

        boolean ret = true;
        //根据此URL得到在布隆过滤器中的对应位，并判断其标志位（6个不同的哈希函数产生6种不同的映射）
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }

        return ret;
    }


    public static class SimpleHash {
        private int cap;
        private int seed;

        //默认构造器，哈希表长默认为DEFAULT_SIZE大小，此哈希函数的种子为seed
        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();

            for (int i = 0; i < len; i++) {
                //将此URL用哈希函数产生一个值（使用到了集合中的每一个元素）
                result = seed * result + value.charAt(i);
            }

            //产生单个信息指纹
            return (cap - 1) & result;
        }
    }


    public static void main(String[] args) {
        String value = "stone2083@yahoo.cn";
        SimpleBloomFilter filter = new SimpleBloomFilter();

        System.out.println(filter.contains(value));

        filter.add(value);
        System.out.println(filter.contains(value));

    }
}
