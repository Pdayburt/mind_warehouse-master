package com.mind.mind_warehouse.util;

import java.text.SimpleDateFormat;
import java.util.*;

public class CodeCreateUtil {
    private static Random random;
    private static SimpleDateFormat simpleDateFormat;
    private static List<String> preList = Arrays.asList(
            "wa", "su", "un", "pr", "prt", "wao", "wai", "wam", "eq", "wap", "pui", "pur", "si", "sr", "fit",
            "au","st","wad","cu"
    );

    /**
     * 生成随机编码
     * @param prefix
     * @return
     */
    public static String getCode(String prefix) {
        String code = null;
        String rs = "";

        if (preList.contains(prefix)) {
            random = new Random();
            simpleDateFormat = new SimpleDateFormat("yyMMdd");
            String date = simpleDateFormat.format(new Date());
            for (int i = 0; i < 6; i++) {
                rs += random.nextInt(10);
            }
            code = prefix + date + rs;
        }
        return code;
    }

    /**
     * 生成交易流水号 12位数字
     * @return
     */
    public static String getTradeNo() {
        String tradeNo;
        String rs = "";
        random = new Random();
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        for (int i = 0; i < 6; i++) {
            rs += random.nextInt(10);
        }
        tradeNo = date + rs;
        return tradeNo;
    }

    /**
     * 生成批次 10位数字
     * @return
     */
    public static String getBatch() {
        String batch;
        String rs = "";
        random = new Random();
        simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String date = simpleDateFormat.format(new Date());
        for (int i = 0; i < 4; i++) {
            rs += random.nextInt(10);
        }
        batch = date + rs;
        return batch;
    }
}
