package com.quick.hbase;

import com.quick.hbase.config.HBaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author wangxc
 * @date: 2019/7/20 下午5:45
 */
@RestController
public class HBaseClientController {

    private final static String TABLE = "quick-hbase-table";
    private final static String TABLE_FAM_1 = "quick";
    private final static String TABLE_FAM_2 = "hbase";

    @Autowired
    private HBaseClient hBaseClient;

    @RequestMapping(value = "/hbase/createTable", method = RequestMethod.GET)
    public void createTable() throws IOException {
        hBaseClient.createTable(TABLE, TABLE_FAM_1, TABLE_FAM_2);
    }

    /**
     * 向TABLE中插入一条记录，列族quick下，写了两个key，speed和感觉feel，列族hbase下插入三个key 动作action、时间time和用户user，类似一个日志
     */
    @RequestMapping(value = "/hbase/insertOrUpdate", method = RequestMethod.GET)
    public void insertOrUpdate() throws IOException {
        hBaseClient.insertOrUpdate(TABLE, "1", TABLE_FAM_1, "speed", "1km/h");
        hBaseClient.insertOrUpdate(TABLE, "1", TABLE_FAM_1, "feel", "better");
        hBaseClient.insertOrUpdate(TABLE, "1", TABLE_FAM_2, "action", "create table");
        hBaseClient.insertOrUpdate(TABLE, "1", TABLE_FAM_2, "time", "2019年07月20日17:52:53");
        hBaseClient.insertOrUpdate(TABLE, "1", TABLE_FAM_2, "user", "admin");
        /**
         * shell 结果
         * hbase(main):007:0> scan 'quick-hbase-table'
         * ROW                                          COLUMN+CELL
         *  1                                           column=hbase:action,
         *  timestamp=1563616496366, value=create table
         *  1                                           column=hbase:time,
         *  timestamp=1563616496379, value=2019\xE5\xB9\xB407\xE6\x9C\x8820\xE6\x97\xA517:52:53
         *  1                                           column=hbase:user,
         *  timestamp=1563616496384, value=admin
         *  1                                           column=quick:feel,
         *  timestamp=1563616496362, value=better
         *  1                                           column=quick:speed,
         *  timestamp=1563616496353, value=1km/h
         * 1 row(s)
         */
        hBaseClient.insertOrUpdate(TABLE, "2", TABLE_FAM_2, "user", "admin");

    }

    @RequestMapping(value = "/hbase/deleteRow", method = RequestMethod.GET)
    public void deleteRow() throws IOException {
        hBaseClient.deleteRow(TABLE, "2");
    }


    @RequestMapping(value = "/hbase/deleteColumnFamily", method = RequestMethod.GET)
    public void deleteColumnFamily() throws IOException {
        hBaseClient.deleteColumnFamily(TABLE, "1", TABLE_FAM_2);
    }

    @RequestMapping(value = "/hbase/deleteColumn", method = RequestMethod.GET)
    public void deleteColumn() throws IOException {
        hBaseClient.deleteColumn(TABLE, "1", TABLE_FAM_2, "action");
    }

    @RequestMapping(value = "/hbase/deleteTable", method = RequestMethod.GET)
    public void deleteTable() throws IOException {
        hBaseClient.deleteTable(TABLE);
    }

    @RequestMapping(value = "/hbase/getValue", method = RequestMethod.GET)
    public void getValue() {
        String result = hBaseClient.getValue(TABLE, "1", TABLE_FAM_2, "time");
        System.out.println(result);

    }

    @RequestMapping(value = "/hbase/selectOneRow", method = RequestMethod.GET)
    public String selectOneRoxw() throws IOException {
        return hBaseClient.selectOneRow(TABLE, "1");
    }

    @RequestMapping(value = "/hbase/scanTable", method = RequestMethod.GET)
    public void scanTable() throws IOException {
        hBaseClient.scanTable(TABLE, "{FILTER=>\"PrefixFilter('2019')\"");
    }

    @RequestMapping(value = "/hbase/tableExists", method = RequestMethod.GET)
    public void tableExists() throws IOException {
        System.out.println(hBaseClient.tableExists(TABLE));
    }

    //@RequestMapping("/test/emop2")
    //@ResponseBody
    //public Object getMerchantList3(HttpServletRequest request) throws InterruptedException {
    //    OneApm.addCustomMetric(CustomMetric.newBuilder().name("sds").value(
    //            "fff").build());
    //    return "/test/emop/";
    //}
}
