package com.quick.redis.service.impl;

import com.quick.redis.service.CompanyService;
import com.quick.redis.util.KeyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/9/26
 * Time: 21:29
 * Description:
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private Logger logger = LogManager.getLogger(CompanyServiceImpl.class);

    private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
    private static String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private static String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer isCompany(String companyName) {
        try {
            Integer result = 0;
            String key = KeyUtil.COMPANY_KEY + companyName;
            String sk = KeyUtil.SCHOOL_KEY + companyName;
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
                Random random = new Random();
                result = random.nextInt(1000);
                redisTemplate.opsForValue().set(key, result);
                redisTemplate.opsForValue().set(sk, result);


            } else {
                result = Integer.parseInt(o.toString());
            }
            return result;
        } catch (Exception e) {
            logger.error(e.fillInStackTrace());
            return 1;
        }

    }

    @Override
    public Integer add() {

        for(int i=0;i<2;i++) {
            String key = "Resume:company:" + getChineseName();
            redisTemplate.opsForValue().set(key, i);
        }

        return 2;
    }

    @Override
    public Integer del() {
//        redisTemplate.
        return null;
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }


    @Override
    public String get(String key) {

        return stringRedisTemplate.opsForValue().get(key);
    }

    private static String name_sex = "";

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    private static String getChineseName() {
        int index=getNum(0, firstName.length()-1);
        String first=firstName.substring(index, index+1);
        int sex=getNum(0,1);
        String str=boy;
        int length=boy.length();
        if(sex==0){
            str=girl;
            length=girl.length();
            name_sex = "女";
        }else {
            name_sex="男";
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        //添加名称为hash (key) 的hash元素
        jedis.hmset("hash", map);
        //向名称为hash的hash中添加key为key5，value 为value5元素
        jedis.hset("hash", "key5", "value5");
        //返回hash的全部元素
        System.out.println("散列hash的所有键值对为: " + jedis.hgetAll("hash"));
        //返回hash键值对的全部键
        System.out.println("散列hash的所有键为: " + jedis.hkeys("hash"));
        //返回hash键值对的全部值
        System.out.println("散列hash的所有值为: " + jedis.hvals("hash"));
        System.out.println("将key6保存的值加上一个整数,如果key6不存在则添加key6: " + jedis.hincrBy("hash","key6",6));
        System.out.println("散列hash的所有键值对为: " + jedis.hgetAll("hash"));
        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6: " + jedis.hincrBy("hash","key6",9));
        System.out.println("散列hash的所有键值对为: " + jedis.hgetAll("hash"));
        System.out.println("删除一个或者多个键值对: " + jedis.hdel("hash","key2"));
        System.out.println("散列hash的所有键值对为: " + jedis.hgetAll("hash"));
        System.out.println("散列hash中键值对的个数: " + jedis.hlen("hash"));
        System.out.println("判断hash中是否存在key2: " + jedis.hexists("hash","key2"));
        System.out.println("判断hash中是否存在key3: " + jedis.hexists("hash","key3"));
        System.out.println("获取hash中的值: " + jedis.hmget("hash","key3"));
        System.out.println("获取hash中的值: " + jedis.hmget("hash","key3", "key4"));

        Pipeline pipelined = jedis.pipelined();
        pipelined.set("aaa","ss");
        pipelined.set("aaaa","ss");
        pipelined.sync();

    }
}
